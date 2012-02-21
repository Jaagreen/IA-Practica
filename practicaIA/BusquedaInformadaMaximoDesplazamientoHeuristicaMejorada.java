/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

package practicaIA;

/**
 * Clase que implementa el metodo de busqueda informada mediante el algoritmo A*
 * y utilizando una heuristica mejorada basada en el maximo desplazamiento en 
 * filas o columnas.
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public class BusquedaInformadaMaximoDesplazamientoHeuristicaMejorada extends BusquedaInformadaAestrella
{
    private BusquedaInformadaMaximoDesplazamiento heuristicaMaximoDesplazamientoOriginal;
    
    public BusquedaInformadaMaximoDesplazamientoHeuristicaMejorada(Mapa mapa)
    {
        super("Maximo desplazamiento en filas o columnas (mejorado)", mapa);
        
        heuristicaMaximoDesplazamientoOriginal = new BusquedaInformadaMaximoDesplazamiento(mapa);
    }

    
    /**
     * Metodo para calcular cal sera el coste minimo para desplazarse desde la
     * posicion indicada por el nodo a cualquier nodo vecino.
     * @param n Nodo sobre el que se quiere conocer el minimo coste de avance.
     * @return El valor menos uno del minimo coste de avance a cualquier nodo vecino.
     */
    private int costeDesplazamientoMinimo(Nodo n)
    {
        int minimo = 5; //Le asignamos el valor del coste maximo de avanzar a una posicion.
        
        int fila = n.getPosicion()[0];
        int col = n.getPosicion()[1];
        
        if(fila > 0 && getMapa().getDificultadPosicion(fila - 1, col) < minimo)
            minimo = getMapa().getDificultadPosicion(fila - 1, col);
        
        if(col > 0 && getMapa().getDificultadPosicion(fila, col - 1) < minimo)
            minimo = getMapa().getDificultadPosicion(fila, col - 1);
        
        if(fila < (getMapa().getNumeroFilas() - 1) && getMapa().getDificultadPosicion(fila + 1, col) < minimo)
            minimo = getMapa().getDificultadPosicion(fila + 1, col);
        
        if(col < (getMapa().getNumeroColumnas() -1) && getMapa().getDificultadPosicion(fila, col + 1) < minimo)
            minimo = getMapa().getDificultadPosicion(fila, col + 1);
        
        //Si se permiten giros de 45 grados entoces tambie hay que mirar las posiciones vecinas en diagonal.
        if(getViajero().getGradosGiros() == 45)
        {
            //Comprobamso que no estamos en la primera fila
            if(fila > 0)
            {   //Comprobamso que no estamos en la esquina superior izquierda.
                if(col > 0 && getMapa().getDificultadPosicion(fila -1, col -1) < minimo)
                    minimo = getMapa().getDificultadPosicion(fila - 1, col -1);
                
                //Comprobamso que no estamos en la esquina superior derecha.
                if(col < (getMapa().getNumeroColumnas() -1) && getMapa().getDificultadPosicion(fila -1, col +1) < minimo)
                    minimo = getMapa().getDificultadPosicion(fila -1, col + 1);            
            }

            //Comprobamso que no estamos en la ultima fila.
            if(fila < (getMapa().getNumeroFilas() - 1))
            {
                //Comprobamso que no estamos en la esquina inferior izquierda.
                if(col > 0 && getMapa().getDificultadPosicion(fila +1, col -1) < minimo)
                    minimo = getMapa().getDificultadPosicion(fila +1, col -1);
                
                //Comprobamso que no estamos en la esquina inferior derecha.
                if(col < (getMapa().getNumeroColumnas() -1) && getMapa().getDificultadPosicion(fila +1, col +1) < minimo)
                    minimo = getMapa().getDificultadPosicion(fila +1, col + 1);            
            }            
        }
        
        //Restamos uno ya que ese valor ya esta contemplado en la otra parte de la funcion heuristica.
        return minimo - 1; 
    }

    
    @Override
    int funcionHeuristica(Nodo n)
    {
        return heuristicaMaximoDesplazamientoOriginal.funcionHeuristica(n) + costeDesplazamientoMinimo(n);
    }
}
