package GUI;

import javax.swing.*;
import java.awt.*;

public class Registration extends JFrame {

    // Paleta de Colores
    static Color VerdeB = new Color(19, 50, 21);
    static Color BeigeB = new Color(243, 232, 211);

    // Campos de Texto (Atributos de clase)
    public JTextField txtUser;
    public JTextField txtPassword;
    public JTextField txtNombre;

    // Botones (Atributos de clase)
    public JButton btnGuardarUsr;
    public JButton btnIniciarSesion;
    public JButton LimpiarCampos;

    // Variables de control de dimensiones
    static int ejeY = 70;

    public Registration() {
        setTitle("Registro");
        setSize(700, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(1, 2));
    }

    public static void Ajustar(JLabel Campo, JPanel panel){
        Campo.setBounds(60, ejeY, 250, 20);
        Campo.setFont(new Font("Arial", Font.BOLD, 14));
        Campo.setForeground(VerdeB);
        panel.add(Campo);
        ejeY += 20;
    }

    public static void Ajustar(JTextField Campo, JPanel panel){
        Campo.setBounds(60, ejeY, 250, 35); // Altura 35 para igualar al Login
        panel.add(Campo);
        ejeY += 45; // Espaciado entre bloques de texto
    }

    public static void AjustarBotones(JButton boton){
        boton.setBackground(VerdeB);
        boton.setForeground(BeigeB);
        boton.setFocusable(false);
    }

    public void Contenedor() {
        // --- PANEL IZQUIERDO (Formulario Beige) ---
        JPanel PanelLeft = new JPanel();
        PanelLeft.setBackground(BeigeB);
        PanelLeft.setLayout(null);

        JLabel lblTitulo = new JLabel("Crear Cuenta");
        lblTitulo.setForeground(VerdeB);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(60, 25, 200, 30);
        PanelLeft.add(lblTitulo);

        // Inicialización de campos usando los objetos de clase
        JLabel lblUser = new JLabel("Usuario:");
        Ajustar(lblUser, PanelLeft);
        txtUser = new JTextField("Ingresa el Usuario");
        Ajustar(txtUser, PanelLeft);

        JLabel lblNombre = new JLabel("Nombre:");
        Ajustar(lblNombre, PanelLeft);
        txtNombre = new JTextField("Ingresa el Nombre");
        Ajustar(txtNombre, PanelLeft);

        JLabel lblPassword = new JLabel("Contraseña:");
        Ajustar(lblPassword, PanelLeft);
        txtPassword = new JTextField("Ingresa la Contraseña");
        Ajustar(txtPassword, PanelLeft);

        // Botón Limpiar (Estilo "borrar" del Login)
        LimpiarCampos = new JButton("Limpiar Campos");
        LimpiarCampos.setBounds(60, 275, 180, 20);
        AjustarBotones(LimpiarCampos);
        PanelLeft.add(LimpiarCampos);

        // Botón Crear (Estilo "btnLogin" del Login)
        btnIniciarSesion = new JButton("Registrarse");
        btnIniciarSesion.setBounds(110, 310, 150, 40);
        AjustarBotones(btnIniciarSesion);
        PanelLeft.add(btnIniciarSesion);

        add(PanelLeft);

        // --- PANEL DERECHO (Bienvenida Verde) ---
        JPanel PanelRight = new JPanel();
        PanelRight.setBackground(VerdeB);
        PanelRight.setLayout(new GridBagLayout());

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(VerdeB);

        JLabel lblWelcome = new JLabel("¿Ya tienes cuenta?");
        lblWelcome.setForeground(BeigeB);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 22));
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblText = new JLabel("Regresa al inicio para entrar");
        lblText.setForeground(BeigeB);
        lblText.setFont(new Font("Arial", Font.PLAIN, 14));
        lblText.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botón IR AL LOGIN (Estilo "btnIgnore" del Login)
        btnGuardarUsr = new JButton("INICIAR SESIÓN");
        btnGuardarUsr.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardarUsr.setBackground(BeigeB);
        btnGuardarUsr.setForeground(VerdeB);
        btnGuardarUsr.setPreferredSize(new Dimension(150, 30));

        innerPanel.add(lblWelcome);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        innerPanel.add(lblText);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerPanel.add(btnGuardarUsr);

        PanelRight.add(innerPanel);
        add(PanelRight);
    }

    public static void main(String[] args) {
        Registration registration = new Registration();
        registration.Contenedor();
        registration.setVisible(true);
    }
}