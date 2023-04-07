package sk.borzik.Destinacia;

import sk.borzik.Pouzivatelia.pouzivatel;
import sk.borzik.Vozidla.vozidla;

import javax.swing.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class destinacia implements Serializable {
    private double cena_za_osobu;
    private int hodnotenie;
    private String nazov_rezortu;
    private ArrayList<Pamiatky> pamiatky;

    public destinacia(double cena_za_osobu, int hodnotenie, String nazov_rezortu, ArrayList<Pamiatky> pamiatky){
        this.cena_za_osobu=cena_za_osobu;
        this.hodnotenie=hodnotenie;
        this.nazov_rezortu=nazov_rezortu;
        this.pamiatky=pamiatky;
    }

    public double getCena_za_osobu() {
        return cena_za_osobu;
    }

    public int getHodnotenie() {
        return hodnotenie;
    }

    public String getNazov_rezortu() {
        return nazov_rezortu;
    }

    public ArrayList<Pamiatky> getPamiatky() {
        return pamiatky;
    }



    public void setCena_za_osobu(double cena_za_osobu){
        this.cena_za_osobu=cena_za_osobu;
    }

    public void setHodnotenie(int hodnotenie){
        this.hodnotenie=hodnotenie;
    }

    public void setNazov_rezortu(String nazov_rezortu){
        this.nazov_rezortu=nazov_rezortu;
    }

    public void setPamiatky(ArrayList pamiatky){
        this.pamiatky=pamiatky;
    }



    public void serializuj(){
        ArrayList<destinacia> vsetky_destinacie = new ArrayList<destinacia>();

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
    }

    public ArrayList<destinacia> deserializuj(){
        ArrayList<destinacia> vsetky_destinacie = new ArrayList<destinacia>();

        try {
            FileInputStream fis = new FileInputStream("vsetky_vylety.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            vsetky_destinacie = (ArrayList<destinacia>) ois.readObject();
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
        return vsetky_destinacie;
    }

    public void serializuj(ArrayList<destinacia> vozidla) throws IOException {
        try{
            FileOutputStream fileOut = new FileOutputStream("vsetky_vylety.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(vozidla);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in vsetky_vylety.ser");
        } catch (IOException i){
            i.printStackTrace();
        }
    }

    public void roztried_rezorty(ArrayList<destinacia> vsetky_rezorty, pouzivatel pouzivatel, javax.swing.JComboBox destinacia_dropbox){
        switch (pouzivatel.getTyp()){
            case("Dobrodruh") :
                Zazitkovy_vylet.roztried_rezorty(vsetky_rezorty, destinacia_dropbox);
                return;
            case("Relax"):
                Dovolenka_rezorty.roztried_rezorty(vsetky_rezorty, destinacia_dropbox);
                return;
            case("Student"):
                Pamiatkove_cesty.roztried_rezorty(vsetky_rezorty, destinacia_dropbox);
                return;
        }

    }

    public void prepocitaj_cenu(javax.swing.JTextField pocet_ludi_field, javax.swing.JComboBox destinacia_dropbox, javax.swing.JComboBox doprava_dropbox, ArrayList<destinacia> finalVsetky_vylety, ArrayList<vozidla> finalVsetky_vozidla, javax.swing.JTextPane output){
        int index1 = 0, index2=0;                                                                                         //berieme hodnoty z GUI
        int pocet_ludi= Integer.parseInt(pocet_ludi_field.getText());
        String destinacia_nazov= destinacia_dropbox.getSelectedItem().toString();
        String doprava= doprava_dropbox.getSelectedItem().toString();

        for(int i = 0; i< finalVsetky_vylety.size(); i++){
            if(destinacia_nazov== finalVsetky_vylety.get(i).getNazov_rezortu()) {
                index1 = i;                                                                                      //prehľadáme list a hľadáme výberv uživateľa
            }
        }

        for(int x = 0; x< finalVsetky_vozidla.size(); x++){
            if(doprava== finalVsetky_vozidla.get(x).sposob_dopravy){
                index2=x;
            }
        }

        BigDecimal bd = new BigDecimal(finalVsetky_vylety.get(index1).getCena_za_osobu()*pocet_ludi + 10*finalVsetky_vozidla.get(index2).cena_za_kilometer).setScale(2, BigDecimal.ROUND_HALF_UP); //zaokrúhľujeme na 2 desatinné miesta

        output.setText("Cena registrácie je : " + bd + "€" + "  |  BEZ ZĽAVY (ZĽAVA SA APLIKUJE AŽ PRI KÚPE)");          //vypíšeme cenu registrácie
    }

    public void ohodnotit(){

    }

    public void vloz_rezervacie(JComboBox rezervacie_box, ArrayList<destinacia> finalVsetky_rezervacie){
        for(int i = 0; i< finalVsetky_rezervacie.size(); i++){
            rezervacie_box.addItem(finalVsetky_rezervacie.get(i).getNazov_rezortu());
        }
    }


}
