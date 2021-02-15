
 package com.example.movieproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.*;

 public class Movie implements Serializable {





     public int getMovieID() {
         return mMovieID;
     }

     public void setMovieID(int mMovieID) {
         this.mMovieID = mMovieID;
     }

     public String getMovieName() {
         return mMovieName;
     }

     public void setMovieName(String mMovieName) {
         this.mMovieName = mMovieName;
     }

     public String getMovieDescription() {
         return mMovieDescription;
     }

     public void setMovieDescription(String mMovieDescription) {
         this.mMovieDescription = mMovieDescription;
     }

     public int getYearReleased() {
         return mYearReleased;
     }

     public void setYearReleased(int mYearReleased) {
         this.mYearReleased = mYearReleased;
     }

     public double getRating() {
         return mRating;
     }

     public void setRating(double mRating) {
         this.mRating = mRating;
     }

     public String getImgUrl() {
         return mImgUrl;
     }

     public void setImgUrl(String mImgUrl) {
         this.mImgUrl = mImgUrl;
     }

     public ArrayList<Object> getmCastList() {
         return mCastList;
     }

     public void setCastList(ArrayList<Object> mCastList) {
         this.mCastList = mCastList;
     }

     private int mMovieID;
     private String mMovieName;
     private String mMovieDescription;
     private int mYearReleased;
     private double mRating;
     private String mImgUrl;

     private ArrayList<Object> mCastList;





    public Movie(Map <String, String> movieMap ){     //changed from HashMap to Map
        mMovieID = parseInt(movieMap.get("Movie_ID"));              //parseInt
        mMovieName = movieMap.get("Movie_Name");
        mMovieDescription = movieMap.get("Movie_Description");
        mYearReleased = parseInt(movieMap.get("Year_Released"));
        mRating = Double.parseDouble(movieMap.get("Rating"));
        mImgUrl = movieMap.get("Movie_Cover");
       // mCastList = (ArrayList<Object>) movieMap.get("cast");

    }




    public Movie(){

    }
}
