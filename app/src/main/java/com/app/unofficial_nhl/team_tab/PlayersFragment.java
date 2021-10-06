package com.app.unofficial_nhl.team_tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.unofficial_nhl.NetworkService;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.pojos.Roster_;
import com.app.unofficial_nhl.pojos.Teams;
import com.app.unofficial_nhl.ui.cardview.CardViewAdapterPlayers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class PlayersFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView teamName;

    public PlayersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        teamName = getActivity().findViewById(R.id.teamName);
        recyclerView = getView().findViewById(R.id.players_list_recycleview);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        int teamid = StaticData.teamToIdMap.get(teamName.getText());

        NetworkService.getInstance()
                .getJSONApi()
                .getRosterOfTeam(teamid)
                .enqueue(new Callback<Teams>() {
                    @Override
                    public void onResponse(@NonNull Call<Teams> call, @NonNull Response<Teams> response) {
                        Teams data = response.body();
                        List<Roster_> players = data.getTeams().get(0).getRoster().getRoster();
                        List<String> playersNames = new ArrayList<>();
                        List<String> jerseyNumbers = new ArrayList<>();
                        List<String> positionTypes = new ArrayList<>();
                        List<String> positionNames = new ArrayList<>();
                        List<Integer> ids = new ArrayList<>();

                        for (Roster_ pl : players) {
                            jerseyNumbers.add(pl.getJerseyNumber());
                            playersNames.add(pl.getPerson().getFullName());
                            positionTypes.add(pl.getPosition().getType());
                            positionNames.add(pl.getPosition().getName());
                            ids.add(pl.getPerson().getId());
                            System.out.println(pl.getPerson().getId());
                        }
                        CardViewAdapterPlayers cardViewAdapterPlayers = new CardViewAdapterPlayers(playersNames, jerseyNumbers, positionTypes, positionNames, ids);
                        recyclerView.setAdapter(cardViewAdapterPlayers);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });


    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_players_tab2, container , false);



        return view;
    }
}
