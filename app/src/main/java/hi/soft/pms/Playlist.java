package hi.soft.pms;

import java.util.ArrayList;
import java.util.UUID;

public class Playlist {


    private UUID mId;
    private String mPlaylistName;
    private User mCreator;
    private ArrayList<Song> mSongList = new ArrayList<>();
    private int mNumberSongs;
    private int mDuration;
    private ArrayList<User> mUsersList = new ArrayList<>();

    //Constructor
    public Playlist(String playlistName){
        mId = UUID.randomUUID();
        this.mPlaylistName = playlistName;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
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

    public ArrayList<User> getUsersList() {
        return mUsersList;
    }

    public void setUsersList(ArrayList<User> usersList) {
        mUsersList = usersList;
    }
}
