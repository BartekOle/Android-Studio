package com.example.e_stolik;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Historia extends AppCompatActivity {

    Button btn_szczegoly, btn_zrezygnuj;
    DatabaseHelper databaseHelper;
    RadioButton RB;
    public static final String IDRezerwacji = "";

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
                Intent intent = new Intent(Historia.this, Main.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.item2:
                Intent intent4 = new Intent(Historia.this, Rezerwacja.class);
                startActivity(intent4);
                finish();
                return true;
            case R.id.item3:
                closeOptionsMenu();
                return true;
            case R.id.item4:
                Intent intent3 = new Intent(Historia.this, ProfilActivity.class);
                startActivity(intent3);
                finish();
                return true;
            case R.id.item5:
                new UserSession(Historia.this).removeUser();
                Intent intent2 = new Intent(Historia.this, LoginActivity.class);
                startActivity(intent2);
                finish();
                Toast.makeText(Historia.this,"Wylogowywanie udane", Toast.LENGTH_LONG).show();
                return true;
            default:onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historialayout);
        databaseHelper = new DatabaseHelper(this);
        final RadioGroup RG3 = (RadioGroup) findViewById(R.id.radioGroup);
        final UserSession user = new UserSession(Historia.this);
        addRadioButtons(user.getLogin());
        btn_zrezygnuj = (Button)findViewById(R.id.btn_zrzygnuj);
        btn_zrezygnuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = RG3.getCheckedRadioButtonId();
                RB = findViewById(radioId);
                databaseHelper.UpdateZrezygnowane(Integer.parseInt(RB.getTag().toString()));
                Toast.makeText(getApplicationContext(), "Zrezygnowano z rezerwacji", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }
        });
        btn_szczegoly = (Button)findViewById(R.id.btn_szczegoly);
        btn_szczegoly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = RG3.getCheckedRadioButtonId();
                RB = findViewById(radioId);
                Intent intent = new Intent(Historia.this, Szczegoly.class);
                intent.putExtra("IDRezerwacji", RB.getTag().toString());
                startActivity(intent);
                finish();
            }
        });
    }

    public void addRadioButtons(String login) {
        databaseHelper = new DatabaseHelper(this);
        ArrayList<RezerwacjaModel> Rezerwacje = databaseHelper.SelectRezerwacjaUser(login);
            RadioGroup RG = findViewById(R.id.radioGroup);
            for (int i = 0; i < Rezerwacje.size(); i++) {
                RadioButton rdbtn = new RadioButton(this);
                rdbtn.setId(View.generateViewId());
                rdbtn.setText("Rezerwacja: " + Rezerwacje.get(i).getID() + " " + Rezerwacje.get(i).getdata() + " " + Rezerwacje.get(i).getgodzinaRozpoczecia() + ":" + Rezerwacje.get(i).getgodzinaZakonczenia());
                rdbtn.setTag(Rezerwacje.get(i).getID());
                RG.addView(rdbtn);
            }
    }
}