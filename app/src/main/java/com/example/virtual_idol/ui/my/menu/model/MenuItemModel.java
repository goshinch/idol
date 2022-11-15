package com.example.virtual_idol.ui.my.menu.model;

public class MenuItemModel {
    private String subTitle, title;

    public MenuItemModel(String subTitle, String title) {
        this.subTitle = subTitle;
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getTitle() {
        return title;
    }
}
