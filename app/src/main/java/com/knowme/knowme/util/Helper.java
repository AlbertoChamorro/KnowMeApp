package com.knowme.knowme.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.knowme.knowme.R;
import com.knowme.knowme.util.interfaces.IAlertDialog;
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

    public static <T> void showFragment(AppCompatActivity activity, T fragment, int containerViewId) {

        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, (Fragment) fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    public static AlertDialog.Builder setupDialog(Context context, String title, String message, Boolean touchOutSide , Drawable iconResource){

        AlertDialog.Builder builderDialog = new AlertDialog.Builder(context);
        builderDialog.setTitle(title);
        builderDialog.setMessage(message);
        builderDialog.setCancelable(touchOutSide == null ? false : touchOutSide);

        if (iconResource != null) {
            Drawable icon = context.getResources().getDrawable(R.drawable.ic_action_message_dialog);
            builderDialog.setIcon(icon);
        }
        // TODO: divider line

        return builderDialog;
    }

    public static void builOptionButtonDialog(AlertDialog.Builder builderDialog,String textButton,
                                        Boolean isOkAction, final IAlertDialog managerDialog){

        if (isOkAction) {
            builderDialog.setPositiveButton(textButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    managerDialog.okAction(dialog, which);
                }
            });
        }else{
            builderDialog.setNegativeButton(textButton,new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    managerDialog.cancelAction(dialog, which);
                }
            });
        }
    }

    public static AlertDialog createDialog(AlertDialog.Builder builderDialog){

         AlertDialog dialog = builderDialog.create();
         dialog.show();

         return dialog;
    }

    public static void customOptionButtonColor(AlertDialog dialog, Context context, int textColor, Boolean isOkAction){

        int optionButton = isOkAction ? AlertDialog.BUTTON_POSITIVE : AlertDialog.BUTTON_NEGATIVE;
        dialog.getButton(optionButton).setTextColor(context.getResources().getColor(textColor));
    }
}
