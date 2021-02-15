package com.example.movieproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MovieAppActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener {


    private static final String TAG = "MovieAppActivity";
    public static RequestQueue queue;
    public static Context applicationContext;

    //private RecyclerView.Adapter<MovieAdapter.ViewHolder> mAdapter;
    private MovieAdapter mMovieAdapter;
    private ArrayList<Movie> mList = new ArrayList<Movie>();

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Movie mMovie;
    public static String MOVIE_ID;

    // public static final String EXTRA_URL = "itemUrl";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_app);
        Log.d(TAG, "onCreate: started");

        applicationContext = getApplicationContext();

        mRecyclerView = findViewById(R.id.movie_app_recycler_view);
        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(applicationContext);
        mRecyclerView.setLayoutManager(mLayoutManager);


        if (MovieAppActivity.queue == null) {
            MovieAppActivity.queue = Volley.newRequestQueue(getApplicationContext());
            parseJSON();
        }
    }
        private void parseJSON () {
            String url = getResources().getString(R.string.api_url) + "/Movie/GetAllMovies";

            final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Map<String, Object> apiResponseMap = Utils.toMap(new JSONObject(response));
                        if (apiResponseMap.get("status").toString().equals("success")) {
                                 Log.v(TAG, apiResponseMap.get("status").toString());

//Create constructor for movie class that takes HashMap, gets the relevant values and assigns them to the object. Create new object for each value in Api response
// Then insert into an array List in a for loop. Then you end up with a response that is a completely dynamic movie List.

                            ArrayList<Map<String, String>> movieList = new ArrayList<>();
                            movieList = (ArrayList<Map<String, String>>) apiResponseMap.get("movies");

                            if (movieList != null) {
                                Log.v("movies", "movies are not null!!!!");
                                for (Map<String, String> item : movieList) {
                                    Movie movie = new Movie(item);
                                    mList.add(movie);
                                }
                            }
//                            for (Movie movie : mList) {
//                                System.out.println(movie.getMovieName());
//
//                            }
                            mMovieAdapter = new MovieAdapter(applicationContext, mList );
                            mRecyclerView.setAdapter(mMovieAdapter);
                            mMovieAdapter.setOnMovieClickListener(MovieAppActivity.this);
                            mMovieAdapter.notifyDataSetChanged();

                            Log.v("movies", apiResponseMap.get("movies").toString());
                        } else {
                            Log.v("error", apiResponseMap.get("message").toString());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.v("Error:", e.getMessage());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.v("Response is:", "Volley Error: " + error.getMessage());
                }

            });
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MovieAppActivity.queue.add(stringRequest);
                }
            }, 200);

        }


    public void onResume() {
        super.onResume();
        mRecyclerView.setAdapter(mMovieAdapter);
    }


    @Override
    public void onMovieClick(int position) {
        Log.d(TAG, "onMovieClick: clicked");
        Intent movieInfoIntent = new Intent(this, MovieInfoActivity.class);
        mMovie = mList.get(position);

        movieInfoIntent.putExtra("movie_name", mMovie.getMovieName());
        movieInfoIntent.putExtra("movie_description", mMovie.getMovieDescription());
        movieInfoIntent.putExtra("movie_image", mMovie.getImgUrl());
        movieInfoIntent.putExtra("movie_rating", mMovie.getRating());
        movieInfoIntent.putExtra("movie_cast", mMovie.getmCastList());
        movieInfoIntent.putExtra("movie_year", mMovie.getYearReleased());

        startActivity(movieInfoIntent);
    }
}
//6.30 Coding in Flow video 6




