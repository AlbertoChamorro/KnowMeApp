package com.knowme.knowme.post.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.knowme.knowme.R;
import com.knowme.knowme.util.Helper;

public class NewPostActivity extends AppCompatActivity {

    private ImageView mImageViewDetailHeader;
    private TextView titleToolbar;
    private ImageView imageLeftToolbar;
    private ImageView imageRightToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        this.setupToolbar();

        this.mImageViewDetailHeader = (ImageView) findViewById(R.id.image_view_detail_header);

        if (this.getIntent().getExtras() != null) {
            String photoPath = getIntent().getExtras().getString("PHOTO_PATH_TEMP");
            Helper.LoadImage(this, photoPath, mImageViewDetailHeader, null, null);
        }
    }

    private void setupToolbar(){

        this.titleToolbar = (TextView) findViewById(R.id.title_toolbar);
        this.titleToolbar.setText(getResources().getString(R.string.text_create_account));

        this.imageLeftToolbar = (ImageView) findViewById(R.id.left_image_toolbar);

        this.imageLeftToolbar.setBackgroundResource(R.drawable.ic_action_arrow_left);

        this.imageLeftToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        this.imageRightToolbar = (ImageView) findViewById(R.id.right_image_toolbar);
        this.imageRightToolbar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
