package com.avorobyev174.mec_winet.classes.winet;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.avorobyev174.mec_winet.R;
import com.avorobyev174.mec_winet.WinetInfoActivity;
import com.avorobyev174.mec_winet.classes.floor.Floor;
import com.avorobyev174.mec_winet.classes.house.House;
import com.avorobyev174.mec_winet.classes.section.Section;
import com.avorobyev174.mec_winet.classes.vestibule.Vestibule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class WinetActivity extends AppCompatActivity {
    private List<Winet> winetList;
    private WinetAdapter adapter;
    private ListView winetListView;
    private House house;
    private Section section;
    private Floor floor;
    private Vestibule vestibule;
    private TextView infoBar;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winet_activity);
        init();
    }

    private void init() {
        infoBar = findViewById(R.id.info_bar);
        Bundle arguments = getIntent().getExtras();
        this.vestibule = (Vestibule) arguments.getSerializable(Vestibule.class.getSimpleName());
        this.floor = vestibule.getFloor();
        infoBar.setText(house.getFullStreetName() + " → " + section.getShortNumber() + " → " + floor.getShortNumber() + " → " + vestibule.getShortNumber());
        sharedPreferences = getSharedPreferences("winet-storage", MODE_PRIVATE);

        fillWinetList();

        winetListView = findViewById(R.id.winet_list_view);

        adapter = new WinetAdapter(this, android.R.layout.simple_list_item_1, winetList, getLayoutInflater());
        winetListView.setAdapter(adapter);

        winetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Winet winet = winetList.get(i);
                Intent intent = new Intent(WinetActivity.this, WinetInfoActivity.class);
                intent.putExtra(Winet.class.getSimpleName(), winet);
                //intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) winetList);
                startActivity(intent);
            }
        });
    }

    public void createWinetOnClick(View view) {
        String type = "301";
        String serNumber = "0";
        Winet winet = new Winet(serNumber, type, house, section, floor, vestibule);
        winetList.add(winet);

        sharedPreferencesEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(winetList);

        sharedPreferencesEditor.putString("winetList", json);
        sharedPreferencesEditor.apply();
        adapter.notifyDataSetChanged();
    }

    private void fillWinetList() {
        winetList = new ArrayList<>();
        String serializedObject = sharedPreferences.getString("winetList", null);
        //Log.e("123", serializedObject);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Winet>>(){}.getType();
            winetList = gson.fromJson(serializedObject, type);
        }
        //        winetList.add(new Winet("123456", "401", house, section, floor, vestibule));
        //        winetList.add(new Winet("123457", "303", house, section, floor, vestibule));
    }
}
