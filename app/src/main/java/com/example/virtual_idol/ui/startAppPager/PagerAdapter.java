package com.example.virtual_idol.ui.startAppPager;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.virtual_idol.components.LOG;
import com.example.virtual_idol.ui.startAppPager.login.LoginFragment;
import com.example.virtual_idol.ui.startAppPager.login.signUp.SignupFragment;
import com.example.virtual_idol.ui.startAppPager.splash.SplashFragment;

public class PagerAdapter extends FragmentStateAdapter implements LOG {
    //페이지 카운트 변수
    public int setPagerCount;
    //프래그먼트 클래스 변수
    private Fragment fragment;
    //싱글톤으로 생성된 회원가입 프래그먼트 클래스
    private SignupFragment signUp;
    //페이지 어댑터 클래스 변수
    private static PagerAdapter pagerAdapter;

    //페이지 어댑터를 싱글톤으로 인스턴스 생성
    public static PagerAdapter getInstance(FragmentManager fm, Lifecycle lifecycle) {
        if (pagerAdapter == null)
            pagerAdapter = new PagerAdapter(fm, lifecycle);
        return pagerAdapter;
    }

    //페이저 어댑터 생성자
    private PagerAdapter(FragmentManager fm, Lifecycle lifecycle) {
        super(fm, lifecycle);
    }

    //프래그먼트 인스턴스를 생성
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                fragment = new SplashFragment();
                break;
            case 1:
                fragment = new LoginFragment();
                break;
            default:
                signUp = SignupFragment.newInstance();
                fragment = signUp;
                break;
        }
        return fragment;
    }

    //페이저 카운트
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+ setPagerCount);
        return setPagerCount;
    }
}

