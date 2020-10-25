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
import com.avorobyev174.mec_winet.classes.vestibule.Vestibule;
import com.avorobyev174.mec_winet.classes.vestibule.VestibuleAdapter;

import java.util.ArrayList;
import java.util.List;


public class VestibuleActivity extends AppCompatActivity {
    private List<Vestibule> vestList;
    private VestibuleAdapter adapter;
    private ListView vestListView;
    private House house;
    private Section section;
    private Floor floor;
    private TextView infoBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vestibule_activity);
        init();
    }

    private void init() {
        infoBar = findViewById(R.id.info_bar);
        Bundle arguments = getIntent().getExtras();
        Floor floor = (Floor) arguments.getSerializable(Floor.class.getSimpleName());
        this.floor = floor;
        this.section = floor.getSection();
        this.house = section.getHouse();
        infoBar.setText(house.getFullStreetName() + " → " + section.getShortNumber() + " → " + floor.getShortNumber());

        vestList = new ArrayList<>();
        vestList.add(new Vestibule(1, house, section, floor));
        vestList.add(new Vestibule(2, house, section, floor));

        vestListView = findViewById(R.id.vestibule_list_view);

        adapter = new VestibuleAdapter(this, android.R.layout.simple_list_item_1, vestList, getLayoutInflater());
        vestListView.setAdapter(adapter);

        vestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Vestibule vestibule = vestList.get(i);
                Intent intent = new Intent(VestibuleActivity.this, WinetActivity.class);
                intent.putExtra(Vestibule.class.getSimpleName(), vestibule);
                startActivity(intent);
            }
        });
    }
}
