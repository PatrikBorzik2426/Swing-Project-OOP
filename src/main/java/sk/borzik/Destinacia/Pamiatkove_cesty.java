package sk.borzik.Destinacia;

import java.util.ArrayList;

public class Pamiatkove_cesty extends destinacia  {
    private String typ="pamiatkove_cesty";
    public Pamiatkove_cesty(double cena_za_osobu, int hodnotenie, String nazov_rezortu, ArrayList<Pamiatky> pamiatky) {
        super(cena_za_osobu, hodnotenie, nazov_rezortu, pamiatky);
    }

    public static void roztried_rezorty(ArrayList<destinacia> vsetky_vylety, javax.swing.JComboBox destinacia_dropbox){
        for(int i=0; i<vsetky_vylety.size(); i++){
            if(vsetky_vylety.get(i) instanceof Pamiatkove_cesty) {
                destinacia_dropbox.addItem(vsetky_vylety.get(i).getNazov_rezortu());        //ukazeme userovi iba tie, ktoré si môže dovoliť
            }
        }
    }


}
