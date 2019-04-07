package hi.soft.pms;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hi.soft.pms.posts.UserRegistration;

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
                String password = mPassword.getText().toString();
                String passwordConfirmation = mPasswordConfirmation.getText().toString();

                //if email is valid
                if (!isEmailValid(email)){
                    Toast.makeText(CreateAccountActivity.this, getResources().getString(R.string.invalid_email), Toast.LENGTH_LONG).show();
                }
                else if (!isUserNameValid(userName)) {
                    Toast.makeText(CreateAccountActivity.this, getResources().getString(R.string.invalid_userName), Toast.LENGTH_LONG).show();
                }
                else if (!isPasswordValid(password)) {
                    Toast.makeText(CreateAccountActivity.this, getResources().getString(R.string.invalid_password), Toast.LENGTH_LONG).show();
                }
                else if (!doPasswordsMatch(password, passwordConfirmation)) {
                    Toast.makeText(CreateAccountActivity.this, getResources().getString(R.string.password_mismatch), Toast.LENGTH_LONG).show();
                }
                else {
                    UserPost userRegistrationTask = new UserPost();
                    userRegistrationTask.execute(userName, email, password);
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

    private boolean isEmailValid(CharSequence email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean isUserNameValid(String userName){
        String validExpression = "^[a-z0-9]{5,20}$";
        Pattern pattern = Pattern.compile(validExpression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }
    private boolean isPasswordValid(String password) {
        String validExpression = "[\\S+]{8,15}$";
        Pattern pattern = Pattern.compile(validExpression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    private boolean doPasswordsMatch(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }


    //class definition of the AsyncTask that does the POST request on the server to register a new user
    private class UserPost extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {

            String userNameToPost = strings[0];
            String emailToPost = strings[1];
            String passwordToPost = strings[2];

            try {
                new UserRegistration().userPost(userNameToPost, emailToPost, passwordToPost);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //System.out.println(":)");

            String userName = mUserName.getText().toString();

            Intent i = new Intent(CreateAccountActivity.this, PlaylistOverviewActivity.class);
            i.putExtra("currentUser", userName);
            startActivity(i);

        }
    }


}
