package com.example.paolo.mobapdemp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText registerEmail;
    private EditText registerPass;
    private FirebaseAuth firebaseAuth;
    private TextView signinText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        loginBtn = findViewById(R.id.loginBtn);
        registerEmail = findViewById(R.id.registerEmail);
        registerPass = findViewById(R.id.registerPass);
        signinText = findViewById(R.id.signinText);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = registerEmail.getText().toString().trim();
                String pass = registerPass.getText().toString().trim();
                //If no fields in these are entered
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignupActivity.this,"Please Enter Email", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(SignupActivity.this,"Please Enter Password", Toast.LENGTH_LONG).show();
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(SignupActivity.this,"User added to the database" , Toast.LENGTH_LONG).show();
                                    registerEmail.setText("");
                                    registerPass.setText("");
                                }else{
                                    Toast.makeText(SignupActivity.this,"Could not register", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        signinText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                SignupActivity.this.startActivity(intent);
                SignupActivity.this.finish();
            }
        });

    }
}
