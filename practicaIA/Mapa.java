/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

package practicaIA;

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
    
    private static final int inicioPorDefecto[] = {0,4};
    private static final int metaPorDefecto[] = {5,5};
    
    private int mapa[][];
    private int inicio[];
    private int meta[];
    
    public Mapa()
    {
        cargarMapaPorDefecto();

    }
    
    public final void cargarMapaPorDefecto()
    {
        mapa = mapaPorDefecto;
        inicio = inicioPorDefecto;
        meta = metaPorDefecto;
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
                if(inicio[0] == fila && inicio[1] == col)
                    //Escribimos una A (de Abdel) en rojo y en negrita.
                    System.out.print("\033[1;31m A \033[0m");                    
                else if(meta[0] == fila && meta[1] == col)
                    //Escribimos una G (de Gertrudis) en rojo y en negrita.
                    System.out.print("\033[1;31m G \033[0m");
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
