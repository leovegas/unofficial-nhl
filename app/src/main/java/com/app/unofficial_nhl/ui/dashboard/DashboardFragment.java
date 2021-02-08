package com.app.unofficial_nhl.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.app.unofficial_nhl.NetworkService;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.MyTableAdapter;
import com.app.unofficial_nhl.helper_classes.MyTableViewListener;
import com.app.unofficial_nhl.helper_classes.data_models.Cell;
import com.app.unofficial_nhl.helper_classes.data_models.ColumnHeader;
import com.app.unofficial_nhl.helper_classes.data_models.RowHeader;
import com.app.unofficial_nhl.pojos.Record;
import com.app.unofficial_nhl.pojos.TeamRecord;
import com.app.unofficial_nhl.pojos.Teams;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.sort.SortState;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private List<RowHeader> mRowHeaderList;
    private List<ColumnHeader> mColumnHeaderList;
    private List<List<Cell>> mCellList;
    private                         Teams teams;


    private MyTableAdapter mTableAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

//        Intent myIntent = new Intent(getActivity(), MainActivity.class);
//        getActivity().startActivity(myIntent);
        Spinner spinner = (Spinner) root.findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) root.findViewById(R.id.spinner2);
        TableView mTableView = root.findViewById(R.id.content_container);



        mRowHeaderList = new ArrayList<RowHeader>();
        mColumnHeaderList = new ArrayList<ColumnHeader>();
        mCellList = new ArrayList<List<Cell>>();

        NetworkService.getInstance()
                .getJSONApi()
                .getStandings()
                .enqueue(new Callback<Teams>() {
                    @Override
                    public void onResponse(@NonNull Call<Teams> call, @NonNull Response<Teams> response) {
                        teams = response.body();
                        mTableAdapter.setAllItems(createColumnHeaderModelList(), createRowHeaderList(teams), createCellList(teams));
                    }

                    @Override
                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });


        mTableAdapter = new MyTableAdapter();
        mTableView.setAdapter(mTableAdapter);
        mTableView.setTableViewListener(new MyTableViewListener(mTableView));


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), R.layout.spinner_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }
        };

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(root.getContext(), R.layout.spinner_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }

        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("Regular");
        adapter.add("Playoffs");
        adapter.add("Regular");

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.add("by Division");
        adapter2.add("by Conference");
        adapter2.add("by League");
        adapter2.add("by League");


        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount()); //set the hint the default selection so it appears on launch.

        spinner2.setAdapter(adapter2);
        spinner2.setSelection(adapter2.getCount()); //set the hint the default selection so it appears on launch.

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(root.getContext(), "Spinner item 1!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(root.getContext(), "Spinner item 2!", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        return root;
    }

    private List<ColumnHeader> createColumnHeaderModelList() {
        List<ColumnHeader> list = new ArrayList<>();

        // Create Column Headers
        list.add(new ColumnHeader("Record"));
        list.add(new ColumnHeader("Wins"));
        list.add(new ColumnHeader("GS/GA"));
        list.add(new ColumnHeader("Points"));
        list.add(new ColumnHeader("GP"));
        list.add(new ColumnHeader("Streak"));
        list.add(new ColumnHeader("divisionRank"));
        list.add(new ColumnHeader("conferenceRank"));
        list.add(new ColumnHeader("leagueRank"));
        list.add(new ColumnHeader("wildCardRank"));

        return list;
    }

    private List<RowHeader> createRowHeaderList(Teams teams) {

        List<RowHeader> list = new ArrayList<>();

        for (Record team : teams.getRecords()) {
            List<TeamRecord> teamRecords = team.getTeamRecords();
            for (int i = 0, teamRecordsSize = teamRecords.size(); i < teamRecordsSize; i++) {
                TeamRecord t = teamRecords.get(i);

                // The order should be same with column header list;
                list.add(new RowHeader(t.getTeam().getName()));
            }
        }
        return list;
    }

    private List<List<Cell>> createCellList(Teams teams) {
        List<List<Cell>> lists = new ArrayList<>();

        // Creating cell model list from User list for Cell Items
        // In this example, User list is populated from web service
        for (Record team : teams.getRecords()) {
            List<TeamRecord> teamRecords = team.getTeamRecords();
            for (int i = 0, teamRecordsSize = teamRecords.size(); i < teamRecordsSize; i++) {
                TeamRecord t = teamRecords.get(i);
                List<Cell> list = new ArrayList<>();

                // The order should be same with column header list;
                list.add(new Cell(t.getLeagueRecord().getWins()+ "-" + t.getLeagueRecord().getLosses() + "-" + t.getLeagueRecord().getOt()));    // "Nickname"
                list.add(new Cell(t.getRegulationWins()+""));       // "Email"
                list.add(new Cell(t.getGoalsScored()+"/"+t.getGoalsAgainst()));   // "BirthDay"
                list.add(new Cell(t.getPoints()+""));      // "Gender"
                list.add(new Cell(t.getGamesPlayed()+""));         // "Age"
                list.add(new Cell(t.getStreak().getStreakCode()));         // "Job"
                list.add(new Cell(t.getDivisionRank()));      // "Salary"
                list.add(new Cell(t.getConferenceRank())); // "CreatedAt"
                list.add(new Cell(t.getLeagueRank())); // "UpdatedAt"
                list.add(new Cell(t.getWildCardRank()));    // "Address"

                // Add
                lists.add(list);
            }
        }

        return lists;
    }



}