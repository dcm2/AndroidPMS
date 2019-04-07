package hi.soft.pms.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.UUID;

public class Playlist {

    @SerializedName(value = "id")
    private Long mId;
    @SerializedName(value = "title")
    private String mPlaylistName;
    @SerializedName(value = "creator")
    private User mCreator;
    @SerializedName(value = "songList")
    private ArrayList<Song> mSongList = new ArrayList<>();
    @SerializedName(value = "numSongs")
    private int mNumberSongs;
    @SerializedName(value = "duration")
    private int mDuration;
    //private ArrayList<User> mUsersList = new ArrayList<>();

    //Constructor
    public Playlist(String playlistName){
        this.mPlaylistName = playlistName;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getPlaylistName() {
        return mPlaylistName;
    }

    public void setPlaylistName(String playlistName) {
        mPlaylistName = playlistName;
    }

    public User getCreator() {
        return mCreator;
    }

    public void setCreator(User creator) {
        mCreator = creator;
    }

    public ArrayList<Song> getSongList() {
        return mSongList;
    }

    public void setSongList(ArrayList<Song> songList) {
        mSongList = songList;
    }

    public int getNumberSongs() {
        return mNumberSongs;
    }

    public void setNumberSongs(int numberSongs) {
        mNumberSongs = numberSongs;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    /*public ArrayList<User> getUsersList() {
        return mUsersList;
    }*/

    /*public void setUsersList(ArrayList<User> usersList) {
        mUsersList = usersList;
    }*/

    //for debugging purposes
    @Override
    public String toString() {
        return "name= " + mPlaylistName + ", creator= " + mCreator.getUserName();
    }
}
