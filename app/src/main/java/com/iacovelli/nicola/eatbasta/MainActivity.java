package com.iacovelli.nicola.eatbasta;

import android.app.Application;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText user;
    EditText pwd;
    Button loginBtn;
    TextView recoveryPwdTxt;
    TextView registerTxtBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        user = findViewById(R.id.user_login);
        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validate();
            }
        });
        pwd = findViewById(R.id.password_login);
        pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validate();
            }
        });
        loginBtn = findViewById(R.id.login_button);
        loginBtn.setOnClickListener(this);
        recoveryPwdTxt = findViewById(R.id.password_recovery);
        recoveryPwdTxt.setOnClickListener(this);
        registerTxtBtn = findViewById(R.id.register_button);
        registerTxtBtn.setOnClickListener(this);
    }

    private void validate() {
        String u = user.getText().toString();
        String p = pwd.getText().toString();
        if (Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), u) && p.length()>0){
            if (!loginBtn.isEnabled()){
                loginBtn.setEnabled(true);
            }
        } else {
            if (loginBtn.isEnabled()){
                loginBtn.setEnabled(false);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(this, "Utente loggato", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, CartActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Utente non loggato", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.login_button:
                String email = user.getText().toString();
                String password = pwd.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Login", "signInWithEmail:success");
                                    Intent intent = new Intent(MainActivity.this, CartActivity.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Login", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Login incorretto", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.password_recovery:
                Toast.makeText(this, "Stai provando a recuperare la password", Toast.LENGTH_SHORT).show();
                break;
            case R.id.register_button:
                i = new Intent(this,RegisterActivity.class);
                startActivity(i);
                break;
            default:
                Log.d(getLocalClassName(),"Errore");
        }
    }
}
