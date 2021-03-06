package hi.soft.pms.fetchers;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import hi.soft.pms.model.Song;

public class SongsFetcher {

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

    public List<Song> fetchSongs(String userName, String playlistName){

        List<Song> mSongsFromPlaylist = new ArrayList<>();

        try {
            String url = BASE_USER_URL + userName + "/playlists/" + playlistName + "/songs";
                //for debugging purposes
                System.out.println("fetched from: " + url);

            String jsonString = getURLString(url);
            JSONArray jsonArray = new JSONArray(jsonString);

            parseSongs(mSongsFromPlaylist, jsonArray);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mSongsFromPlaylist;
    }

    private void parseSongs(List<Song> mSongsFromPlaylist, JSONArray jsonArray) throws IOException, JSONException {

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject songJSONObject = jsonArray.getJSONObject(i);

            Song song = new Gson().fromJson(songJSONObject.toString(), Song.class);

            mSongsFromPlaylist.add(song);
        }




    }


}
