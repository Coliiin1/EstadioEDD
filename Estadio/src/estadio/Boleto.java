/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estadio;

/**
 *
 * @author Alberto
 */
public class Boleto
{

    private String idBoleto;
    private Categoria categoria;
    private Asientos asiento;
    private EstadoAsientos estado;

    public Boleto(String idBoleto, Asientos asiento, EstadoAsientos estado)
    {
        this.idBoleto = idBoleto;
        this.asiento=asiento;
        this.categoria=this.asiento.getCategoria();
        this.estado = estado;
    }
    public Boleto(String idBoleto,Categoria categoria, int fila, int columna,EstadoAsientos estado, Double precio)
    {
        this.idBoleto = idBoleto;
        this.asiento=asiento;
        this.categoria=categoria;
        this.estado = estado;
        Asientos asiento = new Asientos(fila, columna);
        asiento.setCategoria(categoria);
        asiento.setEstado(estado);
        
    }

    public double getPrecio()
    {
        return categoria.getPrecio();
    }

    /**
     * @return the idBoleto
     */
    public String getIdBoleto()
    {
        return idBoleto;
    }

    /**
     * @param idBoleto the idBoleto to set
     */
    public void setIdBoleto(String idBoleto)
    {
        this.idBoleto = idBoleto;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria()
    {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria)
    {
        this.categoria = categoria;
    }

    /**
     * @return the asiento
     */
    public Asientos getNumeroAsiento()
    {
        return asiento;
    }

    /**
     * @param numeroAsiento the asiento to set
     */
    public void setNumeroAsiento(Asientos numeroAsiento)
    {
        this.asiento = numeroAsiento;
    }

    @Override
    public String toString()
    {
        return "ID: " + idBoleto
                + " Categoria: " + categoria.name()
                + " Asiento: " + asiento
                + " Precio: $" + categoria.getPrecio()
                + " Estado: " + estado;
        
    }
    
    public EstadoAsientos getEstado(){
        return estado;
    }
    
}
