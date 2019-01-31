package com.iacovelli.nicola.eatbasta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText password;
    EditText verifyPassword;
    EditText email;
    EditText phone;
    Button registerBtn;
    private FirebaseAuth mAuth;
    boolean isEmail, isPassword, isPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        password = findViewById(R.id.password_register);
        password.addTextChangedListener(new ValidateTextWatcher(password));
        verifyPassword = findViewById(R.id.verify_password_register);
        verifyPassword.addTextChangedListener(new ValidateTextWatcher(verifyPassword));
        email = findViewById(R.id.email_register);
        email.addTextChangedListener(new ValidateTextWatcher(email));
        phone = findViewById(R.id.phone_register);
        phone.addTextChangedListener(new ValidateTextWatcher(phone));
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
        if (isEmail && isPassword && isPhone) {
            if (!registerBtn.isEnabled()){
                registerBtn.setEnabled(true);
            }
        } else {
            if (registerBtn.isEnabled()) {
                registerBtn.setEnabled(false);
            }
        }
    }

    private void checkEmail() {
        String u = email.getText().toString();
        isEmail = Utility.checkEmail(u);
    }

    private void checkPassword() {
        String p = password.getText().toString();
        String vP = verifyPassword.getText().toString();
        isPassword = Utility.checkPassword(p, vP);
    }

    private void checkPhone() {
        String ph = phone.getText().toString();
        isPhone = Utility.checkPhone(ph);
    }


    class ValidateTextWatcher implements TextWatcher {

        View v;

        ValidateTextWatcher(View v) {
            this.v = v;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (v.getId()) {
                case R.id.email_register:
                    checkEmail();
                    break;
                case R.id.password_register:
                case R.id.verify_password_register:
                    checkPassword();
                    break;
                case R.id.phone_register:
                    checkPhone();
                    break;
            }
            validate();
        }
    }


}
