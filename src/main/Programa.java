package main;

import Funciones.leerDatosTeclado;
import funciones.TresEnRaya;

public class Programa {

    public static void main(String[] args) {
        boolean volverJugar = false;
        do{
            System.out.println("Bienvenidos al juego del 3 en raya");
            
            //Crea tablero y lo saca por pantalla
            int tablero [][] = new int[3][3];
            System.out.println("Este es vuestro tablero");
            TresEnRaya.mostrarTablero(tablero);

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
            while(!ganador && !TresEnRaya.juegoTerminado(tablero)){
                do{  
                    //Coje las posiciones del jugador
                    int y = leerDatosTeclado.LeerEntero("Dame la columna en la que quieres colocar", 1, 3);
                    y--;
                    int x = leerDatosTeclado.LeerEntero("Dame la fila en la que quieres colocar", 1, 3);
                    x--;
                    
                    //Comprueba si la posición es válida y coloca la pieza, en cada caso negativo saca un mensaje avisando
                    colocar = TresEnRaya.colocarFicha(tablero, turnoActual, x, y);
                    
                    //Muestra el tablero
                    TresEnRaya.mostrarTablero(tablero);
                    
                }while(colocar != true);
                
                //Cambia de turno comprobando antes si hay un ganador
                ganador = TresEnRaya.hayGanador(tablero);
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
            volverJugar = TresEnRaya.seguirJugando(volverJugar);
        }while(volverJugar);
    }

}
