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
import android.view.Window;

import com.example.virtual_idol.act.systembar.BarInsets;
import com.example.virtual_idol.act.PagerHandler;
import com.example.virtual_idol.act.systembar.SystemBarsControllerCompat;
import com.example.virtual_idol.components.LOG;
import com.example.virtual_idol.databinding.FragmentSplashBinding;

public class SplashFragment extends Fragment implements LOG, BarInsets{

    private SplashViewModel splashViewModel;
    private FragmentSplashBinding binding;
    private PagerHandler getPageHandle;
    private SystemBarsControllerCompat systemBarsControllerCompat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        binding = FragmentSplashBinding.inflate(inflater, container, false);

        //페이저 핸들러
        getPageHandle = PagerHandler.getInstance();

        binding.nextLogin.setOnClickListener(onClickListener);
        binding.progressBar.setProgress(30);

        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        systemBarsControllerCompat = SystemBarsControllerCompat.getIstance();
        systemBarsController(systemBarsControllerCompat.windowController(getActivity().getWindow()));
//        getPageHandle.pagerChange(1);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getPageHandle.pagerChange(1);
        }
    };
}