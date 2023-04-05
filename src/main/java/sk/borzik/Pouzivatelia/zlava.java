package sk.borzik.Pouzivatelia;
/*
Využívame Design Pattern Strategy - využitie pri situáciach, kde môže nastat viacero možností, ktoré sa vykonávajú podľa toho, čo je zadané. (vyhýbame sa opakovania kódu)
*/

import java.io.Serializable;

public interface zlava extends Serializable {
    String zlava();     //metóda, ktorá sa bude implementovať v každej triede, ktorá bude implementovať tento interface
}

class ISIC_zlava implements zlava{          //prava moznost zlavy
    public String zlava(){
        return "ISIC";
    }
}

class ziadna_zlava implements zlava{        //druha moznost zlavy
    public String zlava(){
        return "Ziadna";
    }
}

class admin_zlava implements zlava{         //tretia moznost zlavy
    public String zlava(){
        return "Admin";
    }
}
