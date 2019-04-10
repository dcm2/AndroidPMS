package hi.soft.pms.posts;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import hi.soft.pms.model.Vote;

public class VotePost {

    private final String BASE_URL = "http://10.0.2.2:8080/pms_db/user";

    public void postThisVote(String vote, String userName, String playlistTitle, String songTitle) throws IOException {

        //initiates the connection with the db through http
        String path = BASE_URL + "/" + userName + "/playlists/" + playlistTitle + "/songs/" + songTitle;

        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        try {
            //transforms vote to send in JSON format
            Vote voteToSend = new Vote(Integer.parseInt(vote));
            String jsonVote = new Gson().toJson(voteToSend);

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");

            DataOutputStream output = new DataOutputStream(conn.getOutputStream());
            output.writeBytes(jsonVote);

            output.flush();
            output.close();

            conn.connect();
            String status = conn.getResponseMessage();
            System.out.println(status);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }
}
