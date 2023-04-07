package sk.borzik.gui;

import sk.borzik.Destinacia.Pamiatky;
import sk.borzik.Destinacia.destinacia;
import sk.borzik.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import sk.borzik.Observer.ObserverPouzivatela;
import sk.borzik.Observer.ZoberPouzivatela;
import sk.borzik.Pouzivatelia.Admin;
import sk.borzik.Pouzivatelia.pouzivatel;

public class Login_output extends JFrame {
    private JTextArea text;
    private JPanel panel1;
    private JButton zrusenieButton;
    private JButton nova_rezervacia;
    private JComboBox comboBox1;
    private JButton zmena_pamiatky;
    private JComboBox pamiatky_box;
    private JButton vypis_pamiatky;

    public Login_output(String title, String login) throws HeadlessException{
        super(title);

        setContentPane(panel1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 720);

        pouzivatel user = null; String viac_riadkov="";


        try{
            pouzivatel pouzivatel = new pouzivatel(0,0,"","","","",null);
            user=pouzivatel.odserializuj(login);
        } catch (IOException ex) {
            System.out.println("Nepodarilo sa nacitat pouzivatela");
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Nepodarilo sa nacitat pouzivatela");
            throw new RuntimeException(ex);
        }
        pouzivatel pouzivatel_pomocny= new pouzivatel(0,0,"","","","",null);
        destinacia destinacia_pomocna= new destinacia(0,0,"",null);
        comboBox1= pouzivatel_pomocny.pridanie_moznosti(comboBox1,user);
        destinacia_pomocna.vloz_rezervacie(pamiatky_box,user.getRezervacie());



        ZoberPouzivatela pouzivatel= new ZoberPouzivatela();

        ObserverPouzivatela observer = new ObserverPouzivatela(pouzivatel);

        pouzivatel.nastavPouzivatela(user.getVek(),user.getRozpocet(),user.getMeno(),user.getUlica(),user.getTyp(),user.getPassword(),user.getRezervacie(),text);


        pouzivatel finalUser = user;

        nova_rezervacia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nova_rezervacia menu3 = null;
                try {
                    menu3 = new nova_rezervacia("Nova rezervacia", finalUser);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Main.mainContainer.add(menu3.getContentPane(), "nova_rezervacia");
                Main.cardLayout.show(Main.mainContainer, "nova_rezervacia");
            }
        });

        sk.borzik.Pouzivatelia.pouzivatel finalUser1 = user;
        zrusenieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Admin admin = new Admin(0,0,null, null, null, null);
                    admin.pridaj_rquest(finalUser1.getMeno(),comboBox1);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        vypis_pamiatky.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pamiatky pamiatka = new Pamiatky("");
                pamiatka.vypis_pamiatky(text, pamiatky_box.getSelectedItem().toString(), finalUser1);
            }
        });

        zmena_pamiatky.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Uprava_pamiatky menu4 = null;
                try {
                    menu4 = new Uprava_pamiatky("Uprava_pamiatky", finalUser, pamiatky_box.getSelectedItem().toString());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Main.mainContainer.add(menu4.getContentPane(), "Uprava_pamiatky");
                Main.cardLayout.show(Main.mainContainer, "Uprava_pamiatky");
            }
        });
}}
