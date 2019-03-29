package hi.soft.pms.model;

import java.util.UUID;

public class Song {

    private UUID mId;
    private SongInfo mSongInfo;
    private Playlist mBelongsTo;
    private String mTitle;
    private int mUpVotes;
    private int mDownVotes;
    private int mTotalVotes;

    //Two constructors (may not be necessary but we have these for now, susceptible to be changed)
    public Song(){
        this.mId = UUID.randomUUID();
    }
    public Song(SongInfo songInfo){
        mId = UUID.randomUUID();
        this.mSongInfo = songInfo;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
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
