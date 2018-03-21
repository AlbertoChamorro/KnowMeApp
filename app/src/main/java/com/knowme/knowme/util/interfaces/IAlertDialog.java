package com.knowme.knowme.util.interfaces;

import android.content.DialogInterface;

/**
 * Created by coredeveloper on 3/21/18.
 */

public interface IAlertDialog{

    void okAction(DialogInterface dialog, int which);
    void cancelAction(DialogInterface dialog, int which);
}
