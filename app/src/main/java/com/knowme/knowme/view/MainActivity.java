package com.knowme.knowme.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.knowme.knowme.R;
import com.knowme.knowme.post.view.HomeFragment;
import com.knowme.knowme.util.Helper;
import com.knowme.knowme.util.interfaces.IAlertDialog;
import com.knowme.knowme.view.fragment.ProfileFragment;
import com.knowme.knowme.view.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private IAlertDialog iAlertDialog = new IAlertDialog() {
        @Override
        public void okAction(DialogInterface dialog, int which) {
            finish();
        }

        @Override
        public void cancelAction(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    };

    private int selectedTab = R.id.menu_home;
    private Boolean firstRun = true;

    // var bottom navigation view
    private BottomNavigationView bottomBar;

    // vars for navigation drawer
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    // vars for custom toolbar
    private Toolbar toolbar;
    private TextView titleToolbar;
    private ImageView imageLeftToolbar;
    private ImageView imageRightToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup bottom navigation view
        this.setupBottomNavigationView();

        // toolbar
        this.setupToolbar();

        // navigation drawer setup
        this.setupNavigationDrawer();
    }

    private void setupNavigationDrawer() {
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.setupDrawerNavigationView(mDrawerLayout);
    }

    private void setupDrawerNavigationView(DrawerLayout mDrawerLayout) {
        this.mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        this.mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });

        this.setupBiometricsAuth(this);
    }

    private void setupBiometricsAuth(final AppCompatActivity context) {
        Menu menu = this.mNavigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.biometric_auth);
        View actionView = MenuItemCompat.getActionView(menuItem);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.showToast(context, "Auth biometrics...", Toast.LENGTH_LONG);
            }
        });
    }

    private void selectDrawerItem(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.locate_us:
                Helper.showToast(this, "Function not implement, now in development", Toast.LENGTH_SHORT);
                break;
            case R.id.settings_account:
                Helper.showToast(this, "Function not implement, soon", Toast.LENGTH_SHORT);
                break;
            case R.id.profile_account:
                    Helper.showToast(this, "Function not implement", Toast.LENGTH_SHORT);
                break;
            default: break;
        }

        item.setChecked(true);
        this.mDrawerLayout.closeDrawers();
    }

    public void setupBottomNavigationView() {

        this.bottomBar = (BottomNavigationView) findViewById(R.id.navigation);
        this.bottomBar.setOnNavigationItemSelectedListener(
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
        bottomBar.setSelectedItemId(selectedTab);
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
        if(this.mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            this.mDrawerLayout.closeDrawers();
            return;
        }

        this.builderDialog();
    }

    private void setupToolbar(){

        this.titleToolbar = (TextView) findViewById(R.id.title_toolbar);
        this.titleToolbar.setText(getResources().getString(R.string.app_name));

        this.imageLeftToolbar = (ImageView) findViewById(R.id.left_image_toolbar);
        this.imageLeftToolbar.setBackgroundResource(R.drawable.ic_action_menu);
        this.imageLeftToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        this.imageRightToolbar = (ImageView) findViewById(R.id.right_image_toolbar);
        this.imageRightToolbar.setBackgroundResource(R.drawable.ic_action_logout);
        this.imageRightToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builderDialog();
            }
        });
    }

    private void builderDialog(){
        AlertDialog.Builder builderDialog = Helper.setupDialog(this, "Mensaje", "¿Desea cerrar sesión?", null, null);
        Helper.builOptionButtonDialog(builderDialog, "Aceptar",
                true, iAlertDialog);
        Helper.builOptionButtonDialog(builderDialog, "Cancelar",
                false, iAlertDialog);
        AlertDialog dialog = Helper.createDialog(builderDialog);
        Helper.customOptionButtonColor(dialog, this, R.color.colorPrimary, true);
        Helper.customOptionButtonColor(dialog, this, R.color.colorRed, false);
    }

}
