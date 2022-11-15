package com.example.virtual_idol.ui.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.virtual_idol.R;
import com.example.virtual_idol.databinding.FragmentMyBinding;
import com.example.virtual_idol.ui.my.menu.list.MenuListAdapter;

public class MyFragment extends Fragment {

    private FragmentMyBinding binding;
    private MenuListAdapter adapter;
    private MyViewModel myViewModel;

    private String my_email="", my_PhoneNumber="";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding = FragmentMyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ListView listView = root.findViewById(R.id.menu_item_list);
        adapter = new MenuListAdapter(getContext());
        adapter.setMenuList(myViewModel.MenuItemListSet(my_email, my_PhoneNumber));
        listView.setAdapter(adapter);

        return root;
    }

    private void listViewHeightSet(MenuListAdapter adapter, ListView listView) {
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount()));
        listView.setLayoutParams(params);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}