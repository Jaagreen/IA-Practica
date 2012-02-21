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
    private Viajero viajero;
    private Mapa mapa;
    
    public EstrategiaBusqueda(String tipo, String nombreHeuristica, Mapa mapa)
    {
        this.tipo = tipo;
        this.nombreHeuristica = nombreHeuristica;                
        this.mapa = mapa;
        this.viajero = mapa.getViajero();
    }
    
    
    public String getTipo()
    {
        return tipo;
    }
    
    
    public String getNombreHeuristica()
    {
        return nombreHeuristica;
    }
    
    
    public Mapa getMapa()
    {
        return mapa;
    }
    
    
    public Viajero getViajero()
    {
        return viajero;
    }
    
    /**
     * Metodo que determina si un nodo es solucion del problema, es decir, si el nodo
     * es la posicon de destino a la que quiere llegar el viajero.
     * @param nodo Nodo al que se le quiere realizar la prueba de meta.
     * @return True si la posicion del nodo coincide con la de la meta, false en caso contrario.
     */
    public boolean esNodoMeta(Nodo nodo)
    {
        //Un nodo es meta si coindide con la posicion de meta a la que quiere llegar el viajero.
        return nodo.getPosicion()[0] == viajero.getPosMeta()[0] &&
               nodo.getPosicion()[1] == viajero.getPosMeta()[1];
    }
    
    public abstract void mostrarEstadoActual();
    
    public abstract void buscar();
    
    public abstract void buscarIteraionAiteracion();
    
    public void resetear(Mapa mapa)
    {
        this.mapa = mapa;
        this.viajero = mapa.getViajero();
    }
    
    public void mostarInformacion()
    {
        System.out.print("\033[s"); //Guardamos la posicion del cursor.

        System.out.printf("\033[4;%dHGiros de: " + viajero.getGradosGiros() + " grados\n", 10+4*mapa.getNumeroColumnas());

        
        System.out.printf("\033[%dGEstrategia: " + tipo + "\n", 10+4*mapa.getNumeroColumnas());

        if(!nombreHeuristica.equals(""))
            System.out.printf("\033[%dGHeuristica: " + nombreHeuristica + "\n", 
                                10+4*mapa.getNumeroColumnas());

        System.out.printf("\033[%dGCoste posicion G: " + 
                          mapa.getDificultadPosicion(viajero.getPosMeta()[0], 
                                                     viajero.getPosMeta()[1]) + 
                          "\n", 10+4*mapa.getNumeroColumnas());

        
        System.out.printf("\033[%dGOrientacion: " + viajero.getOrientacion() + "\n", 10+4*mapa.getNumeroColumnas());

        System.out.printf("\033[%dGPosicion: (" + viajero.getPosicion()[0] + "," + 
                          viajero.getPosicion()[1] + ")\n", 10+4*mapa.getNumeroColumnas());

        System.out.printf("\033[%dGNodo: ([(fila, col) orientacion], coste, heuristica)", 
                           10+4*mapa.getNumeroColumnas());
        
        System.out.print("\033[u"); //Restauramos la posicion del cursor.         
    }
    
}
