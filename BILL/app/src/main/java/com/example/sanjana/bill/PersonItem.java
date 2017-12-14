package com.example.sanjana.bill;

import java.io.Serializable;

/**
 * Created by anumeha on 13/12/17.
 */

public class PersonItem implements Serializable {
    Double quantity;
    String name;
    String fooditem;

    public PersonItem(Double quantity, String name,String fooditem){
        this.fooditem=fooditem;
        this.quantity=quantity;
        this.name=name;
    }
    public String getPName(){
        return name;
    }

    public Double getQuantity(){
        return quantity;

    }
    public String getFooditem(){
        return fooditem;
    }
}
