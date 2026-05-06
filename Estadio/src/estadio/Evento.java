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
    public Date Fecha; 
    public String Descripcion;
    public Queue<Reporte> colaReportes;
    public String rutaImg;
    
    public Evento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
        estadio = new EstadioClass();
        rutaImg = "";
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
    
    public void actualizarListas(){
        boletosGeneral.clear();
        boletosVip.clear();
        boletosPreferencial.clear();
        Boleto temp;
        for (int i = 0; i < estadio.getFILAS(); i++) {
            for (int j = 0; j < estadio.getCOLUMNAS(); j++) {
                switch (estadio.getMatriz()[i][j].getCategoria()) { //categorira
                    case VIP: boletosVip.add(temp=new Boleto(estadio.getMatriz()[i][j].getId(), estadio.getMatriz()[i][j], estadio.getMatriz()[i][j].getEstado()));
                        break;
                    case PREFERENCIAL: boletosPreferencial.add(temp=new Boleto(estadio.getMatriz()[i][j].getId(), estadio.getMatriz()[i][j], estadio.getMatriz()[i][j].getEstado()));
                        break;
                    case GENERAL: boletosGeneral.add(temp=new Boleto(estadio.getMatriz()[i][j].getId(), estadio.getMatriz()[i][j], estadio.getMatriz()[i][j].getEstado()));
                        break;
                    
                    default:
                        throw new AssertionError();
                }
            }
        }
    }
    
    public void imprimirlistas(){
        System.out.println("VIP: ");
        for (Boleto recorre: boletosVip) {
            System.out.print(recorre.toString()+"\n");
        }
        System.out.println("--------------------------------------------------");
        System.out.println("PREFERENCIAL: ");
        for (Boleto recorre: boletosPreferencial) {
            System.out.print(recorre.toString()+"\n");
        }
        System.out.println("--------------------------------------------------");
        System.out.println("GENERAL: ");
        for (Boleto recorre: boletosGeneral) {
            System.out.print(recorre.toString()+"\n");
        }
        System.out.println("--------------------------------------------------");
    }
}
