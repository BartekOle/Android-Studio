package com.example.dios1.zadanie4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Aktywnosc0 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktywnosc0);


    }

    public void uruchomPierwsza(View view)
    {
        final Intent intencja = new Intent(this,Aktywnosc1.class);
        startActivity(intencja);
    }

    public void uruchomDruga(View view)
    {
        final Intent intencja2 = new Intent(this,Aktywnosc2.class);
        startActivity(intencja2);
    }
}
