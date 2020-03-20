package com.onesoul.moviecataloguefinal.movie;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.onesoul.moviecataloguefinal.BuildConfig;
import com.onesoul.moviecataloguefinal.R;
import com.onesoul.moviecataloguefinal.database.DmlHelper;

import java.util.Objects;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_MOVIE = "extra_movie";
    private DmlHelper dmlHelper;
    private Movie movie;
    private Button btnFavorite;
    private boolean isFavorite = false;
    private String URL_IMAGE = BuildConfig.URL_IMAGE;

    public MovieDetailActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        TextView tvTitle = findViewById(R.id.tv_title_movie);
        TextView tvOverview = findViewById(R.id.tv_overview_movie);
        TextView tvReleaseDate = findViewById(R.id.tv_release_date);
        TextView tvVoteCount = findViewById(R.id.tv_vote_count);
        TextView tvVoteAverage = findViewById(R.id.tv_vote_average);
        ImageView imgPhoto = findViewById(R.id.img_poster_movie);
        btnFavorite = findViewById(R.id.button_favorite);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.detail_movie);
        }

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        btnFavorite.setOnClickListener(this);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        tvTitle.setText(Objects.requireNonNull(movie).getmTitle());
        tvOverview.setText(movie.getmOverview());
        tvReleaseDate.setText(movie.getmReleaseDate());
        tvVoteCount.setText(movie.getmVoteCount());
        tvVoteAverage.setText(movie.getmVoteAverage());
        Glide.with(this)
                .load(URL_IMAGE + movie.getmPhoto())
                .placeholder(R.drawable.ic_thumbnail_big)
                .transform(new FitCenter())
                .into(imgPhoto);

        dmlHelper = dmlHelper.getInstance(getApplicationContext());
        if (dmlHelper.CheckData(String.valueOf(movie.getmId()))) {
            btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_favorite_red));
            isFavorite = true;
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_favorite) {
            if (!isFavorite) {
                movie.setmType("movie");
                dmlHelper = DmlHelper.getInstance(getApplicationContext());
                dmlHelper.open();
                long result = dmlHelper.insertMovie(movie);
                dmlHelper.close();
                if (result > 0) {
                    btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_favorite_red));
                    Toast.makeText(getApplicationContext(), movie.getmTitle() + " " + getString(R.string.has_add), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), movie.getmTitle() + " " + getString(R.string.hasnot_add), Toast.LENGTH_SHORT).show();
                }
            } else {
                movie.setmType("movie");
                dmlHelper.open();
                long result = dmlHelper.deleteMovie(movie.getmId());
                dmlHelper.close();
                if (result > 0) {
                    btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_favorite_red_border));
                    Toast.makeText(getApplicationContext(), movie.getmTitle() + " " + getString(R.string.has_delete), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), movie.getmTitle() + " " + getString(R.string.hasnot_delete), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
