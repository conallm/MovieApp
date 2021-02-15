package com.example.movieproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static final String SHARED_PREFS = "SHAREDPREFS";
    public static final String LOGGED_IN = "LOGGED_STATE";
    Context context; // private static
    public static final String EMAIL_KEY = "email";
    public static final String PASS_VALUE = "password";

    public SessionManagement(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        // editor.commit();

    }

    public void writeSessionStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGGED_IN, status);
        editor.apply();
    }

    public boolean getSession() {


        return sharedPreferences.getBoolean(LOGGED_IN, false);


    }


    /* public void setLoggedIn ( boolean state){
         getSharedPreferences().edit().putBoolean(LOGGED_IN, state).apply();
     }
*/
    public void saveSession(User user) {

        String email = user.getEmail();
        editor.putString(EMAIL_KEY, email).commit();
        String password = user.getPassword();
        editor.putString(PASS_VALUE, password).commit();
       // Toast.makeText(context, "Login Data has been saved", Toast.LENGTH_LONG).show();

    }
}



