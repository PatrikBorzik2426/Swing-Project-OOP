package sk.borzik.Pouzivatelia;

import com.sun.net.httpserver.Request;
import sk.borzik.Destinacia.destinacia;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Admin extends pouzivatel implements autorita{

    public Admin(int vek, double rozpocet, String meno, String ulica, String typ, String password) {
        super(vek, rozpocet, meno, ulica, typ, password,null);

        setZlava(new admin_zlava());
    }

    public  void serializuj(ArrayList<Rezervacie> vsetky_rezervacie) throws IOException {
        try{
            FileOutputStream fileOut = new FileOutputStream("vsetky_rezervacie.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(vsetky_rezervacie);
            out.close();
            fileOut.close();
        } catch (IOException i){
            i.printStackTrace();
        }
    }
    public  ArrayList<Rezervacie> odserializuj() throws IOException, ClassNotFoundException {
            ArrayList<Rezervacie> rezervacie = new ArrayList<Rezervacie>();
        try{
            FileInputStream fileIn = new FileInputStream("vsetky_rezervacie.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            rezervacie = (ArrayList<Rezervacie>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i){
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c){
            System.out.println("rezervacie class not found");
            c.printStackTrace();
            return null;
        }
        return rezervacie;
    }

    public  void serializuj_request(ArrayList<Rezervacie> vsetky_requesty) throws IOException {
        try{
            FileOutputStream fileOut = new FileOutputStream("vsetky_requesty.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(vsetky_requesty);
            out.close();
            fileOut.close();
        } catch (IOException i){
            i.printStackTrace();
        }
    }
    public  ArrayList<Rezervacie> odserializuj_request() throws IOException, ClassNotFoundException {
        ArrayList<Rezervacie> rezervacie = new ArrayList<Rezervacie>();
        try{
            FileInputStream fileIn = new FileInputStream("vsetky_requesty.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            rezervacie = (ArrayList<Rezervacie>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i){
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c){
            System.out.println("rezervacie class not found");
            c.printStackTrace();
            return null;
        }
        return rezervacie;
    }

    public void vypis_rezervacie(JTextArea textObrazovka) throws IOException, ClassNotFoundException {
        ArrayList<Rezervacie> rezervacie=odserializuj();
        String vypis="";

        for(Rezervacie rezervacia:rezervacie){
            vypis=vypis+(rezervacia.login+"   -->    "+rezervacia.destinacia+"\n");

        }
        textObrazovka.setText("VŠETKY REGISTRÁCIE: (fromát | USER | DESTINACIA)\n"+"--------------------------------------------------------+\n"+vypis);
    }

    public void pridaj_rezervaciu(String login, String destinacia) throws IOException, ClassNotFoundException {
        ArrayList<Rezervacie> vsetky_rezervacie = new ArrayList<>();
        vsetky_rezervacie=odserializuj();

        if(vsetky_rezervacie==null){
            vsetky_rezervacie=new ArrayList<>();
            vsetky_rezervacie.add(new Rezervacie(login,destinacia));
            serializuj(vsetky_rezervacie);
            return;
        }
        else {
            vsetky_rezervacie=odserializuj();
            vsetky_rezervacie.add(new Rezervacie(login,destinacia));
            serializuj(vsetky_rezervacie);
            return;
        }
    }

    public  void pridaj_rquest(String login, JComboBox vyber) throws IOException, ClassNotFoundException {
        ArrayList<Rezervacie> vsetky_requesty = new ArrayList<>();
        vsetky_requesty=odserializuj_request();



        if(vsetky_requesty==null){
            vsetky_requesty=new ArrayList<>();
            vsetky_requesty.add(new Rezervacie(login, (String) vyber.getSelectedItem()));
            serializuj_request(vsetky_requesty);
            return;
        }
        else {
            vsetky_requesty=odserializuj_request();

            for(Rezervacie rezervacia:vsetky_requesty){
                if(rezervacia.destinacia.equals((String) vyber.getSelectedItem()) && rezervacia.login.equals(login)){
                    return;
                }
            }

            int counter=0;
            for(int i=0;i<vsetky_requesty.size();i++){
                if(vsetky_requesty.get(i).login.equals(login)){
                    counter++;
                }
                if(counter>2){
                    serializuj_request(vsetky_requesty);
                    return;
                }
            }
            vsetky_requesty.add(new Rezervacie(login,(String) vyber.getSelectedItem()));
            serializuj_request(vsetky_requesty);
            return;
        }
    }

    public void uprav_rezervaciu(String login, String destinacia){

    }

    public void pozri_requesty(JComboBox vyber) throws IOException, ClassNotFoundException {
       ArrayList<Rezervacie> vsetky_rezervacie= new ArrayList<>();

       vsetky_rezervacie=odserializuj_request();
       if(vsetky_rezervacie==null){
           return;
       }
       for(Rezervacie rezervacia:vsetky_rezervacie){
           vyber.addItem(rezervacia.login+ "-->" + rezervacia.destinacia);
       }
    }

    public  void suhlas_vymazanie(JComboBox vyber, ArrayList<Rezervacie> vsetky_requesty){
        String[] split=vyber.getSelectedItem().toString().split("-->");
        String login=split[0];
        String destinacia=split[1];
        pouzivatel pouzivatel=new pouzivatel(0,0,null,null,null,null,null);


        for(int i = 0; i < vsetky_requesty.size(); i++){
            if(vsetky_requesty.get(i).login.equals(login) && vsetky_requesty.get(i).destinacia.equals(destinacia)){
                vsetky_requesty.remove(i);

                vyber.removeItem(login+"-->"+destinacia);


                break;
            }
        }
        try{
            pouzivatel hladany_user=pouzivatel.odserializuj(login);
            ArrayList<Rezervacie> vsetky_rezervacie=odserializuj();

            for(int i = 0; i < vsetky_rezervacie.size(); i++){
                if(vsetky_rezervacie.get(i).login.equals(login) && vsetky_rezervacie.get(i).destinacia.equals(destinacia)){
                   vsetky_rezervacie.remove(i);
                   break;
                }
            }


            for(int i = 0; i < hladany_user.getRezervacie().size(); i++){
                if(hladany_user.getRezervacie().get(i).getNazov_rezortu().equals(destinacia)){
                    hladany_user.getRezervacie().remove(i);
                }
            }
            hladany_user.serializuj(hladany_user.getVek(),hladany_user.getRozpocet(),hladany_user.getMeno(),hladany_user.getUlica(),hladany_user.getTyp(),hladany_user.getPassword(),hladany_user.getRezervacie());
            serializuj(vsetky_rezervacie);
            serializuj_request(vsetky_requesty);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public  void nesuhlas_vymazanie(JComboBox vyber, ArrayList<Rezervacie> vsetky_requesty) throws IOException {
        String[] split=vyber.getSelectedItem().toString().split("-->");
        String login=split[0];
        String destinacia=split[1];
        pouzivatel pouzivatel=new pouzivatel(0,0,null,null,null,null,null);


        for(int i = 0; i < vsetky_requesty.size(); i++){
            if(vsetky_requesty.get(i).login.equals(login) && vsetky_requesty.get(i).destinacia.equals(destinacia)){
                vsetky_requesty.remove(i);

                vyber.removeItem(login+"-->"+destinacia);

                serializuj_request(vsetky_requesty);

                break;
            }
        }
    }

}
