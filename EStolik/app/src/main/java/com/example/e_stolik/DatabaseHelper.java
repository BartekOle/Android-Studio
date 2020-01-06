package com.example.e_stolik;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "login.db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE klient(login TEXT PRIMARY KEY, haslo TEXT, imie TEXT, nazwisko TEXT, email TEXT UNIQUE, nrTelefonu Integer )");
        db.execSQL("CREATE TABLE rezerwacja(ID INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, nrStolika INTEGER, data DATE, godzinaRozpoczecia DATETIME, godzinaZakonczenia DATETIME, imie TEXT, nazwisko TEXT, nrTelefonu Integer, zrezygnowane BOOLEAN DEFAULT 0, przedawnione BOOLEAN DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS klient");
        db.execSQL("DROP TABLE IF EXISTS rezerwacja");
    }

    public void createTable()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("CREATE TABLE klient(login TEXT PRIMARY KEY, haslo TEXT, imie TEXT, nazwisko TEXT, email TEXT UNIQUE, nrTelefonu Integer )");
        sqLiteDatabase.execSQL("CREATE TABLE rezerwacja(ID INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, nrStolika INTEGER, data DATE, godzinaRozpoczecia DATETIME, godzinaZakonczenia DATETIME, imie TEXT, nazwisko TEXT, nrTelefonu Integer, zrezygnowane BOOLEAN DEFAULT 0, przedawnione BOOLEAN DEFAULT 0)");
    }

    public boolean InsertUser(String login, String haslo, String imie, String nazwisko, String email, int nrTelefonu){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", login);
        contentValues.put("haslo", haslo);
        contentValues.put("imie", imie);
        contentValues.put("nazwisko", nazwisko);
        contentValues.put("email", email);
        contentValues.put("nrTelefonu", nrTelefonu);
        long result = sqLiteDatabase.insert("klient", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean InsertRezer(String login, int nrStolika, String data, String godzinaRozpoczecia, String godzinaZakonczenia, String imie, String nazwisko, int nrTelefonu){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", login);
        contentValues.put("nrStolika", nrStolika);
        contentValues.put("data", data);
        contentValues.put("godzinaRozpoczecia", godzinaRozpoczecia);
        contentValues.put("godzinaZakonczenia", godzinaZakonczenia);
        contentValues.put("imie", imie);
        contentValues.put("nazwisko", nazwisko);
        contentValues.put("nrTelefonu", nrTelefonu);
        long result = sqLiteDatabase.insert("rezerwacja", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean Update(String login, String imie, String nazwisko, String email, int nrTelefonu){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("imie", imie);
        contentValues.put("nazwisko", nazwisko);
        contentValues.put("email", email);
        contentValues.put("nrTelefonu", nrTelefonu);
        long result = sqLiteDatabase.update("klient", contentValues, "login=?", new String[]{login});
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean CheckUsername(String login){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM klient WHERE login=?", new String[]{login});
        if(cursor.getCount() > 0){
            return false;
        }else{
            return true;
        }
    }

    public Boolean CheckEmail(String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM klient WHERE email=?", new String[]{email});
        if(cursor.getCount() > 0){
            return false;
        }else{
            return true;
        }
    }

    public Boolean CheckLogin(String login, String haslo){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM klient WHERE login=? AND haslo=?", new String[]{login, haslo});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public String[] SelectUser(String login)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[5];
        Cursor cursor = db.rawQuery("SELECT imie, nazwisko, login, email, nrTelefonu FROM klient WHERE login=? ", new String[]{login});
        if (cursor.moveToFirst()){
            do {
                columns[0] = cursor.getString(0);
                columns[1] = cursor.getString(1);
                columns[2] = cursor.getString(2);
                columns[3] = cursor.getString(3);
                columns[4] = cursor.getString(4);
            } while(cursor.moveToNext());
        }
        return columns;
    }

    public String[] SelectTest(String login)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[11];
        Cursor cursor = db.rawQuery("SELECT ID, login, nrStolika, data,  godzinaRozpoczecia, godzinaZakonczenia, imie, nazwisko, nrTelefonu, zrezygnowane, przedawnione FROM rezerwacja WHERE login=? ", new String[]{login});
        if (cursor.moveToFirst()){
            do {
                columns[0] = cursor.getString(0);
                columns[1] = cursor.getString(1);
                columns[2] = cursor.getString(2);
                columns[3] = cursor.getString(3);
                columns[4] = cursor.getString(4);
                columns[5] = cursor.getString(5);
                columns[6] = cursor.getString(6);
                columns[7] = cursor.getString(7);
                columns[8] = cursor.getString(8);
                columns[9] = cursor.getString(9);
                columns[10] = cursor.getString(10);
            } while(cursor.moveToNext());
        }
        return columns;
    }

    public void CheckPrzedawnione(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("przedawnione", 1);
        sqLiteDatabase.update("rezerwacja", contentValues, "data <= DATE('now')", new String[]{});
    }

    public void UpdateZrezygnowane(int ID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("zrezygnowane", 1);
        sqLiteDatabase.update("rezerwacja", contentValues, "ID = ?", new String[]{String.valueOf(ID)});
    }

    public boolean CheckPoprawnaData(String godzinaRozpoczecia, String godzinaZakonczenia, String dataRezerwacji, int nrStolika){
        SQLiteDatabase db = this.getReadableDatabase();
        boolean brakBledu = true;
        Cursor cursor = db.rawQuery("SELECT data, godzinaRozpoczecia, godzinaZakonczenia, przedawnione, zrezygnowane, nrStolika FROM rezerwacja WHERE  przedawnione = 0 AND zrezygnowane = 0 AND data = ? AND nrStolika = ?", new String[]{dataRezerwacji, String.valueOf(nrStolika)});
        if (cursor.moveToFirst()){
            do {
                if((godzinaRozpoczecia.length() <  cursor.getString(1).length()) || (godzinaRozpoczecia.length() ==  cursor.getString(1).length() && godzinaRozpoczecia.compareTo(cursor.getString(1)) < 0))
                {
                    if((godzinaZakonczenia.length() <  cursor.getString(1).length()) || (godzinaZakonczenia.length() ==  cursor.getString(1).length() && godzinaZakonczenia.compareTo(cursor.getString(1)) < 1))
                    {

                    }
                    else
                    {
                        brakBledu = false;
                    }
                }
                else if((godzinaRozpoczecia.length() >  cursor.getString(2).length()) || (godzinaRozpoczecia.length() ==  cursor.getString(2).length() && godzinaRozpoczecia.compareTo(cursor.getString(2)) > -1))
                {


                }
                else
                {
                    brakBledu = false;
                }
            }while(cursor.moveToNext());
            }
        return brakBledu;
        }

    public ArrayList<RezerwacjaModel> SelectRezerwacjaData(int nrStolika) {
        ArrayList<RezerwacjaModel> rezerwacjaModelArrayList = new ArrayList<RezerwacjaModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT data, godzinaRozpoczecia, godzinaZakonczenia FROM rezerwacja WHERE przedawnione = 0 AND zrezygnowane = 0  AND nrStolika = ?", new String[]{String.valueOf(nrStolika)});
        if (cursor.moveToFirst()) {
            do {
                RezerwacjaModel rezerwacja = new RezerwacjaModel();
                rezerwacja.setdata(cursor.getString(0));
                rezerwacja.setgodzinaRozpoczecia(cursor.getString(1));
                rezerwacja.setgodzinaZakonczenia(cursor.getString(2));
                rezerwacjaModelArrayList.add(rezerwacja);
            } while (cursor.moveToNext());
        }
        return rezerwacjaModelArrayList;
    }

    public ArrayList<RezerwacjaModel> SelectRezerwacjaUser(String login) {
        ArrayList<RezerwacjaModel> rezerwacjaModelArrayList = new ArrayList<RezerwacjaModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ID, login, nrStolika, data,  godzinaRozpoczecia, godzinaZakonczenia, imie, nazwisko, nrTelefonu FROM rezerwacja WHERE przedawnione = 0 AND zrezygnowane = 0 AND login = ?", new String[]{login});
        if (cursor.moveToFirst()) {
            do {
                RezerwacjaModel rezerwacja = new RezerwacjaModel();
                rezerwacja.setID(cursor.getString(0));
                rezerwacja.setLogin(cursor.getString(1));
                rezerwacja.setnrStolika(cursor.getString(2));
                rezerwacja.setdata(cursor.getString(3));
                rezerwacja.setgodzinaRozpoczecia(cursor.getString(4));
                rezerwacja.setgodzinaZakonczenia(cursor.getString(5));
                rezerwacja.setimie(cursor.getString(6));
                rezerwacja.setnazwisko(cursor.getString(7));
                rezerwacja.setnrTelefonu(cursor.getString(8));
                rezerwacjaModelArrayList.add(rezerwacja);
            } while (cursor.moveToNext());
        }
        return rezerwacjaModelArrayList;
    }

    public ArrayList<RezerwacjaModel> SelectRezerwacjaID(int ID) {
        ArrayList<RezerwacjaModel> rezerwacjaModelArrayList = new ArrayList<RezerwacjaModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ID, login, nrStolika, data,  godzinaRozpoczecia, godzinaZakonczenia, imie, nazwisko, nrTelefonu FROM rezerwacja WHERE ID = ?", new String[]{String.valueOf(ID)});
        if (cursor.moveToFirst()) {
            do {
                RezerwacjaModel rezerwacja = new RezerwacjaModel();
                rezerwacja.setID(cursor.getString(0));
                rezerwacja.setLogin(cursor.getString(1));
                rezerwacja.setnrStolika(cursor.getString(2));
                rezerwacja.setdata(cursor.getString(3));
                rezerwacja.setgodzinaRozpoczecia(cursor.getString(4));
                rezerwacja.setgodzinaZakonczenia(cursor.getString(5));
                rezerwacja.setimie(cursor.getString(6));
                rezerwacja.setnazwisko(cursor.getString(7));
                rezerwacja.setnrTelefonu(cursor.getString(8));
                rezerwacjaModelArrayList.add(rezerwacja);
            } while (cursor.moveToNext());
        }
        return rezerwacjaModelArrayList;
    }

    }
