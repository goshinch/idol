package com.example.virtual_idol.ui.my.menu.list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.virtual_idol.R;
import com.example.virtual_idol.components.LOG;
import com.example.virtual_idol.ui.my.menu.model.MenuItemModel;

import java.util.ArrayList;

public class MenuListAdapter extends BaseAdapter implements LOG {
    private ArrayList<MenuItemModel> menuList = new ArrayList<>();
    private Context context;

    public MenuListAdapter(Context context) {
        this.context = context;
    }

    public void setMenuList(ArrayList<MenuItemModel> menuList) {
        for (MenuItemModel i:menuList) {
            Log.d(TAG, "setMenuList: "+ i.getTitle());
        }
        this.menuList = menuList;
    }

    public void addMenuList(MenuItemModel item) {
        menuList.add(item);
    }

    public void changeItem(int i, MenuItemModel item) {
        menuList.set(i,item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount: "+ menuList.size());
        return menuList.size();
    }

    @Override
    public Object getItem(int i) {
        return menuList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MenuItemModel itemModel = menuList.get(i);
        Log.d(TAG, "getView: "+ itemModel.getTitle());
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.my_menu_item, viewGroup, false);
        }

        TextView title = view.findViewById(R.id.title);
        TextView sub_title = view.findViewById(R.id.sub_title);

        title.setText(itemModel.getTitle());
        sub_title.setText(itemModel.getSubTitle());

        return view;
    }
}
