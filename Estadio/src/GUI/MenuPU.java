/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/*
* @author Alberto
 */
public class MenuPU extends JFrame {

    static Color VerdeB = new Color(76, 175, 80);
    static Color BeigeB = new Color(243, 232, 211);
    static Color TextoBtn = new Color(255, 255, 255);
    static Color VerdeTarjeta = new Color(27, 94, 32);

    public JButton btnInicio;
    public JButton btnEPopulares;
    public JButton btnIniciarSesion;
    public JTextField txtBuscar;

    public JPanel tarjeta1;
    public JPanel tarjeta2;
    public JPanel tarjeta3;

    public MenuPU() {
        setTitle("Menu Principal Usuario");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BeigeB);
    }

    public void Contenedor() {
        // Panel trasero donde van los botones de navegacion
        JPanel panelFondo = new JPanel();
        panelFondo.setBackground(VerdeTarjeta);
        panelFondo.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 8));
        panelFondo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(VerdeTarjeta, 1, true),
                new EmptyBorder(2, 4, 2, 4)));

        // Boton inicio
        btnInicio = crearBotonNav("Inicio");
        panelFondo.add(btnInicio);

        // Boton Eventos Populares
        btnEPopulares = crearBotonNav("Eventos Populares");
        panelFondo.add(btnEPopulares);

        // Boton Iniciar Sesion
        btnIniciarSesion = crearBotonNav("Iniciar Sesion");
        panelFondo.add(btnIniciarSesion);

        // Barra de busqueda
        txtBuscar = new JTextField("Buscar Evento", 18);
        txtBuscar.setFont(new Font("Arial", Font.BOLD, 14));
        txtBuscar.setForeground(Color.GRAY);
        txtBuscar.setBackground(Color.WHITE);
        txtBuscar.setPreferredSize(new Dimension(250, 36));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(4, 40, 4, 40)));
        // Limpiar el espacio al hacer clic
        // el metodo fue buscado de foros de interet :)
        txtBuscar.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (txtBuscar.getText().contains("Buscar Evento")) {
                    txtBuscar.setText("");// Elimina el texto que teniamos de busca evento
                }
                txtBuscar.setForeground(Color.BLACK);
            }

            public void focusLost(FocusEvent e) {
                if (txtBuscar.getText().isEmpty()) {
                    txtBuscar.setText("Buscar Evento");// restaura el texto de la barra de busqueda
                    txtBuscar.setForeground(Color.GRAY);
                }
            }
        });
        panelFondo.add(txtBuscar);
        //hola
        JPanel navPanel = new JPanel();
        navPanel.setBackground(BeigeB);
        navPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        navPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        navPanel.add(panelFondo);

        // Panel principal para las tarjetas del centro
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(VerdeTarjeta);
        mainPanel.setLayout(new GridLayout(1, 3, 12, 0));// 3 celdas iguales en una sola fila de 12 pixeles
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        tarjeta1 = crearTarjeta(false);// solo el panel pero sin contendio
        mainPanel.add(tarjeta1);

        tarjeta2 = crearTarjeta(false);
        mainPanel.add(tarjeta2);

        tarjeta3 = crearTarjeta(false);
        mainPanel.add(tarjeta3);

        JPanel margenPrincipal = new JPanel(new BorderLayout());
        margenPrincipal.setBackground(BeigeB);
        margenPrincipal.setBorder(new EmptyBorder(0, 12, 12, 12));
        margenPrincipal.add(mainPanel, BorderLayout.CENTER);

        add(navPanel, BorderLayout.NORTH); // barra de navegacion arriba
        add(margenPrincipal, BorderLayout.CENTER); // tarjetas en el resto de la ventana
    }

    private JButton crearBotonNav(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(VerdeB);
        // btn.setLayout(new BorderLayout());
        btn.setForeground(TextoBtn);
        btn.setFocusPainted(false);// eliminamos el contorno de foco punteado por defecto
        btn.setBorderPainted(false);// sin borde exterior del boton
        btn.setOpaque(true);// fuerza que el color de fonde se renderice
        btn.setPreferredSize(new Dimension(190, 36));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Oscurece el boton al entrar en cursor
        // Esto tambien lo saque de un video :3
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(56, 142, 60));
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(VerdeB);
            }
        });
        return btn;// retorna el boton ya configurado
    }

    private JPanel crearTarjeta(boolean conImagen) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(VerdeB);
        card.setBorder(BorderFactory.createLineBorder(VerdeB, 3, true));
        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPU ventana = new MenuPU();
            ventana.Contenedor();
            ventana.setVisible(true);
        });
    }

}
