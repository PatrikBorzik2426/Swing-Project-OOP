package sk.borzik.gui;

import sk.borzik.Main;
import sk.borzik.Pouzivatelia.pouzivatel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginMenu extends JFrame {
    private JPanel formPanel;
    private JTextField Login_user;
    private JButton button_register;
    private JTextField Password_user;
    private JButton login_pls_podz;


    public LoginMenu(String title) throws HeadlessException{
        super(title);

        setContentPane(formPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 720);

        login_pls_podz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = Login_user.getText();
                String password = Password_user.getText();

                pouzivatel user = new pouzivatel(0,0,null,null,null,null,null);

                try{
                    user=user.odserializuj(login);
                } catch (IOException ex) {
                    System.out.println("Nepodarilo sa nacitat pouzivatela");
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    System.out.println("Nepodarilo sa nacitat pouzivatela");
                    throw new RuntimeException(ex);
                }

                try {
                    user.over_pouzivatela(user,user.getPassword());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        button_register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Main.cardLayout.show(Main.mainContainer, "registerMenu");
            }
        });

    }


}
