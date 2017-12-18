package com.example.sanjana.bill;

import android.app.Activity;
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

import java.util.ArrayList;

/**
 * Created by anumeha on 12/12/17.
 */

public class SecondActivity extends Activity implements OnItemSelectedListener {

    ArrayList<String> list;
    ArrayList<PersonItem> personlist;
    ArrayList<PersonItem> personlist1;
    String itemname;
    public String HELLO = "Mha";
    private Spinner spinner;
    Double quantity;
    NumberPicker np;
    EditText name;
    TextView ev;
    String person;
    ListView listview;
    CustomAdapterPerson CustomAdapterperson;
    final String nums[] = {"Select Fraction", "0", "0.34", "0.25", "0.5", "1", "2", "3"};
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
        ev = (TextView) findViewById(R.id.textView);
        ev.setText(args.getCharSequence("NAME"));
        np = (NumberPicker) findViewById(R.id.np);
        np.setMaxValue(nums.length - 1);
        np.setMinValue(0);
        np.setWrapSelectorWheel(false);
        np.setDisplayedValues(nums);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected value from picker
                quantity = Double.valueOf(nums[newVal]);

            }
        });
        personlist = new ArrayList<PersonItem>();
        personlist1 = new ArrayList<PersonItem>();
        listview = findViewById(R.id.listquant);
        CustomAdapterperson = new CustomAdapterPerson(this, personlist);
        listview.setEmptyView(findViewById(R.id.empty));
        listview.setAdapter(CustomAdapterperson);
        name = (EditText) findViewById(R.id.personname);

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
        Toast.makeText(getApplicationContext(), "Selected: " + quantity, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Name: " + person, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Selected: " + itemname, Toast.LENGTH_LONG).show();
        if(person.equals(HELLO)) {
            PersonItem item = new PersonItem(quantity,"", itemname);
            personlist.add(item);

        }
        else {
            PersonItem item = new PersonItem(quantity,person, itemname);
            personlist.add(item);
            personlist1.add(item);
        }
        CustomAdapterperson.notifyDataSetChanged();
        HELLO=new String(person);

    }
}