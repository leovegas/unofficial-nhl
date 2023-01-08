package com.app.unofficial_nhl.team_tab;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.unofficial_nhl.GoaliePlayersActivity;
import com.app.unofficial_nhl.NetworkService;
import com.app.unofficial_nhl.PlayersActivity;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.pojos.Roster_;
import com.app.unofficial_nhl.pojos.Teams;
import com.app.unofficial_nhl.ui.cardview.CardViewAdapterPlayers;
import com.app.unofficial_nhl.ui.home.RecyclerTouchListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class PlayersFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView teamName, goalsScoredV;

    public PlayersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        teamName = getActivity().findViewById(R.id.teamName);
        recyclerView = getView().findViewById(R.id.players_list_recycleview);
        goalsScoredV = getActivity().findViewById(R.id.goalsScored);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                CardView cv = (CardView) view.findViewById(R.id.cv);
                int playerid = (int) cv.getTag();
                TextView positionText = cv.findViewById(R.id.positionText);
                String role = (String) positionText.getText();

                System.out.println(goalsScoredV.getText());

                showAbout(getActivity(), getActivity().getCurrentFocus());

//                if (role.equals("Goalie")) {
//                    view.animate().withLayer().alpha(0).setDuration(100).withEndAction(new Runnable() {
//                        @Override
//                        public void run() {
//                            view.animate().withLayer()
//                                    .alpha(1f).setDuration(100).start();
//                            Intent intent = new Intent(getActivity(), GoaliePlayersActivity.class);
//                            intent.putExtra("playerid", playerid);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
//                        }
//                    }).setStartDelay(200).start();
//                } else {
//                    view.animate().withLayer().alpha(0).setDuration(100).withEndAction(new Runnable() {
//                        @Override
//                        public void run() {
//                            view.animate().withLayer()
//                                    .alpha(1f).setDuration(100).start();
//
//                            Intent intent = new Intent(getActivity(), PlayersActivity.class);
//                            intent.putExtra("playerid", playerid);
//                            intent.putExtra("teamgoals", goalsScoredV.getText());
//
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
//                        }
//                    }).setStartDelay(200).start();
//                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


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

    public static void showAbout(Context context, View view) {

        // setup the alert builder
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle("Google Play");
        builder.setMessage("Thank you for using our app!  \n\n The full version of the app with the a fullscreen video recaps and with a information about players can be purchased on Google Play.  \n \n \n"
                +"Contacts: \n timplay89@gmail.com");

        // add a button
        builder.setPositiveButton("Get it", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.app.nhl_recaps_stats_schelude_p"));
                context.startActivity(intent);
                dialog.cancel();
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_players_tab2, container , false);

        return view;
    }
}
