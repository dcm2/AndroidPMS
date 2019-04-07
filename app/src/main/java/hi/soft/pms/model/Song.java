package hi.soft.pms.model;

import com.google.gson.annotations.SerializedName;


public class Song {

    @SerializedName(value = "id")
    private Long mId;
    @SerializedName(value = "songInfo")
    private SongInfo mSongInfo;
    @SerializedName(value = "belongsTo")
    private Playlist mBelongsTo;
    @SerializedName(value = "title")
    private String mTitle;
    @SerializedName(value = "upVotes")
    private int mUpVotes;
    @SerializedName(value = "downVotes")
    private int mDownVotes;
    @SerializedName(value = "votes")
    private int mTotalVotes;

    //Two constructors (may not be necessary but we have these for now, susceptible to be changed)
    public Song(){ }
    public Song(SongInfo songInfo){
        this.mSongInfo = songInfo;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public SongInfo getSongInfo() {
        return mSongInfo;
    }

    public void setSongInfo(SongInfo songInfo) {
        mSongInfo = songInfo;
    }

    public Playlist getBelongsTo() {
        return mBelongsTo;
    }

    public void setBelongsTo(Playlist belongsTo) {
        mBelongsTo = belongsTo;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getUpVotes() {
        return mUpVotes;
    }

    public void setUpVotes(int upVotes) {
        mUpVotes = upVotes;
    }

    public int getDownVotes() {
        return mDownVotes;
    }

    public void setDownVotes(int downVotes) {
        mDownVotes = downVotes;
    }

    public int getTotalVotes() {
        return mTotalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        mTotalVotes = totalVotes;
    }
}
