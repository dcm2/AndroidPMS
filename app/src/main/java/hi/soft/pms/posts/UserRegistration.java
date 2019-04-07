package hi.soft.pms.posts;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UserRegistration {

    private final String BASE_USER_URL = "http://10.0.2.2:8080/pms_db/user";

    public void userPost(String userNameToPost, String emailToPost, String passwordToPost) throws IOException {

        //initiates the connection with the db through http
        URL url = new URL(BASE_USER_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        try {

            //create the jsonObject to send
            JSONObject jUserToPost = new JSONObject();

            jUserToPost.put("userName", userNameToPost);
            jUserToPost.put("email", emailToPost);
            jUserToPost.put("password", passwordToPost);

            //prints jsonObject for debugging purposes
            System.out.println(jUserToPost.toString());

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");

            DataOutputStream output = new DataOutputStream(conn.getOutputStream());
            output.writeBytes(jUserToPost.toString());

            output.flush();
            output.close();

            conn.connect();
            String status = conn.getResponseMessage();
            System.out.println(status);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }
}
