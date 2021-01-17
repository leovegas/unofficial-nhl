package com.app.unofficial_nhl.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.app.unofficial_nhl.NetworkService;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.ListRow;
import com.app.unofficial_nhl.helper_classes.MyCustomArrayAdapter;
import com.app.unofficial_nhl.pojos.Game;
import com.app.unofficial_nhl.pojos.Team_;
import com.app.unofficial_nhl.pojos.Teams;
import com.app.unofficial_nhl.pojos.Teams_;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =  ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        ArrayList<Game> gamesByDate = new ArrayList<>();
       // getActivity().getActionBar().show();
        NetworkService.getInstance()
                .getJSONApi()
                .getSheduledGamesByDate("2021-01-17")
                .enqueue(new Callback<Teams>() {
                    @Override
                    public void onResponse(@NonNull Call<Teams> call, @NonNull Response<Teams> response) {
                        Teams data = response.body();
                        if (data != null) {
                            gamesByDate.addAll(data.getDates().get(0).getGames());
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }

                });

        @DrawableRes
        int logo_team1 = R.drawable.arizona;
        @DrawableRes
        int logo_team2 = R.drawable.new_york_rangers;

        ArrayList<ListRow> alldata = new ArrayList<ListRow>();

        for (Game game : gamesByDate)
        {
            System.out.println(game.getTeams().getHome().getTeam().getName());
            System.out.println(game.getTeams().getAway().getTeam().getName());
            Team_ team_h = (Team_) game.getTeams().getHome().getTeam();
            Team_ team_a = (Team_) game.getTeams().getAway().getTeam();

            ListRow listRow = new ListRow(team_h.getName(), team_a.getName(), logo_team1, logo_team2);
            alldata.add(listRow);
        }

        MyCustomArrayAdapter adapter = new MyCustomArrayAdapter (getActivity(), alldata);
        final ListView listview = (ListView) root.findViewById(R.id.listview);
        listview.setAdapter(adapter);
        return root;

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