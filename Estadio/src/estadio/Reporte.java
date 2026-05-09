/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estadio;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 *
 * @author fabri
 */
public class Reporte {
    private int idVenta;
    private LocalDate fecha;
    private LinkedList<Boleto> boletos;
    private int numero_boletos;
    private Categoria categoria;
    private double ingreso;

    public Reporte(int idVenta, LinkedList<Boleto> boletos, int numero_boletos, Categoria categoria) {
        this.idVenta = idVenta;
        this.fecha = LocalDate.now();
        this.boletos = boletos;
        this.numero_boletos = numero_boletos;
        this.categoria = categoria;
        this.ingreso = calcularIngreso();
    }
    
    public int getIdVenta() {
        return idVenta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LinkedList<Boleto> getBoletos() {
        return boletos;
    }

    public int getNumero_boletos() {
        return numero_boletos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public double getIngreso() {
        return ingreso;
    }
    
    private double calcularIngreso(){
        if (boletos==null){
            return 0.00;
        }
        double total=0;
        
        for(Boleto boleto:boletos){
            total+=boleto.getPrecio();
        }
        return total;
    }
    
    public void mostrar(){
        System.out.println("id: "+idVenta+" ingreso: "+ingreso+" Detalles:");
        for(Boleto bol:boletos){
            System.out.println(bol.toString());
        }
    }
}
