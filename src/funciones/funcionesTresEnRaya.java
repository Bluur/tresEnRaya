
package funciones;

import Funciones.leerDatosTeclado;

public class funcionesTresEnRaya {
    
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

    public static boolean seguirJugando(boolean volverJugar) {
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
