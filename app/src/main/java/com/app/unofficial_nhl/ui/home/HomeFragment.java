package com.app.unofficial_nhl.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.tabs.Today;
import com.app.unofficial_nhl.tabs.Tomorrow;
import com.app.unofficial_nhl.tabs.Yesterday;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    private String[] titles = new String[]{"Yesterday", "Today", "Tomorrow"};
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =  ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager2 vp = root.findViewById(R.id.view_pager);
        TabLayout tl = root.findViewById(R.id.tab_layout);
        vp.setUserInputEnabled(false);

        vp.setAdapter(new ViewPagerFragmentAdapter(this));

        new TabLayoutMediator(tl, vp,
                (tab, position) -> tab.setText(titles[position])).attach();
        Objects.requireNonNull(tl.getTabAt(1)).select();
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
                    return new Yesterday();
                case 1:
                    return new Today();
                case 2:
                    return new Tomorrow();
                default: return new Today();
            }

        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }

}