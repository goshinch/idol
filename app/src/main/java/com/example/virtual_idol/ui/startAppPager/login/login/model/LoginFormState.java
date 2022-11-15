package com.example.virtual_idol.ui.startAppPager.login.login.model;

import androidx.annotation.Nullable;

public class LoginFormState {
    @Nullable
    private Integer emailError;
    @Nullable
    private Integer passwordError;
    private boolean isDataValid;

    public LoginFormState(@Nullable Integer emailError, @Nullable Integer passwordError) {
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    public LoginFormState(boolean isDataValid) {
        this.isDataValid = isDataValid;
        this.emailError = null;
        this.passwordError = null;
    }

    @Nullable
    public Integer getEmailError() {
        return emailError;
    }

    @Nullable
    public Integer getPasswordError() {
        return passwordError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }
}
