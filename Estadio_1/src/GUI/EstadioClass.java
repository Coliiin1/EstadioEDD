/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estadio;

/**
 *
 * @author Alberto
 */
public class EstadioClass
{

    private Asientos[][] matriz;
    private int FILAS = 10;
    private int COLUMNAS = 30;

    public EstadioClass()
    {
        matriz = new Asientos[FILAS][COLUMNAS];
        for (int f = 0; f < FILAS; f++)
        {
            for (int c = 0; c < COLUMNAS; c++)
            {
                matriz[f][c] = new Asientos(f, c);
            }
        }
    }

    public boolean isDisponible(int fila, int col)
    {
        return getMatriz()[fila][col].getEstado() == EstadoAsientos.DISPONIBLE;
    }

    public Asientos getAsiento(int fila, int col)
    {
        return matriz[fila][col];
    }

    /**
     * @return the matriz
     */
    public Asientos[][] getMatriz()
    {
        return matriz;
    }

    //modificar la categoria por el admin
    public void asignarCategoria(int fila, int col, Categoria cat)
    {
        matriz[fila][col].setCategoria(cat);
    }

    //se marca como Ocupado al confirmar la compra
    public void ocuparAsiento(int fila, int col)
    {
        if (!isDisponible(fila, col))
        {
            throw new IllegalStateException("El asiento ya está ocupado.");
        }
        matriz[fila][col].setEstado(EstadoAsientos.OCUPADO);
    }

    public void seleccionarAsiento(int fila, int col)
    {
        if (!isDisponible(fila, col))
        {
            throw new IllegalStateException("El asiento no está disponible.");
        }
        matriz[fila][col].setEstado(EstadoAsientos.SELECCIONADO);
    }

    public void deseleccionarAsiento(int fila, int col)
    {
        matriz[fila][col].setEstado(EstadoAsientos.DISPONIBLE);
    }

    public String generarIdAsiento(int fila, int col)
    {
        return "F" + (fila + 1) + "-C" + (col + 1);
    }

    /**
     * @return the FILAS
     */
    public int getFILAS()
    {
        return FILAS;
    }

    /**
     * @return the COLUMNAS
     */
    public int getCOLUMNAS()
    {
        return COLUMNAS;
    }
}
