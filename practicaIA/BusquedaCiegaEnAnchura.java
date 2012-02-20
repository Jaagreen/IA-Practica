/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

package practicaIA;

/**
 * Clase que implementa el metodo de busqueda ciega en anchura.
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public class BusquedaCiegaEnAnchura extends EstrategiaBusqueda
{
    public BusquedaCiegaEnAnchura(Viajero viajero)
    {
        super("Busqueda ciega en anchura", "", viajero);
    }

    
    @Override
    public void mostrarEstadoActual()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
