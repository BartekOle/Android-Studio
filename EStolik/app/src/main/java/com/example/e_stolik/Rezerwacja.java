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

public class Rezerwacja extends AppCompatActivity {

    Button btn_stolik1a, btn_stolik2a, btn_stolik3a, btn_stolik4a, btn_stolik5a, btn_stolik1b, btn_stolik2b, btn_stolik3b, btn_stolik4b, btn_stolik5b;
    public static final String nrStolika = "";

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
                Intent intent = new Intent(Rezerwacja.this, Main.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.item2:
                closeOptionsMenu();
                return true;
            case R.id.item3:
                Intent intent4 = new Intent(Rezerwacja.this, Historia.class);
                startActivity(intent4);
                finish();
                return true;
            case R.id.item4:
                Intent intent3 = new Intent(Rezerwacja.this, ProfilActivity.class);
                startActivity(intent3);
                finish();
                return true;
            case R.id.item5:
                new UserSession(Rezerwacja.this).removeUser();
                Intent intent2 = new Intent(Rezerwacja.this, LoginActivity.class);
                startActivity(intent2);
                finish();
                Toast.makeText(Rezerwacja.this,"Wylogowywanie udane", Toast.LENGTH_LONG).show();
                return true;
            default:onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rezerwacjalayout);
        final UserSession user = new UserSession(Rezerwacja.this);
        btn_stolik1a = findViewById(R.id.btn_reze1a);
        btn_stolik1a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rezerwacja.this, RezerwacjaDlaSiebie.class);
                intent.putExtra("nrStolika", "1");
                startActivity(intent);

            }
        });
        btn_stolik1b = findViewById(R.id.btn_reze1b);
        btn_stolik1b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rezerwacja.this, RezerwacjaDlaKogos.class);
                intent.putExtra("nrStolika", "1");
                startActivity(intent);
                finish();
            }
        });
        btn_stolik2a = findViewById(R.id.btn_reze2a);
        btn_stolik2a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rezerwacja.this, RezerwacjaDlaSiebie.class);
                intent.putExtra("nrStolika", "2");
                startActivity(intent);
                finish();
            }
        });
        btn_stolik2b = findViewById(R.id.btn_reze2b);
        btn_stolik2b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rezerwacja.this, RezerwacjaDlaKogos.class);
                intent.putExtra("nrStolika", "2");
                startActivity(intent);
                finish();
            }
        });
        btn_stolik3a = findViewById(R.id.btn_reze3a);
        btn_stolik3a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rezerwacja.this, RezerwacjaDlaSiebie.class);
                intent.putExtra("nrStolika", "3");
                startActivity(intent);
                finish();
            }
        });
        btn_stolik3b = findViewById(R.id.btn_reze3b);
        btn_stolik3b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rezerwacja.this, RezerwacjaDlaKogos.class);
                intent.putExtra("nrStolika", "3");
                startActivity(intent);
                finish();
            }
        });
        btn_stolik4a = findViewById(R.id.btn_reze4a);
        btn_stolik4a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rezerwacja.this, RezerwacjaDlaSiebie.class);
                intent.putExtra("nrStolika", "4");
                startActivity(intent);
                finish();
            }
        });
        btn_stolik4b = findViewById(R.id.btn_reze4b);
        btn_stolik4b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rezerwacja.this, RezerwacjaDlaKogos.class);
                intent.putExtra("nrStolika", "4");
                startActivity(intent);
                finish();
            }
        });

        btn_stolik5a = findViewById(R.id.btn_reze5a);
        btn_stolik5a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rezerwacja.this, RezerwacjaDlaSiebie.class);
                intent.putExtra("nrStolika", "5");
                startActivity(intent);
                finish();
            }
        });
        btn_stolik5b = findViewById(R.id.btn_reze5b);
        btn_stolik5b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rezerwacja.this, RezerwacjaDlaKogos.class);
                intent.putExtra("nrStolika", "5");
                startActivity(intent);
                finish();
            }
        });
    }
}