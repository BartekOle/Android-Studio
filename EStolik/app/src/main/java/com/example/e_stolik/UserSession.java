package com.example.e_stolik;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {
    Context context;

    public void removeUser()
    {
        sharedPreferences.edit().clear().commit();
    }

    public String getLogin() {
        login = sharedPreferences.getString("userdata", "");
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        sharedPreferences.edit().putString("userdata", login).commit();
    }

    private String login;
    SharedPreferences sharedPreferences;

    public UserSession(Context context)
    {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);

    }

}
