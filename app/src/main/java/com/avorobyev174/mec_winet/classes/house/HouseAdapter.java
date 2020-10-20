package com.avorobyev174.mec_winet.classes.house;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.avorobyev174.mec_winet.R;

import java.util.ArrayList;
import java.util.List;

public class HouseAdapter extends ArrayAdapter<House> {
    private LayoutInflater inflater;
    private List<House> houseList = new ArrayList<>();
    private Context context;
    private Activity activity;
    private HouseAdapter houseAdapter;


    public HouseAdapter(@NonNull Context context, int resource, List<House> houseList, LayoutInflater inflater, Activity activity) {
        super(context, resource, houseList);
        this.inflater = inflater;
        this.houseList = houseList;
        this.context = context;
        this.activity = activity;
        this.houseAdapter = this;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        House house = houseList.get(position);
        convertView = inflater.inflate(R.layout.house_list_item_view, null, false);

        final ViewHolder viewHolder = new ViewHolder(convertView, house, houseAdapter);
        convertView.setTag(viewHolder);

        return convertView;
    }

    private class ViewHolder {
        public TextView textView;
        public ImageButton deleteButton;
        private House house;
        private HouseAdapter houseAdapter;

        private ViewHolder(View rootView, House house, HouseAdapter houseAdapter) {
            this.houseAdapter = houseAdapter;
            this.house = house;

            textView = rootView.findViewById(R.id.houses_list_item_text);
            deleteButton = rootView.findViewById(R.id.deleteButton);

            textView.setText(house.getFullStreetName());

            deleteButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        // pointer goes down
                        ((ImageView) view).setColorFilter(context.getResources().getColor(R.color.red),
                                PorterDuff.Mode.SRC_ATOP);
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        // pointer goes up
                        ((ImageView) view).setColorFilter(context.getResources().getColor(R.color.light_grey),
                                PorterDuff.Mode.SRC_ATOP);
                    }
                    return false;
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteHouse(view);
                }
            });
        }

        private void deleteHouse(View view) {
            HouseDeleteDialog houseDeleteDialog = new HouseDeleteDialog(activity, house, houseAdapter, houseList);
            houseDeleteDialog.show();
        }
    }
}

