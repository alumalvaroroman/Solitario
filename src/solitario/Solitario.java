package solitario;

import java.io.BufferedReader;


public class Solitario {
    
    private final int FILA = 7;
    private final int COLUMNA = 7;
    
    private char[][] tablero;
    
    private String[] patron;
    
    private int origenX;
    private int origenY;
    private int destinoX;
    private int destinoY;
    
    BufferedReader br;
    
    

    public Solitario() {
        tablero = new char[FILA][COLUMNA];
        
    }
    
    public void rellenarTablero(){
        for (int i = 0; i < FILA; i++) {
            for (int j = 0; j < COLUMNA; j++) {
                if (i < 2 || i > 4) {
                    switch (j) {
                        case 0:
                            tablero[i][j] = ' ';
                            break;
                        case 1:
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
                        case 5:
                            tablero[i][j] = ' ';
                            break;
                        case 6:
                            tablero[i][j] = ' ';
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
        for (int i = 0; i < FILA; i++) {
            for (int j = 0; j < COLUMNA; j++) {
                texto += tablero[j][i];
            }
            texto += "\n";
        }
        return texto;
    }
    
    public boolean esCorrecto(){
        boolean correcto = false;
        if (origenX == '#' && origenY == '#') {
            correcto = true;
        }
        if (destinoX == '.' && destinoY == '.') {
            correcto = true;
        }
        if (destinoX-1 == '#' && destinoY-1 == '#') {
            correcto = true;
        }
        return correcto;
    }
    
    public void setMovimiento(int origenX, int origenY, int destinoX, int destinoY){
        char datoOrigen = tablero[origenX][origenY];
        char datoFinal = tablero[destinoX][destinoY];
        tablero[destinoX][destinoY] = datoOrigen;
        tablero[origenX][origenY] = datoFinal;
        if (origenY == destinoY) {
            if (origenX > destinoX) {
                tablero[destinoX+1][destinoY] = datoFinal;
            } else{
                tablero[destinoX-1][destinoY] = datoFinal;
            }
        }  
            
        if(origenX == destinoX){
            if (origenY > destinoY) {
                tablero[destinoX][destinoY+1] = datoFinal;
            } else{
                tablero[destinoX][destinoY-1] = datoFinal;
            }
        }
    }
}
