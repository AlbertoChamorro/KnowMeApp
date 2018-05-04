package com.knowme.knowme.active_android.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.knowme.knowme.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActiveAndroidActivity extends AppCompatActivity {

    @BindView(R.id.title_toolbar)
    public TextView titleToolbar;

    @BindView(R.id.left_image_toolbar)
    public ImageView imageLeftToolbar;

    @BindView(R.id.right_image_toolbar)
    public ImageView imageRightToolbar;

    @BindView(R.id.progress_bar_active_android)
    public ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_android);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {

        this.titleToolbar.setText(getResources().getString(R.string.text_active_android));
        this.imageLeftToolbar.setBackgroundResource(R.drawable.ic_action_arrow_left);
        this.imageLeftToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        this.imageRightToolbar.setVisibility(View.INVISIBLE);
        this.toggleProgressBar(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void toggleProgressBar(Boolean state) {

        int stateVisibility = state == true ? View.VISIBLE : View.GONE;
        this.mProgressBar.setVisibility(stateVisibility);
    }
}
