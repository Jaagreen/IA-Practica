/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

package practicaIA;

import java.util.LinkedList;
import java.util.Set;

/**
 * Clase que implementa el metodo de busqueda ciega en anchura.
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public class BusquedaCiegaEnAnchura extends EstrategiaBusqueda
{
    private LinkedList<Nodo> nodosAbiertos;
    private LinkedList<Nodo> nodosCerrados;
    private Nodo nodoInicial = null;
    private Nodo nodoFinal = null;
    private boolean solucionEncontrada = false;

    public BusquedaCiegaEnAnchura(Mapa mapa)
    {
        super("Busqueda ciega en anchura", "", mapa);

        //Inicializamos como listas vacias las listas de nodos abiertos y cerrados.
        this.nodosAbiertos = new LinkedList<Nodo>();
        this.nodosCerrados = new LinkedList<Nodo>();
    }


    @Override
    public void resetear(Mapa mapa)
    {
        super.resetear(mapa);
        this.nodosAbiertos = new LinkedList<Nodo>();
        this.nodosCerrados = new LinkedList<Nodo>();
        nodoInicial = null;
        solucionEncontrada = false;
    }


    private void inicializarBusqueda()
    {
        //Si aun no se ha iniciado la busqueda el nodoInicial tendra valor null.
        //Eso indica que la inicializacion aun no se ha hecho.
        if(nodoInicial == null)
        {
            //Creamos el nodo inicial con los datos de la posicion de inicio del viajero y su orientacion.
            nodoInicial = new Nodo(getViajero().getPosicion(), getViajero().getOrientacion());

            //Como es el primer no no tiene padre.
            nodoInicial.setPadre(null);

            //Como es el primer nodo el coste para llegar hasta el es 0.
            nodoInicial.setCoste(0);

            nodoInicial.setProfundidad(0);

            //Anadimos el nodo inicial en la lista de nodos abiertos.
            nodosAbiertos.add(nodoInicial);

            aumentarCotadorNodosGenerado();
        }
    }


    private boolean realizarIteracionDeBusqueda()
    {
        Nodo n = nodosAbiertos.getFirst();

        //Quitamos el mejorNodo de la lista de nodos abiertos y lo metemos en la lista de nodos cerrados.
        nodosCerrados.add(n);
        nodosAbiertos.remove(n);

        //Situamos al viajero en la posicion del mejor nodo.
        getViajero().setPosicionYorientacion(n);
        getMapa().setPosicionAccedida(n.getPosicion());

        Set<Nodo> conjuntoSucesores = getViajero().getNodosSucesores();

        //Anadimos los sucesores a la lista de nodos abiertos.
        for(Nodo sucesor: conjuntoSucesores)
        {
            //Asignamos como padre de cada nodo suceso al nodo mejorNodo.
            sucesor.setPadre(n);

            //Si no existe en la lista de nodos abiertos, entonces lo anadimos
            if(!nodosAbiertos.contains(n))
                nodosAbiertos.addLast(sucesor);
        }
        
        //Realizamos la prueba de meta a los sucesores.
        for(Nodo sucesor: conjuntoSucesores)
        {
            aumentarCotadorNodosGenerado();
            
            sucesor.setProfundidad(n.getProfundidad() + 1);

            //Si el nodo sucesor esta orientado en la misma direccion es por que
            //lo que se ha hecho es avanzar.
            if(sucesor.getOrientacion().equals(n.getOrientacion()))
            {
                //El coste de cada nodo sucesor es el coste de ir hasta el nodo n
                //mas el coste de ir desde el nodo n hasta el nodo sucesor actual.
                sucesor.setCoste(n.getCoste() +
                                 getMapa().getDificultadPosicion(sucesor.getPosicion()[0],
                                                                 sucesor.getPosicion()[1]));
            }
            else //Si la direccion es distinta lo que se ha hecho es girar por tanto el coste es 1.
            {
                //Si se ha realizado un cambio de direccion sumamos uno al coste total.
                sucesor.setCoste(n.getCoste() + 1);
            }


            //Comprobamos si el nodo actual es meta.
            if(esNodoMeta(sucesor))
            {
                //Situamos al viajero en la posicion del mejor nodo.
                getViajero().setPosicionYorientacion(sucesor);
                nodoFinal = sucesor;
                return true;
            }
        }

        //El mejor nodo seleccionado no era la posicion de meta.
        return false;
    }


    private void mostrarCaminoSeguido(Nodo nodo)
    {
        //Reconstruimos el camino optimo.
        LinkedList<Nodo> camino = new LinkedList<Nodo>();

        //Anadimos cada padre del mejor nodo al camino optimo.
        while(nodo != null)
        {
            camino.addFirst(nodo);
            nodo = nodo.getPadre();
        }

        System.out.println("CAMINO SEGUIDO:");
        //Mostramos el camino optimo.
        for(int i = 0; i < (camino.size()-1); i++)
            System.out.print(camino.get(i).estadoYcoste() + " -> ");

        System.out.println(camino.getLast().estadoYcoste() + "\n");
    }


    private void mostrarNodosAbiertos()
    {
        System.out.println("\nNODOS ABIERTOS:");

        //Mostramos el conjunto de nodos abiertos.
        for(Nodo nodo: nodosAbiertos)
            System.out.print(nodo.estadoYcoste() + " - ");

        System.out.println("\n");
    }


    private void marcarPosicionesCaminoEncontrado()
    {
        if(solucionEncontrada)
        {
            getMapa().borrarPosicionesAccedidas();

            Nodo n = nodoFinal;
            while(n != null)
            {
                getMapa().setPosicionAccedida(n.getPosicion());
                n = n.getPadre();
            }
        }
    }

    private void resultadosBusqueda()
    {
        System.out.println("COSTE DE LA BUSQUEDA: " + nodosCerrados.size());
        System.out.println("PROFUNDIDAD DEL ARBOL: " + nodoFinal.getCoste());
        System.out.println("NUMERO DE NODOS GENERADOS: " + getNumeroNodosGenerados());
        System.out.println("FACTOR DE RAMIFIACION EFECTIVA: " +                               
                           BranchingFactor.compute(getNumeroNodosGenerados(), nodoFinal.getProfundidad()));
    }


    @Override
    public void buscar()
    {
        inicializarBusqueda();

        Funciones.limpiarPantalla();
        
        if(esNodoMeta(nodoInicial))
        {
            nodoFinal = nodoInicial;
        }
        else
        {
            //Mientras que haya nodos en la lista de nodos abiertos.
            while(!nodosAbiertos.isEmpty() && !solucionEncontrada)
            {
                solucionEncontrada = realizarIteracionDeBusqueda();
            }
        }

        marcarPosicionesCaminoEncontrado();

        getMapa().mostrar();
        mostarInformacion();

        mostrarNodosAbiertos();

        mostrarCaminoSeguido(nodoFinal);

        if(solucionEncontrada)
        {
            System.out.println("¡¡¡SOLUCION ENCONTRADA!!!\n");

            resultadosBusqueda();
        }
        else if(nodosAbiertos.isEmpty())
        {
            System.out.println("No se ha podido encontrar la solucion al problema.");
        }

        Funciones.pausa();

        //Restauramos el valor de solucionEncotrada por si se quiere volver a realizar la busqueda.
        solucionEncontrada = false;
    }


    @Override
    public void buscarIteraionAiteracion()
    {
        inicializarBusqueda();

        Funciones.limpiarPantalla();

        getMapa().mostrar();
        mostarInformacion();
        mostrarNodosAbiertos();        

        //Mientras que haya nodos en la lista de nodos abiertos.
        while(!nodosAbiertos.isEmpty() && !solucionEncontrada)
        {
            Funciones.pausa();
            
            solucionEncontrada = realizarIteracionDeBusqueda();

            Funciones.limpiarPantalla();
            getMapa().mostrar();
            mostarInformacion();

            mostrarNodosAbiertos();            
        }

        marcarPosicionesCaminoEncontrado();        

        mostrarCaminoSeguido(nodoFinal);

        if(solucionEncontrada)
        {
            System.out.println("\n¡¡¡SOLUCION ENCONTRADA!!!\n");

            resultadosBusqueda();
        }
        else if(nodosAbiertos.isEmpty())
        {
            System.out.println("No se ha podido encontrar la solucion al problema.");
        }

        Funciones.pausa();

        //Restauramos el valor de solucionEncotrada por si se quiere volver a realizar la busqueda.
        solucionEncontrada = false;
    }
}
