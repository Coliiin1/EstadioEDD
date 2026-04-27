package GUI;
import java.awt.*;
import javax.swing.*;

public class Login extends JFrame{
    Color VerdeB = new Color(19, 50, 21); 
    Color BeigeB = new Color(243, 232, 211); 
    public JButton btnLogin;
    public JButton btnSignup;
    public JButton btnIgnore;
    
    public JButton borrar;
    
    public JTextField txtUser;
    public JTextField txtPass;
    
    public JLabel error;
    public Login() {
        setTitle("Inicio de Sesión");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(1, 2)); // dos paneles lado a lado
    }
    
    public void Contenedor(){
        // Panel Izquierdo (Login)
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(BeigeB);

        JLabel lblLogin = new JLabel("Inicio de Sesion");
        lblLogin.setForeground(VerdeB);
        lblLogin.setFont(new Font("Arial", Font.BOLD, 16));
        lblLogin.setBounds(60, 30, 200, 30);
        loginPanel.add(lblLogin);
        
        txtUser = new JTextField("Ingresa tu Usuario");
        txtUser.setBounds(60, 80, 250, 35);
        loginPanel.add(txtUser);

        borrar = new JButton("Limpiar Campos");
        borrar.setBounds(60, 180, 130, 20);
        borrar.setBackground(VerdeB);
        borrar.setForeground(BeigeB);
        loginPanel.add(borrar);
        txtPass = new JTextField("Ingresa la Contraseña");
        txtPass.setBounds(60, 130, 250, 35);
        loginPanel.add(txtPass);

        btnLogin = new JButton("Iniciar Sesion");
        btnLogin.setBounds(110, 230, 150, 40);
        btnLogin.setBackground(VerdeB);
        btnLogin.setForeground(BeigeB);
        loginPanel.add(btnLogin);
        
        error=new JLabel("");
        error.setBounds(110, 200, 150, 40);
        error.setForeground(new Color(250,0,0));
        loginPanel.add(error);

        // Panel Derecho (Bienvenida)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(VerdeB);
        rightPanel.setLayout(new GridBagLayout());

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(VerdeB);
        
        JLabel lblWelcome = new JLabel("Bienvenido Usuario!");
        lblWelcome.setForeground(BeigeB);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 22));
        lblWelcome.setBackground(VerdeB);
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblText = new JLabel("Inicia Sesion o Crea una Cuenta :D");
        lblText.setForeground(BeigeB);
        lblText.setFont(new Font("Arial", Font.PLAIN, 14));
        lblText.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnIgnore = new JButton("CREAR CEUNTA ");
        btnIgnore.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIgnore.setBackground(BeigeB);
        
        innerPanel.add(lblWelcome);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        innerPanel.add(lblText);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerPanel.add(btnIgnore);

        rightPanel.add(innerPanel);

        // Agregar paneles al frame
        add(loginPanel);
        add(rightPanel);
    }
    
    public static void main(){
        Login n = new Login();
        n.Contenedor();
        n.setVisible(true);
    }
}