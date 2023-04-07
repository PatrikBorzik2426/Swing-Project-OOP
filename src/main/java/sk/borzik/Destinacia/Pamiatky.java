package sk.borzik.Destinacia;

import sk.borzik.Pouzivatelia.pouzivatel;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Pamiatky implements Serializable {
    private String nazov_pamiatky;

    public Pamiatky(String nazov_pamiatky) {
        this.nazov_pamiatky = nazov_pamiatky;
    }

    public String getNazov_pamiatky() {
        return nazov_pamiatky;
    }

    public void setNazov_pamiatky(String nazov_pamiatky) {
        this.nazov_pamiatky = nazov_pamiatky;
    }

    public void vypis_pamiatky(JTextArea tabulka_na_vypis, String destinacia, pouzivatel user) {
        String uz_vypisane = tabulka_na_vypis.getText() + "\n\nPamiatky: \n";
        ArrayList<destinacia> destinacie = user.getRezervacie();


        for (int i = 0; i < destinacie.size(); i++) {
            if (user.getRezervacie().get(i).getNazov_rezortu().equals(destinacia)) {
                ArrayList<Pamiatky> pamiatky = user.getRezervacie().get(i).getPamiatky();
                for (Pamiatky pamiatka : pamiatky) {
                    uz_vypisane = uz_vypisane + pamiatka.getNazov_pamiatky() + " --> ";
                }
                break;
            }
        }

        tabulka_na_vypis.setText(uz_vypisane);
    }

    public void vloz_pamiatky(pouzivatel user, String destinacia, JComboBox box) {
        ArrayList<destinacia> rezervacie = user.getRezervacie();

        for (destinacia rezervacia : rezervacie) {
            if (rezervacia.getNazov_rezortu().equals(destinacia)) {
                ArrayList<Pamiatky> pamiatky = rezervacia.getPamiatky();
                for (Pamiatky pamiatka : pamiatky) {
                    box.addItem(pamiatka.getNazov_pamiatky());
                }
                break;
            }
        }
    }

    public void vymaz_pamiatku(pouzivatel user, JComboBox box, String destinacia) throws IOException {
        ArrayList<destinacia> rezervacie = user.getRezervacie();

        for (destinacia rezervacia : rezervacie) {
            if (rezervacia.getNazov_rezortu().equals(destinacia)) {
                ArrayList<Pamiatky> pamiatky = rezervacia.getPamiatky();
                for (Pamiatky pamiatka : pamiatky) {
                    if (pamiatka.getNazov_pamiatky().equals(box.getSelectedItem().toString())) {
                        pamiatky.remove(pamiatka);
                        user.serializuj(user.getVek(), user.getRozpocet(), user.getMeno(), user.getUlica(), user.getTyp(), user.getPassword(), user.getRezervacie());
                        break;
                    }
                }
                break;
            }
        }
    }

    public void pridaj_pamiatku(pouzivatel user, String destinacia, String nazov_pamiatky, int pozicia) throws IOException {
        ArrayList<destinacia> rezervacie = user.getRezervacie();
        for (destinacia rezervacia : rezervacie) {
            if (rezervacia.getNazov_rezortu().equals(destinacia)) {
                ArrayList<Pamiatky> pamiatky = rezervacia.getPamiatky();
                pamiatky.add(pozicia - 1, new Pamiatky(nazov_pamiatky));
                user.serializuj(user.getVek(), user.getRozpocet(), user.getMeno(), user.getUlica(), user.getTyp(), user.getPassword(), user.getRezervacie());
                break;
            }

        }
    }
}
