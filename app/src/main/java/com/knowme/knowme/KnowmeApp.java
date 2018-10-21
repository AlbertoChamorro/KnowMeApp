package com.knowme.knowme;

// com.activeandroid.app.Application is of ActiveAndroid package
// android.app.Application of native android package
import com.activeandroid.app.Application;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by coredeveloper on 3/17/18.
 */

public class KnowmeApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
    }
}
