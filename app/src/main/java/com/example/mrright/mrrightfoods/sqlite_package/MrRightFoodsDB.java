package com.example.mrright.mrrightfoods.sqlite_package;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mrright.mrrightfoods.wrapper_package.CartFoods;
import com.example.mrright.mrrightfoods.wrapper_package.Foods;

import java.util.ArrayList;

/**
 * Created by Mr.Right on 05-12-2018.
 */

public class MrRightFoodsDB extends SQLiteOpenHelper {
    public static String sDataBaseName = "mrrightfoods.db";
    public static String sFoodsTableName = "foods";
    public static String sCartTableName = "cart";
    public static String food_id = "food_id";
    public static String type = "type";
    public static String name = "name";
    public static String price = "price";
    public static String cart_id = "cart_id";
    public static String count = "count";

    public MrRightFoodsDB(Context context) {
        super(context, sDataBaseName, null, 1);
        //this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase mrrightdb) {
        mrrightdb.execSQL("CREATE TABLE " + sFoodsTableName + "(" + food_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + type + " TEXT, " + name + " TEXT, " + price + " REAL)");
        mrrightdb.execSQL("CREATE TABLE " + sCartTableName + "(" + cart_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + food_id + " INTEGER, " + count + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase mrrightdb, int oldVersion, int newVersion) {
        mrrightdb.execSQL("DROP TABLE IF EXISTS " + sFoodsTableName);
        mrrightdb.execSQL("DROP TABLE IF EXISTS " + sCartTableName);
        onCreate(mrrightdb);
    }

    private boolean isTableExist(String tableName) {
        SQLiteDatabase mrrightdb = this.getWritableDatabase();
        boolean tableexist = false;
        Cursor cursor = mrrightdb.rawQuery("SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = '" + tableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                tableexist = true;
            }
            cursor.close();
        }
        return tableexist;
    }

    private boolean checkTableData() {
        SQLiteDatabase mrrightdb = this.getWritableDatabase();
        boolean dataexist = false;
        if (isTableExist(sFoodsTableName)) {
            Cursor cursor = mrrightdb.rawQuery("SELECT * FROM foods;", null);
            if (cursor.getCount() != 9) {
                dataexist = true;
            }
            cursor.close();
        }
        return dataexist;
    }

    public void insertFoods(ArrayList<Foods> foodsArrayList) {
        SQLiteDatabase mrrightdb = this.getWritableDatabase();
        if (checkTableData()) {
            mrrightdb.execSQL("DROP TABLE IF EXISTS " + sFoodsTableName);
            mrrightdb.execSQL("DROP TABLE IF EXISTS " + sCartTableName);
            onCreate(mrrightdb);
            for (int i = 0; i < foodsArrayList.size(); i++) {
                ContentValues values = new ContentValues();
                values.put(type, foodsArrayList.get(i).getType());
                values.put(name, foodsArrayList.get(i).getName());
                values.put(price, foodsArrayList.get(i).getPrice());
                mrrightdb.insert(sFoodsTableName, null, values);
            }
        }
    }

    public ArrayList<Foods> getFoods(String uitype) {
        ArrayList<Foods> foodsArrayList = new ArrayList<>();
        SQLiteDatabase mrrightdb = this.getWritableDatabase();
        Cursor cursor = mrrightdb.query(sFoodsTableName, new String[]{food_id, type, name, price}, type + " = ? ", new String[]{uitype}, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int idValue = cursor.getInt(cursor.getColumnIndex(food_id));
            String nameValue = cursor.getString(cursor.getColumnIndex(name));
            String typeValue = cursor.getString(cursor.getColumnIndex(type));
            double priceValue = cursor.getDouble(cursor.getColumnIndex(price));
            Foods foods = new Foods(idValue, nameValue, typeValue, priceValue);
            foodsArrayList.add(foods);
            cursor.moveToNext();
        }
        return foodsArrayList;
    }

    public ArrayList<Foods> getproduct(int uiid) {
        ArrayList<Foods> foodsArrayList = new ArrayList<>();
        SQLiteDatabase mrrightdb = this.getWritableDatabase();
        try {
            Cursor cursor = mrrightdb.rawQuery("SELECT * FROM foods WHERE id = " + uiid + " ", null);
            if (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String type = cursor.getString(cursor.getColumnIndex("type"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                double price = cursor.getDouble(cursor.getColumnIndex("double"));
                Foods foods = new Foods(id, type, name, price);
                foodsArrayList.add(foods);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodsArrayList;
    }


    public String addToCart(String addFoodid) {
        SQLiteDatabase mrrightdb = this.getWritableDatabase();
        Cursor cursor = mrrightdb.query(sCartTableName, new String[]{cart_id, food_id, count}, food_id + " = ? ", new String[]{addFoodid}, null, null, null, null);
        if (cursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put(food_id, addFoodid);
            values.put(count, 1);
            mrrightdb.insert(sCartTableName, null, values);
            return "Added to Cart";
        } else {
            cursor.moveToNext();
            int preCount = cursor.getInt(cursor.getColumnIndex(food_id));
            ContentValues values = new ContentValues();
            values.put(count, (preCount + 1));
            long result = mrrightdb.update(sCartTableName, values, food_id + " = ? ", new String[]{addFoodid});
            return "Quantity Increased";
        }
    }

    public int cartcount() {
        SQLiteDatabase mrrightdb = this.getWritableDatabase();
        Cursor cursor = mrrightdb.query(sCartTableName, new String[]{cart_id}, null, null, null, null, null);
        return cursor.getCount();
    }

    public double cartTotal() {
        SQLiteDatabase mrrightdb = this.getWritableDatabase();
        double priceValue = 0.0;
        Cursor cursor = mrrightdb.rawQuery("SELECT SUM(foods.price) as totalprice FROM foods,cart WHERE foods.food_id = cart.food_id;", null, null);
        if (cursor.moveToFirst()) {
            priceValue = cursor.getDouble(cursor.getColumnIndex("totalprice"));
        }
        return priceValue;
    }

    public ArrayList<CartFoods> cartFoods() {
        SQLiteDatabase mrrightdb = this.getWritableDatabase();
        ArrayList<CartFoods> cartFoodsArrayList = new ArrayList<>();
        Cursor cursor = mrrightdb.rawQuery("SELECT foods.food_id as food_id,foods.name as name,foods.price as price,cart.count as count FROM foods,cart WHERE foods.food_id = cart.food_id;", null, null);
        while (cursor.moveToNext()) {
            int idValue = cursor.getInt(cursor.getColumnIndex(food_id));
            String nameValue = cursor.getString(cursor.getColumnIndex(name));
            double priceValue = cursor.getDouble(cursor.getColumnIndex(price));
            int qtyValue = cursor.getInt(cursor.getColumnIndex(count));
            CartFoods cartFoods = new CartFoods(idValue, nameValue, priceValue, qtyValue);
            cartFoodsArrayList.add(cartFoods);
        }
        return cartFoodsArrayList;
    }
}
