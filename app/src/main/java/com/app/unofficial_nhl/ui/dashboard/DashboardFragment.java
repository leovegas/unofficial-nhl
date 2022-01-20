package com.app.unofficial_nhl.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.app.unofficial_nhl.NetworkService;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.ColumnHeaderLongPressPopup;
import com.app.unofficial_nhl.helper_classes.MyTableAdapter;
import com.app.unofficial_nhl.helper_classes.MyTableViewListener;
import com.app.unofficial_nhl.helper_classes.data_models.Cell;
import com.app.unofficial_nhl.helper_classes.data_models.ColumnHeader;
import com.app.unofficial_nhl.helper_classes.data_models.RowHeader;
import com.app.unofficial_nhl.helper_classes.holder.ColumnHeaderViewHolder;
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
    private Teams teams;
    private MotionLayout motionLayout;

    private MyTableAdapter mTableAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

//        Intent myIntent = new Intent(getActivity(), MainActivity.class);
//        getActivity().startActivity(myIntent);
        Spinner spinner = (Spinner) root.findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) root.findViewById(R.id.spinner2);
        TableView mTableView = (TableView) root.findViewById(R.id.content_container);
        motionLayout = (MotionLayout) getActivity().findViewById(R.id.container);
        //motionLayout.setTransition(R.id.start, R.id.start);

        mRowHeaderList = new ArrayList<RowHeader>();
        mColumnHeaderList = new ArrayList<ColumnHeader>();
        mCellList = new ArrayList<List<Cell>>();

        getTableData();

        mTableAdapter = new MyTableAdapter();
        mTableView.setAdapter(mTableAdapter);
        mTableView.setTableViewListener(new MyTableViewListener(mTableView));


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

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.add("West division");
        adapter2.add("Central division");
        adapter2.add("East division");
        adapter2.add("North division");
        adapter2.add("League (press here)");
        adapter2.add("League (press here)");


        spinner2.setAdapter(adapter2);
        spinner2.setSelection(adapter2.getCount()); //set the hint the default selection so it appears on launch.

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(root.getContext(), "West", Toast.LENGTH_SHORT).show();
                        NetworkService.getInstance()
                                .getJSONApi()
                                .getStandings()
                                .enqueue(new Callback<Teams>() {
                                    @Override
                                    public void onResponse(@NonNull Call<Teams> call, @NonNull Response<Teams> response) {
                                        teams = response.body();
                                        mTableAdapter.setAllItems(createColumnHeaderModelList(), createRowHeaderListWest(teams), createCellListWest(teams));
                                    }
                                    @Override
                                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                                        System.out.println("Error occurred while getting request!");
                                        t.printStackTrace();
                                    }
                                });
                        break;
                    case 1:
                        Toast.makeText(root.getContext(), "Central", Toast.LENGTH_SHORT).show();
                        NetworkService.getInstance()
                                .getJSONApi()
                                .getStandings()
                                .enqueue(new Callback<Teams>() {
                                    @Override
                                    public void onResponse(@NonNull Call<Teams> call, @NonNull Response<Teams> response) {
                                        teams = response.body();
                                        mTableAdapter.setAllItems(createColumnHeaderModelList(), createRowHeaderListCentral(teams), createCellListCentral(teams));
                                    }
                                    @Override
                                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                                        System.out.println("Error occurred while getting request!");
                                        t.printStackTrace();
                                    }
                                });
                        break;

                    case 2:
                        Toast.makeText(root.getContext(), "East", Toast.LENGTH_SHORT).show();
                        NetworkService.getInstance()
                                .getJSONApi()
                                .getStandings()
                                .enqueue(new Callback<Teams>() {
                                    @Override
                                    public void onResponse(@NonNull Call<Teams> call, @NonNull Response<Teams> response) {
                                        teams = response.body();
                                        mTableAdapter.setAllItems(createColumnHeaderModelList(), createRowHeaderListEast(teams), createCellListEast(teams));
                                    }
                                    @Override
                                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                                        System.out.println("Error occurred while getting request!");
                                        t.printStackTrace();
                                    }
                                });
                        break;
                    case 3:
                        Toast.makeText(root.getContext(), "North", Toast.LENGTH_SHORT).show();
                        NetworkService.getInstance()
                                .getJSONApi()
                                .getStandings()
                                .enqueue(new Callback<Teams>() {
                                    @Override
                                    public void onResponse(@NonNull Call<Teams> call, @NonNull Response<Teams> response) {
                                        teams = response.body();
                                        mTableAdapter.setAllItems(createColumnHeaderModelList(), createRowHeaderListNorth(teams), createCellListNorth(teams));
                                    }
                                    @Override
                                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                                        System.out.println("Error occurred while getting request!");
                                        t.printStackTrace();
                                    }
                                });
                        break;
                    case 4:
                        Toast.makeText(root.getContext(), "League", Toast.LENGTH_SHORT).show();
                        getTableData();
                        break;
                    case 5:
                        getTableData();
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
        list.add(new ColumnHeader("League rank"));
        list.add(new ColumnHeader("Record"));
        list.add(new ColumnHeader("Wins"));
        list.add(new ColumnHeader("GS/GA"));
        list.add(new ColumnHeader("Points"));
        list.add(new ColumnHeader("GP"));
        list.add(new ColumnHeader("Streak"));
        list.add(new ColumnHeader("DR"));
        list.add(new ColumnHeader("CR"));
        list.add(new ColumnHeader("WCR"));
        list.add(new ColumnHeader("PPR"));

        return list;
    }

    private List<RowHeader> createRowHeaderList(Teams teams) {

        List<RowHeader> list = new ArrayList<>();

        for (Record team : teams.getRecords()) {
            List<TeamRecord> teamRecords = team.getTeamRecords();
            for (int i = 0, teamRecordsSize = teamRecords.size(); i < teamRecordsSize; i++) {
                TeamRecord t = teamRecords.get(i);

                // The order should be same with column header list;
                list.add(new RowHeader(String.valueOf(i),t.getTeam().getName()));
            }
        }
        return list;
    }

    private List<RowHeader> createRowHeaderListWest(Teams teams) {

        List<RowHeader> list = new ArrayList<>();

        List<Record> records = teams.getRecords();
        for (int j = 0, recordsSize = 1; j < recordsSize; j++) {
            Record team = records.get(j);
            List<TeamRecord> teamRecords = team.getTeamRecords();
            for (int i = 0, teamRecordsSize = teamRecords.size(); i < teamRecordsSize; i++) {
                TeamRecord t = teamRecords.get(i);

                // The order should be same with column header list;
                list.add(new RowHeader(String.valueOf(i), t.getTeam().getName()));
            }
        }
        return list;
    }
    private List<RowHeader> createRowHeaderListCentral(Teams teams) {

        List<RowHeader> list = new ArrayList<>();

        List<Record> records = teams.getRecords();
        for (int j = 1, recordsSize = 2; j < recordsSize; j++) {
            Record team = records.get(j);
            List<TeamRecord> teamRecords = team.getTeamRecords();
            for (int i = 0, teamRecordsSize = teamRecords.size(); i < teamRecordsSize; i++) {
                TeamRecord t = teamRecords.get(i);

                // The order should be same with column header list;
                list.add(new RowHeader(String.valueOf(i), t.getTeam().getName()));
            }
        }
        return list;
    }
    private List<RowHeader> createRowHeaderListEast(Teams teams) {

        List<RowHeader> list = new ArrayList<>();

        List<Record> records = teams.getRecords();
        for (int j = 2, recordsSize = 3; j < recordsSize; j++) {
            Record team = records.get(j);
            List<TeamRecord> teamRecords = team.getTeamRecords();
            for (int i = 0, teamRecordsSize = teamRecords.size(); i < teamRecordsSize; i++) {
                TeamRecord t = teamRecords.get(i);

                // The order should be same with column header list;
                list.add(new RowHeader(String.valueOf(i), t.getTeam().getName()));
            }
        }
        return list;
    }
    private List<RowHeader> createRowHeaderListNorth(Teams teams) {

        List<RowHeader> list = new ArrayList<>();

        List<Record> records = teams.getRecords();
        for (int j = 3, recordsSize = 4; j < recordsSize; j++) {
            Record team = records.get(j);
            List<TeamRecord> teamRecords = team.getTeamRecords();
            for (int i = 0, teamRecordsSize = teamRecords.size(); i < teamRecordsSize; i++) {
                TeamRecord t = teamRecords.get(i);

                // The order should be same with column header list;
                list.add(new RowHeader(String.valueOf(i), t.getTeam().getName()));
            }
        }
        return list;
    }


    private List<List<Cell>> createCellList(Teams teams) {
        List<List<Cell>> lists = new ArrayList<>();

        // Creating cell model list from User list for Cell Items
        // In this example, User list is populated from web service
        List<Record> records = teams.getRecords();
        for (int j = 0; j < records.size(); j++) {
            Record team = records.get(j);
            List<TeamRecord> teamRecords = team.getTeamRecords();
            for (int i = 0, teamRecordsSize = teamRecords.size(); i < teamRecordsSize; i++) {
                TeamRecord t = teamRecords.get(i);
                // The order should be same with column header list;
                List<Cell> listBuf= new ArrayList<>();

                listBuf.add(new Cell(t.getLeagueRank(),  Integer.parseInt(t.getLeagueRank()))); // "UpdatedAt"
                listBuf.add(new Cell(t.getLeagueRecord().getWins() + "-" + t.getLeagueRecord().getLosses() + "-" + t.getLeagueRecord().getOt(), String.valueOf(t.getLeagueRecord().getWins())));    // "Nickname"
                listBuf.add(new Cell(t.getRegulationWins() + "", String.valueOf(t.getRegulationWins())));       // "Email"
                listBuf.add(new Cell(t.getGoalsScored() + "/" + t.getGoalsAgainst(), String.valueOf(t.getGoalsScored())));   // "BirthDay"
                listBuf.add(new Cell(t.getPoints() + "", Integer.parseInt(t.getPoints()+"")));      // "Gender"
                listBuf.add(new Cell(t.getGamesPlayed() + "", String.valueOf(t.getGamesPlayed())));         // "Age"
                if (t.getStreak() != null) {
                    listBuf.add(new Cell(t.getStreak().getStreakCode(), String.valueOf(t.getStreak())));
                } else {
                    listBuf.add(new Cell("-", "-"));
                }
                listBuf.add(new Cell(t.getDivisionRank(), Integer.parseInt(t.getDivisionRank())));      // "Salary"
                listBuf.add(new Cell(t.getConferenceRank(),Integer.parseInt(t.getConferenceRank()))); // "CreatedAt"
                listBuf.add(new Cell(t.getWildCardRank(), Integer.parseInt(t.getWildCardRank())));    // "Address"
                listBuf.add(new Cell(t.getPpLeagueRank(), Integer.parseInt(t.getPpLeagueRank())));    // "Address"

                lists.add(listBuf);

                // Add
            }
        }

        return lists;
    }

    private List<List<Cell>> createCellListWest(Teams teams) {
        List<List<Cell>> lists = new ArrayList<>();

        // Creating cell model list from User list for Cell Items
        // In this example, User list is populated from web service
        List<Record> records = teams.getRecords();
        for (int j = 0; j < 1; j++) {
            Record team = records.get(j);
            List<TeamRecord> teamRecords = team.getTeamRecords();
            for (int i = 0, teamRecordsSize = teamRecords.size(); i < teamRecordsSize; i++) {
                TeamRecord t = teamRecords.get(i);
                // The order should be same with column header list;
                List<Cell> listBuf= new ArrayList<>();

                listBuf.add(new Cell(t.getLeagueRank(),  Integer.parseInt(t.getLeagueRank())));
                listBuf.add(new Cell(t.getLeagueRecord().getWins() + "-" + t.getLeagueRecord().getLosses() + "-" + t.getLeagueRecord().getOt(), String.valueOf(t.getLeagueRecord().getWins())));
                listBuf.add(new Cell(t.getRegulationWins() + "", String.valueOf(t.getRegulationWins())));
                listBuf.add(new Cell(t.getGoalsScored() + "/" + t.getGoalsAgainst(), String.valueOf(t.getGoalsScored())));
                listBuf.add(new Cell(t.getPoints() + "", Integer.parseInt(t.getPoints()+"")));
                listBuf.add(new Cell(t.getGamesPlayed() + "", String.valueOf(t.getGamesPlayed())));

                if (t.getStreak() != null) {
                    listBuf.add(new Cell(t.getStreak().getStreakCode(), String.valueOf(t.getStreak())));
                } else {
                    listBuf.add(new Cell("-", "-"));
                }
                listBuf.add(new Cell(t.getDivisionRank(), Integer.parseInt(t.getDivisionRank())));
                listBuf.add(new Cell(t.getConferenceRank(),Integer.parseInt(t.getConferenceRank())));
                listBuf.add(new Cell(t.getWildCardRank(), Integer.parseInt(t.getWildCardRank())));
                listBuf.add(new Cell(t.getPpLeagueRank(), Integer.parseInt(t.getPpLeagueRank())));

                lists.add(listBuf);

                // Add
            }
        }

        return lists;
    }
    private List<List<Cell>> createCellListCentral(Teams teams) {
        List<List<Cell>> lists = new ArrayList<>();

        // Creating cell model list from User list for Cell Items
        // In this example, User list is populated from web service
        List<Record> records = teams.getRecords();
        for (int j = 1; j < 2; j++) {
            Record team = records.get(j);
            List<TeamRecord> teamRecords = team.getTeamRecords();
            for (int i = 0, teamRecordsSize = teamRecords.size(); i < teamRecordsSize; i++) {
                TeamRecord t = teamRecords.get(i);
                // The order should be same with column header list;
                List<Cell> listBuf= new ArrayList<>();

                listBuf.add(new Cell(t.getLeagueRank(),  Integer.parseInt(t.getLeagueRank()))); // "UpdatedAt"
                listBuf.add(new Cell(t.getLeagueRecord().getWins() + "-" + t.getLeagueRecord().getLosses() + "-" + t.getLeagueRecord().getOt(), String.valueOf(t.getLeagueRecord().getWins())));    // "Nickname"
                listBuf.add(new Cell(t.getRegulationWins() + "", String.valueOf(t.getRegulationWins())));       // "Email"
                listBuf.add(new Cell(t.getGoalsScored() + "/" + t.getGoalsAgainst(), String.valueOf(t.getGoalsScored())));   // "BirthDay"
                listBuf.add(new Cell(t.getPoints() + "", Integer.parseInt(t.getPoints()+"")));      // "Gender"
                listBuf.add(new Cell(t.getGamesPlayed() + "", String.valueOf(t.getGamesPlayed())));         // "Age"
                if (t.getStreak() != null) {
                    listBuf.add(new Cell(t.getStreak().getStreakCode(), String.valueOf(t.getStreak())));
                } else {
                    listBuf.add(new Cell("-", "-"));
                }                listBuf.add(new Cell(t.getDivisionRank(), Integer.parseInt(t.getDivisionRank())));      // "Salary"
                listBuf.add(new Cell(t.getConferenceRank(),Integer.parseInt(t.getConferenceRank()))); // "CreatedAt"
                listBuf.add(new Cell(t.getWildCardRank(), Integer.parseInt(t.getWildCardRank())));    // "Address"
                listBuf.add(new Cell(t.getPpLeagueRank(), Integer.parseInt(t.getPpLeagueRank())));    // "Address"

                lists.add(listBuf);

                // Add
            }
        }

        return lists;
    }
    private List<List<Cell>> createCellListEast(Teams teams) {
        List<List<Cell>> lists = new ArrayList<>();

        // Creating cell model list from User list for Cell Items
        // In this example, User list is populated from web service
        List<Record> records = teams.getRecords();
        for (int j = 2; j < 3; j++) {
            Record team = records.get(j);
            List<TeamRecord> teamRecords = team.getTeamRecords();
            for (int i = 0, teamRecordsSize = teamRecords.size(); i < teamRecordsSize; i++) {
                TeamRecord t = teamRecords.get(i);
                // The order should be same with column header list;
                List<Cell> listBuf= new ArrayList<>();

                listBuf.add(new Cell(t.getLeagueRank(),  Integer.parseInt(t.getLeagueRank()))); // "UpdatedAt"
                listBuf.add(new Cell(t.getLeagueRecord().getWins() + "-" + t.getLeagueRecord().getLosses() + "-" + t.getLeagueRecord().getOt(), String.valueOf(t.getLeagueRecord().getWins())));    // "Nickname"
                listBuf.add(new Cell(t.getRegulationWins() + "", String.valueOf(t.getRegulationWins())));       // "Email"
                listBuf.add(new Cell(t.getGoalsScored() + "/" + t.getGoalsAgainst(), String.valueOf(t.getGoalsScored())));   // "BirthDay"
                listBuf.add(new Cell(t.getPoints() + "", Integer.parseInt(t.getPoints()+"")));      // "Gender"
                listBuf.add(new Cell(t.getGamesPlayed() + "", String.valueOf(t.getGamesPlayed())));         // "Age"
                if (t.getStreak() != null) {
                    listBuf.add(new Cell(t.getStreak().getStreakCode(), String.valueOf(t.getStreak())));
                } else {
                    listBuf.add(new Cell("-", "-"));
                }                listBuf.add(new Cell(t.getDivisionRank(), Integer.parseInt(t.getDivisionRank())));      // "Salary"
                listBuf.add(new Cell(t.getConferenceRank(),Integer.parseInt(t.getConferenceRank()))); // "CreatedAt"
                listBuf.add(new Cell(t.getWildCardRank(), Integer.parseInt(t.getWildCardRank())));    // "Address"
                listBuf.add(new Cell(t.getPpLeagueRank(), Integer.parseInt(t.getPpLeagueRank())));    // "Address"

                lists.add(listBuf);

                // Add
            }
        }

        return lists;
    }
    private List<List<Cell>> createCellListNorth(Teams teams) {
        List<List<Cell>> lists = new ArrayList<>();

        // Creating cell model list from User list for Cell Items
        // In this example, User list is populated from web service
        List<Record> records = teams.getRecords();
        for (int j = 3; j < 4; j++) {
            Record team = records.get(j);
            List<TeamRecord> teamRecords = team.getTeamRecords();
            for (int i = 0, teamRecordsSize = teamRecords.size(); i < teamRecordsSize; i++) {
                TeamRecord t = teamRecords.get(i);
                // The order should be same with column header list;
                List<Cell> listBuf= new ArrayList<>();

                listBuf.add(new Cell(t.getLeagueRank(),  Integer.parseInt(t.getLeagueRank()))); // "UpdatedAt"
                listBuf.add(new Cell(t.getLeagueRecord().getWins() + "-" + t.getLeagueRecord().getLosses() + "-" + t.getLeagueRecord().getOt(), String.valueOf(t.getLeagueRecord().getWins())));    // "Nickname"
                listBuf.add(new Cell(t.getRegulationWins() + "", String.valueOf(t.getRegulationWins())));       // "Email"
                listBuf.add(new Cell(t.getGoalsScored() + "/" + t.getGoalsAgainst(), String.valueOf(t.getGoalsScored())));   // "BirthDay"
                listBuf.add(new Cell(t.getPoints() + "", Integer.parseInt(t.getPoints()+"")));      // "Gender"
                listBuf.add(new Cell(t.getGamesPlayed() + "", String.valueOf(t.getGamesPlayed())));         // "Age"
                if (t.getStreak() != null) {
                    listBuf.add(new Cell(t.getStreak().getStreakCode(), String.valueOf(t.getStreak())));
                } else {
                    listBuf.add(new Cell("-", "-"));
                }                listBuf.add(new Cell(t.getDivisionRank(), Integer.parseInt(t.getDivisionRank())));      // "Salary"
                listBuf.add(new Cell(t.getConferenceRank(),Integer.parseInt(t.getConferenceRank()))); // "CreatedAt"
                listBuf.add(new Cell(t.getWildCardRank(), Integer.parseInt(t.getWildCardRank())));    // "Address"
                listBuf.add(new Cell(t.getPpLeagueRank(), Integer.parseInt(t.getPpLeagueRank())));    // "Address"

                lists.add(listBuf);

                // Add
            }
        }

        return lists;
    }


    public void getTableData() {
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
    }

}