package com.knowme.knowme.post.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knowme.knowme.R;
import com.knowme.knowme.adapter.PictureAdapterRecyclerView;
import com.knowme.knowme.model.Picture;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final int REQUEST_CAMERA_IDENTIFIER = 1;
    private FloatingActionButton cameraFloatingButton;
    private String photoPathTemp = "";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        this.cameraFloatingButton = (FloatingActionButton) view.findViewById(R.id.camera_floating_button);
        this.cameraFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        RecyclerView picturesRecylcler = (RecyclerView) view.findViewById(R.id.pictureCardRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        picturesRecylcler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(this.buildPictures(), R.layout.cardview_picture, getActivity());
        picturesRecylcler.setAdapter(pictureAdapterRecyclerView);

        return view;
    }

    private void takePicture() {
        Intent takeCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takeCameraIntent.resolveActivity(getActivity().getPackageManager()) != null){

            File photoFile = null;
            try {
                photoFile = createPictureFile();

            }catch (Exception e){}

            if (photoFile != null) {
                // for getUriForFile is required create un resource xml file_path with paths allows, and added in manifest
                Uri photoUri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName(), photoFile);
                takeCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takeCameraIntent, REQUEST_CAMERA_IDENTIFIER);
            }
        }
    }

    private File createPictureFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File photo = File.createTempFile(imageFileName, ".jpg", storageDir);

        this.photoPathTemp = "file:" + photo.getAbsolutePath();

        return photo;
    }

    // override activity result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Activity Result", "Test !!!");

        if (requestCode == REQUEST_CAMERA_IDENTIFIER && resultCode == getActivity().RESULT_OK){
            Log.d("Home Fragment", "Camera SuccessFull !!!");
            Intent intent = new Intent(getActivity(), NewPostActivity.class);
            intent.putExtra("PHOTO_PATH_TEMP", this.photoPathTemp);
            startActivity(intent);
        }
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
}
