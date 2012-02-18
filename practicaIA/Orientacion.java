/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

package practicaIA;

/**
 * Enumerado para indicar los tipos de orientacion.
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public enum Orientacion
{
    NORTE("norte", "N"),
    SUR("sur", "S"),
    ESTE("este", "E"),
    OESTE("oeste", "O"),
    NORESTE("noreste", "NE"),
    NOROESTE("noroeste", "NO"),
    SURESTE("sureste", "SE"),
    SUROROESTE("suroeste", "SO");
    
    private String valor;
    private String abreviatura;
    
    Orientacion(String valor, String abreviatura)
    {
        this.valor = valor;
        this.abreviatura = abreviatura;
    }
    
    public String getValor()
    {
        return valor;
    }
    
    public String getAbrebiatura()
    {
        return abreviatura;
    }
}
