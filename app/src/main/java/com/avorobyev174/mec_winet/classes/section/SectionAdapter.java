package com.avorobyev174.mec_winet.classes.section;

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

public class SectionAdapter extends ArrayAdapter<Section> {
    private LayoutInflater inflater;
    private List<Section> sectionList = new ArrayList<>();
    private Context context;


    public SectionAdapter(@NonNull Context context, int resource, List<Section> sectionList, LayoutInflater inflater) {
        super(context, resource, sectionList);
        this.inflater = inflater;
        this.sectionList = sectionList;
        this.context = context;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Section section = sectionList.get(position);
        convertView = inflater.inflate(android.R.layout.simple_list_item_1, null, false);
        final ViewHolder viewHolder = new ViewHolder(convertView, section);
        convertView.setTag(viewHolder);

        return convertView;
    }

    private class ViewHolder {
        public TextView textView;

        private ViewHolder(View rootView, Section section) {
            textView = (TextView) rootView.findViewById(android.R.id.text1);
            textView.setText(section.getFullNumber());
        }
    }
}
