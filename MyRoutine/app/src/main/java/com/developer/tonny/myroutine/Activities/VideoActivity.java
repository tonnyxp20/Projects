package com.developer.tonny.myroutine.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.developer.tonny.myroutine.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlaybackEventListener{

    private YouTubePlayerView youTubePlayerView;
    public static final String claveYouTube = "AIzaSyALQ2p1AG28b1k3EAw97Z4eMuuMz5dph3I";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubeView);
        youTubePlayerView.initialize(claveYouTube, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean fueRestaurada) {
        if (!fueRestaurada) {
            youTubePlayer.loadVideo("hGl-LAm-v3o");
            //youTubePlayer.cueVideo("hGl-LAm-v3o&list=PL3EgmZkn2bNW6xf-0wB9a8mtdtGV1gI60");
            //https://youtu.be/hGl-LAm-v3o?list=PL3EgmZkn2bNW6xf-0wB9a8mtdtGV1gI60
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, 1).show();
        } else {
            String error = "Error al inicializar YouTube" + youTubeInitializationResult.toString();
            Toast.makeText(getApplication(), error, Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            getYoutubePlayerProvider().initialize(claveYouTube, this);
        }
    }

    protected YouTubePlayer.Provider getYoutubePlayerProvider() {
        return youTubePlayerView;
    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }
}