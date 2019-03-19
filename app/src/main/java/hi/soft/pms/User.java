package hi.soft.pms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class User implements Serializable {

    private static final long serialVersionUID = 5177222050535318633L;

    private UUID mId;
    private String mUserName;
    private String mPassword;
    private String mEmail;
    private ArrayList<Playlist> mPlaylists = new ArrayList<>();


    public User(){
        mId = UUID.randomUUID();
    }

    public User(String userName, String password){
        mId = UUID.randomUUID();
        this.mUserName = userName;
        this.mPassword = password;
    }

    public User(String userName, String password, String email){
        this.mUserName = userName;
        this.mPassword = password;
        this.mEmail = email;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    //for debugging purposes
    @Override
    public String toString() {
        return "User -> userName=" + mUserName + "ID=" + mId + ", password=" + mPassword + ", email=" + mEmail
                + ", playlists=";
    }
}

