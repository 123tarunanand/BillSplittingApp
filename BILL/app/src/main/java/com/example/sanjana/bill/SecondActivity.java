package com.example.sanjana.bill;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by anumeha on 12/12/17.
 */

public class SecondActivity extends Activity implements OnItemSelectedListener {

    ArrayList<String> list;
    ArrayList<PersonItem> personlist1;
    HashMap<String, ArrayList<PersonItemhash>> Calculation = new HashMap<>();

    String itemname;

    private Spinner spinner;
    Double quantity;
    NumberPicker np;
    EditText name;
    TextView ev;
    String person;
    String title;
    ListView listview;
    CustomAdapterPerson CustomAdapterperson;
    int n,p;
    HashMap<String,Double> Items;
    final String nums[] = {"0", "0.34", "0.25", "0.5", "1", "2", "3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("FOODLIST");
        ArrayList<Item> Foodlist = (ArrayList<Item>) args.getSerializable("ARRAYLIST");
        Items=(HashMap<String,Double>)args.getSerializable("HashList");
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        addItemsOnSpinner(Foodlist);
        ev = (TextView) findViewById(R.id.textView);
        title = args.getString("NAME");
        ev.setText(title);
        np = (NumberPicker) findViewById(R.id.np);
        np.setMaxValue(nums.length - 1);
        np.setMinValue(0);
        np.setValue(0);
        np.setWrapSelectorWheel(true);
        np.setDisplayedValues(nums);


        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected value from picker
                n=oldVal;
                p=newVal;
                quantity = Double.valueOf(nums[newVal]);

            }
        });

        personlist1 = new ArrayList<PersonItem>();
        listview = findViewById(R.id.listquant);
        CustomAdapterperson = new CustomAdapterPerson(this, personlist1);
        listview.setEmptyView(findViewById(R.id.empty));
        listview.setAdapter(CustomAdapterperson);
        name = (EditText) findViewById(R.id.personname);
        try
        {
            if(quantity.isNaN())
            {
                quantity = 0.0;
            }
        }
        catch( NullPointerException e)
        {
            quantity=  Double.valueOf(0);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        itemname = parent.getItemAtPosition(position).toString();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void addItemsOnSpinner(ArrayList<Item> Foodlist) {
        spinner = (Spinner) findViewById(R.id.spinner);
        list = new ArrayList<String>();
        for (int i = 0; i < Foodlist.size(); i++) {
            list.add(Foodlist.get(i).getItem());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void order(View v) {
        person = name.getText().toString();
        if(person.isEmpty())
        {
            AlertDialog a = new AlertDialog.Builder(this).create();
            a.setTitle("Error");
            a.setMessage("Enter the name of the person");
            a.show();
        }
        else
        {

        if (Calculation.containsKey(person)) {
            ArrayList<PersonItemhash> list = Calculation.get(person);

            for(int i = 0; i < list.size() ;i++) {
                PersonItemhash k = list.get(i);

               if (k.getFooditem().equals(itemname)) {
                    AlertDialog a = new AlertDialog.Builder(this).create();
                    a.setTitle("Error");
                    a.setMessage("Item has already been entered");
                    a.show();
                   return;
                }
            }
            PersonItemhash item1 = new PersonItemhash(quantity, itemname);
            list.add(item1);
            Calculation.put(person, list);


        } else {


            PersonItemhash item1 = new PersonItemhash(quantity, itemname);
            ArrayList<PersonItemhash> var = new ArrayList<PersonItemhash>();
            var.add(item1);
            Calculation.put(person, var);
        }



        Toast.makeText(getApplicationContext(), "Selected: " + quantity, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Name: " + person, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Selected: " + itemname, Toast.LENGTH_LONG).show();
        PersonItem item = new PersonItem(quantity, person, itemname);
        personlist1.add(item);
        CustomAdapterperson.notifyDataSetChanged();}


    }

    public void submitorder(View v) {
        if(personlist1.isEmpty())
        {
            AlertDialog a = new AlertDialog.Builder(this).create();
            a.setTitle("Error");
            a.setMessage("Please enter atleast one person");
            a.show();
        }
        else
        {
        Intent intent = new Intent(getApplicationContext(), Thirdact.class);
        Bundle args = new Bundle();
        Set<String> keys = Calculation.keySet();
        HashMap<String,Double>FinalDisplay=new HashMap<>();
        for (String key : keys) {
            ArrayList<PersonItemhash> list = Calculation.get(key);
            Double FinalPrice=0.0;
            for (int i = 0; i < list.size(); i++)
            {

                PersonItemhash item=list.get(i);
                Double cost=Items.get(item.getFooditem());
                FinalPrice+=cost*item.getQuantity();

            }
            FinalDisplay.put(key,FinalPrice);
        }
        args.putSerializable("FinalDisplay",(Serializable)FinalDisplay);
        args.putString("NAME",title);
        intent.putExtra("List",args);
        startActivity(intent);}
    }
}