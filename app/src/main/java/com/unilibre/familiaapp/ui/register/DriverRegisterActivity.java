package com.unilibre.familiaapp.ui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.unilibre.familiaapp.R;

public class DriverRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private StorageReference mStorageRef;
    Button property_card, soat, mechanical_inspection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_register);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        property_card = findViewById(R.id.propertycard);
        soat = findViewById(R.id.soat);
        mechanical_inspection = findViewById(R.id.mechanical);
    }

    private void FileChooser(int REQUEST_CODE){
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a image"), REQUEST_CODE);
    }

    @Override
    public void onClick(View view) {
        if(view == property_card){
            // Do smt
        }
        if(view==soat){
            // Do smt
        }
        if (view==mechanical_inspection){
            // Do smt
        }
    }
}