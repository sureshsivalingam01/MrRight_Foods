package com.example.mrright.mrrightfoods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mrright.mrrightfoods.fragments_package.ChineseFoods;
import com.example.mrright.mrrightfoods.fragments_package.IndianFoods;
import com.example.mrright.mrrightfoods.fragments_package.ItalianFoods;
import com.example.mrright.mrrightfoods.sqlite_package.MrRightFoodsDB;
import com.example.mrright.mrrightfoods.wrapper_package.Foods;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MrRightFoodsDB mrRightFoodsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mrRightFoodsDB = new MrRightFoodsDB(MainActivity.this);
        foodInsertion();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            private String[] title = {"Indian", "Chinese", "Italian"};

            @Override
            public int getCount() {
                return title.length;
            }

            @Override
            public Fragment getItem(int i) {
                switch (i) {
                    case 0:
                        return new IndianFoods();
                    case 1:
                        return new ChineseFoods();
                    case 2:
                        return new ItalianFoods();
                    default:
                        return null;
                }
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.cartCount);
        View view = menuItem.getActionView();
        TextView txtCartCount = (TextView) view.findViewById(R.id.txtCartCount);
        RelativeLayout relativeCart = (RelativeLayout) view.findViewById(R.id.relativeCart);
        txtCartCount.setText(String.valueOf(mrRightFoodsDB.cartcount()));
        relativeCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),CartActivity.class));
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void foodInsertion() {
        String names[] = {"Briyani", "Dosa", "Chapti", "Spinach Noodles", "Fried Mashi", "Dumplings", "Panzenella", "Pasta", "Margherita Pizza"};
        String types[] = {"Indian", "Indian", "Indian", "Chinese", "Chinese", "Chinese", "Italian", "Italian", "Italian"};
        double prices[] = {200.0, 100.0, 100.0, 150.0, 90.0, 60.0, 200.0, 100.0, 200.0};
        ArrayList<Foods> foodsArrayList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Foods foods = new Foods(i, names[i], types[i], prices[i]);
            foodsArrayList.add(foods);
        }
        mrRightFoodsDB.insertFoods(foodsArrayList);
    }
}
