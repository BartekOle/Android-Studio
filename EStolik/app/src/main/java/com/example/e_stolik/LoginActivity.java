package com.example.e_stolik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    Button btn_register, btn_login;
    EditText et_login, et_haslo;
    public static final String UserLogin = "";

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);

        databaseHelper = new DatabaseHelper(this);
        et_login = (EditText)findViewById(R.id.et_login);
        et_haslo = (EditText)findViewById(R.id.et_haslo);

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_register = (Button)findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = et_login.getText().toString();
                String haslo = et_haslo.getText().toString();
                if (login.equals("") || haslo.equals("")) {
                    Toast.makeText(getApplicationContext(), "Nie wypełniono wszystkich pól", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checklogin = databaseHelper.CheckLogin(login, haslo);
                    if (checklogin == true) {
                        Toast.makeText(LoginActivity.this, "Logowanie udane", Toast.LENGTH_LONG).show();
                        UserSession user = new UserSession(LoginActivity.this);
                        user.setLogin(login);
                        Intent intent = new Intent(LoginActivity.this, Main.class);
                        intent.putExtra(UserLogin, login);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Błędne hasło albo login", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}