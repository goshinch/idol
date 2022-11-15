package com.example.virtual_idol.ui.startAppPager.login.signUp;

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
import com.example.virtual_idol.ui.startAppPager.login.email.pwd.state.EmailPasswordStateCheck;
import com.example.virtual_idol.ui.startAppPager.login.signUp.sign.model.SignFormState;

public class SignupViewModel extends AndroidViewModel implements LOG, EmailPasswordStateCheck {
    //에디트 텍스트 규칙 체크 모델
    private MutableLiveData<SignFormState> signFormState = new MutableLiveData<>();
    //회원 가입 오류 메시지
    private MutableLiveData<String> signException = new MutableLiveData<>();
    //회원 가입이 성공했는지 체크
    private MutableLiveData<Boolean> signUpOkCheck = new MutableLiveData<>();

    //파이어베이스 레지스터 클래스, 여기서 파이어베이스 명령 관리함
    private FirebaseRepository firebaseRepository;

    //mvvm에 맞춰쓰기 위해 AndroidViewModel을 불가피하게 쓰게됨
    //context를 넘겨도 되었겠지만 context를 무분별하게 던져 주는건 좋지 않은 코드라고 함
    //왜 그런지는 후에 자세히 공부해보자
    public SignupViewModel(@NonNull Application application) {
        super(application);
        firebaseRepository = new FirebaseRepository(application);
    }

    LiveData<Boolean> getSignOkCheck() {return signUpOkCheck;}
    LiveData<SignFormState> getSignFormState() {
        return signFormState;
    }
    LiveData<String> getSignException() {return signException;}

    public void setSignNullException() {signException.setValue(null);}
    public void setSignUpOkCheck() {signUpOkCheck.setValue(false);}

    //에디트 텍스트 규칙 체크
    public void signDataChanged(String userId, String password, String passwordOk) {
        if (!EmailPasswordStateCheck.isUserValid(userId)) {
            //이메일 규칙 체크
            signFormState.setValue(new SignFormState(R.string.invalid_email, null, null));
        } else if (!EmailPasswordStateCheck.isPasswordValid(password)) {
            //비밀번호 규칙 체크
            signFormState.setValue(new SignFormState(null, R.string.invalid_password, null));
        } else if (!EmailPasswordStateCheck.isPasswordCheck(password,passwordOk)) {
            //비밀번호 확인
            signFormState.setValue(new SignFormState(null, null, R.string.invalid_password_ok));
        } else {
            //모든 규칙이 맞았을때
            signFormState.setValue(new SignFormState(true));
        }
    }

    //파이어베이스 가입정보 등록
    public void signUp(String userId, String password, String name) {
        //가입 정보 등록
        firebaseRepository.signUp(userId, password, name);
        //가입 성공시 콜백
        firebaseRepository.setCallbackSignUpSuccess(new CallbackSignUpSuccess() {
            @Override
            public void signUpSuccess() {
                signUpOkCheck.setValue(true);
            }
        });
        //가입 실패시 콜백
        firebaseRepository.setCallbackException(new CallbackException() {
            @Override
            public void signUpException(String exception) {
                signException.setValue(exception);
            }
        });
    }
}