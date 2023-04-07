package sk.borzik.Pouzivatelia;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public interface autorita {
     void pridaj_rezervaciu(String login, String destinacia) throws IOException, ClassNotFoundException;
     default void pozri_requesty(JComboBox vyber) throws IOException, ClassNotFoundException{
          ArrayList<Rezervacie> vsetky_rezervacie= new ArrayList<>();
          Admin admin=new Admin(51,0,"admin","admin","admin","admin");

          vsetky_rezervacie=admin.odserializuj_request();
          if(vsetky_rezervacie==null){
               return;
          }
          for(Rezervacie rezervacia:vsetky_rezervacie){
               vyber.addItem(rezervacia.login+ "-->" + rezervacia.destinacia);
          }
     }

}
