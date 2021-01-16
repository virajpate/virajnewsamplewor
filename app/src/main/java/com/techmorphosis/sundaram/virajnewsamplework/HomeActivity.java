package com.techmorphosis.sundaram.virajnewsamplework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class HomeActivity extends AppCompatActivity {

    Button vdo1,vdo2,vdo3;

    //admob

    InterstitialAd interstitialAd;
    public int count=0;
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LoadInterstitial();

        vdo1=findViewById(R.id.video1);
        vdo2=findViewById(R.id.video2);
        vdo3=findViewById(R.id.video3);

        BtnListner();


    }

    private void BtnListner() {

        vdo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count++;

                Log.i(TAG, "my value" + String.valueOf(count));

                Intent intent=new Intent(HomeActivity.this,MainActivity.class);
                intent.putExtra("key",count);
                startActivity(intent);



            }
        });


        vdo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //interstitialAdView();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });

        vdo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //interstitialAdView();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });

    }

    private void LoadInterstitial() {

        //admob init
        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.admob_interstitial_id));
        interstitialAd.loadAd(new AdRequest.Builder().build());


    }

    private void interstitialAdView() {

        if (interstitialAd.isLoaded()){
            interstitialAd.show();

            interstitialAd.setAdListener(new AdListener(){
                @Override
                public void onAdClosed() {

                }
            });
            return;
        }
        else
        {
            finish();
        }
    }

    @Override
    protected void onPause() {


        int pauscount=count;
        Log.i(TAG,"my pause value" + String.valueOf(pauscount));
        super.onPause();
    }



}
