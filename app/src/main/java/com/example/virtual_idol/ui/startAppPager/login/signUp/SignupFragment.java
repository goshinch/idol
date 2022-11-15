package com.example.virtual_idol.ui.startAppPager.login.signUp;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.virtual_idol.R;
import com.example.virtual_idol.act.EditStateSet;
import com.example.virtual_idol.act.PagerHandler;
import com.example.virtual_idol.components.LOG;
import com.example.virtual_idol.databinding.FragmentSignUpBinding;
import com.example.virtual_idol.ui.startAppPager.login.signUp.sign.model.SignFormState;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupFragment extends Fragment implements LOG {
    //뷰모델
    private SignupViewModel signUpViewModel;
    private FragmentSignUpBinding binding;
    //페이저 핸들러
    private PagerHandler getPageHandle;
    //파이어베이스
    private FirebaseAuth mAuth;

    //로그인 페이지는 새로 아이디를 생성할때마다 불필요한 메모리를 줄이기 위해 싱글톤으로 생성
    private static SignupFragment signupFragment = new SignupFragment();
    public static SignupFragment newInstance() {
        if (signupFragment == null)
            signupFragment = new SignupFragment();
        return signupFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpViewModel = new ViewModelProvider(this,
                (ViewModelProvider.Factory) ViewModelProvider
                        .AndroidViewModelFactory
                        .getInstance(getActivity().getApplication()))
                .get(SignupViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);

        //파이어베이스 인스턴스
        mAuth = FirebaseAuth.getInstance();

        //페이저 핸들러
        getPageHandle = PagerHandler.getInstance();
        //페이저 핸들러 카운터 set
        getPageHandle.setPagerCount(3);

        //회원가입 버튼
        binding.signupOk.setOnClickListener(onClickListener);
        //뒤로가기 버튼
        binding.signupBackpage.setOnClickListener(onClickListener);

        //에디트 텍스트 규칙 체크
        binding.userId.addTextChangedListener(afterTextChangedListener);
        binding.password.addTextChangedListener(afterTextChangedListener);
        binding.passwordOk.addTextChangedListener(afterTextChangedListener);

        //회원가입 성공시
        signUpViewModel.getSignOkCheck().observe((LifecycleOwner) getContext(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    binding.userId.setText(null);
                    binding.password.setText(null);
                    binding.passwordOk.setText(null);

                    signUpViewModel.setSignUpOkCheck();
                    // 이전 페이지
                    getPageHandle.pagerChange(-1);
                    // 전체 페이지 수정
                    getPageHandle.setPagerCount(2);
                    // 페이지 어댑터에 재설정
                    getPageHandle.setAdapterPager();
                }
            }
        });

        //회원가입 실패시 실패 에러 메시지
        signUpViewModel.getSignException().observe((LifecycleOwner) getContext(), new Observer<String>() {
            @Override
            public void onChanged(String e) {
                if (e != null) {
                    binding.idLayout.setError(e);
                    signUpViewModel.setSignNullException();
                }
            }
        });

        //아이디, 비밀번호, 비밀번호 확인 규칙 체크
        signUpViewModel.getSignFormState().observe((LifecycleOwner) getContext(), new Observer<SignFormState>() {
            @Override
            public void onChanged(SignFormState signFormState) {
                if (signFormState == null) return;
                binding.signupOk.setClickable(signFormState.isDataValid());

                editStateSet(binding.idLayout, signFormState.getIdError());
                editStateSet(binding.passwordLayout, signFormState.getPasswordError());
                editStateSet(binding.passwordOkLayout, signFormState.getPasswordOkError());
            }
        });

        binding.signupLayout.setOnClickListener(onClickListener);

        View root = binding.getRoot();
        return root;
    }
    public void editStateSet(TextInputLayout layout, Integer err) {
        if (err != null) {
            layout.setError(getContext().getString(err));
        } else {
            layout.setError(null);
        }
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

    TextWatcher afterTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        //에디트 텍스트 값이 체인지 된후 호출출
        @Override
       public void afterTextChanged(Editable editable) {
            signUpViewModel.signDataChanged(
                    binding.userId.getText().toString(),
                    binding.password.getText().toString(),
                    binding.passwordOk.getText().toString()
            );
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.signup_backpage:
                    // 이전 페이지
                    getPageHandle.pagerChange(-1);
                    break;
                case R.id.signup_ok:
                    //회원 가입 클릭시 파이어베이스에서 가입이 가능한지 체크후 가입 또는 오류 메시지
                    signUpViewModel.signUp(binding.userId.getText().toString(),
                            binding.password.getText().toString(),
                            binding.nickName.getText().toString());
                    break;
                case R.id.signup_layout:
                    hideKeyboard();
                    break;
            }
        }
    };
}