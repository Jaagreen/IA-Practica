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
public class BusquedaInformadaMaximoDesplazamiento extends BusquedaInformadaAestrella
{
    public BusquedaInformadaMaximoDesplazamiento(Mapa mapa)
    {
        super("Maximo desplazamiento en filas o columnas", mapa);
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
                    case NORESTE: return 4;
                    case NORTE:
                    case ESTE: return 3;
                    case NOROESTE:
                    case SURESTE: return 2;
                    case OESTE:
                    case SUR: return 1;
                    case SUROESTE: return 0;
                            
                }
            }
            else if(n.getPosicion()[1] < v.getPosMeta()[1]) //Si el viajero esta a la izquierda de la meta.
            {
                switch(n.getOrientacion())
                {
                    case NOROESTE: return 4;
                    case NORTE:
                    case OESTE: return 3;
                    case NORESTE:
                    case SUROESTE: return 2;
                    case ESTE:
                    case SUR: return 1;
                    case SURESTE: return 0;
                }
            }
            else //El viajero esta en la misma columna que la posicion de meta.
            {
                switch(n.getOrientacion()) 
                {                    
                    case NORTE: return 4;
                    case NORESTE:
                    case NOROESTE: return 3;
                    case ESTE:
                    case OESTE: return 2;
                    case SURESTE:
                    case SUROESTE: return 1;
                    case SUR: return 0;
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
                    case NOROESTE: return 0;
                    case NORTE:
                    case OESTE: return 1;
                    case NORESTE:
                    case SUROESTE: return 2;
                    case ESTE:
                    case SUR: return 3;
                    case SURESTE: return 4;
                }
            }
            else if(n.getPosicion()[1] < v.getPosMeta()[1]) //Si el viajero esta a la izquierda de la meta.
            {
                switch(n.getOrientacion())
                {
                    case NORESTE: return 0;
                    case NORTE:
                    case ESTE: return 1;
                    case NOROESTE:
                    case SURESTE: return 2;
                    case OESTE:
                    case SUR: return 3;
                    case SUROESTE: return 4;
                }
            }
            else //El viajero esta en la misma columna que la posicion de meta.
            {
                switch(n.getOrientacion()) 
                {                    
                    case NORTE: return 0;
                    case NORESTE:
                    case NOROESTE: return 1;
                    case ESTE:
                    case OESTE: return 2;
                    case SURESTE:
                    case SUROESTE: return 3;
                    case SUR: return 4;
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
                    case NOROESTE: 
                    case SUROESTE: return 1;
                    case NORTE:
                    case SUR: return 2;
                    case NORESTE:
                    case SURESTE: return 3;
                    case ESTE: return 4;
                }
            }
            else if(n.getPosicion()[1] < v.getPosMeta()[1]) //Si el viajero esta a la izquierda de la meta.
            {
                switch(n.getOrientacion())
                {
                    case OESTE: return 4;
                    case NOROESTE: 
                    case SUROESTE: return 3;
                    case NORTE:
                    case SUR: return 2;
                    case NORESTE:
                    case SURESTE: return 1;
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
               //Maximo de la distancia por filas o por columnas.
               Math.max(Math.abs(v.getPosMeta()[0] - n.getPosicion()[0]), //Distancia por filas.
                        Math.abs(v.getPosMeta()[1] - n.getPosicion()[1])) + //Distancia por columnas.
               //Anadimos 1 (el coste del giro) si el viajero no esta en la misma fila o 
               //columna que la meta y tampoco esta a la misma distanta por filas y por columnas.
               (v.getPosMeta()[0] == n.getPosicion()[0] || 
                v.getPosMeta()[1] == n.getPosicion()[1] ||
                Math.abs(v.getPosMeta()[0] - n.getPosicion()[0]) ==      //Comprobamso que no este a la misma distancia
                Math.abs(v.getPosMeta()[1] - n.getPosicion()[1])? 0 : 1);//por filas y por columnas
    }
}
