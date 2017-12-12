package com.example.sanjana.bill;


public class Item {
    public  String ItemName;
    public int Price;

    public Item(String ItemName, Integer Price) {
        this.ItemName = ItemName;
        this.Price = Price;
    }
    public String getItem() {
        return ItemName;
    }

    public Integer getPrice() {
        return Price;
    }
}

