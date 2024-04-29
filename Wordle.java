//Juego del WORDLE
package WORDLE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Wordle {

    public static Scanner rojo = new Scanner(System.in);

    public static final String PALABRAS = "C:\\Users\\Javi\\IdeaProjects\\PROYECTOS\\src\\WORDLE\\palabras.txt";
    public static final String RANKING = "C:\\Users\\Javi\\IdeaProjects\\PROYECTOS\\src\\WORDLE\\ranking.txt";
    //CARGA DE FICHEROS
    public static LinkedList<String> fichPalabras = cargaFichero(PALABRAS);
    public static LinkedList<String> fichRanking = cargaFichero(RANKING);

    public static final int INTENTOS = 8;
    public static final int MULTIPLICADOR = 3;
    public static final int JUGADORES_EN_RANKING = 5;
//______________________________________________________________________________________________________________________MAIN
    public static void main(String[] args) throws InterruptedException{

        String respuesta, palabraUsuario, palabra, nombre;
        int intentos, puntos;

        do {
            intentos = INTENTOS; //Intentos para acertar la palabra

            cartelJuego(); //Cartel para Jugar o No Jugar

            respuesta = rojo.next();

            if (!respuesta.equals("0")) {
                //REINICIAR BUFFER PARA NO REGISTRAR DATOS VACÍOS
                Scanner rojo = new Scanner(System.in);

                System.out.print("                                                                          INTRODUCE TU NOMBRE: ");
                nombre = rojo.next();
                //Método que devuelve una palabra con la longitud elegida por el usuario
                palabra = menuPalabras();

                System.out.println("\n                                                                                   " + intentos + " INTENTOS\n");
                //Se dibuja la plantilla inicial según la longitud de la palabra
                plantillas(palabra);

                String[] colores, verdes;

                do {
                    //Array donde se guardan en forma de texto los colores correspondientes
                    colores = new String[palabra.length()];
                    Arrays.fill(colores, " ");
                    //Array donde se guardan en todas las posiciones el verde por si no acierta la Palabra
                    verdes = new String[palabra.length()];
                    Arrays.fill(verdes, "verde");

                    //Método que comprueba los "colores" y posiciones de las letras
                    //Devuelve el Array de tipo char con la palabra del usuario
                    char[] pusuario = compruebaPalabra(palabra, colores);

                    pintaCuadrados(pusuario, colores);
                    //Convierte el array de tipo 'char' a un String
                    palabraUsuario = conviertePalabra(pusuario);

                    if (!palabraUsuario.equals(palabra))
                        intentos--;

                } while (!palabraUsuario.equals(palabra) && intentos != 0);

                if (palabraUsuario.equals(palabra))
                    System.out.println("                                                                                  ¡ENHORABUENA!");
                else {
                    System.out.println( "                                                                                  FIN DEL JUEGO\n\n" +
                                        "                                                                                 LA PALABRA ERA:\n\n");
                    //Se tranforma la palabra a un array de tipo char
                    char[] solucion = palabra.toCharArray();
                    pintaCuadrados(solucion, verdes);
                }
                //Se registran los puntos según los intentos y palabra empleados
                puntos = sumaPuntos(intentos, palabra);
                //Se actualiza el ranking con el NUEVO JUGADOR

                System.out.println("                                                                              PUNTOS TOTALES: " + puntos + "pts");

                actualizaRanking(puntos, nombre, palabra);
            }
            //Se vuelcan los datos de la lista al fichero ranking.txt
            vuelcaFichero();
            //Si la respuesta es distinta de 0:
            if (!respuesta.equals("0"))
                //Muestra el Ranking de Jugadores
                muestraRanking();

        } while (!respuesta.equals("0"));

        findeJuego();
    }
//______________________________________________________________________________________________________________________MOSTRAR RANKING
    public static void muestraRanking() throws InterruptedException{

        System.out.print("__________________________________________________________________________________________________________________________________________________________________________________________________\n\n");
        int linea = 0, posicion = 0;
        //Bucle para mostrar el Ranking
        for (String s : fichRanking) {
            //Si la línea es uno título (JER = 5, cada 6 líneas hay un título):
            if (linea % (JUGADORES_EN_RANKING+1) == 0) {

                Thread.sleep(1800);

                //Fondo blanco para el título
                System.out.print("                                                                                  \033[44m  " + s + "  \033[0m");
                posicion = 0;
            //Si la linea no es un titulo
            } else {
                posicion++;
                //posición 1-5, info[1]nombre, info[0]puntos
                String[] info = s.split("-");
                System.out.print("                                                                                \033[33m" + posicion + "º\033[0m " + info[1] + " - " + info[0] + " pts");
            }
            System.out.print("\n\n");
            linea++;
        }

    }
//______________________________________________________________________________________________________________________REGISTRA PUNTUACIÓN / ACTUALIZA RANKING
    public static void actualizaRanking(Integer puntos, String nombre, String palabra){

        //Lista que guarda solo el apartado de ranking específico
        LinkedList<String> ranking = new LinkedList<>();

        String nuevoJugador = puntos.toString() + "-" + nombre;
        int posicionRanking = 0, i, j, pos;
        boolean letras = false;

        //Bucle que recorre la lista con el fichero cargado del ranking y no se han encontrado las letras
        for (i = 0; i < fichRanking.size() && !letras; i++) {

            //Si la linea del fichero del ranking es igual al formato de titulo de nº de letras de la palabra (x LETRAS):
            if (fichRanking.get(i).equalsIgnoreCase(formatoLETRAS(palabra))) {// x LETRAS

                posicionRanking = i+1;
                letras = true;

                //Bucle que recorre las lineas del fichero de los concursantes específicos
                for (j = posicionRanking; j < posicionRanking + JUGADORES_EN_RANKING; j++) {
                    //Se añade a la lista de ranking las 5 lineas guardadas del fichero del apartado correspondiente
                    ranking.add(fichRanking.get(j));
                }
                //Se añade la puntuación actual y el nombre del jugador
                ranking.add(nuevoJugador);
            }

        }
        //Se reordena el ranking en base a los puntos
        Collections.sort(ranking, new ComparaPuntos());
        //Se elimina la puntuación más baja
        String eliminado = ranking.removeLast();

        //Si el nuevo jugador y el jugador eliminado del ranking NO SON EL MISMO, se actualiza ------------
        if (!nuevoJugador.equals(eliminado))
            //Se actualiza la lista
            insertaEnRanking(posicionRanking, ranking);

    }
//______________________________________________________________________________________________________________________INSERTA EN EL RANKING
    private static void insertaEnRanking(int posicionRanking, LinkedList<String> ranking) {

        int n = 0;
        //Bucle que se ejecuta JUGADORES_EN_RANKING veces (5),
        //Se sustituyen los valores originales del fichero por los valores especificos del ranking
        while (n < JUGADORES_EN_RANKING) {
            fichRanking.set(posicionRanking, ranking.get(n));
            posicionRanking++;
            n++;
        }
        System.out.println("\n\n                                                                        ¡ ¡ ¡ ENTRASTE EN EL RANKING ! ! !\n\n");
    }
//______________________________________________________________________________________________________________________FORMATO
    private static String formatoLETRAS(String palabra){
        return palabra.length() + " LETRAS";
    }
//______________________________________________________________________________________________________________________SUMA PUNTOS
    public static int sumaPuntos(int intentos, String palabra){
        //Variable 'puntos' = case de los intentos
        int puntos = switch (intentos) {
            case 8 -> 1000;
            case 7 -> 800;
            case 6 -> 600;
            case 5 -> 400;
            case 4 -> 300;
            case 3 -> 200;
            case 2 -> 150;
            case 1 -> 100;
            default -> 50;
        };
        return puntos * palabra.length() * MULTIPLICADOR;
    }
//______________________________________________________________________________________________________________________CARGA FICHERO
    public static LinkedList<String> cargaFichero(String NUEVOFICHERO){

        LinkedList<String> fichero = new LinkedList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(NUEVOFICHERO))) {
            //Le cargan todas las lineas del fichero en el arraylistcon formato de string
            String linea = in.readLine();
            while (linea != null) {
                fichero.add(linea);
                linea = in.readLine();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return fichero;
    }
//______________________________________________________________________________________________________________________VUELCA FICHERO
    public static void vuelcaFichero(){

        try (BufferedWriter out = new BufferedWriter(new FileWriter(RANKING))) {
            //Bucle que recorre la lista con las lineas de texto y las escribe en el fichero RANKING
            for (int i = 0; i < fichRanking.size(); i++) {
                String linea = fichRanking.get(i);
                out.write(linea);
                out.newLine();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

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
    static String menuPalabras(){

        int letras;

        String cadena =   "\n__________________________________________________________________________________________________________________________________________________________________________________________________\n" +
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

        //Se guarda en un array las palabras con el nº de letras seleccionado.
        String[] palabras = fichPalabras.get(letras-3).split(" ");

        return palabraAleatoria(palabras);
    }
//______________________________________________________________________________________________________________________PALABRA ALEATORIA
    public static String palabraAleatoria(String[] palabras){
        return palabras[((int) (Math.random() * palabras.length)) - 1];
    }
//______________________________________________________________________________________________________________________MENÚ PLANTILLAS
    static void plantillas(String palabra){

        String plantilla = "";
        //3 veces por la altura del cuadrado
        for (int i = 0; i < 3; i++) {
            plantilla += "\n" + espacios(palabra.length());
            //Se repite tantas veces como longitud de la palabra
            for (int j = 0; j < palabra.length(); j++) {
                plantilla += "\033[47m" + "       " + "\033[0m ";
            }
        }
        System.out.println(plantilla);
    }
//______________________________________________________________________________________________________________________COLORES DE LA PALABRA
    static char[] compruebaPalabra(String palabra, String[] colores){

        //Devuelve una palabra válida para verificar que el intento sea válido
        String pusuario = palabraUsuario(palabra);
        //Transforma a un array de tipo char la palabra con formato String
        char[] copia = palabra.toCharArray();
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

        String pusuario;

        boolean longitud, numeros;
        //Bucle para verificar que la palabra introducida es válida para verificar
        do {
            String copia_sin_espacios = "";
            longitud = true;
            numeros = false;

            pusuario = rojo.nextLine().toUpperCase();
            //Bucle para eliminar los espacios de la palabra en el caso de que existan
            for (int i = 0; i < pusuario.length(); i++) {
                if (pusuario.charAt(i) != ' ')
                    copia_sin_espacios += pusuario.charAt(i);
            }
            pusuario = copia_sin_espacios;  //palabra sin espacios
            //Bucle para verificar que no hay algún carácter distinto de una letra
            for (int i = 0; i < pusuario.length(); i++) {
                if (pusuario.charAt(i) < 'A' || pusuario.charAt(i) > 'Z') {
                    numeros = true;
                }
            }
            //Si la palabra contiene algún carácter que no sea una letra:
            if (numeros)
                System.out.println("Introduce una palabra válida");
            //Si la longitud de la palabra es menor o mayor a la indicada
            if ((pusuario.length() < palabra.length() || pusuario.length() > palabra.length()) && !numeros) {
                longitud = false;
                System.out.println("Asegurate de introducir una palabra de " + palabra.length() + " LETRAS");
            }

        } while (!longitud || numeros);

        return pusuario;
    }
//______________________________________________________________________________________________________________________PINTA LOS CUADRADOS
    static void pintaCuadrados(char[] pusuraio, String[] colores){

        String cadena = "";
        //Se repite 3 veces por la altura del cuadrado, 1 por cada línea
        for (int i = 0; i < 3; i++) {
            //Según la longitud del array de colores se espacia más o menos para pintar los cuadros en el centro
            cadena += espacios(colores.length); //agrega el espacio suficiente paracuadrar los cuadrados en el centro
            //
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
    static String espacios(int longitud){

        String cadena = "";
        switch (longitud){
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
        //Convierte el array de tipo char a un String
        for (int i = 0; i < pusuario.length; i++) {
            palabraUsuario += pusuario[i];
        }
        return palabraUsuario;
    }
//______________________________________________________________________________________________________________________SOLO ENTERO (restricción)
    static int soloEntero(){

        Scanner rojo = new Scanner(System.in);

        System.out.print("\n\n\n                                                                                        ");
        while(!rojo.hasNextInt()){  //La clase '.hasNextInt' es únicamente de uso hacia variables de tipo 'STRING'
            //Verifica si el texto introducido es numérico
            System.out.println("Introduce un valor válido:\n                                                                                        ");
            rojo.next();
        }
        return rojo.nextInt();
    }
}
//2 días