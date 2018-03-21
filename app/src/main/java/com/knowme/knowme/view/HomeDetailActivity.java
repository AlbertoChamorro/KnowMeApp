package com.knowme.knowme.view;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.MenuItem;

import com.knowme.knowme.R;

public class HomeDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);

        this.showToolbar("Detalle de Home", true);
        this.collapsing = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Fade());
        }
    }

    public void showToolbar(String title, Boolean showBackButton) {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar_detail_home);
        //this.changeDefaultIconLeftToolbar(title, showBackButton);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showBackButton);
    }

    private void changeDefaultIconLeftToolbar(String title, Boolean showBackButton){

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_action_arrow_left);
        //drawable.setBounds(10, 180, 10, 180);
        this.toolbar.setNavigationIcon(drawable);
        setSupportActionBar(this.toolbar);

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showBackButton);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.onBackPressed();
        return true;
    }
}
