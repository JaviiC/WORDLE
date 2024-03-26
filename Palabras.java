package WORDLE;

import java.util.Scanner;

public class Palabras {

    public static void main(String[] args) {

        Scanner rojo = new Scanner(System.in);

        int cuenta = 1;

        System.out.println("Introduce una palabra para comprobar si se encuentra registrada:");

        while (cuenta > 0) {
            String palabra = rojo.next().toUpperCase();

            switch (palabra.length()) {                         //ELIGE EL MÉTODO SEGÚN EL Nº DE LETRAS
                case 3:
                    palabra3(palabra);
                    break;
                case 4:
                    palabra4(palabra);
                    break;
                case 5:
                    palabra5(palabra);
                    break;
                case 6:
                    palabra6(palabra);
                    break;
                case 7:
                    palabra7(palabra);
                    break;
                case 8:
                    palabra8(palabra);
                    break;
            }
        }

    }
//______________________________________________________________________________________________________________________PALABRAS - 3 letras
        static void palabra3(String palabra){

            String[] p3letras = {"SOL", "RIO", "IRA", "ALA", "LIO", "FAN", "ASA", "TOP", "DOS", "PIN", "PUS", "AJO", "RON", "LUZ", "MAR", "COL", "GEL", "BOL", "TOC", "SER", "FIN", "SAL", "MAL", "PUA", "PEZ", "RED", "PIS", "MIL", "POP", "DUO", "MES", "REO", "UVA", "UNO", "OSO", "ORO", "OSA", "VID", "VEZ", "ANO", "ZOO", "ZEN", "TIO", "TIC", "TOS", "GAY", "DON", "COZ", "ABA", "DON", "HOZ", "FEO", "FEA", "GOL", "ARO", "BOA", "PAZ"};

            boolean esta = false;

            for (int i = 0; i < p3letras.length; i++) {
                if (p3letras[i].equals(palabra))
                    esta = true;
            }

            if (!esta) {
                System.out.println(palabra.length() + " letras");
                System.out.println("Esta palabra no está");
            } else
                System.out.println("Esta palabra si está");
        }
//______________________________________________________________________________________________________________________PALABRAS - 4 letras
        static void palabra4(String palabra){

            String[] p4letras = {"DUDA", "TONO", "COPA", "ASNO", "AGUA", "LAGO", "DUNA", "URNA", "GAMA", "SANO", "MANO", "FORO", "CIMA", "SUMA", "SAPO", "FOCA", "CARO", "CANA", "JOYA", "JOTA", "PROA", "POPA", "EDAD", "ALTO", "DURO", "DOCE", "ODIO", "SURF", "DIVA", "ARMA", "BALA", "TELE", "TELA", "EURO", "FAMA", "FAJO", "CRIA", "RAYO", "VIDA", "RAYA", "CUCO", "CUBO", "CACA", "CRUZ", "PATA", "PATO", "KILO", "KIWI", "LUNA", "MESA", "HENO", "FETO", "SOPA", "BUZO", "PASO", "GATO", "GATA", "GRUA", "IDEA", "MITO", "HITO", "FRIO", "PERA", "HOYO", "CALO", "TORO", "CASA", "GODO", "HUMO", "GAFE", "ARCO", "AROS", "ARCA", "APIO", "ACTO", "BOLO", "LIRA", "BESO", "VACA", "RATA", "LOCA", "NADA", "AMOR", "RATO", "COMA", "BUZO", "CEPO", "CAJA", "PESA", "PLAN", "HILO", "ROMA"};

            boolean esta = false;

            for (int i = 0; i < p4letras.length; i++) {
                if (p4letras[i].equals(palabra))
                    esta = true;
            }

            if (!esta) {
                System.out.println(palabra.length() + " letras");
                System.out.println("Esta palabra no está");
            } else
                System.out.println("Esta palabra si está");
        }
//______________________________________________________________________________________________________________________PALABRAS - 5 letras
        static void palabra5(String palabra){

            String[] p5letras = {"ARPON", "FRENO", "TANGO", "TRONO", "CALLE", "LENTO", "SALUD", "FAROL", "TAPIZ", "BUFON", "GAMBA", "BOXEO", "FALDA", "CHINO", "CIELO", "ESQUI", "ARBOL", "HACHA", "GALLO", "REINA", "HUEVO", "CORTE", "JALEA", "CISNE", "COCHE", "NEGRO", "VERDE", "CARTA", "BRAZO","PLOMO", "PLUMA", "BACHE", "ALETA","COMBO", "MIRLO", "KARMA", "PLAZA", "FORMA", "FIRMA", "GRIFO", "MAREA", "TABLA", "ACERO", "TECHO", "PERRO", "CERDO", "PERLA", "FRESA", "PLATA", "PAJAR", "AVION", "MOVIL", "GORRA", "REGLA", "RATON", "PUZLE", "AUTOR", "ARROZ", "MARCA", "ABEJA", "CLAVO", "PLAYA", "MALLA", "SELLO", "SUELO", "FOLIO", "PAPEL", "CARRO", "TARTA", "TRATO", "TRAMO", "TRAMA", "DEDAL", "DRAMA", "TONTO", "MENTA", "ANSIA", "PRIMO", "PRIMA", "BILIS", "CHELO", "PIANO", "TECLA", "IDIOTA", "ESCOBA", "MUJER", "CALMA", "SALSA", "RUEDO", "RUEDA", "MORAL", "NOCHE", "GOLPE"};

            boolean esta = false;

            for (int i = 0; i < p5letras.length; i++) {
                if (p5letras[i].equals(palabra))
                    esta = true;
            }

            if (!esta) {
                System.out.println(palabra.length() + " letras");
                System.out.println("Esta palabra no está");
            } else
                System.out.println("Esta palabra si está");
        }
//______________________________________________________________________________________________________________________PALABRAS - 6 letras
        static void palabra6(String palabra){

            String[] p6letras = {"VERDAD", "ZAFIRO", "ZAPATO", "CORONA", "COLLAR", "JARDIN", "PREGUNTA", "VERANO", "SARTEN", "MOFETA", "ESTELA", "FAMOSO", "DUENDE", "VIOLIN", "TAMBOR", "TIENDA", "CARTEL", "MADERO", "REFRAN", "ESTOPA", "RANCHO", "RACIMO", "OFERTA", "ESCOTE", "JURADO", "TALLER", "MANCHA", "RASTRO", "MORADO", "MUSICA", "ADICTO", "DEBATE", "ACENTO", "ADORNO", "MADRID", "CUERDA", "PIEDRA", "CUADRO", "CORCHO", "BROCHA", "ABRAZO", "CUARZO", "SUERTE", "FLECHA", "ABRIGO", "CIUDAD", "MUERTE", "ACEITE", "PERCHA", "CECINA", "PLANTA", "COCTEL", "PIEDRA", "PUEBLO", "PILOTO", "COLETA", "MALETA", "SABANA", "MARMOL", "CATANA", "CAMISA", "CORDON", "ALERTA", "MESETA", "PESETA", "TIEMPO", "PUERRO", "CONCHA", "VELERO", "LOCURA", "AVISPA", "VIDRIO", "AGUILA", "PECADO", "CORAZA", "PIERNA", "PEREZA", "SALIVA", "MIRADA", "COLEGA", "NEVERA", "FARAON", "ESPERA", "PELOTA", "FUTBOL", "PINCHO", "DIENTE", "LENGUA", "MOTERO", "MOTERA", "CRESTA", "PISTON", "MADERA", "CORCEL", "TIRANO", "ROTURA", "FISURA", "FUERTE", "FLAUTA", "FUENTE", "CAMADA", "MUERTE", "FLAUTA"};

            boolean esta = false;

            for (int i = 0; i < p6letras.length; i++) {
                if (p6letras[i].equals(palabra))
                    esta = true;
            }

            if (!esta) {
                System.out.println(palabra.length() + " letras");
                System.out.println("Esta palabra no está");
            } else
                System.out.println("Esta palabra si está");
        }
//______________________________________________________________________________________________________________________PALABRAS - 7 letras
        static void palabra7(String palabra){

            String[] p7letras = {"CAMARON", "VOLANTE", "PULSERA", "SOBRINO", "BURBUJA", "GARGOLA", "AUTOBUS", "CARTERA", "NAVIDAD", "FABRICA", "RAMONES", "OCASION", "EMBARGO", "HABITAT", "OBLICUO", "MELENDI", "GORGOLA", "TABASCO", "EMBALSE", "COCAINA", "EXTASIS", "TACONES", "TALADRO", "EMPUJON", "SARDINA", "RESCATE", "RAQUETA", "ARDILLA", "RADICAL", "MARATON", "SECADOR", "BANDIDO", "PARTIDO", "JUPITER", "PERSONA", "ESTUCHE", "ALCOHOL", "SANIDAD", "REFORMA", "MOMENTO", "ABIERTO", "CANASTA", "FORMULA", "ALIENTO", "ABANICO", "LECHUGA", "CAFEINA", "ABONADO", "ABOGADO", "ABSURDO", "ACUARIO", "ACUERDO", "ANCIANO", "BACHATA", "BALDOSA", "BANDERA", "DIALOGO", "BANDALO", "DIARREA", "ECLIPSE", "EDREDON", "EGIPCIO", "IGLESIA", "ELEGIDO", "PORTERO", "JUGADOR", "MILITAR", "TENEDOR", "POLICIA", "BOMBERO", "ASESINO", "MUSICAL", "LANGOSTA", "CRISTAL", "PESCADO", "PANTERA", "CANTERA", "SOLTERA", "SOLTERO", "TECLADO", "VITRINA", "CANTINA", "MORTERO", "PEINADO", "SECADOR", "PALADAR", "SUJETAR", "INCESTO"};

            boolean esta = false;

            for (int i = 0; i < p7letras.length; i++) {
                if (p7letras[i].equals(palabra))
                    esta = true;
            }

            if (!esta) {
                System.out.println(palabra.length() + " letras");
                System.out.println("Esta palabra no está");
            } else
                System.out.println("Esta palabra si está");
        }
//______________________________________________________________________________________________________________________PALABRAS - 8 letras
        static void palabra8(String palabra){

            String[] p8letras = {"LANGOSTA", "CRIMINAL", "DIAMANTE", "FLAMENCO", "INVIERNO", "ESTRELLA", "ENSALADA", "ORQUESTA", "BORRACHO", "COLETERO", "FARSANTE", "FRACTURA", "CORDENES", "MANIBELA", "RASCADOR", "PROBLEMA", "COMENSAL", "SABATICO", "BIZCOCHO", "ELECCION", "GARBANZO", "HALLAZGO", "GARGANTA", "OFENSIVO", "ELEGANTE", "ELEFANTE", "CACAHUETE", "REACCION", "MECEDORA", "EJERCITO", "ECONOMIA", "TAMBALEO", "MACARRON", "RASTREAR", "REACTIVO", "SABOTAJE", "SACARINA", "ABEJORRO", "AMARILLO", "CRISTAL", "BARRANCO", "DESCANSO", "GOLEADOR", "DISTRITO", "GUITARRA", "ESTANQUE" , "PARAGUAS", "PANTALON", "POLITICO", "CANGREJO", "REALIDAD", "MUNICION", "PERDEDOR", "CUBIERTO", "CINTURON", "CABEZAZO","CABLEADO", "CAZADORA", "CACHIMBA", "CACHONDA", "CAJONERA", "ALABANZA", "BATIDORA", ""};

            boolean esta = false;

            for (int i = 0; i < p8letras.length; i++) {
                if (p8letras[i].equals(palabra))
                    esta = true;
            }

            if (!esta) {
                System.out.println(palabra.length() + " letras");
                System.out.println("Esta palabra no está");
            } else
                System.out.println("Esta palabra si está");
        }

}
