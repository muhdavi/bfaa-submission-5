package com.onesoul.moviecataloguefinal.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NAME = "movie";

    public static final class MovieColumns implements BaseColumns {
        public static String MOVIE_ID = "id";
        public static String TV_ID = "id";
        public static String TYPE = "type";
        public static String TITLE = "title";
        public static String OVERVIEW = "overview";
        public static String RELEASE_DATE = "release_date";
        public static String VOTE_COUNT = "vote_count";
        public static String VOTE_AVERAGE = "vote_average";
        public static String URL_IMAGE = "poster_path";
    }
}
