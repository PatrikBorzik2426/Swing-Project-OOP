package sk.borzik.Vozidla;

import sk.borzik.Destinacia.destinacia;

import java.io.*;
import java.util.ArrayList;

public class vozidla implements Vozidla_interface, Serializable {
    public String sposob_dopravy;
    public int cena_za_kilometer;
    public double rychlosť_dopravy;
    public int kapacita;
    public int dostuponost;
    public vozidla(String sposob_dopravy, int cena_za_kilometer, double rychlosť_dopravy, int kapacita, int dostuponost){
     this.sposob_dopravy=sposob_dopravy;
     this.cena_za_kilometer=cena_za_kilometer;
     this.rychlosť_dopravy=rychlosť_dopravy;
     this.kapacita=kapacita;
     this.dostuponost=dostuponost;
    }

    public void pridaj_vozidlo(){
        System.out.println("Pridavam vozidlo");
    }
    public void vypis_dostupne(){
        System.out.println("Vypisujem dostupne");
    }


    public static void serializuj(String sposob,int cena_za_kilometer, double rychlosť_dopravy, int kapacita, int dostuponost) throws IOException {
        vozidla vozidla = new vozidla(sposob, cena_za_kilometer, rychlosť_dopravy,kapacita,dostuponost);

        try{
            FileOutputStream fileOut = new FileOutputStream(sposob+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(vozidla);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in "+sposob+".ser");
        } catch (IOException i){
            i.printStackTrace();
        }
    }

    public static vozidla odserializuj(String sposob,int cena_za_kilometer, double rychlosť_dopravy, int kapacita, int dostuponost) throws IOException {
        vozidla vozidla = new vozidla(sposob, cena_za_kilometer, rychlosť_dopravy,kapacita,dostuponost);

        try{
            FileInputStream fileIn = new FileInputStream(sposob+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            vozidla = (vozidla) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i){
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c){
            System.out.println("pouzivatel class not found");
            c.printStackTrace();
            return null;
        }
        return vozidla;
    }

        public static ArrayList<vozidla> deserializuj(){
        ArrayList<vozidla> vsetky_vozidla = new ArrayList<vozidla>();

        try {
            FileInputStream fis = new FileInputStream("vsetky_vozidla.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            vsetky_vozidla = (ArrayList<vozidla>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
        return vsetky_vozidla;
    }

    public static void serializuj(ArrayList<vozidla> vozidla) throws IOException {
        try{
            FileOutputStream fileOut = new FileOutputStream("vsetky_vozidla.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(vozidla);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in vsetky_vozidla.ser");
        } catch (IOException i){
            i.printStackTrace();
        }
    }

    public static void roztried(ArrayList<vozidla> vsetky_vozidla, javax.swing.JComboBox vozidlo_dropbox){
        for(int i=0; i<vsetky_vozidla.size(); i++){
            if(vsetky_vozidla.get(i).dostuponost==1) {
                vozidlo_dropbox.addItem(vsetky_vozidla.get(i).sposob_dopravy);        //ukazeme userovi iba tie, ktoré si môže dovoliť
            }
        }
    }

}
