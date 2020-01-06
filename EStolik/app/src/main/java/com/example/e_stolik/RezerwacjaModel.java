package com.example.e_stolik;

import java.io.Serializable;

public class RezerwacjaModel implements Serializable {
    private String ID, login, nrStolika, data, godzinaRozpoczecia, godzinaZakonczenia, imie, nazwisko, nrTelefonu, zrezygnowane, przedawnione;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getnrStolika() {
        return nrStolika;
    }

    public void setnrStolika(String nrStolika) {
        this.nrStolika = nrStolika;
    }

    public String getdata() {
        return data;
    }

    public void setdata(String data) {
        this.data = data;
    }

    public String getgodzinaRozpoczecia() {
        return godzinaRozpoczecia;
    }

    public void setgodzinaRozpoczecia(String godzinaRozpoczecia) {
        this.godzinaRozpoczecia = godzinaRozpoczecia;
    }

    public String getgodzinaZakonczenia() {
        return godzinaZakonczenia;
    }

    public void setgodzinaZakonczenia(String godzinaZakonczenia) {
        this.godzinaZakonczenia = godzinaZakonczenia;
    }

    public String getimie() {
        return imie;
    }

    public void setimie(String imie) {
        this.imie = imie;
    }

    public String getnazwisko() {
        return nazwisko;
    }

    public void setnazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getnrTelefonu() {
        return nrTelefonu;
    }

    public void setnrTelefonu(String nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    public String getzrezygnowane() {
        return zrezygnowane;
    }

    public void setzrezygnowane(String zrezygnowane) {
        this.zrezygnowane = zrezygnowane;
    }

    public String getprzedawnione() {
        return przedawnione;
    }

    public void setprzedawnione(String przedawnione) {
        this.przedawnione = przedawnione;
    }
}