package com.example.mrright.mrrightfoods.adapter_package;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrright.mrrightfoods.R;
import com.example.mrright.mrrightfoods.sqlite_package.MrRightFoodsDB;
import com.example.mrright.mrrightfoods.wrapper_package.Foods;

import java.util.ArrayList;


public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder> {
    private Activity activity;
    private ArrayList<Foods> foodsArrayList;

    public FoodsAdapter(Activity activity, ArrayList<Foods> foodsArrayList) {
        this.activity = activity;
        this.foodsArrayList = foodsArrayList;
    }

    @Override
    public int getItemCount() {
        return foodsArrayList.size();
    }

    @NonNull
    @Override
    public FoodsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.foods_layout, viewGroup, false);
        return new FoodsViewHolder(view);
    }

    public class FoodsViewHolder extends RecyclerView.ViewHolder {
        public CardView cardview;
        public TextView txtName, txtPrice;
        public Button btnAdd;

        public FoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            btnAdd = (Button) itemView.findViewById(R.id.btnAdd);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodsViewHolder foodsViewHolder, final int i) {
        foodsViewHolder.txtName.setText(String.valueOf(foodsArrayList.get(i).getName()));
        foodsViewHolder.txtPrice.setText(String.valueOf(foodsArrayList.get(i).getPrice()));
        foodsViewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MrRightFoodsDB mrRightFoodsDB = new MrRightFoodsDB(activity);
                String response = mrRightFoodsDB.addToCart(String.valueOf(foodsArrayList.get(i).getFood_id()));
                activity.invalidateOptionsMenu();
                Toast.makeText(activity, response, Toast.LENGTH_LONG).show();
            }
        });
    }
}
