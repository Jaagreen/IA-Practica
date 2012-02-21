/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

package practicaIA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase que contiene funciones utilizadas en varias clases del programa.
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public class Funciones
{
    /**
     * Metodo para borrar todo el contenido de la pantalla y situar el cursor en
     * la parte superior derecha de la pantalla.
     */
    public static void limpiarPantalla()
    {
        System.out.println("\033[1;1H\033[2J");
    }
    
    /**
     * Metodo empleado para poder leer una cadena de caracteres desde el teclado.
     * @return La cadena de caracteres leida
     * @throws IOException Se lanzara una execpcion de entrada-salida en caso de que no se pueda leer del teclado.
     */
    public static String readLn() throws IOException
    {
        InputStreamReader flujo = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(flujo);

        return teclado.readLine();
    }

    
    /**
     * Metodo que muestra por pantalla el mensaje "Pulse ENTER para continuar . . ." 
     * y espera con el programa detenido hasta que el usuario pulse enter.     
     */
    public static void pausa()
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
    public static void esperar(int miliSegundos) 
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
     * Metodo para otener un numero pseudoaleatorio entre dos numeros.
     * @param max Cota superior del rango de numeros aleatorios.
     * @param min Cota inferiror del rango de numeros pseudoaleatorios.
     * @return El numero pseudoaleatorio generado.
     */
    public static int obtenerNumeroAleatorio(int min, int max)
    {
        return (int) (Math.random() * ((max+1) - min)) + min;
    }    
    
    
    /**
     * Metodo para comprobar si una cadena esta vacia o si todos los caracteres que 
     * contiene son espacios en blanco o tabuladores.
     * @param cadena Cadena que se quiere analizar.
     * @return True si la cadena esta en blanco, false en caso contrario.
     */
    public static boolean esCadenaEnBlanco(String cadena)
    {
        //Recorremos todos los caracteres de la cadena.
        for(char c: cadena.toCharArray())
        {
            //Si alguno es distinto del espacion en blanco o del tabulador entonces devolvemos false.
            if(c != ' ' && c != '\t')
                return false;
        }
        
        //Si todos los caracteres son espacios en blanco o tabuladores entoces devolvemos true.
        return true;
    }
}

