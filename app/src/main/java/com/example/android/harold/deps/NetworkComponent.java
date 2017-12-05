package com.example.android.harold.deps;

import com.example.android.harold.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Edgar Harold Reyes on 8/8/2017.
 * Flat Planet Pty Ltd
 * edgar.reyes@flatplanet.com.au
 */

@Singleton
@Component(modules = { NetworkModule.class})
public interface NetworkComponent {
    void inject(MainActivity activity);
}
