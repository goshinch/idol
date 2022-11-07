package com.example.virtual_idol;

import com.example.virtual_idol.act.PagerHandler;
import com.example.virtual_idol.components.LOG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.virtual_idol.databinding.ActivityStartAppBinding;
import com.example.virtual_idol.ui.startAppPager.PagerAdapter;


public class StartApp extends AppCompatActivity implements LOG {

    private ActivityStartAppBinding binding;
    private PagerAdapter adapter;
    private ViewPager2 pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Handle the splash screen transition.
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);

        // Keep the splash screen visible for this Activity
//        splashScreen.setKeepOnScreenCondition(() -> true );

        binding = ActivityStartAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pager = binding.pager;
//        setContentView(R.layout.activity_start_app);
//        adapter = new PagerAdapter(getSupportFragmentManager(), getLifecycle());
        adapter = PagerAdapter.getInstance(getSupportFragmentManager(), getLifecycle());
        PagerHandler pagerHandler = PagerHandler.getInstance();
        pagerHandler.setAdapter(adapter);
        pagerHandler.setPagerCount(2);
        pagerHandler.setViewpager(pager);
        pagerHandler.setAdapterPager();

    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0)
            super.onBackPressed();
        else pager.setCurrentItem(0);
    }
}