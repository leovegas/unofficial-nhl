package com.app.unofficial_nhl;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.app.unofficial_nhl.helper_classes.ListRow;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.pojos.Game;
import com.app.unofficial_nhl.pojos.Teams;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    String teamHome = "";
    String teamAway = "";
    String detailedState = "";
    String venueName = "";
    String gameTime = "";
    String gameDate = "";
    String homeScore = "";
    String awayScore = "";
    ArrayList<ListRow> alldata = new ArrayList<ListRow>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkService.getInstance()
                .getJSONApi()
                .getSheduledGamesByDate2("2021-03-23")
                .subscribeOn(Schedulers.io())
                .flatMapIterable(teams -> teams.getDates().get(0).getGames())
                .toList()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3)
                .subscribe(new DisposableObserver<List<Game>>() {

                    @Override
                    public void onNext(List<Game> gamesByDate) {

                        Log.i("reading", "inTommorow");
                        for (Game game : gamesByDate) {
                            Teams teams = game.getTeams();
                            teamHome = teams.getHome().getTeam().getName();
                            teamAway = teams.getAway().getTeam().getName();
                            detailedState = game.getStatus().getDetailedState();
                            venueName = game.getVenue().getName();

                            homeScore = String.valueOf(game.getTeams().getHome().getScore());
                            awayScore = String.valueOf(game.getTeams().getAway().getScore());


                            ListRow listRow = new ListRow(teamHome, teamAway, venueName, gameTime, gameDate, detailedState, awayScore, homeScore, null, null);
                            alldata.add(listRow);

                        }

                        System.out.println("ready");

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}