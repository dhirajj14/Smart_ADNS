package com.example.smartadns;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    int mtabsCount;

    public FragmentPagerAdapter(FragmentManager fm, int tabsCount) {
        super(fm);
        this.mtabsCount = tabsCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MapFragment tab1 = new MapFragment();
                return tab1;
            case 1:
                contactDisplayFragment tab2 = new contactDisplayFragment();
                return tab2;
            case 2:
                contactUpdateFragment tab3 = new contactUpdateFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mtabsCount;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
