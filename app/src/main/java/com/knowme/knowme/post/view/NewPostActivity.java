package com.knowme.knowme.post.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.knowme.knowme.R;
import com.knowme.knowme.util.Helper;

public class NewPostActivity extends AppCompatActivity {

    private ImageView mImageViewDetailHeader;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle("Registrar Post");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.mImageViewDetailHeader = (ImageView) findViewById(R.id.image_view_detail_header);

        if (this.getIntent().getExtras() != null) {
            String photoPath = getIntent().getExtras().getString("PHOTO_PATH_TEMP");
            Helper.LoadImage(this, photoPath, mImageViewDetailHeader, null, null);
        }
    }
}
