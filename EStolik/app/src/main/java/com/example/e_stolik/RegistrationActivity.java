package com.example.e_stolik;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    EditText et_login, et_haslo, et_haslo2, et_imie, et_nazwisko, et_email, et_numerTelefonu;
    Button btn_register, btn_login;
    public static final String UserLogin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrationlayout);

        databaseHelper = new DatabaseHelper(this);
        et_login = (EditText)findViewById(R.id.et_login);
        et_haslo = (EditText)findViewById(R.id.et_haslo);
        et_haslo2 = (EditText)findViewById(R.id.et_haslo2);
        et_imie = (EditText)findViewById(R.id.et_imie);
        et_nazwisko = (EditText)findViewById(R.id.et_nazwisko);
        et_email = (EditText)findViewById(R.id.et_email);
        et_numerTelefonu = (EditText)findViewById(R.id.et_nrTelefonu);
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = et_login.getText().toString();
                String haslo = et_haslo.getText().toString();
                String haslo2 = et_haslo2.getText().toString();
                String imie = et_imie.getText().toString();
                String nazwisko = et_nazwisko.getText().toString();
                String email = et_email.getText().toString();
                String nrTelefonu = et_numerTelefonu.getText().toString();

                if(login.equals("") || haslo.equals("") || haslo2.equals("") || imie.equals("") || nazwisko.equals("") || email.equals("") || nrTelefonu.equals("")){
                    Toast.makeText(getApplicationContext(), "Nie wypełniono wszystkich pól", Toast.LENGTH_SHORT).show();
                }else{
                    if(haslo.equals(haslo2)){
                        Boolean checkusername = databaseHelper.CheckUsername(login);
                        if(checkusername == true){
                            Boolean checkemail = databaseHelper.CheckEmail(email);
                                if(checkemail == true){
                                    int nrTelefonuInteger = Integer.parseInt(et_numerTelefonu.getText().toString());
                                Boolean insert = databaseHelper.InsertUser(login, haslo, imie, nazwisko, email, nrTelefonuInteger);
                                if(insert == true){
                                    Toast.makeText(getApplicationContext(), "Rejestracja się udała", Toast.LENGTH_SHORT).show();
                                    et_login.setText("");
                                    et_haslo.setText("");
                                    et_haslo2.setText("");
                                    et_imie.setText("");
                                    et_nazwisko.setText("");
                                    et_email.setText("");
                                    et_numerTelefonu.setText("");
                                    UserSession user = new UserSession(RegistrationActivity.this);
                                    user.setLogin(login);
                                    Intent intent = new Intent(RegistrationActivity.this, Main.class);
                                    intent.putExtra(UserLogin, login);
                                    startActivity(intent);
                                }else{
                                }
                        }else
                            {
                                Toast.makeText(getApplicationContext(), "Użytkownik o takim emailu istnieje", Toast.LENGTH_SHORT).show();
                            }}
                                else{
                                Toast.makeText(getApplicationContext(), "Użytkownik o takim loginie istnieje", Toast.LENGTH_SHORT).show();
                            }
                    }else{
                        Toast.makeText(getApplicationContext(), "Hasło do siebie nie pasują", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
