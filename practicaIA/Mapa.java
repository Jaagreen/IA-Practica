/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

package practicaIA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Clase principal. Esta es la clase que hay que ejecutar para lanzar el programa.
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public class Mapa 
{
    private static final int mapaPorDefecto[][] = 
                                                {
                                                    {1,2,2,1,1,3,2,1},
                                                    {3,2,5,1,2,4,2,1},
                                                    {2,3,5,3,3,2,4,4},
                                                    {1,3,1,1,4,1,1,2},
                                                    {3,5,1,2,4,4,1,4},
                                                    {1,3,1,1,4,1,1,1}
                                                };
    private int mapa[][];
    private Viajero viajero;
    
    
    public Mapa()
    {       
        cargarMapaPorDefecto();
    }
    
    
    public Mapa(String rutaFichero) throws IOException, NumberFormatException
    {
        FileReader ficheroMapa = new FileReader(rutaFichero); 
        BufferedReader buff = new BufferedReader(ficheroMapa);        
        LinkedList<String> filas = new LinkedList<String>();
        String linea;
        
        //Leemos cada fila de las filas almacenadas en el fichero.
        while((linea = buff.readLine()) != null)
        {
            //Si la linea esta vacia nos la saltamos.
            if(linea.isEmpty() || Funciones.esCadenaEnBlanco(linea))
                continue;

            //Anadimos la fila a la lista de filas.
            filas.add(linea);
        }

        if(filas.isEmpty())
            throw new NumberFormatException("El fichero " + rutaFichero + " no contiene un mapa");
        
        StringTokenizer st = new StringTokenizer(filas.getFirst());
        
        //Creamos el mapa con las dimensiones indicadas en el fichero.
        mapa = new int[filas.size()][st.countTokens()];        

        
        //Inicializamos el mapa con los valores leidos.
        //Recorremos cada fila del mapa.
        for(int i = 0; i < filas.size(); i++)
        {            
            st = new StringTokenizer(filas.get(i));
            
            //Comprobamos que la fila tenta la longitud correcta.
            if(st.countTokens() != mapa[i].length)
                throw new NumberFormatException("El mapa indicado en el fichero " + rutaFichero + 
                                                " no es correcto: las filas tienen distinta longitud");
            
            //Obtenemos el valor de cada columna de la fila actual e inicializamos el
            //mapa con dicho valor.
            for(int j = 0; j < mapa[i].length; j++)
            {                
                mapa[i][j] = Integer.parseInt(st.nextToken());
                
                //Comprobamos que el valor leido es correcto.
                if(mapa[i][j] < 1)
                    throw new NumberFormatException("El mapa indicado en el fichero " + rutaFichero + 
                                                    " no es correcto: existen posiciones con valores menores que 1");
                
                if(mapa[i][j] > 5)
                    throw new NumberFormatException("El mapa indicado en el fichero " + rutaFichero + 
                                                    " no es correcto: existen posiciones con valores mayores que 5");
            }
        }

        //Cerramos los flujos de entrada
        buff.close();
        ficheroMapa.close();
    }
    
    
    
    /**
     * Constructor de la clase mapa la cual crea un mapa aleatorio en funcion de los parametros recibidos.
     * @param numeroFilas Numero de filas del mapa aleatorio.
     * @param numeroColumnas Numero de columnas del mapa aleatorio.
     * @param numeroObstaculos Numero de ostaculos del mapa aleatorio.
     * @param viajero Persona que viajara por el mapa aleatorio.
     */
    public Mapa(int numeroFilas, int numeroColumnas, int numeroObstaculos) throws IllegalArgumentException
    {
        if(numeroFilas <= 0)
            throw new IllegalArgumentException("El numero de filas tiene que ser mayor que 0");
        
        if(numeroColumnas <= 0)
            throw new IllegalArgumentException("El numero de columnas tiene que ser mayor que 0");
        
        if(numeroObstaculos <= 0)
            throw new IllegalArgumentException("El numero de ostaculos tiene que ser mayor o igual que 0");
        
        if(numeroObstaculos > numeroFilas*numeroColumnas)
            throw new IllegalArgumentException("El numero de ostaculos es mayor que el numero de posiciones del mapa");
        
        mapa = new int[numeroFilas][numeroColumnas];
        
        
        //Inicializamos el mapa al coste minimo
        for(int i = 0; i < numeroFilas; i++)
            for(int j = 0; j < numeroColumnas; j++)
                mapa[i][j] = 1;
        
        
        //Anadimos los obstaculos al mapa.
        int i = 0;
        while(i < numeroObstaculos)
        {
            //Seleccionamos aleatoriamente una fila y una columna para situar el obstaculo.
            int fila = Funciones.obtenerNumeroAleatorio(0, numeroFilas-1);
            int columna = Funciones.obtenerNumeroAleatorio(0, numeroColumnas-1);
            
            //Si en la posicion seleccioada ya se habia asignado un ostaculo, entonces
            //generamos otra posicon aleatoria en la que situar el ostaculo.
            if(mapa[fila][columna] != 1)
                continue;
            
            //Situamos el obstaculo.
            mapa[fila][columna] = Funciones.obtenerNumeroAleatorio(2, 5);
            i++;
        }
    }   
    
    
    public final void cargarMapaPorDefecto()
    {
        mapa = mapaPorDefecto;
        
        if(viajero != null)
            viajero.cargarPosicionesPorDefecto();
    }
    
    
    public void asignarViajero(Viajero viajero)
    {
        //Anadimos el viajero al mapa.
        this.viajero = viajero;        
    }
    
    
    public int getNumeroFilas()
    {
        return mapa.length;
    }
    
    
    public int getNumeroColumnas()
    {
        return mapa[0].length;
    }   
    
    
    public Viajero getViajero()
    {
        return viajero;
    }
    
    
    public int getDificultadPosicion(int fila, int columna)
    {
        return mapa[fila][columna];
    }    
    
    
    /**
     * Metodo que muestra por pantalla el estado actual del tablero.
     */
    public void mostrar()
    {
        for(int fila = 0; fila < mapa.length; fila++)
        {
            for(int col = 0; col < mapa[fila].length; col++)
            {
                //Indica que apartir de ahi el texto va a ser en azul y en negrita. 
                System.out.print("\033[1;34m----");                
            }
            System.out.println("-");
            
            //Escribimos un caracter "|" y luego restablecemos las propiedades normales del texto.
            System.out.print("|\033[0m");
            
            for(int col = 0; col < mapa[fila].length; col++)
            {
                if(viajero != null && viajero.getPosInicio()[0] == fila && viajero.getPosInicio()[1] == col)
                {
                    //Escribimos una A (de Abdel) en rojo y en negrita.
                    System.out.print("\033[1;31m A \033[0m");                    
                }
                else if(viajero != null && viajero.getPosMeta()[0] == fila && viajero.getPosMeta()[1] == col)
                {
                    //Escribimos una G (de Gertrudis) en rojo y en negrita.
                    System.out.print("\033[1;31m G \033[0m");
                }
                else
                    System.out.print(" " + mapa[fila][col] + " ");
            
                //Escribimos un caracter "|" en azul y negrita.
                System.out.print("\033[1;34m|\033[0m"); 
            }
            
            System.out.println();
        }
        
        for(int col = 0; col < mapa[0].length; col++)
        {
            System.out.print("\033[1;34m----"); //Escribimos --- en azul y negrita
        }
        //Escribimos un - y luego restablecemos las propiedades normales del texto.        
        System.out.println("-\033[0m");        
    } 
}
