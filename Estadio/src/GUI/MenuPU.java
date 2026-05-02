package GUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;

public class MenuPU extends JFrame {

    static Color VerdeB       = new Color(76, 175, 80);
    static Color BeigeB       = new Color(243, 232, 211);
    static Color TextoBtn     = new Color(255, 255, 255);
    static Color VerdeTarjeta = new Color(27, 94, 32);

    public JButton btnEventoPrincipal;
    public JButton btnInfoGeneral;
    public JButton btnIngresa;
    public JButton btnComprar;

    public JPanel panelImagen;
    public JPanel panelLateral;

    public MenuPU() {

        setTitle("Menu Principal Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ventana adaptable
        setMinimumSize(new Dimension(1100, 700));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        setIconImage(getToolkit().getImage(getClass().getResource("/Imagenes/Logo.jpg")));
        
        addComponentListener(new java.awt.event.ComponentAdapter() {

            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                revalidate();
                repaint();
            }
        });

        setLayout(new BorderLayout());
        getContentPane().setBackground(BeigeB);
    }

    public void Contenedor() {

        // =========================================================
        // NAVBAR
        // =========================================================

        JPanel panelFondo = new JPanel(new BorderLayout()) {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(VerdeTarjeta);

                g2.fillRoundRect(
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        40,
                        40
                );

                g2.dispose();

                super.paintComponent(g);
            }
        };

        panelFondo.setOpaque(false);
        panelFondo.setBorder(new EmptyBorder(10, 15, 10, 15));

        // =========================================================
        // PANEL IZQUIERDO NAVBAR
        // =========================================================

        JPanel izquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        izquierda.setOpaque(false);

        // =========================================================
        // LOGO REDONDEADO REAL
        // =========================================================

        ImageIcon iconoLogo = new ImageIcon("src/Imagenes/Logo.jpg");

        Image imagenLogo = iconoLogo.getImage();

        JLabel lblLogo = new JLabel() {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Recorte circular REAL
                Shape clip = new java.awt.geom.Ellipse2D.Float(
                        0,
                        0,
                        getWidth(),
                        getHeight()
                );

                g2.setClip(clip);

                // Dibujar imagen adaptada
                g2.drawImage(
                        imagenLogo,
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        this
                );

                g2.dispose();
            }
        };

        lblLogo.setPreferredSize(new Dimension(50, 50));
        lblLogo.setBorder(new RoundedBorder(50, VerdeB, 4));
        

        // =========================================================
        // TITULO
        // =========================================================

        JLabel lblNombre = new JLabel("Nombre de la Aplicacion"); //Por cambiar

        lblNombre.setFont(new Font("Arial", Font.BOLD, 18));
        lblNombre.setForeground(Color.WHITE);

        izquierda.add(lblLogo);
        izquierda.add(lblNombre);

        // =========================================================
        // PANEL BOTONES
        // =========================================================

        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 15, 0));
        panelBotones.setOpaque(false);

        btnEventoPrincipal = crearBotonNav("Evento Principal");
        btnInfoGeneral = crearBotonNav("Informacion General");
        btnIngresa = crearBotonNav("Ingresa");

        panelBotones.add(btnEventoPrincipal);
        panelBotones.add(btnInfoGeneral);
        panelBotones.add(btnIngresa);

        panelBotones.setPreferredSize(new Dimension(650, 45));

        panelFondo.add(izquierda, BorderLayout.WEST);
        panelFondo.add(panelBotones, BorderLayout.CENTER);

        JPanel navPanel = new JPanel(new BorderLayout());
        navPanel.setBackground(BeigeB);
        navPanel.setBorder(new EmptyBorder(15, 15, 10, 15));
        navPanel.add(panelFondo, BorderLayout.CENTER);

        // =========================================================
        // CONTENEDOR PRINCIPAL
        // =========================================================

        JPanel contenedor = new JPanel(new BorderLayout(15, 0)) {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(new Color(230, 220, 200));

                g2.fillRoundRect(
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        45,
                        45
                );

                g2.dispose();

                super.paintComponent(g);
            }
        };

        contenedor.setOpaque(false);
        contenedor.setBorder(new EmptyBorder(15, 15, 15, 15));

        // =========================================================
        // PANEL IMAGEN EVENTO
        // =========================================================

        // =========================================================
        // PANEL IMAGEN EVENTO
        // =========================================================

        ImageIcon eventoIcon = new ImageIcon("src/Imagenes/Logo.jpg"); //Cambiar por icono de evento

        Image imagenEvento = eventoIcon.getImage();

        panelImagen = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Bordes redondeados REALES
                Shape clip = new RoundRectangle2D.Float(
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        40,
                        40
                );

                g2.setClip(clip);

                // Dibujar imagen adaptada al panel
                g2.drawImage(
                        imagenEvento,
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        this
                );

                g2.dispose();
            }
        };

        panelImagen.setOpaque(false);
        panelImagen.setBorder(new RoundedBorder(40, VerdeTarjeta, 4));

        // =========================================================
        // PANEL LATERAL
        // =========================================================

        panelLateral = new JPanel(new BorderLayout()) {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(VerdeB);

                g2.fillRoundRect(
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        40,
                        40
                );

                g2.dispose();

                super.paintComponent(g);
            }
        };

        panelLateral.setOpaque(false);
        panelLateral.setPreferredSize(new Dimension(320, 0));
        panelLateral.setBorder(new RoundedBorder(40, VerdeTarjeta, 4));

        // =========================================================
        // BOTON COMPRAR
        // =========================================================

        btnComprar = crearBotonRedondeado("Comprar", VerdeTarjeta);

        btnComprar.setFont(new Font("Arial", Font.BOLD, 18));
        btnComprar.setPreferredSize(new Dimension(240, 50));

        JPanel bottomLateral = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 0, 15)
        );

        bottomLateral.setOpaque(false);
        bottomLateral.add(btnComprar);

        panelLateral.add(bottomLateral, BorderLayout.SOUTH);

        // =========================================================
        // AGREGAR AL CONTENEDOR
        // =========================================================

        contenedor.add(panelImagen, BorderLayout.CENTER);
        contenedor.add(panelLateral, BorderLayout.EAST);

        JPanel margen = new JPanel(new BorderLayout());

        margen.setBackground(BeigeB);
        margen.setBorder(new EmptyBorder(0, 15, 15, 15));

        margen.add(contenedor, BorderLayout.CENTER);

        add(navPanel, BorderLayout.NORTH);
        add(margen, BorderLayout.CENTER);
    }

    // =============================================================
    // BOTONES NAVBAR
    // =============================================================

    private JButton crearBotonNav(String texto) {

        JButton btn = crearBotonRedondeado(texto, VerdeB);

        btn.setFont(new Font("Arial", Font.BOLD, 15));

        return btn;
    }

    // =============================================================
    // BOTON REDONDEADO
    // =============================================================

    private JButton crearBotonRedondeado(String texto, Color color) {

        JButton btn = new JButton(texto) {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(getBackground());

                g2.fillRoundRect(
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        30,
                        30
                );

                super.paintComponent(g);

                g2.dispose();
            }
        };

        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(false);

        btn.setForeground(Color.WHITE);
        btn.setBackground(color);

        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(color.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(color);
            }
        });

        return btn;
    }

    // =============================================================
    // BORDE REDONDEADO
    // =============================================================

    class RoundedBorder extends AbstractBorder {

        private final int radius;
        private final Color color;
        private final int thickness;

        RoundedBorder(int radius, Color color, int thickness) {

            this.radius = radius;
            this.color = color;
            this.thickness = thickness;
        }

        @Override
        public void paintBorder(
                Component c,
                Graphics g,
                int x,
                int y,
                int width,
                int height
        ) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(color);

            g2.setStroke(new BasicStroke(thickness));

            g2.draw(new RoundRectangle2D.Double(
                    x + 1,
                    y + 1,
                    width - 3,
                    height - 3,
                    radius,
                    radius
            ));

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {

            return new Insets(10, 10, 10, 10);
        }
    }

    // =============================================================
    // MAIN
    // =============================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            MenuPU ventana = new MenuPU();
            ventana.Contenedor();
            ventana.setVisible(true);
        });
    }
}
