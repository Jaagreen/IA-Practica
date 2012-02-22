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

            //Anadimos el nodo inicial en la lista de nodos abiertos.
            nodosAbiertos.add(nodoInicial);
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

            //Si no existe en la lista de nodos abiertos, entnces lo anadimos
            if(!nodosAbiertos.contains(n))
                nodosAbiertos.addLast(sucesor);
        }
        
        //Realizamos la prueba de meta a los sucesores.
        for(Nodo sucesor: conjuntoSucesores)
        {
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


    private void mostrarCaminoSeguido(Nodo mejorNodo)
    {
        //Reconstruimos el camino optimo.
        LinkedList<Nodo> camino = new LinkedList<Nodo>();

        //Anadimos cada padre del mejor nodo al camino optimo.
        while(mejorNodo != null)
        {
            camino.addFirst(mejorNodo);
            mejorNodo = mejorNodo.getPadre();
        }

        System.out.println("CAMINO SEGUIDO:");
        //Mostramos el camino optimo.
        for(int i = 0; i < (camino.size()-1); i++)
            System.out.print(camino.get(i) + " -> ");

        System.out.println(camino.getLast() + "\n");
    }


    private void mostrarNodosAbiertos()
    {
        System.out.println("\nNODOS ABIERTOS:");

        //Mostramos el conjunto de nodos abiertos.
        for(Nodo nodo: nodosAbiertos)
            System.out.print(nodo + " - ");

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
            System.out.println("¡¡¡SOLUCION ENCONTRADA!!!");
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

        Funciones.pausa();

        //Mientras que haya nodos en la lista de nodos abiertos.
        while(!nodosAbiertos.isEmpty() && !solucionEncontrada)
        {
            solucionEncontrada = realizarIteracionDeBusqueda();

            Funciones.limpiarPantalla();
            getMapa().mostrar();
            mostarInformacion();

            mostrarNodosAbiertos();

            Funciones.pausa();
        }

        marcarPosicionesCaminoEncontrado();

        if(solucionEncontrada)
        {
            System.out.println("\n¡¡¡SOLUCION ENCONTRADA!!!");
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
