package com.app.unofficial_nhl;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.app.unofficial_nhl.pojos.Record;
import com.app.unofficial_nhl.pojos.TeamRecord;
import com.app.unofficial_nhl.pojos.Teams;
import androidx.appcompat.app.AppCompatActivity;
import com.app.unofficial_nhl.tabs.Today;
import com.app.unofficial_nhl.tabs.Tomorrow;
import com.app.unofficial_nhl.tabs.Yesteday;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private String[] titles = new String[]{"Yesteday", "Today", "Tomorrow"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null)
        {
            actionBar.hide();

        }
        ViewPager2 vp = findViewById(R.id.view_pager);
        TabLayout tl = findViewById(R.id.tab_layout);

        vp.setAdapter(new ViewPagerFragmentAdapter(this));

        // attaching tab mediator
        new TabLayoutMediator(tl, vp,
                (tab, position) -> tab.setText(titles[position])).attach();

    }


    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new Yesteday();
                case 1:
                    return new Today();
                case 2:
                    return new Tomorrow();
            }
            return new Yesteday();
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }

}