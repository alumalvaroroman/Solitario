package solitario;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import java.io.BufferedReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


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
            if (origenX > destinoX) {//Derecha-Izquierda orig
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
            if (origenY > destinoY) {//Abajo-Arriba
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
    
    public void crearXML(){
        try {
            DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
            //Crear un nuevo documento XML
            Document documento = creadorDocumento.newDocument();


            //Crear el nodo raíz y colgarlo del documento
            Element elementoRaiz = documento.createElement("MOVIMIENTOS");
            documento.appendChild(elementoRaiz);
            
            for (int i = 0; i < listaMovimientos.size(); i++) {
                //Crear un elemento MOVIMIENTO colgando de MOVIMIENTOS
                Element elementoMovimiento = documento.createElement("MOVIMIENTO");
                elementoRaiz.appendChild(elementoMovimiento);

                //Crear un elemento NUM_MOVIMIENTO colgando de MOVIMIENTO
                Element elementoNumMovimiento = documento.createElement("NUM_MOVIMIENTO");
                elementoMovimiento.appendChild(elementoNumMovimiento);

                //Crear un elemento ORIGENX colgando de MOVIMIENTO
                Element elementoOrigenX = documento.createElement("ORIGENX");
                elementoNumMovimiento.appendChild(elementoOrigenX);
                
                //Crear un elemento ORIGENY colgando de MOVIMIENTO
                Element elementoOrigenY = documento.createElement("ORIGENY");
                elementoNumMovimiento.appendChild(elementoOrigenY);

                //Crear un elemento DESTINOX colgando de MOVIMIENTO
                Element elementoDestinoX = documento.createElement("DESTINOX");
                elementoNumMovimiento.appendChild(elementoDestinoX);

                //Crear un elemento DESTINOY colgando de MOVIMIENTO
                Element elementoDestinoY = documento.createElement("DESTINOY");
                elementoNumMovimiento.appendChild(elementoDestinoY);

                //Obtener los numero de movimiento y colgarlos de NUM_MOVIMIENTO
                Text textoNumMovimiento = documento.createTextNode(String.valueOf(i + 1));
                elementoNumMovimiento.appendChild(textoNumMovimiento);

                //Obtener la fila de origen y colgarlos de ORIGENX
                Text textoOrigenX = documento.createTextNode(String.valueOf(listaMovimientos.get(i).getOrigenX()));
                elementoOrigenX.appendChild(textoOrigenX);
                
                //Obtener la columna de origen y colgarlos de ORIGENY
                Text textoOrigenY = documento.createTextNode(String.valueOf(listaMovimientos.get(i).getOrigenY()));
                elementoOrigenY.appendChild(textoOrigenY);

                //Obtener la fila de destino y colgarlos de DESTINOX
                Text textoDestinoX = documento.createTextNode(String.valueOf(listaMovimientos.get(i).getDestinoX()));
                elementoDestinoX.appendChild(textoDestinoX);

                //Obtener la columna de destino y colgarlos de DESTINOY
                Text textoDestinoY = documento.createTextNode(String.valueOf(listaMovimientos.get(i).getDestinoY()));
                elementoDestinoY.appendChild(textoDestinoY);
            }
            //Generar el tranformador para obtener el documento XML en un fichero
            TransformerFactory fábricaTransformador = TransformerFactory.newInstance();
            Transformer transformador = fábricaTransformador.newTransformer();
            //Insertar saltos de línea al final de cada línea
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            //Añadir 3 espacios delante, en función del nivel de cada nodo
            transformador.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "3");
            Source origen = new DOMSource(documento);
            Result destino = new StreamResult("movimientos.xml");
            transformador.transform(origen, destino);

        } catch (ParserConfigurationException ex) {
            System.out.println("ERROR: No se ha podido crear el generador de documentos XML\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (TransformerConfigurationException ex) {
            System.out.println("ERROR: No se ha podido crear el transformador del documento XML\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (TransformerException ex) {
            System.out.println("ERROR: No se ha podido crear la salida del documento XML\n"+ex.getMessage());
            ex.printStackTrace();
        }
    }
}
