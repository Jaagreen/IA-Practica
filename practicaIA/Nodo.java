/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

package practicaIA;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase que representa cada uno de los nodos del arbol de busqueda.
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public class Nodo
{
    private int posicion[] = new int[2];
    private Orientacion orientacion;
    private Nodo padre;
    private Set<Nodo> sucesores;
    private int coste;
    private int valorHeuristica;
    private boolean nodoExplorado;
    
    public Nodo(int posicion[], Orientacion orientacion)
    {
        this.posicion[0] = posicion[0];
        this.posicion[1] = posicion[1];
        this.orientacion = orientacion;        
        sucesores = new HashSet<Nodo>();
        nodoExplorado = false;
    }
    
    
    public Nodo(int posicion[], Orientacion orientacion, Nodo padre, int coste, int valorHeuristica)
    {
        this.posicion[0] = posicion[0];
        this.posicion[1] = posicion[1];
        this.orientacion = orientacion;
        this.padre = padre;
        this.coste = coste;
        this.valorHeuristica = valorHeuristica;
        sucesores = new HashSet<Nodo>();
        nodoExplorado = false;
    }
    
    public int[] getPosicion()
    {
        return posicion;
    }
    
    public Orientacion getOrientacion()
    {
        return orientacion;
    }
    
    public Nodo getPadre()
    {
        return padre;
    }
    
    
    public void setPadre(Nodo padre)
    {
        this.padre = padre;
    }
    
    
    public int getCoste()
    {
        return coste;
    }
    
    
    public void setCoste(int coste)
    {
        this.coste = coste;
    }
    
    
    public int getValorHeuristica()
    {
        return valorHeuristica;
    }
    
    
    public void setValorHeuristica(int valorHeuristica)
    {
        this.valorHeuristica = valorHeuristica;
    }
    
    
    public Set<Nodo> getSucesores()
    {
        return sucesores;
    }
    
    
    public void addSucesor(Nodo nodo)
    {
        sucesores.add(nodo);
    }
    
    
    /**
     * Metodo para calcular el valor de la funcion de evaluacion. Dicha fucion devuelve
     * la suma del coste para llegar hasta este nodo (hasta la posicion actual) mas el
     * coste estimado por la heuristica para llegar hasta la poscion meta.
     * @return Suma del coste mas el valor de la heuristica.
     */
    public int f()
    {
        return coste + valorHeuristica;
    }
    
    
    /*
     * TODO Este metodo public boolean nodoExplorado() y public void marcarComoExplorado() si al final no son necesarios en 
     * la busqueda en profundidad entonces se pueden borrar. Y por tanto tambien hay que borrar el atributo nodoExplorado.
     */
    public boolean nodoExplorado()
    {
        return nodoExplorado;
    }
    
    
    public void marcarComoExplorado()
    {
        nodoExplorado = true;
    }   
    
    
    public String estado()
    {
        return "[(" + posicion[0] + "," + posicion[1] + ") " + orientacion.getAbrebiatura() + "]";
    }
    
    
    @Override
    public String toString()
    {
        return "(" + estado() + "," + coste + "," + valorHeuristica + ")";
    }
    
    
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Nodo))
            return false;
        
        Nodo nodo = (Nodo) o;
        
        return nodo.getPosicion()[0] == posicion[0] && 
               nodo.getPosicion()[1] == posicion[1] &&
               nodo.getOrientacion() == orientacion;
    }


    @Override
    public int hashCode()
    {
        int hash = 7;
        hash += 41 * hash + Arrays.hashCode(this.posicion);
        hash += 41 * hash + (this.orientacion != null ? this.orientacion.hashCode() : 0);
        return hash;
    }
}
