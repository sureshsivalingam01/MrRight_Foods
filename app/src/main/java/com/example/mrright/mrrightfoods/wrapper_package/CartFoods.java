package com.example.mrright.mrrightfoods.wrapper_package;

public class CartFoods {
    public int food_id, qty;
    public String name = "";
    public double price;

    public CartFoods(int food_id, String name, double price, int qty) {
        this.food_id = food_id;
        this.qty = qty;
        this.name = name;
        this.price = price;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
