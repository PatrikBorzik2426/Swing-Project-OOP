package sk.borzik.Destinacia;

import java.util.ArrayList;

public class Zazitkovy_vylet extends destinacia{
    private String typ = "zazitkovy_vylet";
    public Zazitkovy_vylet(double cena_za_osobu, int hodnotenie, String nazov_rezortu, ArrayList<Pamiatky> pamiatky) {
        super(cena_za_osobu, hodnotenie, nazov_rezortu, pamiatky);
    }

    public static void roztried_rezorty(ArrayList<destinacia> vsetky_vylety, javax.swing.JComboBox destinacia_dropbox){
        for(int i=0; i<vsetky_vylety.size(); i++){
            if(vsetky_vylety.get(i) instanceof Zazitkovy_vylet) {
                destinacia_dropbox.addItem(vsetky_vylety.get(i).getNazov_rezortu());        //ukazeme userovi iba tie, ktoré si môže dovoliť
            }
        }
    }
}
