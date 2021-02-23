package com.example.liftlog;

import android.app.Application;
import android.content.Context;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;

public class MyApplication extends Application {

    private static Application thisApp;
    public static FirebaseAuth fAuth;
    public static FirebaseUser fUser;
    public static DatabaseReference dataRef;
    public static User user;
    public static ArrayList<Exercise> exerciseList;
    public static Routine routine = new Routine(0,"nSuns","nSuns 531 is an beginner/intermediate program focused on multiple sets of main lifts. It takes advantage of the compound engagement of main lifts and slow, small increases in overall weight. Accessory lifts should be used to target muscles that aren't included in main lifts. The ones suggested can be switched out for others, depending on the goals of the user.\n" + "\n" + "In the tables below, sets that are bolded are \"max\" sets. These represent 90% of the maximum weight you can lift unless indicated otherwise. The sets prior to these should be warmed up into with lower weight while the sets following should decrease in weight. Sets that are italicized are to be done with the same weight, around 60% of the maximum weight capable or less.", "x");

    public static Application getApplication() {
        return thisApp;
    }

    public static Context getContext() {
        return thisApp.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        thisApp = this;
        this.exerciseList = new ArrayList<Exercise>();
    }
}

