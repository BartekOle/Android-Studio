package com.example.e_stolik;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    EditText et_login, et_imie, et_nazwisko, et_email, et_numerTelefonu;
    Button btn_confirme;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(ProfilActivity.this, Main.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.item2:
                Intent intent3 = new Intent(ProfilActivity.this, Rezerwacja.class);
                startActivity(intent3);
                finish();
                return true;
            case R.id.item3:
                Intent intent4 = new Intent(ProfilActivity.this, Historia.class);
                startActivity(intent4);
                finish();
                return true;
            case R.id.item4:
                closeOptionsMenu();
                return true;
            case R.id.item5:
                new UserSession(ProfilActivity.this).removeUser();
                Intent intent2 = new Intent(ProfilActivity.this, LoginActivity.class);
                startActivity(intent2);
                finish();
                Toast.makeText(ProfilActivity.this,"Wylogowywanie udane", Toast.LENGTH_LONG).show();
                return true;
            default:onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profillayout);
        final UserSession user = new UserSession(ProfilActivity.this);
        databaseHelper = new DatabaseHelper(this);
        et_login = (EditText)findViewById(R.id.et_login);
        et_imie = (EditText)findViewById(R.id.et_imie);
        et_nazwisko = (EditText)findViewById(R.id.et_nazwisko);
        et_email = (EditText)findViewById(R.id.et_email);
        et_numerTelefonu = (EditText)findViewById(R.id.et_nrTelefonu);
        btn_confirme = (Button)findViewById(R.id.btn_confirme);

        String[] userProfil = databaseHelper.SelectUser(user.getLogin());
        String imie = userProfil[0];
        String nazwisko = userProfil[1];
        String login = userProfil[2];
        final String emailOld = userProfil[3];
        String nrTelefonu = userProfil[4];
        et_login.setText(login);
        et_imie.setText(imie);
        et_nazwisko.setText(nazwisko);
        et_email.setText(emailOld);
        et_numerTelefonu.setText(nrTelefonu);

       btn_confirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = et_login.getText().toString();
                String imie = et_imie.getText().toString();
                String nazwisko = et_nazwisko.getText().toString();
                String email = et_email.getText().toString();
                String nrTelefonu = et_numerTelefonu.getText().toString();

                if(imie.equals("") || nazwisko.equals("") || email.equals("") || nrTelefonu.equals("")){
                    Toast.makeText(getApplicationContext(), "Nie wypełniono wszystkich pul", Toast.LENGTH_SHORT).show();
                }else {
                    if (!emailOld.equals(email)) {
                        Boolean checkemail = databaseHelper.CheckEmail(email);
                        if (checkemail == true) {
                            int nrTelefonuInteger = Integer.parseInt(et_numerTelefonu.getText().toString());
                            Boolean update = databaseHelper.Update(login, imie, nazwisko, email, nrTelefonuInteger);
                            if (update == true) {
                                Toast.makeText(getApplicationContext(), "Z aktualizowano dane", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(getIntent());
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Użytkownik o takim emailu istnieje", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        int nrTelefonuInteger = Integer.parseInt(et_numerTelefonu.getText().toString());
                        Boolean update = databaseHelper.Update(login, imie, nazwisko, email, nrTelefonuInteger);
                        if (update == true) {
                            Toast.makeText(getApplicationContext(), "Z aktualizowano dane", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(getIntent());
                        }
                    }
                }
                    }
        });
    }
}