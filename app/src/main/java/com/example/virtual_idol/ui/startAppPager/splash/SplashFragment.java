package com.example.virtual_idol.ui.startAppPager.splash;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.virtual_idol.act.PagerHandler;
import com.example.virtual_idol.components.LOG;
import com.example.virtual_idol.databinding.FragmentSplashBinding;

public class SplashFragment extends Fragment implements LOG {

    private SplashViewModel splashViewModel;
    private FragmentSplashBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);

        binding = FragmentSplashBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        PagerHandler getPageCount = PagerHandler.getInstance();

        Log.d(TAG, "onCreateView: " + getPageCount.getCurrentPage());

        return root;
    }

}