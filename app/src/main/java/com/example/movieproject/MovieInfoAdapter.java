//package com.example.movieproject;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.StrictMode;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.net.URL;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//class MovieInfoAdapter extends RecyclerView.Adapter<MovieInfoAdapter.ViewHolder> {
//
//    private List<Movie> movie;
//    //Movie movie = new Movie();
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        public ImageView starring1;
//        public ImageView starring2;
//        public ImageView starring3;
//        public ImageView avatar;
//
//        public TextView movie_name;
//        public TextView movie_description;
//        public TextView year_released;
//        public TextView movie_cast_star1;
//        public TextView movie_cast_star2;
//        public TextView movie_cast_star3;
//        public TextView rating;
//        public View layout;
//
//        public ViewHolder(View v) {
//            super(v);
//            layout = v;
//            movie_name = v.findViewById(R.id.movie_name);
//            movie_description = v.findViewById(R.id.movie_description);
//            year_released = v.findViewById(R.id.year_released);
//            rating = v.findViewById(R.id.rating);
//            avatar = v.findViewById(R.id.movie_cover);
//
//        }
//
//    }
//
//    @Override
//    public MovieInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View movieInfoView = inflater.inflate(R.layout.movie_info, parent, false);
//        MovieInfoAdapter.ViewHolder movie = new MovieInfoAdapter.ViewHolder(movieInfoView);
//        return movie;
//    }
//
////    @Override
////    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
////
////    }
//
//    public MovieInfoAdapter(List<Movie> dataset) {
//        movie = dataset;
//    }
//
//
//    @Override
//
//    public void onBindViewHolder(MovieInfoAdapter.ViewHolder holder, final int position){
//        //final Map movie = (HashMap)movies.get(position);
//        final Movie movieInfo = movie.get(position);
//        holder.movie_name.setText(movieInfo.getMovieName());//(movie.get("Movie Name").toString());
//        holder.movie_description.setText(movieInfo.getMovieDescription());//(movie.get("Movie Description").toString());
//        holder.year_released.setText(movieInfo.getYearReleased());//(movie.get("Year").toString());
//        holder.rating.setText(String.valueOf(movieInfo.getRating()));//(movie.get("Rating").toString());
//        holder.movie_cast_star1.setText(movieInfo.getCastList("Cast_Name"));
//        holder.movie_cast_star2.setText(movieInfo.getCast_Name());
//        holder.movie_cast_star3.setText(movieInfo.getCast_Name());
//
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        try{
//            URL url = new URL(MovieApp.applicationContext.getResources().getString(R.string.api_url)
//            +movieInfo.getImageUrl());
//            Bitmap avatar = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            holder.avatar.setImageBitmap(avatar);
//
//            Bitmap starring1 = BitmapFactory.decodeStream(url.openConnection().getInputStream());;
//            holder.starring1.setImageBitmap(starring1);
//
//            Bitmap starring2 = BitmapFactory.decodeStream(url.openConnection().getInputStream());;
//            holder.starring2.setImageBitmap(starring2);
//
//            Bitmap starring3 = BitmapFactory.decodeStream(url.openConnection().getInputStream());;
//            holder.starring3.setImageBitmap(starring3);
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return movie.size();
//    }
//
//}
//
