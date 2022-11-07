package com.example.virtual_idol.ui.startAppPager.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.virtual_idol.MainActivity;
import com.example.virtual_idol.act.PagerHandler;
import com.example.virtual_idol.act.Preferences;
import com.example.virtual_idol.act.systembar.BarInsets;
import com.example.virtual_idol.act.systembar.SystemBarsControllerCompat;
import com.example.virtual_idol.databinding.FragmentLoginBinding;
import com.example.virtual_idol.components.LOG;
import com.google.android.material.checkbox.MaterialCheckBox;

public class LoginFragment extends Fragment implements LOG, BarInsets {

    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;
    private Preferences preferences;
    private PagerHandler getPageHandle;
    private SystemBarsControllerCompat systemBarsControllerCompat;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        binding.autoLogin.addOnCheckedStateChangedListener(onCheckedAutoLogin);
        binding.saveId.addOnCheckedStateChangedListener(onCheckedSaveId);

        preferences = Preferences.getInstance();
        preferences.setTable(getContext(), "check");

        getPageHandle = PagerHandler.getInstance();

        binding.naverbtn.setOnClickListener(onClickNaver);
        binding.googlebtn.setOnClickListener(onClickGoogle);

        binding.loginBackpage.setOnClickListener(onClickBackpage);

        binding.ok.setOnClickListener(onClickOk);

        binding.signup.setOnClickListener(onClickSiginup);

        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        systemBarsControllerCompat = SystemBarsControllerCompat.getIstance();
        systemBarsController(systemBarsControllerCompat.windowController(getActivity().getWindow()));
    }

    @Override
    public void systemBarsController(WindowInsetsControllerCompat controller) {
        controller.show(NAVIGATION_BARS);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

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

    View.OnClickListener onClickSiginup = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + view);
            getPageHandle.setPagerCount(3);
            getPageHandle.setAdapterPager();
            getPageHandle.pagerChange(2);
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

    View.OnClickListener onClickOk = new View.OnClickListener() {
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
            getPageHandle.pagerChange(-1);
        }
    };
}