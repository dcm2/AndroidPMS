package hi.soft.pms;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hi.soft.pms.fetchers.PlaylistFetcher;
import hi.soft.pms.model.Playlist;
import hi.soft.pms.posts.PlaylistPost;

public class PlaylistOverviewActivity extends AppCompatActivity {

    private static final String CURRENT_USER = null;
    private String mCurrentUser;
    private Button mCreatePlaylistButton;
    private EditText mPlaylistToCreate;
    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_overview);

        mCurrentUser = getIntent().getStringExtra("currentUser");

        //just to check is is all fine here
        Toast.makeText(PlaylistOverviewActivity.this, mCurrentUser, Toast.LENGTH_LONG).show();


        PlaylistsRequest playlistRequestTask = new PlaylistsRequest();
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

    //class definition of the AsyncTask that gets the Playlists of the current User
    private class PlaylistsRequest extends AsyncTask<String, Void, List<Playlist>>{

        @Override
        protected List<Playlist> doInBackground(String... strings) {

            String playlistsFromUser = strings[0];

            return new PlaylistFetcher().fetchPlaylists(playlistsFromUser);
        }

        @Override
        protected void onPostExecute(List<Playlist> playlists) {
            super.onPostExecute(playlists);

            //creates a new ArrayList<String> with just the names of the playlists
            final ArrayList<String> playlistNamesArray = new ArrayList<>();
            for(int i = 0; i < playlists.size(); i++) {
                playlistNamesArray.add(playlists.get(i).getPlaylistName());
            }


            //creates listView to display
            mListView = findViewById(R.id.playlist_list_to_display);
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter(PlaylistOverviewActivity.this, android.R.layout.simple_list_item_1, playlistNamesArray);
            mListView.setAdapter(arrayAdapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(PlaylistOverviewActivity.this, "playlist: " + playlistNamesArray.get(position), Toast.LENGTH_LONG).show();

                    Intent i = new Intent(PlaylistOverviewActivity.this, PlaylistDetailActivity.class);
                    i.putExtra("playlistName", playlistNamesArray.get(position));
                    i.putExtra("fromUser", getIntent().getStringExtra("currentUser"));
                    startActivity(i);


                }
            });

            mPlaylistToCreate = findViewById(R.id.playlist_to_create);
            mCreatePlaylistButton = findViewById(R.id.playlist_creation_button);
            mCreatePlaylistButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String playlistToCreate = mPlaylistToCreate.getText().toString();

                    if (isPlaylistNameValid(playlistToCreate)){

                        //adds it to the db!
                        PlaylistCreation playlistCreationTask = new PlaylistCreation();
                        playlistCreationTask.execute(getIntent().getStringExtra("currentUser"), playlistToCreate);
                            //prints for debugging purposes
                            System.out.println(getIntent().getStringExtra("currentUser") + ", and " + playlistToCreate);


                        //adds it to the listView
                        playlistNamesArray.add(playlistToCreate);
                        arrayAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(PlaylistOverviewActivity.this, "Sorry, playlist name is not valid ", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }

    private boolean isPlaylistNameValid(String playlistNameToCreate){
        String validExpression = "^[^\\s][a-zA-Z0-9\\W ]+";
        Pattern pattern = Pattern.compile(validExpression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(playlistNameToCreate);
        return matcher.matches();
    }

    //class definition of the AsyncTask that does the POST request on the server to create a new playlist
    private class PlaylistCreation extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {

            String userName = strings[0];
            String playlistName = strings[1];

            try {
                new PlaylistPost().playlistPost(userName, playlistName);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }








    /*public static Intent newIntent(Context packageContext, User user){
        Intent i = new Intent(packageContext, PlaylistOverviewActivity.class);
        i.putExtra(CURRENT_USER, user);
        return i;
    }*/
}

