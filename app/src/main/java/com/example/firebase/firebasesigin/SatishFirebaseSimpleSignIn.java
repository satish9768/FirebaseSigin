package com.example.firebase.firebasesigin;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Harish on 16-Aug-16.
 */
public class SatishFirebaseSimpleSignIn extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Setting Firebase to be used anywhere in the application
        Firebase.setAndroidContext(this);
    }
}
