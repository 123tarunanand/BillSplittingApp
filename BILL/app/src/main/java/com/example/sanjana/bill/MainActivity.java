package com.example.sanjana.bill;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends Activity {
    ListView listView;
    EditText editTextView;
    EditText ev;
    EditText editTextView1;
    ArrayList<Item> ItemModelList;
    CustomAdapter customAdapter;
    Button sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        editTextView = (EditText) findViewById(R.id.editTextView);
        editTextView1 = (EditText) findViewById(R.id.editTextView1);
        ItemModelList = new ArrayList<Item>();
        customAdapter = new CustomAdapter(this, ItemModelList);
        listView.setEmptyView(findViewById(R.id.empty));
        listView.setAdapter(customAdapter);
    }

    public void addValue(View v) {
        try {
            String name = editTextView.getText().toString();
            double i = Double.parseDouble(editTextView1.getText().toString());
            Item item = new Item(name, i);
            ItemModelList.add(item);
            customAdapter.notifyDataSetChanged();
            editTextView.setText("");
            editTextView1.setText("");
        }
        catch (NumberFormatException e)
        {
            editTextView.setText("");
            editTextView1.setText("");
            AlertDialog a = new AlertDialog.Builder(this).create();
            a.setTitle("Error");
            a.setMessage("Incorrect entry");
            a.show();
        }
    }
    public void submit(View v){

        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        Bundle args = new Bundle();
        ev = (EditText) findViewById(R.id.editText);
        if(ev.getText().toString().isEmpty())
        {
            AlertDialog a = new AlertDialog.Builder(this).create();
            a.setTitle("Error");
            a.setMessage("Please enter an event name");
            a.show();
        }
        else
        {

            args.putSerializable("ARRAYLIST",(Serializable)ItemModelList);
            args.putString("NAME",ev.getText().toString());
            intent.putExtra("FOODLIST",args);
            startActivity(intent);
        }



    }
}
