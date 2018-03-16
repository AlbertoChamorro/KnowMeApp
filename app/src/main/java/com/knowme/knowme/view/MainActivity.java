package com.knowme.knowme.view;

import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.knowme.knowme.R;
import com.knowme.knowme.post.view.HomeFragment;
import com.knowme.knowme.view.fragment.ProfileFragment;
import com.knowme.knowme.view.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomBar = (BottomNavigationView) findViewById(R.id.navigation);
        this.initBottomNavigation(bottomBar);
        bottomBar.setSelectedItemId(R.id.menu_home);
    }

    public void initBottomNavigation(BottomNavigationView view) {
        view.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_home:
                                    item.setChecked(true);
                                    showFragment(new HomeFragment(), R.id.tab_container_fragment);
                                break;
                            case R.id.menu_search:
                                    item.setChecked(true);
                                    showFragment(new SearchFragment(), R.id.tab_container_fragment);
                                break;
                            case R.id.menu_profile:
                                    item.setChecked(true);
                                    showFragment(new ProfileFragment(), R.id.tab_container_fragment);
                                break;
                        }
                        return false;
                    }
                });
    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        }
//        if (!viewIsAtHome) { //if the current view is not the News fragment
//            displayView(R.id.nav_news); //display the News fragment
//        } else {
//            moveTaskToBack(true);  //If view is in News fragment, exit application
//        }
    }

    public <T> void showFragment(T fragment, int containerId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, (Fragment) fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }
}
