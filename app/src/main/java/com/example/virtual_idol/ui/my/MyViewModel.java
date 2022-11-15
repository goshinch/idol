package com.example.virtual_idol.ui.my;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.virtual_idol.ui.my.menu.model.MenuItemModel;

import java.util.ArrayList;
import java.util.Arrays;

public class MyViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public ArrayList<MenuItemModel> MenuItemListSet(String my_email, String my_PhoneNumber) {
        return new ArrayList<MenuItemModel>(
                Arrays.asList(
                        new MenuItemModel("계정", my_email),
                        new MenuItemModel("전화번호", my_PhoneNumber),
                        new MenuItemModel("", "내 댓글"),
                        new MenuItemModel("", "내 게시글"),
                        new MenuItemModel("", "로그아웃"),
                        new MenuItemModel("", "탈퇴")
                )
        );
    }

    public LiveData<String> getText() {
        return mText;
    }
}