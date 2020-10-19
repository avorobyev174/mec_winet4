package com.avorobyev174.mec_winet.classes.house;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HouseAdapter extends ArrayAdapter<House> {
    private LayoutInflater inflater;
    private List<House> houseList = new ArrayList<>();
    private Context context;


    public HouseAdapter(@NonNull Context context, int resource, List<House> houseList, LayoutInflater inflater) {
        super(context, resource, houseList);
        this.inflater = inflater;
        this.houseList = houseList;
        this.context = context;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        House house = houseList.get(position);
        convertView = inflater.inflate(android.R.layout.simple_list_item_1, null, false);
        final ViewHolder viewHolder = new ViewHolder(convertView, house);
        convertView.setTag(viewHolder);

        return convertView;
    }

    private class ViewHolder {
        public TextView textView;

        private ViewHolder(View rootView, House house) {
            textView = (TextView) rootView.findViewById(android.R.id.text1);
            textView.setText(house.getFullStreetName());
        }
    }
}
