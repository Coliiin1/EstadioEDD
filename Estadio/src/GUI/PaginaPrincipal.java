/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author fabri
 */
public class PaginaPrincipal extends JFrame implements ActionListener{
    
    static Color VerdeB = new Color(19, 50, 21);
    static Color BeigeB = new Color(243, 232, 211);
    
    public PaginaPrincipal(){
        setSize(1400,800);
        setLayout(null);
        setTitle("Pagina principal");
        setLocationRelativeTo(this);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public static void main(String[] args) {
        PaginaPrincipal pg=new PaginaPrincipal();
        pg.setVisible(true);
    }
}
