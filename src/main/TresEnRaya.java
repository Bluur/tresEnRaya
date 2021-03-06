
package main;

import funciones.funcionesTresEnRaya;

public class TresEnRaya {
    private int[][] tablero;
    
    //Constructores
    public TresEnRaya(){
        this.tablero = new int[3][3];
    }
    
    //Métodos
    public boolean colocarFicha(int ficha, int x, int y){
        if(this.tablero[x][y] == 0){
            this.tablero[x][y] = ficha;
            return true;
        }else{
            return false;
        }
    }
    
    public boolean hayGanador(){
        return funcionesTresEnRaya.hayGanador(tablero);
    }
    
    public boolean juegoTerminado(){
        return funcionesTresEnRaya.juegoTerminado(tablero);
    }
    
    public void imprimir(){
        funcionesTresEnRaya.mostrarTablero(tablero);
    }
}
