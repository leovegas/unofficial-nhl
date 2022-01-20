package com.app.unofficial_nhl;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.app.unofficial_nhl.ui.home.HomeViewModel;
import com.app.unofficial_nhl.ui.notifications.NotificationsViewModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    PieChart pieChart;
    PieData pieData;
    List<PieEntry> pieEntryList = new ArrayList<>();
    HomeViewModel homeViewModel;
    MotionLayout motionLayout;
    NotificationsViewModel notificationsViewModel;

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        ViewGroup v = (ViewGroup) LayoutInflater.from(this)
                .inflate(R.layout.custom_actionbar, null);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM,
                ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(v,
                new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        Gravity.CENTER_VERTICAL | Gravity.RIGHT));

    }

    @Override
    public void onBackPressed() {
        if(motionLayout.isInteractionEnabled()) motionLayout.transitionToStart();
        else {
            new AlertDialog.Builder(this)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            MainActivity2.super.onBackPressed();
                        }
                    }).create().show();
        }

    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            BottomNavigationView bottomNav = findViewById(R.id.nav_view);
            NavigationUI.setupWithNavController(bottomNav, navController);

            System.out.println("max items +" + bottomNav.getMaxItemCount());
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_favorites)
                    .build();


            ActionBar actionBar = getSupportActionBar();

            if (actionBar != null) {
                setupActionBar();
                actionBar.hide();

            }
            motionLayout = findViewById(R.id.container);
            motionLayout.getTransition(R.id.baseTransition).setEnable(false);

            notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
            LiveData<String> data1 = notificationsViewModel.getText();

            data1.observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    System.out.println(s+" changed");
                    if (s.equals("block"))
                    {
                        System.out.println("block2");
                        motionLayout.getTransition(R.id.baseTransition).setEnable(false);

                    }
                }
            });
            homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
            LiveData<String> data2 = homeViewModel.getText();

            data2.observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    System.out.println(s+" changed");
                    motionLayout.getTransition(R.id.baseTransition).setEnable(true);
                    motionLayout.transitionToEnd();
                }
            });



            //-----------
            pieChart = findViewById(R.id.pieChart);
            pieChart.animateY(6000, Easing.EaseInOutQuad);
            Legend l = pieChart.getLegend();
            l.setEnabled(false);
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setOrientation(Legend.LegendOrientation.VERTICAL);
            l.setDrawInside(false);
            l.setXEntrySpace(7f);
            l.setYEntrySpace(0f);
            l.setYOffset(0f);

            pieChart.setUsePercentValues(true);
            pieChart.setRotationEnabled(false);
            pieChart.setDrawHoleEnabled(true);
            pieChart.setHoleRadius(70f);
            pieChart.setHoleColor(Color.TRANSPARENT);
            pieChart.setEntryLabelColor(Color.TRANSPARENT);
            Description description = new Description();
            description.setText("");
            pieChart.setDescription(description);
            pieEntryList.add(new PieEntry(50,""));
            pieEntryList.add(new PieEntry(50,""));
            PieDataSet pieDataSet = new PieDataSet(pieEntryList,"");
            pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
            pieData = new PieData(pieDataSet);
            pieChart.setData(pieData);
            pieChart.invalidate();

            //-----------
        }


}