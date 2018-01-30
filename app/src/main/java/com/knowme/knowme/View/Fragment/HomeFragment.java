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
        this.showToolbar(getResources().getString(R.string.text_home_bottombar),false, view);

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
                "https://losviajesdedomi.com/wp-content/uploads/2014/02/halong-bay-1-600x402.jpg","José Rámirez","10 días","450 Me gusta"
        ));
        pictures.add(new Picture(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTg-FVz4v6DogPagv-eIq4pTGxEp3w4P_Eq4oxGTIUHyJ6lWizhAA","Alberto Castellón","15 días","4 Me gusta"
        ));
        pictures.add(new Picture(
                "https://i2.wp.com/medioambienteynaturaleza.com/wp-content/uploads/2015/06/Fondos-de-pantalla-de-paisajes-naturales25.jpg","Josh Warren","3 días","50 Me gusta"
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
