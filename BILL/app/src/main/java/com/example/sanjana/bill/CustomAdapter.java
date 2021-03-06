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
 * Created by Sanjana on 11/12/17.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Item> itemModelList;
    public CustomAdapter(Context context, ArrayList<Item> modelList) {
        this.context = context;
        this.itemModelList = modelList;
    }
    @Override
    public int getCount() {
        return itemModelList.size();
    }
    @Override
    public Object getItem(int position) {
        return itemModelList.get(position);
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
            convertView = mInflater.inflate(R.layout.item, null);
            TextView item = (TextView) convertView.findViewById(R.id.ItemName);
            TextView price = (TextView) convertView.findViewById(R.id.Price);
            ImageView imgRemove = (ImageView) convertView.findViewById(R.id.imgRemove);
            Item m = itemModelList.get(position);
            item.setText(m.getItem());
            price.setText(Double.toString(m.getPrice()));

            imgRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemModelList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
        return convertView;
    }
}