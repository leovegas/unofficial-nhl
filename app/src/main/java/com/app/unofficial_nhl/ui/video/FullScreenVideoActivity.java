package com.app.unofficial_nhl.ui.video;

import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.FullScreenMediaController;

public class FullScreenVideoActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen_videoview);

        videoView = findViewById(R.id.videoView);

        String fullScreen =  getIntent().getStringExtra("fullScreenInd");
        String url = getIntent().getStringExtra("url");
        if("y".equals(fullScreen)){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		 WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }

        videoView.setVideoPath(url);

        mediaController = new FullScreenMediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        //videoView.start();
    }
} 