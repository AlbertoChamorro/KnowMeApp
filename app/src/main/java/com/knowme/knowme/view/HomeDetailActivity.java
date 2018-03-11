package com.knowme.knowme.view;

import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;

import com.knowme.knowme.R;

public class HomeDetailActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);
        this.showToolbar("Detalle de Home", true);
        if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Fade());
        }
    }

    public void showToolbar(String title, Boolean showBackButton) {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showBackButton);

        CollapsingToolbarLayout collapsing = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        finish();
//        return super.onOptionsItemSelected(item);
//    }
}
