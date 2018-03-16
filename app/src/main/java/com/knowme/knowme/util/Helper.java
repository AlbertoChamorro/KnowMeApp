package com.knowme.knowme.util;

import android.app.Activity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.ImageView;

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
}
