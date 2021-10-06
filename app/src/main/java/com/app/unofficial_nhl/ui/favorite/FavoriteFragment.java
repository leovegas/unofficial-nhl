package com.app.unofficial_nhl.ui.favorite;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.TeamInfoActivity;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.helper_classes.TinyDB;
import com.app.unofficial_nhl.ui.cardview.CardViewAdapterTeams;
import com.app.unofficial_nhl.ui.home.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    private FavoritesViewModel notificationsViewModel;

    RecyclerView recyclerView;

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

        List<Integer> logos = (ArrayList<Integer>) new ArrayList<>(StaticData.logosMap.values());
        List<String> teams = (ArrayList<String>) new ArrayList<>(StaticData.logosMap.keySet());

        CardViewAdapterTeams cardViewAdapterTeams = new CardViewAdapterTeams(logos, teams);
        recyclerView.setAdapter(cardViewAdapterTeams);

        TinyDB tinydb = new TinyDB(getContext());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View v, int position) {
                String team = teams.get(position);
                v.animate().withLayer().alpha(0).setDuration(100).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        v.animate().withLayer()
                                .alpha(1f).setDuration(100).start();
                        Intent intent = new Intent(getContext(), TeamInfoActivity.class);
                        intent.putExtra("TEAMNAME", team);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }).setStartDelay(200).start();
            }

            @Override
            public void onLongClick(View view, int position) {
                ImageView reminder = (ImageView) view.findViewById(R.id.reminder);
                reminder.setBackgroundColor(Color.RED);
                String team = teams.get(position);
                if (tinydb.getInt(team)==0) {
                    tinydb.putInt(team,StaticData.teamToIdMap.get(team));
                }else {
                    tinydb.remove(team);
                    reminder.setBackgroundColor(Color.TRANSPARENT);
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


}