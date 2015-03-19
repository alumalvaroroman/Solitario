package solitario;


public class Movimientos {
    
    private int origenX;
    private int origenY;
    private int destinoX;
    private int destinoY;
    
    public Movimientos(int origenX, int origenY, int destinoX, int destinoY) {
        this.origenX = origenX;
        this.origenY = origenY;
        this.destinoX = destinoX;
        this.destinoY = destinoY;
    }

    public int getOrigenX() {
        return origenX;
    }

    public int getOrigenY() {
        return origenY;
    }

    public int getDestinoX() {
        return destinoX;
    }

    public int getDestinoY() {
        return destinoY;
    }  
}
