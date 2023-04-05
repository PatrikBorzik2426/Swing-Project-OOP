package sk.borzik.Pouzivatelia;

import sk.borzik.Destinacia.destinacia;
import sk.borzik.Main;
import sk.borzik.VlastnaExpetion;
import sk.borzik.Vozidla.vozidla;
import sk.borzik.gui.Admin_menu;
import sk.borzik.gui.Login_output;

import javax.swing.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;


public class pouzivatel implements Serializable {
    private int vek;
    private double rozpocet;
    private String meno;
    private String ulica;
    private String typ;
    private String password;
    private ArrayList<destinacia> rezervacie = new ArrayList<destinacia>(); // VIKARTOVCE
    private zlava zlava;

    public pouzivatel(int vek, double rozpocet, String meno, String ulica, String typ, String password, ArrayList<destinacia> rezervacie){
        this.rozpocet=rozpocet;
        this.meno=meno;
        this.vek=vek;
        this.ulica=ulica;
        this.typ=typ;
        this.password=password;
        this.rezervacie=rezervacie;
    }

    public int getVek() {
        return vek;
    }
    public double getRozpocet() {
        return rozpocet;
    }
    public String getMeno() {
        return meno;
    }
    public String getUlica() {
        return ulica;
    }

    public String getTyp() {
        return typ;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<destinacia> getRezervacie() {
        return rezervacie;
    }

    public zlava getZlava() {
        return zlava;
    }

    public void setZlava(zlava zlava) {
        this.zlava = zlava;
    }
    public void setVek(int vek) {
        this.vek = vek;
    }
    public void setRozpocet(double rozpocet) {
        this.rozpocet = rozpocet;
    }
    public void setMeno(String meno) {
        this.meno = meno;
    }
    public void setUlica(String ulica) {
        this.ulica = ulica;
    }
    public void setTyp(String typ) {
        this.typ = typ;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRezervacie(ArrayList<destinacia> rezervacie) {
        this.rezervacie = rezervacie;
    }
    

    public String typ_zlavy(){      //vypíše typ zľavy akú má objekt
        return  zlava.zlava();
    }
    public void nastavenie_zlavy(zlava new_zlava){      //slúži na dinamickú zmenu zľavy
        zlava=new_zlava;
    }

    public  void serializuj(int vek, double rozpocet, String meno, String ulica, String typ, String password, ArrayList<destinacia> destinacie) throws IOException {
        pouzivatel pouzivatel = new pouzivatel(vek, rozpocet, meno, ulica, typ, password,destinacie);

        try{
            FileOutputStream fileOut = new FileOutputStream(meno+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(pouzivatel);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in "+meno+".ser");
        } catch (IOException i){
            i.printStackTrace();
        }
    }

    public pouzivatel odserializuj(String meno) throws IOException, ClassNotFoundException {
        pouzivatel pouzivatel = new pouzivatel(0,0,null,null,null, null, null);
        try{
            FileInputStream fileIn = new FileInputStream(meno+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            pouzivatel = (pouzivatel) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i){
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c){
            System.out.println("pouzivatel class not found");
            c.printStackTrace();
            return null;
        }
        return pouzivatel;
    }


    public static double zlava(double cena){
        double zlava=1;
        cena=cena*zlava;
        return cena;
    }

    public void vek_nie_je_nula(int vek){
        try{
            if(vek<0 || vek>100) {
                throw new VlastnaExpetion("Vek musi byt v rozmedzi 0-100");
            }
        } catch (VlastnaExpetion e) {
            throw new RuntimeException(e);
        }
    }

    public  void urci_typ(JTextField textmeno, JTextField textulica, JTextField textvek, JTextField textrozpocet, JComboBox dropdowntyp, JPasswordField textheslo){
        String meno = textmeno.getText();
        String ulica = textulica.getText();
        String heslo = textheslo.getText();
        int vek = Integer.parseInt(textvek.getText());
        double rozpocet = Double.parseDouble(textrozpocet.getText());
        String typ = dropdowntyp.getSelectedItem().toString();
        ArrayList<destinacia> arrayList=new ArrayList<destinacia>();


        switch (typ){
            case "Dobrodruh":
                Dobrodruh dobrodruh = new Dobrodruh(vek, rozpocet, meno, ulica, typ, heslo, arrayList);
                dobrodruh.vek_nie_je_nula(vek);

                try {
                    dobrodruh.serializuj(vek, rozpocet, meno, ulica, typ, heslo, arrayList);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                break;
            case "Relax":
                Relax relax = new Relax(vek, rozpocet, meno, ulica, typ, heslo, arrayList);
                relax.vek_nie_je_nula(vek);

                try{
                    relax.serializuj(vek, rozpocet, meno, ulica, typ, heslo,arrayList);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;

            case "Student":
                Student student = new Student(vek, rozpocet, meno, ulica, typ, heslo, arrayList);
                student.vek_nie_je_nula(vek);

                try{
                    student.serializuj(vek, rozpocet, meno, ulica, typ, heslo,arrayList);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
        }
        Main.cardLayout.show(Main.mainContainer, "loginMenu");
    }

    public  void odpocitaj_kupu(javax.swing.JTextField pocet_ludi_field, javax.swing.JComboBox destinacia_dropbox, javax.swing.JComboBox doprava_dropbox, ArrayList<destinacia> finalVsetky_vylety, ArrayList<vozidla> finalVsetky_vozidla,javax.swing.JTextPane output, pouzivatel user) throws IOException, ClassNotFoundException {
        int index1 = 0, index2=0;                                                                                         //berieme hodnoty z GUI
        int pocet_ludi= Integer.parseInt(pocet_ludi_field.getText());
        String destinacia_nazov= destinacia_dropbox.getSelectedItem().toString();
        String doprava= doprava_dropbox.getSelectedItem().toString();

        for(int i = 0; i< finalVsetky_vylety.size(); i++){
            if(destinacia_nazov== finalVsetky_vylety.get(i).getNazov_rezortu()) {
                index1 = i;                                                                                      //prehľadáme list a hľadáme výberv uživateľa
            }
        }

        for(int x = 0; x< finalVsetky_vozidla.size(); x++){
            if(doprava== finalVsetky_vozidla.get(x).sposob_dopravy){
                index2=x;
            }
        }

        BigDecimal bd = new BigDecimal(finalVsetky_vylety.get(index1).getCena_za_osobu()*pocet_ludi + 10*finalVsetky_vozidla.get(index2).cena_za_kilometer).setScale(2, BigDecimal.ROUND_HALF_UP); //zaokrúhľujeme na 2 desatinné miesta

        switch (user.typ){
            case "Dobrodruh":
                bd= BigDecimal.valueOf(Dobrodruh.zlava(bd.doubleValue()));
                break;
            case "Relax":
                bd= BigDecimal.valueOf(Relax.zlava(bd.doubleValue()));
                break;
            case "Student":
                bd= BigDecimal.valueOf(Student.zlava(bd.doubleValue()));
                break;
        }

        if(user.rozpocet-bd.doubleValue()<0){
            output.setText("Nemáte dostatok peňazí na túto destináciu!");
            return;
        }

        user.rozpocet=user.rozpocet-bd.doubleValue();                                                               //odčítame cenu registrácie od rozpočtu
        destinacia pridanie_destinacie= finalVsetky_vylety.get(index1);                                             //vytvoríme novú destináciu
        user.rezervacie.add(pridanie_destinacie);                                                                   //pridáme destináciu do listu rezervovaných destinácií

        output.setText("Budget je: " + user.rozpocet + "€");

        try {
            user.serializuj(user.vek, user.rozpocet, user.meno,user.ulica,user.typ, user.password, user.rezervacie);                                                                                      //serializujeme usera
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Admin admin=new Admin(0,0,null,null,null,null);

        try {
            admin.pridaj_rezervaciu(user.meno, destinacia_nazov);
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }

    public  void over_pouzivatela(pouzivatel user, String password) throws IOException, ClassNotFoundException {
        if(user.password.equals(password)){
            System.out.println("Podarilo sa nájsť usera! \n");

            Class<? extends pouzivatel> userClass = user.getClass();                        //Jednoduché využiie získania triedy z objektu pomocou RTTI (Run-Time Type Identification)

            if(userClass == Admin.class) {
                user.rozpocet=1000000000;

                Admin_menu menu2 = new Admin_menu("admin_menu");
                Main.mainContainer.add(menu2.getContentPane(), "admin_menu");
                Main.cardLayout.show(Main.mainContainer, "admin_menu");
            }else {
                Login_output menu3 = new Login_output("Login_output", user.meno);
                Main.mainContainer.add(menu3.getContentPane(), "login_output");
                Main.cardLayout.show(Main.mainContainer, "login_output");
            }

        } else {
            System.out.println("Nespravne heslo");
        }
    }

    public  JComboBox pridanie_moznosti(JComboBox moznosti, pouzivatel user){
        for(int i=0; i<user.rezervacie.size();i++){
            moznosti.addItem(user.rezervacie.get(i).getNazov_rezortu());
        }
        return moznosti;
    }

}
