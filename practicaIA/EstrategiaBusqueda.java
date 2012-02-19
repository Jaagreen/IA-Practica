/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

package practicaIA;

/**
 * Clase abstracta de la que heredaran los distintos tipos de estrategias de busqueda.
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public abstract class EstrategiaBusqueda
{
    private String tipo;
    private String nombreHeuristica;
    
    
    public EstrategiaBusqueda(String tipo, String nombreHeuristica)
    {
        this.tipo = tipo;
        this.nombreHeuristica = nombreHeuristica;                
    }
    
    
    public String getTipo()
    {
        return tipo;
    }
    
    
    public String getNombreHeuristica()
    {
        return nombreHeuristica;
    }
    
    
    public abstract void mostrarEstadoActual();
    
}
