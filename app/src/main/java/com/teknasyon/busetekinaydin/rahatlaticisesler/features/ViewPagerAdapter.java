package com.teknasyon.busetekinaydin.rahatlaticisesler.features;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.teknasyon.busetekinaydin.rahatlaticisesler.features.FavoriteSounds.FavoriteSoundFragment;
import com.teknasyon.busetekinaydin.rahatlaticisesler.features.Category.CategorySoundsFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public static String KEY_TAB_TITLE_FAVORITE = "Favorilerim";
    public static String KEY_TAB_TITLE_LIBRARY = "Kitaplık";

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment selectedFragment;

        switch (position) {
            case 0:
                selectedFragment = FavoriteSoundFragment.newInstance();
                break;
            case 1:
                selectedFragment = CategorySoundsFragment.newInstance();
                break;
            default:
                return null;
        }

        return selectedFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence selectedTitle;

        switch (position) {
            case 0:
                selectedTitle = KEY_TAB_TITLE_FAVORITE;
                break;
            case 1:
                selectedTitle = KEY_TAB_TITLE_LIBRARY;
                break;
            default:
                return null;

        }
        return selectedTitle;
    }
}
