package com.example.placement2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.placement2.Fragments.DetailsFragment;
import com.example.placement2.Fragments.RequirementsFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter {
    public SectionPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DetailsFragment detailsFragment = new DetailsFragment();
                return detailsFragment;
            case 1:
                RequirementsFragment requirementsFragment = new RequirementsFragment();
                return requirementsFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    //title  for fragments
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "COMPANY DETAILS";
            case 1:
                return "REQUIREMENT DETAILS";

            default:
                return null;
        }
    }
}
