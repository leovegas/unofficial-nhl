package com.app.unofficial_nhl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

    private String[] titles = new String[]{"Stats", "Players"};
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private TextView teamnameView, confAndPos, teamWLO;
    private ImageView teamlogoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);
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
        teamlogoView.setImageDrawable(resizeImage(StaticData.logosMap.get(teamname)));




       /* viewPager.addOnLayoutChangeListener();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

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
        System.out.println("now!!!!" + now);
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

    public Drawable resizeImage(int imageResource) {// R.drawable.large_image
        // Get device dimensions
        Display display = getWindowManager().getDefaultDisplay();
        double deviceWidth = display.getWidth();

        BitmapDrawable bd = (BitmapDrawable) this.getResources().getDrawable(
                imageResource);
        double imageHeight = bd.getBitmap().getHeight();
        double imageWidth = bd.getBitmap().getWidth();

        double ratio = deviceWidth / imageWidth;
        int newImageHeight = (int) (imageHeight * ratio);

        Bitmap bMap = BitmapFactory.decodeResource(getResources(), imageResource);
        Drawable drawable = new BitmapDrawable(this.getResources(),
                getResizedBitmap(bMap, newImageHeight, (int) deviceWidth));

        return drawable;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }

    /************************ Resize Bitmap *********************************/


}