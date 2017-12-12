package com.example.anumeha.billsplit1;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import java.util.*;
import android.view.*;

import com.example.anumeha.billsplit1.R;

public class MainActivity extends Activity {

    ListView listview;
    Button Addbutton;
    EditText GetValue;
    EditText GetValue1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listView1);
        Addbutton = (Button) findViewById(R.id.button1);
        GetValue = (EditText) findViewById(R.id.editText1);
        GetValue1 = (EditText) findViewById(R.id.editText2);

        final ArrayList<Item> ItemListelements=new ArrayList<Item>();
        final CustomAdapter adapter = new CustomAdapter(this,ItemListelements);
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setEmptyView(findViewById(R.id.empty));
        listview.setAdapter(adapter);
        Addbutton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                Item new_item=new Item(GetValue.getText().toString(),Integer.parseInt(GetValue1.getText().toString()));
                ItemListelements.add(new_item);
                adapter.notifyDataSetChanged();
                GetValue.setText("");
                GetValue1.setText("");

            }
        });
        listview.setAdapter(adapter);
    }
}