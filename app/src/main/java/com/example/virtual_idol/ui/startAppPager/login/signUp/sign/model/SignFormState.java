package com.example.virtual_idol.ui.startAppPager.login.signUp.sign.model;

import androidx.annotation.Nullable;

public class SignFormState {
    @Nullable
    private Integer idError;
    @Nullable
    private Integer passwordError;
    @Nullable
    private Integer passwordOkError;
    private boolean isDataValid;

    public SignFormState(@Nullable Integer idError, @Nullable Integer passwordError, @Nullable Integer passwordOkError) {
        this.idError = idError;
        this.passwordError = passwordError;
        this.passwordOkError = passwordOkError;
        this.isDataValid = false;
    }

    public SignFormState(boolean isDataValid) {
        this.idError = null;
        this.passwordError = null;
        this.passwordOkError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    public Integer getIdError() {
        return idError;
    }

    @Nullable
    public Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    public Integer getPasswordOkError() {
        return passwordOkError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }
}

