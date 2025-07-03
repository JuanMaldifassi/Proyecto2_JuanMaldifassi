/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Archivos;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author juanp
 */
public class FileChooser {
    
    private JFrame ventana;
    private String txt;
    private String ruta;

    public FileChooser(JFrame ventana) {
        this.ventana = ventana;
        this.txt = null;
        this.ruta = null;
    }
    
    public JFrame getVentana() {
        return ventana;
    }

    public void setVentana(JFrame ventana) {
        this.ventana = ventana;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
    
    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    public void fileChooser(){
        JFileChooser fc = new JFileChooser();

        // Creo el filtro para archivos .txt
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos TXT (*.txt)", "txt");

        // Le indico el filtro
        fc.setFileFilter(filtro);

        // Abrimos la ventana, guardamos la opción seleccionada por el usuario
        int seleccion = fc.showOpenDialog(ventana);

        // Si el usuario presiona aceptar
        if (seleccion == JFileChooser.APPROVE_OPTION) {

            // Selecciono el fichero
            File fichero = fc.getSelectedFile();

            //Guardo la ruta
            this.setRuta(fichero.getAbsolutePath());
            
            try (FileReader fr = new FileReader(fichero)) {
                StringBuilder cadena = new StringBuilder();
                int valor = fr.read();

                // Leo el contenido del archivo JSON
                while (valor != -1) {
                    cadena.append((char) valor);
                    valor = fr.read();
                }

                // Modifico el valor del JTextArea para mostrar el contenido del archivo
                txt = cadena.toString();

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } else {
            // Si el usuario no seleccionó ningún archivo, muestra un mensaje
            JOptionPane.showMessageDialog(ventana, "No se ha seleccionado ningún archivo.");
        }  
    }
}
