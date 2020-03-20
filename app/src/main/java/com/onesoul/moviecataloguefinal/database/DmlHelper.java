package com.onesoul.moviecataloguefinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.onesoul.moviecataloguefinal.movie.Movie;
import com.onesoul.moviecataloguefinal.tvshow.Tvshow;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.onesoul.moviecataloguefinal.database.DatabaseContract.MovieColumns.MOVIE_ID;
import static com.onesoul.moviecataloguefinal.database.DatabaseContract.MovieColumns.OVERVIEW;
import static com.onesoul.moviecataloguefinal.database.DatabaseContract.MovieColumns.RELEASE_DATE;
import static com.onesoul.moviecataloguefinal.database.DatabaseContract.MovieColumns.TITLE;
import static com.onesoul.moviecataloguefinal.database.DatabaseContract.MovieColumns.TV_ID;
import static com.onesoul.moviecataloguefinal.database.DatabaseContract.MovieColumns.TYPE;
import static com.onesoul.moviecataloguefinal.database.DatabaseContract.MovieColumns.URL_IMAGE;
import static com.onesoul.moviecataloguefinal.database.DatabaseContract.MovieColumns.VOTE_AVERAGE;
import static com.onesoul.moviecataloguefinal.database.DatabaseContract.MovieColumns.VOTE_COUNT;
import static com.onesoul.moviecataloguefinal.database.DatabaseContract.TABLE_NAME;

public class DmlHelper {
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static DmlHelper INSTANCE;
    private static SQLiteDatabase sqLiteDatabase;


    private DmlHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static DmlHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DmlHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (sqLiteDatabase.isOpen())
            sqLiteDatabase.close();
    }

    public long insertMovie(Movie movie) {
        ContentValues args = new ContentValues();
        args.put(_ID, movie.getmId());
        args.put(MOVIE_ID, movie.getmId());
        args.put(TYPE, movie.getmType());
        args.put(TITLE, movie.getmTitle());
        args.put(OVERVIEW, movie.getmOverview());
        args.put(RELEASE_DATE, movie.getmReleaseDate());
        args.put(VOTE_COUNT, movie.getmVoteCount());
        args.put(VOTE_AVERAGE, movie.getmVoteAverage());
        args.put(URL_IMAGE, movie.getmPhoto());
        return sqLiteDatabase.insert(DATABASE_TABLE, null, args);
    }

    public long deleteMovie(int id) {
        return sqLiteDatabase.delete(DATABASE_TABLE, _ID + " = '" + id + "'", null);
    }

    public ArrayList<Movie> getListMovieFavorite(String type) {
        ArrayList<Movie> arrayList = new ArrayList<>();
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE,
                new String[]{_ID, TYPE, TITLE, OVERVIEW, RELEASE_DATE, VOTE_COUNT, VOTE_AVERAGE, URL_IMAGE},
                TYPE + "=?",
                new String[]{type},
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new Movie();
                movie.setmId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movie.setmType(cursor.getString(cursor.getColumnIndexOrThrow(TYPE)));
                movie.setmTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movie.setmOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                movie.setmReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                movie.setmVoteCount(cursor.getString(cursor.getColumnIndexOrThrow(VOTE_COUNT)));
                movie.setmVoteAverage(cursor.getString(cursor.getColumnIndexOrThrow(VOTE_AVERAGE)));
                movie.setmPhoto(cursor.getString(cursor.getColumnIndexOrThrow(URL_IMAGE)));
                arrayList.add(movie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public boolean CheckData(String id) throws SQLException {
        boolean isFavorite = false;
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE
                + " WHERE " + MOVIE_ID + "=?", new String[]{id});
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                isFavorite = true;
            } while (cursor.moveToNext());
        }
        assert cursor != null;
        cursor.close();
        return isFavorite;
    }

    public long insertTvshow(Tvshow tvshow) {
        ContentValues args = new ContentValues();
        args.put(_ID, tvshow.getmId());
        args.put(TV_ID, tvshow.getmId());
        args.put(TYPE, tvshow.getmType());
        args.put(TITLE, tvshow.getmTitle());
        args.put(OVERVIEW, tvshow.getmOverview());
        args.put(RELEASE_DATE, tvshow.getmReleaseDate());
        args.put(VOTE_COUNT, tvshow.getmVoteCount());
        args.put(VOTE_AVERAGE, tvshow.getmVoteAverage());
        args.put(URL_IMAGE, tvshow.getmPhoto());
        return sqLiteDatabase.insert(DATABASE_TABLE, null, args);
    }

    public long deleteTvshow(int id) {
        return sqLiteDatabase.delete(DATABASE_TABLE, _ID + " = '" + id + "'", null);
    }

    public ArrayList<Tvshow> getListTvFavorite(String type) {
        ArrayList<Tvshow> arrayList = new ArrayList<>();
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE,
                new String[]{_ID, TYPE, TITLE, OVERVIEW, RELEASE_DATE, VOTE_COUNT, VOTE_AVERAGE, URL_IMAGE},
                TYPE + "=?",
                new String[]{type},
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        Tvshow tvshow;
        if (cursor.getCount() > 0) {
            do {
                tvshow = new Tvshow();
                tvshow.setmId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                tvshow.setmType(cursor.getString(cursor.getColumnIndexOrThrow(TYPE)));
                tvshow.setmTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                tvshow.setmOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                tvshow.setmReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                tvshow.setmVoteCount(cursor.getString(cursor.getColumnIndexOrThrow(VOTE_COUNT)));
                tvshow.setmVoteAverage(cursor.getString(cursor.getColumnIndexOrThrow(VOTE_AVERAGE)));
                tvshow.setmPhoto(cursor.getString(cursor.getColumnIndexOrThrow(URL_IMAGE)));
                arrayList.add(tvshow);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
}
