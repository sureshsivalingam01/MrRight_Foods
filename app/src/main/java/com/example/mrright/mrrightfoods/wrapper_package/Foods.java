package com.example.mrright.mrrightfoods.wrapper_package;

/**
 * Created by Mr.Right on 04-12-2018.
 */

public class Foods {
    int food_id;
    String name = "",type = "";
    double price;
    boolean flag;

    public Foods(int food_id, String name, String type, double price){
        this.food_id = food_id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
