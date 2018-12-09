package com.example.mrright.mrrightfoods.fragments_package;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mrright.mrrightfoods.R;
import com.example.mrright.mrrightfoods.adapter_package.FoodsAdapter;
import com.example.mrright.mrrightfoods.sqlite_package.MrRightFoodsDB;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ItalianFoods extends Fragment {

    private View view;
    private RecyclerView italianfoodsRecyclerView;
    private MrRightFoodsDB mrRightFoodsDB;


    public ItalianFoods() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_italian_foods, container, false);

        mrRightFoodsDB = new MrRightFoodsDB(getContext());

        italianfoodsRecyclerView = (RecyclerView) view.findViewById(R.id.foodsListRecyclerView);
        italianfoodsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        italianfoodsRecyclerView.setAdapter(new FoodsAdapter(getActivity(), mrRightFoodsDB.getFoods("Italian")));
        return view;
    }
}
