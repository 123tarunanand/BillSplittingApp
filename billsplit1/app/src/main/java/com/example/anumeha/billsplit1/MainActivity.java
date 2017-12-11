package com.example.anumeha.billsplit1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

/** Note that here we are inheriting ListActivity class instead of Activity class **/
public class MainActivity extends ListActivity {
    /** Items entered by the user is stored in this ArrayList variable */
    ArrayList<String> list = new ArrayList<String>();
    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> adapter;
    HashMap<String,Integer> cartlist= new HashMap<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** Setting a custom layout for the list activity */
        setContentView(R.layout.activity_main);
        /** Reference to the button of the layout main.xml */
        Button btn = (Button) findViewById(R.id.btnAdd);
        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        /** Defining a click event listener for the button "Add" */
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.txtItem);
                list.add(edit.getText().toString());
                edit.setText(" ");
                adapter.notifyDataSetChanged();
            }
        });
        setListAdapter(adapter);

    }
    public void Additemprice(ArrayList<String> arraylist){
        for(int i=0;i<arraylist.size();i++){
            String item=arraylist.get(i);
            String array1[]=item.split(" ");
            cartlist.put(array1[0],Integer.parseInt(array1[1]));
            EditText edit = (EditText) findViewById(R.id.txtItem);
            edit.setText(cartlist.get(array1[0]));
        }

    }
}