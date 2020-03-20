package com.onesoul.moviecataloguefinal.favorite;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.onesoul.moviecataloguefinal.R;
import com.onesoul.moviecataloguefinal.movie.Movie;
import com.onesoul.moviecataloguefinal.movie.MovieDetailActivity;

import java.util.ArrayList;

public class MovieFavoriteAdapter extends RecyclerView.Adapter<MovieFavoriteAdapter.MovieFavoriteViewHolder> {
    private final ArrayList<Movie> listMovieFavorite = new ArrayList<>();
    private final Activity activity;

    public MovieFavoriteAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public MovieFavoriteAdapter.MovieFavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        return new MovieFavoriteViewHolder(view);
    }

    private ArrayList<Movie> getMovieFavorite() {
        return listMovieFavorite;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieFavoriteAdapter.MovieFavoriteViewHolder holder, int position) {
        holder.tvTitle.setText(listMovieFavorite.get(position).getmTitle());
        holder.tvOverview.setText(listMovieFavorite.get(position).getmOverview());
        holder.tvRate.setText(listMovieFavorite.get(position).getmVoteAverage());
        Glide.with(activity)
                .load("https://image.tmdb.org/t/p/w500/" + getMovieFavorite().get(position).getmPhoto())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_thumbnail))
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return listMovieFavorite.size();
    }

    public class MovieFavoriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle, tvOverview, tvRate;
        ImageView imgPhoto;

        MovieFavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            tvRate = itemView.findViewById(R.id.tv_rate);
            imgPhoto = itemView.findViewById(R.id.img_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Intent movieIntent = new Intent(activity, MovieDetailActivity.class);
                movieIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, getMovieFavorite().get(position));
                activity.startActivity(movieIntent);
            }
        }
    }

    void setListMovieFavorite(ArrayList<Movie> listFavorite) {
        if (listFavorite.size() > 0) this.listMovieFavorite.clear();
        this.listMovieFavorite.addAll(listFavorite);
        notifyDataSetChanged();
    }
}
