package com.example.placementstaff;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.placementstaff.Fragments.AcceptedFragment;
import com.example.placementstaff.Fragments.PendingFragment;
import com.example.placementstaff.Fragments.RejectedFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter {
    public SectionPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PendingFragment pendingFragment = new PendingFragment();
                return pendingFragment;
            case 1:
                AcceptedFragment acceptedFragment = new AcceptedFragment();
                return acceptedFragment;
            case 2:
                RejectedFragment rejectedFragment = new RejectedFragment();
                return rejectedFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    //title  for fragments
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "PENDING";
            case 1:
                return "ACCEPTED";
            case 2:
                return "REJECTED";
            default:
                return null;
        }
    }
}
