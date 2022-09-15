package com.app.unofficial_nhl;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.util.Log;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
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
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {


    private ImageView preteam1logo, preteam2logo, iceField;
    private TextView postteam1name, preteam2name, score1, score2, gamestate;
    private TextView preteamposition1, preteamposition2, preteamrecord1, preteamrecord2;
    private TextView card_goals_home, card_goals_away, card_shots_home, card_shots_away, card_penalties_home, card_penalties_away, card_pp_opportunities_home;
    private TextView card_pp_opportunities_away, card_pp_goals_home, card_faceoff_wins_home, card_pp_goals_away, card_faceoff_wins_away, card_blocked_home;
    private TextView card_blocked_away, card_hits_home, card_hits_away, stars;
    private FrameLayout iceLayout, frameLayout2, videoLayout;
    private Toolbar toolbar;
    private ScrollingTextView scrollingTextView;
    private VideoView videoView;
    private MediaController mediaController;
    private int[] arrayMessage;
    private String[] teams;
    private Team team;
    private List<Integer> scoringPlays = new ArrayList<Integer>();
    private List<String> whoscored = new ArrayList<>();
    private List<String> currentGoals = new ArrayList<>();
    private List<String> periodsAndTime = new ArrayList<>();
    private List<String> scoredteams = new ArrayList<>();
    private List<Coordinates> gooalCoordinates = new ArrayList<>();
    private List<String> starsOfGame = new ArrayList<>();
    private MotionLayout motionLayout;
    private TinyDB tinyDB;
    Display display;
    private FrameLayout frameLayoutBehind;
    private int gameid;
    private ImageButton rosterHome, rosterAway;


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
        super.onBackPressed();

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
            case R.id.about:
                StaticData.showAbout(this,getCurrentFocus());
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
        if (Build.VERSION.SDK_INT >= 26) {
            display = getDisplay();
        } else display = getWindowManager().getDefaultDisplay();

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
        frameLayoutBehind = (FrameLayout) findViewById(R.id.frameLayoutBehind);

        card_goals_home = findViewById(R.id.card_goals_home);
        card_goals_away = findViewById(R.id.card_goals_away);
        card_shots_home = findViewById(R.id.card_shots_home);
        card_shots_away = findViewById(R.id.card_shots_away);
        card_penalties_home = findViewById(R.id.card_penalties_home);
        card_penalties_away = findViewById(R.id.card_penalties_away);
        card_pp_opportunities_home = findViewById(R.id.card_pp_opportunities_home);
        card_pp_opportunities_away = findViewById(R.id.card_pp_opportunities_away);
        card_pp_goals_home = findViewById(R.id.card_pp_goals_home);
        card_faceoff_wins_home = findViewById(R.id.card_faceoff_wins_home);
        card_pp_goals_away = findViewById(R.id.card_pp_goals_away);
        card_faceoff_wins_away = findViewById(R.id.card_faceoff_wins_away);
        card_blocked_home = findViewById(R.id.card_blocked_home);
        card_blocked_away = findViewById(R.id.card_blocked_away);
        card_hits_home = findViewById(R.id.card_hits_home);
        card_hits_away = findViewById(R.id.card_hits_away);
        stars = findViewById(R.id.stars);
        rosterHome = findViewById(R.id.rosterHome);
        rosterAway = findViewById(R.id.rosterAway);

        preteam1logo.setImageDrawable(StaticData.resizeImage(R.drawable.main_logo, this, display));
        preteam2logo.setImageDrawable(StaticData.resizeImage(R.drawable.main_logo, this, display));

        frameLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println(gameid);
                if (gameid != 0) {
                    getGameStats(gameid);
                }
                cardflip(v, getApplicationContext(), 0);
            }
        });
        frameLayoutBehind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardflip(v, getApplicationContext(), 1);
            }
        });
        iceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardflip(v, getApplicationContext(), 2);
            }
        });

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
                if (videoView != null) {
                    videoView.start();
                }
                getGameStats(gameid);
                cardflip(frameLayout2, getApplicationContext(), 0);
                ValueAnimator anim2 = ValueAnimator.ofInt(iceLayout.getMeasuredHeight(), StaticData.dpToPx(0, getApplicationContext()));
                anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int val = (Integer) valueAnimator.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams = iceLayout.getLayoutParams();
                        layoutParams.height = val;
                        iceLayout.setLayoutParams(layoutParams);
                    }
                });
                anim2.setDuration(400);
                anim2.start();

            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

            }
        });

        if (!tinyDB.getListString("teams").isEmpty()) {
            //System.out.println("yes not empty");
            ArrayList<String> s = tinyDB.getListString("teams");
            teams = new String[s.size()];
            for (int i = 0, sSize = s.size(); i < sSize; i++) {
                String el = s.get(i);
                teams[i] = el;
            }
            ArrayList<Integer> x = tinyDB.getListInt("arrayMessage");
            arrayMessage = new int[x.size()];
            for (int i = 0, sSize = x.size(); i < sSize; i++) {
                int el = x.get(i);
                arrayMessage[i] = el;
            }
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
        try {
            preteam1logo.setImageDrawable(StaticData.resizeImage(StaticData.logosMap.get(home), this, display));
            preteam2logo.setImageDrawable(StaticData.resizeImage(StaticData.logosMap.get(away), this, display));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        gameid = arrayMessage[10];

        preteam1logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeamInfoActivity.class);
                intent.putExtra("TEAMNAME", home);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        preteam2logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeamInfoActivity.class);
                intent.putExtra("TEAMNAME", away);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        rosterHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeamInfoActivity.class);
                intent.putExtra("tabnumber",1);
                intent.putExtra("TEAMNAME", home);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        rosterAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeamInfoActivity.class);
                intent.putExtra("tabnumber",1);
                intent.putExtra("TEAMNAME", away);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

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
                                                    currentGoals.add(play.getAbout().getGoals().getHome() + ":" + play.getAbout().getGoals().getAway());
                                                    periodsAndTime.add(play.getAbout().getOrdinalNum() + " " + play.getAbout().getPeriodTime());
                                                    scoredteams.add(play.getTeam().getName());
                                                    gooalCoordinates.add(play.getCoordinates());
                                                }
                                            }
                                        }
                                        CardViewAdapter cardViewAdapter = new CardViewAdapter(whoscored, periodsAndTime, scoredteams, gooalCoordinates, currentGoals);
                                        recyclerView.setAdapter(cardViewAdapter);
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {
                                        dispose();

                                        ValueAnimator anim2 = ValueAnimator.ofInt(iceLayout.getMeasuredHeight(), StaticData.dpToPx(0, getApplicationContext()));
                                        anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                            @Override
                                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                                int val = (Integer) valueAnimator.getAnimatedValue();
                                                ViewGroup.LayoutParams layoutParams = iceLayout.getLayoutParams();
                                                layoutParams.height = val;
                                                iceLayout.setLayoutParams(layoutParams);
                                            }
                                        });
                                        anim2.setDuration(1000);
                                        anim2.start();

                                        drawGoals(gooalCoordinates, scoredteams);
                                        NetworkService.getInstance()
                                                .getJSONApi()
                                                .getLiveData(feedid)
                                                .subscribeOn(Schedulers.io())
                                                .map(gameResult -> gameResult.getLiveData().getDecisions())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new DisposableObserver<Decisions>() {

                                                    @Override
                                                    public void onNext(Decisions value) {
                                                        if (value.getFirstStar() != null) {
                                                            String sb = value.getFirstStar().getFullName() +
                                                                    ". " +
                                                                    value.getSecondStar().getFullName() +
                                                                    ". " +
                                                                    value.getThirdStar().getFullName() +
                                                                    ". ";
                                                            stars.setText(sb);
                                                        }

                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {

                                                    }

                                                    @Override
                                                    public void onComplete() {
                                                        dispose();
                                                    }
                                                });

                                    }
                                });
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
            try {
                iv.setImageDrawable(StaticData.resizeImage(StaticData.logosMap.get(teams.get(i)), this, display));
            } catch (Exception e) {
                e.printStackTrace();
                iv.setImageDrawable(StaticData.resizeImage(R.drawable.main_logo, this, display));
            }

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
                                            recap = value.get(3).getItems().get(0).getPlaybacks().get(size - 1).getUrl();
                                            initializeVideo(recap);
                                        }


                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        videoView.setBackground(StaticData.resizeImage(R.color.background, getParent(), display));

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
        videoView.setVideoPath(videoSource);

        mediaController = new FullScreenMediaController(this, videoSource);
        mediaController.setAnchorView(videoView);

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

    public synchronized void cardflip(View v, Context context, int isopen) {
        if (isopen==0) {
            ValueAnimator anim = ValueAnimator.ofInt(frameLayoutBehind.getMeasuredHeight(), StaticData.dpToPx(250, context));
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = frameLayoutBehind.getLayoutParams();
                    layoutParams.height = val;
                    frameLayoutBehind.setLayoutParams(layoutParams);
                }
            });
            anim.setDuration(400);
            anim.start();

            ValueAnimator anim2 = ValueAnimator.ofInt(frameLayout2.getMeasuredHeight(), StaticData.dpToPx(0, context));
            anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = frameLayout2.getLayoutParams();
                    layoutParams.height = val;
                    frameLayout2.setLayoutParams(layoutParams);
                }
            });
            anim2.setDuration(400);
            anim2.start();

        } else if(isopen==2) {
            ValueAnimator anim = ValueAnimator.ofInt(frameLayout2.getMeasuredHeight(), StaticData.dpToPx(250, context));
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = frameLayout2.getLayoutParams();
                    layoutParams.height = val;
                    frameLayout2.setLayoutParams(layoutParams);
                }
            });
            anim.setDuration(400);
            anim.start();
            ValueAnimator anim2 = ValueAnimator.ofInt(iceLayout.getMeasuredHeight(), StaticData.dpToPx(0, context));
            anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = iceLayout.getLayoutParams();
                    layoutParams.height = val;
                    iceLayout.setLayoutParams(layoutParams);
                }
            });
            anim2.setDuration(400);
            anim2.start();
        } else if (isopen==1) {

            ValueAnimator anim = ValueAnimator.ofInt(iceLayout.getMeasuredHeight(), StaticData.dpToPx(270, context));
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = iceLayout.getLayoutParams();
                    layoutParams.height = val;
                    iceLayout.setLayoutParams(layoutParams);
                }
            });
            anim.setDuration(400);
            anim.start();
            ValueAnimator anim2 = ValueAnimator.ofInt(frameLayoutBehind.getMeasuredHeight(), StaticData.dpToPx(0, context));
            anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = frameLayoutBehind.getLayoutParams();
                    layoutParams.height = val;
                    frameLayoutBehind.setLayoutParams(layoutParams);
                }
            });
            anim2.setDuration(400);
            anim2.start();

        }

    }

    public void getGameStats(int gameid) {

        NetworkService.getInstance()
                .getJSONApi()
                .getGameStats(gameid)
                .enqueue(new Callback<GameStats>() {
                    @Override
                    public void onResponse(@NonNull Call<GameStats> call, @NonNull Response<GameStats> response) {
                        GameStats data1 = response.body();
                        if (data1 != null) {
                            Teams data = data1.getTeams();
                            if (data != null) {
                                if (data.getHome() != null) {
                                    card_goals_home.setText(String.valueOf((data.getHome().getTeamStats().getTeamSkaterStats().getGoals())));
                                    card_shots_home.setText(String.valueOf((data.getHome().getTeamStats().getTeamSkaterStats().getShots())));
                                    card_penalties_home.setText(String.valueOf((data.getHome().getTeamStats().getTeamSkaterStats().getPim())));
                                    card_pp_opportunities_home.setText(String.valueOf((data.getHome().getTeamStats().getTeamSkaterStats().getPowerPlayOpportunities().intValue())));
                                    card_pp_goals_home.setText(String.valueOf((data.getHome().getTeamStats().getTeamSkaterStats().getPowerPlayGoals().intValue())));
                                    card_faceoff_wins_home.setText(data.getHome().getTeamStats().getTeamSkaterStats().getFaceOffWinPercentage() + "%");
                                    card_blocked_home.setText(String.valueOf((data.getHome().getTeamStats().getTeamSkaterStats().getBlocked())));
                                    card_hits_home.setText(String.valueOf((data.getHome().getTeamStats().getTeamSkaterStats().getHits())));

                                    card_goals_away.setText(String.valueOf((data.getAway().getTeamStats().getTeamSkaterStats().getGoals())));
                                    card_shots_away.setText(String.valueOf((data.getAway().getTeamStats().getTeamSkaterStats().getShots())));
                                    card_penalties_away.setText(String.valueOf((data.getAway().getTeamStats().getTeamSkaterStats().getPim())));
                                    card_pp_opportunities_away.setText(String.valueOf((data.getAway().getTeamStats().getTeamSkaterStats().getPowerPlayOpportunities().intValue())));
                                    card_pp_goals_away.setText(String.valueOf((data.getAway().getTeamStats().getTeamSkaterStats().getPowerPlayGoals().intValue())));
                                    card_faceoff_wins_away.setText(data.getAway().getTeamStats().getTeamSkaterStats().getFaceOffWinPercentage() + "%");
                                    card_blocked_away.setText(String.valueOf((data.getAway().getTeamStats().getTeamSkaterStats().getBlocked())));
                                    card_hits_away.setText(String.valueOf((data.getAway().getTeamStats().getTeamSkaterStats().getHits())));
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GameStats> call, @NonNull Throwable t) {
                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });
    }


}