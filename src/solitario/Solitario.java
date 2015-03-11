package solitario;


public class Solitario {
    
    private final int FILA = 7;
    private final int COLUMNA = 7;
    
    private char[][] tablero;
    
    private String[] patron;

    public Solitario() {
        tablero = new char[FILA][COLUMNA];
        
    }
    
    public void rellenarTablero(){
        for (int i = 0; i < FILA; i++) {
            for (int j = 0; j < COLUMNA; j++) {
                if (i < 2 || i > 4) {
                    switch (j) {
                        case 0:
                            tablero[i][j] = '-';
                            break;
                        case 1:
                            tablero[i][j] = '-';
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
                            tablero[i][j] = '-';
                            break;
                        case 6:
                            tablero[i][j] = '-';
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
                texto += tablero[i][j];
            }
            texto += "\n";
        }
        return texto;
    }
}
