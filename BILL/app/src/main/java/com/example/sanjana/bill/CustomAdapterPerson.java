package com.example.sanjana.bill;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by anumeha on 17/12/17.
 */

public class CustomAdapterPerson extends BaseAdapter{
    Context context;
    ArrayList<PersonItem> itemperlist;
    public CustomAdapterPerson(Context context, ArrayList<PersonItem> modelList) {
        this.context = context;
        this.itemperlist = modelList;
    }
    @Override
    public int getCount() {
        return itemperlist.size();
    }
    @Override
    public Object getItem(int position) {
        return itemperlist.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = null;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.item_person, null);
            TextView person = (TextView) convertView.findViewById(R.id.PerName);
            TextView item = (TextView) convertView.findViewById(R.id.Item);
            TextView quantity = (TextView) convertView.findViewById(R.id.quant);
            ImageView imgRemove = (ImageView) convertView.findViewById(R.id.imgRemove);
            PersonItem m = itemperlist.get(position);
            person.setText(m.getPName());
            item.setText(m.getFooditem());

           try{ quantity.setText(String.valueOf(m.getQuantity()));}
           catch(NullPointerException e)
           {
               quantity.setText(String.valueOf(0.0));
           }

            imgRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemperlist.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
        return convertView;
    }
}

