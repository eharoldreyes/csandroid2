package com.example.android.harold;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.harold.api.AbtApi;
import com.example.android.harold.api.AbtClient;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    public AbtApi mAbtApi;
    @Inject
    public AbtClient mAbtClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApplication) getApplication()).getNetworkComponent().inject(this);
    }
}
