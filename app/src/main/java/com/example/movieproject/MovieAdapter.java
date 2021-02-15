package com.example.movieproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
//private Context mContext;
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Movie> movies = new ArrayList<>(); //changed to ArrayList

    private OnMovieClickListener mListener;  //changed from variable name mOnMovieClickListener
    private Context mContext;

//    public void setOnMovieListener(OnMovieListener listener) {  //new code
//        mListener = listener;
//    }
    public interface OnMovieClickListener {
        void onMovieClick(int position);
}

    public void setOnMovieClickListener(OnMovieClickListener listener){
        mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView movie_name;
        public TextView movie_description;
        public TextView year_released;
        public TextView rating;
        public View layout;
        public ImageView avatar;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            movie_name = v.findViewById(R.id.movie_name);
            movie_description = v.findViewById(R.id.movie_description);
            year_released = v.findViewById(R.id.year_released);
            rating = v.findViewById(R.id.rating);
            avatar = v.findViewById(R.id.movie_cover);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onMovieClick(position);
                        }

                    }
                }

            });
        }

    }

    public MovieAdapter(Context context, ArrayList<Movie> dataset){ //changed to ArrayList
        this.movies = dataset;

        mContext = context;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View movieView = inflater.inflate(R.layout.item_view, parent, false);//item_view
        ViewHolder movieHolder = new ViewHolder(movieView);
        return movieHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder:called");

        final Movie movie = movies.get(position);
        holder.movie_name.setText(movie.getMovieName());
        holder.movie_description.setText(movie.getMovieDescription());
        holder.year_released.setText(Integer.toString(movie.getYearReleased()));
        holder.rating.setText(Double.toString(movie.getRating()));
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL(MovieAppActivity.applicationContext.getResources().getString(R.string.api_url)
                    + movie.getImgUrl());
            Bitmap avatar = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.avatar.setImageBitmap(avatar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

}
