package sk.borzik;

import java.io.*;
import java.util.ArrayList;
import java.util.*;

import sk.borzik.Destinacia.Dovolenka_rezorty;
import sk.borzik.Destinacia.Pamiatkove_cesty;
import sk.borzik.Destinacia.Zazitkovy_vylet;
import sk.borzik.Destinacia.destinacia;
import sk.borzik.Observer.Pozorovatel;
import sk.borzik.Pouzivatelia.Admin;
import sk.borzik.Pouzivatelia.pouzivatel;
import sk.borzik.Vozidla.vozidla;

public class funkcie {

    public static void main(String[] args) throws IOException {


        BufferedReader reader = null;
        Random nahodka=new Random();

        ArrayList<destinacia> vsetky_destinacie = new ArrayList<destinacia>();



        try {
            reader = new BufferedReader(new FileReader("zazitkove_vylety.txt"));

            String line = reader.readLine();

            while (line != null) {
                double cena_za_osobu=nahodka.nextDouble(300);
                Zazitkovy_vylet vylet = new Zazitkovy_vylet(cena_za_osobu, 0, line, null);
                vsetky_destinacie.add(vylet);

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            reader = new BufferedReader(new FileReader("pamiatkove_cesty.txt"));

            String line = reader.readLine();

            while (line != null) {
                double cena_za_osobu=nahodka.nextDouble(300);
                Pamiatkove_cesty vylet = new Pamiatkove_cesty(cena_za_osobu, 0, line, null);
                vsetky_destinacie.add(vylet);

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            reader = new BufferedReader(new FileReader("dovolenka_rezorty.txt"));

            String line = reader.readLine();

            while (line != null) {
                double cena_za_osobu=nahodka.nextDouble(300);
                Dovolenka_rezorty vylet = new Dovolenka_rezorty(cena_za_osobu, 0, line, null);
                vsetky_destinacie.add(vylet);

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream("vsetky_vylety.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vsetky_destinacie);
            oos.close();
            fos.close();
            System.out.println("ArrayList serialized to list.ser");

        } catch (IOException e) {
            e.printStackTrace();
        }



        Admin admin=new Admin(20,10000,"admin","admin","admin","1");

        try{
            FileOutputStream fileOut = new FileOutputStream("admin.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(admin);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in "+".ser");
        } catch (IOException i){
            i.printStackTrace();
        }


        ArrayList<vozidla>vsetky_vozidla=new ArrayList<vozidla>();

        vsetky_vozidla.add(new vozidla("auto",120,50,100,1));
        vsetky_vozidla.add(new vozidla("autobus",100,40,58,1));
        vsetky_vozidla.add(new vozidla("lietadlo",350,120,200,1));

        try{
            FileOutputStream fos = new FileOutputStream("vsetky_vozidla.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vsetky_vozidla);
            oos.close();
            fos.close();
            System.out.println("ArrayList serialized to list.ser");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

