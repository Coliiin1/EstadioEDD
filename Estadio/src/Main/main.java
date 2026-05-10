/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import GUI.*;
import java.sql.SQLException;
/**
 *
 * @author fabri
 */
public class main {
    public static void main(String[] args) throws SQLException {
        MenuPrincipal mainPrincipal = new MenuPrincipal();
        mainPrincipal.ContenedorPrincipal();
        mainPrincipal.setVisible(true);
    }
}
