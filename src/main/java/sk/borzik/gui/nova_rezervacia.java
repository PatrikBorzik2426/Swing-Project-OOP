package sk.borzik.gui;

import sk.borzik.Destinacia.Dovolenka_rezorty;
import sk.borzik.Main;
import sk.borzik.Destinacia.destinacia;
import sk.borzik.Pouzivatelia.Admin;
import sk.borzik.Pouzivatelia.Rezervacie;
import sk.borzik.Pouzivatelia.pouzivatel;
import sk.borzik.Vozidla.vozidla;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.math.*;

public class nova_rezervacia extends JFrame {
    private JPanel formPanel;
    private JButton spat;
    private JButton rezervovat;
    private JComboBox destinacia_dropbox;
    private JTextField pocet_ludi_field;
    private JComboBox doprava_dropbox;
    private JTextPane output;
    private JLabel destinacia_label;
    private JLabel pocet_ludi;
    private JLabel doprava;
    private JButton prepocitaj_button;


    public nova_rezervacia(String title,pouzivatel finalUser) throws HeadlessException, IOException {
        super(title);

        setContentPane(formPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 720);

        pouzivatel user = finalUser;

        ArrayList<vozidla> vsetky_vozidla=new ArrayList<vozidla>();             //vytvorim si novy arraylist
        ArrayList<destinacia> vsetky_vylety=new ArrayList<destinacia>();        //vytvorim si novy arraylist

        destinacia destinacia = new destinacia(0,0,null,null);
        vsetky_vylety=destinacia.deserializuj();                                //nacitame všetky mozne destinácie
        vsetky_vozidla=vozidla.deserializuj();
        ArrayList<destinacia> finalVsetky_vylety = vsetky_vylety;
        ArrayList<vozidla> finalVsetky_vozidla = vsetky_vozidla;

        destinacia.roztried_rezorty(vsetky_vylety,user,destinacia_dropbox);                                     //roztriedime vsetky destinacie podla ceny
        vozidla.roztried(vsetky_vozidla,doprava_dropbox);


        spat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Login_output menu4 = new Login_output("Output", user.getMeno());;
                Main.mainContainer.add(menu4.getContentPane(), "login_output");
                Main.cardLayout.show(Main.mainContainer, "login_output");
            }
        });

        prepocitaj_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               destinacia.prepocitaj_cenu(pocet_ludi_field,destinacia_dropbox,doprava_dropbox,finalVsetky_vylety,finalVsetky_vozidla,output);
            }
        });

        rezervovat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pouzivatel pouzivatel=new pouzivatel(0,0,null,null,null,null, null);
                    pouzivatel.odpocitaj_kupu(pocet_ludi_field,destinacia_dropbox,doprava_dropbox,finalVsetky_vylety,finalVsetky_vozidla,output,user);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }
}
