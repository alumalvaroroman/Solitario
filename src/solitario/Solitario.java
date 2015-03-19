package solitario;

import java.io.BufferedReader;
import java.util.ArrayList;


public class Solitario {
    
    private final int FILA = 7;
    private final int COLUMNA = 7;
    
    private char[][] tablero;
    
    //private String[] patron;
    
    private int origenX;
    private int origenY;
    private int destinoX;
    private int destinoY;
        
    private ArrayList<Movimientos> listaMovimientos = new ArrayList();
    
    BufferedReader br;

    public Solitario() {
        tablero = new char[FILA][COLUMNA];
        
    }

   public void setCoordenadas(int origenX, int origenY, int destinoX, int destinoY){
       this.origenX = origenX;
       this.origenY = origenY;
       this.destinoX = destinoX;
       this.destinoY = destinoY;
   }
   
    
   public void rellenarTablero(){
        for (int i = 0; i < FILA; i++) {
            for (int j = 0; j < COLUMNA; j++) {
                if (i < 2 || i > 4) {
                    switch (j) {
                        case 0:
                        case 1:
                        case 5:
                        case 6:
                            tablero[i][j] = ' ';
                            break;
                        case 2:
                            tablero[i][j] = '#';
                            break;
                        case 3:
                            tablero[i][j] = '#';
                            break;
                        case 4:
                            tablero[i][j] = '#';
                            break;
                    }
                } else {
                    if (i == 2 || i == 4) {
                        tablero[i][j] = '#';
                    } else{
                        if (j == 3) {
                            tablero[i][j] = '.';
                        } else{
                            tablero[i][j] = '#';
                        }
                    }
                }
            }
        }
    }
   
    
    public String pintarTablero(){
        String texto = "";
        for (int y = 0; y < FILA; y++) {
            for (int x = 0; x < COLUMNA; x++) {
                texto += tablero[x][y];
            }
            texto += "\n";
        }
        return texto;
    }
    
    public boolean esCorrecto(){
        boolean correcto = false;
            if (origenY == destinoY) {
                if (origenX > destinoX) {
                   if (tablero[origenX][origenY] == '#' && tablero[origenX-1][origenY] == '#' && tablero[destinoX][destinoY] == '.') {
                        correcto = true;
                   } 
                }
                if(origenX < destinoX){
                    if (tablero[origenX][origenY] == '#' && tablero[origenX+1][origenY] == '#' && tablero[destinoX][destinoY] == '.') {
                        correcto = true;
                    }
                }   
            }
            if (origenX == destinoX) {
                if (origenY > destinoY) {
                   if (tablero[origenX][origenY] == '#' && tablero[origenX][origenY-1] == '#' && tablero[destinoX][destinoY] == '.') {
                        correcto = true;
                   } 
                }
                if(origenY < destinoY){
                    if (tablero[origenX][origenY] == '#' && tablero[origenX][origenY+1] == '#' && tablero[destinoX][destinoY] == '.') {
                        correcto = true;
                    }
                }   
            }
        return correcto;
    }
    
    public void realizarMovimiento(){
        char datoOrigen = tablero[origenX][origenY];
        char datoFinal = tablero[destinoX][destinoY];
        tablero[destinoX][destinoY] = datoOrigen;
        tablero[origenX][origenY] = datoFinal;
        // Movimiento horizontal
        if (origenY == destinoY) {
            if (origenX > destinoX) {
                tablero[destinoX+1][destinoY] = datoFinal;
            } else{
                tablero[destinoX-1][destinoY] = datoFinal;
            }   
        }  
        //Movimiento vertical
        if(origenX == destinoX){
            if (origenY > destinoY) {
                tablero[destinoX][destinoY+1] = datoFinal;
            } else{
                tablero[destinoX][destinoY-1] = datoFinal;
            }
        }
        
        Movimientos movimientos = new Movimientos(origenX,origenY,destinoX,destinoY);
        listaMovimientos.add(movimientos);
    }
    
    public void deshacerMovimiento(){
        this.origenX = listaMovimientos.get(listaMovimientos.size()-1).getOrigenX();
        this.origenY = listaMovimientos.get(listaMovimientos.size()-1).getOrigenY();
        this.destinoX = listaMovimientos.get(listaMovimientos.size()-1).getDestinoX();
        this.destinoY = listaMovimientos.get(listaMovimientos.size()-1).getDestinoY();

        // Movimiento horizontal
        if (origenY == destinoY) {
            if (origenX > destinoX) {
                tablero[destinoX+1][destinoY] = '#';
                tablero[destinoX][destinoY] = '.';
                tablero[origenX][origenY] = '#';
            } else{
                tablero[destinoX-1][destinoY] = '#';
                tablero[destinoX][destinoY] = '.';
                tablero[origenX][origenY] = '#';
                
            }   
        }  
        //Movimiento vertical
        if(origenX == destinoX){
            if (origenY > destinoY) {
                tablero[destinoX][destinoY+1] = '#';
                tablero[destinoX][destinoY] = '.';
                tablero[origenX][origenY] = '#';
            } else{
                tablero[destinoX][destinoY-1] = '#';
                tablero[destinoX][destinoY] = '.';
                tablero[origenX][origenY] = '#';
            }
        }
        if(!listaMovimientos.isEmpty()){
            listaMovimientos.remove(listaMovimientos.size() - 1);
        }
    }
}
