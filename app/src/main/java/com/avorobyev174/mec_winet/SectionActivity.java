package com.avorobyev174.mec_winet;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.avorobyev174.mec_winet.classes.house.House;
import com.avorobyev174.mec_winet.classes.section.Section;
import com.avorobyev174.mec_winet.classes.section.SectionAdapter;

import java.util.ArrayList;
import java.util.List;


public class SectionActivity extends AppCompatActivity {
    private List<Section> sectionList;
    private SectionAdapter adapter;
    private ListView sectionListView;
    private House house;
    private TextView infoBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sections_activity);
        init();
    }

    private void init() {
        infoBar = findViewById(R.id.info_bar);
        Bundle arguments = getIntent().getExtras();
        House house = (House) arguments.getSerializable(House.class.getSimpleName());
        this.house = house;
        infoBar.setText(house.getFullStreetName());

        sectionList = new ArrayList<>();
        sectionList.add(new Section(1, house));
        sectionList.add(new Section(2, house));

        sectionListView = findViewById(R.id.sections_list_view);

        adapter = new SectionAdapter(this, android.R.layout.simple_list_item_1, sectionList, getLayoutInflater());
        sectionListView.setAdapter(adapter);

        sectionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Section section = sectionList.get(i);
                Intent intent = new Intent(SectionActivity.this, FloorActivity.class);
                intent.putExtra(Section.class.getSimpleName(), section);
                startActivity(intent);
            }
        });
    }
}
