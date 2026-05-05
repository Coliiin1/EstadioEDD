/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estadio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author fabri
 */
public class Evento {
    private String nombreEvento;
    private EstadioClass estadio;
    private LinkedList<Boleto> boletosVip; 
    private LinkedList<Boleto> boletosPreferencial; 
    private LinkedList<Boleto> boletosGeneral; 
    private HashMap<String,Categoria> categorias;
    private Asientos[][] asientosVip,asientosPref,asientosGene;
    Queue<Reporte> pilaReportes;

    public Evento(String nombreEvento, EstadioClass estadio) {
        this.nombreEvento = nombreEvento;
        this.estadio = estadio;
        boletosVip=new LinkedList();
        boletosPreferencial=new LinkedList();
        boletosGeneral=new LinkedList();
        categorias=new HashMap();
    }
    
}
