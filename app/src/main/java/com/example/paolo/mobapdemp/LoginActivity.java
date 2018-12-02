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

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText enterEmail;
    private EditText enterPass;
    private FirebaseAuth firebaseAuth;
    private TextView signinText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){ //user is already logged in
            //put activity here
        }

        signinText = findViewById(R.id.signinText);
        loginBtn = findViewById(R.id.loginBtn);
        enterEmail = findViewById(R.id.enterEmail);
        enterPass = findViewById(R.id.enterPass);

        enterEmail.setText("test@gmail.com");
        enterPass.setText("password");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = enterEmail.getText().toString().trim();
                String pass = enterPass.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this,"Please Enter Email", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginActivity.this,"Please Enter Password", Toast.LENGTH_LONG).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this,"Logging in User: " + enterEmail.getText().toString().trim()
                                            , Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    LoginActivity.this.startActivity(intent);
                                    LoginActivity.this.finish();

                                }else{
                                    Toast.makeText(LoginActivity.this,"Could not login invalid username/password"
                                            , Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        signinText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                LoginActivity.this.startActivity(intent);
                LoginActivity.this.finish();
            }
        });

    }
}
