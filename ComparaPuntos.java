package WORDLE;

import java.util.Comparator;

public class ComparaPuntos implements Comparator<String> {

    @Override
    public int compare(String s1, String s2){
        //Separamos la parte numerica de la cadena String
        String[] conj1 = s1.split("-");
        String[] conj2 = s2.split("-");
        //Lo convertimos a valor numérico
        Integer i1 = Integer.parseInt(conj1[0]);
        Integer i2 = Integer.parseInt(conj2[0]);
        //Se comparan los números
        return i2-i1;
    }

}
