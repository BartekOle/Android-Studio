package com.example.e_stolik;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {

    String Login;
    TextView tv_login;
    Button btn_logout ;

    @Override
    public void onBackPressed() {
    }

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
                closeOptionsMenu();
                return true;
            case R.id.item2:
                Intent intent3 = new Intent(Main.this, Rezerwacja.class);
                startActivity(intent3);
                finish();
                return true;
            case R.id.item3:
                Intent intent4 = new Intent(Main.this, Historia.class);
                startActivity(intent4);
                finish();
                return true;
            case R.id.item4:
                Intent intent = new Intent(Main.this, ProfilActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.item5:
                new UserSession(Main.this).removeUser();
                Intent intent2 = new Intent(Main.this, LoginActivity.class);
                startActivity(intent2);
                finish();
                Toast.makeText(Main.this,"Wylogowywanie udane", Toast.LENGTH_LONG).show();
                return true;
            default:onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);

        tv_login = (TextView)findViewById(R.id.tv_login);
        final UserSession user = new UserSession(Main.this);
        /*Intent intent = getIntent();
        if (intent.hasExtra(LoginActivity.UserLogin)) {
            Login = intent.getStringExtra(LoginActivity.UserLogin);
            tv_login.setText(tv_login.getText().toString()+ Login + user.getLogin());
        } else if(intent.hasExtra(RegistrationActivity.UserLogin)) {
            Login = intent.getStringExtra(RegistrationActivity.UserLogin);
            tv_login.setText(tv_login.getText().toString()+ Login + user.getLogin());
        }
        else
        {
            Login = getIntent().getExtras().getString("UserLogin");
            tv_login.setText(tv_login.getText().toString() + Login + user.getLogin());
        }*/
        tv_login.setText(tv_login.getText().toString() + user.getLogin());

    }
}