package com.developer.tonny.design.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.developer.tonny.design.Fragments.FirstFragment;
import com.developer.tonny.design.Fragments.SecondFragment;
import com.developer.tonny.design.Fragments.ThirdFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    // Cambio de tabs
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            case 2:
                return new ThirdFragment();
            default:
                return null;
        }
    }

    // Numero de tabs
    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
