package sk.borzik.Observer;

import sk.borzik.Destinacia.destinacia;

import javax.swing.*;
import java.util.ArrayList;

public interface Pozorovatel {
    public void aktualizuj(int vek, double rozpocet, String meno, String ulica, String typ, String password, ArrayList<destinacia> rezervacie, JTextArea text);
}
