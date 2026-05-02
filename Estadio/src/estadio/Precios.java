package estadio;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
/**
 *
 * @author fabri
 */
public class Precios {
    //esta clase tiene como finalidad hacer un hash map con los precios 
    private Map<String,Double>precios;
    private Scanner sc=new Scanner(System.in);

    public Precios() {
        precios = new HashMap<String,Double>();
    }
    public void agregarPrecio(){
        System.out.println("AGREGA UN BOLETO Y PRECIO");
        String llave=sc.nextLine();
        double precio=Double.parseDouble(sc.nextLine());
        precios.put(llave,precio);
    }
    public void agregarPrecio(String clave, double precio){
        precios.put(clave,precio);
    }
    public void displayPrecios(){
        Set<String> clavesOrdenadas=precios.keySet();
        for(String clave:clavesOrdenadas){
            System.out.printf("%-20s  %10s\n",clave,precios.get(clave));
        }
    }
    public static void main(String args[]){
        Precios precios=new Precios();
        precios.agregarPrecio("VIP",1500);
        precios.agregarPrecio("Preferencial",1000);
        precios.agregarPrecio("General",500);
        precios.displayPrecios();
    }
}
