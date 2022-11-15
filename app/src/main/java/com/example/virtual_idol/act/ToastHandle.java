package com.example.virtual_idol.act;

public class ToastHandle {

    private static ToastHandle instance;
    public static ToastHandle getInstance() {
      if (instance == null)
          instance = new ToastHandle();
      return instance;
    }


}
