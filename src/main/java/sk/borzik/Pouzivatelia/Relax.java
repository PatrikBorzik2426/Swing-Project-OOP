package sk.borzik.Pouzivatelia;

import sk.borzik.Destinacia.destinacia;

import java.util.ArrayList;

public class Relax extends pouzivatel{
    public Relax(int vek, double rozpocet, String meno, String ulica, String typ, String password, ArrayList<destinacia> zoznam_destinacii) {
        super(vek, rozpocet, meno, ulica, typ, password, zoznam_destinacii);
        setZlava(new ziadna_zlava());
        }

    public static double zlava(double cena){
        double zlava=0.6;
        cena=cena*zlava;
        return cena;
    }


}
