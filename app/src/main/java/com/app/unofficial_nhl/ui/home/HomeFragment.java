package com.app.unofficial_nhl.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.tabs.*;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    private String[] titles;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =  ViewModelProviders.of(this).get(HomeViewModel.class);
        titles = new String[]{"-3 day", "-2 day", "      Yesterday       ", "       Today       ", "       Tomorrow       ", "+2 day", "+3 day"};

        root = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager2 vp = root.findViewById(R.id.view_pager);
        TabLayout tl = root.findViewById(R.id.tab_layout);



        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //do stuff here
                Objects.requireNonNull(tl.getTabAt(tab.getPosition())).select();
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tl.getTabAt(0).view.performClick();
        vp.setCurrentItem(0);
        vp.setUserInputEnabled(false);

        vp.setAdapter(new ViewPagerFragmentAdapter(this));

        tl.setTabMode(TabLayout.MODE_SCROLLABLE);
        tl.setTabGravity(TabLayout.GRAVITY_CENTER);
/*
       new TabLayoutMediator(tl, vp,
                (tab, position) -> tab.setText(titles[position])).attach();*/



        return root;


    }
    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new minusThree(86400000*3);
                case 1:
                    return new minusTwo(86400000*2);
                case 2:
                    return new Yesterday(86400000);
                case 3:
                    return new Today();
                case 4:
                    return new Tomorrow(86400000);
                case 5:
                    return new plusTwo(86400000*2);
                case 6:
                    return new plusThree(86400000*3);


                default: return null;
            }

        }

        @Override
        public int getItemCount() {
            return 7;
        }
    }

}