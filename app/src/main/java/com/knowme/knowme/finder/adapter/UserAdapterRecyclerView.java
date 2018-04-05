package com.knowme.knowme.finder.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.knowme.knowme.R;
import com.knowme.knowme.model.UserGitHub;
import com.knowme.knowme.util.Helper;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by coredeveloper on 4/4/18.
 */

public class UserAdapterRecyclerView extends RecyclerView.Adapter<UserAdapterRecyclerView.UserGitHubViewHolder>{

    private ArrayList<UserGitHub> users;
    private int resource;
    private Activity activity;

    public UserAdapterRecyclerView(ArrayList<UserGitHub> data, int resource, Activity activity) {
        this.users    = data;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public UserGitHubViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new UserGitHubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserGitHubViewHolder holder,int position) {
        UserGitHub user = this.users.get(position);

        Helper.LoadImage(activity, user.getAvatarUrl(), holder.avatar, null, null);
        holder.title.setText(user.getLogin());
        holder.subtitle.setText(user.getType());
        holder.description.setText(user.getDescriptionLevel());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserGitHubViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView avatar;
        private TextView  title;
        private TextView  subtitle;
        private TextView  description;

        public UserGitHubViewHolder(View itemView) {

            super(itemView);

            this.avatar      = (CircleImageView) itemView.findViewById(R.id.avatar_image_view);
            this.title       = (TextView)        itemView.findViewById(R.id.title);
            this.subtitle    = (TextView)        itemView.findViewById(R.id.subtitle);
            this.description = (TextView)        itemView.findViewById(R.id.description);
        }
    }
}
