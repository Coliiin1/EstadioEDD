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

    private boolean[][] asientosVIP;
    private boolean[][] asientosGen;
    private boolean[][] asientosPref;

    /**
     * Esto significa que cada categoria tendria 150 asientos ya se le puede
     * modificar si quieren. Y cada que se crea un objeto Estadio,
     * automaticamente se inicializan estas tres matrices dejandolas todas
     * disponibles
     */
    private static final int filas = 10;
    private static final int columnas = 15;

    public EstadioClass()
    {
        asientosVIP = new boolean[filas][columnas];
        asientosGen = new boolean[filas][columnas];
        asientosPref = new boolean[filas][columnas];
    }

    /**
     * isDisponible llama a getMatriz y revisa si el asiento es false. El
     * operador "!" invierte el valor para que, si es false (libre), el metodo
     * devuelva true (si, esta disponible).
     */
    public boolean isDisponible(Categoria cat, int fila, int col)
    {
        return !getMatriz(cat)[fila][col];
    }

    public void ocuparAsiento(Categoria cat, int fila, int col)
    {
        if (!isDisponible(cat, fila, col))
        {
            throw new IllegalStateException("El asiento ya esta ocupado.");
        }
        getMatriz(cat)[fila][col] = false;
    }

    public boolean[][] getMatriz(Categoria cat)
    {
        return switch (cat)
        {
            case VIP ->
                asientosVIP;
            case GENERAL ->
                asientosGen;
            case PREFERENCIAL ->
                asientosPref;
        };
    }

    /**
     * Como habia comentado Faxz, el ID de los asientos podria ser
     * "F3-C7" para fila 2 y columna 6 ya que el +1 incremenda ese indice
     * (base 0-> base 1) 
     */
    public String generarIDAsientos(int fila, int col)
    {
        return "F" + (fila+1) + "-C" + (col+1);
    }

    /**
     * @return the filas
     */
    public static int getFilas()
    {
        return filas;
    }

    /**
     * @return the columnas
     */
    public static int getColumnas()
    {
        return columnas;
    }

}
