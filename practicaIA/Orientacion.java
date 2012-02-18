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
    NORTE("norte", "N", 0),
    SUR("sur", "S", 1),
    ESTE("este", "E", 2),
    OESTE("oeste", "O", 3),
    NORESTE("noreste", "NE", 4),
    NOROESTE("noroeste", "NO", 5),
    SURESTE("sureste", "SE", 6),
    SUROESTE("suroeste", "SO", 7);
    
    private String valor;
    private String abreviatura;
    private int id;
    
    Orientacion(String valor, String abreviatura, int id)
    {
        this.valor = valor;
        this.abreviatura = abreviatura;
        this.id = id;
    }
    
    
    public String getValor()
    {
        return valor;
    }
    
    
    public String getAbrebiatura()
    {
        return abreviatura;
    }
    
    
    public int getId()
    {
        return id;
    }
    
    /**
     * Metodo para obtener la Orientacion asociada a un identificador.
     * @param id El identificador de la orientacion que se quiere obtener.
     * @return La orientacion cuyo id coincide con el indicado por parametro o 
     *         null en caso de que no coincida con ninguno.
     */
    public static Orientacion getOrientacionById(int id)
    {        
        for(Orientacion o: Orientacion.values())
        {
            if(o.getId() == id)
                return o;
        }
        
        return null;
    }
}
