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
 * Clase que implementa el estado y el comportamiento del viajero (de Abdel).
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public class Viajero
{
    private static final int inicioPorDefecto[] = {0,4};
    private static final int metaPorDefecto[] = {5,5};
    //private static final Orientacion orientacionPorDefecto = Orientacion.SUR; TODO Borrar esto
    
    private int posInicio[] = new int[2];
    private int posMeta[] = new int[2];
    private int posicion[] = new int[2];
    private Orientacion orientacion;
    private int gradosGiros;
    private Mapa mapa;
    
    /**
     * 
     * Crea un viajero en unas posiciones aleatorias dentro del mapa y con una orientacion aleatoria.
     * @param mapa Mapa sobre el que se va a situar el viajero.
     * @param gradosGiros Tipo de giros que realizara el viajero. (de 90 o de 45 grados)     
     */
    public Viajero(Mapa mapa, int gradosGiros)
    {
        if(gradosGiros != 90 && gradosGiros != 45)
            throw new IllegalArgumentException("Los giros tienen que ser de 90 o de 45 grados");
        
        this.gradosGiros = gradosGiros;
        this.mapa = mapa;
                
        //Generamos una posicion de inicio aleatoria.
        posInicio[0] = Funciones.obtenerNumeroAleatorio(0, mapa.getNumeroFilas()-1);
        posInicio[1] = Funciones.obtenerNumeroAleatorio(0, mapa.getNumeroColumnas()-1);

        posicion[0] = posInicio[0];
        posicion[1] = posInicio[1];

        //Generamos una posicion de meta aleatoria.
        do
        {
            posMeta[0] = Funciones.obtenerNumeroAleatorio(0, mapa.getNumeroFilas()-1);
            posMeta[1] = Funciones.obtenerNumeroAleatorio(0, mapa.getNumeroColumnas()-1);
        }
        //Si la posicion de inicio y de meta coinciden entonces generamos otra posicion de meta.
        while(posInicio[0] == posMeta[0] && posInicio[1] == posMeta[1]);

        //Asignamos una orientacion aleatoria para el viajero.
        if(gradosGiros == 90)
            //Con giros de 90 grados la orientacion puede ser Norte(0), Sur(1), Este(2), Oeste(3).
            orientacion = Orientacion.getOrientacionById(Funciones.obtenerNumeroAleatorio(0, 3));
        else if(gradosGiros == 45)        
            //Con giros de 45 grados la orientacion puede ser: 
            //Norte(0), Sur(1), Este(2), Oeste(3), Noreste(4), Noroeste(5), Sureste(6), Suroeste(7).
            orientacion = Orientacion.getOrientacionById(Funciones.obtenerNumeroAleatorio(0, Orientacion.values().length -1));
        
        //Asignamos este viajero al mapa.
        mapa.asignarViajero(this);        
        mapa.borrarPosicionesAccedidas();
    }
    
    
    /**
     * Crea un viajero con las posiciones por defecto.
     * @param mapa Mapa sobre el que se va a situar el viajero.
     * @param gradosGiros Tipo de giros que realizara el viajero. (de 90 o de 45 grados)
     * @param orientacion Orientacion del viajero en el mapa.
     */
    public Viajero(Mapa mapa, int gradosGiros, Orientacion orientacion)
    {
        if(gradosGiros != 90 && gradosGiros != 45)
            throw new IllegalArgumentException("Los giros tienen que ser de 90 o de 45 grados");
        
        this.gradosGiros = gradosGiros;
        this.mapa = mapa;
        this.orientacion = orientacion; 
        
        this.posicion[0] = inicioPorDefecto[0]; //Es necesario para poder asignar el viajero al mapa.
        this.posicion[1] = inicioPorDefecto[1]; 
        
        //Asignamos este viajero al mapa.
        mapa.asignarViajero(this);
        mapa.borrarPosicionesAccedidas();
        
        cargarPosicionesPorDefecto();        
    }
    
    
    public Viajero(Mapa mapa, int gradosGiros, int posInicio[], int posMeta[], Orientacion orientacion)
    {
        this.orientacion = orientacion;
        this.mapa = mapa;       
        setGradosGiros(gradosGiros);               
        setPosInicio(posInicio);
        setPosMeta(posMeta);
        setPosicion(posInicio);

        //Asignamos este viajero al mapa.
        mapa.asignarViajero(this);
        mapa.borrarPosicionesAccedidas();
    }
    
    
    public final void cargarPosicionesPorDefecto()
    {
        setPosInicio(inicioPorDefecto);
        setPosMeta(metaPorDefecto);
        setPosicion(inicioPorDefecto);        
        //orientacion = orientacionPorDefecto; TODO borrar esto
    }
    
    
    public int[] getPosInicio()
    {
        return posInicio;
    }
    
    
    public int[] getPosMeta()
    {
        return posMeta;
    }
    
    public final void setPosInicio(int[] posicion)
    {        
        setPosicion(this.posInicio, posicion);
        mapa.borrarPosicionesAccedidas();
    }    
    
    public final void setPosMeta(int[] posicion)
    {
        setPosicion(this.posMeta, posicion);
        mapa.borrarPosicionesAccedidas();
    }
    
    
    public int[] getPosicion()
    {
        return posicion;
    }
    
    public final void setPosicion(int[] posicion)
    {        
        setPosicion(this.posicion, posicion);
    }
    
    
    private void setPosicion(int[] posicion, int[] nuevaPosicion)
    {
        if(nuevaPosicion[0] < 0)
            throw new IllegalArgumentException("El numero de fila indicado tiene que ser mayor o igual a 0");
        
        if(nuevaPosicion[0] >= mapa.getNumeroFilas())
            throw new IllegalArgumentException("El numero de fila indicado es mayor que las filas del mapa");
        
        if(nuevaPosicion[1] < 0)
            throw new IllegalArgumentException("El numero de columna indicado tiene que ser mayor o igual a 0");
        
        if(nuevaPosicion[1] >= mapa.getNumeroColumnas())
            throw new IllegalArgumentException("El numero de columna indicado es mayor que las columnas del mapa");
        
        posicion[0] = nuevaPosicion[0];
        posicion[1] = nuevaPosicion[1];
    }
    
    
    public Orientacion getOrientacion()
    {
        return orientacion;
    }
    
    public void setOrientacion(Orientacion orientacion)
    {
        this.orientacion = orientacion;
    }
    
    
    public int getGradosGiros()
    {
        return gradosGiros;
    }
    
    
    public final void setGradosGiros(int gradosGiros)
    {
        if(gradosGiros != 90 && gradosGiros != 45)
            throw new IllegalArgumentException("Los giros tienen que ser de 90 o de 45 grados");        
        
        this.gradosGiros = gradosGiros;
    }
    
    
    public void setPosicionYorientacion(Nodo nodo)
    {
        setPosicion(nodo.getPosicion());
        this.orientacion = nodo.getOrientacion();
    }
    
    /**
     * Metodo que devuelve la orientacion del viajero si girase en sentido horario
     * los grados indicados por el atributo gradoGiros.
     * @return La orientacion despues del giro.
     */
    private Orientacion operadorGirarSentidoHorario()
    {
        if(gradosGiros == 90) //Si los giros son de 90 grados.
        {
            switch(orientacion)
            {
                case NORTE: 
                    return Orientacion.ESTE;
                
                case ESTE: 
                    return Orientacion.SUR;

                case SUR: 
                    return Orientacion.OESTE;
        
                case OESTE: 
                    return Orientacion.NORTE;                
            } 
        }
        else //Si se trata de giros de 45 grados.
        {
            switch(orientacion)
            {
                case NORTE: 
                    return Orientacion.NORESTE;

                case NORESTE: 
                    return Orientacion.ESTE;
 
                case ESTE: 
                    return Orientacion.SURESTE;
                  
                case SURESTE: 
                    return Orientacion.SUR;
                   
                case SUR: 
                    return Orientacion.SUROESTE;
                    
                case SUROESTE: 
                    return Orientacion.OESTE;
                   
                case OESTE: 
                    return Orientacion.NOROESTE;
                   
                case NOROESTE: 
                    return Orientacion.NORTE; 
            }
        }
        
        return orientacion;
    }    
    
    
    /**
     * Metodo que gira la orientacion del viajero en sentido horaro los grados
     * indicados por el atributo gradoGiros.
     * @return La nueva orientacion del viajero.
     */
    public Orientacion girarSentidoHorario()
    {
        orientacion = operadorGirarSentidoHorario();
        return orientacion;
    }

    
    /**
     * Metodo que devuelve la orientacion del viajero si girase en sentido antihorario
     * los grados indicados por el atributo gradoGiros.
     * @return La orientacion despues del giro.
     */
    private Orientacion operadorGirarSentidoAntiHorario()
    {
        if(gradosGiros == 90) //Si los giros son de 90 grados.
        {
            switch(orientacion)
            {
                case NORTE: 
                    return Orientacion.OESTE;
                
                case OESTE: 
                    return Orientacion.SUR;
                    
                case SUR: 
                    return Orientacion.ESTE;

                case ESTE: 
                    return Orientacion.NORTE;
            } 
        }
        else //Si se trata de giros de 45 grados.
        {
            switch(orientacion)
            {
                case NORTE: 
                    return Orientacion.NOROESTE;
                    
                case NOROESTE: 
                    return Orientacion.OESTE;
                    
                case OESTE: 
                    return Orientacion.SUROESTE;
                    
                case SUROESTE: 
                    return Orientacion.SUR;
                    
                case SUR: 
                    return Orientacion.SURESTE;
                    
                case SURESTE: 
                    return Orientacion.ESTE;

                case ESTE: 
                    return Orientacion.NORESTE;
                
                case NORESTE: 
                    return Orientacion.NORTE;
            }
        }
        
        return orientacion;
    }    
    
    
    /**
     * Metodo que gira la orientacion del viajero en sentido antihoraro los grados
     * indicados por el atributo gradoGiros.
     * @return La nueva orientacion del viajero.
     */
    public Orientacion girarSentidoAntiHorario()
    {
        orientacion = operadorGirarSentidoAntiHorario();
        return orientacion;
    }    
    

    /**
     * Metodo que devuelve la posicion a la que llegaria el viajero si
     * avanzase en la orientacion indicada por el atributo orientacion.
     * @return La nueva posicion del viajero.
     */
    private int[] operadorAvanzar()
    {
        switch(orientacion)
        {
            case NORTE: 
                if(posicion[0] == 0)
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el norte ya que "
                                                        + "el viajero esta en el extremo norte del mapa");
                return new int[] {posicion[0] - 1, posicion[1]};
                
            case NORESTE: 
                if(posicion[0] == 0)
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el norte ya que "
                                                        + "el viajero esta en el extremo norte del mapa");
                
                if(posicion[1] == (mapa.getNumeroColumnas()-1))
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el este ya que "
                                                        + "el viajero esta en el extremo este del mapa");
                return new int[] {posicion[0] - 1, posicion[1] + 1};

            case ESTE:                                 
                if(posicion[1] == (mapa.getNumeroColumnas()-1))
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el este ya que "
                                                        + "el viajero esta en el extremo este del mapa");                
                return new int[] {posicion[0], posicion[1] + 1};

            case SURESTE: 
                if(posicion[0] == (mapa.getNumeroFilas()-1))
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el sur ya que "
                                                        + "el viajero esta en el extremo sur del mapa");
                
                if(posicion[1] == (mapa.getNumeroColumnas()-1))
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el este ya que "
                                                        + "el viajero esta en el extremo este del mapa");
                return new int[] {posicion[0] + 1, posicion[1] + 1};

            case SUR: 
                if(posicion[0] == (mapa.getNumeroFilas()-1))
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el sur ya que "
                                                        + "el viajero esta en el extremo sur del mapa");          
                return new int[] {posicion[0] + 1, posicion[1]};

            case SUROESTE: 
                if(posicion[0] == (mapa.getNumeroFilas()-1))
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el sur ya que "
                                                        + "el viajero esta en el extremo sur del mapa");
                
                if(posicion[1] == 0)
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el oeste ya que "
                                                        + "el viajero esta en el extremo oeste del mapa");
                return new int[] {posicion[0] + 1, posicion[1] - 1};

            case OESTE:                 
                if(posicion[1] == 0)
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el oeste ya que "
                                                        + "el viajero esta en el extremo oeste del mapa");
                return new int[] {posicion[0], posicion[1] - 1};

            case NOROESTE: 
                if(posicion[0] == 0)
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el norte ya que "
                                                        + "el viajero esta en el extremo norte del mapa");
                
                if(posicion[1] == 0)
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el oeste ya que "
                                                        + "el viajero esta en el extremo oeste del mapa");
                return new int[] {posicion[0] - 1, posicion[1] - 1};
        }   
        
        return posicion;
    }    
    
    
    /**
     * Metodo que avanza una casilla la posicion actual del viajero en la orientacion
     * indicada por el atributo orientacion.
     * @return La nueva posicion del viajero.
     */
    public int[] avanzar()
    {
        posicion = operadorAvanzar();
        
        return posicion;
    }
    
    /**
     * Metodo que genera una lista con los nodos sucesores del nodo actual.
     * Este metodo solo inicializa la posicion y la orientacion de los nodos sucesores. 
     * La estrategia de busqueda que llame a este metodo se tiene que encargar de 
     * asignarle el nodo padre, el coste y el valor de la heuristica a cada uno de los
     * nodos sucesores.
     * @return La lista de nodos sucesores.
     */
    public Set<Nodo> getNodosSucesores()
    {
        Set<Nodo> sucesores = new HashSet<Nodo>();
        
        try
        {
            sucesores.add(new Nodo(operadorAvanzar(), orientacion));
        }
        catch(Exception e){ /*Si se intenta avanzar fuera del mapa ignoramos el nuevo nodo*/}
        
        
        sucesores.add(new Nodo(posicion, operadorGirarSentidoHorario()));
        
        sucesores.add(new Nodo(posicion, operadorGirarSentidoAntiHorario()));
        
        return sucesores;
    }
}
