package com.avorobyev174.mec_winet.classes.api;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.avorobyev174.mec_winet.R;

public class HouseCreateDialog extends Dialog {
    public Activity activity;
    public Button yes, no;
    public EditText street, streetNumber;

    public HouseCreateDialog(@NonNull Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.house_create_dialog_activity);

        yes = (Button) findViewById(R.id.createHouseButton);
        no = (Button) findViewById(R.id.cancelCreateHouseButton);

        street = (EditText) findViewById(R.id.street);
        streetNumber = (EditText) findViewById(R.id.streetNumber);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_yes:
//                if (listener != null) {
//                    listener.createListName(list_name);
//                }
//                break;
//        }
//
//        SharedPreferences settings = getContext().getSharedPreferences("create_house_dialog", Context.MODE_PRIVATE);
//
//        SharedPreferences.Editor prefEditor = settings.edit();
//        prefEditor.putString("street", street.getText().toString());
//        prefEditor.putString("streetNumber", streetNumber.getText().toString());
//        prefEditor.commit();
//
//        dismiss();
//    }
}
