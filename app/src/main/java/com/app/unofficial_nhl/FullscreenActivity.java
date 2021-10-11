package com.app.unofficial_nhl;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.util.Log;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.unofficial_nhl.helper_classes.FullScreenMediaController;
import com.app.unofficial_nhl.helper_classes.ScrollingTextView;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.helper_classes.TinyDB;
import com.app.unofficial_nhl.pojos.*;
import com.app.unofficial_nhl.tabs.Yesterday;
import com.app.unofficial_nhl.ui.cardview.CardViewAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {


    private ImageView preteam1logo, preteam2logo, iceField;
    private TextView postteam1name, preteam2name, score1, score2, gamestate;
    private TextView preteamposition1, preteamposition2, preteamrecord1, preteamrecord2;
    private FrameLayout iceLayout,frameLayout2, videoLayout;
    private Toolbar toolbar;
    private ScrollingTextView scrollingTextView;
    private VideoView videoView;
    private MediaController mediaController;
    private int[] arrayMessage;
    private String[] teams;
    private Team team;
    private List<Integer> scoringPlays = new ArrayList<Integer>();
    private List<String> whoscored = new ArrayList<>();
    private List<String> periodsAndTime = new ArrayList<>();
    private List<String> scoredteams = new ArrayList<>();
    private List<Coordinates> gooalCoordinates = new ArrayList<>();
    private List<String> starsOfGame = new ArrayList<>();
    private MotionLayout motionLayout;
    private TinyDB tinyDB;

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */

    @Override
    public void onBackPressed() {
        FullscreenActivity.super.onBackPressed();
/*            new AlertDialog.Builder(this)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            FullscreenActivity.super.onBackPressed();
                        }
                    }).create().show();*/


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
                default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        tinyDB = new TinyDB(getApplicationContext());

        preteam1logo = findViewById(R.id.prelogo1);
        preteam2logo = findViewById(R.id.prelogo2);
        postteam1name = findViewById(R.id.postteam1name);
        preteam2name = findViewById(R.id.preteam2name);
        preteamposition1 = findViewById(R.id.preteamposition1);
        preteamposition2 = findViewById(R.id.preteamposition2);
        preteamrecord1 = findViewById(R.id.preteamrecord1);
        preteamrecord2 = findViewById(R.id.preteamrecord2);
        score1 = findViewById(R.id.score1);
        score2 = findViewById(R.id.score2);
        gamestate = findViewById(R.id.gamestate);
        iceLayout = (FrameLayout) findViewById(R.id.iceLayout);
        iceField = (ImageView) findViewById(R.id.icefield);
        scrollingTextView = (ScrollingTextView) findViewById(R.id.toolbar);
        frameLayout2 = (FrameLayout) findViewById(R.id.frameLayout2);
        videoLayout = (FrameLayout) findViewById(R.id.videoLayout);
        motionLayout = (MotionLayout) findViewById(R.id.frameLayout5);
        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {
                ActionBar actionBar = getSupportActionBar();

                if (actionBar != null) {
                    actionBar.show();
                }
            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                if (videoView!=null)
                {
                    videoView.start();
                }

            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

            }
        });

        if (!tinyDB.getListString("teams").isEmpty()) {
            System.out.println("yes not empty");
            ArrayList<String> s = tinyDB.getListString("teams");
            teams = new String[s.size()];
            Arrays.setAll(teams, s::get);
            ArrayList<Integer> x = tinyDB.getListInt("arrayMessage");
            arrayMessage = new int[x.size()];
            Arrays.setAll(arrayMessage, x::get);
            tinyDB.remove("teams");
            tinyDB.remove("arrayMessage");
        } else {
            Intent intent = getIntent();
            teams = intent.getStringArrayExtra("TEAMS");
            arrayMessage = intent.getIntArrayExtra(Yesterday.EXTRA_MESSAGE);
            tinyDB.remove("teams");
            tinyDB.remove("arrayMessage");
        }


        // inside your activity (if you did not enable transitions in your theme)
        getWindow().setExitTransition(new Explode());

       /* BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.navigation_home: {
                        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.navigation_dashboard: {
                        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.navigation_notifications: {
                        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(intent);
                        break;
                    }
                    default:
                        break;
                }


                return false;
            }
        });*/
/*        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbar();*/



        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            setupActionBar();

            //actionBar.hide();

        }
        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        RecyclerView recyclerView = findViewById(R.id.resultsRecyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        String home = teams[0];
        String away = teams[1];
        String feedid = teams[2];
        String detailedState = teams[3];

        postteam1name.setText(home);
        preteam2name.setText(away);
        preteam1logo.setImageDrawable(resizeImage(StaticData.logosMap.get(home)));
        preteam2logo.setImageDrawable(resizeImage(StaticData.logosMap.get(away)));
        score1.setText(String.valueOf(arrayMessage[0]));
        score2.setText(String.valueOf(arrayMessage[1]));
        gamestate.setText("Status " + detailedState);
        int wins1 = arrayMessage[2];
        int losses1 = arrayMessage[3];
        int ot1 = arrayMessage[4];
        int wins2 = arrayMessage[5];
        int losses2 = arrayMessage[6];
        int ot2 = arrayMessage[7];
        preteamrecord1.setText(wins1 + "-" + losses1 + "-" + ot1);
        preteamrecord2.setText(wins2 + "-" + losses2 + "-" + ot2);
        getTeamsInfo(arrayMessage[8], preteamposition1);
        getTeamsInfo(arrayMessage[9], preteamposition2);

        getEventContent(feedid);



        NetworkService.getInstance()
                .getJSONApi()
                .getLiveData(feedid)
                .subscribeOn(Schedulers.io())
                .flatMapIterable(gameResult -> gameResult.getLiveData().getPlays().getScoringPlays())
                .toList()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Integer>>() {

                    @Override
                    public void onNext(List<Integer> gamesByDate) {

                        Log.i("reading", "inTommorow");
                        scoringPlays.addAll(gamesByDate);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        NetworkService.getInstance()
                                .getJSONApi()
                                .getLiveData(feedid)
                                .subscribeOn(Schedulers.io())
                                .flatMapIterable(gameResult -> gameResult.getLiveData().getPlays().getAllPlays())
                                .toList()
                                .toObservable()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new DisposableObserver<List<Play>>() {

                                    @Override
                                    public void onNext(List<Play> plays) {
                                        for (Integer score : scoringPlays) {
                                            for (int i = 0, playsSize = plays.size(); i < playsSize; i++) {
                                                Play play = plays.get(i);
                                                if (i == score) {
                                                    whoscored.add(play.getResult().getDescription());
                                                    periodsAndTime.add(play.getAbout().getOrdinalNum() + " " + play.getAbout().getPeriodTime());
                                                    scoredteams.add(play.getTeam().getName());
                                                    gooalCoordinates.add(play.getCoordinates());
                                                    //starsOfGame.add(play.getResult())
                                                }
                                            }
                                        }
                                        CardViewAdapter cardViewAdapter = new CardViewAdapter(whoscored, periodsAndTime, scoredteams, gooalCoordinates);
                                        recyclerView.setAdapter(cardViewAdapter);
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {
                                        dispose();
                                        drawGoals(gooalCoordinates, scoredteams);
                                    }
                                });
                    }
                });



    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        this.getSupportActionBar().setTitle(getString(R.string.app_name));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "back pressed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        ViewGroup v = (ViewGroup) LayoutInflater.from(this)
                .inflate(R.layout.custom_actionbar, null);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM,
                ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(v,
                new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        Gravity.CENTER_VERTICAL | Gravity.RIGHT));

    }


    public void getTeamsRank(int id, TextView textView) {
        NetworkService.getInstance()
                .getJSONApi()
                .getStandings()
                .enqueue(new Callback<Teams>() {
                    @Override
                    public void onResponse(@NonNull Call<Teams> call, @NonNull Response<Teams> response) {
                        Teams teams = response.body();
                        if (teams != null) {
                            List<Record> records = teams.getRecords();
                            for (Record rec : records) {
                                List<TeamRecord> teamRecords = rec.getTeamRecords();
                                for (TeamRecord teamRecord : teamRecords) {
                                    if (teamRecord.getTeam().getId() == id) {
                                        String rank = teamRecord.getConferenceRank();
                                        if (!rank.equals("0")) {
                                            textView.append(" " + rank + "");
                                        }
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                        textView.append("");
                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });

    }

    public void getTeamsInfo(int id, TextView textView) {

        NetworkService.getInstance()
                .getJSONApi()
                .getTeamInfo(id)
                .enqueue(new Callback<Teams>() {
                    @Override
                    public void onResponse(@NonNull Call<Teams> call, @NonNull Response<Teams> response) {
                        Teams data = response.body();
                        team = data.getTeams().get(0);
                        textView.setText(team.getConference().getName());
                        getTeamsRank(id, textView);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                        textView.append("");
                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });
    }

    public Drawable resizeImage(int imageResource) {// R.drawable.large_image
        // Get device dimensions
        Display display = getWindowManager().getDefaultDisplay();
        double deviceWidth = display.getWidth();

        BitmapDrawable bd = (BitmapDrawable) this.getResources().getDrawable(
                imageResource);
        double imageHeight = bd.getBitmap().getHeight();
        double imageWidth = bd.getBitmap().getWidth();

        double ratio = deviceWidth / imageWidth;
        int newImageHeight = (int) (imageHeight * ratio);

        Bitmap bMap = BitmapFactory.decodeResource(getResources(), imageResource);
        Drawable drawable = new BitmapDrawable(this.getResources(),
                getResizedBitmap(bMap, newImageHeight, (int) deviceWidth));

        return drawable;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }

    /************************ Resize Bitmap *********************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void drawGoals(List<Coordinates> coordinates, List<String> teams) {
        // Create layout parameters for ImageView
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams((int) StaticData.dpToPx(20, getApplicationContext()), (int) StaticData.dpToPx(20, getApplicationContext()));
        lp.gravity = Gravity.CENTER;

        for (int i = 0, coordinatesSize = coordinates.size(); i < coordinatesSize; i++) {
            Coordinates coord = coordinates.get(i);
            // Initialize a new ImageView widget
            ImageView iv = new ImageView(getApplicationContext());
            iv.setTag("Goal number " + (i + 1));
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), v.getTag().toString(), 2000).show();
                }
            });

            // Set an image for ImageView
            iv.setImageDrawable(resizeImage(StaticData.logosMap.get(teams.get(i))));

            // Add layout parameters to ImageView
            iv.setLayoutParams(lp);

            // Finally, add the ImageView to layout
            iceLayout.addView(iv);
            Rect rectf = new Rect();

//For coordinates location relative to the parent
            iceField.getLocalVisibleRect(rectf);

//For coordinates location relative to the screen/display
            //iceField.getGlobalVisibleRect(rectf);
/*
            Log.d("WIDTH        :", String.valueOf(rectf.width()));
            Log.d("HEIGHT       :", String.valueOf(rectf.height()));
            Log.d("left         :", String.valueOf(rectf.left));
            Log.d("right        :", String.valueOf(rectf.right));
            Log.d("top          :", String.valueOf(rectf.top));
            Log.d("bottom       :", String.valueOf(rectf.bottom));*/
            float widthHalf = rectf.width() / 2 - 20; //4.87
            float heightHalf = rectf.height() / 2 - 130; //5.764
            double kx = widthHalf / 100;
            double ky = heightHalf / 42.5 * -1;
            if (coord.getX() == null) {
                iceLayout.removeView(iv);
            } else {
                iv.setTranslationX((float) (Float.parseFloat(coord.getX()) * kx));
                iv.setTranslationY((float) (Float.parseFloat(coord.getY()) * ky));
            }
        }
    }

    private void getEventContent(String id) {
        NetworkService.getInstance()
                .getJSONApi()
                .getEventContent(id)
                .subscribeOn(Schedulers.io())
                .flatMapIterable(event -> event.getHighlights().getGameCenter().getItems())
                .toList()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .retry(2)
                .timeout(10000, TimeUnit.MILLISECONDS)
                .subscribe(new DisposableObserver<List<Item>>() {


                    @Override
                    public void onNext(List<Item> value) {

                        StringBuilder goalDescriptions = new StringBuilder();
                        for (Item item : value) {
                            goalDescriptions.append(item.getDescription()).append(" ");
                        }
                        scrollingTextView.setText(goalDescriptions.toString());

                        NetworkService.getInstance()
                                .getJSONApi()
                                .getEventContent(id)
                                .subscribeOn(Schedulers.io())
                                .flatMapIterable(epg -> epg.getMediaEPG().getEpgs())
                                .toList()
                                .toObservable()
                                .observeOn(AndroidSchedulers.mainThread())
                                .retry(2)
                                .timeout(10000, TimeUnit.MILLISECONDS)
                                .subscribe(new DisposableObserver<List<Epg>>() {
                                    String recap = "";

                                    @Override
                                    public void onNext(List<Epg> value) {
                                        if (value.size() >= 3 && value.get(3).getItems().size() >= 1 && value.get(3).getItems().get(0).getPlaybacks().size() >= 1) {
                                            int size = value.get(3).getItems().get(0).getPlaybacks().size();
                                            System.out.println(size);
                                            recap = value.get(3).getItems().get(0).getPlaybacks().get(size - 1).getUrl();
                                            initializeVideo(recap);
                                        }


                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        videoView.setBackground(resizeImage(R.drawable.background));

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initializeVideo(String videoURL) {
        String videoSource = videoURL;

        videoView = (VideoView) findViewById(R.id.videoView);

        ArrayList<String> listString = new ArrayList<>();
        ArrayList<Integer> listInt = new ArrayList<>();
/*        for (int i = 0; i < arrayMessage.length; i++) {
            listInt.add(arrayMessage[i]);
        }
        for (int i = 0; i < teams.length; i++) {
            listString.add(teams[i]);
        }
        tinyDB.putListInt("arrayMessage",listInt);
        tinyDB.putListString("teams",listString);*/

        mediaController = new FullScreenMediaController(this, videoSource);
        mediaController.setAnchorView(videoView);

        videoView.setVideoPath(videoSource);
        videoView.setMediaController(mediaController);
        MediaPlayer.OnCompletionListener myVideoViewCompletionListener
                = new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer arg0) {
                Toast.makeText(getApplicationContext(),
                        "End of Video",
                        Toast.LENGTH_LONG).show();
            }
        };

        MediaPlayer.OnPreparedListener MyVideoViewPreparedListener
                = new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer arg0) {
//                Toast.makeText(getApplicationContext(),
//                        "Media file is loaded and ready to go",
//                        Toast.LENGTH_LONG).show();

            }
        };

        MediaPlayer.OnErrorListener myVideoViewErrorListener
                = new MediaPlayer.OnErrorListener() {

            @Override
            public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
                Toast.makeText(getApplicationContext(),
                        "Error!!!",
                        Toast.LENGTH_LONG).show();
                return true;
            }
        };
        videoView.setOnCompletionListener(myVideoViewCompletionListener);
        videoView.setOnPreparedListener(MyVideoViewPreparedListener);
        videoView.setOnErrorListener(myVideoViewErrorListener);

        videoView.requestFocus();
        //videoView.start();
    }


}