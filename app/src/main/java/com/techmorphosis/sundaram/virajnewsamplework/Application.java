package com.techmorphosis.sundaram.virajnewsamplework;

import android.os.StrictMode;

import com.google.android.gms.ads.MobileAds;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        StrictMode.VmPolicy.Builder builder=new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        //initiliza admob sdk
        MobileAds.initialize(this);
    }
}
