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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Szczegoly extends AppCompatActivity {

    TextView  tv_stolik, tv_termin, tv_dane, tv_telefon;
    Button btn_cofnij;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.szczegolylayout);
        databaseHelper = new DatabaseHelper(this);
        int IDRezerwacji = Integer.parseInt(getIntent().getStringExtra("IDRezerwacji"));
        ArrayList<RezerwacjaModel> Rezerwacje = databaseHelper.SelectRezerwacjaID(IDRezerwacji);
        btn_cofnij = (Button)findViewById(R.id.btn_cofnij);
        tv_stolik = findViewById(R.id.tv_stolik);
        tv_termin = findViewById(R.id.tv_termin);
        tv_dane = findViewById(R.id.tv_dane);
        tv_telefon = findViewById(R.id.tv_telefon);
        tv_stolik.setText("Stolik numer: " + Rezerwacje.get(0).getnrStolika());
        tv_termin.setText(Rezerwacje.get(0).getdata() + " " + Rezerwacje.get(0).getgodzinaRozpoczecia() + ":" + Rezerwacje.get(0).getgodzinaZakonczenia());
        tv_dane.setText(Rezerwacje.get(0).getimie() + " " + Rezerwacje.get(0).getnazwisko());
        tv_telefon.setText(Rezerwacje.get(0).getnrTelefonu());
        btn_cofnij.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Szczegoly.this, Historia.class);
                startActivity(intent);
                finish();
            }
        });
    }
}