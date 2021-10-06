package com.app.unofficial_nhl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.app.unofficial_nhl.helper_classes.MyTableAdapter;
import com.app.unofficial_nhl.helper_classes.MyTableViewListener;
import com.app.unofficial_nhl.helper_classes.data_models.Cell;
import com.app.unofficial_nhl.helper_classes.data_models.ColumnHeader;
import com.app.unofficial_nhl.helper_classes.data_models.RowHeader;
import com.app.unofficial_nhl.pojos.Record;
import com.app.unofficial_nhl.pojos.TeamRecord;
import com.app.unofficial_nhl.pojos.Teams;
import com.app.unofficial_nhl.ui.dashboard.DashboardViewModel;
import com.evrencoskun.tableview.TableView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class PlayersActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

    }
}