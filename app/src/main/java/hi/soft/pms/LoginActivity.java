package hi.soft.pms;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import hi.soft.pms.fetchers.UserFetcher;
import hi.soft.pms.model.User;

public class LoginActivity extends AppCompatActivity {

    private Button mLoginButton;
    private EditText mUserNameField;
    private EditText mPasswordField;
    private TextView mGotoRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserNameField = (EditText) findViewById(R.id.login_username);
        mPasswordField = (EditText) findViewById(R.id.login_password);

        mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String findThisUser = mUserNameField.getText().toString();
                UserRequest userRequestTask = new UserRequest();
                userRequestTask.execute(findThisUser);
            }
        });

        mGotoRegistration = findViewById(R.id.login_goto_registration);
        mGotoRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoRegistration = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(gotoRegistration);
            }
        });
    }

    public boolean validation(String enteredUserName, String enteredPassword, User prospectUser) {
        return ((enteredUserName.equals(prospectUser.getUserName()))&&(enteredPassword.equals(prospectUser.getPassword())));
    }

    //class definition of the AsyncTask that gets the User to compare credentials!
    private class UserRequest extends AsyncTask<String, Void, User>{

        @Override
        protected User doInBackground(String... strings) {

            String findThisUser = strings[0];

            return new UserFetcher().fetchUserCredentials(findThisUser);
        }

        @Override
        protected void onPostExecute(User prospectUser) {
            super.onPostExecute(prospectUser);

            String enteredUserName = mUserNameField.getText().toString();
            String enteredPassword = mPasswordField.getText().toString();

            if(validation(enteredUserName, enteredPassword, prospectUser)){
                //creates intent to go to next activity
                Intent i = new Intent(LoginActivity.this, PlaylistOverviewActivity.class);
                i.putExtra("currentUser", enteredUserName);
                startActivity(i);
            }
        }
    }
}
