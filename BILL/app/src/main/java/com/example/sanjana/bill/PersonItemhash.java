package com.example.sanjana.bill;

/**
 * Created by Sanjana on 18/12/17.
 */

import java.io.Serializable;


public class PersonItemhash implements Serializable {
    Double quantity;
    String fooditem;

    public PersonItemhash(Double quantity,String fooditem){
        this.fooditem=fooditem;
        this.quantity=quantity;
        ;
    }

    public Double getQuantity(){
        return quantity;

    }
    public String getFooditem(){
        return fooditem;
    }
}