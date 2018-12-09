package com.example.mrright.mrrightfoods;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mrright.mrrightfoods.adapter_package.CartAdapter;
import com.example.mrright.mrrightfoods.adapter_package.FoodsAdapter;
import com.example.mrright.mrrightfoods.sqlite_package.MrRightFoodsDB;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recycviewCart;
    private EditText edtCartTotal;
    private MrRightFoodsDB mrRightFoodsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recycviewCart = (RecyclerView) findViewById(R.id.recycviewCart);
        recycviewCart.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        edtCartTotal = (EditText) findViewById(R.id.edtCartTotal);
        mrRightFoodsDB = new MrRightFoodsDB(getApplicationContext());
        edtCartTotal.setText(String.valueOf(mrRightFoodsDB.cartTotal()));
        CartAdapter cartAdapter = new CartAdapter(CartActivity.this, mrRightFoodsDB.cartFoods());
        recycviewCart.setAdapter(cartAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CartActivity.this,MainActivity.class));
        finish();
    }
}
