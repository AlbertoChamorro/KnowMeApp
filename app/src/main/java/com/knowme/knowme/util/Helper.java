package com.knowme.knowme.util;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.knowme.knowme.R;
import com.squareup.picasso.Picasso;

/**
 * Created by coredeveloper on 3/15/18.
 */

public class Helper {

    public static SpannableString underlineText(String text){
        SpannableString content = new SpannableString(text);
        content.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        return content;
    }

    public static void LoadImage(Activity context, String url, ImageView container, Integer placeHolder,  Integer errorImage){

        placeHolder = placeHolder == null ? R.drawable.placeholder : placeHolder;
        errorImage = errorImage == null ? R.drawable.placeholder : errorImage;

        Picasso.with(context)
                .load(url)
                .placeholder(placeHolder.intValue())
                .error(errorImage.intValue())
                // .resize(50, 50)
                // .centerCrop()
                .into(container);
    }

    public static void showToast(Activity context, String message, int time){

        Toast.makeText(context, message, time).show();
    }

    public static void showSnackBar(View viewContainer, String error, int time, int colorView, int colorText){

        Snackbar snackbar = Snackbar
                .make(viewContainer, error, time);
                //.setAction("Aceptar", new View.OnClickListener(){
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });

        // Changing message text color
        //snackbar.setActionTextColor(colorText);

        // Changing action button text color
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(colorView);
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(colorText);

        snackbar.show();
    }
}
