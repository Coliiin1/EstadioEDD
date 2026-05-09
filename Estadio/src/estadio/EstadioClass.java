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
    private int FILAS = 18;
    private int COLUMNAS = 38;
    private int disposicion[][];
    
    public EstadioClass()
    {
        matriz= new Asientos[][]{
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(0, 12),new Asientos(0, 16),new Asientos(0, 17),new Asientos(0, 18),new Asientos(0, 19),new Asientos(0, 20),new Asientos(0, 21),new Asientos(0, 22),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,new Asientos(1, 11),new Asientos(1, 12),new Asientos(1, 13),new Asientos(1, 14),new Asientos(1, 15),new Asientos(1, 16),new Asientos(1, 17),new Asientos(1, 18),new Asientos(1, 19),new Asientos(1, 20),new Asientos(1, 21),new Asientos(1, 22),new Asientos(1, 23),new Asientos(1, 24),new Asientos(1, 25),new Asientos(1, 26),null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,new Asientos(2, 8),new Asientos(2, 9),new Asientos(2, 10),new Asientos(2, 11),new Asientos(2, 12),new Asientos(2, 13),new Asientos(2, 14),new Asientos(2, 15),new Asientos(2, 16),new Asientos(2, 17),new Asientos(2, 18),new Asientos(2, 19),new Asientos(2, 20),new Asientos(2, 21),new Asientos(2, 22),new Asientos(2, 23),new Asientos(2, 24),new Asientos(2, 25),new Asientos(2, 26),new Asientos(2, 27),new Asientos(2, 28),new Asientos(2, 29),null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,new Asientos(2, 7),new Asientos(2, 8),new Asientos(2, 9),new Asientos(2, 10),new Asientos(2, 11),new Asientos(2, 12),new Asientos(2, 13),new Asientos(2, 14),new Asientos(2, 15),new Asientos(2, 16),new Asientos(2, 17),new Asientos(2, 18),new Asientos(2, 19),new Asientos(2, 20),new Asientos(2, 21),new Asientos(2, 22),new Asientos(2, 23),new Asientos(2, 24),new Asientos(2, 25),new Asientos(2, 26),new Asientos(2, 27),new Asientos(2, 28),new Asientos(2, 29),new Asientos(2, 30),null,null,null,null,null,null,null},
            {null,null,null,null,null,new Asientos(2, 5),new Asientos(2, 6),new Asientos(2, 7),new Asientos(2, 8),new Asientos(2, 9),new Asientos(2, 10),new Asientos(2, 11),new Asientos(2, 12),new Asientos(2, 13),new Asientos(2, 14),new Asientos(2, 15),new Asientos(2, 16),new Asientos(2, 17),new Asientos(2, 18),new Asientos(2, 19),new Asientos(2, 20),new Asientos(2, 21),new Asientos(2, 22),new Asientos(2, 23),new Asientos(2, 24),new Asientos(2, 25),new Asientos(2, 26),new Asientos(2, 27),new Asientos(2, 28),new Asientos(2, 29),new Asientos(2, 30),new Asientos(2, 31),new Asientos(2, 32),null,null,null,null,null},
            {null,null,null,null,new Asientos(2, 4),new Asientos(2, 5),new Asientos(2, 6),new Asientos(2, 7),new Asientos(2, 8),new Asientos(2, 9),new Asientos(2, 10),new Asientos(2, 11),new Asientos(2, 12),new Asientos(2, 13),new Asientos(2, 14),new Asientos(2, 15),new Asientos(2, 16),new Asientos(2, 17),new Asientos(2, 18),new Asientos(2, 19),new Asientos(2, 20),new Asientos(2, 21),new Asientos(2, 22),new Asientos(2, 23),new Asientos(2, 24),new Asientos(2, 25),new Asientos(2, 26),new Asientos(2, 27),new Asientos(2, 28),new Asientos(2, 29),new Asientos(2, 30),new Asientos(2, 31),new Asientos(2, 32),new Asientos(2, 33),null,null,null,null},
            {null,null,null,new Asientos(2, 3),new Asientos(2, 4),new Asientos(2, 5),new Asientos(2, 6),new Asientos(2, 7),new Asientos(2, 8),new Asientos(2, 9),new Asientos(2, 10),new Asientos(2, 11),new Asientos(2, 12),new Asientos(2, 13),new Asientos(2, 14),new Asientos(2, 15),new Asientos(2, 16),new Asientos(2, 17),new Asientos(2, 18),new Asientos(2, 19),new Asientos(2, 20),new Asientos(2, 21),new Asientos(2, 22),new Asientos(2, 23),new Asientos(2, 24),new Asientos(2, 25),new Asientos(2, 26),new Asientos(2, 27),new Asientos(2, 28),new Asientos(2, 29),new Asientos(2, 30),new Asientos(2, 31),new Asientos(2, 32),new Asientos(2, 33),new Asientos(2, 34),null,null,null},
            {null,null,null,new Asientos(2, 3),new Asientos(2, 4),new Asientos(2, 5),new Asientos(2, 6),new Asientos(2, 7),new Asientos(2, 8),new Asientos(2, 9),new Asientos(2, 10),new Asientos(2, 11),new Asientos(2, 12),new Asientos(2, 13),new Asientos(2, 14),new Asientos(2, 15),new Asientos(2, 16),new Asientos(2, 17),new Asientos(2, 18),new Asientos(2, 19),new Asientos(2, 20),new Asientos(2, 21),new Asientos(2, 22),new Asientos(2, 23),new Asientos(2, 24),new Asientos(2, 25),new Asientos(2, 26),new Asientos(2, 27),new Asientos(2, 28),new Asientos(2, 29),new Asientos(2, 30),new Asientos(2, 31),new Asientos(2, 32),new Asientos(2, 33),new Asientos(2, 34),null,null,null},
            {null,null,new Asientos(2, 2),new Asientos(2, 3),new Asientos(2, 4),new Asientos(2, 5),new Asientos(2, 6),new Asientos(2, 7),new Asientos(2, 8),new Asientos(2, 9),new Asientos(2, 10),new Asientos(2, 11),new Asientos(2, 12),new Asientos(2, 13),new Asientos(2, 14),new Asientos(2, 15),new Asientos(2, 16),new Asientos(2, 17),new Asientos(2, 18),new Asientos(2, 19),new Asientos(2, 20),new Asientos(2, 21),new Asientos(2, 22),new Asientos(2, 23),new Asientos(2, 24),new Asientos(2, 25),new Asientos(2, 26),new Asientos(2, 27),new Asientos(2, 28),new Asientos(2, 29),new Asientos(2, 30),new Asientos(2, 31),new Asientos(2, 32),new Asientos(2, 33),new Asientos(2, 34),new Asientos(2, 35),null,null},
            {null,new Asientos(2, 1),new Asientos(2, 2),new Asientos(2, 3),new Asientos(2, 4),new Asientos(2, 5),new Asientos(2, 6),new Asientos(2, 7),new Asientos(2, 8),new Asientos(2, 9),new Asientos(2, 10),new Asientos(2, 11),new Asientos(2, 12),new Asientos(2, 13),new Asientos(2, 14),new Asientos(2, 15),new Asientos(2, 16),new Asientos(2, 17),new Asientos(2, 18),new Asientos(2, 19),new Asientos(2, 20),new Asientos(2, 21),new Asientos(2, 22),new Asientos(2, 23),new Asientos(2, 24),new Asientos(2, 25),new Asientos(2, 26),new Asientos(2, 27),new Asientos(2, 28),new Asientos(2, 29),new Asientos(2, 30),new Asientos(2, 31),new Asientos(2, 32),new Asientos(2, 33),new Asientos(2, 34),new Asientos(2, 35),new Asientos(2, 36),null},
            {null,new Asientos(2, 1),new Asientos(2, 2),new Asientos(2, 3),new Asientos(2, 4),new Asientos(2, 5),new Asientos(2, 6),new Asientos(2, 7),new Asientos(2, 8),new Asientos(2, 9),new Asientos(2, 10),new Asientos(2, 11),new Asientos(2, 12),new Asientos(2, 13),new Asientos(2, 14),new Asientos(2, 15),new Asientos(2, 16),new Asientos(2, 17),new Asientos(2, 18),new Asientos(2, 19),new Asientos(2, 20),new Asientos(2, 21),new Asientos(2, 22),new Asientos(2, 23),new Asientos(2, 24),new Asientos(2, 25),new Asientos(2, 26),new Asientos(2, 27),new Asientos(2, 28),new Asientos(2, 29),new Asientos(2, 30),new Asientos(2, 31),new Asientos(2, 32),new Asientos(2, 33),new Asientos(2, 34),new Asientos(2, 35),new Asientos(2, 36),null},
            {new Asientos(2, 0),new Asientos(2, 1),new Asientos(2, 2),new Asientos(2, 3),new Asientos(2, 4),new Asientos(2, 5),new Asientos(2, 6),new Asientos(2, 7),new Asientos(2, 8),new Asientos(2, 9),new Asientos(2, 10),new Asientos(2, 11),new Asientos(2, 12),new Asientos(2, 13),new Asientos(2, 14),new Asientos(2, 15),new Asientos(2, 16),new Asientos(2, 17),new Asientos(2, 18),new Asientos(2, 19),new Asientos(2, 20),new Asientos(2, 21),new Asientos(2, 22),new Asientos(2, 23),new Asientos(2, 24),new Asientos(2, 25),new Asientos(2, 26),new Asientos(2, 27),new Asientos(2, 28),new Asientos(2, 29),new Asientos(2, 30),new Asientos(2, 31),new Asientos(2, 32),new Asientos(2, 33),new Asientos(2, 34),new Asientos(2, 35),new Asientos(2, 36),new Asientos(2, 37)},
            {new Asientos(2, 0),new Asientos(2, 1),new Asientos(2, 2),new Asientos(2, 3),new Asientos(2, 4),new Asientos(2, 5),new Asientos(2, 6),new Asientos(2, 7),new Asientos(2, 8),new Asientos(2, 9),new Asientos(2, 10),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(2, 27),new Asientos(2, 28),new Asientos(2, 29),new Asientos(2, 30),new Asientos(2, 31),new Asientos(2, 32),new Asientos(2, 33),new Asientos(2, 34),new Asientos(2, 35),new Asientos(2, 36),new Asientos(2, 37)},
            {new Asientos(2, 0),new Asientos(2, 1),new Asientos(2, 2),new Asientos(2, 3),new Asientos(2, 4),new Asientos(2, 5),new Asientos(2, 6),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(2, 31),new Asientos(2, 32),new Asientos(2, 33),new Asientos(2, 34),new Asientos(2, 35),new Asientos(2, 36),new Asientos(2, 37)},
            {new Asientos(2, 0),new Asientos(2, 1),new Asientos(2, 2),new Asientos(2, 3),new Asientos(2, 4),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(2, 33),new Asientos(2, 34),new Asientos(2, 35),new Asientos(2, 36),new Asientos(2, 37)},
            {new Asientos(2, 0),new Asientos(2, 1),new Asientos(2, 2),new Asientos(2, 3),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(2, 34),new Asientos(2, 35),new Asientos(2, 36),new Asientos(2, 37)},
            {new Asientos(2, 0),new Asientos(2, 1),new Asientos(2, 2),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(2, 35),new Asientos(2, 36),new Asientos(2, 37)},
            {null,new Asientos(2, 1),new Asientos(2, 2),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(2, 35),new Asientos(2, 36),null},

        
        
        
        
        
        
        
        
        
        
        };

        
        
        
        
        
//        matriz = new Asientos[FILAS][COLUMNAS];
//        for (int f = 0; f < FILAS; f++)
//        {
//            for (int c = 0; c < COLUMNAS; c++)
//            {
//                matriz[f][c] = new Asientos(f, c);
//            }
//        }
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
        return matriz.length;
    }

    /**
     * @return the COLUMNAS
     */
    public int getCOLUMNAS()
    {
        return matriz[0].length;
    }
}
