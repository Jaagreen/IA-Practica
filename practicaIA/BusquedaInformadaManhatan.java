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
public class BusquedaInformadaManhatan extends BusquedaInformadaAestrella
{    
    public BusquedaInformadaManhatan(Mapa mapa)
    {
        super("Manhatan (modificada)", mapa);
    }
    
    /**
     * Metodo que devuelve el numero minimo de giros a realizar con respecto a la
     * orientación actual del viajero para que éste se encuentre en dirección de
     * avance correcta hacia la posicion de meta.
     * @return El coste e los giros para orientarse correctamente.
     */
    private int costeGirosOrientacion(Nodo n)
    {
        Viajero v = getViajero();
        
        //Si el viajero esta en una fila mas hacia el norte que la meta.
        if(n.getPosicion()[0] < v.getPosMeta()[0])
        {
            if(n.getPosicion()[1] > v.getPosMeta()[1]) //Si el viajero esta a la derecha de la meta.
            {
                switch(n.getOrientacion())
                {
                    case SUR:
                    case OESTE: return 0;
                    case NORTE:                     
                    case ESTE: return 1;
                }
            }
            else if(n.getPosicion()[1] < v.getPosMeta()[1]) //Si el viajero esta a la izquierda de la meta.
            {
                switch(n.getOrientacion())
                {
                    case SUR: 
                    case ESTE: return 0;
                    case NORTE: 
                    case OESTE: return 1;
                }
            }
            else //El viajero esta en la misma columna que la posicion de meta.
            {
                switch(n.getOrientacion()) 
                {                    
                    case SUR: return 0;
                    case ESTE:
                    case OESTE: return 1;
                    case NORTE: return 2;
                }
            }
        }
        //Si el viajero esta en una fila mas hacia el sur que la de la meta
        else if(n.getPosicion()[0] > v.getPosMeta()[0])
        {
            if(n.getPosicion()[1] > v.getPosMeta()[1]) //Si el viajero esta a la derecha de la meta.
            {
                switch(n.getOrientacion())
                {
                    case NORTE:
                    case OESTE: return 0;
                    case SUR:                     
                    case ESTE: return 1;
                }
            }
            else if(n.getPosicion()[1] < v.getPosMeta()[1]) //Si el viajero esta a la izquierda de la meta.
            {
                switch(n.getOrientacion())
                {
                    case NORTE: 
                    case ESTE: return 0;
                    case SUR: 
                    case OESTE: return 1;
                }
            }
            else //El viajero esta en la misma columna que la posicion de meta.
            {
                switch(n.getOrientacion()) 
                {                    
                    case NORTE: return 0;
                    case ESTE:
                    case OESTE: return 1;
                    case SUR: return 2;
                }
            }            
        }
        else //Si el viajero esta en la misma fila que la posicion de meta.
        {
            if(n.getPosicion()[1] > v.getPosMeta()[1]) //Si el viajero esta a la derecha de la meta.
            {
                switch(n.getOrientacion())
                {
                    case OESTE: return 0;
                    case NORTE:
                    case SUR: return 1;
                    case ESTE: return 2;
                }
            }
            else if(n.getPosicion()[1] < v.getPosMeta()[1]) //Si el viajero esta a la izquierda de la meta.
            {
                switch(n.getOrientacion())
                {
                    case OESTE: return 1;
                    case NORTE:
                    case SUR: return 1;
                    case ESTE: return 0;
                }
            }
            else
                return 0; //El viajero esta en la posicion de meta.                
        }
        
        return 0;
    }
    
    
    /**
     * Funcion que estima el coste para llegar a la meta desde la posion y orientacion
     * indicada por el nodo recidbo como parametro.
     * @param n Nodo sobre el que se quiere conocer el valor de la funcion heuristica.
     * @return El valor de la funcion heuristica para el nodo.
     */
    @Override
    int funcionHeuristica(Nodo n)
    {
        Viajero v = getViajero();                
        
        return costeGirosOrientacion(n) + //Coste de los giros para orientarse en la direccion correcta.
               Math.abs(v.getPosMeta()[0] - n.getPosicion()[0]) + //Distancia por filas.
               Math.abs(v.getPosMeta()[1] - n.getPosicion()[1]) + //Distancia por columnas.
               //Anadimos 1 (el coste del giro) si el viajero no esta en la misma fila o columna que la meta.
               (v.getPosMeta()[0] == n.getPosicion()[0] || v.getPosMeta()[1] == n.getPosicion()[1]? 0 : 1);
    }
}
