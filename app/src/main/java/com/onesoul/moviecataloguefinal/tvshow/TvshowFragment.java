package com.onesoul.moviecataloguefinal.tvshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onesoul.moviecataloguefinal.MainViewModel;
import com.onesoul.moviecataloguefinal.R;

import java.util.ArrayList;
import java.util.Objects;

public class TvshowFragment extends Fragment {
    private RecyclerView recyclerView;
    private TvAdapter tvAdapter;
    private ProgressBar progressBar;

    public TvshowFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_tv_fragment);
        progressBar = view.findViewById(R.id.progress_bar);

        MainViewModel mainViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);
        mainViewModel.getTvshows().observe(getViewLifecycleOwner(), getTvshow);
        mainViewModel.setTvshow();

        tvAdapter = new TvAdapter(getActivity());
        tvAdapter.notifyDataSetChanged();

        showRecyclerList();
        showLoading(true);
        recyclerView.setHasFixedSize(true);
    }

    private void showRecyclerList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(tvAdapter);
    }

    public final Observer<ArrayList<Tvshow>> getTvshow = new Observer<ArrayList<Tvshow>>() {
        @Override
        public void onChanged(ArrayList<Tvshow> tvshows) {
            if (tvshows != null) {
                tvAdapter.setListTv(tvshows);
                showRecyclerList();
                showLoading(false);
            } else {
                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
