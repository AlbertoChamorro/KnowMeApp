package com.knowme.knowme.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knowme.knowme.R;
import com.knowme.knowme.View.Adapter.PictureAdapterRecyclerView;
import com.knowme.knowme.View.Model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.showToolbar("Home",false, view);

        RecyclerView picturesRecylcler = (RecyclerView) view.findViewById(R.id.pictureCardRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        picturesRecylcler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(this.buildPictures(), R.layout.cardview_picture, getActivity());
        picturesRecylcler.setAdapter(pictureAdapterRecyclerView);

        return view;
    }

    public ArrayList<Picture> buildPictures() {
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture(
                "https://www.google.com.ni/imgres?imgurl=https%3A%2F%2Foinsidercom.files.wordpress.com%2F2017%2F03%2F2nqzjyqi.png&imgrefurl=https%3A%2F%2Foinsidercom.wordpress.com%2F2017%2F01%2F17%2Fin-this-tutorial-we-learn-how-to-implement-qr-code-scanner-generator-using-zxing-scanner-generator-library-in-android-application-2%2F&docid=-ihLMI3SKaQUbM&tbnid=RrfLyer-GLR-NM%3A&vet=10ahUKEwiE4ca2zfvYAhWRvVMKHctFB-AQMwg8KAAwAA..i&w=400&h=400&itg=1&bih=796&biw=1707&q=generator%20images%20picasso%20android&ved=0ahUKEwiE4ca2zfvYAhWRvVMKHctFB-AQMwg8KAAwAA&iact=mrc&uact=8","José Rámirez","10 días","450"
        ));
        pictures.add(new Picture(
                "https://www.google.com.ni/imgres?imgurl=https%3A%2F%2Foinsidercom.files.wordpress.com%2F2017%2F03%2F2nqzjyqi.png&imgrefurl=https%3A%2F%2Foinsidercom.wordpress.com%2F2017%2F01%2F17%2Fin-this-tutorial-we-learn-how-to-implement-qr-code-scanner-generator-using-zxing-scanner-generator-library-in-android-application-2%2F&docid=-ihLMI3SKaQUbM&tbnid=RrfLyer-GLR-NM%3A&vet=10ahUKEwiE4ca2zfvYAhWRvVMKHctFB-AQMwg8KAAwAA..i&w=400&h=400&itg=1&bih=796&biw=1707&q=generator%20images%20picasso%20android&ved=0ahUKEwiE4ca2zfvYAhWRvVMKHctFB-AQMwg8KAAwAA&iact=mrc&uact=8","Alberto Castellón","15 días","4"
        ));
        pictures.add(new Picture(
                "https://www.google.com.ni/imgres?imgurl=https%3A%2F%2Foinsidercom.files.wordpress.com%2F2017%2F03%2F2nqzjyqi.png&imgrefurl=https%3A%2F%2Foinsidercom.wordpress.com%2F2017%2F01%2F17%2Fin-this-tutorial-we-learn-how-to-implement-qr-code-scanner-generator-using-zxing-scanner-generator-library-in-android-application-2%2F&docid=-ihLMI3SKaQUbM&tbnid=RrfLyer-GLR-NM%3A&vet=10ahUKEwiE4ca2zfvYAhWRvVMKHctFB-AQMwg8KAAwAA..i&w=400&h=400&itg=1&bih=796&biw=1707&q=generator%20images%20picasso%20android&ved=0ahUKEwiE4ca2zfvYAhWRvVMKHctFB-AQMwg8KAAwAA&iact=mrc&uact=8","Josh Warren","3 días","50"
        ));
       return pictures;
    }

    public void showToolbar(String title, Boolean showBackButton, View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(showBackButton);
    }
}
