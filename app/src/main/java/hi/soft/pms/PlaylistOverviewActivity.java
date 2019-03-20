package hi.soft.pms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlaylistOverviewActivity extends AppCompatActivity {

    private static final String CURRENT_USER = null;
    private User mCurrentUser;
    private Button mCreatePlaylistButton;
    private EditText mPlaylistName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_overview);

        //To test how to recover the User attached to the intent as an extra
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
        });
    }

    public static Intent newIntent(Context packageContext, User user){
        Intent i = new Intent(packageContext, PlaylistOverviewActivity.class);
        i.putExtra(CURRENT_USER, user);
        return i;
    }
}

