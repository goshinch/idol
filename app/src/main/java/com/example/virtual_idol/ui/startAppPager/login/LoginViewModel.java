package com.example.virtual_idol.ui.startAppPager.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.virtual_idol.R;
import com.example.virtual_idol.components.LOG;
import com.example.virtual_idol.firebase.CallbackException;
import com.example.virtual_idol.firebase.CallbackSignUpSuccess;
import com.example.virtual_idol.firebase.FirebaseRepository;
import com.example.virtual_idol.ui.startAppPager.login.email_pwd_state.EmailPasswordStateCheck;
import com.example.virtual_idol.ui.startAppPager.login.login.model.*;

public class LoginViewModel extends AndroidViewModel implements LOG, EmailPasswordStateCheck {
    private FirebaseRepository firebaseRepository;

    //에디트 텍스트 규칙 체크 모델
    private MutableLiveData<LoginFormState> loginFormStateMutableLiveData = new MutableLiveData<>();
    //로그인 성공시 true
    private MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
    //로그인 실패시 에러 반환
    private MutableLiveData<String> loginFailed = new MutableLiveData<>();

    LiveData<LoginFormState> getLoginFormStateLiveData() {return loginFormStateMutableLiveData;}
    LiveData<Boolean> getLoginSuccess() {return loginSuccess;}
    LiveData<String> getLoginFailed() {return loginFailed;}

    public void setLoginSuccess() {loginSuccess.setValue(false);}
    public void setLoginFailed() {loginFailed.setValue(null);}

    public LoginViewModel(@NonNull Application application) {
        super(application);
        firebaseRepository = new FirebaseRepository(application);
    }

    //에디트 텍스트 규칙 체크
    public void loginDataChanged(String userId, String password) {
        if (!EmailPasswordStateCheck.isUserValid(userId)) {
            //이메일 규칙 체크
            loginFormStateMutableLiveData.setValue(new LoginFormState(R.string.invalid_email, null));
        } else if (!EmailPasswordStateCheck.isPasswordValid(password)) {
            //비밀번호 규칙 체크
            loginFormStateMutableLiveData.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            //모두 규칙에 맞을시
            loginFormStateMutableLiveData.setValue(new LoginFormState(true));
        }
    }

    public void logIn(String email, String password) {
        //파이어베이스 로그인 정보 확인
        firebaseRepository.logIn(email, password);
        //로그인 성공시
        firebaseRepository.setCallbackSignUpSuccess(new CallbackSignUpSuccess() {
            @Override
            public void signUpSuccess() {
                loginSuccess.setValue(true);
            }
        });
        //로그인 실패시
        firebaseRepository.setCallbackException(new CallbackException() {
            @Override
            public void signUpException(String exception) {
                loginFailed.setValue(exception);
            }
        });
    }
}