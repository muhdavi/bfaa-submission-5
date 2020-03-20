package com.onesoul.moviecataloguefinal.tvshow;

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

public class TvDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_TV = "extra_tv";
    private Button btnFavorite;
    private Tvshow tvshow;
    private DmlHelper dmlHelper;
    private boolean isFavorite = false;
    private String URL_IMAGE = BuildConfig.URL_IMAGE;

    public TvDetailActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);
        TextView tvTitle = findViewById(R.id.tv_title_tv);
        TextView tvOverview = findViewById(R.id.tv_overview_tv);
        TextView tvReleaseDate = findViewById(R.id.tv_release_date);
        TextView tvVoteCount = findViewById(R.id.tv_vote_count);
        TextView tvVoteAverage = findViewById(R.id.tv_vote_average);
        ImageView imgPhoto = findViewById(R.id.img_poster_tv);
        btnFavorite = findViewById(R.id.button_favorite);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.detail_tvshow);
        }

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        btnFavorite.setOnClickListener(this);

        tvshow = getIntent().getParcelableExtra(EXTRA_TV);
        tvTitle.setText(Objects.requireNonNull(tvshow).getmTitle());
        tvOverview.setText(tvshow.getmOverview());
        tvReleaseDate.setText(tvshow.getmReleaseDate());
        tvVoteCount.setText(tvshow.getmVoteCount());
        tvVoteAverage.setText(tvshow.getmVoteAverage());
        Glide.with(this)
                .load(URL_IMAGE + tvshow.getmPhoto())
                .placeholder(R.drawable.ic_thumbnail_big)
                .transform(new FitCenter())
                .into(imgPhoto);

        dmlHelper = dmlHelper.getInstance(getApplicationContext());
        if (dmlHelper.CheckData(String.valueOf(tvshow.getmId()))) {
            btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_favorite_red));
            isFavorite = true;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_favorite) {
            if (!isFavorite) {
                tvshow.setmType("tvshow");
                dmlHelper = DmlHelper.getInstance(getApplicationContext());
                dmlHelper.open();
                long result = dmlHelper.insertTvshow(tvshow);
                dmlHelper.close();
                if (result > 0) {
                    btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_favorite_red));
                    Toast.makeText(getApplicationContext(), tvshow.getmTitle() + " " + getString(R.string.has_add), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), tvshow.getmTitle() + getString(R.string.hasnot_add), Toast.LENGTH_SHORT).show();
                }
            } else {
                tvshow.setmType("tvshow");
                dmlHelper.open();
                long result = dmlHelper.deleteTvshow(tvshow.getmId());
                dmlHelper.close();
                if (result > 0) {
                    btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_favorite_red_border));
                    Toast.makeText(getApplicationContext(), tvshow.getmTitle() + " " + getString(R.string.has_delete), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed " + tvshow.getmTitle() + " " + getString(R.string.hasnot_delete), Toast.LENGTH_SHORT).show();
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
