package sk.borzik.Pouzivatelia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Rezervacie implements Serializable {
    String login;
    String destinacia;


    public Rezervacie(String login, String destinacia) {
        this.login= login;
        this.destinacia= destinacia;
    }


}


