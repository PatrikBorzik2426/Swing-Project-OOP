package sk.borzik.Observer;

import javax.swing.*;

public interface Predmet {

    public void pridajPozorovatela(Pozorovatel pozorovatel);
    public void odstranPozorovatela(Pozorovatel pozorovatel);
    public void upozorniPozorovatelov(JTextArea textPane);



}
