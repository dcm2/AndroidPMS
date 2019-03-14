package hi.soft.pms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

                String user = mUserNameField.getText().toString();
                String psswd = mPasswordField.getText().toString();

                User currentUser = createUser(user, psswd);

                Intent i = PlaylistOverviewActivity.newIntent(LoginActivity.this, currentUser);
                i.putExtra("user", currentUser);
                startActivity(i);
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

    public User createUser(String userName, String psswd){
        User currentUser = new User(userName, psswd);
        return currentUser;
    }

}
