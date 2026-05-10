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
        /*matriz= new Asientos[][]{
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(0, 15),new Asientos(0, 16),new Asientos(0, 17),new Asientos(0, 18),new Asientos(0, 19),new Asientos(0, 20),new Asientos(0, 21),new Asientos(0, 22),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,new Asientos(1, 11),new Asientos(1, 12),new Asientos(1, 13),new Asientos(1, 14),new Asientos(1, 15),new Asientos(1, 16),new Asientos(1, 17),new Asientos(1, 18),new Asientos(1, 19),new Asientos(1, 20),new Asientos(1, 21),new Asientos(1, 22),new Asientos(1, 23),new Asientos(1, 24),new Asientos(1, 25),new Asientos(1, 26),null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,new Asientos(2, 8),new Asientos(2, 9),new Asientos(2, 10),new Asientos(2, 11),new Asientos(2, 12),new Asientos(2, 13),new Asientos(2, 14),new Asientos(2, 15),new Asientos(2, 16),new Asientos(2, 17),new Asientos(2, 18),new Asientos(2, 19),new Asientos(2, 20),new Asientos(2, 21),new Asientos(2, 22),new Asientos(2, 23),new Asientos(2, 24),new Asientos(2, 25),new Asientos(2, 26),new Asientos(2, 27),new Asientos(2, 28),new Asientos(2, 29),null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,new Asientos(3, 7),new Asientos(3, 8),new Asientos(3, 9),new Asientos(3, 10),new Asientos(3, 11),new Asientos(3, 12),new Asientos(3, 13),new Asientos(3, 14),new Asientos(3, 15),new Asientos(3, 16),new Asientos(3, 17),new Asientos(3, 18),new Asientos(3, 19),new Asientos(3, 20),new Asientos(3, 21),new Asientos(3, 22),new Asientos(3, 23),new Asientos(3, 24),new Asientos(3, 25),new Asientos(3, 26),new Asientos(3, 27),new Asientos(3, 28),new Asientos(3, 29),new Asientos(3, 30),null,null,null,null,null,null,null},
            {null,null,null,null,null,new Asientos(4, 5),new Asientos(4, 6),new Asientos(4, 7),new Asientos(4, 8),new Asientos(4, 9),new Asientos(4, 10),new Asientos(4, 11),new Asientos(4, 12),new Asientos(4, 13),new Asientos(4, 14),new Asientos(4, 15),new Asientos(4, 16),new Asientos(4, 17),new Asientos(4, 18),new Asientos(4, 19),new Asientos(4, 20),new Asientos(4, 21),new Asientos(4, 22),new Asientos(4, 23),new Asientos(4, 24),new Asientos(4, 25),new Asientos(4, 26),new Asientos(4, 27),new Asientos(4, 28),new Asientos(4, 29),new Asientos(4, 30),new Asientos(4, 31),new Asientos(4, 32),null,null,null,null,null},
            {null,null,null,null,new Asientos(5, 4),new Asientos(5, 5),new Asientos(5, 6),new Asientos(5, 7),new Asientos(5, 8),new Asientos(5, 9),new Asientos(5, 10),new Asientos(5, 11),new Asientos(5, 12),new Asientos(5, 13),new Asientos(5, 14),new Asientos(5, 15),new Asientos(5, 16),new Asientos(5, 17),new Asientos(5, 18),new Asientos(5, 19),new Asientos(5, 20),new Asientos(5, 21),new Asientos(5, 22),new Asientos(5, 23),new Asientos(5, 24),new Asientos(5, 25),new Asientos(5, 26),new Asientos(5, 27),new Asientos(5, 28),new Asientos(5, 29),new Asientos(5, 30),new Asientos(5, 31),new Asientos(5, 32),new Asientos(5, 33),null,null,null,null},
            {null,null,null,new Asientos(6, 3),new Asientos(6, 4),new Asientos(6, 5),new Asientos(6, 6),new Asientos(6, 7),new Asientos(6, 8),new Asientos(6, 9),new Asientos(6, 10),new Asientos(6, 11),new Asientos(6, 12),new Asientos(6, 13),new Asientos(6, 14),new Asientos(6, 15),new Asientos(6, 16),new Asientos(6, 17),new Asientos(6, 18),new Asientos(6, 19),new Asientos(6, 20),new Asientos(6, 21),new Asientos(6, 22),new Asientos(6, 23),new Asientos(6, 24),new Asientos(6, 25),new Asientos(6, 26),new Asientos(6, 27),new Asientos(6, 28),new Asientos(6, 29),new Asientos(6, 30),new Asientos(6, 31),new Asientos(6, 32),new Asientos(6, 33),new Asientos(6, 34),null,null,null},
            {null,null,null,new Asientos(7, 3),new Asientos(7, 4),new Asientos(7, 5),new Asientos(7, 6),new Asientos(7, 7),new Asientos(7, 8),new Asientos(7, 9),new Asientos(7, 10),new Asientos(7, 11),new Asientos(7, 12),new Asientos(7, 13),new Asientos(7, 14),new Asientos(7, 15),new Asientos(7, 16),new Asientos(7, 17),new Asientos(7, 18),new Asientos(7, 19),new Asientos(7, 20),new Asientos(7, 21),new Asientos(7, 22),new Asientos(7, 23),new Asientos(7, 24),new Asientos(7, 25),new Asientos(7, 26),new Asientos(7, 27),new Asientos(7, 28),new Asientos(7, 29),new Asientos(7, 30),new Asientos(7, 31),new Asientos(7, 32),new Asientos(7, 33),new Asientos(7, 34),null,null,null},
            {null,null,new Asientos(8, 2),new Asientos(8, 3),new Asientos(8, 4),new Asientos(8, 5),new Asientos(8, 6),new Asientos(8, 7),new Asientos(8, 8),new Asientos(8, 9),new Asientos(8, 10),new Asientos(8, 11),new Asientos(8, 12),new Asientos(8, 13),new Asientos(8, 14),new Asientos(8, 15),new Asientos(8, 16),new Asientos(8, 17),new Asientos(8, 18),new Asientos(8, 19),new Asientos(8, 20),new Asientos(8, 21),new Asientos(8, 22),new Asientos(8, 23),new Asientos(8, 24),new Asientos(8, 25),new Asientos(8, 26),new Asientos(8, 27),new Asientos(8, 28),new Asientos(8, 29),new Asientos(8, 30),new Asientos(8, 31),new Asientos(8, 32),new Asientos(8, 33),new Asientos(8, 34),new Asientos(8, 35),null,null},
            {null,new Asientos(9, 1),new Asientos(9, 2),new Asientos(9, 3),new Asientos(9, 4),new Asientos(9, 5),new Asientos(9, 6),new Asientos(9, 7),new Asientos(9, 8),new Asientos(9, 9),new Asientos(9, 10),new Asientos(9, 11),new Asientos(9, 12),new Asientos(9, 13),new Asientos(9, 14),new Asientos(9, 15),new Asientos(9, 16),new Asientos(9, 17),new Asientos(9, 18),new Asientos(9, 19),new Asientos(9, 20),new Asientos(9, 21),new Asientos(9, 22),new Asientos(9, 23),new Asientos(9, 24),new Asientos(9, 25),new Asientos(9, 26),new Asientos(9, 27),new Asientos(9, 28),new Asientos(9, 29),new Asientos(9, 30),new Asientos(9, 31),new Asientos(9, 32),new Asientos(9, 33),new Asientos(9, 34),new Asientos(9, 35),new Asientos(9, 36),null},
            {null,new Asientos(10, 1),new Asientos(10, 2),new Asientos(10, 3),new Asientos(10, 4),new Asientos(10, 5),new Asientos(10, 6),new Asientos(10, 7),new Asientos(10, 8),new Asientos(10, 9),new Asientos(10, 10),new Asientos(10, 11),new Asientos(10, 12),new Asientos(10, 13),new Asientos(10, 14),new Asientos(10, 15),new Asientos(10, 16),new Asientos(10, 17),new Asientos(10, 18),new Asientos(10, 19),new Asientos(10, 20),new Asientos(10, 21),new Asientos(10, 22),new Asientos(10, 23),new Asientos(10, 24),new Asientos(10, 25),new Asientos(10, 26),new Asientos(10, 27),new Asientos(10, 28),new Asientos(10, 29),new Asientos(10, 30),new Asientos(10, 31),new Asientos(10, 32),new Asientos(10, 33),new Asientos(10, 34),new Asientos(10, 35),new Asientos(10, 36),null},
            {new Asientos(11, 0),new Asientos(11, 1),new Asientos(11, 2),new Asientos(11, 3),new Asientos(11, 4),new Asientos(11, 5),new Asientos(11, 6),new Asientos(11, 7),new Asientos(11, 8),new Asientos(11, 9),new Asientos(11, 10),new Asientos(11, 11),new Asientos(11, 12),new Asientos(11, 13),new Asientos(11, 14),new Asientos(11, 15),new Asientos(11, 16),new Asientos(11, 17),new Asientos(11, 18),new Asientos(11, 19),new Asientos(11, 20),new Asientos(11, 21),new Asientos(11, 22),new Asientos(11, 23),new Asientos(11, 24),new Asientos(11, 25),new Asientos(11, 26),new Asientos(11, 27),new Asientos(11, 28),new Asientos(11, 29),new Asientos(11, 30),new Asientos(11, 31),new Asientos(11, 32),new Asientos(11, 33),new Asientos(11, 34),new Asientos(11, 35),new Asientos(11, 36),new Asientos(11, 37)},
            {new Asientos(12, 0),new Asientos(12, 1),new Asientos(12, 2),new Asientos(12, 3),new Asientos(12, 4),new Asientos(12, 5),new Asientos(12, 6),new Asientos(12, 7),new Asientos(12, 8),new Asientos(12, 9),new Asientos(12, 10),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(12, 27),new Asientos(12, 28),new Asientos(12, 29),new Asientos(12, 30),new Asientos(12, 31),new Asientos(12, 32),new Asientos(12, 33),new Asientos(12, 34),new Asientos(12, 35),new Asientos(12, 36),new Asientos(12, 37)},
            {new Asientos(13, 0),new Asientos(13, 1),new Asientos(13, 2),new Asientos(13, 3),new Asientos(13, 4),new Asientos(13, 5),new Asientos(13, 6),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(13, 31),new Asientos(13, 32),new Asientos(13, 33),new Asientos(13, 34),new Asientos(13, 35),new Asientos(13, 36),new Asientos(13, 37)},
            {new Asientos(14, 0),new Asientos(14, 1),new Asientos(14, 2),new Asientos(14, 3),new Asientos(14, 4),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(14, 33),new Asientos(14, 34),new Asientos(14, 35),new Asientos(14, 36),new Asientos(14, 37)},
            {new Asientos(15, 0),new Asientos(15, 1),new Asientos(15, 2),new Asientos(15, 3),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(15, 34),new Asientos(15, 35),new Asientos(15, 36),new Asientos(15, 37)},
            {new Asientos(16, 0),new Asientos(16, 1),new Asientos(16, 2),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(16, 35),new Asientos(16, 36),new Asientos(16, 37)},
            {null,new Asientos(17, 1),new Asientos(17, 2),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(17, 35),new Asientos(17, 36),null},
        };*/

        /*matriz = new Asientos[][]{
            {null,new Asientos(0, 1),new Asientos(0, 2),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(0, 35),new Asientos(0, 36),null},
            {new Asientos(1, 0),new Asientos(1, 1),new Asientos(1, 2),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(1, 35),new Asientos(1, 36),new Asientos(1, 37)},
            {new Asientos(2, 0),new Asientos(2, 1),new Asientos(2, 2),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(2, 35),new Asientos(2, 36),new Asientos(2, 37)},
            {new Asientos(3, 0),new Asientos(3, 1),new Asientos(3, 2),new Asientos(3, 3),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(3, 34),new Asientos(3, 35),new Asientos(3, 36),new Asientos(3, 37)},
            {new Asientos(4, 0),new Asientos(4, 1),new Asientos(4, 2),new Asientos(4, 3),new Asientos(4, 4),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(4, 33),new Asientos(4, 34),new Asientos(4, 35),new Asientos(4, 36),new Asientos(4, 37)},
            {new Asientos(5, 0),new Asientos(5, 1),new Asientos(5, 2),new Asientos(5, 3),new Asientos(5, 4),new Asientos(5, 5),new Asientos(5, 6),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(5, 31),new Asientos(5, 32),new Asientos(5, 33),new Asientos(5, 34),new Asientos(5, 35),new Asientos(5, 36),new Asientos(5, 37)},
            {new Asientos(6, 0),new Asientos(6, 1),new Asientos(6, 2),new Asientos(6, 3),new Asientos(6, 4),new Asientos(6, 5),new Asientos(6, 6),new Asientos(6, 7),new Asientos(6, 8),new Asientos(6, 9),new Asientos(6, 10),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Asientos(6, 27),new Asientos(6, 28),new Asientos(6, 29),new Asientos(6, 30),new Asientos(6, 31),new Asientos(6, 32),new Asientos(6, 33),new Asientos(6, 34),new Asientos(6, 35),new Asientos(6, 36),new Asientos(6, 37)},
            {new Asientos(7, 0),new Asientos(7, 1),new Asientos(7, 2),new Asientos(7, 3),new Asientos(7, 4),new Asientos(7, 5),new Asientos(7, 6),new Asientos(7, 7),new Asientos(7, 8),new Asientos(7, 9),new Asientos(7, 10),new Asientos(7, 11),new Asientos(7, 12),new Asientos(7, 13),new Asientos(7, 14),new Asientos(7, 15),new Asientos(7, 16),new Asientos(7, 17),new Asientos(7, 18),new Asientos(7, 19),new Asientos(7, 20),new Asientos(7, 21),new Asientos(7, 22),new Asientos(7, 23),new Asientos(7, 24),new Asientos(7, 25),new Asientos(7, 26),new Asientos(7, 27),new Asientos(7, 28),new Asientos(7, 29),new Asientos(7, 30),new Asientos(7, 31),new Asientos(7, 32),new Asientos(7, 33),new Asientos(7, 34),new Asientos(7, 35),new Asientos(7, 36),new Asientos(7, 37)},
            {new Asientos(8, 0),new Asientos(8, 1),new Asientos(8, 2),new Asientos(8, 3),new Asientos(8, 4),new Asientos(8, 5),new Asientos(8, 6),new Asientos(8, 7),new Asientos(8, 8),new Asientos(8, 9),new Asientos(8, 10),new Asientos(8, 11),new Asientos(8, 12),new Asientos(8, 13),new Asientos(8, 14),new Asientos(8, 15),new Asientos(8, 16),new Asientos(8, 17),new Asientos(8, 18),new Asientos(8, 19),new Asientos(8, 20),new Asientos(8, 21),new Asientos(8, 22),new Asientos(8, 23),new Asientos(8, 24),new Asientos(8, 25),new Asientos(8, 26),new Asientos(8, 27),new Asientos(8, 28),new Asientos(8, 29),new Asientos(8, 30),new Asientos(8, 31),new Asientos(8, 32),new Asientos(8, 33),new Asientos(8, 34),new Asientos(8, 35),new Asientos(8, 36),null},
            {null,new Asientos(9, 1),new Asientos(9, 2),new Asientos(9, 3),new Asientos(9, 4),new Asientos(9, 5),new Asientos(9, 6),new Asientos(9, 7),new Asientos(9, 8),new Asientos(9, 9),new Asientos(9, 10),new Asientos(9, 11),new Asientos(9, 12),new Asientos(9, 13),new Asientos(9, 14),new Asientos(9, 15),new Asientos(9, 16),new Asientos(9, 17),new Asientos(9, 18),new Asientos(9, 19),new Asientos(9, 20),new Asientos(9, 21),new Asientos(9, 22),new Asientos(9, 23),new Asientos(9, 24),new Asientos(9, 25),new Asientos(9, 26),new Asientos(9, 27),new Asientos(9, 28),new Asientos(9, 29),new Asientos(9, 30),new Asientos(9, 31),new Asientos(9, 32),new Asientos(9, 33),new Asientos(9, 34),new Asientos(9, 35),new Asientos(9, 36),null},
            {null,null,new Asientos(10, 2),new Asientos(10, 3),new Asientos(10, 4),new Asientos(10, 5),new Asientos(10, 6),new Asientos(10, 7),new Asientos(10, 8),new Asientos(10, 9),new Asientos(10, 10),new Asientos(10, 11),new Asientos(10, 12),new Asientos(10, 13),new Asientos(10, 14),new Asientos(10, 15),new Asientos(10, 16),new Asientos(10, 17),new Asientos(10, 18),new Asientos(10, 19),new Asientos(10, 20),new Asientos(10, 21),new Asientos(10, 22),new Asientos(10, 23),new Asientos(10, 24),new Asientos(10, 25),new Asientos(10, 26),new Asientos(10, 27),new Asientos(10, 28),new Asientos(10, 29),new Asientos(10, 30),new Asientos(10, 31),new Asientos(10, 32),new Asientos(10, 33),new Asientos(10, 34),new Asientos(10, 35),null,null},
            {null,null,null,new Asientos(11, 3),new Asientos(11, 4),new Asientos(11, 5),new Asientos(11, 6),new Asientos(11, 7),new Asientos(11, 8),new Asientos(11, 9),new Asientos(11, 10),new Asientos(11, 11),new Asientos(11, 12),new Asientos(11, 13),new Asientos(11, 14),new Asientos(11, 15),new Asientos(11, 16),new Asientos(11, 17),new Asientos(11, 18),new Asientos(11, 19),new Asientos(11, 20),new Asientos(11, 21),new Asientos(11, 22),new Asientos(11, 23),new Asientos(11, 24),new Asientos(11, 25),new Asientos(11, 26),new Asientos(11, 27),new Asientos(11, 28),new Asientos(11, 29),new Asientos(11, 30),new Asientos(11, 31),new Asientos(11, 32),new Asientos(11, 33),new Asientos(11, 34),null,null,null},
            {null,null,null,new Asientos(12, 3),new Asientos(12, 4),new Asientos(12, 5),new Asientos(12, 6),new Asientos(12, 7),new Asientos(12, 8),new Asientos(12, 9),new Asientos(12, 10),new Asientos(12, 11),new Asientos(12, 12),new Asientos(12, 13),new Asientos(12, 14),new Asientos(12, 15),new Asientos(12, 16),new Asientos(12, 17),new Asientos(12, 18),new Asientos(12, 19),new Asientos(12, 20),new Asientos(12, 21),new Asientos(12, 22),new Asientos(12, 23),new Asientos(12, 24),new Asientos(12, 25),new Asientos(12, 26),new Asientos(12, 27),new Asientos(12, 28),new Asientos(12, 29),new Asientos(12, 30),new Asientos(12, 31),new Asientos(12, 32),new Asientos(12, 33),new Asientos(12, 34),null,null,null},
            {null,null,null,null,new Asientos(13, 4),new Asientos(13, 5),new Asientos(13, 6),new Asientos(13, 7),new Asientos(13, 8),new Asientos(13, 9),new Asientos(13, 10),new Asientos(13, 11),new Asientos(13, 12),new Asientos(13, 13),new Asientos(13, 14),new Asientos(13, 15),new Asientos(13, 16),new Asientos(13, 17),new Asientos(13, 18),new Asientos(13, 19),new Asientos(13, 20),new Asientos(13, 21),new Asientos(13, 22),new Asientos(13, 23),new Asientos(13, 24),new Asientos(13, 25),new Asientos(13, 26),new Asientos(13, 27),new Asientos(13, 28),new Asientos(13, 29),new Asientos(13, 30),new Asientos(13, 31),new Asientos(13, 32),new Asientos(13, 33),null,null,null,null},
            {null,null,null,null,null,new Asientos(14, 5),new Asientos(14, 6),new Asientos(14, 7),new Asientos(14, 8),new Asientos(14, 9),new Asientos(14, 10),new Asientos(14, 11),new Asientos(14, 12),new Asientos(14, 13),new Asientos(14, 14),new Asientos(14, 15),new Asientos(14, 16),new Asientos(14, 17),new Asientos(14, 18),new Asientos(14, 19),new Asientos(14, 20),new Asientos(14, 21),new Asientos(14, 22),new Asientos(14, 23),new Asientos(14, 24),new Asientos(14, 25),new Asientos(14, 26),new Asientos(14, 27),new Asientos(14, 28),new Asientos(14, 29),new Asientos(14, 30),new Asientos(14, 31),new Asientos(14, 32),null,null,null,null,null},
            {null,null,null,null,null,null,null,new Asientos(15, 7),new Asientos(15, 8),new Asientos(15, 9),new Asientos(15, 10),new Asientos(15, 11),new Asientos(15, 12),new Asientos(15, 13),new Asientos(15, 14),new Asientos(15, 15),new Asientos(15, 16),new Asientos(15, 17),new Asientos(15, 18),new Asientos(15, 19),new Asientos(15, 20),new Asientos(15, 21),new Asientos(15, 22),new Asientos(15, 23),new Asientos(15, 24),new Asientos(15, 25),new Asientos(15, 26),new Asientos(15, 27),new Asientos(15, 28),new Asientos(15, 29),new Asientos(15, 30),null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,new Asientos(16, 8),new Asientos(16, 9),new Asientos(16, 10),new Asientos(16, 11),new Asientos(16, 12),new Asientos(16, 13),new Asientos(16, 14),new Asientos(16, 15),new Asientos(16, 16),new Asientos(16, 17),new Asientos(16, 18),new Asientos(16, 19),new Asientos(16, 20),new Asientos(16, 21),new Asientos(16, 22),new Asientos(16, 23),new Asientos(16, 24),new Asientos(16, 25),new Asientos(16, 26),new Asientos(16, 27),new Asientos(16, 28),new Asientos(16, 29),null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,new Asientos(17, 11),new Asientos(17, 12),new Asientos(17, 13),new Asientos(17, 14),new Asientos(17, 15),new Asientos(17, 16),new Asientos(17, 17),new Asientos(17, 18),new Asientos(17, 19),new Asientos(17, 20),new Asientos(17, 21),new Asientos(17, 22),new Asientos(17, 23),new Asientos(17, 24),new Asientos(17, 25),new Asientos(17, 26),null,null,null,null,null,null,null,null,null,null,null},
        };*/
        
       matriz = new Asientos[FILAS][COLUMNAS];
        
        
        
        
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
