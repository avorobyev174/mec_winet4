package com.avorobyev174.mec_winet.classes.floor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.avorobyev174.mec_winet.classes.section.Section;

import java.util.ArrayList;
import java.util.List;

public class FloorAdapter extends ArrayAdapter<Floor> {
    private LayoutInflater inflater;
    private List<Floor> floorList = new ArrayList<>();
    private Context context;


    public FloorAdapter(@NonNull Context context, int resource, List<Floor> floorList, LayoutInflater inflater) {
        super(context, resource, floorList);
        this.inflater = inflater;
        this.floorList = floorList;
        this.context = context;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Floor floor = floorList.get(position);
        convertView = inflater.inflate(android.R.layout.simple_list_item_1, null, false);
        final ViewHolder viewHolder = new ViewHolder(convertView, floor);
        convertView.setTag(viewHolder);

        return convertView;
    }

    private class ViewHolder {
        public TextView textView;

        private ViewHolder(View rootView, Floor floor) {
            textView = (TextView) rootView.findViewById(android.R.id.text1);
            textView.setText(floor.getFullNumber());
        }
    }
}
