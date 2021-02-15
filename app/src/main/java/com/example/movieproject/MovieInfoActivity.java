package com.example.movieproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieInfoActivity extends AppCompatActivity {

    List<Movie> movie;
    ImageView avatar;
    TextView movieDesc;
    TextView movie_Year;
    TextView movie_Rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);

        Intent intent = getIntent();

        String movieName = intent.getStringExtra("movie_name");
        String movieDescription = intent.getStringExtra("movie_description");
        double rating = intent.getDoubleExtra("movie_rating", 0);
        int year = intent.getIntExtra("year_released", 1900);
        String imageUrl = intent.getStringExtra("movie_image");

        movieDesc = findViewById(R.id.info_description);
        movie_Rating = findViewById(R.id.info_rating);
        movie_Year = findViewById(R.id.info_year);
        avatar = findViewById(R.id.info_avatar);

        movieDesc.setText(movieDescription);
        movie_Rating.setText(Double.toString(rating));
        movie_Year.setText(Integer.toString(year));
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL(MovieAppActivity.applicationContext.getResources().getString(R.string.api_url)
                    + imageUrl);
            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            avatar.setImageBitmap(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}