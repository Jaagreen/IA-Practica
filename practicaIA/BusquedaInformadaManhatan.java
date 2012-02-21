/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

package practicaIA;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase que implementa el metodo de busqueda informada mediante el algoritmo A*
 * y utilizando como heuristica una variacion de la heuristica de Manhatan.
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public class BusquedaInformadaManhatan extends EstrategiaBusqueda
{
    private Set<Nodo> nodosAbiertos;
    private Set<Nodo> nodosCerrados;
    
    public BusquedaInformadaManhatan(Mapa mapa)
    {
        super("Busqueda informada A*", "Manhatan (modificada)", mapa);
        
        //Inicializamos como listas vacias las listas de nodos abiertos y cerrados.
        this.nodosAbiertos = new HashSet<Nodo>();
        this.nodosCerrados = new HashSet<Nodo>();
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
    private int funcionHeuristica(Nodo n)
    {
        Viajero v = getViajero();                
        
        return costeGirosOrientacion(n) + //Coste de los giros para orientarse en la direccion correcta.
               Math.abs(v.getPosMeta()[0] - n.getPosicion()[0]) + //Distancia por filas.
               Math.abs(v.getPosMeta()[1] - n.getPosicion()[1]) + //Distancia por columnas.
               //Anadimos 1 (el coste del giro) si el viajero no esta en la misma fila o columna que la meta.
               (v.getPosMeta()[0] == n.getPosicion()[0] || v.getPosMeta()[1] == n.getPosicion()[1]? 0 : 1);
    }
    
    /**
     * Metodo que busca en el conjunto de nodos abiertos un nodo con la misma posicion 
     * y orientacion. No se tiene en cuenta que el nodo padre sea el mismo ni que 
     * el coste y el valor de la funcion heuristica sea el mismo. Solo es necesario que
     * coincida la posicion y la orientacion para considerar que el nodo recibido como
     * parametro es el mismo que el encontrado en el conjunto de nodos abiertos.
     * @param nodo Nodos que sobre el que se quiere saber si existe en el conjunto de nodos abiertos.
     * @return El nodo de la lista de nodos abiertos que tiene la misma posicon y
     *         orientacion que el recibido como parametro, o null en caso de que no
     *         haya ningun nodo que coincida.
     */
    private Nodo existeNodoEnAbiertos(Nodo nodo)
    {
        //Recorremos todo el conjunto de nodos abiertos.
        for(Nodo n: nodosAbiertos)
            //Si encontramos un nodo con la misma posicion y orientacion que el nodo 
            //recibido como parametro entonces devolvemos ese nodo.
            if(nodo.equals(n))
                return n;
        
        //Si no se ha encontrado ningun nodo igual devolvemos null.
        return null;
    }
    

    /**
     * Metodo que busca en el conjunto de nodos cerrados un nodo con la misma posicion 
     * y orientacion. No se tiene en cuenta que el nodo padre sea el mismo ni que 
     * el coste y el valor de la funcion heuristica sea el mismo. Solo es necesario que
     * coincida la posicion y la orientacion para considerar que el nodo recibido como
     * parametro es el mismo que el encontrado en el conjunto de nodos cerrados.
     * @param nodo Nodos que sobre el que se quiere saber si existe en el conjunto de nodos cerrados.
     * @return El nodo de la lista de nodos cerrados que tiene la misma posicon y
     *         orientacion que el recibido como parametro, o null en caso de que no
     *         haya ningun nodo que coincida.
     */
    private Nodo existeNodoEnCerrados(Nodo nodo)
    {
        //Recorremos todo el conjunto de nodos abiertos.
        for(Nodo n: nodosCerrados)
            //Si encontramos un nodo con la misma posicion y orientacion que el nodo 
            //recibido como parametro entonces devolvemos ese nodo.
            if(nodo.equals(n))
                return n;
        
        //Si no se ha encontrado ningun nodo igual devolvemos null.
        return null;
    }
    
    
    @Override
    public void buscar()
    {       
        //Creamos el nodo inicial con los datos de la posicion de inicio del viajero y su orientacion.
        Nodo nodoInicial = new Nodo(getViajero().getPosInicio(), getViajero().getOrientacion());
        
        //Como es el primer no no tiene padre.
        nodoInicial.setPadre(null);
        
        //Como es el primer nodo el coste para llegar hasta el es 0.
        nodoInicial.setCoste(0);
        
        //Le asignamos el valor de heuristica que le corresponde.
        nodoInicial.setValorHeuristica(funcionHeuristica(nodoInicial));
        
        //Anadimos el nodo inicial en la lista de nodos abiertos.
        nodosAbiertos.add(nodoInicial);
        
        //Mientras que haya nodos en la lista de nodos abiertos.
        while(!nodosAbiertos.isEmpty())
        {            
            //Obtenemso el primer nodo de la lista de nodos abiertos.
            Nodo mejorNodo = nodosAbiertos.iterator().next();
            
            //Buscamos en la lista de nodos abiertos el nodo que mejor valor de la funcion f() tenga. 
            //Cuanto menos valor de f() mejor es el nodo. f = coste + valorFuncionHeuristica.
            for(Nodo nodo: nodosAbiertos)
            {
                if(nodo.f() < mejorNodo.f())
                    mejorNodo = nodo;
            }
            
            nodosAbiertos.remove(mejorNodo);
            
            System.out.println("\nMejor nodo = " + mejorNodo + " hashcode=" + mejorNodo.hashCode());
            
            //Quitamos el mejorNodo de la lista de nodos abiertos y lo metemos en la lista de nodos cerrados.
            nodosCerrados.add(mejorNodo);
            
            
            //Comprobamos si el mejor nodo selecionado es meta.
            if(esNodoMeta(mejorNodo))
            {
                //TODO HAY QUE TERMINAR LA LISTA DE TAREAS A RALIZAR CUANDO SE ENCUENTRE EL NODO META.
                System.out.println("¡¡¡SOLUCION ENCONTRADA!!!");
                return;
            }
            
            //Situamos al viajero en la posicion del mejor nodo.
            //getViajero().setPosicion(mejorNodo.getPosicion());
            getViajero().setPosicionYorientacion(mejorNodo);
            
            for(Nodo sucesor: getViajero().getNodosSucesores())
            {
                //Asignamos como padre de cada nodo suceso al nodo mejorNodo.
                sucesor.setPadre(mejorNodo);
                
                //El coste de cada nodo sucesor es el coste de ir hasta el mejorNodo 
                //mas el coste de ir desde el mejorNodo hasta el nodo sucesor actual.
                sucesor.setCoste(mejorNodo.getCoste() + 
                                 getMapa().getDificultadPosicion(sucesor.getPosicion()[0], 
                                                                 sucesor.getPosicion()[1]));
                              
                Nodo viejo;
                
                //Comprobamos si ya existia el nodo en el conjunto de nodos abiertos.
                if((viejo = existeNodoEnAbiertos(sucesor)) != null)
                {
                    mejorNodo.addSucesor(viejo);
                    
                    //Analizamos si el nuevo padre que hemos encontrado para el nodo viejo
                    //es mejor que el que tenia, y si es asi actualizarmos el coste y enlace paterno,
                    //de modo que en el grafo queden reflejados solo los caminos optimos.
                    //Entoces, si el coste para llegar al nodo viejo es mayor que para el nodo sucesor.
                    if(viejo.getCoste() > sucesor.getCoste())
                    {
                        viejo.setPadre(mejorNodo); //Al nodo viejo le asignamos como padre el nodo mejorNodo.
                        viejo.setCoste(sucesor.getCoste()); //Actualizamos el coste del nodo.
                    }
                }
                //Comprobamos si ya existia el nodo en el conjunto de nodos cerrados.
                else if((viejo = existeNodoEnCerrados(sucesor)) != null)
                {
                    
                    mejorNodo.addSucesor(viejo);
                    
                    //Si el coste para llegar al nodo viejo es mayor que para el nodo sucesor.
                    if(viejo.getCoste() > sucesor.getCoste())
                    {
                        viejo.setPadre(mejorNodo); //Al nodo viejo le asignamos como padre el nodo mejorNodo.
                        viejo.setCoste(sucesor.getCoste()); //Actualizamos el coste del nodo.
                        
                        //Propagamos el nuevo mejor coste realizando un recorrido en 
                        //profundidad a partir del nodo viejo.
                        propagarMejora(viejo);
                    }
                }
                else //Si el nodo no existe en el conjunto de abiertos ni de cerrados.
                {
                    System.out.println("Sucesor = " + sucesor + " hashcode=" + sucesor.hashCode());
                    //Le asignamos el valor de heuristica que le corresponde.
                    sucesor.setValorHeuristica(funcionHeuristica(sucesor));
                    
                    //Anadimos el nodo al conjunto de nodos abiertos.
                    nodosAbiertos.add(sucesor);
                    
                    //Anadimos el nodo como sucesor del nodo mejorNodo.
                    mejorNodo.addSucesor(sucesor);
                }
                
                System.out.println("Sucesor = " + sucesor + " hashcode=" + sucesor.hashCode());
            }
        }
    }
    
    /**
     *  Metodo para propagar la mejora obtenida sobre el coste del nodo indicado
     * como parametro.
     * @param nodo Nodo a partir del cual se propagara la mejora.
     */
    private void propagarMejora(Nodo nodo)
    {
        //Recorremos el conjunto de nodos sucesores del nodo recibido como parametro.
        for(Nodo sucesor: nodo.getSucesores())
        {
            //Comprobamos si el padre del sucesor es el nodo recibido como parametro.
            if(sucesor.getPadre() == nodo)
            {
                //Actualizamos el valor del coste para llegar hasta este nodo.
                sucesor.setCoste(nodo.getCoste() + 
                                 getMapa().getDificultadPosicion(sucesor.getPosicion()[0], 
                                                                 sucesor.getPosicion()[1]));
                Nodo viejo;
                //Si el nodo sucesor esta en el conjunto de nodos cerrados.
                if((viejo = existeNodoEnCerrados(sucesor)) != null)
                    propagarMejora(viejo); //Propagamos la mejora.
            }
            //Si el padre del sucesor no es el nodo recibido como parametro y el 
            //nodo recibido como parametro tiene menor coste que el padre del nodo sucesor.
            else if(nodo.getCoste() < sucesor.getPadre().getCoste())
            {
                //Asignamos el nodo recibido como parametro como padre del sucesor actual. 
                sucesor.setPadre(nodo);
                
                //Actualizamos el valor del coste para llegar hasta este nodo.
                sucesor.setCoste(nodo.getCoste() + 
                                 getMapa().getDificultadPosicion(sucesor.getPosicion()[0], 
                                                                 sucesor.getPosicion()[1]));
                Nodo viejo;
                //Si el nodo sucesor esta en el conjunto de nodos cerrados.
                if((viejo = existeNodoEnCerrados(sucesor)) != null)
                    propagarMejora(viejo); //Propagamos la mejora.
            }
        }
    }  
     
    
    @Override
    public void mostrarEstadoActual()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
