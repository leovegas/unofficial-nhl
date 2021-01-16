package com.app.unofficial_nhl;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import com.app.unofficial_nhl.pojos.Record;
import com.app.unofficial_nhl.pojos.TeamRecord;
import com.app.unofficial_nhl.pojos.Teams;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        NetworkService.getInstance()
                .getJSONApi()
                .getPlayerInfoById(8477474)
                .enqueue(new Callback<Teams>() {
                    @Override
                    public void onResponse(@NonNull Call<Teams> call, @NonNull  Response<Teams> response) {
                        Teams people = response.body();

                        System.out.println(people.getPeople().get(0).getFullName());
                        System.out.println(people.getPeople().get(0).getBirthCity());
                        System.out.println(people.getPeople().get(0).getBirthCountry());
                        System.out.println(people.getPeople().get(0).getLink());

                    }

                    @Override
                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }

                });

        NetworkService.getInstance()
                .getJSONApi()
                .getStandings()
                .enqueue(new Callback<Teams>() {
                    @Override
                    public void onResponse(@NonNull Call<Teams> call, @NonNull  Response<Teams> response) {
                        Map<Integer,String> standings = new TreeMap<>();
                        Teams teams = response.body();
                        for (Record team : teams.getRecords()) {
                            for (TeamRecord t : team.getTeamRecords()) {
                                standings.put(Integer.valueOf(t.getLeagueRank()), t.getTeam().getName());
                            }
                        }
                        System.out.println(standings);

                    }

                    @Override
                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }

                });

        final ListView listview = (ListView) findViewById(R.id.listview);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        final ArrayList<String> list = new ArrayList<String>(Arrays.asList(values));
        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.closest_games_list,
               R.id.textView5, list);
        listview.setAdapter(adapter);

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