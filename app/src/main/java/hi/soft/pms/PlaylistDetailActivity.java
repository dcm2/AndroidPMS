package hi.soft.pms;

import android.content.Intent;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import hi.soft.pms.fetchers.SongsFetcher;
import hi.soft.pms.model.Song;
import hi.soft.pms.posts.VotePost;

public class PlaylistDetailActivity extends AppCompatActivity {

    private TextView mPlaylistName;
    private ListView mListView;
    private String mCurrentUser;
    private String mCurrentPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_detail);

        mPlaylistName = (TextView) findViewById(R.id.playlistName_to_display);
        mPlaylistName.setText(getIntent().getStringExtra("playlistName"));

        mCurrentPlaylist = getIntent().getStringExtra("playlistName");
        mCurrentUser = getIntent().getStringExtra("fromUser");

        SongsRequest songsRequestTask = new SongsRequest();
        songsRequestTask.execute(mCurrentUser, mCurrentPlaylist);
    }

    //class definition of the AsyncTask that gets the Songs of the current Playlist
    private class SongsRequest extends AsyncTask<String, Void, List<Song>>{

        @Override
        protected List<Song> doInBackground(String... strings) {

            String currentUser = strings[0];
            String currentPlaylist = strings[1];

            return new SongsFetcher().fetchSongs(currentUser, currentPlaylist);
        }

        @Override
        protected void onPostExecute(List<Song> songs) {
            super.onPostExecute(songs);

            //creates a simple ArrayList<String> with just the name of the songs
            final ArrayList<String> songsArray = new ArrayList<>();
            for(int i = 0; i < songs.size(); i++){
                songsArray.add(songs.get(i).getTitle());
            }

            //creates listView to display
            mListView = findViewById(R.id.song_list_to_display);
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter(PlaylistDetailActivity.this, android.R.layout.simple_list_item_1, songsArray);
            mListView.setAdapter(arrayAdapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(PlaylistDetailActivity.this, "I want to Upvote: " + songsArray.get(position), Toast.LENGTH_LONG).show();

                    VotingSystem votingSystemTask = new VotingSystem();
                    votingSystemTask.execute("1", mCurrentUser, mCurrentPlaylist, songsArray.get(position));


                }
            });

            mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(PlaylistDetailActivity.this, "I want to Downvote: " + songsArray.get(position), Toast.LENGTH_LONG).show();

                    VotingSystem votingSystemTask = new VotingSystem();
                    votingSystemTask.execute("-1", mCurrentUser, mCurrentPlaylist, songsArray.get(position));

                    return true;
                }
            });

        }
    }

    //class definition of the AsyncTask that calls the POST request on the server to allow voting system
    private class VotingSystem extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {

            String vote = strings[0];
            String userName = strings[1];
            String playlistTitle = strings[2];
            String songTitle = strings[3];


            try {
                new VotePost().postThisVote(vote, userName, playlistTitle, songTitle);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }










}
