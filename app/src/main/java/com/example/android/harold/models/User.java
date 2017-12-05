package com.example.android.harold.models;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Edgar Harold Reyes on 8/8/2017.
 * Flat Planet Pty Ltd
 * edgar.reyes@flatplanet.com.au
 */
@Entity
public class User implements Serializable {

    @Keep
    public static User Gson(String jsonString) {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
                .fromJson(jsonString, User.class);
    }

    private static final long serialVersionUID = 7532845780240406472L;
    @Id
    @SerializedName("id")
    public long id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("changed_by_user")
    private long changedByUser;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("expires_in")
    private long tokenExpiration;

    private boolean loggedIn;
    private boolean modified;


    @Generated(hash = 1807422798)
    public User(long id, String name, String email, String password,
            long changedByUser, Date createdAt, String accessToken,
            String refreshToken, long tokenExpiration, boolean loggedIn,
            boolean modified) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.changedByUser = changedByUser;
        this.createdAt = createdAt;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenExpiration = tokenExpiration;
        this.loggedIn = loggedIn;
        this.modified = modified;
    }


    @Generated(hash = 586692638)
    public User() {
    }


    @Keep
    public JSONObject toJSON() throws JSONException {
        return new JSONObject(new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
                .toJson(this));
    }


    public long getId() {
        return this.id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return this.email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public long getChangedByUser() {
        return this.changedByUser;
    }


    public void setChangedByUser(long changedByUser) {
        this.changedByUser = changedByUser;
    }


    public Date getCreatedAt() {
        return this.createdAt;
    }


    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public String getAccessToken() {
        return this.accessToken;
    }


    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    public String getRefreshToken() {
        return this.refreshToken;
    }


    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    public long getTokenExpiration() {
        return this.tokenExpiration;
    }


    public void setTokenExpiration(long tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }


    public boolean getLoggedIn() {
        return this.loggedIn;
    }


    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }


    public boolean getModified() {
        return this.modified;
    }


    public void setModified(boolean modified) {
        this.modified = modified;
    }


}
