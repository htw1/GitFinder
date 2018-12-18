
package com.htw.githubrepofinder.model;
import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("description")
    private String mDescription;

    @SerializedName("full_name")
    private String mFullName;

    @SerializedName("id")
    private Long mId;

    @SerializedName("language")
    private String mLanguage;

    @SerializedName("name")
    private String mName;

    @SerializedName("owner")
    private Owner mOwner;

    @SerializedName("private")
    private Boolean mPrivate;

    @SerializedName("stargazers_count")
    private Long mStargazersCount;

    @SerializedName("updated_at")
    private String mUpdatedAt;

    @SerializedName("watchers")
    private Long mWatchers;

    public Items(String mDescription, String mFullName, Long mId, String mLanguage, String mName, Owner mOwner, Boolean mPrivate, Long mStargazersCount, String mUpdatedAt, Long mWatchers) {
        this.mDescription = mDescription;
        this.mFullName = mFullName;
        this.mId = mId;
        this.mLanguage = mLanguage;
        this.mName = mName;
        this.mOwner = mOwner;
        this.mPrivate = mPrivate;
        this.mStargazersCount = mStargazersCount;
        this.mUpdatedAt = mUpdatedAt;
        this.mWatchers = mWatchers;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmFullName() {
        return mFullName;
    }

    public Long getmId() {
        return mId;
    }

    public String getmLanguage() {
        return mLanguage;
    }

    public String getmName() {
        return mName;
    }

    public Owner getmOwner() {
        return mOwner;
    }

    public Boolean getmPrivate() {
        return mPrivate;
    }

    public Long getmStargazersCount() {
        return mStargazersCount;
    }

    public String getmUpdatedAt() {
        return mUpdatedAt;
    }

    public Long getmWatchers() {
        return mWatchers;
    }
}
