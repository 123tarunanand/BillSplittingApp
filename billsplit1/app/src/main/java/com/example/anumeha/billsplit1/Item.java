package com.example.sanjana.codesprint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import java.util.*;
import android.view.*;

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


