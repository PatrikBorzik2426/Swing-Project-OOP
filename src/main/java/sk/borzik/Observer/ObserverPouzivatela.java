package sk.borzik.Observer;

import sk.borzik.Destinacia.destinacia;

import javax.swing.*;
import java.util.ArrayList;

public class ObserverPouzivatela implements Pozorovatel {

    private int vek;
    private double rozpocet;
    private String meno;
    private String ulica;
    private String typ;
    private String password;
    private ArrayList<destinacia> rezervacie;

    private static int observerIDTracker=0;

    private int observerID;

    private Predmet ZoberPouzivatela;

    public ObserverPouzivatela(Predmet ZoberPouzivatela){
        this.ZoberPouzivatela=ZoberPouzivatela;
        this.observerID=++observerIDTracker;
        ZoberPouzivatela.pridajPozorovatela(this);
    }

    @Override
    public void aktualizuj(int vek, double rozpocet, String meno, String ulica, String typ, String password, ArrayList<destinacia> rezervacie, JTextArea text) {
        this.vek=vek;
        this.rozpocet=rozpocet;
        this.meno=meno;
        this.ulica=ulica;
        this.typ=typ;
        this.password=password;
        this.rezervacie=rezervacie;

        printZmeny(rezervacie, text);
    }

    public void printZmeny(ArrayList<destinacia> rezervacie, JTextArea text) {
        String rezorty = "";

        for (int i = 0; i < rezervacie.size(); i++) {
            rezorty = rezorty + rezervacie.get(i).getNazov_rezortu() + " | ";
        }

        text.setText("Vek: " + vek + "\n" + "Rozpocet: " + rozpocet + "\n" + "Meno: " + meno + "\n" + "Mesto: " + ulica + "\n" + "Typ: " + typ + "\n" + "Heslo: " + password + "\n" + "Rezervacie: " + rezorty);

    }


}

