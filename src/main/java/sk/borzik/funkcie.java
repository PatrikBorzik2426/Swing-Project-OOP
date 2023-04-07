package sk.borzik;

import java.io.*;
import java.util.ArrayList;
import java.util.*;

import sk.borzik.Destinacia.*;
import sk.borzik.Observer.Pozorovatel;
import sk.borzik.Pouzivatelia.Admin;
import sk.borzik.Pouzivatelia.pouzivatel;
import sk.borzik.Vozidla.vozidla;

public class funkcie {

    public static void main(String[] args) throws IOException {


        BufferedReader reader = null;
        Random nahodka=new Random();

        ArrayList<destinacia> vsetky_destinacie = new ArrayList<destinacia>();



        try {
            reader = new BufferedReader(new FileReader("zazitkove_vylety.txt"));

            String line = reader.readLine();

            String[][] landmarks1 = {
                    {"Matira Beach", "Mount Otemanu", "Bora Bora Lagoonarium", "Shark and Ray Snorkel Safari", "Coral Gardens"},
                    {"Akrotiri Archaeological Site", "Red Beach", "Oia", "Santorini Caldera", "Ancient Thira"},
                    {"Vaadhoo Island", "Male Fish Market", "Maldive Victory", "Utheemu Ganduvaru", "Hukuru Miskiy"},
                    {"Road to Hana", "Haleakalā National Park", "Iao Valley State Park", "Maui Ocean Center", "Lahaina Banyan Court Park"},
                    {"Tegalalang Rice Terrace", "Ubud Monkey Forest", "Campuhan Ridge Walk", "Tegenungan Waterfall", "Ubud Palace"},
                    {"Great Migration", "Serengeti National Park Visitor Centre", "Serengeti Balloon Safari", "Serengeti Hippo Pool", "Oldupai Gorge"},
                    {"Milford Sound", "Skyline Queenstown", "Queenstown Gardens", "Walter Peak High Country Farm", "AJ Hackett Bungy"},
                    {"Lake Louise Ski Resort", "Moraine Lake", "Lake Louise Gondola", "Banff National Park", "Plain of Six Glaciers"},
                    {"Cathedral Rock Trail", "Chapel of the Holy Cross", "Slide Rock State Park", "Devil's Bridge Trail", "Red Rock State Park"},
                    {"Amalfi Cathedral", "Path of the Gods", "Villa Rufolo", "Emerald Grotto", "Ravello Festival"}
            };
            int x=0;
            while (line != null) {
                int i=0;
                double cena_za_osobu=nahodka.nextDouble(300);
                Zazitkovy_vylet vylet = new Zazitkovy_vylet(cena_za_osobu, 0, line, null);
                while (x<10){
                    while(i<5){
                        if(vylet.getPamiatky()==null)
                        {
                            vylet.setPamiatky(new ArrayList<Pamiatky>());
                            vylet.getPamiatky().add(new Pamiatky(landmarks1[x][i]));
                            i++;
                        }else{
                            vylet.getPamiatky().add(new Pamiatky(landmarks1[x][i]));
                            i++;
                        }


                    }
                    x++;
                    break;
                }
                vsetky_destinacie.add(vylet);

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            reader = new BufferedReader(new FileReader("pamiatkove_cesty.txt"));

            String line = reader.readLine();


            String[][] landmarks2 = {
                    {"Machu Picchu", "Huayna Picchu", "The Sun Gate", "Temple of the Sun", "Inti Watana"},
                    {"Charles Darwin Research Station", "Tortuga Bay", "Los Tuneles", "Kicker Rock", "Bartolome Island"},
                    {"Manaus", "Anavilhanas Archipelago", "Meeting of the Waters", "Jaú National Park", "Encontro das Águas Park"},
                    {"Devil's Throat", "Garganta del Diablo Trail", "Upper Circuit Trail", "Lower Circuit Trail", "Macuco Safari"},
                    {"Christ the Redeemer", "Copacabana Beach", "Sugarloaf Mountain", "Lapa Arches", "Botanical Garden of Rio de Janeiro"},
                    {"Christ the Redeemer", "Rio de Janeiro Botanical Garden", "Escadaria Selarón", "Lapa Arches", "Sugarloaf Mountain"},
                    {"Rapa Nui National Park", "Ahu Tongariki", "Rano Kau Volcano", "Orongo Ceremonial Village", "Anakena Beach"},
                    {"Banõs Hot Springs", "Valley of the Moon", "El Tatio Geyser Field", "Rainbow Valley", "Flamingo Lagoon"},
                    {"Salar de Uyuni Salt Flats", "Train Cemetery", "Incahuasi Island", "Laguna Colorada", "Colchani Village"},
                    {"Floating Islands of the Uros", "Isla del Sol", "Isla de la Luna", "Taquile Island", "Chucuito Peninsula"}
            };

            int x=0;
            while (line != null) {
                int i=0;
                double cena_za_osobu=nahodka.nextDouble(300);
                Pamiatkove_cesty vylet = new Pamiatkove_cesty(cena_za_osobu, 0, line, null);
                while (x<10){
                    while(i<5){
                        if(vylet.getPamiatky()==null)
                        {
                            vylet.setPamiatky(new ArrayList<Pamiatky>());
                            vylet.getPamiatky().add(new Pamiatky(landmarks2[x][i]));
                            i++;
                        }else{
                            vylet.getPamiatky().add(new Pamiatky(landmarks2[x][i]));
                            i++;
                        }


                    }
                    x++;
                    break;
                }

                vsetky_destinacie.add(vylet);

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            reader = new BufferedReader(new FileReader("dovolenka_rezorty.txt"));

            String line = reader.readLine();

            String[][] landmarks3 = {
                    {"Death Road, Bolivia", "Yungas Road", "Salar de Uyuni", "La Paz", "Lake Titicaca"},
                    {"Crocodile Island, Brazil", "Christ the Redeemer", "Iguazu Falls", "Copacabana Beach", "Amazon Rainforest"},
                    {"Mount Hua, China", "Terracotta Army", "The Great Wall of China", "Forbidden City", "Potala Palace"},
                    {"Danakil Desert, Ethiopia", "Erta Ale Volcano", "Dallol", "Lalibela", "Omo Valley"},
                    {"Devil's Pool, Victoria Falls, Zambia", "Victoria Falls", "Mosi-oa-Tunya National Park", "Livingstone Island", "Chobe National Park"},
                    {"El Caminito del Rey, Spain", "Alhambra", "Park Güell", "Sagrada Familia", "La Tomatina"},
                    {"Chernobyl, Ukraine", "Kiev Pechersk Lavra", "St. Sophia's Cathedral", "Kiev Fortress", "Independence Square"},
                    {"Isle of the Dolls, Mexico", "Xochimilco", "Palacio de Bellas Artes", "Teotihuacan", "Frida Kahlo Museum"},
                    {"Snake Island, Brazil", "São Paulo Museum of Art", "São Paulo Cathedral", "Ibirapuera Park", "São Paulo Zoo"},
                    {"Kokoda Track, Papua New Guinea", "National Parliament House", "Port Moresby Nature Park", "Ela Beach", "Papua New Guinea National Museum and Art Gallery"}
            };

            int x=0;
            while (line != null) {
                int i=0;
                double cena_za_osobu=nahodka.nextDouble(300);
                Dovolenka_rezorty vylet = new Dovolenka_rezorty(cena_za_osobu, 0, line, null);
                while (x<10){
                    while(i<5){
                        if(vylet.getPamiatky()==null)
                        {
                            vylet.setPamiatky(new ArrayList<Pamiatky>());
                            vylet.getPamiatky().add(new Pamiatky(landmarks3[x][i]));
                            i++;
                        }else{
                            vylet.getPamiatky().add(new Pamiatky(landmarks3[x][i]));
                            i++;
                        }


                    }
                    x++;
                    break;
                }

                vsetky_destinacie.add(vylet);

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream("vsetky_vylety.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vsetky_destinacie);
            oos.close();
            fos.close();
            System.out.println("ArrayList serialized to list.ser");

        } catch (IOException e) {
            e.printStackTrace();
        }



        Admin admin=new Admin(20,10000,"admin","admin","admin","1");

        try{
            FileOutputStream fileOut = new FileOutputStream("admin.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(admin);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in "+".ser");
        } catch (IOException i){
            i.printStackTrace();
        }



        ArrayList<vozidla>vsetky_vozidla=new ArrayList<vozidla>();

        vsetky_vozidla.add(new vozidla("auto",120,50,100,1));
        vsetky_vozidla.add(new vozidla("autobus",100,40,58,1));
        vsetky_vozidla.add(new vozidla("lietadlo",350,120,200,1));

        try{
            FileOutputStream fos = new FileOutputStream("vsetky_vozidla.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vsetky_vozidla);
            oos.close();
            fos.close();
            System.out.println("ArrayList serialized to list.ser");



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

