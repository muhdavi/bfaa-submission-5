package com.onesoul.moviecataloguefinal.movie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.onesoul.moviecataloguefinal.BuildConfig;
import com.onesoul.moviecataloguefinal.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movie> listMovie;
    private final Context context;
    private String URL_IMAGE = BuildConfig.URL_IMAGE;

    public MovieAdapter(Context context) {
        this.listMovie = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.tvTitle.setText(getListMovie().get(position).getmTitle());
        holder.tvOverview.setText(getListMovie().get(position).getmOverview());
        holder.tvRate.setText(getListMovie().get(position).getmVoteAverage());
        Glide.with(context)
                .load(URL_IMAGE + getListMovie().get(position).getmPhoto())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_thumbnail)
                        .transform(new RoundedCorners(16)))
                .into(holder.imgPhoto);
    }

    public void setListMovie(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
    }

    private ArrayList<Movie> getListMovie() {
        return listMovie;
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle, tvOverview, tvRate;
        ImageView imgPhoto;

        MovieViewHolder(@NonNull View itemView) {
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
                Intent movieIntent = new Intent(context, MovieDetailActivity.class);
                movieIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, getListMovie().get(position));
                context.startActivity(movieIntent);
            }
        }
    }
}
