package hi.soft.pms.model;

import com.google.gson.annotations.SerializedName;

public class Vote {

    @SerializedName(value = "id")
    private Long mId;

    @SerializedName(value = "myVote")
    private int mVote;


    public Vote(int vote){
        this.mVote = vote;
    }


    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public int getVote() {
        return mVote;
    }

    public void setVote(int vote) {
        mVote = vote;
    }
}
