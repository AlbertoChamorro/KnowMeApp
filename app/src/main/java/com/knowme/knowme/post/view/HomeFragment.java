package com.knowme.knowme.post.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.knowme.knowme.R;
import com.knowme.knowme.adapter.PictureAdapterRecyclerView;
import com.knowme.knowme.model.Picture;
import com.knowme.knowme.util.Helper;
import com.knowme.knowme.util.interfaces.IAlertDialog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private IAlertDialog iAlertDialog = new IAlertDialog() {
        @Override
        public void okAction(DialogInterface dialog, int which) {
            getActivity().finish();
        }

        @Override
        public void cancelAction(DialogInterface dialog,int which) {
            dialog.cancel();
        }
    };

    private static final int REQUEST_CAMERA_IDENTIFIER = 1;
    private FloatingActionButton cameraFloatingButton;
    private String photoPathTemp = "";

    private Toolbar toolbar;
    private TextView titleToolbar;
    private ImageView imageLeftToolbar;
    private ImageView imageRightToolbar;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // toolbar
        //this.showToolbar(getResources().getString(R.string.text_home_bottombar),false, view);
        this.setupToolbar(view);

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

//    public void showToolbar(String title, Boolean showBackButton, View view) {
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(showBackButton);
//    }

    private void setupToolbar(View view){

        //this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.titleToolbar = (TextView) view.findViewById(R.id.title_toolbar);
        this.titleToolbar.setText(getResources().getString(R.string.app_name));

        this.imageLeftToolbar = (ImageView) view.findViewById(R.id.left_image_toolbar);
        this.imageLeftToolbar.setBackgroundResource(R.drawable.ic_action_menu);
        this.imageLeftToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
            }
        });

        this.imageRightToolbar = (ImageView) view.findViewById(R.id.right_image_toolbar);
        this.imageRightToolbar.setBackgroundResource(R.drawable.ic_action_logout);
        this.imageRightToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builderDialog();
            }
        });
    }

    private void builderDialog(){
        AlertDialog.Builder builderDialog = Helper.setupDialog(getActivity(), "Mensaje", "¿Desea cerrar sesión?", null, null);
        Helper.builOptionButtonDialog(builderDialog, "Aceptar",
                true, iAlertDialog);
        Helper.builOptionButtonDialog(builderDialog, "Cancelar",
                false, iAlertDialog);
        AlertDialog dialog = Helper.createDialog(builderDialog);
        Helper.customOptionButtonColor(dialog, getActivity(), R.color.colorPrimary, true);
        Helper.customOptionButtonColor(dialog, getActivity(), R.color.colorRed, false);
    }
}
