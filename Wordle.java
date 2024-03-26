//Juego del WORDLE
package WORDLE;

import java.util.Scanner;

public class Wordle {
//______________________________________________________________________________________________________________________MAIN
    public static void main(String[] args) {

        Scanner rojo = new Scanner(System.in);

        String respuesta;

        do {

            cartelJuego(); //Cartel para Jugar o No Jugar

            respuesta = rojo.next();

            if (!respuesta.equals("0")) {
                String palabra = menu();                //Método que devuelve una palabra con la longitud elegida por el usuario

                String palabraUsuario;

                plantillas(palabra);

                do {
                    String[] colores = new String[palabra.length()];

                    for(int i = 0; i < colores.length; i++) { //rELLENA EL Arrays con " " para evitar que se rellene con 'null'
                        colores[i] = " ";
                    }

                    char[] pusuario = compruebaPalabra(palabra, colores); //Método que comprueba los "colores" y posiciones de las letras
                    //Devuelve el Arrays con la palabra del usuario

                    pintaCuadrados(pusuario, colores);

                    palabraUsuario = conviertePalabra(pusuario);

                } while (!palabraUsuario.equals(palabra));

                System.out.println("                                                                                  ¡ENHORABUENA!");
            }

        } while (!respuesta.equals("0"));

        findeJuego(); //Cartel de Fin de Juego

    }
//______________________________________________________________________________________________________________________CARTEL DE JUEGO
    static void cartelJuego(){

        System.out.println( "\n                                                              " + "\033[46m" + "                                                      " + "\033[0m" +
                "\n                                                              " + "\033[46m" + "\033[1;30m" + "           PULSA CUALQUIER TECLA PARA JUGAR           " + "\033[0m" +
                "\n                                                              " + "\033[46m" + "\033[1;30m" + "                 PULSA '0' PARA SALIR                 " + "\033[0m" +
                "\n                                                              " + "\033[46m" + "                                                      " + "\033[0m");
    }
//______________________________________________________________________________________________________________________CARTEL FIN DE JUEGO
    static void findeJuego(){

        System.out.println( "\n                                                              " + "\033[46m" + "                                                      " + "\033[0m" +
                "\n                                                              " + "\033[46m" + "\033[1;30m" + "           GRACIAS POR JUGAR. ¡HASTA PRONTO!          " + "\033[0m" +
                "\n                                                              " + "\033[46m" + "                                                      " + "\033[0m");

    }
//______________________________________________________________________________________________________________________MENÚ
    static String menu(){

        int letras;
        String palabra = "";
        String cadena = "";

        cadena +=   "\n__________________________________________________________________________________________________________________________________________________________________________________________________\n" +
                "\n      "+"\033[45m"+"                                                                                                                                                                    "+"\033[0m"+
                "\n      "+"\033[45m"+"\033[1;30m"+"                                                                           W  O  R  D  L  E                                                                         "+"\033[0m"+
                "\n      "+"\033[45m"+"\033[1;30m"+"                                                                                                                                                               J.C  "+"\033[0m"+
                "\n" +
                "\n" +
                "\n                                                                 "+"\033[45m"+"                                               "+"\033[0m" +
                "\n                                                                 "+"\033[45m"+"\033[1;30m"+"        N Ú M E R O   D E   L E T R A S        "+"\033[0m" +
                "\n                                                                 "+"\033[45m"+"\033[1;30m"+"_______________________________________________"+"\033[0m" +
                "\n                                                                 "+"\033[45m"+"\033[1;30m"+"       |       |       |       |       |       "+"\033[0m" +
                "\n                                                                 "+"\033[45m"+"\033[1;30m"+"   3   |   4   |   5   |   6   |   7   |   8   "+"\033[0m" +
                "\n                                                                 "+"\033[45m"+"\033[1;30m"+"       |       |       |       |       |       "+"\033[0m";

        System.out.println(cadena);

        do {
            letras = soloEntero();
            if (letras < 3 || letras > 8)
                System.out.println("La palabra debe ser de 3 a 8 letras, introduce un número válido");
        } while (letras < 3 || letras > 8);

        switch (letras){                         //ELIGE EL MÉTODO SEGÚN EL Nº DE LETRAS
            case 3:
                palabra = palabra3();
                break;
            case 4:
                palabra = palabra4();
                break;
            case 5:
                palabra = palabra5();
                break;
            case 6:
                palabra = palabra6();
                break;
            case 7:
                palabra = palabra7();
                break;
            case 8:
                palabra = palabra8();
                break;
        }

        return palabra;
    }
//______________________________________________________________________________________________________________________PALABRA ALEATORIA - 3 letras
    static String palabra3(){

        String[] p3letras = {"SOL", "TOP", "DOS", "PIN", "PUS", "AJO", "RON", "LUZ", "MAR", "COL", "GEL", "BOL", "TOC", "SER", "FIN", "SAL", "MAL", "PUA", "PEZ", "RED", "PIS", "MIL", "POP", "DUO", "MES", "REO", "UVA", "UNO", "OSO", "ORO", "OSA", "VID", "VEZ", "ANO", "ZOO", "ZEN", "TIO", "TIC", "TOS", "GAY", "DON", "COZ", "ABA", "DON", "HOZ", "FEO", "FEA", "GOL", "ARO", "BOA", "PAZ"};
        //50 palabras
        String palabra;

        int pos = (int)(Math.random()* p3letras.length);

        palabra = p3letras[pos];

        return palabra;
    }
//______________________________________________________________________________________________________________________PALABRA ALEATORIA - 4 letras
    static String palabra4(){

        String[] p4letras = {"DUDA", "JOYA", "JOTA", "PROA", "POPA", "EDAD", "ALTO", "DURO", "DOCE", "ODIO", "DIVA","ARMA", "BALA", "TELE", "TELA", "EURO", "FAMA", "FAJO", "CRIA", "RAYO", "VIDA", "RAYA", "CUCO", "CUBO", "CACA", "CRUZ", "PATA", "PATO", "KILO", "KIWI", "LUNA", "MESA", "HENO", "FETO", "SOPA", "BUZO", "PASO", "GATO", "GATA", "GRUA", "IDEA", "MITO", "HITO", "FRIO", "PERA", "HOYO", "CALO", "TORO", "CASA", "GODO", "HUMO", "GAFE", "ARCO", "AROS", "ARCA", "APIO", "ACTO", "BOLO", "LIRA", "BESO", "VACA", "LOCA", "NADA", "AMOR", "RATO", "COMA", "BUZO", "CEPO", "CAJA", "PESA", "PLAN", "HILO", "ROMA"};
        //72 palabras
        String palabra;

        int pos = (int)(Math.random()* p4letras.length);

        palabra = p4letras[pos];

        return palabra;
    }
//______________________________________________________________________________________________________________________PALABRA ALEATORIA - 5 letras
    static String palabra5(){

        String[] p5letras = {"ARPON", "HACHA", "GALLO", "REINA", "HUEVO", "CORTE", "JALEA", "CISNE", "COCHE", "NEGRO", "VERDE", "CARTA", "BRAZO","PLOMO", "PLUMA", "BACHE", "ALETA","COMBO", "MIRLO", "KARMA", "PLAZA", "FORMA", "FIRMA", "GRIFO", "MAREA", "TABLA", "ACERO", "TECHO", "PERRO", "CERDO", "PERLA", "FRESA", "PLATA", "PAJAR", "AVION", "MOVIL", "GORRA", "REGLA", "RATON", "PUZLE", "AUTOR", "ARROZ", "MARCA", "ABEJA", "CLAVO", "PLAYA", "MALLA", "SELLO", "SUELO", "FOLIO", "PAPEL", "CARRO", "TARTA", "TRATO", "TRAMO", "TRAMA", "DEDAL", "DRAMA", "TONTO", "MENTA", "ANSIA", "PRIMO", "PRIMA", "BILIS", "CHELO", "PIANO", "TECLA", "IDIOTA", "ESCOBA", "MUJER", "CALMA", "SALSA", "RUEDO", "RUEDA", "MORAL", "NOCHE", "GOLPE"};
        //71 PALABRAS
        String palabra;

        int pos = (int)(Math.random()* p5letras.length);

        palabra = p5letras[pos];

        return palabra;
    }
//______________________________________________________________________________________________________________________PALABRA ALEATORIA - 6 letras
    static String palabra6(){

        String[] p6letras = {"VERDAD", "MADERO", "REFRAN", "ESTOPA", "RANCHO", "RACIMO", "OFERTA", "ESCOTE", "JURADO", "TALLER", "MANCHA", "RASTRO", "MORADO", "MUSICA", "ADICTO", "DEBATE", "ACENTO", "ADORNO", "MADRID", "CUERDA", "PIEDRA", "CUADRO", "CORCHO", "BROCHA", "ABRAZO", "CUARZO", "SUERTE", "FLECHA", "ABRIGO", "CIUDAD", "MUERTE", "ACEITE", "PERCHA", "CECINA", "PLANTA", "COCTEL", "PIEDRA", "PUEBLO", "PILOTO", "COLETA", "MALETA", "SABANA", "MARMOL", "CATANA", "CAMISA", "CORDON", "ALERTA", "MESETA", "PESETA", "TIEMPO", "PUERRO", "CONCHA", "VELERO", "LOCURA", "AVISPA", "VIDRIO", "AGUILA", "PECADO", "CORAZA", "PIERNA", "PEREZA", "SALIVA", "MIRADA", "COLEGA", "NEVERA", "FARAON", "ESPERA", "PELOTA", "FUTBOL", "PINCHO", "DIENTE", "LENGUA", "MOTERO", "MOTERA", "CRESTA", "PISTON", "MADERA", "CORCEL", "TIRANO", "ROTURA", "FISURA", "FUERTE", "FLAUTA", "FUENTE", "CAMADA", "MUERTE", "FLAUTA"};
        //77 PALABRAS
        String palabra;

        int pos = (int)(Math.random()* p6letras.length);

        palabra = p6letras[pos];

        return palabra;
    }
//______________________________________________________________________________________________________________________PALABRA ALEATORIO - 7 letras
    static String palabra7(){

        String[] p7letras = {"CAMARON", "NAVIDAD", "FABRICA", "RAMONES", "OCASION", "EMBARGO", "HABITAT", "OBLICUO", "MELENDI", "GORGOLA", "TABASCO", "EMBALSE", "COCAINA", "EXTASIS", "TACONES", "TALADRO", "EMPUJON", "SARDINA", "RESCATE", "RAQUETA", "ARDILLA", "RADICAL", "MARATON", "SECADOR", "BANDIDO", "PARTIDO", "JUPITER", "PERSONA", "ESTUCHE", "ALCOHOL", "SANIDAD", "REFORMA", "MOMENTO", "ABIERTO", "CANASTA", "FORMULA", "ALIENTO", "ABANICO", "LECHUGA", "CAFEINA", "ABONADO", "ABOGADO", "ABSURDO", "ACUARIO", "ACUERDO", "ANCIANO", "BACHATA", "BALDOSA", "BANDERA", "DIALOGO", "BANDALO", "DIARREA", "ECLIPSE", "EDREDON", "EGIPCIO", "IGLESIA", "ELEGIDO", "PORTERO", "JUGADOR", "MILITAR", "TENEDOR", "POLICIA", "BOMBERO", "ASESINO", "MUSICAL", "LANGOSTA", "CRISTAL", "PESCADO", "PANTERA", "CANTERA", "SOLTERA", "SOLTERO", "TECLADO", "VITRINA", "CANTINA", "MORTERO", "PEINADO", "SECADOR", "PALADAR", "SUJETAR", "INCESTO"};
        //79 PALABRAS
        String palabra;

        int pos = (int)(Math.random()* p7letras.length);

        palabra = p7letras[pos];

        return palabra;
    }
//______________________________________________________________________________________________________________________PALABRA ALEATORIA - 8 letras
    static String palabra8(){

        String[] p8letras = {"LANGOSTA", "CORDENES", "MANIBELA", "RASCADOR", "PROBLEMA", "COMENSAL", "SABATICO", "BIZCOCHO", "ELECCION", "GARBANZO", "HALLAZGO", "GARGANTA", "OFENSIVO", "ELEGANTE", "ELEFANTE", "CACAHUETE", "REACCION", "MECEDORA", "EJERCITO", "ECONOMIA", "TAMBALEO", "MACARRON", "RASTREAR", "REACTIVO", "SABOTAJE", "SACARINA", "ABEJORRO", "AMARILLO", "CRISTAL", "BARRANCO", "DESCANSO", "GOLEADOR", "DISTRITO", "GUITARRA", "ESTANQUE" , "PARAGUAS", "PANTALON", "POLITICO", "CANGREJO", "REALIDAD", "MUNICION", "PERDEDOR", "CUBIERTO", "CINTURON", "CABEZAZO","CABLEADO", "CAZADORA", "CACHIMBA", "CACHONDA", "CAJONERA", "ALABANZA", "BATIDORA", ""};
        //52 PALABRAS
        String palabra;

        int pos = (int)(Math.random()* p8letras.length);

        palabra = p8letras[pos];

        return palabra;
    }
//______________________________________________________________________________________________________________________MENÚ PLANTILLAS
    static void plantillas(String palabra){

        switch (palabra.length()){ //Dependiendo de la longitud de la palabra imprime una PLANTILLA
            case 3:
                plantilla3();
                break;
            case 4:
                plantilla4();
                break;
            case 5:
                plantilla5();
                break;
            case 6:
                plantilla6();
                break;
            case 7:
                plantilla7();
                break;
            case 8:
                plantilla8();
                break;
        }
    }
//______________________________________________________________________________________________________________________PLANTILLA - 3 letras
    static void plantilla3(){

        String plantilla =  "\n                                                                             " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" +
                "\n                                                                             " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" +
                "\n                                                                             " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m ";
        System.out.println(plantilla);

    }
//______________________________________________________________________________________________________________________PLANTILLA - 4 letras
    static void plantilla4(){

        String plantilla =  "\n                                                                         " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" +
                "\n                                                                         " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" +
                "\n                                                                         " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m";
        System.out.println(plantilla);

    }
//______________________________________________________________________________________________________________________PLANTILLA - 5 letras
    static void plantilla5(){

        String plantilla =  "\n                                                                     " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" +
                "\n                                                                     " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" +
                "\n                                                                     " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m";
        System.out.println(plantilla);

    }
//______________________________________________________________________________________________________________________PLANTILLA - 6 letras
    static void plantilla6(){

        String plantilla =  "\n                                                                 " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" +
                "\n                                                                 " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" +
                "\n                                                                 " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m";
        System.out.println(plantilla);


    }
//______________________________________________________________________________________________________________________PLANTILLA - 7 letras
    static void plantilla7(){

        String plantilla =  "\n                                                             " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" +
                "\n                                                             " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" +
                "\n                                                             " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m";
        System.out.println(plantilla);

    }
//______________________________________________________________________________________________________________________PLANTILLA - 8 letras
    static void plantilla8(){

        String plantilla =  "\n                                                         " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" +
                "\n                                                         " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" +
                "\n                                                         " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m" + " " + "\033[0m" + "\033[47m" + "       " + "\033[0m";
        System.out.println(plantilla);


    }
//______________________________________________________________________________________________________________________COLORES DE LA PALABRA
    static char[] compruebaPalabra(String palabra, String[] colores){

        String pusuario = palabraUsuario(palabra); //Devuelve una palabra válida para verificar que el intento sea válido

        char[] copia = palabra.toCharArray(); //Con este metodo se crea un Arrays guardando en cada caja una letra de la palabra ¡!

        char[] copiaUsuario = pusuario.toCharArray();
//------------------------------------------------------------------------------------------------------------------------------------------SACADA---------------------------------------
        for (int i = 0; i < copia.length; i++) {                //PALABRA ORIGINAL -

            if (copia[i] == copiaUsuario[i]) {  //Si la letra está en el lugar correcto, la letra de la copia del Array se transforma en '-'
                //y en el Array de colores se guarda el verde
                copia[i] = '-';
                colores[i] = "verde";
            }                                   //Se cambia la letra que está en su lugar para que no se confunda cuando se busque otra misma letra que no está en su sitio.

            for (int j = 0; j < copiaUsuario.length; j++) {     //PALABRA USUARIO  -

                if (copia[i] == copiaUsuario[j] && !colores[j].equals("verde") && !colores[j].equals("amarillo")) {  //si la letra de la original está en algún sitio de la del usuario y esta no estaba en su lugar (!= "verde")
                    copia[i] = '-';
                    colores[j] = "amarillo";
                } else if (copia[i] != copiaUsuario[j] && !colores[j].equals("amarillo") && !colores[j].equals("verde"))
                    colores[j] = "blanco";

            }

        }
//-------------------------------------------------------------------------------------------------------------------------------------------SACADA-------------------------------------
        return copiaUsuario;
    }
//______________________________________________________________________________________________________________________PALABRA VÁLIDA
    static String palabraUsuario(String palabra){

        Scanner rojo = new Scanner(System.in);

        String pusuraio;

        boolean longitud;
        boolean numeros;

        do {                                                    //Bucle para verificar que la palabra introducida es válida para verificar
            String copia_sin_espacios = "";
            longitud = true;
            numeros = false;

            pusuraio = rojo.nextLine().toUpperCase();

            for (int i = 0; i < pusuraio.length(); i++) {       //Bucle para eliminar los espacios
                if (pusuraio.charAt(i) != ' ')
                    copia_sin_espacios += pusuraio.charAt(i);
            }
            pusuraio = copia_sin_espacios;  //palabra sin espacios

            for (int i = 0; i < pusuraio.length(); i++) {                //Bucle para verificar que no hay algún carácter distinto de una letra
                if (pusuraio.charAt(i) < 'A' || pusuraio.charAt(i) > 'Z') {
                    numeros = true;
                }
            }

            if (numeros)
                System.out.println("Introduce una palabra válida");

            if ((pusuraio.length() < palabra.length() || pusuraio.length() > palabra.length()) && !numeros) {
                longitud = false;
                System.out.println("Asegurate de introducir una palabra de " + palabra.length() + " letras.");
            }

        } while (!longitud || numeros);

        return pusuraio;
    }
//______________________________________________________________________________________________________________________PINTA LOS CUADRADOS
    static void pintaCuadrados(char[] pusuraio, String[] colores){

        String cadena = "";

        for (int i = 0; i < 3; i++) {

            cadena += espacios(colores); //agrega el espacio suficiente paracuadrar los cuadrados en el centro

            for (int j = 0; j < colores.length; j++) {

                if (i == 1){
                    cadena += colores(colores[j]) + "   " + "\033[1;30m" + pusuraio[j] + "   " + colores("fin") + " ";
                } else
                    cadena += colores(colores[j]) + "       " + colores("fin") + " ";

            }
            cadena += "\n";

        }

        System.out.println(cadena);

    }
//______________________________________________________________________________________________________________________COLORES
    static String colores(String color){

        switch (color){

            case "verde":
                color = "\033[42m";
                break;
            case "amarillo":
                color = "\033[43m";
                break;
            case "blanco":
                color = "\033[47m";
                break;
            case "fin":
                color = "\033[0m";
                break;
        }

        return color;
    }
//______________________________________________________________________________________________________________________ESPACIOS (antes de los cuadrados)
    static String espacios(String[] colores){

        String cadena = "";

        switch (colores.length){

            case 3:
                cadena += "                                                                             ";
                break;
            case 4:
                cadena += "                                                                         ";
                break;
            case 5:
                cadena += "                                                                     ";
                break;
            case 6:
                cadena += "                                                                 ";
                break;
            case 7:
                cadena += "                                                             ";
                break;
            case 8:
                cadena += "                                                         ";
        }

        return cadena;
    }
//______________________________________________________________________________________________________________________ARRAY CHAR -> STRING
    static String conviertePalabra(char[] pusuario){

        String palabraUsuario = "";
        //pusuario es el Array de tipo 'char'

        for (int i = 0; i < pusuario.length; i++) {
            palabraUsuario += pusuario[i];
        }

        return palabraUsuario;
    }
//______________________________________________________________________________________________________________________SOLO ENTERO (restricción)
    static int soloEntero(){

        Scanner rojo = new Scanner(System.in);

        System.out.println("\n\n");
        System.out.print("                                                                                        ");

        while(!rojo.hasNextInt()){  //La clase '.hasNextInt' es únicamente de uso hacia variables de tipo 'STRING'
            //Verifica si el texto introducido es numérico

            System.out.println("Introduce un valor válido:");
            System.out.print("                                                                                        ");

            rojo.next();
        }
        return rojo.nextInt();
    }
}

//2 días