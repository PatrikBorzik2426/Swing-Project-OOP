package sk.borzik.gui;

import sk.borzik.Destinacia.Pamiatky;
import sk.borzik.Main;
import sk.borzik.Pouzivatelia.pouzivatel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Uprava_pamiatky extends JFrame {
    private JPanel panel1;
      private JTextField pamiatka_pridanie_text;
    private JButton pamiatka_pridanie_button;
    private JComboBox vsetky_pamiatky;
    private JButton odober_button;
    private JTextArea textArea;
    private JTextField pozicia;
    private JButton spat;

    public Uprava_pamiatky(String title, pouzivatel finalUser, String vyber) throws HeadlessException, IOException {
        super(title);

        setContentPane(panel1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 720);

        Pamiatky pamiatky_pomocne= new Pamiatky(null);
        pamiatky_pomocne.vypis_pamiatky(textArea,vyber,finalUser);
        pamiatky_pomocne.vloz_pamiatky(finalUser,vyber,vsetky_pamiatky);


        spat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login_output menu4 = new Login_output("Output", finalUser.getMeno());;
                Main.mainContainer.add(menu4.getContentPane(), "login_output");
                Main.cardLayout.show(Main.mainContainer, "login_output");
            }
        });

        odober_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pamiatky pamiatky_pomocne= new Pamiatky(null);
                try {
                    pamiatky_pomocne.vymaz_pamiatku(finalUser,vsetky_pamiatky,vyber);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                pamiatky_pomocne.vypis_pamiatky(textArea,vyber,finalUser);
            }
        });

        pamiatka_pridanie_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pamiatky pamiatky_pomocne= new Pamiatky(null);
                try {
                    pamiatky_pomocne.pridaj_pamiatku(finalUser,vyber, pamiatka_pridanie_text.getText(),Integer.parseInt(pozicia.getText()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                pamiatky_pomocne.vypis_pamiatky(textArea,vyber,finalUser);
            }
        });
    }
}
