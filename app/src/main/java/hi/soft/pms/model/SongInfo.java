package hi.soft.pms.model;

import com.google.gson.annotations.SerializedName;


public class SongInfo {

    @SerializedName(value = "id")
    private Long mId;
    @SerializedName(value = "title")
    private String mTitle;
    @SerializedName(value = "artist")
    private String mArtist;
    @SerializedName(value = "album")
    private String mAlbum;
    @SerializedName(value = "length")
    private int mDuration;
    @SerializedName(value = "genre")
    private String mGenre;
    //private ArrayList<Song> mUsedBySong = new ArrayList<>();

    //Two constructors (may not be necessary but we have these for now, susceptible to be changed)
    public SongInfo(){ }
    public SongInfo(String title, String artist, String album, int duration, String genre){
        this.mTitle = title;
        this.mArtist = artist;
        this.mAlbum = album;
        this.mDuration = duration;
        this.mGenre = genre;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
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

    /*public ArrayList<Song> getUsedBySong() {
        return mUsedBySong;
    }*/

    /*public void setUsedBySong(ArrayList<Song> usedBySong) {
        mUsedBySong = usedBySong;
    }*/
}
