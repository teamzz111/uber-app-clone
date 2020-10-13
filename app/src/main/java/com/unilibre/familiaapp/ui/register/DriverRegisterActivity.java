package com.unilibre.familiaapp.ui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.unilibre.familiaapp.R;

public class DriverRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private StorageReference mStorageRef;
    private Button property_card, soat, mechanical_inspection;
    private Uri property_card_path, soat_path, mechanical_inspection_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_register);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        property_card = (Button) findViewById(R.id.propertycard);
        soat = (Button)findViewById(R.id.soat);
        mechanical_inspection = (Button)findViewById(R.id.mechanical);

        property_card.setOnClickListener(this);
        soat.setOnClickListener(this);
        mechanical_inspection.setOnClickListener(this);
    }

    private void FileChooser(int REQUEST_CODE){
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a file"), REQUEST_CODE);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //String Fpath = data.getDataString();
        // do somthing...
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            if (requestCode == 234){
                property_card_path = data.getData();
            }
            else if (requestCode == 537){
                soat_path = data.getData();
            }
            else if (requestCode == 451){
                mechanical_inspection_path = data.getData();
            }
            //Toast.makeText(DriverRegisterActivity.this, "Path: "+data.getData()+", RqCode: "+requestCode+", RsCode: "+resultCode, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        if(view == property_card){
            // Do smt
            FileChooser(234);
            //Toast.makeText(DriverRegisterActivity.this, "Property Card", Toast.LENGTH_SHORT).show();
        }
        else if(view==soat){
            // Do smt
            FileChooser(537);
            //Toast.makeText(DriverRegisterActivity.this, "SOAT", Toast.LENGTH_SHORT).show();
        }
        else if (view==mechanical_inspection){
            // Do smt
            FileChooser(451);
            //Toast.makeText(DriverRegisterActivity.this, "Mechanical Inspection", Toast.LENGTH_SHORT).show();
        }
    }
}