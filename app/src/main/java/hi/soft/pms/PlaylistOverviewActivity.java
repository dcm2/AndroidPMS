package hi.soft.pms;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import hi.soft.pms.fetchers.PlaylistFetcher;
import hi.soft.pms.model.Playlist;
import hi.soft.pms.model.User;

public class PlaylistOverviewActivity extends AppCompatActivity {

    private static final String CURRENT_USER = null;
    private String mCurrentUser;
    private Button mCreatePlaylistButton;
    private EditText mPlaylistName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_overview);

        mCurrentUser = getIntent().getStringExtra("currentUser");

        //just to check is is all fine here
        Toast.makeText(PlaylistOverviewActivity.this, mCurrentUser, Toast.LENGTH_LONG).show();

        PlaylistRequest playlistRequestTask = new PlaylistRequest();
        playlistRequestTask.execute(mCurrentUser);









        /*To test how to recover the User attached to the intent as an extra
        mCurrentUser = (User) getIntent().getSerializableExtra("user");
        Toast.makeText(PlaylistOverviewActivity.this, mCurrentUser.toString(), Toast.LENGTH_LONG).show();

        mPlaylistName = findViewById(R.id.playlist_to_create);

        mCreatePlaylistButton = findViewById(R.id.playlist_creation_button);
        mCreatePlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playlistName = mPlaylistName.getText().toString();
                mCurrentUser.getPlaylists().add(new Playlist(playlistName, mCurrentUser));

                Toast.makeText(PlaylistOverviewActivity.this, mCurrentUser.toString(), Toast.LENGTH_LONG).show();
            }
        });*/
    }

    //here goes the definition of the AsyncTask that gets the Playlists of the current user
    private class PlaylistRequest extends AsyncTask<String, Void, List<Playlist>>{

        @Override
        protected List<Playlist> doInBackground(String... strings) {

            String playlistsFromUser = strings[0];

            return new PlaylistFetcher().fetchPlaylists(playlistsFromUser);
        }

        @Override
        protected void onPostExecute(List<Playlist> playlists) {
            super.onPostExecute(playlists);

            System.out.println("done with the test! Check the following results");

            for(int i = 0; i < playlists.size(); i++) {
                System.out.println(playlists.get(i));
            }

        }
    }







    /*public static Intent newIntent(Context packageContext, User user){
        Intent i = new Intent(packageContext, PlaylistOverviewActivity.class);
        i.putExtra(CURRENT_USER, user);
        return i;
    }*/
}

