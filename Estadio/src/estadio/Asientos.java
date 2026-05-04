/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estadio;

/**
 *
 * @author Alberto
 */
public class Asientos
{
    /**
     * El final en fila y columna 
     * nos garantiza inmutabilidad y restringe modifcaciones en estas variables
     */
    private final int fila;
    private final int columna;
    private Categoria categoria;//sera asignada por el Admin
    private EstadoAsientos estado;//Disponible, seleccionado, ocupado

    public Asientos(int fila, int columna)
    {
        this.fila = fila;
        this.columna = columna;
        this.categoria = Categoria.GENERAL;
        this.estado = EstadoAsientos.DISPONIBLE;
    }

    /**
     * @return the fila
     */
    public int getFila()
    {
        return fila;
    }

    /**
     * @return the columna
     */
    public int getColumna()
    {
        return columna;
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
     * @return the estado
     */
    public EstadoAsientos getEstado()
    {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoAsientos estado)
    {
        this.estado = estado;
    }

    public boolean isDisponible()
    {
        return estado == EstadoAsientos.DISPONIBLE;
    }

    public String getId()
    {
        return "F" + (fila + 1) + "-C" + (columna + 1);
    }
}
