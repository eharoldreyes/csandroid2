package com.example.android.harold.api;

import android.app.Application;

import com.example.android.harold.MyApplication;
import com.example.android.harold.models.DaoSession;
import com.example.android.harold.models.User;

import java.util.List;

/**
 * Created by Edgar Harold Reyes on 8/8/2017.
 * Flat Planet Pty Ltd
 * edgar.reyes@flatplanet.com.au
 */

public class AbtApi {

    public static final String TAG = AbtApi.class.getSimpleName();

    private static volatile AbtApi sSoleInstance;
    private final AbtClient mAbtClient;
    private final DaoSession dbSession;

    private User authorizedUser;
    private String auth;

    public interface NetworkCallback {
        void onSuccess(Object response);

        void onError(Throwable error);
    }

    public interface MultiCallback {
        void onFinish(List<?> success, List<TaggedThrowable> errors);
    }

    public static class TaggedThrowable extends Throwable {
        private String tag;

        public TaggedThrowable(Throwable t) {
            super(t);
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }

    public static AbtApi getInstance(Application app, AbtClient abtClient) {
        if (sSoleInstance == null) {
            synchronized (AbtApi.class) {
                if (sSoleInstance == null)
                    sSoleInstance = new AbtApi(app, abtClient);
            }
        }

        return sSoleInstance;
    }

    private AbtApi(Application app, AbtClient abtClient) {
        if (sSoleInstance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        } else {
            this.mAbtClient = abtClient;
            this.dbSession = ((MyApplication) app).getDbSession();
        }
    }

//    private void generateCredentials() {
//        if (selectedEntity == null)
//            selectedEntity = session.getEntity();
//        if (authorizedUser == null)
//            authorizedUser = session.getAuthorizedUser();
//        auth = "Bearer " + authorizedUser.getAccessToken();
//    }
//
//    private void catchErrorBody(Call<ResponseBody> call, Response<ResponseBody> response, Callback<ResponseBody> callback) {
//        try {
//            JSONObject jsonObject = new JSONObject(response.errorBody().string());
//            String msg = null;
//            try {
//                msg = jsonObject.getString("message");
//            } catch (JSONException x) {
//                x.printStackTrace();
//            }
//            if (msg == null)
//                msg = response.errorBody().toString();
//
//            callback.onFailure(call, new Exception(msg)); //TODO catch error by codes
//        } catch (Exception e) {
//            e.printStackTrace();
//            Logger.e("catchErrorBody", e);
//            callback.onFailure(call, e);
//        }
//    }

//    public void forgotPassword(String email, final NetworkCallback callback) {
//        mAbtClient.forgotPassword(email, BuildConfig.RESET_PASSWORD_REDIR).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                callback.onSuccess(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                callback.onError(t);
//            }
//        });
//    }

//    public void login(final String username, final String password, final NetworkCallback callback) {
//        try {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("username", username);
//            jsonObject.put("password", password);
//            jsonObject.put("grant_type", "password");
//            jsonObject.put("client_id", BuildConfig.CLIENT_ID);
//            jsonObject.put("client_secret", BuildConfig.CLIENT_SECRET);
//
//            mAbtClient.login("application/json", jsonObject.toString()).enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    try {
//                        if (response.errorBody() != null) {
//                            catchErrorBody(call, response, this);
//                        } else {
//                            String str = response.body().toString();
//                            Log.i(TAG, str);
//
//
//                            User user = User.Gson(response.body().string());
//                            user.setLoggedIn(true);
//                            user = session.authorize(user);
//
//                            sendFirebaseToken(new NetworkCallback() {
//                                @Override
//                                public void onSuccess(Object response) {
//                                    Log.i(TAG, response.toString());
//                                }
//
//                                @Override
//                                public void onError(Throwable error) {
//                                    error.printStackTrace();
//                                    Logger.e(TAG, error);
//                                }
//                            });
//
//
//                            callback.onSuccess(user);
//
//
//                        }
//                    } catch (Exception e) {
//                        onFailure(call, e);
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    callback.onError(t);
//                }
//            });
//
//        } catch (JSONException e) {
//            callback.onError(e);
//        }
//
//
//    }
//
//    public void getUserEntities(String ids, final NetworkCallback callback) {
//        if (!session.isActive())
//            throw new IllegalAccessError("Invalid session");
//        generateCredentials();
//
//        mAbtClient.getUserEntity(ids, authorizedUser.getEmail(), auth)
//                .enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        try {
//                            if (response.errorBody() != null) {
//                                catchErrorBody(call, response, this);
//                            } else {
//                                JSONObject j = new JSONObject(response.body().string()).getJSONObject("user");
//                                User jUser = User.Gson(j.toString());
//
//                                authorizedUser.setId(jUser.getId());
//                                for (ServerEntity entity : jUser.getEntities()) {
//                                    entity.setUserId(authorizedUser.getId());
//                                    if (selectedEntity != null && selectedEntity.getId() == entity.getId())
//                                        entity.setSelected(true);
//                                }
//                                dbSession.getServerEntityDao().insertOrReplaceInTx(jUser.getEntities());
//                                dbSession.getUserDao().update(authorizedUser);
//
//                                if (callback != null)
//                                    callback.onSuccess(authorizedUser.getEntities());
//
//                            }
//                        } catch (Exception e) {
//                            onFailure(call, e);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        t.printStackTrace();
//                        Logger.e(TAG + " getUserEntities", t);
//                        if (callback != null)
//                            callback.onError(t);
//                    }
//                });
//    }

}
