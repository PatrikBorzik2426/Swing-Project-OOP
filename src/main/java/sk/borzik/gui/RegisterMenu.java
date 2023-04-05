package sk.borzik.gui;

import sk.borzik.Destinacia.destinacia;
import sk.borzik.Main;
import sk.borzik.Pouzivatelia.Dobrodruh;
import sk.borzik.Pouzivatelia.Relax;
import sk.borzik.Pouzivatelia.Student;
import sk.borzik.Pouzivatelia.pouzivatel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterMenu extends JFrame {

    private JButton register_in;
    private JLabel meno;
    private JTextField textmeno;
    private JTextField textulica;
    private JTextField textvek;
    private JLabel ulica;
    private JLabel vek;
    private JLabel rozpocet;
    private JTextField textrozpocet;
    private JLabel typ;
    private JComboBox dropdowntyp;
    private JPanel formPanel;
    private JPasswordField textheslo;
    private JLabel heslo;

    public RegisterMenu(String title) throws HeadlessException {
        super(title);

        setContentPane(formPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 720);

        dropdowntyp.addItem("Dobrodruh");
        dropdowntyp.addItem("Relax");
        dropdowntyp.addItem("Student");

        register_in.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pouzivatel pouzivatel=new pouzivatel(0,0,null,null,null,null,null);
                pouzivatel.urci_typ(textmeno, textulica, textvek, textrozpocet,dropdowntyp,textheslo);
            }
        });

    }
}
