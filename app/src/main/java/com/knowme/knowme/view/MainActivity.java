package com.knowme.knowme.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.knowme.knowme.R;
import com.knowme.knowme.post.view.HomeFragment;
import com.knowme.knowme.util.Helper;
import com.knowme.knowme.view.fragment.ProfileFragment;
import com.knowme.knowme.view.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private int selectedTab = R.id.menu_home;
    private Boolean firstRun = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomBar = (BottomNavigationView) findViewById(R.id.navigation);
        this.setListenerBottomNavigationViewSelected(bottomBar);
        bottomBar.setSelectedItemId(selectedTab);
    }

    public void setListenerBottomNavigationViewSelected(BottomNavigationView view) {
        view.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_home:
                                    selectedTapBottomBottomView(R.id.tab_container_fragment, item, R.id.menu_home, new HomeFragment());
                                break;
                            case R.id.menu_search:
                                    selectedTapBottomBottomView(R.id.tab_container_fragment, item, R.id.menu_search, new SearchFragment());
                                break;
                            case R.id.menu_profile:
                                    selectedTapBottomBottomView(R.id.tab_container_fragment, item, R.id.menu_profile, new ProfileFragment());
                                break;
                        }
                        return false;
                    }
                });
    }

    private <T> void selectedTapBottomBottomView (int containerViewId, MenuItem menuItem, int selectedResourceTapId, T fragmentToShow){

        if (firstRun || selectedTab != menuItem.getItemId()) {
            menuItem.setChecked(true);
            Helper.showFragment(this, fragmentToShow, containerViewId);
            firstRun = false;
            selectedTab = selectedResourceTapId;
        }
    }

    @Override
    public void onBackPressed() {

    }
}
