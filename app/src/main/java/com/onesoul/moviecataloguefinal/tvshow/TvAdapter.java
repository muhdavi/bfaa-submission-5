package com.onesoul.moviecataloguefinal.tvshow;

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

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {
    private final Context context;
    private ArrayList<Tvshow> listTvshow;
    private String URL_IMAGE = BuildConfig.URL_IMAGE;

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        return new TvViewHolder(view);
    }

    public TvAdapter(Context context) {
        this.listTvshow = new ArrayList<>();
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int position) {
        holder.tvTitle.setText(getListTv().get(position).getmTitle());
        holder.tvOverview.setText(getListTv().get(position).getmOverview());
        holder.tvRate.setText(getListTv().get(position).getmVoteAverage());
        Glide.with(context)
                .load(URL_IMAGE + getListTv().get(position).getmPhoto())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_thumbnail)
                        .transform(new RoundedCorners(16)))
                .into(holder.imgPhoto);
    }

    public void setListTv(ArrayList<Tvshow> listTv) {
        this.listTvshow = listTv;
    }

    private ArrayList<Tvshow> getListTv() {
        return listTvshow;
    }

    @Override
    public int getItemCount() {
        return getListTv().size();
    }

    class TvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle, tvOverview, tvRate;
        ImageView imgPhoto;

        public TvViewHolder(@NonNull View itemView) {
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
                Intent tvIntent = new Intent(context, TvDetailActivity.class);
                tvIntent.putExtra(TvDetailActivity.EXTRA_TV, getListTv().get(position));
                context.startActivity(tvIntent);
            }
        }
    }
}
