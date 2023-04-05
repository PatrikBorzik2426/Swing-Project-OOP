package sk.borzik.Pouzivatelia;

import sk.borzik.Destinacia.destinacia;

import java.util.ArrayList;

public class Student extends pouzivatel{
    public Student(int vek, double rozpocet, String meno, String ulica, String typ, String password, ArrayList<destinacia> zoznam_destinacii){
        super(vek, rozpocet, meno, ulica, typ, password, zoznam_destinacii);
        setZlava(new ISIC_zlava()); // zadanie ISIC zlavy
    }

    public static double zlava(double cena){
        double zlava=0.5;
        cena=cena*zlava;
        return cena;
    }
}
