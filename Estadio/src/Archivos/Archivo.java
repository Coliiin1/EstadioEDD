/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Archivos;

import estadio.Reporte;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author fabri
 */
public class Archivo {
    public static void crearReporte(Reporte rep){
        DateTimeFormatter fortmato=DateTimeFormatter.ofPattern("ddMMyyyy");
        String fecha=rep.getFecha().format(fortmato);
        String dir="reporte_ventas_"+fecha+".txt";
        PrintWriter bsalida=null;
        try {
            File arch=new File(dir);
            bsalida = new PrintWriter(arch);
            bsalida.write(rep.getIdVenta());
            bsalida.write(rep.getNumero_boletos());
            bsalida.write(fecha);
            bsalida.write(rep.getCategoria().toString());
            JOptionPane.showMessageDialog(null, "SE CREO EL ARCHIVO");
            bsalida.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "SE ENCONTRO UN ERROR");
        }
    }
}
