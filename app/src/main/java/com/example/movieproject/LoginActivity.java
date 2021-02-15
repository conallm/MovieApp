package com.example.movieproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editPassword;
    Button btnLogin;
    SessionManagement sessionManagement;
   // SharedPreferences sharedPreferences;
    public static RequestQueue queue;
    public static final String SHARED_PREFS = "SHAREDPREFS";
    Context mContext; //private static



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);

       sessionManagement = new SessionManagement(mContext);//(LoginActivity.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = new User(editEmail.getText().toString(), editPassword.getText().toString());

                sessionManagement.saveSession(user);

                if (LoginActivity.queue == null){
                    queue = Volley.newRequestQueue(getApplicationContext());

                }

                String url = getResources().getString(R.string.api_url)+"/User/Login";

                final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            Map loginResponse = Utils.toMap(new JSONObject(response));
                            if(loginResponse.get("status").toString().equals("success")){
                                Log.v("user", loginResponse.get("user").toString());

                                sessionManagement.writeSessionStatus(true);
                                Log.v("Status", "Logged In" );
                            } else {
                                Log.v("error", loginResponse.get("message").toString());
                            }
                        } catch (Exception e){
                            Log.v("Error:", "Error creating JSON object");
                        }
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.v("Response is:", "That didn't work!");
                    }
                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("email", editEmail.getText().toString());
                        params.put("password", editPassword.getText().toString());
                        return params;
                    }
                };
                Handler handler = new Handler();
                handler.postDelayed(new Runnable(){
                    @Override
                            public void run() {
                        LoginActivity.queue.add(stringRequest);
                    }
                }, 200);

                launchMovieApp();
                finish();
            }
        });

    }


    protected void onStart(){
        super.onStart();
        sessionManagement = new SessionManagement(getApplicationContext());
        checkSession();
    }
    public void launchMovieApp(){
        Intent movieAppIntent = new Intent(LoginActivity.this, MovieAppActivity.class);
        movieAppIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(movieAppIntent); //mContext
        //finish();
    }
    private void checkSession() {
        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
        boolean logged_in = sessionManagement.getSession();
        if (!logged_in == false) {
            launchMovieApp();
           // finish();
        } else {
        }
    }
}







