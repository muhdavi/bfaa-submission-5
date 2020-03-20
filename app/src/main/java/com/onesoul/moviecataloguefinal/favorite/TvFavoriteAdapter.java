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
import com.onesoul.moviecataloguefinal.tvshow.TvDetailActivity;
import com.onesoul.moviecataloguefinal.tvshow.Tvshow;

import java.util.ArrayList;

public class TvFavoriteAdapter extends RecyclerView.Adapter<TvFavoriteAdapter.TvFavoriteViewHolder> {
    private final ArrayList<Tvshow> listTvFavorite = new ArrayList<>();
    private final Activity activity;

    public TvFavoriteAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListTvFavorite(ArrayList<Tvshow> listTvFavorite) {
        if (listTvFavorite.size() > 0) this.listTvFavorite.clear();
        this.listTvFavorite.addAll(listTvFavorite);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvFavoriteAdapter.TvFavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        return new TvFavoriteViewHolder(view);
    }

    private ArrayList<Tvshow> getTvFavorite() {
        return listTvFavorite;
    }

    @Override
    public void onBindViewHolder(@NonNull TvFavoriteAdapter.TvFavoriteViewHolder holder, int position) {
        holder.tvTitle.setText(listTvFavorite.get(position).getmTitle());
        holder.tvOverview.setText(listTvFavorite.get(position).getmOverview());
        holder.tvRate.setText(listTvFavorite.get(position).getmVoteAverage());
        Glide.with(activity)
                .load("https://image.tmdb.org/t/p/w500/" + getTvFavorite().get(position).getmPhoto())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_thumbnail))
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return listTvFavorite.size();
    }

    public class TvFavoriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle, tvOverview, tvRate;
        ImageView imgPhoto;

        public TvFavoriteViewHolder(@NonNull View itemView) {
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
                Intent tvIntent = new Intent(activity, TvDetailActivity.class);
                tvIntent.putExtra(TvDetailActivity.EXTRA_TV, getTvFavorite().get(position));
                activity.startActivity(tvIntent);
            }
        }
    }
}
