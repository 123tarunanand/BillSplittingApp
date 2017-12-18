package com.example.sanjana.bill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class ThirdActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       // setContentView(R.layout.third_activity);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("List");


    }}


