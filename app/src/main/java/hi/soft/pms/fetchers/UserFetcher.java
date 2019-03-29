package hi.soft.pms.fetchers;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import hi.soft.pms.model.User;

public class UserFetcher {

    private final String BASE_USER_URL = "http://10.0.2.2:8080/pms_db/user/";

    public byte[] getUrlBytes(String urlSpec) throws IOException {

        URL url = new URL(urlSpec);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = conn.getInputStream();

            if(conn.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw new IOException(conn.getResponseMessage() + ": with" + urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while( (bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }

            out.close();
            return out.toByteArray();
        } finally {
            conn.disconnect();
        }
    }

    public String getURLString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public User fetchUserCredentials(String userNameToFind) {

        User prospectUser = new User();

        try {
            String url = BASE_USER_URL + userNameToFind;

            String jsonString = getURLString(url);
            JSONObject jsonBody = new JSONObject(jsonString);

            parseUserCredentials(prospectUser, jsonBody);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return prospectUser;
    }

    private void parseUserCredentials (User prospectUser, JSONObject jsonBody) throws IOException, JSONException{

        prospectUser.setUserName(jsonBody.getString("userName"));
        prospectUser.setPassword(jsonBody.getString("password"));
    }
}
