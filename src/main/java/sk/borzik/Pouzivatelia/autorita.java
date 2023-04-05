package sk.borzik.Pouzivatelia;

import javax.swing.*;
import java.io.IOException;

public interface autorita {
     void pridaj_rezervaciu(String login, String destinacia) throws IOException, ClassNotFoundException;
     void pozri_requesty(JComboBox vyber) throws IOException, ClassNotFoundException;

}
