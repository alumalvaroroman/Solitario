package solitario;

public class Prueba {

    static Solitario solitario = new Solitario();
    
    public static void main(String[] args) {
        solitario.rellenarTablero();
        System.out.println(solitario.pintarTablero());
    }
    
}
