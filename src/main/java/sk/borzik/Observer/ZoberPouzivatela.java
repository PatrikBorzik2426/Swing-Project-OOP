package sk.borzik.Observer;

import sk.borzik.Destinacia.destinacia;

import javax.swing.*;
import java.util.ArrayList;

public class ZoberPouzivatela implements Predmet {

    private ArrayList<Pozorovatel> pozorovatelia = new ArrayList<Pozorovatel>();
    private int vek;
    private double rozpocet;
    private String meno;
    private String ulica;
    private String typ;
    private String password;
    private ArrayList<destinacia> rezervacie;

    public ZoberPouzivatela() {
    }

    @Override
    public void pridajPozorovatela(Pozorovatel novy_pozorovatel) {
        pozorovatelia.add(novy_pozorovatel);
    }

    @Override
    public void odstranPozorovatela(Pozorovatel vymazovany_pozorovatel) {
        int pozorovatelIndex=pozorovatelia.indexOf(vymazovany_pozorovatel);
        pozorovatelia.remove(pozorovatelIndex);
    }

    @Override


    public void upozorniPozorovatelov(JTextPane textPane) {       //prechádzame všetkými observermi v ArrayListe a aktualizujeme ich hodnoty
        for(Pozorovatel pozorovatel: pozorovatelia){
            pozorovatel.aktualizuj(vek, rozpocet, meno, ulica, typ, password, rezervacie, textPane);
        }
    }

    public void nastavPouzivatela(int vek, double rozpocet, String meno, String ulica, String typ, String password, ArrayList<destinacia> rezervacie, JTextPane text){
        this.vek=vek;
        this.rozpocet=rozpocet;
        this.meno=meno;
        this.ulica=ulica;
        this.typ=typ;
        this.password=password;
        this.rezervacie=rezervacie;
        upozorniPozorovatelov(text);            // Upozorní observerov
    }
}

