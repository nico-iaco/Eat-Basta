package com.iacovelli.nicola.eatbasta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.Utility;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText user;
    TextInputEditText pwd;
    Button loginBtn;
    TextView recoveryPwdTxt;
    TextView registerTxtBtn;
    Switch darkSwitch;
    LinearLayoutCompat mainLayout;
    boolean isEmail, isPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = findViewById(R.id.main_layout);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        user = findViewById(R.id.user_login);
        user.addTextChangedListener(new LoginTextWatcher(user));
        pwd = findViewById(R.id.password_login);
        pwd.addTextChangedListener(new LoginTextWatcher(pwd));
        loginBtn = findViewById(R.id.login_button);
        loginBtn.setOnClickListener(this);
        recoveryPwdTxt = findViewById(R.id.password_recovery);
        recoveryPwdTxt.setOnClickListener(this);
        registerTxtBtn = findViewById(R.id.register_button);
        registerTxtBtn.setOnClickListener(this);
        darkSwitch = findViewById(R.id.dark_switch);
        darkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //TODO: Implementare il cambio di tema
            }
        });
    }

    class LoginTextWatcher implements TextWatcher {

        View v;

        LoginTextWatcher(View v) {
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
                case R.id.user_login:
                    String u = user.getText().toString();
                    isEmail = Utility.checkEmail(u);
                    break;
                case R.id.password_login:
                    String p = pwd.getText().toString();
                    isPassword = Utility.checkPassword(p);
                    break;
            }
            validate();
        }
    }

    private void validate() {
        if (isEmail && isPassword) {
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
            Intent i = new Intent(this, RestaurantActivity.class);
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
                                    Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
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
                i = new Intent(this, RegisterActivity.class);
                startActivity(i);
                break;
            default:
                Log.d(getLocalClassName(),"Errore");
        }
    }
}
