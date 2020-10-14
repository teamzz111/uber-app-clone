package com.unilibre.familiaapp.ui.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.unilibre.familiaapp.R;
import com.unilibre.familiaapp.ui.login.LoginActivity;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DriverRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private StorageReference StorageRef;
    private FirebaseAuth fAuth;
    private Button property_card, soat, mechanical_inspection, register;
    private Uri property_card_path, soat_path, mechanical_inspection_path;
    private EditText tx_name, tx_id, tx_email, tx_bank, tx_password, tx_line, tx_model, tx_license_plate;
    private Spinner sl_brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_register);

        StorageRef = FirebaseStorage.getInstance().getReference();
        fAuth= FirebaseAuth.getInstance();

        property_card = (Button) findViewById(R.id.propertycard);
        soat = (Button)findViewById(R.id.soat);
        mechanical_inspection = (Button)findViewById(R.id.mechanical);
        register = (Button)findViewById(R.id.register);

        tx_name = (EditText) findViewById(R.id.name);
        tx_id = (EditText) findViewById(R.id.id);
        tx_email = (EditText) findViewById(R.id.email);
        tx_bank = (EditText) findViewById(R.id.bank);
        tx_password = (EditText) findViewById(R.id.password);
        tx_line = (EditText) findViewById(R.id.line);
        tx_model = (EditText) findViewById(R.id.model);
        tx_license_plate = (EditText) findViewById(R.id.licenseplate);

        sl_brand = (Spinner)findViewById(R.id.brand);

        property_card.setOnClickListener(this);
        soat.setOnClickListener(this);
        mechanical_inspection.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void FileChooser(int REQUEST_CODE){
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a file"), REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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

    private void uploadFile(Uri file, String file_name){
        if(file != null) {
            //Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
            StorageReference riversRef = StorageRef.child("car_docs/"+file_name+".pdf");

            riversRef.putFile(file)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                        }
                    });
        }
    }

    public void RegisterDriver(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String name = tx_name.getText().toString().trim();
        String id = tx_id.getText().toString().trim();
        String email = tx_email.getText().toString().trim();
        String bank = tx_bank.getText().toString().trim();
        String password = tx_password.getText().toString().trim();

        String brand = sl_brand.getSelectedItem().toString();
        String line = tx_line.getText().toString().trim();
        String model = tx_model.getText().toString().trim();
        String license_plate = tx_license_plate.getText().toString().trim();

        Map<String, Object> user = new HashMap<>();
        user.put("Email", email);
        user.put("Name", name);

        Map<String, Object> driver = new HashMap<>();
        driver.put("IdCard", id);
        driver.put("DriverLicense", id);
        driver.put("BankAccount", bank);
        driver.put("Status", "Check Data");

        db.collection("Users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });

        db.collection("Drivers")
                .add(driver)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });

        if(property_card_path != null){
            uploadFile(property_card_path,id+"-"+license_plate+"-property_card");
        }
        if(property_card_path != null) {
            uploadFile(soat_path, id + "-" + license_plate + "-soat");
        }
        if(property_card_path != null) {
            uploadFile(mechanical_inspection_path, id + "-" + license_plate + "-mechanical_inspection");
        }

        Toast.makeText(DriverRegisterActivity.this, "Éxito al registrar el conductor.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(DriverRegisterActivity.this, LoginActivity.class));
    }

    @Override
    public void onClick(View view) {
        if(view == property_card){
            FileChooser(234);
        }
        else if(view == soat){
            FileChooser(537);
        }
        else if (view == mechanical_inspection){
            FileChooser(451);
        }
        else if (view == register){
            RegisterDriver();
        }
    }
}