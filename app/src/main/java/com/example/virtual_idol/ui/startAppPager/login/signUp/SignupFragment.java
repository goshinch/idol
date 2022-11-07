package com.example.virtual_idol.ui.startAppPager.login.signUp;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.virtual_idol.act.PagerHandler;
import com.example.virtual_idol.databinding.FragmentSignUpBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SignupFragment extends Fragment {
    //뷰모델
    private SignupViewModel signUpViewModel;
    private FragmentSignUpBinding binding;
    //페이저 핸들러
    private PagerHandler getPageHandle;
    //파이어베이스
    private FirebaseAuth auth;

    //로그인 페이지는 새로 아이디를 생성할때마다 불필요한 메모리를 줄이기 위해 싱글톤으로 생성
    private static SignupFragment signupFragment = new SignupFragment();
    public static SignupFragment newInstance() {
        if (signupFragment == null)
            signupFragment = new SignupFragment();
        return signupFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        signUpViewModel = new ViewModelProvider(this).get(SignupViewModel.class);
        binding = FragmentSignUpBinding.inflate(inflater, container, false);

        //파이어베이스
        auth = FirebaseAuth.getInstance();

        //페이저 핸들러
        getPageHandle = PagerHandler.getInstance();
        //페이저 핸들러 카운터 set
        getPageHandle.setPagerCount(3);

        binding.ok.setOnClickListener(onClickOk);
        binding.signupBackpage.setOnClickListener(onClickBackpage);

        View root = binding.getRoot();
        return root;
    }

    View.OnClickListener onClickBackpage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getPageHandle.pagerChange(-1);
        }
    };

    View.OnClickListener onClickOk = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getPageHandle.pagerChange(-1);
            getPageHandle.setPagerCount(2);
            getPageHandle.setAdapterPager();
        }
    };

}