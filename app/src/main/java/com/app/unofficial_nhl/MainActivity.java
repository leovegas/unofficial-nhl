package com.app.unofficial_nhl;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.app.unofficial_nhl.pojos.Teams;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.CharArrayWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

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
                        teams.getRecords().forEach(team -> {
                            team.getTeamRecords().forEach(t -> {
                                standings.put(Integer.valueOf(t.getLeagueRank()),t.getTeam().getName());
                            });
                        });
                        System.out.println(standings);

                    }

                    @Override
                    public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }

                });
    }

}