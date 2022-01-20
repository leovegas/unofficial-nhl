package com.app.unofficial_nhl;

import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.pojos.*;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class GoaliePlayersActivity extends AppCompatActivity {

    private TextView playerName, playerNumber, birthDay, currentAge, birthCity, birthCountry, height, weight, shootsCatches;
    private TextView wins, losses, ot, shutouts, timeOnIce, saves, ppsaves, goalsagainstgame;
    private TextView games_goalie, shotsAgainst, goalsAgainst, powerPlaySavePercentage, evenStrengthSavePercentage, gamesStarted;
    private PieChart chartSave, chartWins;
    private TextView rank_wins,rank_saves,rank_shutOuts,rank_shotsAgainst,rank_savePercentage,rank_timeOnIce;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goalie_players);
        playerName = findViewById(R.id.playerName);
        playerNumber = findViewById(R.id.playerNumber);
        birthDay = findViewById(R.id.birthDay);
        currentAge = findViewById(R.id.currentAge);
        birthCity = findViewById(R.id.birthCity);
        birthCountry = findViewById(R.id.birthCountry);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        shootsCatches = findViewById(R.id.shootsCatches);

        wins = findViewById(R.id.wins);
        losses = findViewById(R.id.losses);
        ot = findViewById(R.id.ot);
        shutouts = findViewById(R.id.shutouts);
        timeOnIce = findViewById(R.id.timeOnIce);
        saves = findViewById(R.id.saves);
        ppsaves = findViewById(R.id.ppsaves);
        goalsagainstgame = findViewById(R.id.goalsagainstgame);
        games_goalie = findViewById(R.id.games_goalie);
        shotsAgainst = findViewById(R.id.shotsAgainst);
        goalsAgainst = findViewById(R.id.goalsAgainst);
        powerPlaySavePercentage = findViewById(R.id.powerPlaySavePercentage);
        evenStrengthSavePercentage = findViewById(R.id.evenStrengthSavePercentage);
        gamesStarted = findViewById(R.id.gamesStarted);
        chartSave = findViewById(R.id.chartSave);
        chartWins = findViewById(R.id.chartWins);

        rank_wins = findViewById(R.id.rank_wins);
        rank_saves = findViewById(R.id.rank_saves);
        rank_shutOuts = findViewById(R.id.rank_shutOuts);
        rank_shotsAgainst = findViewById(R.id.rank_shotsAgainst);
        rank_savePercentage = findViewById(R.id.rank_savePercentage);
        rank_timeOnIce = findViewById(R.id.rank_timeOnIce);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            setupActionBar();

            //actionBar.hide();

        }

        int playerid = getIntent().getIntExtra("playerid",0);
        String role = getIntent().getStringExtra("role");

        getPlayerRanks(playerid);

        if (playerid!=0) {
            NetworkService.getInstance()
                    .getJSONApi()
                    .getPlayerInfoById(playerid)
                    .enqueue(new Callback<Teams>() {

                        @Override
                        public void onResponse(Call<Teams> call, Response<Teams> response) {
                            Teams teams = response.body();
                            if (teams != null) {
                                List<Person> personList = teams.getPeople();
                                if (personList != null && personList.size() > 0) {
                                    Person person = personList.get(0);
                                    if (person != null) {
                                        playerName.setText(person.getFullName());
                                        playerNumber.setText(person.getPrimaryNumber());
                                        birthDay.setText(person.getBirthDate());
                                        currentAge.setText(person.getCurrentAge()+"");
                                        birthCity.setText(person.getBirthCity());
                                        birthCountry.setText(person.getBirthCountry());
                                        height.setText(person.getHeight());
                                        weight.setText(person.getWeight()+"");
                                        shootsCatches.setText(person.getShootsCatches());
                                    }
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<Teams> call, Throwable t) {

                        }
                    });

            NetworkService.getInstance()
                    .getJSONApi()
                    .getPlayerStats(playerid)
                    .enqueue(new Callback<PlayerStat>() {

                        @Override
                        public void onResponse(Call<PlayerStat> call, Response<PlayerStat> response) {
                            PlayerStat stat = response.body();

                            if (stat != null) {
                                List<StatPlayerStat> stats = stat.getStats();
                                if (stats != null && stats.size() > 0) {
                                    List<SplitPlayerStat> splits = stats.get(0).getSplits();
                                    if (splits != null && splits.size() > 0) {
                                        Stat__1 stat__1 = splits.get(0).getStat();
                                        if (stat__1 != null) {
                                            wins.setText(stat__1.getWins() + "");
                                            losses.setText(stat__1.getLosses() + "");
                                            ot.setText(stat__1.getOt() + "");
                                            shutouts.setText(stat__1.getShutouts() + "");
                                            timeOnIce.setText(stat__1.getTimeOnIce());
                                            saves.setText(stat__1.getSaves() + "");
                                            ppsaves.setText(stat__1.getPowerPlaySaves() + "");
                                            goalsagainstgame.setText(String.format("%.2f", stat__1.getGoalAgainstAverage()));
                                            games_goalie.setText(stat__1.getGames() + "");
                                            shotsAgainst.setText(stat__1.getShotsAgainst() + "");
                                            goalsAgainst.setText(stat__1.getGoalsAgainst()+"");
                                            powerPlaySavePercentage.setText(String.format("%.2f", stat__1.getPowerPlaySavePercentage()));
                                            evenStrengthSavePercentage.setText(String.format("%.2f", stat__1.getEvenStrengthSavePercentage()));
                                            gamesStarted.setText(stat__1.getGamesStarted() + "");

                                            double saveProcent = 0;
                                            double winsProcent;


                                            try {
                                                saveProcent = stat__1.getSavePercentage();
                                                winsProcent = (100 * (stat__1.getWins()+stat__1.getShutouts())) / stat__1.getGames();

                                            } catch (ArithmeticException e) {
                                                System.out.println(e.getMessage());
                                                saveProcent = 0;
                                                winsProcent = 0;
                                            }
                                            setChart(saveProcent, chartSave);
                                            setChart(winsProcent / 100, chartWins);



                                    }else System.out.println("stat__1==null");
                                } else {
                                    //TODO
                                    System.out.println("splits==null");
                                }
                            }else System.out.println("stats == null");

                        } else System.out.println("stat==null");
                        }

                        @Override
                        public void onFailure(Call<PlayerStat> call, Throwable t) {

                        }
                    });
        }
    }


    private void setChart(double data, PieChart chart) {
        List<PieEntry> pieEntryList = new ArrayList<>();
        PieData pieData;

        chart.animateY(1000, Easing.EaseInOutQuad);
        Legend l = chart.getLegend();
        l.setEnabled(false);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        chart.setUsePercentValues(true);
        chart.setRotationEnabled(false);
        chart.setDrawHoleEnabled(true);
        chart.setHoleRadius(70f);
        chart.setHoleColor(Color.TRANSPARENT);
        chart.setEntryLabelColor(Color.TRANSPARENT);
        chart.setDrawCenterText(true);
        chart.setCenterTextSize(12);
        chart.setCenterTextColor(Color.WHITE);
        chart.setCenterText(String.valueOf(((double) Math.round((data) * 1000d) / 1000d)));
        Description description = new Description();
        description.setText("");
        chart.setDescription(description);
        float value2 = (float) (100 - (data * 100));
        pieEntryList.add(new PieEntry((float) (data * 100), ""));
        pieEntryList.add(new PieEntry(value2, ""));

        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "");
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#88FF6500"));
        colors.add(Color.GRAY);
        pieDataSet.setColors(colors);
        pieData = new PieData(pieDataSet);
        pieData.setValueTextColor(Color.TRANSPARENT);

        chart.setData(pieData);
        chart.setEntryLabelTextSize(2);

        chart.invalidate();
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

    public void getPlayerRanks(int playerid) {

        NetworkService.getInstance()
                .getJSONApi()
                .getPlayerRanks(playerid)
                .enqueue(new Callback<PlayerRank>() {

                    @Override
                    public void onResponse(Call<PlayerRank> call, Response<PlayerRank> response) {
                        if (response.body()!=null) {
                            if (response.body().getStats()!=null&&response.body().getStats().size()>0){
                                if (response.body().getStats().get(0).getSplits()!=null&&response.body().getStats().get(0).getSplits().size()>0) {
                                    rank_wins.setText(response.body().getStats().get(0).getSplits().get(0).getStat().getWins());
                                    rank_saves.setText(response.body().getStats().get(0).getSplits().get(0).getStat().getSaves());
                                    rank_shutOuts.setText(response.body().getStats().get(0).getSplits().get(0).getStat().getShutOuts());
                                    rank_shotsAgainst.setText(response.body().getStats().get(0).getSplits().get(0).getStat().getShotsAgainst());
                                    rank_saves.setText(response.body().getStats().get(0).getSplits().get(0).getStat().getSaves());
                                    rank_timeOnIce.setText(response.body().getStats().get(0).getSplits().get(0).getStat().getTimeOnIce());

                                    if (Integer.parseInt(rank_wins.getText().toString().replaceAll("\\D+",""))<=5) {
                                        FrameLayout frameLayout1 = findViewById(R.id.framePoints);
                                        frameLayout1.setBackgroundResource(R.drawable.corners10_marked);
                                    }
                                    if (Integer.parseInt(rank_saves.getText().toString().replaceAll("\\D+",""))<=5) {
                                        FrameLayout frameLayout1 = findViewById(R.id.frameGoals);
                                        frameLayout1.setBackgroundResource(R.drawable.corners10_marked);
                                    }
                                    if (Integer.parseInt(rank_shutOuts.getText().toString().replaceAll("\\D+",""))<=5) {
                                        FrameLayout frameLayout1 = findViewById(R.id.frameAssists);
                                        frameLayout1.setBackgroundResource(R.drawable.corners10_marked);
                                    }
                                    if (Integer.parseInt(rank_shotsAgainst.getText().toString().replaceAll("\\D+",""))<=5) {
                                        FrameLayout frameLayout1 = findViewById(R.id.frameShots);
                                        frameLayout1.setBackgroundResource(R.drawable.corners10_marked);
                                    }
                                    if (Integer.parseInt(rank_saves.getText().toString().replaceAll("\\D+",""))<=5) {
                                        FrameLayout frameLayout1 = findViewById(R.id.frameHits);
                                        frameLayout1.setBackgroundResource(R.drawable.corners10_marked);
                                    }
                                    if (Integer.parseInt(rank_timeOnIce.getText().toString().replaceAll("\\D+",""))<=5) {
                                        FrameLayout frameLayout1 = findViewById(R.id.framePlusMinus);
                                        frameLayout1.setBackgroundResource(R.drawable.corners10_marked);
                                    }

                                }
                            }
                        }



                    }

                    @Override
                    public void onFailure(Call<PlayerRank> call, Throwable t) {

                    }
                });
    }

}