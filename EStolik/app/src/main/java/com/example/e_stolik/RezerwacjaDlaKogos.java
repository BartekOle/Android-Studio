package com.example.e_stolik;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RezerwacjaDlaKogos extends AppCompatActivity {

    DatePickerDialog datePicker;
    TimePickerDialog timePicker, timePicker2;
    EditText et_imie, et_nazwisko, et_nrTelefonu, et_data, et_godzRoz, et_godzZak;
    Button btn_cofnij;
    DatabaseHelper databaseHelper;
    Button btn_confirme;
    TextView test;    int hourRoz;
    int hourZak;
    int minRoz;
    int minZak;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlakogoslayout);
        init(Integer.parseInt(getIntent().getStringExtra("nrStolika")));
        final UserSession user = new UserSession(RezerwacjaDlaKogos.this);
        et_imie = findViewById(R.id.et_imie);
        et_nazwisko = findViewById(R.id.et_nazwisko);
        et_nrTelefonu = findViewById(R.id.et_nrTelefonu);
        et_data = findViewById(R.id.et_data);
        et_data.setInputType(InputType.TYPE_NULL);
        et_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datePicker = new DatePickerDialog(RezerwacjaDlaKogos.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                et_data.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });
        et_godzRoz = findViewById(R.id.et_godzRoz);
        et_godzRoz.setInputType(InputType.TYPE_NULL);
        et_godzRoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                timePicker = new TimePickerDialog(RezerwacjaDlaKogos.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                et_godzRoz.setText(String.format("%02d:%02d", sHour, sMinute));
                            }
                        }, hour, minutes, true);
                timePicker.show();
            }
        });

        et_godzZak = findViewById(R.id.et_godzZak);
        et_godzZak.setInputType(InputType.TYPE_NULL);
        et_godzZak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                timePicker2 = new TimePickerDialog(RezerwacjaDlaKogos.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                et_godzZak.setText(String.format("%02d:%02d", sHour, sMinute));
                            }
                        }, hour, minutes, true);
                timePicker2.show();
            }
        });
        btn_cofnij = (Button)findViewById(R.id.btn_cofnij);
        btn_cofnij.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RezerwacjaDlaKogos.this, Rezerwacja.class);
                startActivity(intent);
                finish();
            }
        });

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.CheckPrzedawnione();
        btn_confirme = findViewById(R.id.btn_potwierdz);
        /*test = findViewById(R.id.textView);
        String[] rezerwacja = databaseHelper.SelectTest(user.getLogin());
        String ID = rezerwacja[0];
        final String login = rezerwacja[1];
        final String nrStolikaRez = rezerwacja[2];
        String data = rezerwacja[3];
        final String godzinaRozpoczecia = rezerwacja[4];
        final String godzinaZakonczenia = rezerwacja[5];
        final String imie = rezerwacja[6];
        final String nazwisko = rezerwacja[7];
        final String nrTelefonu2 = rezerwacja[8];
        String zrezygnowane = rezerwacja[9];
        String przedawnione = rezerwacja[10];
        test.setText(ID + " " + login + " " + nrStolikaRez + " " + data + " " + godzinaRozpoczecia + " " + godzinaZakonczenia + " " + imie + " " + nazwisko + " " + nrTelefonu2 + " " + zrezygnowane + " " + przedawnione);*/
        btn_confirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = et_data.getText().toString();
                String godzinaRoz = et_godzRoz.getText().toString();
                String godzinaZak = et_godzZak.getText().toString();
                String godzRoz = godzinaRoz.substring(0, 2);
                String minutaRoz = godzinaRoz.substring(3, 5);
                String godzZak = godzinaZak.substring(0, 2);
                String minutaZak = godzinaZak.substring(3, 5);
                hourRoz = Integer.parseInt(godzRoz);
                minRoz = Integer.parseInt(minutaRoz);
                hourZak = Integer.parseInt(godzZak);
                minZak = Integer.parseInt(minutaZak);
                String[] userProfil = databaseHelper.SelectUser(user.getLogin());
                String login2 = userProfil[2];
                String imie2 = et_imie.getText().toString();
                String nazwisko2 = et_nazwisko.getText().toString();
                String nrTelefonu = et_nrTelefonu.getText().toString();
                int nrTelefonuInteger = Integer.parseInt(nrTelefonu);
                int nrStolikaInteger = Integer.parseInt(getIntent().getStringExtra("nrStolika"));
                Date dateNOW = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String data2 = formatter.format(dateNOW).toString();
                if(data.equals("") || godzinaRoz.equals("") || godzinaZak.equals("") || imie2.equals("") || nazwisko2.equals("") || nrTelefonu.equals("")){
                    Toast.makeText(getApplicationContext(), "Nie wypełniono wszystkich pól", Toast.LENGTH_SHORT).show();
                }
                else if((data.length() < data2.length()) || (data.length() == data2.length() && data.compareTo(data2) < 1))
                {
                    Toast.makeText(getApplicationContext(), "Data rezerwacji musi być przynajmniej na jutro", Toast.LENGTH_SHORT).show();
                }
                else if (((hourZak * 60 + minZak) - (hourRoz * 60 + minRoz)) < 60) {
                    Toast.makeText(getApplicationContext(), "Rezerwacja musi trwac przynajmniej godzine", Toast.LENGTH_SHORT).show();
                }  else {
                    Boolean sprawdzDaty = databaseHelper.CheckPoprawnaData(godzinaRoz, godzinaZak, data, nrStolikaInteger);
                    if(sprawdzDaty == true)
                    {
                        Boolean insert = databaseHelper.InsertRezer(login2, nrStolikaInteger, data, godzinaRoz, godzinaZak, imie2, nazwisko2, nrTelefonuInteger);
                        if (insert == true) {
                            Toast.makeText(getApplicationContext(), "Dodano rezerwacje", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(getIntent());
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Godzina już jest zarezerwowana", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });
    }

    public void init(int nrStolika) {
        TableLayout stk = findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        TextView tv1 = new TextView(this);
        tv1.setText(" Data ");
        tv1.setTextColor(Color.WHITE);
        tv1.setBackgroundColor(Color.rgb(253, 226, 173));
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Godzina Rozpoczęcia ");
        tv2.setTextColor(Color.WHITE);
        tv2.setBackgroundColor(Color.rgb(253, 226, 173));
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Godzina Zakończenia ");
        tv3.setTextColor(Color.WHITE);
        tv3.setBackgroundColor(Color.rgb(253, 226, 173));
        tbrow0.addView(tv3);
        stk.addView(tbrow0);
        databaseHelper = new DatabaseHelper(this);
        ArrayList<RezerwacjaModel> Rezerwacje = databaseHelper.SelectRezerwacjaData(nrStolika);
        for (int i = 0; i < Rezerwacje.size(); i++) {
            TableRow tbrow = new TableRow(this);
            TextView t2v = new TextView(this);
            t2v.setText(Rezerwacje.get(i).getdata());
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            t2v.setBackgroundColor(Color.rgb(158, 219, 204));
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText(Rezerwacje.get(i).getgodzinaRozpoczecia());
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            t3v.setBackgroundColor(Color.rgb(158, 219, 204));
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText(Rezerwacje.get(i).getgodzinaZakonczenia());
            t4v.setTextColor(Color.WHITE);
            t4v.setGravity(Gravity.CENTER);
            t4v.setBackgroundColor(Color.rgb(158, 219, 204));
            tbrow.addView(t4v);
            stk.addView(tbrow);
        }

    }
}