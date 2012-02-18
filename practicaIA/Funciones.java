/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

package practicaIA;

/**
 * Clase que contiene funciones utilizadas en varias clases del programa.
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public class Funciones
{
    /**
     * Metodo para otener un numero pseudoaleatorio entre dos numeros.
     * @param max Cota superior del rango de numeros aleatorios.
     * @param min Cota inferiror del rango de numeros pseudoaleatorios.
     * @return El numero pseudoaleatorio generado.
     */
    public static int obtenerNumeroAleatorio(int max, int min)
    {
        return (int) (Math.random() * (max - min)) + min;
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

