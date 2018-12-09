package com.example.mrright.mrrightfoods.adapter_package;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mrright.mrrightfoods.R;
import com.example.mrright.mrrightfoods.wrapper_package.CartFoods;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private ArrayList<CartFoods> cartFoodsArrayList;
    private Activity activity;

    public CartAdapter(Activity activity, ArrayList<CartFoods> cartFoodsArrayList) {
        this.activity = activity;
        this.cartFoodsArrayList = cartFoodsArrayList;
    }

    @Override
    public int getItemCount() {
        return cartFoodsArrayList.size();
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_foods_layout, viewGroup, false);
        return new CartViewHolder(view);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        public TextView txtID, txtName, txtPrice,txtTotal,txtQty;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            txtID = (TextView) itemView.findViewById(R.id.txtID);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtQty = (TextView) itemView.findViewById(R.id.txtQty);
            txtTotal = (TextView) itemView.findViewById(R.id.txtTotal);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder cartViewHolder, int i) {
        cartViewHolder.txtID.setText(String.valueOf((i+1)));
        cartViewHolder.txtName.setText(cartFoodsArrayList.get(i).getName());
        cartViewHolder.txtPrice.setText(String.valueOf(cartFoodsArrayList.get(i).getPrice()));
        cartViewHolder.txtQty.setText(String.valueOf(cartFoodsArrayList.get(i).getQty()));
        cartViewHolder.txtTotal.setText(String.valueOf((cartFoodsArrayList.get(i).getQty() * cartFoodsArrayList.get(i).getPrice())));
    }
}
