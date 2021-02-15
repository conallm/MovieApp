package com.example.movieproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    //SharedPreferences sharedPreferences;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // sharedPreferences = getSharedPreferences(SessionManagement.SHARED_PREFS, Context.MODE_PRIVATE);
        sessionManagement = new SessionManagement(Splash.this);
         //boolean logged_in;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              /*  Intent mainIntent = new Intent(Splash.this, MovieApp.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();  */

                boolean logged_in = sessionManagement.getSession();
                if(!logged_in == false) {
                    //LoginActivity.launchMovieApp();
                    Intent activityIntent = new Intent(getApplicationContext(), MovieAppActivity.class);
                    Splash.this.startActivity(activityIntent);
                    Splash.this.finish();

                }
               else {
                    Intent activityIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    Splash.this.startActivity(activityIntent);
                    Splash.this.finish();
                }


            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}