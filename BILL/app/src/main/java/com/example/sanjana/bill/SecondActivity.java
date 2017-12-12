package com.example.sanjana.bill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by anumeha on 12/12/17.
 */

public class SecondActivity extends Activity implements OnItemSelectedListener {

    ArrayList<String> list;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("FOODLIST");
        ArrayList<Item> Foodlist = (ArrayList<Item>) args.getSerializable("ARRAYLIST");
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        addItemsOnSpinner(Foodlist);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public void addItemsOnSpinner(ArrayList<Item> Foodlist){
        spinner=(Spinner)findViewById(R.id.spinner);
        list = new ArrayList<String>();
        for(int i=0;i<Foodlist.size();i++){
            list.add(Foodlist.get(i).getItem());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

}