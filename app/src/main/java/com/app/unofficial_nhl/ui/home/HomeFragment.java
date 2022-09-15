package com.app.unofficial_nhl.ui.home;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.tabs.*;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Objects;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    private String[] titles;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =  ViewModelProviders.of(this).get(HomeViewModel.class);
        titles = new String[]{"Yesterday", "Today", "Tomorrow", "Calendar"};

        root = inflater.inflate(R.layout.fragment_home, container, false);

        ViewPager2 vp = root.findViewById(R.id.view_pager);
        TabLayout tl = root.findViewById(R.id.tab_layout);


        LinearLayout tabStrip = (LinearLayout) tl.getChildAt(0);
        tabStrip.getChildAt(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar myCalendar = Calendar.getInstance();

                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        long time = Calendar.getInstance().getTime().getTime() - myCalendar.getTime().getTime();
                        vp.setAdapter(new ViewPagerFragmentAdapter2(HomeFragment.this,time));
                        Objects.requireNonNull(tl.getTabAt(3)).select();
                        vp.setCurrentItem(3);
                        tl.getTabAt(3).setTag(true);

                    }

                };
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
/*
            for (int i = 0; i < 3; i++) {
                tabStrip.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tabStrip.getChildAt(3).getTag()!=null){
                            if ((boolean) tabStrip.getChildAt(3).getTag()) {
                                vp.setAdapter(new ViewPagerFragmentAdapter(HomeFragment.this));
                                tabStrip.getChildAt(3).setTag(false);
                            }
                        }

                    }
                });
        }*/

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                System.out.println("TAG "+tab.getTag());
                if (tl.getTabAt(3).getTag()!=null){
                    if ((boolean) tl.getTabAt(3).getTag()) {
                        vp.setAdapter(new ViewPagerFragmentAdapter(HomeFragment.this));
                        tl.getTabAt(3).setTag(false);
                    }
                }
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

        tl.getTabAt(1).select();
        vp.setCurrentItem(1);
        vp.setAdapter(new ViewPagerFragmentAdapter(this));
        tl.setTabMode(TabLayout.MODE_SCROLLABLE);
        tl.setTabGravity(TabLayout.GRAVITY_CENTER);

        new TabLayoutMediator(tl, vp,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(titles[position]);
                    }
                }).attach();




        return root;


    }
    private class ViewPagerFragmentAdapter2 extends FragmentStateAdapter {

        long time;

        public ViewPagerFragmentAdapter2(@NonNull @NotNull Fragment fragment, long time) {
            super(fragment);
            this.time = time;
        }

        public ViewPagerFragmentAdapter2(@NonNull Fragment fragment) {

            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (time>=0) return new Yesterday(time);
            else         return new Tomorrow(-time);
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
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
                    return new Yesterday(86400000);
                case 1:
                    return new Today();
                case 2:
                    return new Tomorrow(86400000);
                case 3:
                    return new Today();


                default: return null;
            }

        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }

}