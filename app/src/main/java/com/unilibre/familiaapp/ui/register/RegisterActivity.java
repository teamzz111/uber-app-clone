package com.unilibre.familiaapp.ui.register;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.unilibre.familiaapp.R;
import com.unilibre.familiaapp.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText mname, memail, mpassword;
    Button mRegisterBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mname = findViewById(R.id.name);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mRegisterBtn = findViewById(R.id.register);

        fAuth= FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            //usuario ya loggeado
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    memail.setError("Se requiere un email");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    mpassword.setError("Se requiere una contraseña");
                    return;
                }
                if(password.length()<6)
                {
                    mpassword.setError("La contraseña debe superar los 6 caracteres");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Éxito al registrar el usuario", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }
}