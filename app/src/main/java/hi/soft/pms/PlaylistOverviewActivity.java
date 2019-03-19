package hi.soft.pms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class PlaylistOverviewActivity extends AppCompatActivity {

    private static final String CURRENT_USER = null;
    private User mCurrentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_overview);

        mCurrentUser = (User) getIntent().getSerializableExtra("user");

        String toDisplay = mCurrentUser.toString();

        Toast.makeText(PlaylistOverviewActivity.this, toDisplay, Toast.LENGTH_LONG).show();
    }

    public static Intent newIntent(Context packageContext, User user){
        Intent i = new Intent(packageContext, PlaylistOverviewActivity.class);
        i.putExtra(CURRENT_USER, user);
        return i;
    }
}

