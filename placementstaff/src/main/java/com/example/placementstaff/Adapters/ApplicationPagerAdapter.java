package com.example.placementstaff.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.placementstaff.Fragments.AcceptedFragment;
import com.example.placementstaff.Fragments.PendingFragment;
import com.example.placementstaff.Fragments.PlacementDetailsFragment;
import com.example.placementstaff.Fragments.RejectedFragment;
import com.example.placementstaff.Fragments.StudentDetailsFragment;

public class ApplicationPagerAdapter  extends FragmentPagerAdapter {
    public ApplicationPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                StudentDetailsFragment studentDetailsFragment = new StudentDetailsFragment();
                return studentDetailsFragment;
            case 1:
                PlacementDetailsFragment placementDetailsFragment = new PlacementDetailsFragment();
                return placementDetailsFragment;
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
                return "STUDENT DETAILS";
            case 1:
                return "PLACEMENT DETAILS";
            default:
                return null;
        }
    }
}
