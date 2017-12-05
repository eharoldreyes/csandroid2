package com.example.android.harold.api;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Edgar Harold Reyes on 8/8/2017.
 * Flat Planet Pty Ltd
 * edgar.reyes@flatplanet.com.au
 */

public interface AbtClient {

    @POST("oauth/access-token")
    Call<ResponseBody> login(
            @Header("Content-Type") String ctype,
            @Body String jsonObject);

    @GET("user-entity-mapping")
    Call<ResponseBody> getUserEntity(
            @Query("ids") String ids,
            @Query("username") String email,
            @Header("Authorization") String auth);


}
