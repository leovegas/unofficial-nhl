package com.app.unofficial_nhl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.*;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.allyants.notifyme.NotifyMe;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.pojos.Record;
import com.app.unofficial_nhl.pojos.Team;
import com.app.unofficial_nhl.pojos.TeamRecord;
import com.app.unofficial_nhl.pojos.Teams;
import com.app.unofficial_nhl.team_tab.TabLayoutAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.*;

public class TeamInfoActivity extends AppCompatActivity {

    private String[] titles = new String[]{"stats", "roster"};
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private TextView teamnameView, confAndPos, teamWLO;
    private ImageView teamlogoView;
    Display display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);
        if (Build.VERSION.SDK_INT >= 26) {
            display = getDisplay();
        }else display = getWindowManager().getDefaultDisplay();

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            setupActionBar();
            //actionBar.hide();

        }
        teamnameView = findViewById(R.id.teamName);
        teamlogoView = findViewById(R.id.teamLogo);
        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Tab1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab2"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        TabLayoutAdapter adapter=new TabLayoutAdapter(this);
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(titles[position])).attach();
        Objects.requireNonNull(tabLayout.getTabAt(0)).select();

        Intent intent = getIntent();
        String teamname = intent.getStringExtra("TEAMNAME");

        teamnameView.setText(teamname);
        teamlogoView.setImageDrawable(StaticData.resizeImage(StaticData.logosMap.get(teamname),this,display));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition()==0){
                    Drawable myIcon = getApplicationContext().getDrawable( R.drawable.news_list );
                    tabLayout.setForeground(myIcon);
                }else {
                    Drawable myIcon = getApplicationContext().getDrawable( R.drawable.news_list2 );
                    tabLayout.setForeground(myIcon);
                }
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static Date addDays(Date date, int s)
    {
        int now = Calendar.getInstance().get(Calendar.SECOND);
        Calendar cal = Calendar.getInstance();
        date.setTime(now+s);
        cal.setTime(date);
        System.out.println("time!!!!!!!!!!!!"+cal.getTime());
        return cal.getTime();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
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


}