package com.example.mrright.mrrightfoods.fragments_package;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mrright.mrrightfoods.R;
import com.example.mrright.mrrightfoods.adapter_package.FoodsAdapter;
import com.example.mrright.mrrightfoods.sqlite_package.MrRightFoodsDB;

public class IndianFoods extends Fragment {

    private View view;
    private RecyclerView indianFoodRecyclerView;
    private MrRightFoodsDB mrRightFoodsDB;
    private String s = "";

    public IndianFoods() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_indian_foods, container, false);

        mrRightFoodsDB = new MrRightFoodsDB(getContext());

        indianFoodRecyclerView = (RecyclerView) view.findViewById(R.id.foodsListRecyclerView);
        indianFoodRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        indianFoodRecyclerView.setAdapter(new FoodsAdapter(getActivity(), mrRightFoodsDB.getFoods("Indian")));
        return view;
    }

}
