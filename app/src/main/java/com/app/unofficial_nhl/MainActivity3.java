package com.app.unofficial_nhl;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.app.unofficial_nhl.helper_classes.ListRow;
import com.app.unofficial_nhl.pojos.Game;
import com.app.unofficial_nhl.pojos.Play;
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
    ArrayList<Integer> scoringPlays = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
}