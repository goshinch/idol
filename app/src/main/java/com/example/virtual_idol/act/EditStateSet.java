package com.example.virtual_idol.act;

import android.app.Activity;

import com.google.android.material.textfield.TextInputLayout;

public class EditStateSet extends Activity {
    private static EditStateSet instance;
    public static EditStateSet getInstance() {
        if (instance == null)
            instance = new EditStateSet();
        return instance;
    }
    public void editStateSet(TextInputLayout layout, Integer err) {
        if (err != null) {
            layout.setError(getString(err));
        } else {
            layout.setError(null);
        }
    }
}
