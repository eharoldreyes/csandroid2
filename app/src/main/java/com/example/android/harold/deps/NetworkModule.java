package com.example.android.harold.deps;

import android.app.Application;

import com.example.android.harold.BuildConfig;
import com.example.android.harold.api.Api;
import com.example.android.harold.api.Client;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Edgar Harold Reyes on 8/8/2017.
 * Flat Planet Pty Ltd
 * edgar.reyes@flatplanet.com.au
 */

@Module
public class NetworkModule {

    private final Application app;
    private File cacheFile;

    public NetworkModule(Application app) {
        this.app = app;
        this.cacheFile = new File(app.getCacheDir(), "responses");
    }

    @Provides
    @Singleton
    Retrofit providesCall() {
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        // Customize the request
                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .removeHeader("Pragma")
                                .header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
                                .build();

                        okhttp3.Response response = chain.proceed(request);
                        response.cacheResponse();
                        // Customize or return the response
                        return response;
                    }
                })
                .cache(cache)

                .build();


        return providesRetrofit().client(okHttpClient).build();
    }

    @Provides
    @Singleton
    Retrofit.Builder providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }

    @Provides
    @Singleton
    //@SuppressWarnings("unused")
    public Client providesNetworkService(Retrofit retrofit) {
        return retrofit.create(Client.class);
    }

    @Provides
    @Singleton
    //@SuppressWarnings("unused")
    public Api providesService(Client networkAbtClient) {
        return Api.getInstance(app, networkAbtClient);
    }


}
