/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

package practicaIA;

/**
 * Clase que implementa el metodo de busqueda informada mediante el algoritmo A*
 * y utilizando como heuristica el maximo desplazamiento en filas o columnas.
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public class BusquedaInformadaMaximoDesplazamiento extends EstrategiaBusqueda
{
    public BusquedaInformadaMaximoDesplazamiento(Mapa mapa)
    {
        super("Busqueda informada A*", "Maximo desplazamiento en filas o columnas", mapa);
    }

    @Override
    public void mostrarEstadoActual()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void buscar()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void buscarIteraionAiteracion()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void resetear(Mapa mapa)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
