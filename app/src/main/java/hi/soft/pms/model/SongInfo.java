package hi.soft.pms.model;

import java.util.ArrayList;
import java.util.UUID;

import hi.soft.pms.model.Song;

public class SongInfo {

    private UUID mId;
    private String mTitle;
    private String mArtist;
    private String mAlbum;
    private int mDuration;
    private String mGenre;
    private ArrayList<Song> mUsedBySong = new ArrayList<>();

    //Two constructors (may not be necessary but we have these for now, susceptible to be changed)
    public SongInfo(){
        mId = UUID.randomUUID();
    }
    public SongInfo(String title, String artist, String album, int duration, String genre){
        mId = UUID.randomUUID();
        this.mTitle = title;
        this.mArtist = artist;
        this.mAlbum = album;
        this.mDuration = duration;
        this.mGenre = genre;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public void setAlbum(String album) {
        mAlbum = album;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public ArrayList<Song> getUsedBySong() {
        return mUsedBySong;
    }

    public void setUsedBySong(ArrayList<Song> usedBySong) {
        mUsedBySong = usedBySong;
    }
}
