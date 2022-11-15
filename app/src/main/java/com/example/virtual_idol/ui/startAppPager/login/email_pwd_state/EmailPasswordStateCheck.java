package com.example.virtual_idol.ui.startAppPager.login.email_pwd_state;

import android.util.Patterns;
import java.util.Objects;

public interface EmailPasswordStateCheck {

    // A placeholder username validation check
    static boolean isUserValid(String userId) {
        if (userId.trim().length() < 5) {
            return false;
        }
        if (userId.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(userId).matches();
        }
//        else {
//            return !userId.trim().isEmpty();
//        }
        return false;
    }

    // A placeholder password validation check
    static boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 10 && passwordValidCheck(password);
    }

    static boolean isPasswordCheck(String password, String passwordOk) {
        return Objects.equals(password, passwordOk);
    }

    static boolean passwordValidCheck(String password) {
        int numCnt = 0;
        for (int i = 0; i < password.length(); i++) {
            if ('0' <= password.charAt(i) && password.charAt(i) <= '9') {
                numCnt++;
            }
        }
        if (numCnt >= 3) return true;
        else return false;
    }


}
