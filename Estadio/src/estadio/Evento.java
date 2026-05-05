/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estadio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author fabri
 */
public class Evento {
    public String nombreEvento;
    public EstadioClass estadio;
    public LinkedList<Boleto> boletosVip; 
    public LinkedList<Boleto> boletosPreferencial; 
    public LinkedList<Boleto> boletosGeneral; 
    public HashMap<String,Categoria> categorias;
    public Asientos[][] asientos;
    public Date Fecha; 
    public String Descripcion;
    public Queue<Reporte> colaReportes;
    public String rutaImg;
    
    public Evento(String nombreEvento, EstadioClass estadio) {
        this.nombreEvento = nombreEvento;
        this.estadio = estadio;
        boletosVip=new LinkedList();
        boletosPreferencial=new LinkedList();
        boletosGeneral=new LinkedList();
        categorias=new HashMap();
        categorias.put("VIP", Categoria.VIP);
        categorias.put("PREFERENCIAL", Categoria.PREFERENCIAL);
        categorias.put("GENERAL", Categoria.GENERAL);
        
        colaReportes=new LinkedList();
        
    }
    
    public void ImpEvento(){
        System.out.println("Nombre: " + nombreEvento);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaTexto = sdf.format(Fecha);
        System.out.println("Fecha seleccionada: " + fechaTexto);
        for(String key : categorias.keySet()){  
            System.out.println(key+" "+categorias.get(key).getPrecio());
        }
    }
    
    public void mostarReporte(){
        if(!colaReportes.isEmpty()){
            return;
        }
        colaReportes.peek().mostrar();
    }
}
