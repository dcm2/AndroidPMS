package hi.soft.pms.posts;

import com.google.gson.Gson;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import hi.soft.pms.model.Playlist;

public class PlaylistPost {

    private final String BASE_URL = "http://10.0.2.2:8080/pms_db/user";

    public void playlistPost(String userName, String playlistName) throws IOException {

        //initiates the connection with the db through http
        String path = BASE_URL + "/" + userName + "/playlists";

        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        try {
            //create playlist to send in JSON format
            Playlist createdPlaylist = new Playlist(playlistName);
            String jsonPlaylist = new Gson().toJson(createdPlaylist);

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");

            DataOutputStream output = new DataOutputStream(conn.getOutputStream());
            output.writeBytes(jsonPlaylist);

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
