/**************************************************************/
/*   PRACTICA IA: Resolucion de problemas de busqueda.        */
/*   Curso: 2011-2012                                         */
/*   Autores: Adrian Carpente Recouso                         */
/*            Juan Rodrigo Cantorna Berdullas                 */
/**************************************************************/

/*
 * Modo automatico:
 *     Compilacion: $ make
 *     Ejecucion:   $ make run
 * 
 * Modo manual:
 *     Compilacion: $ cd ruta_del_codigo_de_la_practica
 *                  $ javac practicaIA/Main.java
 * 
 *     Ejecucion:   $ java practicaIA/Main
 * 
 * Ejecucion de la compilacion empaquetada en Practica_IA.jar:
 *     $ java -jar Practica_IA.jar
 */

package practicaIA;


import java.io.IOException;
import static practicaIA.Funciones.*;


/**
 * Clase principal. Esta es la clase que hay que ejecutar para lanzar el programa.
 * @author Adrian Carpente Recouso
 * @author Juan Rodrigo Cantorna Berdullas
 */
public class Main 
{
    private static Mapa mapa = null;
    private static Viajero viajero = null;
    private static int gradosGiros = 0;
    private static Orientacion orientacion = null;
    private static EstrategiaBusqueda estrategiaBusqueda = null;
       
    /**
     * Metodo que muestra un mensaje de bienvenida y de explicacion de la practica.
     */
    private static void introduccion()
    {
        System.out.println("\033[1;1H\033[2J\n");  //Limpia la pantalla
        System.out.println("\033[1;37;46m\033[2K  ==============================================================\033[0m");  
        System.out.println("\033[1;37;46m\033[2K  |     PRACTICA 1 IA: Resolucion de problemas de busqueda     |\033[0m");  
        System.out.println("\033[1;37;46m\033[2K  ==============================================================\n\033[0m");  
        
        System.out.println("Se desea obtener la mejor ruta para realizar un viaje a un cierto lugar a traves de "
                         + "un terreno con diversas dificultades. Para ello se dispone de un mapa del terreno "
                         + "donde se representa el origen, el destino y la dificultad asociada para atravesar los "
                         + "distintos puntos posibles, pudiendo asociarse ademas un coste a los cambios de direccion "
                         + "en el camino. El objetivo consiste en utilizar la inteligencia artificial para tratar "
                         + "de obtener una solucion a este planteamiento, formulandolo como un problema de busqueda. "
                         + "Para ello se implementan distintos algoritmos de busqueda (ciega e informada).");

        pausa();
    }
    

    private static void menu()
    {
        int opcion = -1;
        
        while(opcion != 0)
        {                        
            mostrarMapaYdatos();
            
            System.out.print("\nSeleccione una opcion:\n"
                            + "   1 - Cargar mapa.\n"
                            + "   2 - Tipos de giros.\n"
                            + "   3 - Orientacion.\n"        
                            + "   4 - Posiciones.\n"
                            + "   5 - Metodo de busqueda.\n"
                            + "   6 - Iniciar busqueda.\n"
                            + "   7 - Iniciar busqueda iteracion a iteracion.\n"
                            + "   0 - Salir.\n\n"

                            + "Opcion: ");
            try
            {
                opcion = Integer.parseInt(readLn());
            }
            catch(IOException e)
            {
                System.out.println("Error leyendo la opcion tecleada.");
                pausa();
                continue;
                
            }
            catch(NumberFormatException e)
            {
                System.out.println("La opcion seleccionada no es correcta");
                pausa();
                continue;
            }
            
            if(opcion < 0 || opcion > 7)
            {
                System.out.println("La opcion seleccionada no es correcta");
                pausa();
                continue;
            }
            
            switch(opcion)
            {
                case 1: menuCargarMapa();
                        break;
                    
                case 2: menuAsignarTipoGiros();
                        break;
                    
                case 3: menuAsignarOrientacion();
                        break;
                    
                case 4: menuAsignarPosicionesViaje();
                        break;
                    
                case 5: menuAsignarEstrategiaBusqueda();
                        break;
                    
                case 6: if(estrategiaBusqueda == null)
                        {
                            System.out.println("\nTiene que indicar una estrategia de busqueda antes de comenzar la busqueda.");
                            pausa();
                            return;
                        }
                    
                        estrategiaBusqueda.resetear(mapa);
                        estrategiaBusqueda.buscar();  //TODO IMPLEMENTAR UN MENU Y CONTROLES DE EJECUCION
                        break;
                    
                case 7: if(estrategiaBusqueda == null)
                        {
                            System.out.println("\nTiene que indicar una estrategia de busqueda antes de comenzar la busqueda.");
                            pausa();
                            return;
                        }
                    
                        estrategiaBusqueda.resetear(mapa);
                        estrategiaBusqueda.buscarIteraionAiteracion();  //TODO IMPLEMENTAR UN MENU Y CONTROLES DE EJECUCION
                        break;
            }
        }                
    }
    
    
    private static void menuCargarMapa()
    {        
        int opcion = -1;
        
        while(opcion < 0 || opcion > 3)
        {                                
            mostrarMapaYdatos();
            
            System.out.print("\nSeleccione una opcion:\n"
                            + "   1 - Cargar mapa por defecto.\n"
                            + "   2 - Cargar mapa aleatorio.\n"
                            + "   3 - Cargar mapa desde fichero.\n"                            
                            + "   0 - Atras.\n\n"

                            + "Opcion: ");
            try
            {
                opcion = Integer.parseInt(readLn());
            }
            catch(IOException e)
            {
                System.out.println("Error leyendo la opcion tecleada.");
                pausa();
                continue;
                
            }
            catch(NumberFormatException e)
            {
                System.out.println("La opcion seleccionada no es correcta");
                pausa();
                continue;
            }
            
            if(opcion < 0 || opcion > 3)
            {
                System.out.println("La opcion seleccionada no es correcta");
                pausa();
                continue;
            }
            
            switch(opcion)
            {
                case 1: mapa = new Mapa();
                        break;
                    
                case 2: menuCargarMapaAleatorio();
                        break;
                    
                case 3: menuCargarMapaDesdeFichero();
                        break;                    
            }
        }
        
        //Al cargar un nuevo mapa reseteamos todo.
        if(opcion > 0 && opcion < 3)
        {
            viajero = null;
            gradosGiros = 0;
            orientacion = null;
        }
    }
    
    
    private static void menuCargarMapaAleatorio()
    {                
        int numFilas = 0, numColumnas = 0, numObstaculos = -1;
        
        //Solicitamos al usuario que indique el numero de filas.
        while(numFilas <= 0)
        {
            mostrarMapaYdatos();
            
            try
            {
                System.out.print("\nIndique el numero de filas.\n"
                               + "   Numero filas: ");
                
                numFilas = Integer.parseInt(readLn());
            }
            catch(IOException e)
            {
                System.out.println("Error leyendo el numero tecleado.");
                pausa();
                continue;

            }
            catch(NumberFormatException e)
            {
                System.out.println("El numero de filas indicado no es valido.");
                pausa();
                continue;
            }       
        }
        
        //Solicitamos al usuario que indique el numero de columnas.
        while(numColumnas <= 0)
        {
            mostrarMapaYdatos();
            
            try
            {
                System.out.print("\nIndique el numero de columnas.\n"
                               + "   Numero columnas: ");
                
                numColumnas = Integer.parseInt(readLn());
            }
            catch(IOException e)
            {
                System.out.println("Error leyendo el numero tecleado.");
                pausa();
                continue;

            }
            catch(NumberFormatException e)
            {
                System.out.println("El numero de columnas indicado no es valido.");
                pausa();
                continue;
            }       
        }
        
        //Solicitamos al usuario que indique el numero de obstaculos.
        while(numObstaculos < 0 || numObstaculos > numFilas*numColumnas)
        {        
            mostrarMapaYdatos();
            
            try
            {
                System.out.print("\nIndique el numero de obstaculos.\n"
                               + "   Numero obstaculos: ");
                
                numObstaculos = Integer.parseInt(readLn());
                
                //Comprobamos que la cantidad de obstaculos indicada no sea mayor que el numero de posicones del mapa.
                if(numObstaculos > numFilas*numColumnas)
                {
                    System.out.println("\nError: El numero de obstaculos es mayor que el numero de posiciones del mapa.");
                    pausa();
                }
            }
            catch(IOException e)
            {
                System.out.println("Error leyendo el numero tecleado.");
                pausa();
                continue;

            }
            catch(NumberFormatException e)
            {
                System.out.println("El numero de obstaculos indicado no es valido.");
                pausa();
                continue;
            }       
        }
        
        mapa = new Mapa(numFilas, numColumnas, numObstaculos);        
    }
    
    
    private static void menuCargarMapaDesdeFichero()
    {         
        mostrarMapaYdatos();                        

        try
        {
            System.out.print("\nIndique el nombre del fichero.\n"
                    + "   Nombre: ");

            mapa = new Mapa(readLn());
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e.getMessage());
            pausa();                

        }
        catch(NumberFormatException e)
        {
            System.out.println("Error: " + e.getMessage());
            pausa();                
        }       
    }
    
    
    private static void menuAsignarTipoGiros()
    {
        int opcion = -1;
        
        while(opcion < 0 || opcion > 2)
        {          
            mostrarMapaYdatos();
            
            System.out.print("\nSeleccione el tipo de giros:\n"
                            + "   1 - Giros de 90 grados.\n"
                            + "   2 - Giros de 45 grados.\n"                            
                            + "   0 - Atras.\n\n"

                            + "Opcion: ");
            try
            {
                opcion = Integer.parseInt(readLn());
            }
            catch(IOException e)
            {
                System.out.println("Error leyendo la opcion tecleada.");
                pausa();
                continue;
                
            }
            catch(NumberFormatException e)
            {
                System.out.println("La opcion seleccionada no es correcta");
                pausa();
                continue;
            }
            
            if(opcion < 0 || opcion > 2)
            {
                System.out.println("La opcion seleccionada no es correcta");
                pausa();
                continue;
            }
            
            switch(opcion)
            {
                case 1: gradosGiros = 90;
                        break;
                    
                case 2: gradosGiros = 45;
                        break;
            }
            
            if(viajero != null)
                viajero.setGradosGiros(gradosGiros);
        }
                
    }
    
    
    private static void menuAsignarOrientacion()
    {   
        int opcion = -1;
        
        while((opcion < 0 || opcion > 8) || (gradosGiros == 90 && opcion > 4))
        {           
            mostrarMapaYdatos();
            
            if(gradosGiros == 90)
            {
                System.out.print("\nSeleccione una orientacion:\n"
                               + "   1 - Norte.\n"
                               + "   2 - Este.\n"
                               + "   3 - Sur.\n"        
                               + "   4 - Oeste.\n"                            
                               + "   0 - Atras.\n\n"

                               + "Opcion: ");
            }
            else if(gradosGiros == 45)
            {
                System.out.print("\nSeleccione una orientacion:\n"
                               + "   1 - Norte.\n"
                               + "   2 - Este.\n"
                               + "   3 - Sur.\n"        
                               + "   4 - Oeste.\n"
                               + "   5 - Noreste.\n"
                               + "   6 - Sureste.\n"
                               + "   7 - Suroeste.\n"        
                               + "   8 - Noroeste.\n"
                               + "   0 - Atras.\n\n"

                               + "Opcion: ");
            }
            else
            {
                System.out.println("\nTiene que indicar el tipo de giros antes de asignar la orientacion.");
                pausa();
                return;
            }
            
            
            try
            {
                opcion = Integer.parseInt(readLn());
            }
            catch(IOException e)
            {
                System.out.println("Error leyendo la opcion tecleada.");
                pausa();
                continue;
                
            }
            catch(NumberFormatException e)
            {
                System.out.println("La opcion seleccionada no es correcta");
                pausa();
                continue;
            }
            
            if((opcion < 0 || opcion > 8) || (gradosGiros == 90 && opcion > 4))
            {
                System.out.println("La opcion seleccionada no es correcta");
                pausa();
                continue;
            }
            
            switch(opcion)
            {
                case 1: orientacion = Orientacion.NORTE;
                        break;
                    
                case 2: orientacion = Orientacion.ESTE;
                        break;
                    
                case 3: orientacion = Orientacion.SUR;
                        break;
                    
                case 4: orientacion = Orientacion.OESTE;
                        break;
                    
                case 5: orientacion = Orientacion.NORESTE;
                        break;
                    
                case 6: orientacion = Orientacion.SURESTE;
                        break;
                    
                case 7: orientacion = Orientacion.SUROESTE;
                        break;
                    
                case 8: orientacion = Orientacion.NOROESTE;
                        break;    
            }
            
            if(viajero != null) 
                viajero.setOrientacion(orientacion);
        }                
    }
    
    
    private static void menuAsignarPosicionesViaje()
    {
        int opcion = -1;
        int posicionIncio[] = null;
        int posicionMeta[] = null;
        
        while(opcion != 0)
        {                        
            limpiarPantalla();
        
            if(mapa == null)
            {
                System.out.println("\nTiene que cargar un mapa antes de asignar las posiciones.");
                pausa();
                return;
            }
            else
                mostrarMapaYdatos();
            
            if(gradosGiros == 0)
            {
                System.out.println("\nTiene que indicar el tipo de giros antes de asignar las posiciones.");
                pausa();
                return;
            }
            
            if(orientacion == null)
            {
                System.out.println("\nTiene que indicar la orientacion antes de asignar las posiciones.");
                pausa();
                return;
            }

            
            System.out.print("\nSeleccione una opcion:\n"
                            + "   1 - Posiciones por defecto.\n"
                            + "   2 - Posiciones aleatorias.\n"
                            + "   3 - Asignar la posicion de inicio.\n"
                            + "   4 - Asignar la posicion de meta.\n"
                            + "   0 - Atras.\n\n"

                            + "Opcion: ");
            try
            {
                opcion = Integer.parseInt(readLn());
            }
            catch(IOException e)
            {
                System.out.println("Error leyendo la opcion tecleada.");
                pausa();
                continue;
                
            }
            catch(NumberFormatException e)
            {
                System.out.println("La opcion seleccionada no es correcta");
                pausa();
                continue;
            }
            
            if(opcion < 0 || opcion > 4)
            {
                System.out.println("La opcion seleccionada no es correcta");
                pausa();
                continue;
            }
            
            switch(opcion)
            {
                case 1: viajero = new Viajero(mapa, gradosGiros, orientacion);                        
                        return;
                    
                case 2: viajero = new Viajero(mapa, gradosGiros);
                        return;
                    
                case 3: if(viajero != null)
                        {
                            viajero.setPosInicio(solicitarPosicion());
                            viajero.setPosicion(viajero.getPosInicio());
                            mapa.asignarViajero(viajero);
                            return;
                        }
                        else
                            posicionIncio = solicitarPosicion();
                
                        break;                    
                    
                case 4: if(viajero != null)
                        {
                            viajero.setPosMeta(solicitarPosicion());
                            mapa.asignarViajero(viajero);
                            return;
                        }
                        else
                            posicionMeta = solicitarPosicion();
                
                        break;
            }
            
            if(posicionIncio != null && posicionMeta != null)
            {
                viajero = new Viajero(mapa, gradosGiros, posicionIncio, posicionMeta, orientacion);
                return;
            }
        }
    }    
    
    
    private static int[] solicitarPosicion()
    {                
        int numFila = -1, numColumna = -1;
        
        //Solicitamos al usuario que indique el numero de filas.
        while(numFila < 0)
        {
            mostrarMapaYdatos();
            
            try
            {
                System.out.print("\nIndique el numero de la fila.\n"
                               + "   Numero fila: ");
                
                numFila = Integer.parseInt(readLn());
                
                if(numFila > (mapa.getNumeroFilas()-1))
                {
                    System.out.println("El numero de fila indicado es mayor que las filas del mapa");
                    continue;
                }
            }
            catch(IOException e)
            {
                System.out.println("Error leyendo el numero tecleado.");
                pausa();
                continue;

            }
            catch(NumberFormatException e)
            {
                System.out.println("El numero de fila indicado no es valido.");
                pausa();
                continue;
            }       
        }
        
        //Solicitamos al usuario que indique el numero de columnas.
        while(numColumna < 0)
        {    
            mostrarMapaYdatos();
            
            try
            {
                System.out.print("\nIndique el numero de la columna.\n"
                               + "   Numero columna: ");
                
                numColumna = Integer.parseInt(readLn());
                
                if(numColumna > (mapa.getNumeroColumnas()-1))
                {
                    System.out.println("El numero de columna indicado es mayor que las columnas del mapa");
                    continue;
                }
            }
            catch(IOException e)
            {
                System.out.println("Error leyendo el numero tecleado.");
                pausa();
                continue;

            }
            catch(NumberFormatException e)
            {
                System.out.println("El numero de columna indicado no es valido.");
                pausa();
                continue;
            }       
        }
        
        return new int[] {numFila, numColumna};
    }
    
   
    private static void menuAsignarEstrategiaBusqueda()
    {
        if(viajero == null)
        {
            System.out.println("\nTiene que indicar las posiciones antes de asignar una estrategia de busqueda.");
            pausa();
            return;
        }        
        
        int opcion = -1;
        
        while(opcion < 0 || opcion > 5)
        {                        
            mostrarMapaYdatos();
            //TODO NO TODAS LAS ESTRATEGIAS DE BUSQUEDA PUEDEN SER SELECCIONADAS. HAY QUE FILTRARLAS EN FUNCION DE SI SON GIROS DE 90 o DE 45
            System.out.print("\nSeleccione una opcion:\n"
                            + "   1 - Busqueda ciega en anchura.\n"
                            + "   2 - Busqueda informada A* - Manhatan (modificada).\n"
                            + "   3 - Busqueda informada A* - Maximo desplazamiento en filas o columas.\n"
                            + "   4 - Busqueda informada A* - ... .\n"                            
                            + "   0 - Atras.\n\n"

                            + "Opcion: ");
            try
            {
                opcion = Integer.parseInt(readLn());
            }
            catch(IOException e)
            {
                System.out.println("Error leyendo la opcion tecleada.");
                pausa();
                continue;
                
            }
            catch(NumberFormatException e)
            {
                System.out.println("La opcion seleccionada no es correcta");
                pausa();
                continue;
            }
            
            if(opcion < 0 || opcion > 5)
            {
                System.out.println("La opcion seleccionada no es correcta");
                pausa();
                continue;
            }
            
            switch(opcion)
            {
                case 1: estrategiaBusqueda = new BusquedaCiegaEnAnchura(mapa);
                        break;
                    
                case 2: estrategiaBusqueda = new BusquedaInformadaManhatan(mapa);
                        break;
                    
                case 3: estrategiaBusqueda = new BusquedaInformadaMaximoDesplazamiento(mapa);
                        break;
                    
                case 4: //TODO Anadir la ultima estrategia informada
                        break;
            }
        }                
    }
    
    
    private static void mostrarMapaYdatos()
    {
        limpiarPantalla();
        
        //Comprobamos si ya se ha cargado algun mapa.
        if(mapa != null)
        {
            //Mostramos el mapa.
            mapa.mostrar();            
            
            //Comprobamos que se haya asignado ya un tipo de giros. En caso afirmativo mostramos los datos.
            if(gradosGiros != 0)
            {
                System.out.print("\033[s"); //Guardamos la posicion del cursor.
                
                System.out.printf("\033[4;%dHGiros de: " + gradosGiros + " grados\n", 10+4*mapa.getNumeroColumnas());
                                
                if(estrategiaBusqueda != null)
                    System.out.printf("\033[%dGEstrategia: " + estrategiaBusqueda.getTipo() + "\n", 
                                      10+4*mapa.getNumeroColumnas());
                
                if(estrategiaBusqueda != null && !estrategiaBusqueda.getNombreHeuristica().equals(""))
                    System.out.printf("\033[%dGHeuristica: " + estrategiaBusqueda.getNombreHeuristica() + "\n", 
                                      10+4*mapa.getNumeroColumnas());
                
                if(viajero != null)
                {
                    System.out.printf("\033[%dGCoste posicion G: " + 
                                      mapa.getDificultadPosicion(viajero.getPosMeta()[0], 
                                                                 viajero.getPosMeta()[1]) + 
                                      "\n", 10+4*mapa.getNumeroColumnas());
                
                    System.out.printf("\033[%dGPosicion: (" + viajero.getPosicion()[0] + "," + 
                                      viajero.getPosicion()[1] + ")\n", 10+4*mapa.getNumeroColumnas());                
                    
                    System.out.printf("\033[%dGOrientacion: " + viajero.getOrientacion() + "\n",
                                      10+4*mapa.getNumeroColumnas());
                }
                    
                if(orientacion != null && viajero == null)
                    System.out.printf("\033[%dGOrientacion: " + orientacion + "\n", 10+4*mapa.getNumeroColumnas());
                
                System.out.print("\033[u"); //Restauramos la posicion del cursor.            
            }
        }
        
    }
    
    public static void main(String[] args) 
    {
        introduccion();
        
        menu();
    }
}
