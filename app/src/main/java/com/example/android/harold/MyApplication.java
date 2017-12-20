package com.example.android.harold;

import android.app.Application;

import com.example.android.harold.deps.DaggerNetworkComponent;
import com.example.android.harold.deps.NetworkComponent;
import com.example.android.harold.deps.NetworkModule;
import com.example.android.harold.models.DaoMaster;
import com.example.android.harold.models.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Harold Reyes on 12/4/2017.
 */

public class MyApplication extends Application {

    private DaoSession dbSession;
    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, BuildConfig.DATABASE_NAME);// + (ENCRYPTED ? "-db-encrypted" : "-db"));
        Database db = helper.getWritableDb();//ENCRYPTED ? helper.getEncryptedWritableDb("superhardtohackpassword") : helper.getWritableDb();
        dbSession = new DaoMaster(db).newSession();
        networkComponent = DaggerNetworkComponent.builder().networkModule(new NetworkModule(this)).build();
    }

    public DaoSession getDbSession() {
        return dbSession;
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }


}
