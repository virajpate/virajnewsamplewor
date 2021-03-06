package com.techmorphosis.sundaram.virajnewsamplework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.ext.ima.ImaAdsLoader;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.ads.AdsLoader;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    PlayerView playerView;
    ProgressBar progressBar;
    ImageView btnFullScreen;

    SimpleExoPlayer simpleExoPlayer;
    boolean flag=false;
    private ImaAdsLoader adsLoader;
    InterstitialAd interstitialAd;

    int count;
    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadInterstitial();
        playerView =findViewById(R.id.playerview);
        progressBar=findViewById(R.id.progress_bar);
        btnFullScreen=findViewById(R.id.bt_fullScrren);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Uri videourl=Uri.parse("https://player.vimeo.com/external/280680490.sd.mp4?s=6a5fea9321f9b4ba4ef00c5bafc2143f5a988960&profile_id=164");

        adsLoader = new ImaAdsLoader(this, Uri.parse(getString(R.string.ad_tag_url)));

        LoadControl loadControl=new DefaultLoadControl();

         Intent intent=getIntent();
         count=intent.getIntExtra("key",0);

        Log.i(TAG,"myvlaue"+ count);

        BandwidthMeter  bandwidthMeter=new DefaultBandwidthMeter();

        TrackSelector trackselector=new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

        simpleExoPlayer= ExoPlayerFactory.newSimpleInstance(MainActivity.this,trackselector,loadControl);

        DefaultHttpDataSourceFactory factory=new DefaultHttpDataSourceFactory("exoplayer_video");

        ExtractorsFactory extractorfactory= new DefaultExtractorsFactory();

        MediaSource mediaSource=new ExtractorMediaSource(videourl,factory,extractorfactory,null,null);


//        DataSource.Factory dataSourceFactory =
//                new DefaultDataSourceFactory(this, Util.getUserAgent(this, getString(R.string.app_name)));


        playerView.setPlayer(simpleExoPlayer);

        playerView.setKeepScreenOn(true);

        simpleExoPlayer.prepare(mediaSource);

        simpleExoPlayer.setPlayWhenReady(true);

        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

                if (playbackState == Player.STATE_BUFFERING){
                    progressBar.setVisibility(View.VISIBLE);
                }
                else if (playbackState == Player.STATE_READY){

                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });

        btnFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag){
                    btnFullScreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    flag = false;

                }
                else {
                    btnFullScreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen_exit));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    flag =true;
                }

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        simpleExoPlayer.setPlayWhenReady(false);
        simpleExoPlayer.getPlaybackState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.getPlaybackState();
    }

    private void LoadInterstitial() {

        //admob init
        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.admob_interstitial_id));
        interstitialAd.loadAd(new AdRequest.Builder().build());

    }

    @Override
    public void onBackPressed() {


        Log.i(TAG,"new"+ count);

        int newcount=count%3;
        Log.i(TAG, "newnew"+ String.valueOf(newcount));


        if (newcount == 0){
            interstitialAdView();
        }
        else {
            finish();
        }
    }

    private void interstitialAdView() {

        if (interstitialAd.isLoaded()){

            interstitialAd.show();

            interstitialAd.setAdListener(new AdListener(){
                @Override
                public void onAdClosed() {
                  finish();
                }
            });

        }
        else
        {
            finish();
        }
    }

}
