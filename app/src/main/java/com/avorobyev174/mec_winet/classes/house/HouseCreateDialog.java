package com.avorobyev174.mec_winet.classes.house;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.avorobyev174.mec_winet.R;
import com.avorobyev174.mec_winet.classes.api.ApiClient;
import com.avorobyev174.mec_winet.classes.api.SimpleResponseWithParams;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HouseCreateDialog extends Dialog {
    public Activity activity;
    public Button createHouseButton, cancelCreateHouseButton;
    public EditText street, streetNumber;
    private HouseAdapter houseAdapter;
    private List<House> houseList;

    public HouseCreateDialog(@NonNull Activity activity, HouseAdapter houseAdapter, List<House> houseList) {
        super(activity);
        this.activity = activity;
        this.houseAdapter = houseAdapter;
        this.houseList = houseList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.house_create_dialog_activity);

        createHouseButton = findViewById(R.id.createHouseButton);
        cancelCreateHouseButton = (Button) findViewById(R.id.cancelCreateHouseButton);

        street = findViewById(R.id.street);
        streetNumber = findViewById(R.id.streetNumber);

        cancelCreateHouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        createHouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<SimpleResponseWithParams> createHouseCall = ApiClient.getHouseApi().createHouse(street.getText().toString(), streetNumber.getText().toString());

                createHouseCall.enqueue(new Callback<SimpleResponseWithParams>() {
                    @Override
                    public void onResponse(Call<SimpleResponseWithParams> call, Response<SimpleResponseWithParams> response) {

                        Log.e("response", "success = " + response.body().getSuccess());
                        int houseId = Integer.valueOf(response.body().getResult());
                        HouseParams houseParams = response.body().getParams();

                        houseList.add(new House(houseId, houseParams.getStreet(), houseParams.getHouseNumber()));

                        Toast.makeText(getContext(), "Дом \"" + houseParams.getStreet() + " " +  houseParams.getHouseNumber() + "\" добавлен в список", Toast.LENGTH_SHORT).show();

                        houseAdapter.notifyDataSetChanged();
                        dismiss();
                    }

                    @Override
                    public void onFailure(Call<SimpleResponseWithParams> call, Throwable t) {
                        Log.e("response", "failure " + t);
                    }
                });
            }
        });
    }

}
