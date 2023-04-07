package sk.borzik;

import sk.borzik.Destinacia.Zazitkovy_vylet;
import sk.borzik.Pouzivatelia.Dobrodruh;
import sk.borzik.Pouzivatelia.pouzivatel;
import sk.borzik.Vozidla.Autobusova_doprava;
import sk.borzik.Vozidla.Letecka_doprava;
import sk.borzik.Vozidla.vozidla;
import sk.borzik.gui.LoginMenu;
import sk.borzik.gui.Login_output;
import sk.borzik.gui.RegisterMenu;

import javax.swing.*;
import javax.swing.UIManager;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static JFrame mainFrame;
    public static CardLayout cardLayout;
    public static Container mainContainer;

    public static void main(String[] args) {

        mainFrame = new JFrame("Mulitfunkčný plánovač");
        mainFrame.setSize(720, 720);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        mainFrame.setLayout(cardLayout);
        mainContainer = mainFrame.getContentPane();

        LoginMenu menu = new LoginMenu("Title neviem aky");
        RegisterMenu menu2= new RegisterMenu("Novy crazy panel");

        mainContainer.add(menu.getContentPane(), "loginMenu");
        mainContainer.add(menu2.getContentPane(), "registerMenu");
        mainFrame.setVisible(true);

        cardLayout.show(mainContainer, "loginMenu");



    };
}
