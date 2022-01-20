package com.app.unofficial_nhl;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

public class PlayersActivity extends AppCompatActivity {

    private TextView playerName, playerNumber, birthDay, currentAge, birthCity, birthCountry, height, weight;
    private TextView shootsCatches, goals, assists, shots, games, timeOnIce, pim, hits, WinningGoals, overTimeGoals;
    private TextView blocked, plusMinus, points, shifts, timeOnIcePerGame;
    private PieChart chartGoalsP, chartPointsP;
    private TextView rank_points, rank_goals, rank_assists, rank_shots, rank_hits, rankPlusMinus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        playerName = findViewById(R.id.playerName);
        playerNumber = findViewById(R.id.playerNumber);
        birthDay = findViewById(R.id.birthDay);
        currentAge = findViewById(R.id.currentAge);
        birthCity = findViewById(R.id.birthCity);
        birthCountry = findViewById(R.id.birthCountry);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        shootsCatches = findViewById(R.id.shootsCatches);

        goals = findViewById(R.id.goals);
        assists = findViewById(R.id.assists);
        shots = findViewById(R.id.shots);
        games = findViewById(R.id.games);
        timeOnIce = findViewById(R.id.timeOnIce);
        pim = findViewById(R.id.pim);
        hits = findViewById(R.id.hits);
        WinningGoals = findViewById(R.id.WinningGoals);
        overTimeGoals = findViewById(R.id.overTimeGoals);
        blocked = findViewById(R.id.blocked);
        plusMinus = findViewById(R.id.plusMinus);
        points = findViewById(R.id.points);
        shifts = findViewById(R.id.shifts);
        timeOnIcePerGame = findViewById(R.id.timeOnIcePerGame);
        chartGoalsP = findViewById(R.id.chartGoalsP);
        chartPointsP = findViewById(R.id.chartPointsP);

        rank_points = findViewById(R.id.rank_points);
        rank_goals = findViewById(R.id.rank_goals);
        rank_assists = findViewById(R.id.rank_assists);
        rank_shots = findViewById(R.id.rank_shots);
        rank_hits = findViewById(R.id.rank_hits);
        rankPlusMinus = findViewById(R.id.rankPlusMinus);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            setupActionBar();

            //actionBar.hide();

        }

        int playerid = getIntent().getIntExtra("playerid",0);
        String teamgoals = getIntent().getStringExtra("teamgoals");

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
                                            goals.setText(stat__1.getGoals() + "");
                                            assists.setText(stat__1.getAssists() + "");
                                            shots.setText(stat__1.getShots() + "");
                                            games.setText(stat__1.getGames() + "");
                                            timeOnIce.setText(stat__1.getTimeOnIce());
                                            pim.setText(stat__1.getPim() + "");
                                            hits.setText(stat__1.getHits() + "");
                                            WinningGoals.setText(stat__1.getGameWinningGoals() + "");
                                            overTimeGoals.setText(stat__1.getOverTimeGoals() + "");
                                            blocked.setText(stat__1.getBlocked() + "");
                                            plusMinus.setText(stat__1.getPlusMinus() + "");
                                            points.setText(stat__1.getPoints() + "");
                                            shifts.setText(stat__1.getShifts() + "");
                                            timeOnIcePerGame.setText(stat__1.getEvenTimeOnIcePerGame() + "");
                                            double procentGoals;
                                            double procentPoints;

                                            try {
                                                procentGoals = (100 * stat__1.getGoals()) / stat__1.getShots();
                                                procentPoints = (100 * stat__1.getGoals()) / Integer.parseInt(teamgoals);

                                            } catch (ArithmeticException e) {
                                                System.out.println(e.getMessage());
                                                procentGoals = 0;
                                                procentPoints = 0;
                                            }
                                            setChart(procentGoals / 100, chartGoalsP);
                                            setChart(procentPoints / 100, chartPointsP);


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
        chart.setCenterTextSize(16);
        chart.setCenterTextColor(Color.WHITE);
        chart.setCenterText((int) (data * 100) + "%");
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
                                    rank_points.setText(response.body().getStats().get(0).getSplits().get(0).getStat().getRankPoints());
                                    rank_goals.setText(response.body().getStats().get(0).getSplits().get(0).getStat().getRankGoals());
                                    rank_assists.setText(response.body().getStats().get(0).getSplits().get(0).getStat().getRankAssists());
                                    rank_shots.setText(response.body().getStats().get(0).getSplits().get(0).getStat().getRankShots());
                                    rank_hits.setText(response.body().getStats().get(0).getSplits().get(0).getStat().getRankHits());
                                    rankPlusMinus.setText(response.body().getStats().get(0).getSplits().get(0).getStat().getRankPlusMinus());

                                    if (Integer.parseInt(rank_points.getText().toString().replaceAll("\\D+",""))<=5) {
                                        FrameLayout frameLayout1 = findViewById(R.id.framePoints);
                                        frameLayout1.setBackgroundResource(R.drawable.corners10_marked);
                                    }
                                    if (Integer.parseInt(rank_goals.getText().toString().replaceAll("\\D+",""))<=5) {
                                        FrameLayout frameLayout1 = findViewById(R.id.frameGoals);
                                        frameLayout1.setBackgroundResource(R.drawable.corners10_marked);
                                    }
                                    if (Integer.parseInt(rank_assists.getText().toString().replaceAll("\\D+",""))<=5) {
                                        FrameLayout frameLayout1 = findViewById(R.id.frameAssists);
                                        frameLayout1.setBackgroundResource(R.drawable.corners10_marked);
                                    }
                                    if (Integer.parseInt(rank_shots.getText().toString().replaceAll("\\D+",""))<=5) {
                                        FrameLayout frameLayout1 = findViewById(R.id.frameShots);
                                        frameLayout1.setBackgroundResource(R.drawable.corners10_marked);
                                    }
                                    if (Integer.parseInt(rank_hits.getText().toString().replaceAll("\\D+",""))<=5) {
                                        FrameLayout frameLayout1 = findViewById(R.id.frameHits);
                                        frameLayout1.setBackgroundResource(R.drawable.corners10_marked);
                                    }
                                    if (Integer.parseInt(rankPlusMinus.getText().toString().replaceAll("\\D+",""))<=5) {
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