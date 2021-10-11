
package com.app.unofficial_nhl.tabs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.preference.PreferenceManager;
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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.unofficial_nhl.NetworkService;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.ListRow;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.pojos.*;
import com.app.unofficial_nhl.ui.home.CustomAdapterGames;
import com.app.unofficial_nhl.ui.home.HomeViewModel;
import com.app.unofficial_nhl.ui.home.RecyclerTouchListener;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Tomorrow extends Fragment {

    private ArrayList<Game> gamesByDate = new ArrayList<>();
    Team team;
    String teamHome = "";
    String teamAway = "";
    String detailedState = "";
    String venueName = "";
    String gameTime = "";
    String gameDate = "";
    String homeScore = "";
    String awayScore = "";

    RecyclerView recyclerView;
    CustomAdapterGames recyclerAdapter;
    ArrayList<ListRow> alldata = new ArrayList<ListRow>();
    TextView nogames;
    ImageView preteam1logo, preteam2logo;
    TextView preteam1name, preteam2name;
    TextView preteamposition1, preteamposition2, preteamrecord1, preteamrecord2;
    TextView prestarttime;
    List<Integer> wlo = new ArrayList<>();

/*    PublishSubject<String> status = PublishSubject.create();

    public Observable<String> getStatusStream() {
        return status;
    }*/

    public void getTeamsRank(int id, TextView textView, int index) {
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
                                        int w = teamRecord.getLeagueRecord().getWins();
                                        int l = teamRecord.getLeagueRecord().getLosses();
                                        int o = teamRecord.getLeagueRecord().getOt();
                                        wlo.add(w);
                                        wlo.add(l);
                                        wlo.add(o);

                                        if (index==1) {
                                            preteamrecord1.setText(String.format("%d-%d-%d", w, l, o));

                                        }
                                        if (index==2){
                                            preteamrecord2.setText(String.format("%d-%d-%d", w, l, o));
                                        }
                                        if (wlo.size()==6) {
                                            int w1 = Integer.parseInt(preteamrecord1.getText().toString().split("-")[0]);
                                            int l1 = Integer.parseInt(preteamrecord1.getText().toString().split("-")[1]);
                                            int o1 = Integer.parseInt(preteamrecord1.getText().toString().split("-")[2]);
                                            int w2 = Integer.parseInt(preteamrecord2.getText().toString().split("-")[0]);
                                            int l2 = Integer.parseInt(preteamrecord2.getText().toString().split("-")[1]);
                                            int o2 = Integer.parseInt(preteamrecord2.getText().toString().split("-")[2]);
                                            prediction((w1+l1+o1+50), w1+50, (w2 + l2 + o2+50), w2+50);
                                            wlo.clear();
                                        }
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

    public void getTeamsInfo(int id, TextView textView, int index) {

        NetworkService.getInstance()
                .getJSONApi()
                .getTeamInfo(id)
                .enqueue(new Callback<Teams>() {
                    @Override
                    public void onResponse(@NonNull Call<Teams> call, @NonNull Response<Teams> response) {
                        Teams data = response.body();
                        team = data.getTeams().get(0);
                        textView.setText(team.getConference().getName());
                        getTeamsRank(id, textView, index);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                        textView.append("");
                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });
    }

    public void prediction(int gp1, int w1, int gp2, int w2) {
        if (w1 != 0 && w2 != 0) {
            double winprocent1 = (gp1 * 100) / w1;
            double winprocent2 = (gp2 * 100) / w2;
            double summ = winprocent1 + winprocent2;
            double prediction1 = (winprocent1 * 100) / summ;
            double prediction2 = (winprocent2 * 100) / summ;
            PieChart pieChart;
            pieChart = getActivity().findViewById(R.id.pieChart);
            PieData pieData;
            List<PieEntry> pieEntryList = new ArrayList<>();
            pieEntryList.add(new PieEntry((float) prediction1, ""));
            pieEntryList.add(new PieEntry((float) prediction2, ""));
            PieDataSet pieDataSet = new PieDataSet(pieEntryList, "");
            pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
            pieData = new PieData(pieDataSet);
            pieChart.setData(pieData);
            pieChart.invalidate();
        }

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View root = inflater.inflate(R.layout.fragment_yesteday_today_tomorrow, container, false);
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
        prestarttime = getActivity().findViewById(R.id.prestarttime);

        nogames.setVisibility(View.GONE);
        loadingBar.setVisibility(View.VISIBLE);

        HomeViewModel homeViewModel =
                ViewModelProviders.of(getActivity()).get(HomeViewModel.class);


/*        Disposable disposable = Observable.create(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(on -> {
                    Log.i("test","there");
                });*/

/*        getStatusStream().subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(status -> {
                },Throwable::printStackTrace);*/

        NetworkService.getInstance()
                .getJSONApi()
                .getSheduledGamesByDate2(sdfDateToday.format(new Date(System.currentTimeMillis()+8640_0_000)))
                .subscribeOn(Schedulers.io())
                .flatMapIterable(teams -> teams.getDates().get(0).getGames())
                .toList()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .retry(2)
                .timeout(20000, TimeUnit.MILLISECONDS)
                .subscribe(new DisposableObserver<List<Game>>() {

                    @Override
                    public void onNext(List<Game> gamesByDate) {
                        Log.i("reading", "inToday");
                        for (Game game : gamesByDate) {
                            Teams teams = game.getTeams();
                            teamHome = teams.getHome().getTeam().getName();
                            teamAway = teams.getAway().getTeam().getName();
                            detailedState = game.getStatus().getDetailedState();
                            venueName = game.getVenue().getName();
                            gameTime = getDateOrTime(game.getGameDate(), 2);
                            gameDate = getDateOrTime(game.getGameDate(), 1);
                            homeScore = String.valueOf(game.getTeams().getHome().getScore());
                            awayScore = String.valueOf(game.getTeams().getAway().getScore());

                            @DrawableRes
                            Drawable logo_team1 = resizeImage(StaticData.logosMap.get(game.getTeams().getHome().getTeam().getName()));
                            @DrawableRes
                            Drawable logo_team2 = resizeImage(StaticData.logosMap.get(game.getTeams().getAway().getTeam().getName()));

                            ListRow listRow = new ListRow(teamHome, teamAway, venueName, gameTime, gameDate, detailedState, awayScore, homeScore, logo_team1, logo_team2);
                            alldata.add(listRow);

                        }

                        recyclerView = root.findViewById(R.id.listview);
                        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
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
                                view.findViewById(R.id.home_score).setVisibility(View.GONE);
                                view.findViewById(R.id.away_score).setVisibility(View.GONE);
                                homeViewModel.fire();

                                String home = gamesByDate.get(position).getTeams().getHome().getTeam().getName();
                                String away = gamesByDate.get(position).getTeams().getAway().getTeam().getName();

                                preteam1name.setText(home);
                                preteam2name.setText(away);
                                preteam1logo.setImageDrawable(resizeImage(StaticData.logosMap.get(home)));
                                preteam2logo.setImageDrawable(resizeImage(StaticData.logosMap.get(away)));
                                prestarttime.setText("NHL "+getDateOrTime(gamesByDate.get(position).getGameDate(),3).toUpperCase());
                                int wins1 = gamesByDate.get(position).getTeams().getHome().getLeagueRecord().getWins();
                                int losses1 = gamesByDate.get(position).getTeams().getHome().getLeagueRecord().getLosses();
                                int ot1 = gamesByDate.get(position).getTeams().getHome().getLeagueRecord().getOt();
                                int wins2 = gamesByDate.get(position).getTeams().getAway().getLeagueRecord().getWins();
                                int losses2 = gamesByDate.get(position).getTeams().getAway().getLeagueRecord().getLosses();
                                int ot2 = gamesByDate.get(position).getTeams().getAway().getLeagueRecord().getOt();
                                prediction((wins1 + losses1 + ot1+50), wins1+50, (wins2 + losses2 + ot2+50), wins2+50);
                                preteamrecord1.setText(wins1 + "-" + losses1 + "-" + ot1);
                                preteamrecord2.setText(wins2 + "-" + losses2 + "-" + ot2);
                                getTeamsInfo(gamesByDate.get(position).getTeams().getHome().getTeam().getId(), preteamposition1,1);
                                getTeamsInfo(gamesByDate.get(position).getTeams().getAway().getTeam().getId(), preteamposition2,2);

                            }

                            @Override
                            public void onLongClick(View view, int position) {
                               // cardflip(view, root.getContext());

                            }
                        }));
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingBar.setVisibility(View.GONE);
                        nogames.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onComplete() {
                        loadingBar.setVisibility(View.GONE);
                    }
                });

/*        NetworkService.getInstance()
                .getJSONApi()
            //    .getSheduledGamesByDate(sdfDateToday.format(new Date(System.currentTimeMillis())))
                .getSheduledGamesByDate("2021-09-26")
                .enqueue(new Callback<Teams>() {
                    @Override
                    public void onResponse(@NonNull Call<Teams> call, @NonNull Response<Teams> response) {
                        loadingBar.setVisibility(View.GONE);


                        Teams data = response.body();
                        System.out.println(data.getDates().get(0));
                        System.out.println(data.getDates().get(0).getGames().get(0).getTeams().getAway().getTeam().getTeamName());
                        if (!data.getDates().isEmpty()) {
                            gamesByDate.addAll(data.getDates().get(0).getGames());
                        }

                        ArrayList<ListRow> alldata = new ArrayList<ListRow>();

                        System.out.println(gamesByDate.size());


                        for (Game game : gamesByDate) {
                            System.out.println(game.getTeams().getHome().getTeam().getName());
                            teamHome = game.getTeams().getHome().getTeam().getName();
                            teamAway = game.getTeams().getAway().getTeam().getName();
                            detailedState = game.getStatus().getDetailedState();
                            venueName = game.getVenue().getName();
                            gameTime = getDateOrTime(game.getGameDate(), 2);
                            gameDate = getDateOrTime(game.getGameDate(), 1);
                            homeScore = String.valueOf(game.getTeams().getHome().getScore());
                            awayScore = String.valueOf(game.getTeams().getAway().getScore());

                            @DrawableRes
                            Drawable logo_team1 = resizeImage(StaticData.logosMap.get(game.getTeams().getHome().getTeam().getName()));
                            @DrawableRes
                            Drawable logo_team2 = resizeImage(StaticData.logosMap.get(game.getTeams().getAway().getTeam().getName()));


                            alldata.clear();
                            ListRow listRow = new ListRow(teamHome, teamAway, venueName, gameTime, gameDate, detailedState, awayScore, homeScore, logo_team1, logo_team2);
                            alldata.add(listRow);

                            MyCustomArrayAdapter adapter = new MyCustomArrayAdapter(getActivity(), alldata);
                            listview = (ListView) root.findViewById(R.id.listview);
                            listview.setAdapter(adapter);

                            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    //Toast.makeText(getActivity(), alldata.get(position).awayScore+"", Toast.LENGTH_SHORT).show();
                                    cardflip(view,root.getContext());
                                }
                            });
                            listview.setOnDragListener(new View.OnDragListener() {

                                @Override
                                public boolean onDrag(View v, DragEvent event) {
                                    NavigationView navigationView = root.findViewById(R.id.nav_view);
                                    navigationView.animate()
                                            .alpha(100)
                                            .setDuration(2000)
                                            .start();

                                    return false;
                                }
                            });


                        }





                    }

                    @Override
                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                        loadingBar.setVisibility(View.GONE);
                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }

                });*/
        // Inflate the layout for this fragment
        return root;
    }


    public void saveArrayList(ArrayList<Game> listArray) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listArray);
        editor.putString("TAG_LIST", json);  ///"TAG_LIST" is a key must same for getting data
        editor.apply();
    }

    public ArrayList<Game> getArrayList() {
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson();
        String json = prefs.getString("TAG_LIST", null);
        Type listType = new TypeToken<ArrayList<Game>>() {
        }.getType();
        ArrayList<Game> mSomeArraylist = gson.fromJson(json, listType);
        return mSomeArraylist;
    }


    public synchronized void cardflip(View v, Context context) {
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

    private void doSomethingOnUi2(Context context) {
        Handler uiThread = new Handler(Looper.getMainLooper());
        ProgressBar progressBar = ((Activity) context).findViewById(R.id.progressBar);

        uiThread.post(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(getContext(), "Please wait... Loading", 5000).show();
            }
        });
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

    /************************ Resize Bitmap *********************************/
    public Drawable resizeImage(int imageResource) {// R.drawable.large_image
        // Get device dimensions
        Display display = getActivity().getWindowManager().getDefaultDisplay();
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


    public String getDateOrTime(String stringTime, int index) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("h:mm a");
        SimpleDateFormat sdfTextDate = new SimpleDateFormat("EEE MMM dd h:mma");

        Date date = new Date();
        try {
            date = sdf.parse(stringTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (index == 1) return sdfDate.format(date);
        if (index == 2) return sdfTime.format(date);
        if (index == 3) return sdfTextDate.format(date);

        return sdfDate.format(date);
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






/*
package com.app.unofficial_nhl.tabs;
import android.app.Activity;
import android.content.Context;
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
import com.app.unofficial_nhl.NetworkService;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.ListRow;
import com.app.unofficial_nhl.helper_classes.MyCustomArrayAdapter;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.pojos.Game;
import com.app.unofficial_nhl.pojos.Teams;
import com.app.unofficial_nhl.ui.home.CustomAdapterGames;
import com.app.unofficial_nhl.ui.home.RecyclerTouchListener;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Tomorrow extends Fragment {

    private ArrayList<Game> gamesByDate = new ArrayList<>();
    String teamHome = "";
    String teamAway = "";
    String detailedState = "";
    String venueName = "";
    String gameTime = "";
    String gameDate = "";
    String homeScore = "";
    String awayScore = "";

    RecyclerView recyclerView;
    CustomAdapterGames recyclerAdapter;
    TextView nogames;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View root = inflater.inflate(R.layout.fragment_yesteday_today_tomorrow, container, false);
        SimpleDateFormat sdfDateToday = new SimpleDateFormat("yyyy-MM-dd");
        ProgressBar loadingBar = root.findViewById(R.id.progressBar);
        nogames = root.findViewById(R.id.nogames);
        nogames.setVisibility(View.GONE);
        loadingBar.setVisibility(View.VISIBLE);

*/
/*        Disposable disposable = Observable.timer(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(on -> {
                    Log.i("test","there");
                });*//*


        NetworkService.getInstance()
                .getJSONApi()
                .getSheduledGamesByDate2(sdfDateToday.format(new Date(System.currentTimeMillis()+8640_0_000)))
                .subscribeOn(Schedulers.io())
                .flatMapIterable(teams -> teams.getDates().get(0).getGames())
                .toList()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .retry(2)
                .timeout(7000, TimeUnit.MILLISECONDS)
                .subscribe(new DisposableObserver<List<Game>>() {

                    @Override
                    public void onNext(List<Game> gamesByDate) {

                        Log.i("reading", "inTommorow");

                        ArrayList<ListRow> alldata = new ArrayList<ListRow>();

                        for (Game game : gamesByDate) {
                            Teams teams = game.getTeams();
                            teamHome = teams.getHome().getTeam().getName();
                            teamAway = teams.getAway().getTeam().getName();
                            detailedState = game.getStatus().getDetailedState();
                            venueName = game.getVenue().getName();
                            gameTime = getDateOrTime(game.getGameDate(), 2);
                            gameDate = getDateOrTime(game.getGameDate(), 1);
                            homeScore = String.valueOf(game.getTeams().getHome().getScore());
                            awayScore = String.valueOf(game.getTeams().getAway().getScore());

                            @DrawableRes
                            Drawable logo_team1 = resizeImage(StaticData.logosMap.get(game.getTeams().getHome().getTeam().getName()));
                            @DrawableRes
                            Drawable logo_team2 = resizeImage(StaticData.logosMap.get(game.getTeams().getAway().getTeam().getName()));

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
                                cardflip(view, root.getContext());
                            }

                            @Override
                            public void onLongClick(View view, int position) {
                                view.findViewById(R.id.home_score).setVisibility(View.GONE);
                                view.findViewById(R.id.away_score).setVisibility(View.GONE);

                            }
                        }));
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingBar.setVisibility(View.GONE);
                        nogames.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onComplete() {
                        loadingBar.setVisibility(View.GONE);
                    }
                });

        // Inflate the layout for this fragment
        return root;
    }

    public synchronized void cardflip(View v, Context context) {
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

    private void doSomethingOnUi2(Context context) {
        Handler uiThread = new Handler(Looper.getMainLooper());
        ProgressBar progressBar = ((Activity) context).findViewById(R.id.progressBar);

        uiThread.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                //Toast.makeText(getContext(), "Please wait... Loading", 5000).show();
            }
        });
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

    */
/************************ Resize Bitmap *********************************//*

    public Drawable resizeImage(int imageResource) {// R.drawable.large_image
        // Get device dimensions
        Display display = getActivity().getWindowManager().getDefaultDisplay();
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

    */
/************************ Resize Bitmap *********************************//*



    public String getDateOrTime(String stringTime, int index) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("h:mm a");

        Date date = new Date();
        try {
            date = sdf.parse(stringTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (index == 1) return sdfDate.format(date);
        if (index == 2) return sdfTime.format(date);
        return sdfDate.format(date);
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


   */
/* private ArrayList<Game> gamesByDate = new ArrayList<>();
    String teamHome = "";
    String teamAway = "";
    String detailedState = "";
    String venueName = "";
    String gameTime = "";
    String gameDate = "";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View root = inflater.inflate(R.layout.fragment_yesteday_today_tomorrow, container, false);
        SimpleDateFormat sdfDateToday = new SimpleDateFormat("yyyy-MM-dd");
        ProgressBar loadingBar = root.findViewById(R.id.progressBar);
        loadingBar.setVisibility(View.VISIBLE);

        NetworkService.getInstance()
                .getJSONApi()
                //.getSheduledGamesByDate(sdfDateToday.format(new Date(System.currentTimeMillis()+8640_0_000)))
                .getSheduledGamesByDate("2021-05-21")
                .enqueue(new Callback<Teams>() {
                    @Override
                    public void onResponse(@NonNull Call<Teams> call, @NonNull Response<Teams> response) {
                        Teams data = response.body();
                        if (!data.getDates().isEmpty()) {
                            gamesByDate.addAll(data.getDates().get(0).getGames());
                        }

                        ArrayList<ListRow> alldata = new ArrayList<ListRow>();

                        System.out.println(gamesByDate.size());
                        for (Game game : gamesByDate) {
                            teamHome = game.getTeams().getHome().getTeam().getName();
                            teamAway = game.getTeams().getAway().getTeam().getName();
                            detailedState = game.getStatus().getDetailedState();
                            venueName = game.getVenue().getName();
                            gameTime = getDateOrTime(game.getGameDate(), 2);
                            gameDate = getDateOrTime(game.getGameDate(), 1);

                            @DrawableRes
                            Drawable logo_team1 = resizeImage(StaticData.logosMap.get(game.getTeams().getHome().getTeam().getName()));
                            @DrawableRes
                            Drawable logo_team2 = resizeImage(StaticData.logosMap.get(game.getTeams().getAway().getTeam().getName()));


                            ListRow listRow = new ListRow(teamHome, teamAway, venueName, gameTime, gameDate, detailedState, "", "", logo_team1, logo_team2);
                            alldata.add(listRow);
                        }

                        MyCustomArrayAdapter adapter = new MyCustomArrayAdapter(getActivity(), alldata);
                        final ListView listview = (ListView) root.findViewById(R.id.listview);
                        listview.setAdapter(adapter);
                        loadingBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }

                });
        // Inflate the layout for this fragment
        return root;
    }

    *//*
*/
/************************ Resize Bitmap *********************************//*
*/
/*
    public Drawable resizeImage(int imageResource) {// R.drawable.large_image
        // Get device dimensions
        Display display = getActivity().getWindowManager().getDefaultDisplay();
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

    public String getDateOrTime(String stringTime, int index) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        System.out.println(TimeZone.getDefault().getDisplayName());
        sdf.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("h:mm a");

        Date date = new Date();
        try {
            date = sdf.parse(stringTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (index == 1) return sdfDate.format(date);
        if (index == 2) return sdfTime.format(date);
        return sdfDate.format(date);
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


    }*//*

    }

*/
