package com.example.sanjana.bill;


import java.io.Serializable;

public class Item implements Serializable{
    public  String ItemName;
    public double Price;

    public Item(String ItemName, double Price) {
        this.ItemName = ItemName;
        this.Price = Price;
    }
    public String getItem() {
        return ItemName;
    }

    public double getPrice() {
        return Price;
    }
}