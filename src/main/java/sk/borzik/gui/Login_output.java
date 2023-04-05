package sk.borzik.gui;

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
    private JTextPane text;
    private JPanel panel1;
    private JButton zrusenieButton;
    private JButton nova_rezervacia;
    private JComboBox comboBox1;

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
        comboBox1= pouzivatel_pomocny.pridanie_moznosti(comboBox1,user);

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

}}
