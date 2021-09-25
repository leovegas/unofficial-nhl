package com.app.unofficial_nhl;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.app.unofficial_nhl.ui.favorite.FavoriteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import org.jetbrains.annotations.NotNull;

public class MainActivity2 extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bottomNav = findViewById(R.id.nav_view);

/*            bottomNav.setOnNavigationItemSelectedListener(new
                                                             BottomNavigationView.OnNavigationItemSelectedListener() {
                                                                 @Override
                                                                 public boolean onNavigationItemSelected(@NonNull MenuItem item)
                                                                 {
                                                                     switch (item.getItemId())
                                                                     {
                                                                         case R.id.navigation_home:
                                                                             return NavigationUI.onNavDestinationSelected(item, navController);
                                                                         case R.id.navigation_dashboard:
                                                                             return NavigationUI.onNavDestinationSelected(item, navController);
                                                                         case R.id.navigation_notifications:
                                                                             return NavigationUI.onNavDestinationSelected(item, navController);
                                                                         case R.id.navigation_favorites:
                                                                             return NavigationUI.onNavDestinationSelected(item, navController);
                                                                     }

                                                                     return true;
                                                                 }
                                                             });*/

            NavigationUI.setupWithNavController(bottomNav, navController);

      //  BottomNavigationView navView = findViewById(R.id.nav_view);
        System.out.println("max items +"+bottomNav.getMaxItemCount());
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
       AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_favorites)
                .build();
       // NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            setupActionBar();
            actionBar.hide();

        }


    }

}