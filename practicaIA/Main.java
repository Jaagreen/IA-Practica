/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

/*
 * Modo automatico:
 *     Compilacion: $ make
 *     Ejecucion:   $ make run
 * 
 * Modo manual:
 *     Compilacion: $ cd ruta_del_codigo_de_la_practica
 *                  $ javac practicaIA/Main.java
 * 
 *     Ejecucion:   $ java practicaIA/Main
 * 
 * Ejecucion de la compilacion empaquetada en Practica_IA.jar:
 *     $ java -jar Practica_IA.jar
 */

package practicaIA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Clase principal. Esta es la clase que hay que ejecutar para lanzar el programa.
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public class Main 
{
    /**
     * Metodo para borrar todo el contenido de la pantalla y situar el cursor en
     * la parte superior derecha de la pantalla.
     */
    private static void limpiarPantalla()
    {
        System.out.println("\033[1;1H\033[2J");
    }
    
    /**
     * Metodo empleado para poder leer una cadena de caracteres desde el teclado.
     * @return La cadena de caracteres leida
     * @throws IOException Se lanzara una execpcion de entrada-salida en caso de que no se pueda leer del teclado.
     */
    private static String readLn() throws IOException
    {
        InputStreamReader flujo = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(flujo);

        return teclado.readLine();
    }

    
    /**
     * Metodo que muestra por pantalla el mensaje "Pulse ENTER para continuar . . ." 
     * y espera con el programa detenido hasta que el usuario pulse enter.     
     */
    private static void pausa()
    {
        try 
        {
            System.out.print("\nPulse ENTER para continuar . . .");
            readLn();
            System.out.println("\033[1;1H\033[2J");	//Limpia la pantalla.	
        }
        catch (IOException ioe) //Capturamos y gestionamos las excepciones de entrar-salida.
        {
            System.err.println("\n  Error al leer del teclado: " + ioe.getMessage());
        }
    }

    
    /**
     * Metodo que mantiene el programa parado el tiempo que se le indican en
     * el atributo de entrada.
     * @param miliSegundos Tiempo en milisegundos que el programa permanecera parado.
     */
    private static void esperar(int miliSegundos) 
    {
        try 
        {
            Thread.sleep(miliSegundos);
        } 
        catch (InterruptedException e) //Capturamos y gestionamos las excepciones de interrupcion.
        {
            System.err.println("\n  ERROR: Interrupcion cancelada.");
            pausa();
        }
    }

    
    /**
     * Metodo que muestra un mensaje de bienvenida y de explicacion de la practica.
     */
    private static void introduccion()
    {
        System.out.println("\033[1;1H\033[2J\n");  //Limpia la pantalla
        System.out.println("\033[1;37;46m\033[2K  ==============================================================\033[0m");  
        System.out.println("\033[1;37;46m\033[2K  |     PRACTICA 1 IA: Resolucion de problemas de busqueda     |\033[0m");  
        System.out.println("\033[1;37;46m\033[2K  ==============================================================\n\033[0m");  
        
        System.out.println("Se desea obtener la mejor ruta para realizar un viaje a un cierto lugar a traves de "
                         + "un terreno con diversas dificultades. Para ello se dispone de un mapa del terreno "
                         + "donde se representa el origen, el destino y la dificultad asociada para atravesar los "
                         + "distintos puntos posibles, pudiendo asociarse ademas un coste a los cambios de direccion "
                         + "en el camino. El objetivo consiste en utilizar la inteligencia artificial para tratar "
                         + "de obtener una solucion a este planteamiento, formulandolo como un problema de busqueda. "
                         + "Para ello se implementan distintos algoritmos de busqueda (ciega e informada).");

        pausa();
    }
    

    private static void menu()
    {
        introduccion();
        
        System.out.println("Seleccione una opcion:\n"
                         + "   1 - Car");
                
    }
    
    public static void main(String[] args) 
    {
        Viajero v;
        Mapa m = new Mapa();
        m.mostrar();
        
        v = new Viajero(m, 90, false);
        m.mostrar();
        
        
        Mapa m2 = new Mapa(8, 10, 3);
        v = new Viajero(m2, 90, true);
        m2.mostrar();
         
        
        Mapa m3 = null;
        
        try
        {
            m3 = new Mapa("Tablero.txt");
        }
        catch(Exception e)
        {
            System.err.println("Error leyendo el tablero del fichero: " + e.getMessage());
            System.exit(-1);
        }   
        
        v = new Viajero(m3, 45, new int[] {3,3}, new int[] {7, 7}, Orientacion.NORTE);
        
        m3.mostrar();

         menu();
    }
}
