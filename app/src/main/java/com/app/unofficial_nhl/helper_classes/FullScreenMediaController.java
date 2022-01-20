package com.app.unofficial_nhl.helper_classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;
import com.app.unofficial_nhl.FullscreenActivity;
import com.app.unofficial_nhl.MainActivity2;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.ui.video.FullScreenVideoActivity;

public class FullScreenMediaController extends MediaController {

    private ImageButton fullScreen;
    private String isFullScreen;
    private String url;
    Context context;

    public FullScreenMediaController(Context context, String url) {
        super(context);
        this.context = context;
        this.url = url;
    }
    public FullScreenMediaController(Context context) {
        super(context);
    }
    @Override
    public void setAnchorView(View view) {

        super.setAnchorView(view);

        //image button for full screen to be added to media controller
        fullScreen = new ImageButton (super.getContext());

        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.RIGHT;
        params.rightMargin = 80;
        addView(fullScreen, params);

        //fullscreen indicator from intent
        isFullScreen =  ((Activity)getContext()).getIntent().
                                getStringExtra("fullScreenInd");

        if("y".equals(isFullScreen)){
            fullScreen.setImageResource(R.drawable.full_screen);
            fullScreen.setBackgroundColor(Color.TRANSPARENT);
        }else{
            fullScreen.setImageResource(R.drawable.full_screen);
            fullScreen.setBackgroundColor(Color.TRANSPARENT);
        }

        //add listener to image button to handle full screen and exit full screen events
        fullScreen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), FullScreenVideoActivity.class);
                Intent intentBack = new Intent(getContext(), MainActivity2.class);

                if(!"y".equals(isFullScreen)){
                    intent.putExtra("fullScreenInd", "y");
                    intent.putExtra("url", url);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    ((Activity)getContext()).startActivity(intent);
                }else{
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    ((Activity)getContext()).startActivity(intentBack);
                }
            }
        });
    }
} 