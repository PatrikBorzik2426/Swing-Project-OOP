package sk.borzik.gui;

import sk.borzik.Pouzivatelia.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Admin_menu extends JFrame {
    private JButton vypis;
    private JButton potvrd;
    private JButton zamietnut;
    private JPanel panel1;
    private JComboBox moznosti;
    private JTextArea textField;

    public Admin_menu(String title) throws IOException, ClassNotFoundException {
        super(title);

        setContentPane(panel1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 720);

        Admin admin=new Admin(1,10,null,null,null,null);
        admin.pozri_requesty(moznosti);


        vypis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin admin = new Admin(0,0,"","","","");
                try {
                    admin.vypis_rezervacie(textField);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        potvrd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin admin = new Admin(0,0,"","","","");
                try {
                    admin.suhlas_vymazanie(moznosti,admin.odserializuj_request());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        zamietnut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin admin = new Admin(0,0,"","","","");
                try {
                    admin.nesuhlas_vymazanie(moznosti,admin.odserializuj_request());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }
    }
