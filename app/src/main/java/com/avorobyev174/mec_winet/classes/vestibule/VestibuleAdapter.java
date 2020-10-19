package com.avorobyev174.mec_winet.classes.vestibule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.avorobyev174.mec_winet.classes.floor.Floor;

import java.util.ArrayList;
import java.util.List;

public class VestibuleAdapter extends ArrayAdapter<Vestibule> {
    private LayoutInflater inflater;
    private List<Vestibule> vestibuleList = new ArrayList<>();
    private Context context;


    public VestibuleAdapter(@NonNull Context context, int resource, List<Vestibule> vestibuleList, LayoutInflater inflater) {
        super(context, resource, vestibuleList);
        this.inflater = inflater;
        this.vestibuleList = vestibuleList;
        this.context = context;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Vestibule vestibule = vestibuleList.get(position);
        convertView = inflater.inflate(android.R.layout.simple_list_item_1, null, false);
        final ViewHolder viewHolder = new ViewHolder(convertView, vestibule);
        convertView.setTag(viewHolder);

        return convertView;
    }

    private class ViewHolder {
        public TextView textView;

        private ViewHolder(View rootView, Vestibule vestibule) {
            textView = (TextView) rootView.findViewById(android.R.id.text1);
            textView.setText(vestibule.getFullNumber());
        }
    }
}
