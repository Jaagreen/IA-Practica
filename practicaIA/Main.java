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
     * 
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

    public static void main(String[] args) 
    {
        Mapa m = new Mapa();        
        Viajero v = new Viajero(m, 90, false);
        m.mostrar();
        
        
        Mapa m2 = new Mapa(8, 10, 3);
        v = new Viajero(m2, 90, true);
        m2.mostrar();
        
        System.out.println("Orientacion: " + m2.getViajero().getOrientacion());
        v.girarSentidoHorario();
        System.out.println("Orientacion: " + m2.getViajero().getOrientacion());
        v.girarSentidoHorario();
        System.out.println("Orientacion: " + m2.getViajero().getOrientacion());
        v.girarSentidoHorario();
        System.out.println("Orientacion: " + m2.getViajero().getOrientacion());
        v.girarSentidoHorario();
        System.out.println("Orientacion: " + m2.getViajero().getOrientacion());
        v.girarSentidoAntiHorario();
        System.out.println("Orientacion: " + m2.getViajero().getOrientacion());
        
        
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
        
        v = new Viajero(m3, 45, new int[] {2,3}, new int[] {7, 7}, Orientacion.NORTE);
        
        m3.mostrar();
        
        System.out.println("Orientacion: " + m3.getViajero().getOrientacion());
        v.girarSentidoHorario();
        System.out.println("Orientacion: " + m3.getViajero().getOrientacion());
        v.girarSentidoHorario();
        System.out.println("Orientacion: " + m3.getViajero().getOrientacion());
        v.girarSentidoHorario();
        System.out.println("Orientacion: " + m3.getViajero().getOrientacion());
        v.girarSentidoHorario();
        System.out.println("Orientacion: " + m3.getViajero().getOrientacion());
        v.girarSentidoHorario();
        System.out.println("Orientacion: " + m3.getViajero().getOrientacion());
        v.girarSentidoHorario();
        System.out.println("Orientacion: " + m3.getViajero().getOrientacion());
        v.girarSentidoHorario();
        System.out.println("Orientacion: " + m3.getViajero().getOrientacion());
        v.girarSentidoHorario();
        System.out.println("Orientacion: " + m3.getViajero().getOrientacion());
        v.girarSentidoAntiHorario();
        System.out.println("Orientacion: " + m3.getViajero().getOrientacion());
        v.girarSentidoAntiHorario();
        System.out.println("Orientacion: " + m3.getViajero().getOrientacion());
        v.girarSentidoAntiHorario();
        System.out.println("Orientacion: " + m3.getViajero().getOrientacion());
        
    }
}
