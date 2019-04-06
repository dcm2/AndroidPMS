package hi.soft.pms.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import hi.soft.pms.model.Playlist;

public class User implements Serializable {

    //private static final long serialVersionUID = 5177222050535318633L;

    @SerializedName(value = "id")
    private Long mId;
    @SerializedName(value = "userName")
    private String mUserName;
    @SerializedName(value = "password")
    private String mPassword;
    @SerializedName(value = "email")
    private String mEmail;
    @SerializedName(value = "playlists")
    private ArrayList<Playlist> mPlaylists = new ArrayList<>();


    public User(){ }

    public User(String userName, String password){
        this.mUserName = userName;
        this.mPassword = password;
    }

    public User(String userName, String password, String email){
        this.mUserName = userName;
        this.mPassword = password;
        this.mEmail = email;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
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

    public ArrayList<Playlist> getPlaylists() {
        return mPlaylists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        mPlaylists = playlists;
    }

    //for debugging purposes
    @Override
    public String toString() {
        return "userName= " + mUserName + ", ID= " + mId + ", password= " + mPassword + ", email= " + mEmail
                + ", playlists= " + mPlaylists.toString();
    }
}

