package main;

import Funciones.leerDatosTeclado;

public class Programa {

    public static void main(String[] args) {
        boolean volverJugar = false;
        do{
            System.out.println("Bienvenidos al juego del 3 en raya");
            
            //Crea tablero y lo saca por pantalla
            int tablero [][] = new int[3][3];
            System.out.println("Este es vuestro tablero");
            Programa.mostrarTablero(tablero);

            //Variables booleanas para chequear estados de la partida
            boolean colocar;
            boolean ganador = false;
            
            //Asigna un nombre a cada jugador y un valor numérico
            String j1 = leerDatosTeclado.leerString("Dame el nombre del jugador 1: ");
            String j2 = leerDatosTeclado.leerString("Dame le nombre del jugador 2: ");
            final int jugador1 = 1;
            final int jugador2 = 2;
            int turnoActual = 0;
            
            //Selecciona jugador
            int jugadorInicial = (int) Math.floor(Math.random()*(2-1+1)+1);

            //Mensaje de salida avisando de a quién le ha tocado iniciar la partida
            if(jugadorInicial==jugador1){
                System.out.println("El jugador que empezará la partida es: "+j1);
                turnoActual = jugador1;
            }else if(jugadorInicial==jugador2){
                System.out.println("El jugador que empezará la partida es: "+j2);
                turnoActual = jugador2;
            }

            //Mientras no haya ganador y no se ha terminado el juego se ejecutará el juego
            while(!ganador && !Programa.juegoTerminado(tablero)){
                do{  
                    //Coje las posiciones del jugador
                    int y = leerDatosTeclado.LeerEntero("Dame la columna en la que quieres colocar", 1, 3);
                    y--;
                    int x = leerDatosTeclado.LeerEntero("Dame la fila en la que quieres colocar", 1, 3);
                    x--;
                    
                    //Comprueba si la posición es válida y coloca la pieza, en cada caso negativo saca un mensaje avisando
                    colocar = Programa.colocarFicha(tablero, turnoActual, x, y);
                    
                    //Muestra el tablero
                    Programa.mostrarTablero(tablero);
                    
                }while(colocar != true);
                
                //Cambia de turno comprobando antes si hay un ganador
                ganador = Programa.hayGanador(tablero);
                if(ganador){
                    if(turnoActual ==1){
                        System.out.println("Ha ganado "+j1);
                    }else{
                        System.out.println("Ha ganado "+j2);
                    }
                }else{
                    if(turnoActual==1){
                        turnoActual=2;
                        System.out.println("Es el turno de "+j2);
                    }
                    else{
                        turnoActual=1;
                        System.out.println("Es el turno de "+j1);
                    }
                }
            }
            volverJugar = seguirJugando(volverJugar);
        }while(volverJugar);
    }
    /*--------------------------------------- Funciones ------------------------------------------------------*/
    
    public static void mostrarTablero(int tablero[][]){
        if(tablero.length!=3 && tablero[0].length!=3){
            throw new IllegalArgumentException("El tablero no es válido");
        }
        //Muestra el tablero con un bucle anidado y un switch
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                switch (tablero[i][j]) {
                    case 1 -> System.out.print("|O|");
                    case 2 -> System.out.print("|X|");
                    default -> System.out.print("| |");
                }
            }
            System.out.println("");
        }
    }
    public static boolean colocarFicha(int tablero[][],int ficha, int x, int y){
        if(tablero.length!=3 && tablero[0].length!=3){
            throw new IllegalArgumentException("El tablero no es válido");
        }
        if(ficha != 1 && ficha != 2){
            throw new IllegalArgumentException("Esa ficha no es válida");
        }
        
        //Comprueba que se pueda colocar la ficha, la coloca y saca un boolean
        boolean colocar;
        if(tablero[x][y] != 0){
            colocar = false;
            System.out.println("No se ha podido colocar tu ficha correctamente");
        }else{
            tablero[x][y] = ficha;
            colocar = true;
            System.out.println("Se ha colocado tu ficha correctamente");
        }
        return colocar;
    }
    public static boolean hayGanador(int tablero[][]){
        if(tablero.length!=3 && tablero[0].length!=3){
            throw new IllegalArgumentException("El tablero no es válido");
        }
        
        boolean horizontal, vertical, diagonal;
        //Horizontal
        horizontal = tresEnRayaHorizontal(tablero);
        
        //Vertical
        vertical = tresEnRayaVertical(tablero);
        
        //Diagonal
        diagonal = tresEnRayaDiagonal(tablero);
        
        return vertical || horizontal || diagonal;
    }
    public static boolean juegoTerminado(int [][] tablero){
        if(tablero.length!=3 && tablero[0].length!=3){
            throw new IllegalArgumentException("El tablero no es válido");
        }
        
        //Comprueba que queden espacios en blanco y devuelve un estado
        boolean espaciosBlancos = false;
        for(int i=0;i<3 && !espaciosBlancos;i++){
            for(int j=0;j<3 && !espaciosBlancos;j++){
                if(tablero[i][j]==0){
                    espaciosBlancos = true;
                }
            }
        }
        if(!espaciosBlancos){
            System.out.println("No quedan huecos en el tablero, hay un empate");
        }
        return !espaciosBlancos;
    }

    private static boolean tresEnRayaVertical(int[][]tablero) {
        if(tablero.length!=3 || tablero[0].length !=3){
            throw new IllegalArgumentException("Ese tablero no me sirve");
        }
        
        boolean vertical = false;
        for(int i=0;i<3 && !vertical;i++){
            if(tablero[0][i] == tablero[1][i] && tablero[0][i] == tablero[2][i] && tablero[0][i] != 0){
                vertical = true;
                System.out.println("Vertical");
            }
        }
        return vertical;
    }
    private static boolean tresEnRayaHorizontal(int[][]tablero) {
        if(tablero.length!=3 || tablero[0].length !=3){
            throw new IllegalArgumentException("Ese tablero no me sirve");
        }
        
        //Horizontal
        boolean horizontal = false;
        for(int i=0; i<3 && !horizontal; i++){
            if(tablero[i][0] == tablero[i][1] && tablero[i][0] == tablero[i][2] && tablero[i][0] != 0){
                horizontal = true;
                System.out.println("Horizontal");
            }
        }
        return horizontal;
    }
    private static boolean tresEnRayaDiagonal(int[][]tablero) {
        //Comprobación
        if(tablero.length!=3 || tablero[0].length !=3){
            throw new IllegalArgumentException("Ese tablero no me sirve");
        }
        
        boolean diagonal = false;
        //Diagonal ascendente
        if(tablero[2][0] == tablero[1][1] && tablero[2][0] == tablero[0][2] && tablero[2][0] != 0){
            diagonal = true;
            System.out.println("Diagonal");
        }
        //Diagonal descendente
        if(tablero[0][0] == tablero[1][1] && tablero [0][0] == tablero[2][2] && tablero[0][0] != 0){
            diagonal = true;
            System.out.println("Diagonal");
        }
        return diagonal;
    }

    private static boolean seguirJugando(boolean volverJugar) {
        //String + boolean para reinicar el programa
        String seguirJugando = leerDatosTeclado.leerString("¿Quieres volver a jugar? Y/N", "y", "n");
        if(seguirJugando.equals("y")){
            volverJugar = true;
        }else if(seguirJugando.equals("n")){
            volverJugar = false;
        }
        return volverJugar;
    }
}
