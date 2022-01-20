package com.app.unofficial_nhl.team_tab;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.app.unofficial_nhl.NetworkService;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.pojos.*;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.snackbar.Snackbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class TeamStatsFragment extends Fragment {

    PieChart chart;
    PieData pieData;
    List<PieEntry> pieEntryList = new ArrayList<>();
    private TextView confAndPos, homeArena, pointsV, leagueRankV, gamesPlayedV, goalsScoredV, goalsAgainstV, streakV,winsV,lossesV,otV,streakField;
    private TextView city, firstYear, site;
    ProgressBar progressBarWins;
    ProgressBar progressBarLosses;
    ProgressBar progressBarOt;
    TextView headcoach;

    public TeamStatsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressBarWins = (ProgressBar) getView().findViewById(R.id.progressBarWins);
        progressBarLosses = (ProgressBar) getView().findViewById(R.id.progressBarLosses);
        progressBarOt = (ProgressBar) getView().findViewById(R.id.progressBarOt);

        chart = getView().findViewById(R.id.chart);

        homeArena = getActivity().findViewById(R.id.homeArena);
        confAndPos = getActivity().findViewById(R.id.confAndPos);
        pointsV = getView().findViewById(R.id.points);
        leagueRankV = getView().findViewById(R.id.leagueRank);
        gamesPlayedV = getView().findViewById(R.id.gamesPlayed);
        goalsScoredV = getView().findViewById(R.id.goalsScored);
        goalsAgainstV = getView().findViewById(R.id.goalsAgainst);
        streakV = getView().findViewById(R.id.streak);
        streakField = getView().findViewById(R.id.streakField);

        winsV = getView().findViewById(R.id.wins);
        lossesV = getView().findViewById(R.id.losses);
        otV = getView().findViewById(R.id.ot);
        city = getActivity().findViewById(R.id.city);
        firstYear = getActivity().findViewById(R.id.firstYearOfPlay);
        ImageView teamlogoView = getActivity().findViewById(R.id.teamLogo);
        headcoach = getActivity().findViewById(R.id.headcoach);

        teamlogoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getTag() != null) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Go to \n "+v.getTag() )
                            .setMessage("Are you sure?")
                            .setNegativeButton(android.R.string.no, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {
                                    Uri uri = Uri.parse(String.valueOf(v.getTag()));
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);
                                }
                            }).create().show();


                }

            }
        });

        TextView textViewId = (TextView) getActivity().findViewById(R.id.teamName);
        int id = StaticData.teamToIdMap.get(textViewId.getText());

        //getCoach(id);

        NetworkService.getInstance()
                .getJSONApi()
                .getTeamInfo(id)
                .enqueue(new Callback<Teams>() {
                    @Override
                    public void onResponse(@NonNull Call<Teams> call, @NonNull Response<Teams> response) {
                        Teams data = response.body();
                        Team team = data.getTeams().get(0);
                        homeArena.setText(team.getVenue().getName());
                        confAndPos.setText(team.getConference().getName());
                        city.setText(team.getVenue().getCity());
                        firstYear.setText(team.getFirstYearOfPlay());
                        teamlogoView.setTag(team.getOfficialSiteUrl());
                        headcoach.setText(team.getDivision().getName());

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
                                                        int w = teamRecord.getLeagueRecord().getWins();
                                                        int l = teamRecord.getLeagueRecord().getLosses();
                                                        int ot = teamRecord.getLeagueRecord().getOt();
                                                        double pointPercent = teamRecord.getPointsPercentage();
                                                        int points = teamRecord.getPoints();
                                                        String leagueRank = teamRecord.getLeagueRank();
                                                        int gamesPlayed = teamRecord.getGamesPlayed();
                                                        int goalsScored = teamRecord.getGoalsScored();
                                                        int goalsAgainst = teamRecord.getGoalsAgainst();
                                                        if (teamRecord.getStreak()!=null) {
                                                            String streak = teamRecord.getStreak().getStreakCode();
                                                            streakV.setText(streak);
                                                        } else {
                                                            streakField.setText("PP rank");
                                                            String pprank = teamRecord.getPpLeagueRank();
                                                            streakV.setText(pprank);
                                                        }
                                                        setProgressAnimate(progressBarWins,w,w+l+ot);
                                                        setProgressAnimate(progressBarLosses,l,w+l+ot);
                                                        setProgressAnimate(progressBarOt,ot,w+l+ot);
                                                        setChart(pointPercent);
                                                        pointsV.setText(points+"");
                                                        leagueRankV.setText(leagueRank);
                                                        gamesPlayedV.setText(gamesPlayed+"");
                                                        goalsScoredV.setText(goalsScored+"");
                                                        goalsAgainstV.setText(goalsAgainst+"");
                                                        winsV.setText(w+"");
                                                        lossesV.setText(l+"");
                                                        otV.setText(ot+"");

                                                        if (!rank.equals("0")) {
                                                            confAndPos.append(" " + rank + "");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                                        confAndPos.append("");
                                        System.out.println("Error occurred while getting request!");
                                        t.printStackTrace();
                                    }
                                });
                    }

                    @Override
                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                        confAndPos.append("");
                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teams_stat_tab1, container, false);
        return view;

    }



    private void setChart(double data) {
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
        chart.setCenterText((int) (data*100)+"%");
        Description description = new Description();
        description.setText("");
        chart.setDescription(description);
        float value2 = (float) (100-(data*100));
        pieEntryList.add(new PieEntry((float) (data*100),""));
        pieEntryList.add(new PieEntry(value2,""));

        PieDataSet pieDataSet = new PieDataSet(pieEntryList,"");
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


    private void doSomethingOnUi(ProgressBar progressBar, int progress) {
        Handler uiThread = new Handler();

        uiThread.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(0);

                progressBar.incrementSecondaryProgressBy(progress);
                progressBar.setProgress(progress);

                Toast.makeText(getContext(), "Please wait... Loading", 5000).show();
            }
        });
    }

    private void setProgressAnimate(ProgressBar pb, int progressTo, int max)
    {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                pb.setMax(max);
                pb.setProgress(0);
                ObjectAnimator animation = ObjectAnimator.ofInt(pb, "progress", pb.getProgress(), progressTo);
                animation.setDuration(2000);
                animation.setAutoCancel(true);
                animation.setInterpolator(new DecelerateInterpolator());
                animation.start();

                //Toast.makeText(getContext(), "Please wait... Loading", 5000).show();
            }
        });

    }

    public void getCoach(int teamid) {
        NetworkService.getInstance()
                .getJSONApi()
                .getCoach(teamid)
                .enqueue(new Callback<TeamsCoach>() {

                    @Override
                    public void onResponse(Call<TeamsCoach> call, Response<TeamsCoach> response) {
                        if (response.body() != null  && response.body().getTeams().get(0).getCoaches()!=null) {
                            System.out.println(response.body().getTeams().get(0).getName());
                            Coach coach = response.body().getTeams().get(0).getCoaches().get(0);
                            TextView headcoach = getActivity().findViewById(R.id.headcoach);
                            headcoach.setText(coach.getPerson().getFullName());
                        }
                    }

                    @Override
                    public void onFailure(Call<TeamsCoach> call, Throwable t) {

                    }
                });
    }

}
