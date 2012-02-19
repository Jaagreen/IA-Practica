/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

package practicaIA;

/**
 * Clase que implementa el estado y el comportamiento del viajero (de Abdel).
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public class Viajero
{
    private static final int inicioPorDefecto[] = {0,4};
    private static final int metaPorDefecto[] = {5,5};
    private static final Orientacion orientacionPorDefecto = Orientacion.SUR;
    
    private int posInicio[];
    private int posMeta[];
    private int posicion[];
    private Orientacion orientacion;
    private int gradosGiros;
    private Mapa mapa;
    
    public Viajero(Mapa mapa, int gradosGiros, boolean aleatorio)
    {
        if(gradosGiros != 90 && gradosGiros != 45)
            throw new IllegalArgumentException("Los giros tienen que ser de 90 o de 45 grados");
        
        this.gradosGiros = gradosGiros;
        this.mapa = mapa;
                
        //Comprobamos si se trata de una inicializacion aleatoria o por defecto.
        if(aleatorio)
        {
            //Generamos una posicion de inicio aleatoria.
            posInicio = new int[] {Funciones.obtenerNumeroAleatorio(0, mapa.getNumeroFilas()-1),
                                   Funciones.obtenerNumeroAleatorio(0, mapa.getNumeroColumnas()-1)};
            
            posicion = posInicio;
            
            //Generamos una posicion de meta aleatoria.
            do
            {
                posMeta = new int[] {Funciones.obtenerNumeroAleatorio(0, mapa.getNumeroFilas()-1),
                                     Funciones.obtenerNumeroAleatorio(0, mapa.getNumeroColumnas()-1)};
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
        }
        else
            cargarPosicionesPorDefecto();
        
        //Asignamos este viajero al mapa.
        mapa.asignarViajero(this);
    }
    
    
    public Viajero(Mapa mapa, int gradosGiros, int posInicio[], int posMeta[], Orientacion orientacion)
    {
        setGradosGiros(gradosGiros);               
        this.posInicio = posInicio;
        this.posicion = posInicio;
        this.posMeta = posMeta;
        this.orientacion = orientacion;
        this.mapa = mapa;

        //Asignamos este viajero al mapa.
        mapa.asignarViajero(this);
    }
    
    
    public final void cargarPosicionesPorDefecto()
    {
        posInicio = inicioPorDefecto;
        posicion = posInicio;
        posMeta = metaPorDefecto;
        orientacion = orientacionPorDefecto;
    }
    
    
    public int[] getPosInicio()
    {
        return posInicio;
    }
    
    
    public int[] getPosMeta()
    {
        return posMeta;
    }
    
    public void setPosInicio(int[] posicion)
    {        
        setPosicion(this.posInicio, posicion);
    }    
    
    public void setPosMeta(int[] posicion)
    {
        setPosicion(this.posMeta, posicion);
    }
    
    
    public int[] getPosicion()
    {
        return posicion;
    }
    
    public void setPosicion(int[] posicion)
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
    
    
    /**
     * Metodo que gira la orientacion del viajero en sentido horaro los grados
     * indicados por el atributo gradoGiros.
     * @return La nueva orientacion del viajero.
     */
    public Orientacion girarSentidoHorario()
    {
        if(gradosGiros == 90) //Si los giros son de 90 grados.
        {
            switch(orientacion)
            {
                case NORTE: 
                    orientacion = Orientacion.ESTE;
                    return orientacion;
                
                case ESTE: 
                    orientacion = Orientacion.SUR;
                    return orientacion;
                    
                case SUR: 
                    orientacion = Orientacion.OESTE;
                    return orientacion;

                case OESTE: 
                    orientacion = Orientacion.NORTE;
                    return orientacion;                
            } 
        }
        else //Si se trata de giros de 45 grados.
        {
            switch(orientacion)
            {
                case NORTE: 
                    orientacion = Orientacion.NORESTE;
                    return orientacion;
                    
                case NORESTE: 
                    orientacion = Orientacion.ESTE;
                    return orientacion;
                    
                case ESTE: 
                    orientacion = Orientacion.SURESTE;
                    return orientacion;
                    
                case SURESTE: 
                    orientacion = Orientacion.SUR;
                    return orientacion;
                    
                case SUR: 
                    orientacion = Orientacion.SUROESTE;
                    return orientacion;
                    
                case SUROESTE: 
                    orientacion = Orientacion.OESTE;
                    return orientacion;

                case OESTE: 
                    orientacion = Orientacion.NOROESTE;
                    return orientacion;
                
                case NOROESTE: 
                    orientacion = Orientacion.NORTE;
                    return orientacion;
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
        if(gradosGiros == 90) //Si los giros son de 90 grados.
        {
            switch(orientacion)
            {
                case NORTE: 
                    orientacion = Orientacion.OESTE;
                    return orientacion;
                
                case OESTE: 
                    orientacion = Orientacion.SUR;
                    return orientacion;
                    
                case SUR: 
                    orientacion = Orientacion.ESTE;
                    return orientacion;

                case ESTE: 
                    orientacion = Orientacion.NORTE;
                    return orientacion;                
            } 
        }
        else //Si se trata de giros de 45 grados.
        {
            switch(orientacion)
            {
                case NORTE: 
                    orientacion = Orientacion.NOROESTE;
                    return orientacion;
                    
                case NOROESTE: 
                    orientacion = Orientacion.OESTE;
                    return orientacion;
                    
                case OESTE: 
                    orientacion = Orientacion.SUROESTE;
                    return orientacion;
                    
                case SUROESTE: 
                    orientacion = Orientacion.SUR;
                    return orientacion;
                    
                case SUR: 
                    orientacion = Orientacion.SURESTE;
                    return orientacion;
                    
                case SURESTE: 
                    orientacion = Orientacion.ESTE;
                    return orientacion;

                case ESTE: 
                    orientacion = Orientacion.NORESTE;
                    return orientacion;
                
                case NORESTE: 
                    orientacion = Orientacion.NORTE;
                    return orientacion;
            }
        }
        
        return orientacion;
    }    
    
    /**
     * Metodo que avanza una casilla la posicion actual del viajero en la orientacion
     * indicada por el atributo orientacion.
     * @return La nueva posicion del viajero.
     */
    public int[] avanzar()
    {
        switch(orientacion)
        {
            case NORTE: 
                if(posicion[0] == 0)
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el norte ya que "
                                                        + "el viajero esta en el extremo norte del mapa");
                posicion[0] = posicion[0] - 1;
                return posicion;
                
            case NORESTE: 
                if(posicion[0] == 0)
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el norte ya que "
                                                        + "el viajero esta en el extremo norte del mapa");
                
                if(posicion[1] == (mapa.getNumeroColumnas()-1))
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el este ya que "
                                                        + "el viajero esta en el extremo este del mapa");
                posicion[0] = posicion[0] - 1;
                posicion[1] = posicion[1] + 1;
                return posicion;

            case ESTE:                                 
                if(posicion[1] == (mapa.getNumeroColumnas()-1))
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el este ya que "
                                                        + "el viajero esta en el extremo este del mapa");                
                posicion[1] = posicion[1] + 1;
                return posicion;

            case SURESTE: 
                if(posicion[0] == (mapa.getNumeroFilas()-1))
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el sur ya que "
                                                        + "el viajero esta en el extremo sur del mapa");
                
                if(posicion[1] == (mapa.getNumeroColumnas()-1))
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el este ya que "
                                                        + "el viajero esta en el extremo este del mapa");
                posicion[0] = posicion[0] + 1;
                posicion[1] = posicion[1] + 1;
                return posicion;

            case SUR: 
                if(posicion[0] == (mapa.getNumeroFilas()-1))
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el sur ya que "
                                                        + "el viajero esta en el extremo sur del mapa");
                posicion[0] = posicion[0] + 1;                
                return posicion;

            case SUROESTE: 
                if(posicion[0] == (mapa.getNumeroFilas()-1))
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el sur ya que "
                                                        + "el viajero esta en el extremo sur del mapa");
                
                if(posicion[1] == 0)
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el oeste ya que "
                                                        + "el viajero esta en el extremo oeste del mapa");
                posicion[0] = posicion[0] + 1;
                posicion[1] = posicion[1] - 1;
                return posicion;

            case OESTE:                 
                if(posicion[1] == 0)
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el oeste ya que "
                                                        + "el viajero esta en el extremo oeste del mapa");
                posicion[1] = posicion[1] - 1;
                return posicion;

            case NOROESTE: 
                if(posicion[0] == 0)
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el norte ya que "
                                                        + "el viajero esta en el extremo norte del mapa");
                
                if(posicion[1] == 0)
                    throw new IndexOutOfBoundsException("No se puede avanzar mas hacia el oeste ya que "
                                                        + "el viajero esta en el extremo oeste del mapa");
                posicion[0] = posicion[0] - 1;
                posicion[1] = posicion[1] - 1;
                return posicion;
        }   
        
        return posicion;
    }
}
