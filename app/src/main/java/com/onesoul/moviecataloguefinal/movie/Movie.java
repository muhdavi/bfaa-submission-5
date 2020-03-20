package com.onesoul.moviecataloguefinal.movie;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.text.SimpleDateFormat;

public class Movie implements Parcelable {
    private int mId;
    private String mType;
    private String mTitle;
    private String mOverview;
    private String mReleaseDate;
    private String mVoteCount;
    private String mVoteAverage;
    private String mPhoto;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getmVoteCount() {
        return mVoteCount;
    }

    public void setmVoteCount(String mVoteCount) {
        this.mVoteCount = mVoteCount;
    }

    public String getmVoteAverage() {
        return mVoteAverage;
    }

    public void setmVoteAverage(String mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public String getmPhoto() {
        return mPhoto;
    }

    public void setmPhoto(String mPhoto) {
        this.mPhoto = mPhoto;
    }

    public Movie() {

    }

    public Movie(JSONObject object) {
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd, mm, yyyy");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            int id = object.getInt("id");
            String title = object.getString("title");
            String overview = object.getString("overview");
            String release_date = object.getString("release_date");
            String vote_count = object.getString("vote_count");
            String vote_average = object.getString("vote_average");
            String url_image = object.getString("poster_path");

            this.mId = id;
            this.mTitle = title;
            this.mOverview = overview;
            this.mReleaseDate = formatter.format(dateFormat.parse(release_date));
            this.mVoteCount = vote_count;
            this.mVoteAverage = vote_average;
            this.mPhoto = url_image;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mType);
        dest.writeString(this.mTitle);
        dest.writeString(this.mOverview);
        dest.writeString(this.mReleaseDate);
        dest.writeString(this.mVoteCount);
        dest.writeString(this.mVoteAverage);
        dest.writeString(this.mPhoto);
    }

    protected Movie(Parcel in) {
        mId = in.readInt();
        mType = in.readString();
        mTitle = in.readString();
        mOverview = in.readString();
        mReleaseDate = in.readString();
        mVoteCount = in.readString();
        mVoteAverage = in.readString();
        mPhoto = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}