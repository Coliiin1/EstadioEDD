package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;

public class MenuPU extends JFrame {

    static final Color VerdeB       = new Color(76, 175, 80);
    static final Color BeigeB       = new Color(243, 232, 211);
    static final Color VerdeTarjeta = new Color(27, 94, 32);
    private Login login = new Login();

    static final String txtInformacion = "";

    public JButton btnEventoPrincipal, btnInfoGeneral, btnIngresa, btnComprar;
    public JPanel  panelImagen, panelLateral;

    private JPanel margenCentral;

    public MenuPU() {
        setTitle("Menu Principal Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 700));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(getToolkit().getImage(getClass().getResource("/Imagenes/Logo.jpg")));
        addComponentListener(new ComponentAdapter() {
            @Override public void componentResized(ComponentEvent e) { revalidate(); repaint(); }
        });
        setLayout(new BorderLayout());
        getContentPane().setBackground(BeigeB);
    }

    public void ContenedorPrincipal() {

        // --- NAVBAR ---
        JPanel panelFondo = panelRedondeado(VerdeTarjeta, 40, new BorderLayout());
        panelFondo.setBorder(new EmptyBorder(10, 15, 10, 15));

        Image imagenLogo = new ImageIcon("src/Imagenes/Logo.jpg").getImage();
        JLabel lblLogo = new JLabel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setClip(new Ellipse2D.Float(0, 0, getWidth(), getHeight()));
                g2.drawImage(imagenLogo, 0, 0, getWidth(), getHeight(), this);
                g2.dispose();
            }
        };
        lblLogo.setPreferredSize(new Dimension(50, 50));
        lblLogo.setBorder(new RoundedBorder(50, VerdeB, 4));

        JLabel lblNombre = new JLabel("Chivo Cordoba UAEMex");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 18));
        lblNombre.setForeground(Color.WHITE);

        JPanel izquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        izquierda.setOpaque(false);
        izquierda.add(lblLogo);
        izquierda.add(lblNombre);

        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 15, 0));
        panelBotones.setOpaque(false);
        panelBotones.setPreferredSize(new Dimension(650, 45));

        btnEventoPrincipal = crearBotonNav("Evento Principal");
        btnInfoGeneral     = crearBotonNav("Informacion General");
        btnIngresa         = crearBotonNav("Ingresa");

        panelBotones.add(btnEventoPrincipal);
        panelBotones.add(btnInfoGeneral);
        panelBotones.add(btnIngresa);

        panelFondo.add(izquierda, BorderLayout.WEST);
        panelFondo.add(panelBotones, BorderLayout.CENTER);

        JPanel navPanel = wrapConMargen(panelFondo, 15, 15, 10, 15);
        navPanel.setBackground(BeigeB);

        margenCentral = new JPanel(new BorderLayout());
        margenCentral.setBackground(BeigeB);

        add(navPanel, BorderLayout.NORTH);
        add(margenCentral, BorderLayout.CENTER);

        mostrarContenido(ContenedorPPrincipal());

        btnEventoPrincipal.addActionListener(e -> mostrarContenido(ContenedorPPrincipal()));
        btnInfoGeneral.addActionListener(e -> mostrarContenido(ContenedorInfo()));
        btnIngresa.addActionListener(e -> {
            login.Contenedor();
            login.setVisible(true);
        });
    }

    public void mostrarContenido(JPanel contenido) {
        margenCentral.removeAll();
        margenCentral.add(contenido, BorderLayout.CENTER);
        margenCentral.revalidate();
        margenCentral.repaint();
    }

    public JPanel ContenedorPPrincipal() {

        Image imagenEvento = new ImageIcon("src/Imagenes/Logo.jpg").getImage();
        panelImagen = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setClip(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 40, 40));
                g2.drawImage(imagenEvento, 0, 0, getWidth(), getHeight(), this);
                g2.dispose();
            }
        };
        panelImagen.setOpaque(false);
        panelImagen.setBorder(new RoundedBorder(40, VerdeTarjeta, 4));

        panelLateral = panelRedondeado(VerdeB, 40, new BorderLayout());
        panelLateral.setPreferredSize(new Dimension(320, 0));
        panelLateral.setBorder(new RoundedBorder(40, VerdeTarjeta, 4));

        btnComprar = crearBotonRedondeado("Comprar", VerdeTarjeta);
        btnComprar.setFont(new Font("Arial", Font.BOLD, 18));
        btnComprar.setPreferredSize(new Dimension(240, 50));
        btnComprar.addActionListener(e -> ContenedorComprar());

        JPanel bottomLateral = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        bottomLateral.setOpaque(false);
        bottomLateral.add(btnComprar);
        panelLateral.add(bottomLateral, BorderLayout.SOUTH);

        JPanel contenedor = panelRedondeado(new Color(230, 220, 200), 45, new BorderLayout(15, 0));
        contenedor.setBorder(new EmptyBorder(15, 15, 15, 15));
        contenedor.add(panelImagen, BorderLayout.CENTER);
        contenedor.add(panelLateral, BorderLayout.EAST);

        JPanel margen = wrapConMargen(contenedor, 0, 15, 15, 15);
        margen.setBackground(BeigeB);
        return margen;
    }

    public JPanel ContenedorInfo() {

        // --- PANEL IZQUIERDO con JTextArea scrolleable ---
        JPanel panelIzquierdo = panelRedondeado(BeigeB, 40, new BorderLayout());
        panelIzquierdo.setBorder(new RoundedBorder(40, VerdeTarjeta, 4));

        JTextArea textInfo = new JTextArea();
        textInfo.setFont(new Font("Arial", Font.PLAIN, 30));
        textInfo.setForeground(Color.BLACK);
        textInfo.setOpaque(false);          // fondo transparente: se ve el BeigeB del panel
        textInfo.setLineWrap(true);         // texto se adapta al ancho del panel
        textInfo.setWrapStyleWord(true);    // corte por palabra, no por letra
        textInfo.setEditable(false);
        textInfo.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        //Informacion sobre el Estadio
        
        textInfo.setText("El Estadio Universitario Alberto “Chivo” Córdoba es uno de los recintos deportivos más representativos de la Universidad Autónoma del Estado de México (UAEMéx). Se encuentra ubicado dentro de Ciudad Universitaria, en Toluca, Estado de México, y es utilizado principalmente para eventos de fútbol, atletismo y fútbol americano. Fue inaugurado el 5 de noviembre de 1964 y cuenta con una capacidad aproximada para 32,000 espectadores.\n" +
"\n" +
"El estadio recibe su nombre en honor a Alberto “Chivo” Córdoba, destacado entrenador de fútbol americano universitario que impulsó el desarrollo deportivo de la UAEMéx. A lo largo de su historia, este recinto ha sido sede de importantes competencias universitarias y partidos profesionales, incluyendo encuentros de los Potros UAEM y del Deportivo Toluca durante la remodelación del Estadio Nemesio Diez en 2016.\n" +
"\n" +
"Uno de los aspectos más llamativos del estadio es su diseño arquitectónico y artístico. En la parte de las gradas se encuentra el mural “Aratmósfera”, creado por el reconocido artista mexiquense Leopoldo Flores entre 1974 y 1976. Esta obra de gran tamaño convierte al estadio en uno de los recintos deportivos más originales de México y del mundo, integrando arte y deporte en un mismo espacio.\n" +
"\n" +
"El estadio también destaca por sus instalaciones deportivas, ya que cuenta con una pista de atletismo certificada, cancha de pasto natural y áreas destinadas para competencias universitarias y eventos masivos. Gracias a sus remodelaciones y mantenimiento, continúa siendo uno de los espacios deportivos más importantes del Estado de México.\n" +
"\n" +
"Ubicado sobre Paseo General Vicente Guerrero, dentro de Ciudad Universitaria de la UAEMéx, el Estadio “Chivo” Córdoba forma parte de la identidad universitaria y cultural de la institución, siendo un símbolo histórico para estudiantes, deportistas y aficionados al deporte universitario.");
        
        //Finaliza Informacion del Estadio
        
        JScrollPane scroll = new JScrollPane(textInfo);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false); // viewport transparente
        scroll.setBorder(null);                // sin borde propio del scroll
        
        panelIzquierdo.add(scroll, BorderLayout.CENTER); // ocupa todo el panel

        // --- IMÁGENES DERECHA ---
        Image imgArriba = new ImageIcon("src/Imagenes/EventoInfo1.jpg").getImage();
        JPanel panelImgArriba = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setClip(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 40, 40));
                g2.drawImage(imgArriba, 0, 0, getWidth(), getHeight(), this);
                g2.dispose();
            }
        };
        panelImgArriba.setOpaque(false);
        panelImgArriba.setBorder(new RoundedBorder(40, VerdeTarjeta, 4));

        Image imgAbajo = new ImageIcon("src/Imagenes/EventoInfo2.jpg").getImage();
        JPanel panelImgAbajo = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setClip(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 40, 40));
                g2.drawImage(imgAbajo, 0, 0, getWidth(), getHeight(), this);
                g2.dispose();
            }
        };
        panelImgAbajo.setOpaque(false);
        panelImgAbajo.setBorder(new RoundedBorder(40, VerdeTarjeta, 4));

        JPanel columnaDerecha = new JPanel(new GridLayout(2, 1, 10, 10));
        columnaDerecha.setOpaque(false);
        columnaDerecha.setPreferredSize(new Dimension(360, 0));
        columnaDerecha.add(panelImgArriba);
        columnaDerecha.add(panelImgAbajo);

        // --- CONTENEDOR ---
        JPanel contenedor = panelRedondeado(new Color(230, 220, 200), 45, new BorderLayout(15, 0));
        contenedor.setBorder(new EmptyBorder(15, 15, 15, 15));
        contenedor.add(panelIzquierdo, BorderLayout.CENTER);
        contenedor.add(columnaDerecha, BorderLayout.EAST);

        JPanel margen = wrapConMargen(contenedor, 0, 15, 15, 15);
        margen.setBackground(BeigeB);
        return margen;
    }

    public void ContenedorComprar() { }

    // --- HELPERS ---

    private JPanel panelRedondeado(Color color, int radio, LayoutManager layout) {
        JPanel p = new JPanel(layout) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        p.setOpaque(false);
        return p;
    }

    private JPanel wrapConMargen(JPanel inner, int top, int left, int bottom, int right) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(new EmptyBorder(top, left, bottom, right));
        wrapper.add(inner, BorderLayout.CENTER);
        return wrapper;
    }

    private JButton crearBotonNav(String texto) {
        JButton btn = crearBotonRedondeado(texto, VerdeB);
        btn.setFont(new Font("Arial", Font.BOLD, 15));
        return btn;
    }

    private JButton crearBotonRedondeado(String texto, Color color) {
        JButton btn = new JButton(texto) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
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
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(color.brighter()); }
            @Override public void mouseExited(MouseEvent e)  { btn.setBackground(color); }
        });
        return btn;
    }

    class RoundedBorder extends AbstractBorder {
        private final int radius, thickness;
        private final Color color;

        RoundedBorder(int radius, Color color, int thickness) {
            this.radius = radius; this.color = color; this.thickness = thickness;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            g2.draw(new RoundRectangle2D.Double(x + 1, y + 1, width - 3, height - 3, radius, radius));
            g2.dispose();
        }

        @Override public Insets getBorderInsets(Component c) { return new Insets(10, 10, 10, 10); }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPU ventana = new MenuPU();
            ventana.ContenedorPrincipal();
            ventana.setVisible(true);
        });
    }
}