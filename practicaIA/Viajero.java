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
    private Orientacion orientacion;
    private int gradosGiros;
    
    public Viajero(Mapa mapa, int gradosGiros, boolean aleatorio)
    {
        if(gradosGiros != 90 && gradosGiros != 45)
            throw new IllegalArgumentException("Los giros tienen que ser de 90 o de 45 grados");
        
        this.gradosGiros = gradosGiros;
        
        //Comprobamos si se trata de una inicializacion aleatoria o por defecto.
        if(aleatorio)
        {
            //Generamos una posicion de inicio aleatoria.
            posInicio = new int[] {Funciones.obtenerNumeroAleatorio(0, mapa.getNumeroFilas()-1),
                                   Funciones.obtenerNumeroAleatorio(0, mapa.getNumeroColumnas()-1)};
            
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
        this.posInicio = posInicio;
        this.posMeta = posMeta;
        this.orientacion = orientacion;
        
        if(gradosGiros != 90 && gradosGiros != 45)
            throw new IllegalArgumentException("Los giros tienen que ser de 90 o de 45 grados");

        this.gradosGiros = gradosGiros;
            
        //Asignamos este viajero al mapa.
        mapa.asignarViajero(this);
    }
    
    
    public final void cargarPosicionesPorDefecto()
    {
        posInicio = inicioPorDefecto;
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
    
    
    public Orientacion getOrientacion()
    {
        return orientacion;
    }
    
    
    public int getGradosGiros()
    {
        return gradosGiros;
    }
}
