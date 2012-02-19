/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

package practicaIA;

/**
 * Clase que implementa el metodo de busqueda informada mediante el algoritmo A*
 * y utilizando como heuristica una variacion de la heuristica de Manhatan.
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public class BusquedaInformadaManhatan extends EstrategiaBusqueda
{
    public BusquedaInformadaManhatan()
    {
        super("Busqueda informada A*", "Manhatan (modificada)");
    }

    @Override
    public void mostrarEstadoActual()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
