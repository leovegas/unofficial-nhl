package com.app.unofficial_nhl.team_tab;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import org.jetbrains.annotations.NotNull;

public class TabLayoutAdapter extends FragmentStateAdapter {

    private String[] titles = new String[]{"Stats", "Players"};

    public TabLayoutAdapter(FragmentActivity fragment) {
        super(fragment);
    }


    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TeamStatsFragment();
            case 1:
                return new PlayersFragment();
            default:
                return new TeamStatsFragment();

        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}

