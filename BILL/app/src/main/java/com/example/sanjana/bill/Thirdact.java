package com.example.sanjana.bill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

public class Thirdact extends AppCompatActivity {
    TextView head;
    String title;
    ListView listView;
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

    }
}
