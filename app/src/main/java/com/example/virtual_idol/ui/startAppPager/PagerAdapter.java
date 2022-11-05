package com.example.virtual_idol.ui.startAppPager;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.virtual_idol.components.LOG;
import com.example.virtual_idol.ui.startAppPager.login.LoginFragment;
import com.example.virtual_idol.ui.startAppPager.splash.SplashFragment;

public class PagerAdapter extends FragmentStateAdapter implements LOG {
    public int pagePo;
    private Fragment fragment;

    public PagerAdapter(FragmentManager fm, Lifecycle lifecycle) {
        super(fm, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                Log.d(TAG, "createFragment: "+position);
                pagePo = position;
                fragment = new SplashFragment();
                break;
            case 1:
                pagePo = position;
                fragment = new LoginFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

