package com.app.unofficial_nhl.ui.favorite;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.unofficial_nhl.NetworkService;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.TeamInfoActivity;
import com.app.unofficial_nhl.alarms.DailyReceiver;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.helper_classes.TinyDB;
import com.app.unofficial_nhl.pojos.Teams;
import com.app.unofficial_nhl.ui.cardview.CardViewAdapterTeams;
import com.app.unofficial_nhl.ui.home.RecyclerTouchListener;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.*;

public class FavoriteFragment extends Fragment {

    private FavoritesViewModel notificationsViewModel;
    private FrameLayout buttonsLayout;
    private ImageButton info, alarm;
    private MotionLayout motionLayout;
    private boolean open = false;


    RecyclerView recyclerView;

    private Map<Calendar, Integer> datemap = new HashMap<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(FavoritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        ProgressBar loadingBar = root.findViewById(R.id.progressBar2);
        recyclerView = root.findViewById(R.id.listviewfavs);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        loadingBar.setVisibility(View.GONE);
        motionLayout = (MotionLayout) getActivity().findViewById(R.id.container);
        //motionLayout.setTransition(R.id.start, R.id.start);

        List<Integer> logos = (ArrayList<Integer>) new ArrayList<>(StaticData.logosMap.values());
        List<String> teams = (ArrayList<String>) new ArrayList<>(StaticData.logosMap.keySet());

        CardViewAdapterTeams cardViewAdapterTeams = new CardViewAdapterTeams(logos, teams);
        recyclerView.setAdapter(cardViewAdapterTeams);

        TinyDB tinydb = new TinyDB(getContext());
        tinydb.putInt("RECORDS_NOW", 0);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View v, int position) {
                System.out.println(recyclerView.getAdapter().getItemCount());
                System.out.println("POSITION " + position);
                buttonsLayout = (FrameLayout) v.findViewById(R.id.buttonsLayout);
                info = (ImageButton) v.findViewById(R.id.teaminfo);
                info.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View vinfo) {
                        v.performClick();
                        String team = teams.get(position);
                        vinfo.animate().withLayer().alpha(0).setDuration(100).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                vinfo.animate().withLayer()
                                        .alpha(1f).setDuration(100).start();
                                Intent intent = new Intent(getContext(), TeamInfoActivity.class);
                                intent.putExtra("TEAMNAME", team);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }).setStartDelay(200).start();
                    }
                });

                alarm = (ImageButton) v.findViewById(R.id.alarm);
                alarm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View valarm) {
                        v.performClick();
                        ArrayList<Integer> teamsids = new ArrayList<>();
                        ImageView reminder = (ImageView) v.findViewById(R.id.reminder);
                        reminder.setVisibility(View.VISIBLE);
                        String team = teams.get(position);
                        int teamid = StaticData.teamToIdMap.get(team);
                        if (tinydb.getInt(team) == 0) {
                            tinydb.putInt(team, StaticData.teamToIdMap.get(team));

                            reminder.setVisibility(View.VISIBLE);
                            getAllGamesForTeam(teamid, team);
                            Toast.makeText(getContext(), "Alarm enabled", 1000).show();
                        } else {
                            tinydb.remove(team);
                            reminder.setVisibility(View.INVISIBLE);

                            StaticData.removeAlarm(getContext(), DailyReceiver.class, teamid, team);
                            Toast.makeText(getContext(), "Alarm disabled", 1000).show();
                        }
                    }
                });


                if (open) {
                    ValueAnimator anim = ValueAnimator.ofInt(buttonsLayout.getMeasuredHeight(), 0);
                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int val = (Integer) valueAnimator.getAnimatedValue();
                            ViewGroup.LayoutParams layoutParams = buttonsLayout.getLayoutParams();
                            layoutParams.height = val;
                            buttonsLayout.setLayoutParams(layoutParams);
                        }
                    });
                    anim.setDuration(400);
                    anim.start();
                    open = false;
                } else {
                    ValueAnimator anim = ValueAnimator.ofInt(buttonsLayout.getMeasuredHeight(), 120);
                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int val = (Integer) valueAnimator.getAnimatedValue();
                            ViewGroup.LayoutParams layoutParams = buttonsLayout.getLayoutParams();
                            layoutParams.height = val;
                            buttonsLayout.setLayoutParams(layoutParams);
                        }
                    });
                    anim.setDuration(400);
                    anim.start();
                    open = true;
                }

            }

            @Override
            public void onLongClick(View view, int position) {
                ArrayList<Integer> teamsids = new ArrayList<>();
                ImageView reminder = (ImageView) view.findViewById(R.id.reminder);
                reminder.setVisibility(View.VISIBLE);
                String team = teams.get(position);
                int teamid = StaticData.teamToIdMap.get(team);
                if (tinydb.getInt(team) == 0) {
                    tinydb.putInt(team, StaticData.teamToIdMap.get(team));

                    reminder.setVisibility(View.VISIBLE);
                    getAllGamesForTeam(teamid, team);
                    Toast.makeText(getContext(), "Alarm enabled", 1000).show();
                } else {
                    tinydb.remove(team);
                    reminder.setVisibility(View.INVISIBLE);

                    StaticData.removeAlarm(getContext(), DailyReceiver.class, teamid, team);
                    Toast.makeText(getContext(), "Alarm disabled", 1000).show();
                }

            }
        }));

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

    public void getAllGamesForTeam(int teamid, String teamname) {
        NetworkService.getInstance()
                .getJSONApi()
                .getAllGamesForTeam(teamid)
                .enqueue(new Callback<Teams>() {

                    @Override
                    public void onResponse(Call<Teams> call, Response<Teams> response) {
                        ArrayList<String> calendarList = new ArrayList<>();
                        ArrayList<Integer> notesIDs = new ArrayList<>();
                        TinyDB tinydb = new TinyDB(getContext());

                        if (response.body() != null) {
                            for (com.app.unofficial_nhl.pojos.Date date : response.body().getDates()) {
                                calendarList.add(date.getGames().get(0).getGameDate());
                            }
                        }
                        datemap = new HashMap<>();
                        for (String s : calendarList) {
                            Map<Calendar, Integer> map = StaticData.StrToCalendar(s);
                            if (map != null) {
                                datemap.putAll(map);
                            }
                        }
/*
                        datemap.putAll(StaticData.StrToCalendar("2021-10-10T19:43:00Z"));
                        datemap.putAll(StaticData.StrToCalendar("2021-10-10T19:44:00Z"));
                        datemap.putAll(StaticData.StrToCalendar("2021-10-10T19:45:00Z"));
                        datemap.putAll(StaticData.StrToCalendar("2021-10-10T19:46:00Z"));
                        datemap.putAll(StaticData.StrToCalendar("2021-10-10T19:47:00Z"));
                        datemap.putAll(StaticData.StrToCalendar("2021-10-10T19:48:00Z"));
                        datemap.putAll(StaticData.StrToCalendar("2021-10-10T19:49:00Z"));
                        datemap.putAll(StaticData.StrToCalendar("2021-10-10T19:50:00Z"));
                        datemap.putAll(StaticData.StrToCalendar("2021-10-10T19:51:00Z"));
                        datemap.putAll(StaticData.StrToCalendar("2021-10-10T19:52:00Z"));
                        datemap.putAll(StaticData.StrToCalendar("2021-10-10T19:53:00Z"));*/


                        if (!datemap.isEmpty()) {
                            for (Map.Entry<Calendar, Integer> entry : datemap.entrySet()) {
                                Calendar k = entry.getKey();
                                Integer v = entry.getValue();
                                StaticData.setAlarm(getContext(), k, v, teamname, k.getTime().toString());
                                notesIDs.add(v);
                            }

                            tinydb.putListInt(teamname + "1", notesIDs);
                            System.out.println(tinydb.getListInt(teamname + "1").size());
                        }

                    }

                    @Override
                    public void onFailure(Call<Teams> call, Throwable t) {

                    }
                });
    }


}