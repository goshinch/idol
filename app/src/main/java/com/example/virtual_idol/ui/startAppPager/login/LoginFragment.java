package com.example.virtual_idol.ui.startAppPager.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.virtual_idol.MainActivity;
import com.example.virtual_idol.act.PagerHandler;
import com.example.virtual_idol.act.Preferences;
import com.example.virtual_idol.databinding.FragmentLoginBinding;
import com.example.virtual_idol.components.LOG;
import com.google.android.material.checkbox.MaterialCheckBox;

public class LoginFragment extends Fragment implements LOG {

    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;
    private Preferences preferences;
    private PagerHandler getPageCount;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        binding.autoLogin.addOnCheckedStateChangedListener(onCheckedAutoLogin);
        binding.saveId.addOnCheckedStateChangedListener(onCheckedSaveId);

        preferences = Preferences.getInstance();
        preferences.setTable(getContext(), "check");

        binding.naverbtn.setOnClickListener(onClickNaver);
        binding.googlebtn.setOnClickListener(onClickGoogle);

        binding.backpage.setOnClickListener(onClickBackpage);

        binding.signIn.setOnClickListener(onClickSignIn);

        getPageCount = PagerHandler.getInstance();

        Log.d(TAG, "onCreateView: " + getPageCount.getCurrentPage());

        View root = binding.getRoot();
        return root;
    }

    MaterialCheckBox.OnCheckedStateChangedListener onCheckedAutoLogin = new MaterialCheckBox.OnCheckedStateChangedListener() {
        @Override
        public void onCheckedStateChangedListener(@NonNull MaterialCheckBox checkBox, int state) {
            preferences.saveBoolean("autoLogin",checkBox.isChecked());
            Log.d(TAG, "preferences test: "+preferences.getBooleanValue("autoLogin"));
        }
    };

    MaterialCheckBox.OnCheckedStateChangedListener onCheckedSaveId = new MaterialCheckBox.OnCheckedStateChangedListener() {
        @Override
        public void onCheckedStateChangedListener(@NonNull MaterialCheckBox checkBox, int state) {
            preferences.saveBoolean("saveId",checkBox.isChecked());
            Log.d(TAG, "preferences test: "+preferences.getBooleanValue("saveId"));
        }
    };

    View.OnClickListener onClickNaver = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + view);
        }
    };

    View.OnClickListener onClickGoogle = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + view);
        }
    };

    View.OnClickListener onClickSignIn = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + view);
            Intent mainIntent = new Intent(getContext(), MainActivity.class);
            startActivity(mainIntent);
            getActivity().finish();
        }
    };

    View.OnClickListener onClickBackpage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getPageCount.pagerBackChange();
        }
    };
}