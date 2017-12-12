package com.example.sanjana.bill;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import java.util.*;
import android.view.*;


public class MainActivity extends Activity {
    ListView listView;
    EditText editTextView;
    EditText editTextView1;
    ArrayList<Item> ItemModelList;
    CustomAdapter customAdapter;
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
        String name = editTextView.getText().toString();
        int i=Integer.parseInt(editTextView1.getText().toString());
        Item item = new Item(name,i);
        ItemModelList.add(item);
        customAdapter.notifyDataSetChanged();
        editTextView.setText("");
        editTextView1.setText("");
        }
    }

