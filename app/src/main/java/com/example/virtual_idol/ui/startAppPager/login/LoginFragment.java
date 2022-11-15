package com.example.virtual_idol.ui.startAppPager.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.virtual_idol.MainActivity;
import com.example.virtual_idol.R;
import com.example.virtual_idol.act.PagerHandler;
import com.example.virtual_idol.act.Preferences;
import com.example.virtual_idol.act.systembar.BarInsets;
import com.example.virtual_idol.act.systembar.SystemBarsControllerCompat;
import com.example.virtual_idol.databinding.FragmentLoginBinding;
import com.example.virtual_idol.components.LOG;
import com.example.virtual_idol.ui.startAppPager.login.login.model.LoginFormState;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment implements LOG, BarInsets {

    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;
    private Preferences preferences;
    private PagerHandler getPageHandle;
    private SystemBarsControllerCompat systemBarsControllerCompat;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this,
                (ViewModelProvider.Factory) ViewModelProvider
                        .AndroidViewModelFactory
                        .getInstance(getActivity().getApplication()))
                .get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        //자동 로그인
        binding.autoLogin.addOnCheckedStateChangedListener(onCheckedAutoLogin);
        //아이디 저장
        binding.saveId.addOnCheckedStateChangedListener(onCheckedSaveId);

        //체크박스 상태 및 계정 아이디, 비밀번호 저장
        preferences = Preferences.getInstance();
        preferences.setTable(getContext(), "check");

        binding.saveId.setChecked(preferences.getBooleanValue("saveId"));
        binding.autoLogin.setChecked(preferences.getBooleanValue("autoLogin"));

        if (preferences.getBooleanValue("autoLogin")) {
            binding.emailOrId.setText(preferences.getStringValue("autoLoginId"));
            binding.password.setText(preferences.getStringValue("autoLoginPassword"));
            loginViewModel.logIn(binding.emailOrId.getText().toString(), binding.password.getText().toString());
        }

        if (preferences.getBooleanValue("saveId")) {
            binding.emailOrId.setText(preferences.getStringValue("saveIdString"));
        }

        //페이지 핸들러 인스턴스
        getPageHandle = PagerHandler.getInstance();

        //네이버 이메일로 로그인
        binding.naverbtn.setOnClickListener(onClickListener);
        //구글 이메일로 로그인
        binding.googlebtn.setOnClickListener(onClickListener);

        //스플레시 페이지로 이동
        binding.loginBackpage.setOnClickListener(onClickListener);

        //로그인
        binding.loginOk.setOnClickListener(onClickListener);

        //회원가입
        binding.signup.setOnClickListener(onClickListener);

        //에디트 텍스트 입력후 규칙 체크
        binding.emailOrId.addTextChangedListener(afterTextChangedListener);
        binding.password.addTextChangedListener(afterTextChangedListener);

        loginViewModel.getLoginSuccess().observe((LifecycleOwner) getContext(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    loginViewModel.setLoginSuccess();
                    Intent mainIntent = new Intent(getContext(), MainActivity.class);
                    startActivity(mainIntent);
                    getActivity().finish();
                }
            }
        });
        loginViewModel.getLoginFailed().observe((LifecycleOwner) getContext(), new Observer<String>() {
            @Override
            public void onChanged(String e) {
                if (e == null) return;
                binding.passwordLayoutLogin.setHelperText(e);
                loginViewModel.setLoginFailed();
            }
        });
        loginViewModel.getLoginFormStateLiveData().observe((LifecycleOwner) getContext(), new Observer<LoginFormState>() {
            @Override
            public void onChanged(LoginFormState loginFormState) {
                if (loginFormState == null) return;
                binding.loginOk.setCheckable(loginFormState.isDataValid());
                editStateSet(binding.emailLayoutLogin, loginFormState.getEmailError());
                editStateSet(binding.passwordLayoutLogin, loginFormState.getPasswordError());
            }
        });

        binding.loginLayout.setOnClickListener(onClickListener);


        View root = binding.getRoot();
        return root;
    }

    private void hideKeyboard()
    {
        if (getActivity() != null && getActivity().getCurrentFocus() != null)
        {
            // 프래그먼트기 때문에 getActivity() 사용
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void editStateSet(TextInputLayout layout, Integer err) {
        if (err != null) {
            layout.setError(getContext().getString(err));
        } else {
            layout.setError(null);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //하단바
        systemBarsControllerCompat = SystemBarsControllerCompat.getIstance();
        systemBarsController(systemBarsControllerCompat.windowController(getActivity().getWindow()));
    }

    @Override
    public void systemBarsController(WindowInsetsControllerCompat controller) {
        controller.show(NAVIGATION_BARS);
    }

    TextWatcher afterTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            loginViewModel.loginDataChanged(
                    binding.emailOrId.getText().toString(),
                    binding.password.getText().toString());
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

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.signup:
                    getPageHandle.setPagerCount(3);
                    getPageHandle.setAdapterPager();
                    getPageHandle.pagerChange(2);
                    break;
                case R.id.naverbtn:
                case R.id.googlebtn:
                    Log.d(TAG, "onClick: " + view);
                    break;
                case R.id.login_ok:
                    loginViewModel.logIn(binding.emailOrId.getText().toString(), binding.password.getText().toString());
                    break;
                case R.id.login_backpage:
                    getPageHandle.pagerChange(-1);
                    break;
                case R.id.login_layout:
                    hideKeyboard();
                    break;
            }
        }
    };

    @Override
    public void onStop() {
        if (preferences.getBooleanValue("saveId"))
            preferences.saveString("saveIdString", binding.emailOrId.getText().toString());
        if (preferences.getBooleanValue("autoLogin")) {
            preferences.saveString("autoLoginId", binding.emailOrId.getText().toString());
            preferences.saveString("autoLoginPassword", binding.password.getText().toString());
        }
        super.onStop();
    }
}