package com.app.unofficial_nhl.tabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.*;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.unofficial_nhl.FullscreenActivity;
import com.app.unofficial_nhl.NetworkService;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.ListRow;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.pojos.Game;
import com.app.unofficial_nhl.pojos.Teams;
import com.app.unofficial_nhl.ui.home.CustomAdapterGames;
import com.app.unofficial_nhl.ui.home.RecyclerTouchListener;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Yesterday extends Fragment {
    public static final String EXTRA_MESSAGE = "com.app.unofficial_nhl.tabs.MESSAGE";

    private ArrayList<Game> gamesByDate = new ArrayList<>();
    String teamHome = "";
    String teamAway = "";
    String detailedState = "";
    String venueName = "";
    String gameTime = "";
    String gameDate = "";
    String homeScore = "";
    String awayScore = "";
    Display display;
    long time;

    ImageView preteam1logo, preteam2logo;
    TextView preteam1name, preteam2name;
    TextView preteamposition1, preteamposition2, preteamrecord1, preteamrecord2;

    RecyclerView recyclerView;
    CustomAdapterGames recyclerAdapter;
    TextView nogames;
    FrameLayout frameLayout4;
    FrameLayout frameLayout7;

    public Yesterday(long time) {
        this.time = time;
    }

    public Yesterday() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View root = inflater.inflate(R.layout.fragment_yesteday_today_tomorrow, container, false);
        if (Build.VERSION.SDK_INT >= 26) {
            display = getActivity().getDisplay();
        } else display = getActivity().getWindowManager().getDefaultDisplay();

        SimpleDateFormat sdfDateToday = new SimpleDateFormat("yyyy-MM-dd");
        ProgressBar loadingBar = root.findViewById(R.id.progressBar);
        preteam1logo = getActivity().findViewById(R.id.prelogo1);
        preteam2logo = getActivity().findViewById(R.id.prelogo2);
        preteam1name = getActivity().findViewById(R.id.preteam1name);
        preteam2name = getActivity().findViewById(R.id.preteam2name);
        preteamposition1 = getActivity().findViewById(R.id.preteamposition1);
        preteamposition2 = getActivity().findViewById(R.id.preteamposition2);
        preteamrecord1 = getActivity().findViewById(R.id.preteamrecord1);
        preteamrecord2 = getActivity().findViewById(R.id.preteamrecord2);
        nogames = root.findViewById(R.id.nogames);
        frameLayout4 = getActivity().findViewById(R.id.frameLayout4);
        frameLayout7 = getActivity().findViewById(R.id.frameLayout7);
        nogames.setVisibility(View.GONE);
        loadingBar.setVisibility(View.VISIBLE);
        frameLayout4.setVisibility(View.GONE);
        frameLayout7.setVisibility(View.GONE);


/*        Disposable disposable = Observable.timer(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(on -> {
                    Log.i("test","there");
                });*/

        NetworkService.getInstance()
                .getJSONApi()
                .getSheduledGamesByDate2(sdfDateToday.format(new Date(System.currentTimeMillis() - time)))
                .subscribeOn(Schedulers.io())
                .flatMapIterable(teams -> teams.getDates().get(0).getGames())
                .toList()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .retry(4)
                .timeout(20000, TimeUnit.MILLISECONDS)
                .subscribe(new DisposableObserver<List<Game>>() {

                    @Override
                    public void onNext(List<Game> gamesByDate) {
                        Log.i("reading", "Yesterday");

                        ArrayList<ListRow> alldata = new ArrayList<ListRow>();

                        for (Game game : gamesByDate) {
                            Teams teams = game.getTeams();
                            teamHome = teams.getHome().getTeam().getName();
                            teamAway = teams.getAway().getTeam().getName();
                            detailedState = game.getStatus().getDetailedState();
                            venueName = game.getVenue().getName();
                            gameTime = StaticData.getDateOrTime(game.getGameDate(), 2);
                            gameDate = StaticData.getDateOrTime(game.getGameDate(), 1);
                            homeScore = String.valueOf(game.getTeams().getHome().getScore());
                            awayScore = String.valueOf(game.getTeams().getAway().getScore());

                            @DrawableRes
                            Drawable logo_team1 = StaticData.resizeImage(R.drawable.main_logo,getActivity(),display);
                            @DrawableRes
                            Drawable logo_team2 = StaticData.resizeImage(R.drawable.main_logo,getActivity(),display);
                            try {
                                logo_team1 = StaticData.resizeImage(StaticData.logosMap.get(game.getTeams().getHome().getTeam().getName()), getActivity(), display);
                                logo_team2 = StaticData.resizeImage(StaticData.logosMap.get(game.getTeams().getAway().getTeam().getName()), getActivity(), display);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            ListRow listRow = new ListRow(teamHome, teamAway, venueName, gameTime, gameDate, detailedState, awayScore, homeScore, logo_team1, logo_team2);
                            alldata.add(listRow);

                        }

                        recyclerView = root.findViewById(R.id.listview);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerAdapter = new CustomAdapterGames(getActivity(), alldata);
                        recyclerView.setAdapter(recyclerAdapter);
                        AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
                        animation1.setDuration(1000);
                        recyclerView.setAlpha(1f);
                        recyclerView.startAnimation(animation1);

                        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

                            @Override
                            public void onClick(View view, int position) {
                                frameLayout4.setVisibility(View.VISIBLE);
                                frameLayout7.setVisibility(View.VISIBLE);
                                cardflip(view, root.getContext(), gamesByDate, position);


                                // getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                            }

                            @Override
                            public void onLongClick(View view, int position) {
                                view.findViewById(R.id.home_score).setVisibility(View.GONE);
                                view.findViewById(R.id.away_score).setVisibility(View.GONE);
                                cardflipOnly(view, root.getContext());
                            }
                        }));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getClass().getName());
                        Handler uiThread = new Handler(Looper.getMainLooper());

                        uiThread.post(new Runnable() {
                            @Override
                            public void run() {

                                if (e instanceof IndexOutOfBoundsException) {
                                    loadingBar.setVisibility(View.GONE);
                                    nogames.setVisibility(View.VISIBLE);
                                    nogames.setText("There are no games...");
                                } else {
                                    if (e instanceof retrofit2.adapter.rxjava2.HttpException) {
                                        loadingBar.setVisibility(View.GONE);
                                        nogames.setVisibility(View.VISIBLE);
                                        nogames.setText("Try again later...");
                                    }else{
                                        loadingBar.setVisibility(View.GONE);
                                        nogames.setVisibility(View.VISIBLE);
                                        nogames.setText("Check internet connection...");
                                    }
                                }
                                frameLayout4.setVisibility(View.GONE);
                                frameLayout7.setVisibility(View.GONE);
                            }
                        });
                    }

                    @Override
                    public void onComplete() {
                        dispose();
                        loadingBar.setVisibility(View.GONE);
                    }
                });

        // Inflate the layout for this fragment
        return root;
    }

    public synchronized void cardflipOnly(View v, Context context) {
        v.animate().withLayer()
                .rotationX(90)
                .setDuration(400)
                .withEndAction(
                        new Runnable() {
                            @Override
                            public void run() {
                                float scale = context.getResources().getDisplayMetrics().density;
                                float distance = v.getCameraDistance() * (scale + (scale / 3));
                                v.setCameraDistance(distance * scale);
                                v.setRotationX(-90);
                                v.findViewById(R.id.away_score).setVisibility(View.VISIBLE);
                                v.findViewById(R.id.home_score).setVisibility(View.VISIBLE);

                                v.animate().withLayer()
                                        .rotationX(0)
                                        .setDuration(400)
                                        .start();
                                v.findViewById(R.id.away_score).animate().withLayer()
                                        .rotationX(0)
                                        .setDuration(400)
                                        .start();
                                v.findViewById(R.id.home_score).animate().withLayer()
                                        .rotationX(0)
                                        .setDuration(400)
                                        .start();
                            }
                        }

                ).start();

    }

    public synchronized void cardflip(View v, Context context, List<Game> games, int position) {
        String home = games.get(position).getTeams().getHome().getTeam().getName();
        String away = games.get(position).getTeams().getAway().getTeam().getName();
        int scorehome = games.get(position).getTeams().getHome().getScore();
        int scoreaway = games.get(position).getTeams().getAway().getScore();
        int wins1 = games.get(position).getTeams().getHome().getLeagueRecord().getWins();
        int losses1 = games.get(position).getTeams().getHome().getLeagueRecord().getLosses();
        int ot1 = games.get(position).getTeams().getHome().getLeagueRecord().getOt();
        int wins2 = games.get(position).getTeams().getAway().getLeagueRecord().getWins();
        int losses2 = games.get(position).getTeams().getAway().getLeagueRecord().getLosses();
        int ot2 = games.get(position).getTeams().getAway().getLeagueRecord().getOt();
        int homeid = games.get(position).getTeams().getHome().getTeam().getId();
        int awayid = games.get(position).getTeams().getAway().getTeam().getId();
        String feedid = games.get(position).getLink().replaceAll("\\D+", "").substring(1);
        String state = games.get(position).getStatus().getDetailedState();
        int gameid = games.get(position).getGamePk();

        int[] arrayMessage = new int[]{scorehome, scoreaway, wins1, losses1, ot1, wins2, losses2, ot2, homeid, awayid, gameid};
        String[] teams = new String[]{home, away, feedid, state};

        v.animate().withLayer().alpha(0).setDuration(100).withEndAction(new Runnable() {
            @Override
            public void run() {
                v.animate().withLayer()
                        .alpha(1f).setDuration(100).start();
                Intent intent = new Intent(getContext(), FullscreenActivity.class);
                intent.putExtra(EXTRA_MESSAGE, arrayMessage);
                intent.putExtra("TEAMS", teams);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).start();
/*        v.animate().withLayer()
                .rotationX(90)
                .setDuration(400)
                .withEndAction(
                        new Runnable() {
                            @Override
                            public void run() {
                                float scale = context.getResources().getDisplayMetrics().density;
                                float distance = v.getCameraDistance() * (scale + (scale / 3));
                                v.setCameraDistance(distance * scale);
                                v.setRotationX(-90);
                                v.findViewById(R.id.away_score).setVisibility(View.VISIBLE);
                                v.findViewById(R.id.home_score).setVisibility(View.VISIBLE);

                                v.animate().withLayer()
                                        .rotationX(0)
                                        .setDuration(400)
                                        .start();
                                v.findViewById(R.id.away_score).animate().withLayer()
                                        .rotationX(0)
                                        .setDuration(400)
                                        .start();
                                v.findViewById(R.id.home_score).animate().withLayer()
                                        .rotationX(0)
                                        .setDuration(400)
                                        .start();


                            }
                        }

                ).start();*/

    }


    private void doSomethingOnUi(Object response) {
        Handler uiThread = new Handler(Looper.getMainLooper());
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "Please wait", 5000).show();
            }
        });
    }

    public void doSomeTaskAsync() {
        HandlerThread ht = new HandlerThread("MyHandlerThread");
        ht.start();
        Handler asyncHandler = new Handler(ht.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Object response = msg.obj;
                doSomethingOnUi(response);
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // your async code goes here.
                try {
                    Thread.sleep(1000);


                    Message message = new Message();
                    message.obj = "My Message!";

                    asyncHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        asyncHandler.post(runnable);
    }


    private static class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }


    }
}


