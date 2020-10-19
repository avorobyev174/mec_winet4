package com.avorobyev174.mec_winet.classes.api.house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avorobyev174.mec_winet.R;
import com.avorobyev174.mec_winet.SectionActivity;
import com.avorobyev174.mec_winet.classes.api.ApiClient;
import com.avorobyev174.mec_winet.classes.api.HouseCreateDialog;
import com.avorobyev174.mec_winet.classes.house.House;
import com.avorobyev174.mec_winet.classes.house.HouseAdapter;


import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HousesActivity extends AppCompatActivity {
    private ListView housesListView;
    private HouseAdapter adapter;
//    private Toolbar toolbar;
    private List<House> houseList;
    private TextView houseTextView;
    private Button addHouseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.houses_activity);
        //Toolbar toolbar = findViewById(R.id.tool_bar);
        addHouseButton = findViewById(R.id.delete_house_button);
        //setSupportActionBar(toolbar);

        houseList = new ArrayList<>();
        getHouses();

        housesListView = findViewById(R.id.houses_list_view);
        houseTextView = findViewById(R.id.info_bar);
        houseTextView.setText(R.string.houses_list);

        housesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                House house = houseList.get(i);
                Intent intent = new Intent(HousesActivity.this, SectionActivity.class);
                intent.putExtra(House.class.getSimpleName(), house);
                startActivity(intent);
            }
        });

    }

    public void getHouses() {
        Call<ResponseHouseInfo> messages = ApiClient.getHouseApi().getHouse();

        messages.enqueue(new Callback<ResponseHouseInfo>() {
            @Override
            public void onResponse(Call<ResponseHouseInfo> call, Response<ResponseHouseInfo> response) {
                Log.e("response", "response " + response);
                Log.e("response", "response " + response.body().getSql());
                Log.e("response", "response " + response.body().getSuccess());

                for (HouseInfo houseInfo : response.body().getResult()) {
                    houseList.add(new House(houseInfo.getId(), houseInfo.getStreet(), houseInfo.getHouseNumber()));
                    Log.e("response", "response " + houseInfo.getId());
                    Log.e("response", "response " + houseInfo.getStreet());
                    Log.e("response", "response " + houseInfo.getHouseNumber());
                }

                adapter = new HouseAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, houseList, getLayoutInflater());
                housesListView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(), "Загружено " + response.body().getResult().size() + " домов", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseHouseInfo> call, Throwable t) {
                Log.e("response", "failure " + t);
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createHouse(View view) {
        HouseCreateDialog houseCreateDialog = new HouseCreateDialog(this);
        houseCreateDialog.show();

        Button createHouseButton = (Button)houseCreateDialog.findViewById(R.id.createHouseButton);
        createHouseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText street = (EditText)houseCreateDialog.findViewById(R.id.street);
                EditText streetNumber = (EditText)houseCreateDialog.findViewById(R.id.streetNumber);

                Call<ResponseCreateHouse> createHouseCall = ApiClient.getHouseApi().createHouse(street.getText().toString(), streetNumber.getText().toString());

                createHouseCall.enqueue(new Callback<ResponseCreateHouse>() {
                    @Override
                    public void onResponse(Call<ResponseCreateHouse> call, Response<ResponseCreateHouse> response) {

                        Log.e("response", "sql " + response.body().getSql());
                        Log.e("response", "result " + response.body().getSuccess());
                        int houseId = Integer.valueOf(response.body().getResult());
                        HouseParams houseParams = response.body().getParams();

                        houseList.add(new House(houseId, houseParams.getStreet(), houseParams.getHouseNumber()));

                        for (int i = 0; i < houseList.size(); i++) {
                            Log.e("house = ", houseList.get(i).getFullStreetName());
                        }

                        adapter.notifyDataSetChanged();
                        houseCreateDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ResponseCreateHouse> call, Throwable t) {
                        Log.e("response", "failure " + t);
                    }
                });

            }
        });

        Button cancelCreateHouseButton = (Button)houseCreateDialog.findViewById(R.id.cancelCreateHouseButton);
        cancelCreateHouseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                houseCreateDialog.dismiss();
            }
        });

    }
}