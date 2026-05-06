package GUI;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Login extends JFrame{
    
    Color VerdeB = new Color(19, 50, 21); 
    Color BeigeB = new Color(243, 232, 211); 
    public JButton btnLogin;
    public JButton btnSignup;
    public JButton btnIgnore;
    private final String usuario = "Admin";
    private final String contra = "12345";
    
    public JButton borrar;
    
    public JTextField txtUser;
    public JPasswordField txtPass;
    
    public JLabel error;
    public Login() {
        setTitle("Inicio de Sesión");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(getToolkit().getImage(getClass().getResource("/Imagenes/Logo.png")));
        setLayout(new GridLayout(1, 2)); // dos paneles lado a lado
    }
    
    public boolean Entrar(String User, String pasw){
        if (User.equals(usuario) && pasw.equals(contra)) {
            txtUser.setText("");
            txtPass.setText("");
            return true;
        }
        JOptionPane.showMessageDialog(null, "Credenciales Invalidas");
        return false;
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
        
        txtUser = new JTextField("Usuario del Administrador");
        txtUser.setBounds(60, 80, 250, 35);
        txtUser.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtUser.getText().contains("Usuario del Administrador")) {
                    txtUser.setText("");// Elimina el texto que teniamos de busca evento
                }
                txtUser.setForeground(Color.GRAY);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtUser.getText().isEmpty()) {
                    txtUser.setText("Usuario del Administrador");// restaura el texto de la barra de busqueda
                    txtUser.setForeground(Color.GRAY);
                }
            }
        });
        txtUser.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtPass.requestFocus(); // Pasa el cursor a la contraseña
                }
            }
        });
        loginPanel.add(txtUser);

        borrar = new JButton("Limpiar Campos");
        borrar.setBounds(60, 180, 130, 20);
        borrar.setBackground(VerdeB);
        borrar.setForeground(BeigeB);
        
        borrar.addActionListener(e -> { 
            txtUser.setText("");
            txtPass.setText("");
        });
        
        loginPanel.add(borrar);
        txtPass = new JPasswordField("Contraseña");
        txtPass.setForeground(Color.GRAY);
        txtPass.setBounds(60, 130, 250, 35);
        txtPass.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtPass.getText().contains("Contraseña")) {
                    txtPass.setText("");// Elimina el texto que teniamos de busca evento
                }
                txtPass.setForeground(Color.GRAY);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtPass.getText().isEmpty()) {
                    txtPass.setText("Contraseña");// restaura el texto de la barra de busqueda
                    txtPass.setForeground(Color.GRAY);
                }
            }
        });
        
        txtPass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLogin.doClick(); 
                }
            }
        });
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
        ImageIcon LogoUAEMEX = new ImageIcon("src/Imagenes/LogoUAEMEX.png");
        JLabel lblLogo = new JLabel();
        lblLogo.setIcon(LogoUAEMEX);
        lblLogo.setPreferredSize(new Dimension(300, 278));
        lblLogo.setBackground(VerdeB);
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

               
        innerPanel.add(lblLogo);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        rightPanel.add(innerPanel);
        
        // Agregar paneles al frame
        add(loginPanel);
        add(rightPanel);
    }
}