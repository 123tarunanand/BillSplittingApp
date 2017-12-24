package com.example.sanjana.bill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Thirdact extends AppCompatActivity {
    TextView head;
    String title;
    ListView listView;
    ArrayList<Item> ItemModelList;
    HashMap<String,Double> FinalDisplay;
    CustomAdapterwithoutdelete customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirdact);
        head = (TextView)findViewById(R.id.textView3);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("List");
        title = args.getString("NAME");
        head.setText(title);
        listView = (ListView)findViewById(R.id.litsy);
        FinalDisplay=(HashMap<String,Double>)args.getSerializable("FinalDisplay");
        ItemModelList = new ArrayList<Item>();
        customAdapter = new CustomAdapterwithoutdelete(this, ItemModelList);
        listView.setAdapter(customAdapter);
        Set<String> keys = FinalDisplay.keySet();
        for(String key : keys)
        {
            Item it = new Item(key, FinalDisplay.get(key));
            ItemModelList.add(it);
            customAdapter.notifyDataSetChanged();
        }


    }
}
