package com.avorobyev174.mec_winet;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.avorobyev174.mec_winet.classes.floor.Floor;
import com.avorobyev174.mec_winet.classes.floor.FloorAdapter;
import com.avorobyev174.mec_winet.classes.house.House;
import com.avorobyev174.mec_winet.classes.section.Section;

import java.util.ArrayList;
import java.util.List;


public class FloorActivity extends AppCompatActivity {
    private List<Floor> floorList;
    private FloorAdapter adapter;
    private ListView floorListView;
    private House house;
    private Section section;
    private TextView infoBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floors_activity);
        init();
    }

    private void init() {
        infoBar = findViewById(R.id.info_bar);
        Bundle arguments = getIntent().getExtras();
        Section section = (Section) arguments.getSerializable(Section.class.getSimpleName());
        this.section = section;
        this.house = section.getHouse();
        infoBar.setText(house.getFullStreetName() + " → " + section.getShortNumber());

        floorList = new ArrayList<>();
        floorList.add(new Floor(1, house, section));
        floorList.add(new Floor(2, house, section));

        floorListView = findViewById(R.id.floors_list_view);

        adapter = new FloorAdapter(this, android.R.layout.simple_list_item_1, floorList, getLayoutInflater());
        floorListView.setAdapter(adapter);
        floorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Floor floor = floorList.get(i);
                Intent intent = new Intent(FloorActivity.this, VestibuleActivity.class);
                intent.putExtra(Floor.class.getSimpleName(), floor);
                startActivity(intent);
            }
        });
    }
}
