
package com.htw.githubrepofinder.model;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Owner {

    @SerializedName("avatar_url")
    private String mAvatarUrl;

    public String getmAvatarUrl() {
        return mAvatarUrl;
    }

    public String getmLogin() {
        return mLogin;
    }

    public String getmUrl() {
        return mUrl;
    }

    @SerializedName("login")
    private String mLogin;

    @SerializedName("url")
    private String mUrl;

    public Owner(String mAvatarUrl, String mLogin, String mUrl) {
        this.mAvatarUrl = mAvatarUrl;
        this.mLogin = mLogin;
        this.mUrl = mUrl;
    }

}
