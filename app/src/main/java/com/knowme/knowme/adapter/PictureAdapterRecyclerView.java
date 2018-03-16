package com.knowme.knowme.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.knowme.knowme.R;
import com.knowme.knowme.model.Picture;
import com.knowme.knowme.util.Helper;
import com.knowme.knowme.view.HomeDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by BETO on 27/01/2018.
 */

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder> {

    private ArrayList<Picture> pictures;
    private int resource;
    private Activity activity;

    public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resource, Activity activity) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        Picture picture = this.pictures.get(position);
        holder.userName.setText(picture.getUserName());
        holder.timeCard.setText(picture.getTime());
        holder.likeNumberCard.setText(picture.getLikeNumber());
        Helper.LoadImage(this.activity, picture.getPictureUrl() ,holder.pictureCard, null, null);
        // set on click
        holder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, HomeDetailActivity.class);
               if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP) {

                   Explode explode = new Explode();
                   explode.setDuration(1000);
                   activity.getWindow().setExitTransition(explode);
                   activity.startActivity(intent,
                                            ActivityOptionsCompat
                                                        .makeSceneTransitionAnimation(activity,
                                                                 view,
                                                                activity.getString(R.string.transition_fade)).toBundle());
               }else {
                    activity.startActivity(intent);
               }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.pictures.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder {

        private ImageView pictureCard;
        private TextView  userName;
        private TextView  timeCard;
        private TextView  likeNumberCard;

        public PictureViewHolder(View itemView) {
            super(itemView);
            this.pictureCard    = (ImageView) itemView.findViewById(R.id.pictureCard);
            this.userName       = (TextView) itemView.findViewById(R.id.userNameCard);
            this.timeCard       = (TextView) itemView.findViewById(R.id.relativeTimeCard);
            this.likeNumberCard = (TextView) itemView.findViewById(R.id.descriptionLikeCard);
        }
    }

}
