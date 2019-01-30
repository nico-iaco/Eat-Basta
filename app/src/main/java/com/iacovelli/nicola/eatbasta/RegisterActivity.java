package com.iacovelli.nicola.eatbasta;

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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements TextWatcher {

    EditText password;
    EditText verifyPassword;
    EditText email;
    Button registerBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        password = findViewById(R.id.password_register);
        password.addTextChangedListener(this);
        verifyPassword = findViewById(R.id.verify_password_register);
        verifyPassword.addTextChangedListener(this);
        email = findViewById(R.id.email_register);
        email.addTextChangedListener(this);
        registerBtn = findViewById(R.id.register_button_register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String p = password.getText().toString();
                mAuth.createUserWithEmailAndPassword(e, p)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Register", "createUserWithEmail:success");
                                    Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(i);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Register", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void validate() {
        String u = email.getText().toString();
        String p = password.getText().toString();
        String vP = verifyPassword.getText().toString();
        if (Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), u) && p.length()>0 && p.equals(vP) ){
            if (!registerBtn.isEnabled()){
                registerBtn.setEnabled(true);
            }
        } else {
            if (registerBtn.isEnabled()) {
                registerBtn.setEnabled(false);
            }
        }
    }

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
}
