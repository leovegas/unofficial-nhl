package com.app.unofficial_nhl.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.tabs.Today;
import com.app.unofficial_nhl.tabs.Tomorrow;
import com.app.unofficial_nhl.tabs.Yesteday;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    private String[] titles = new String[]{"Yesteday", "Today", "Tomorrow"};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =  ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        ViewPager2 vp = root.findViewById(R.id.view_pager);
        TabLayout tl = root.findViewById(R.id.tab_layout);

        vp.setUserInputEnabled(false);



        vp.setAdapter(new ViewPagerFragmentAdapter(this));

        // attaching tab mediator
        new TabLayoutMediator(tl, vp,
                (tab, position) -> tab.setText(titles[position])).attach();

        tl.getTabAt(1).select();


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
                    return new Yesteday();
                case 1:
                    return new Today();
                case 2:
                    return new Tomorrow();
            }
            return null;
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }

}