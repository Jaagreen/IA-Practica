/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

package practicaIA;

/**
 * Clase que implementa el estado y el comportamiento del viajero (de Abdel).
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public class Viajero
{
    private static final int inicioPorDefecto[] = {0,4};
    private static final int metaPorDefecto[] = {5,5};
    private static final Orientacion orientacionPorDefecto = Orientacion.SUR;
    
    private int posInicio[];
    private int posMeta[];
    private Orientacion orientacion;
    
    
    public Viajero()
    {
        cargarPosicionesPorDefecto();
    }
    
    
    public Viajero(int posInicio[], int posMeta[], Orientacion orientacion)
    {
        this.posInicio = posInicio;
        this.posMeta = posMeta;
        this.orientacion = orientacion;                
    }
    
    
    public final void cargarPosicionesPorDefecto()
    {
        posInicio = inicioPorDefecto;
        posMeta = metaPorDefecto;
        orientacion = orientacionPorDefecto;
    }
    
    
    public int[] getPosInicio()
    {
        return posInicio;
    }
    
    
    public int[] getPosMeta()
    {
        return posMeta;
    }
    
    
    public Orientacion getOrientacion()
    {
        return orientacion;
    }
}
