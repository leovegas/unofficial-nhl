package com.app.unofficial_nhl;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.util.Log;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.pojos.*;
import com.app.unofficial_nhl.tabs.Yesterday;
import com.app.unofficial_nhl.ui.cardview.CardViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {


    private ImageView preteam1logo, preteam2logo, iceField;
    private TextView postteam1name, preteam2name, score1, score2;
    private TextView preteamposition1, preteamposition2, preteamrecord1, preteamrecord2;
    private FrameLayout iceLayout;

    private Team team;
    private List<Integer> scoringPlays = new ArrayList<Integer>();
    private List<String> whoscored = new ArrayList<>();
    private List<String> periodsAndTime = new ArrayList<>();
    private List<String> scoredteams = new ArrayList<>();
    private List<Coordinates> gooalCoordinates = new ArrayList<>();


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

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
        iceLayout = (FrameLayout) findViewById(R.id.iceLayout);
        iceField = (ImageView) findViewById(R.id.icefield);

        Intent intent = getIntent();
        String[] teams = intent.getStringArrayExtra("TEAMS");
        int[] arrayMessage = intent.getIntArrayExtra(Yesterday.EXTRA_MESSAGE);


        // inside your activity (if you did not enable transitions in your theme)
        getWindow().setExitTransition(new Explode());

        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
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
        });

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            setupActionBar();
            actionBar.hide();

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

        postteam1name.setText(home);
        preteam2name.setText(away);
        preteam1logo.setImageDrawable(resizeImage(StaticData.logosMap.get(home)));
        preteam2logo.setImageDrawable(resizeImage(StaticData.logosMap.get(away)));
        score1.setText(String.valueOf(arrayMessage[0]));
        score2.setText(String.valueOf(arrayMessage[1]));
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
                                        drawGoals(gooalCoordinates, scoredteams);
                                    }
                                });
                    }
                });

    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
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


    public void drawGoals(List<Coordinates> coordinates, List<String> teams) {
        // Create layout parameters for ImageView
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams((int) StaticData.dpToPx(20, getApplicationContext()), (int) StaticData.dpToPx(20, getApplicationContext()));
        lp.gravity = Gravity.CENTER;

        for (int i = 0, coordinatesSize = coordinates.size(); i < coordinatesSize; i++) {
            Coordinates coord = coordinates.get(i);
            // Initialize a new ImageView widget
            ImageView iv = new ImageView(getApplicationContext());
            iv.setTag("Goal number "+(i+1));
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
            double ky = heightHalf / 42.5;
            if (coord.getX() == null) {
                iceLayout.removeView(iv);
            } else {
                iv.setTranslationX((float) (Float.parseFloat(coord.getX()) * kx));
                iv.setTranslationY((float) (Float.parseFloat(coord.getY()) * ky));
            }
        }
    }
}