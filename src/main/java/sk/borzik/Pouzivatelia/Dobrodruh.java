package sk.borzik.Pouzivatelia;


import sk.borzik.Destinacia.Zazitkovy_vylet;
import sk.borzik.Destinacia.destinacia;

import java.util.ArrayList;

public class Dobrodruh extends pouzivatel{
    public Dobrodruh(int vek, double rozpocet, String meno, String ulica, String typ, String password, ArrayList<destinacia> zoznam_destinacii) {
        super(vek, rozpocet, meno, ulica, typ, password, zoznam_destinacii);
        setZlava(new ziadna_zlava());   //nastavenie zľavy
    }
}
