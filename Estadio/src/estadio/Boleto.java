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
    private int numeroAsiento;
    private boolean estado;

    public Boleto(String idBoleto, Categoria categoria, String numeroAsiento, boolean estado)
    {
        this.idBoleto = idBoleto;
        this.categoria = categoria;
        this.numeroAsiento = numeroAsiento;
        this.estado = estado;
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
     * @return the numeroAsiento
     */
    public String getNumeroAsiento()
    {
        return numeroAsiento;
    }

    /**
     * @param numeroAsiento the numeroAsiento to set
     */
    public void setNumeroAsiento(String numeroAsiento)
    {
        this.numeroAsiento = numeroAsiento;
    }

    @Override
    public String toString()
    {
        return "ID: " + idBoleto
                + " Categoria: " + categoria.name()
                + " Asiento: " + numeroAsiento
                + " Precio: $" + categoria.getPrecio();
    }
}
