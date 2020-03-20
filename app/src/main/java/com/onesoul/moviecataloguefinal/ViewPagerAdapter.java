package com.onesoul.moviecataloguefinal;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.onesoul.moviecataloguefinal.favorite.FavoriteFragment;
import com.onesoul.moviecataloguefinal.favorite.MovieFavoriteFragment;
import com.onesoul.moviecataloguefinal.favorite.TvFavoriteFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final FavoriteFragment mContext;

    public ViewPagerAdapter(FavoriteFragment context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_movie,
            R.string.tab_tvshow
    };

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MovieFavoriteFragment();
                break;
            case 1:
                fragment = new TvFavoriteFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }


}
