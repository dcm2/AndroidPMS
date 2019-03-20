package hi.soft.pms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    private TextView mGotoLogin;
    private Button mSignUpButton;
    private EditText mEmail;
    private EditText mUserName;
    private EditText mPassword;
    private EditText mPasswordConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mPassword =  findViewById(R.id.create_password);
        mPasswordConfirmation = findViewById(R.id.create_confirm_password);

        mEmail = findViewById(R.id.create_email);
        mUserName = findViewById(R.id.create_username);

        mSignUpButton = findViewById(R.id.create_signup_button);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString();
                String userName = mUserName.getText().toString();
                String psswd = mPassword.getText().toString();
                String psswdConfirmation = mPasswordConfirmation.getText().toString();

                if (psswd.equals(psswdConfirmation)) {

                    User currentUser = new User(userName, psswd, email);

                    Intent i = PlaylistOverviewActivity.newIntent(CreateAccountActivity.this, currentUser);
                    i.putExtra("user", currentUser);
                    startActivity(i);
                } else {
                    Toast.makeText(CreateAccountActivity.this, getResources().getString(R.string.password_mismatch), Toast.LENGTH_LONG).show();
                }
            }
        });

        mGotoLogin = findViewById(R.id.create_goto_login);
        mGotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLogin = new Intent(CreateAccountActivity.this, LoginActivity.class);
                startActivity(gotoLogin);
            }
        });




    }




}
