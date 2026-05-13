package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;
import estadio.*;
import static estadio.Categoria.GENERAL;
import static estadio.Categoria.PREFERENCIAL;
import static estadio.Categoria.VIP;
import com.toedter.calendar.JDateChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import BaseDatos.BaseDeDatos;
import java.sql.SQLException;
import java.util.Queue;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import Archivos.GenerarPDF;

public class MenuPrincipal extends JFrame {

    static final Color VerdeB = new Color(76, 175, 80);
    static final Color BeigeB = new Color(243, 232, 211);
    static final Color VerdeTarjeta = new Color(27, 94, 32);
    static final Color COLOR_VIP = new Color(0, 0, 142);
    static final Color COLOR_PREF = new Color(120, 0, 142);
    static final Color COLOR_GEN = new Color(125, 124, 121);
    static final Color COLOR_GRATIS = new Color(236, 239, 241);
    static final Color COLOR_INACTIVO = new Color(60, 60, 60);
    long seleccionados;

    public JLabel lblTotal;

    private EstadioClass estadio;

    private JButton[][] botonesAsientos;
    private Categoria categoriaSeleccionada = Categoria.GENERAL;
    private Login login = new Login();
    private double total = 0;
    private LinkedList<Boleto> boletosComprados;
    public JButton btnEventoPrincipal, btnInfoGeneral, btnIngresa,
            btnComprar, btnSalir, btnModificar;
    public JPanel panelImagen, panelLateral;
    private boolean Admin = false;
    private JPanel margenCentral, navPanel, panelBotones;
    private JButton btnGuardarImg;
    private JButton btnGenerarReportes;
    private GenerarPDF ReportesPDF;

    public Evento eventoPrincipal;

    private DefaultTableModel modeloTabla;
    private double totalGanancias = 0.0;
    private JTextField txtGanancias;
    BaseDeDatos baseDatos;

    public MenuPrincipal() throws SQLException {
        setTitle("Menu Principal Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 700));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(getToolkit().getImage(getClass().getResource("/Imagenes/Logo.png")));
        this.estadio = new EstadioClass();
        this.boletosComprados = new LinkedList<>();
        this.eventoPrincipal = new Evento("Evento");
        this.botonesAsientos = new JButton[eventoPrincipal.estadio.getFILAS()][eventoPrincipal.estadio.getCOLUMNAS()];
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                revalidate();
                repaint();
            }
        });
        setLayout(new BorderLayout());
        getContentPane().setBackground(BeigeB);
        baseDatos = new BaseDeDatos();
        baseDatos.IniciarConexion();
        //baseDatos.GuardarAsientos(eventoPrincipal);
        baseDatos.RecuperarAsientos(eventoPrincipal);
        baseDatos.CerrarConexion();
        baseDatos.IniciarConexion();
        baseDatos.RecuperarEvento(eventoPrincipal);
        baseDatos.CerrarConexion();
        ReportesPDF = new GenerarPDF();
    }

    public void ContenedorPrincipal() {
        margenCentral = new JPanel(new BorderLayout());
        margenCentral.setBackground(BeigeB);
        actualizarNavbar();
        add(margenCentral, BorderLayout.CENTER);
        mostrarContenido(ContenedorPPrincipal());
        login.Contenedor();
    }

    public JPanel ContenedorReportes() {

        // ── Paleta de colores actualizada ──────────────────────────────────────
        Color BeigeBase = new Color(237, 232, 220);  // fondo exterior
        Color BeigeMedio = new Color(232, 224, 206);  // panel central
        Color BeigeOscuro = new Color(197, 185, 154);  // bordes beige
        Color VerdeOscuro = new Color(42, 92, 62);   // sidebar verde
        Color VerdeBoton = new Color(61, 122, 85);    // botón generar / campo ganancias
        Color VerdeTexto = new Color(200, 237, 216);  // texto sobre verde
        Color BeigeBoton = new Color(197, 185, 154);  // botón PDF
        Color TableHeader = new Color(212, 201, 174);  // cabecera tabla
        Color TablePar = new Color(237, 232, 220);
        Color TableImpar = new Color(228, 220, 202);
        Color TextoOscuro = new Color(58, 48, 32);

        // ── Imagen lateral ─────────────────────────────────────────────────────
        Image imagenEvento = new ImageIcon("src/Imagenes/ChivoCordoba1.jpeg").getImage();
        JPanel panelImg = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setClip(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 28, 28));
                g2.drawImage(imagenEvento, 0, 0, getWidth(), getHeight(), this);
                g2.dispose();
            }
        };
        panelImg.setOpaque(false);
        panelImg.setBorder(new RoundedBorder(28, VerdeBoton, 2));
        panelImg.setPreferredSize(new Dimension(172, 130));

        // ── Botón Generar Reporte ───────────────────────────────────────────────
        JButton btnGenerar = crearBotonRedondeado("Generar reporte", VerdeTarjeta);
        btnGenerar.setFont(new Font("Arial", Font.BOLD, 13));
        btnGenerar.setForeground(VerdeTexto);
        btnGenerar.setPreferredSize(new Dimension(172, 38));
        btnGenerar.addActionListener(e -> {
            baseDatos.IniciarConexion();
            eventoPrincipal.colaReportes.clear();
            baseDatos.recuperarCola(eventoPrincipal);
            baseDatos.CerrarConexion();
            cargarTablaReportes();
        });

        // ── Botón Guardar PDF ───────────────────────────────────────────────────
        JButton btnPDF = crearBotonRedondeado("Guardar como PDF", BeigeBoton);
        btnPDF.setFont(new Font("Arial", Font.BOLD, 13));
        btnPDF.setForeground(TextoOscuro);
        btnPDF.setPreferredSize(new Dimension(172, 38));
        btnPDF.addActionListener(e -> {
            baseDatos.IniciarConexion();
            baseDatos.recuperarCola(eventoPrincipal);
            for (Reporte rep : eventoPrincipal.colaReportes) {
                ReportesPDF.generarPDF(rep);
            }
            baseDatos.CerrarConexion();
            JOptionPane.showMessageDialog(null, "PDF'S Generados Correctamente");
        });

        // ── Panel inferior del sidebar ──────────────────────────────────────────
        JPanel bottomLateral = new JPanel();
        bottomLateral.setLayout(new BoxLayout(bottomLateral, BoxLayout.Y_AXIS));
        bottomLateral.setOpaque(false);
        bottomLateral.setBorder(new EmptyBorder(8, 0, 0, 0));
        btnGenerar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPDF.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomLateral.add(btnGenerar);
        bottomLateral.add(Box.createVerticalStrut(8));
        bottomLateral.add(btnPDF);

        // ── Sidebar derecho ─────────────────────────────────────────────────────
        JPanel panelLateralReporte = panelRedondeado(VerdeB, 36, new BorderLayout(0, 10));
        panelLateralReporte.setPreferredSize(new Dimension(200, 0));
        panelLateralReporte.setBorder(new CompoundBorder(
                new RoundedBorder(36, VerdeBoton, 2),
                new EmptyBorder(12, 14, 14, 14)
        ));
        panelLateralReporte.add(panelImg, BorderLayout.CENTER);
        panelLateralReporte.add(bottomLateral, BorderLayout.SOUTH);

        // ── Tabla ───────────────────────────────────────────────────────────────
        String[] columnas = {"Fecha", "Categoría", "Asientos", "Precio"};
        modeloTabla = new DefaultTableModel(columnas, 0);

        JTable tablaReporte = new JTable(modeloTabla) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                c.setBackground(row % 2 == 0 ? TablePar : TableImpar);
                c.setForeground(new Color(90, 79, 60));
                return c;
            }
        };
        tablaReporte.setRowHeight(46);
        tablaReporte.setShowGrid(false);
        tablaReporte.setIntercellSpacing(new Dimension(0, 0));
        tablaReporte.setFont(new Font("Arial", Font.PLAIN, 13));

        // Cabecera de tabla personalizada
        JTableHeader header = tablaReporte.getTableHeader();
        header.setBackground(TableHeader);
        header.setForeground(new Color(74, 63, 44));
        header.setFont(new Font("Arial", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(0, 36));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, BeigeOscuro));

        JScrollPane scrollTabla = new JScrollPane(tablaReporte);
        scrollTabla.setOpaque(false);
        scrollTabla.getViewport().setBackground(TablePar);
        scrollTabla.setBorder(new RoundedBorder(12, BeigeOscuro, 2));

        // ── Fila de ganancias ───────────────────────────────────────────────────
        JLabel lblGanancias = new JLabel("Ganancias Generadas:");
        lblGanancias.setFont(new Font("Arial", Font.BOLD, 17));
        lblGanancias.setForeground(TextoOscuro);

        txtGanancias = new JTextField("$0.00");
        txtGanancias.setBackground(VerdeTarjeta);
        txtGanancias.setForeground(VerdeTexto);
        txtGanancias.setEditable(false);
        txtGanancias.setHorizontalAlignment(JTextField.CENTER);
        txtGanancias.setFont(new Font("Arial", Font.BOLD, 15));
        txtGanancias.setPreferredSize(new Dimension(130, 36));
        txtGanancias.setBorder(new RoundedBorder(10, VerdeBoton, 0));

        JPanel panelGanancias = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 8));
        panelGanancias.setOpaque(false);
        panelGanancias.add(lblGanancias);
        panelGanancias.add(txtGanancias);

        // ── Panel central ───────────────────────────────────────────────────────
        JPanel panelCentro = panelRedondeado(BeigeMedio, 18, new BorderLayout(0, 6));
        panelCentro.setBorder(new CompoundBorder(
                new RoundedBorder(18, BeigeB, 2),
                new EmptyBorder(12, 12, 10, 12)
        ));
        panelCentro.add(scrollTabla, BorderLayout.CENTER);
        panelCentro.add(panelGanancias, BorderLayout.SOUTH);

        // ── Contenedor principal ────────────────────────────────────────────────
        JPanel contenedor = panelRedondeado(BeigeBase, 22, new BorderLayout(14, 0));
        contenedor.setBorder(new EmptyBorder(14, 14, 14, 14));
        contenedor.add(panelCentro, BorderLayout.CENTER);
        contenedor.add(panelLateralReporte, BorderLayout.EAST);

        JPanel margen = wrapConMargen(contenedor, 0, 15, 15, 15);
        margen.setBackground(BeigeBase);
        return margen;
    }

    private void cargarTablaReportes() {
        modeloTabla.setRowCount(0);
        double gananciasTotales = 0;
        Queue<Reporte> cola = eventoPrincipal.colaReportes;
        for (Reporte rep : cola) {
            String fecha = rep.getFecha().toString();
            String categoria = rep.getCategoria().toString();
            StringBuilder asientos = new StringBuilder();
            //for (Boleto b : rep.getBoletos())
            //{
            //    asientos.append(b.getNumeroAsiento()).append(" ");
            //}
            double ingreso = rep.getIngreso();
            gananciasTotales += ingreso;
            modeloTabla.addRow(new Object[]{
                fecha,
                categoria,
                rep.getNumero_boletos(),
                "$" + ingreso
            });

        }

        txtGanancias.setText("$" + gananciasTotales);
    }

    // =========================================================
    // NAVBAR DINÁMICA
    // =========================================================
    private void actualizarNavbar() {
        JPanel panelFondo = panelRedondeado(VerdeTarjeta, 40, new BorderLayout());
        panelFondo.setBorder(new EmptyBorder(10, 15, 10, 15));

        Image imagenLogo = new ImageIcon("src/Imagenes/Logo.png").getImage();
        JLabel lblLogo = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
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

        btnEventoPrincipal = crearBotonNav("Evento Principal");
        btnGenerarReportes = crearBotonNav("Reportes");
        btnInfoGeneral = crearBotonNav("Informacion General");
        btnIngresa = crearBotonNav("Ingresa");
        btnModificar = crearBotonNav("Modificar Evento");
        btnSalir = crearBotonNav("Cerrar sesion");

        if (Admin) {
            panelBotones = new JPanel(new GridLayout(1, 4, 15, 0));
            panelBotones.setOpaque(false);
            panelBotones.setPreferredSize(new Dimension(850, 45));
            panelBotones.add(btnGenerarReportes);
            panelBotones.add(btnModificar);
            panelBotones.add(btnSalir);
        } else {
            panelBotones = new JPanel(new GridLayout(1, 3, 15, 0));
            panelBotones.setOpaque(false);
            panelBotones.setPreferredSize(new Dimension(650, 45));
            panelBotones.add(btnEventoPrincipal);
            panelBotones.add(btnInfoGeneral);
            panelBotones.add(btnIngresa);
        }

        panelFondo.add(izquierda, BorderLayout.WEST);
        panelFondo.add(panelBotones, BorderLayout.CENTER);

        if (navPanel != null) {
            remove(navPanel);
        }
        navPanel = wrapConMargen(panelFondo, 15, 15, 10, 15);
        navPanel.setBackground(BeigeB);
        add(navPanel, BorderLayout.NORTH);
        revalidate();
        repaint();

        btnEventoPrincipal.addActionListener(e -> mostrarContenido(ContenedorPPrincipal()));
        btnInfoGeneral.addActionListener(e -> mostrarContenido(ContenedorInfo()));

        if (Admin) {
            btnModificar.addActionListener(e -> mostrarContenido(ContenedorModEvento()));
            btnSalir.addActionListener(e
                    -> {
                Admin = false;
                actualizarNavbar();
                mostrarContenido(ContenedorPPrincipal());
            });
            btnGenerarReportes.addActionListener(e -> mostrarContenido(ContenedorReportes()));
        } else {
            btnIngresa.addActionListener(e
                    -> {
                login.setVisible(true);
                for (ActionListener l : login.btnLogin.getActionListeners()) {
                    login.btnLogin.removeActionListener(l);
                }
                login.btnLogin.addActionListener(f
                        -> {
                    if (login.Entrar(login.txtUser.getText(), login.txtPass.getText())) {
                        login.setVisible(false);
                        Admin = true;
                        actualizarNavbar();
                        mostrarContenido(ContenedorPPrincipal());
                    }
                });
            });
        }
    }

    // =========================================================
    // CONTENEDOR PRINCIPAL (imagen evento + lateral)
    // =========================================================
    public JPanel ContenedorPPrincipal() {
        Image imagenEvento = new ImageIcon(eventoPrincipal.rutaImg).getImage();
        panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
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

        // -- Titulo del evento en un Label
        JLabel labelTitulo = new JLabel(eventoPrincipal.nombreEvento); // ajusta el campo según tu objeto
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        labelTitulo.setForeground(BeigeB);
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setBorder(new EmptyBorder(15, 10, 5, 10));

        // --- TEXTAREA para desc del evento ---
        JTextArea DescEvento = new JTextArea();
        DescEvento.setLineWrap(true);
        DescEvento.setWrapStyleWord(true);
        DescEvento.setEditable(false);
        DescEvento.setOpaque(false);
        DescEvento.setText(eventoPrincipal.Descripcion);
        DescEvento.setFont(new Font("Arial", Font.PLAIN, 28));
        DescEvento.setForeground(BeigeB);

        JScrollPane scrollPane = new JScrollPane(DescEvento);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // ---------------------------

        btnComprar = crearBotonRedondeado("Comprar", VerdeTarjeta);
        btnComprar.setFont(new Font("Arial", Font.BOLD, 18));
        btnComprar.setPreferredSize(new Dimension(240, 50));
        btnComprar.addActionListener(e -> mostrarContenido(ContenedorComprar()));

        JPanel bottomLateral = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        bottomLateral.setOpaque(false);
        bottomLateral.add(btnComprar);

        panelLateral.add(labelTitulo, BorderLayout.NORTH);  // título arriba
        panelLateral.add(scrollPane, BorderLayout.CENTER);   // textarea en medio
        panelLateral.add(bottomLateral, BorderLayout.SOUTH);

        JPanel contenedor = panelRedondeado(new Color(230, 220, 200), 45, new BorderLayout(15, 0));
        contenedor.setBorder(new EmptyBorder(15, 15, 15, 15));
        contenedor.add(panelImagen, BorderLayout.CENTER);
        contenedor.add(panelLateral, BorderLayout.EAST);

        JPanel margen = wrapConMargen(contenedor, 0, 15, 15, 15);
        margen.setBackground(BeigeB);
        return margen;
    }

    // =========================================================
    // =========================================================
    // MATRIZ DE ASIENTOS REUTILIZABLE
    // modoCompra=true  → filtra por categoría; solo esa categoría es clickeable
    // modoCompra=false → asientos clickeables para asignar categoría (admin)
    // =========================================================
    private JPanel crearMatrizAsientos(boolean modoCompra,
            JComboBox<Categoria> comboCategoria) {

        final int MAX_BOLETOS = 5;
        final Color COLOR_INACTIVO = new Color(60, 60, 60);
        Font fontLabel = new Font("Arial", Font.PLAIN, 16);

        JPanel panelFilas = new JPanel(new GridLayout(eventoPrincipal.estadio.getFILAS(), 1, 0, 3));
        panelFilas.setOpaque(false);
        panelFilas.setBorder(new EmptyBorder(6, 0, 6, 8));
        for (int f = 0; f <= eventoPrincipal.estadio.getFILAS() - 1; f++) {
            JLabel lbl = new JLabel("F" + (f + 1), SwingConstants.RIGHT);
            lbl.setFont(fontLabel);
            panelFilas.add(lbl);
        }

        JPanel panelMatriz = panelRedondeado(VerdeB, 20, new GridLayout(eventoPrincipal.estadio.getFILAS(), eventoPrincipal.estadio.getCOLUMNAS(), 3, 3));
        panelMatriz.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(20, VerdeTarjeta, 5),
                new EmptyBorder(6, 6, 6, 6)));

        for (int f = 0; f < eventoPrincipal.estadio.getFILAS(); f++) {
            for (int c = 0; c < eventoPrincipal.estadio.getCOLUMNAS(); c++) {

                Asientos asiento = eventoPrincipal.estadio.getAsiento(f, c);
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(25, 25));
                btn.setFocusPainted(false);
                btn.setMargin(new Insets(0, 0, 0, 0));
                if (eventoPrincipal.estadio.getAsiento(f, c) == null) {
                    btn.setVisible(false);
                    botonesAsientos[f][c] = btn;
                    panelMatriz.add(btn);
                    continue;
                }
                if (eventoPrincipal.estadio.getAsiento(f, c).getEstado() == EstadoAsientos.OCUPADO) {
                    btn.setBackground(COLOR_INACTIVO);
                    btn.setBorder(null);
                    btn.setEnabled(false);
                }
                actualizarColorBoton(btn, asiento.getCategoria());

                final int fila = f, col = c;

                //Aqui estaba el problema
                if (!modoCompra) {
                    //Esto solo es para que el adminasigne la categoria al asiento inmediatamente 
                    btn.addActionListener(e
                            -> {
                        eventoPrincipal.estadio.asignarCategoria(fila, col, categoriaSeleccionada);
                        actualizarColorBoton(btn, categoriaSeleccionada);
                    });
                } else {
                    btn.addActionListener(e
                            -> {
                        //solo actua si el boton esta habilitado
                        if (!btn.isEnabled()) {
                            return;
                        }
                        if (!asiento.isSeleccionado()) {
                            //si se intenta seleccionar
                            seleccionados = contarSeleccionados();
                            if (seleccionados >= MAX_BOLETOS) {
                                JOptionPane.showMessageDialog(null, "Limite de " + MAX_BOLETOS + " boletos por transaccion", "Limite alcanzado", JOptionPane.INFORMATION_MESSAGE);
                                return;//no cambia nada
                            }
                            asiento.setSeleccionado(true);
                            btn.setBackground((Color.YELLOW));
                            btn.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
                            total = calcularTotal();
                            lblTotal.setText(String.format("Total: $%.2f", total));
                            lblTotal.repaint();
                        } else {
                            //deseleccionar
                            asiento.setSeleccionado(false);
                            actualizarColorBoton(btn, asiento.getCategoria());
                            btn.setBorder(null);
                            total = calcularTotal();
                            lblTotal.setText(String.format("Total: $%.2f", total));
                            lblTotal.repaint();
                        }
                    });
                }

                botonesAsientos[f][c] = btn;
                panelMatriz.add(btn);
            }
        }
        filtrarMatrizPorCategoria((Categoria) comboCategoria.getSelectedItem(), COLOR_INACTIVO);//esto deberia funcionar para desactivar los asientos ya vendidos
        // Filtrado por categoría: solo en modo compra
        if (modoCompra && comboCategoria != null) {
            // Aplica filtro inicial con la categoría ya seleccionada en el combo
            filtrarMatrizPorCategoria((Categoria) comboCategoria.getSelectedItem(), COLOR_INACTIVO);

            comboCategoria.addActionListener(e
                    -> {
                Categoria nueva = (Categoria) comboCategoria.getSelectedItem();
                // Limpia selecciones previas antes de cambiar de categoría
                for (int f2 = 0; f2 < eventoPrincipal.estadio.getFILAS(); f2++) {
                    for (int c2 = 0; c2 < eventoPrincipal.estadio.getCOLUMNAS(); c2++) {
                        Asientos a = eventoPrincipal.estadio.getAsiento(f2, c2);
                        if (a == null) {
                            continue;
                        }
                        if (a.isSeleccionado()) {
                            a.setSeleccionado(false);
                            actualizarColorBoton(botonesAsientos[f2][c2], a.getCategoria());
                            botonesAsientos[f2][c2].setBorder(null);
                        }
                    }
                }
                filtrarMatrizPorCategoria(nueva, COLOR_INACTIVO);
            });
        }
        JPanel panelColumnas = new JPanel(new GridLayout(1,eventoPrincipal.estadio.getCOLUMNAS(),3,0));
        panelColumnas.setOpaque(false);
        panelColumnas.setBorder(new EmptyBorder(8,6,6,8));
        for (int c = 0; c <= eventoPrincipal.estadio.getCOLUMNAS()-1; c++) {
            JLabel lbl = new JLabel("C" + (c+1), SwingConstants.CENTER);
            lbl.setFont(fontLabel);
            panelColumnas.add(lbl);
        }
        JPanel contenedorMatriz = new JPanel(new BorderLayout());
        contenedorMatriz.setOpaque(false);
        contenedorMatriz.add(panelFilas, BorderLayout.WEST);
        contenedorMatriz.add(panelMatriz, BorderLayout.CENTER);
        contenedorMatriz.add(panelColumnas, BorderLayout.SOUTH);
        return contenedorMatriz;
    }

    // =========================================================
    // Habilita/resalta asientos de la categoría elegida; apaga y deshabilita los demás
    private void filtrarMatrizPorCategoria(Categoria cat, Color colorInactivo) {
        for (int f = 0; f < eventoPrincipal.estadio.getFILAS(); f++) {
            for (int c = 0; c < eventoPrincipal.estadio.getCOLUMNAS(); c++) {
                Asientos a = eventoPrincipal.estadio.getAsiento(f, c);
                if (a == null) {
                    continue;
                }
                JButton btn = botonesAsientos[f][c];

                // Si está ocupado, siempre gris y deshabilitado sin importar el filtro
                if (a.getEstado() == EstadoAsientos.OCUPADO) {
                    btn.setBackground(colorInactivo);
                    btn.setBorder(null);
                    btn.setEnabled(false);
                } else if (a.getCategoria() == cat) {
                    // Pertenece a la categoría filtrada: habilitar y colorear normal
                    actualizarColorBoton(btn, a.getCategoria());
                    btn.setBorder(null);
                    btn.setEnabled(true);
                } else {
                    // Otra categoría: gris y deshabilitado
                    btn.setBackground(colorInactivo);
                    btn.setBorder(null);
                    btn.setEnabled(false);
                }
            }
        }
    }

    // CONTENEDOR COMPRAR
    // Panel lateral: leyenda de categorías + total + botón confirmar
    // Centro: matriz de asientos en modo compra
    // =========================================================
    public JPanel ContenedorComprar() {

        eventoPrincipal.actualizarListas();
        eventoPrincipal.imprimirlistas();

        Font fontLabel = new Font("Arial", Font.BOLD, 15);
        Font fontInfo = new Font("Arial", Font.PLAIN, 14);

        // --- COMBO DE CATEGORÍA (se crea ANTES que la matriz para pasarlo al filtro) ---
        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setFont(fontLabel);
        lblCategoria.setForeground(Color.WHITE);

        JComboBox<Categoria> comboCategoria = new JComboBox<>(Categoria.values());
        comboCategoria.setFont(fontInfo);
        comboCategoria.setSelectedItem(Categoria.GENERAL);
        comboCategoria.setMaximumSize(new Dimension(220, 30));

        // --- PANEL LATERAL IZQUIERDO ---
        JPanel panelLateralCompra = panelRedondeado(VerdeB, 30, new BorderLayout());
        panelLateralCompra.setPreferredSize(new Dimension(260, 0));
        panelLateralCompra.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(30, VerdeTarjeta, 4),
                new EmptyBorder(20, 15, 20, 15)));

        JLabel lblTitulo = new JLabel("Selecciona tus asientos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Leyenda de colores
        JPanel panelLeyenda = new JPanel(new GridLayout(3, 1, 5, 8));
        panelLeyenda.setOpaque(false);
        panelLeyenda.add(filaLeyenda("VIP: " + eventoPrincipal.totalBoletosCategoria(eventoPrincipal.boletosVip), COLOR_VIP, fontInfo));
        panelLeyenda.add(filaLeyenda("Preferencial: " + eventoPrincipal.totalBoletosCategoria(eventoPrincipal.boletosPreferencial), COLOR_PREF, fontInfo));
        panelLeyenda.add(filaLeyenda("General: " + eventoPrincipal.totalBoletosCategoria(eventoPrincipal.boletosGeneral), COLOR_GEN, fontInfo));

        // Panel combo + leyenda seleccionado
        JPanel panelFiltro = new JPanel(new GridLayout(4, 1, 5, 8));
        panelFiltro.setOpaque(false);
        panelFiltro.add(lblCategoria);
        panelFiltro.add(comboCategoria);
        panelFiltro.add(filaLeyenda("Seleccionado", Color.YELLOW, fontInfo));
        panelFiltro.add(filaLeyenda("No disponible", new Color(60, 60, 60), fontInfo));

        lblTotal = new JLabel("Total: $0.00");
        lblTotal.setFont(fontLabel);
        lblTotal.setForeground(Color.WHITE);
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        JButton btnConfirmar = crearBotonRedondeado("Confirmar compra", VerdeTarjeta);
        btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
        btnConfirmar.setPreferredSize(new Dimension(210, 45));

        // Actualiza el label de total cada vez que cambia la categoría
        comboCategoria.addActionListener(e
                -> lblTotal.setText("Total: $0.00"));

        JPanel panelNorth = new JPanel(new BorderLayout(0, 10));
        panelNorth.setOpaque(false);
        panelNorth.add(lblTitulo, BorderLayout.NORTH);
        panelNorth.add(panelLeyenda, BorderLayout.CENTER);
        panelNorth.add(panelFiltro, BorderLayout.SOUTH);

        JPanel panelSouth = new JPanel(new BorderLayout(0, 10));
        panelSouth.setOpaque(false);
        panelSouth.add(lblTotal, BorderLayout.NORTH);
        panelSouth.add(btnConfirmar, BorderLayout.CENTER);

        panelLateralCompra.add(panelNorth, BorderLayout.NORTH);
        panelLateralCompra.add(panelSouth, BorderLayout.SOUTH);

        // --- MATRIZ: recibe el combo para conectar el filtro ---
        JPanel matrizAsientos = crearMatrizAsientos(true, comboCategoria);

        // --- CONTENEDOR GENERAL ---
        JPanel contenedor = panelRedondeado(new Color(230, 220, 200), 45, new BorderLayout(15, 0));
        contenedor.setBorder(new EmptyBorder(15, 15, 15, 15));
        contenedor.add(panelLateralCompra, BorderLayout.WEST);
        contenedor.add(matrizAsientos, BorderLayout.CENTER);

        btnConfirmar.addActionListener(e
                -> {
            eventoPrincipal.actualizarListas();
            // Verificar que haya al menos un asiento seleccionado
            if (contarSeleccionados() == 0) {
                JOptionPane.showMessageDialog(this,
                        "Selecciona al menos un asiento antes de confirmar.",
                        "Sin selección", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "Compra confirmada\nTotal: $" + String.format("%.2f", total),
                    "Confirmación", JOptionPane.INFORMATION_MESSAGE);

            mostrarContenido(ContenedorPPrincipal());

            // Marcar los asientos seleccionados como OCUPADO y repintar sus botones
            for (int f = 0; f < eventoPrincipal.estadio.getFILAS(); f++) {
                for (int c = 0; c < eventoPrincipal.estadio.getCOLUMNAS(); c++) {
                    if (eventoPrincipal.estadio.getMatriz()[f][c] == null) {
                        continue;
                    }
                    if (eventoPrincipal.estadio.getMatriz()[f][c].isSeleccionado()) {

                        eventoPrincipal.estadio.getMatriz()[f][c].setSeleccionado(false);
                        eventoPrincipal.estadio.getMatriz()[f][c].setEstado(EstadoAsientos.OCUPADO); // <-- marcar como no disponible
                        botonesAsientos[f][c].setBackground(new Color(60, 60, 60));
                        botonesAsientos[f][c].setBorder(null);
                        botonesAsientos[f][c].setEnabled(false); // <-- deshabilitar el botón
                        boletosComprados.add(new Boleto(eventoPrincipal.estadio.getMatriz()[f][c].getId(), eventoPrincipal.estadio.getMatriz()[f][c], eventoPrincipal.estadio.getMatriz()[f][c].getEstado()));
                    }
                }
            }
            try {
                baseDatos.IniciarConexion();
                baseDatos.ActualizarAsientos(eventoPrincipal);
                baseDatos.CerrarConexion();
            } catch (SQLException ex) {
                System.getLogger(MenuPrincipal.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            // Registrar reporte
            seleccionados = boletosComprados.size();
            baseDatos.IniciarConexion();
            try {
                Reporte repo = new Reporte(1, boletosComprados, (int) seleccionados, (Categoria) comboCategoria.getSelectedItem());
                //
                //eventoPrincipal.colaReportes.peek().mostrar();
                baseDatos.GuardarReporte(repo);
            } catch (SQLException ex) {
                System.getLogger(MenuPrincipal.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            baseDatos.CerrarConexion();
            // Resetear totales
            seleccionados = 0;
            total = 0;
            boletosComprados.clear();
            lblTotal.setText("Total: $0.00");
        });

        JPanel margen = wrapConMargen(contenedor, 0, 15, 15, 15);
        margen.setBackground(BeigeB);
        return margen;
    }

    // Fila de leyenda: cuadro de color + etiqueta
    private JPanel filaLeyenda(String texto, Color color, Font font) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        fila.setOpaque(false);
        JPanel cuadro = new JPanel();
        cuadro.setBackground(color);
        cuadro.setPreferredSize(new Dimension(18, 18));
        cuadro.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        JLabel lbl = new JLabel(texto);
        lbl.setFont(font);
        lbl.setForeground(Color.WHITE);
        fila.add(cuadro);
        fila.add(lbl);
        return fila;
    }

    // Suma el precio de los asientos marcados como seleccionados
    private double calcularTotal() {
        double total = 0;
        for (int f = 0; f < eventoPrincipal.estadio.getFILAS(); f++) {
            for (int c = 0; c < eventoPrincipal.estadio.getCOLUMNAS(); c++) {
                Asientos a = eventoPrincipal.estadio.getAsiento(f, c);
                if (a == null) {
                    continue;
                }
                if (a.isSeleccionado()) {
                    total += a.getCategoria().getPrecio();
                }
            }
        }
        return total;
    }

    //Para contar seleccionados sin recorrer dos veces 
    private long contarSeleccionados() {
        long count = 0;
        for (int f = 0; f < eventoPrincipal.estadio.getFILAS(); f++) {
            for (int c = 0; c < eventoPrincipal.estadio.getCOLUMNAS(); c++) {
                if (eventoPrincipal.estadio.getAsiento(f, c) == null) {
                    continue;
                }
                if (eventoPrincipal.estadio.getAsiento(f, c).isSeleccionado()) {
                    count++;
                }
            }
        }
        return count;
    }

    // =========================================================
    // CONTENEDOR MODIFICAR EVENTO (reutiliza crearMatrizAsientos)
    // =========================================================
    public JPanel ContenedorModEvento() {

        JPanel panelContenedor = new JPanel(new BorderLayout(0, 0));
        panelContenedor.setBackground(BeigeB);
        panelContenedor.setBorder(new EmptyBorder(15, 15, 15, 15));

        // --- PANEL IZQUIERDO CONFIGURACIÓN ---
        JPanel panelConfiguracion = panelRedondeado(BeigeB, 20, null);
        panelConfiguracion.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(20, VerdeB, 5),
                new EmptyBorder(15, 15, 15, 15)));

        Font fontLabel = new Font("Arial", Font.PLAIN, 16);
        Font fontTxt = new Font("Arial", Font.PLAIN, 16);
        int x = 20, w = 240;

        JLabel lblNombre = new JLabel("Nombre del Evento");
        lblNombre.setFont(fontLabel);
        lblNombre.setBounds(x, 25, w, 20);
        JTextField txtNombre = new JTextField(eventoPrincipal.nombreEvento);
        txtNombre.setFont(fontTxt);
        txtNombre.setBounds(x, 50, w, 25);

        JLabel lblSeleccionar = new JLabel("Seleccionar Categoria:");
        lblSeleccionar.setFont(fontLabel);
        lblSeleccionar.setBounds(x, 90, w, 20);
        JComboBox<Categoria> comboCategoria = new JComboBox<>(Categoria.values());
        comboCategoria.setFont(fontTxt);
        comboCategoria.setBounds(x, 120, w, 25);
        comboCategoria.setSelectedItem(Categoria.GENERAL);
        categoriaSeleccionada = Categoria.GENERAL;

        JLabel lblCosto = new JLabel("Costo Boleto:");
        lblCosto.setFont(fontLabel);
        lblCosto.setBounds(x, 160, w, 20);
        JTextField txtCosto = new JTextField(String.valueOf(Categoria.GENERAL.getPrecio()));
        txtCosto.setFont(fontTxt);
        txtCosto.setBounds(x, 190, 110, 25);

        JButton GPrecio = new JButton("Guardar $");
        GPrecio.setOpaque(false);
        GPrecio.setFont(fontLabel);
        GPrecio.setBounds(150, 190, 110, 24);

        comboCategoria.addActionListener(e
                -> {
            categoriaSeleccionada = (Categoria) comboCategoria.getSelectedItem();
            txtCosto.setText(String.valueOf(categoriaSeleccionada.getPrecio()));
        });

        GPrecio.addActionListener(e
                -> {
            try {
                categoriaSeleccionada.setPrecio(Double.parseDouble(txtCosto.getText()));
                comboCategoria.repaint();
                comboCategoria.validate();
            } catch (NumberFormatException ex) {
            }
        });

        // --- FECHA con DatePicker ---
        JLabel lblFecha = new JLabel("Fecha");
        lblFecha.setFont(fontLabel);
        lblFecha.setBounds(x, 235, w, 20);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setFont(fontTxt);
        dateChooser.setDate(eventoPrincipal.Fecha);
        dateChooser.setBounds(x, 255, w, 25);
        dateChooser.setDateFormatString("dd/MM/yyyy");

        JLabel lblDesc = new JLabel("Descripcion");
        lblDesc.setFont(fontLabel);
        lblDesc.setBounds(x, 290, w, 20);
        JTextArea txtDesc = new JTextArea();
        txtDesc.setFont(fontTxt);
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.setText(eventoPrincipal.Descripcion);
        JScrollPane scrollDesc = new JScrollPane(txtDesc);
        scrollDesc.setBounds(x, 310, w, 130);

        btnGuardarImg = new JButton("Seleccionar Imagen");
        btnGuardarImg.setBounds(x, 450, 220, 25);
        panelConfiguracion.add(btnGuardarImg);

        btnGuardarImg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser selector = new JFileChooser();
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes (jpg, jpeg, png, gif, bmp)", "jpg", "jpeg", "png", "gif", "bmp");
                selector.setFileFilter(filtro);
                int resultado = selector.showOpenDialog(MenuPrincipal.this);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File f = selector.getSelectedFile();
                    try {
                        // Carpeta destino dentro del proyecto
                        Path carpetaDestino = Paths.get("src/Imagenes");

                        // Crea la carpeta si no existe
                        if (!Files.exists(carpetaDestino)) {
                            Files.createDirectories(carpetaDestino);
                        }

                        // Ruta completa del archivo destino
                        Path archivoDestino = carpetaDestino.resolve(f.getName());

                        // Copia el archivo; REPLACE_EXISTING evita error si ya hay uno igual
                        Files.copy(f.toPath(), archivoDestino,
                                StandardCopyOption.REPLACE_EXISTING);

                        // Guarda la ruta LOCAL (ya dentro del proyecto)
                        eventoPrincipal.rutaImg = archivoDestino.toString();

                        JOptionPane.showMessageDialog(MenuPrincipal.this,
                                "Imagen guardada:\n" + archivoDestino,
                                "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(MenuPrincipal.this,
                                "Error al copiar la imagen:\n" + ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (resultado == JFileChooser.APPROVE_OPTION) {
                        try {
                            Path carpetaDestino = Paths.get("src/Imagenes");

                            if (!Files.exists(carpetaDestino)) {
                                Files.createDirectories(carpetaDestino);
                            }

                            Path archivoDestino
                                    = carpetaDestino.resolve(f.getName());

                            Files.copy(
                                    f.toPath(),
                                    archivoDestino,
                                    StandardCopyOption.REPLACE_EXISTING
                            );

                            // ESTA ES LA RUTA QUE DEBES GUARDAR
                            eventoPrincipal.rutaImg
                                    = archivoDestino.toString();

                            JOptionPane.showMessageDialog(
                                    MenuPrincipal.this,
                                    "Imagen guardada:\n" + archivoDestino,
                                    "Éxito",
                                    JOptionPane.INFORMATION_MESSAGE
                            );

                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(
                                    MenuPrincipal.this,
                                    "Error al copiar la imagen:\n"
                                    + ex.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }
            }
        });

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(fontTxt);
        btnGuardar.setBackground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBounds(x + 70, 480, 100, 30);

        // Ejemplo de cómo obtener la fecha al guardar
        btnGuardar.addActionListener(e -> {
            Date fechaSeleccionada = dateChooser.getDate();
            String nombre = txtNombre.getText().trim();
            String descripcion = txtDesc.getText().trim();

            if (fechaSeleccionada == null || nombre.isEmpty() || descripcion.isEmpty() || eventoPrincipal.rutaImg.equals("")) {

                JOptionPane.showMessageDialog(null, "Llena todos los campos");
                return; //detiene la ejecución, no guarda nada
            }

            try {
                eventoPrincipal.Fecha = fechaSeleccionada;
                eventoPrincipal.nombreEvento = nombre.toUpperCase();
                eventoPrincipal.Descripcion = descripcion;
                eventoPrincipal.ImpEvento();
                baseDatos.IniciarConexion();
                baseDatos.ActualizarAsientos(eventoPrincipal);
                baseDatos.CerrarConexion();
                baseDatos.IniciarConexion();
                baseDatos.ActualizarEvento(eventoPrincipal);
                baseDatos.CerrarConexion();
                JOptionPane.showMessageDialog(null, "Evento Guardado Correctamente");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar el evento");
            }

        });

        panelConfiguracion.add(lblNombre);
        panelConfiguracion.add(txtNombre);
        panelConfiguracion.add(lblSeleccionar);
        panelConfiguracion.add(comboCategoria);
        panelConfiguracion.add(lblCosto);
        panelConfiguracion.add(txtCosto);
        panelConfiguracion.add(GPrecio);
        panelConfiguracion.add(lblFecha);
        panelConfiguracion.add(dateChooser);
        panelConfiguracion.add(lblDesc);
        panelConfiguracion.add(scrollDesc);
        panelConfiguracion.add(btnGuardar);

        JPanel panelIzquierdo = panelRedondeado(VerdeB, 30, new BorderLayout());
        panelIzquierdo.setPreferredSize(new Dimension(320, 0));
        panelIzquierdo.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(20, VerdeTarjeta, 4),
                new EmptyBorder(15, 15, 15, 15)));
        panelIzquierdo.add(panelConfiguracion, BorderLayout.CENTER);

        // --- MATRIZ reutilizada en modo ADMIN ---
        JPanel matrizAsientos = crearMatrizAsientos(false, comboCategoria);

        panelContenedor.add(panelIzquierdo, BorderLayout.WEST);
        panelContenedor.add(matrizAsientos, BorderLayout.CENTER);
        return panelContenedor;
    }

    private void actualizarColorBoton(JButton btn, Categoria cat) {
        switch (cat) {
            case VIP ->
                btn.setBackground(COLOR_VIP);
            case PREFERENCIAL ->
                btn.setBackground(COLOR_PREF);
            case GENERAL ->
                btn.setBackground(COLOR_GEN);
        }
    }

    public void mostrarContenido(JPanel contenido) {
        margenCentral.removeAll();
        margenCentral.add(contenido, BorderLayout.CENTER);
        margenCentral.revalidate();
        margenCentral.repaint();
    }

    // =========================================================
    // CONTENEDOR INFO
    // =========================================================
    public JPanel ContenedorInfo() {
        JPanel panelIzquierdo = panelRedondeado(BeigeB, 40, new BorderLayout());
        panelIzquierdo.setBorder(new RoundedBorder(40, VerdeTarjeta, 4));

        JTextArea textInfo = new JTextArea();
        textInfo.setFont(new Font("Arial", Font.PLAIN, 30));
        textInfo.setForeground(Color.BLACK);
        textInfo.setOpaque(false);
        textInfo.setLineWrap(true);
        textInfo.setWrapStyleWord(true);
        textInfo.setEditable(false);
        textInfo.setBorder(new EmptyBorder(15, 15, 15, 15));
        textInfo.setText("""
                         El Estadio Universitario Alberto \u201cChivo\u201d C\u00f3rdoba es uno de los recintos deportivos m\u00e1s representativos de la Universidad Aut\u00f3noma del Estado de M\u00e9xico (UAEM\u00e9x). Se encuentra ubicado dentro de Ciudad Universitaria, en Toluca, Estado de M\u00e9xico, y es utilizado principalmente para eventos de f\u00fatbol, atletismo y f\u00fatbol americano. Fue inaugurado el 5 de noviembre de 1964 y cuenta con una capacidad aproximada para 32,000 espectadores.
                         
                         El estadio recibe su nombre en honor a Alberto \u201cChivo\u201d C\u00f3rdoba, destacado entrenador de f\u00fatbol americano universitario que impuls\u00f3 el desarrollo deportivo de la UAEM\u00e9x. A lo largo de su historia, este recinto ha sido sede de importantes competencias universitarias y partidos profesionales, incluyendo encuentros de los Potros UAEM y del Deportivo Toluca durante la remodelaci\u00f3n del Estadio Nemesio Diez en 2016.
                         
                         Uno de los aspectos m\u00e1s llamativos del estadio es su dise\u00f1o arquitect\u00f3nico y art\u00edstico. En la parte de las gradas se encuentra el mural \u201cAratm\u00f3sfera\u201d, creado por el reconocido artista mexiquense Leopoldo Flores entre 1974 y 1976. Esta obra de gran tama\u00f1o convierte al estadio en uno de los recintos deportivos m\u00e1s originales de M\u00e9xico y del mundo, integrando arte y deporte en un mismo espacio.
                         
                         El estadio tambi\u00e9n destaca por sus instalaciones deportivas, ya que cuenta con una pista de atletismo certificada, cancha de pasto natural y \u00e1reas destinadas para competencias universitarias y eventos masivos. Gracias a sus remodelaciones y mantenimiento, contin\u00faa siendo uno de los espacios deportivos m\u00e1s importantes del Estado de M\u00e9xico.
                         
                         Ubicado sobre Paseo General Vicente Guerrero, dentro de Ciudad Universitaria de la UAEM\u00e9x, el Estadio \u201cChivo\u201d C\u00f3rdoba forma parte de la identidad universitaria y cultural de la instituci\u00f3n, siendo un s\u00edmbolo hist\u00f3rico para estudiantes, deportistas y aficionados al deporte universitario.""");

        JScrollPane scroll = new JScrollPane(textInfo);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        panelIzquierdo.add(scroll, BorderLayout.CENTER);

        JPanel columnaDerecha = new JPanel(new GridLayout(2, 1, 10, 10));
        columnaDerecha.setOpaque(false);
        columnaDerecha.setPreferredSize(new Dimension(360, 0));
        columnaDerecha.add(crearPanelImagen(new ImageIcon("src/Imagenes/ChivoCordoba1.jpeg").getImage()));
        columnaDerecha.add(crearPanelImagen(new ImageIcon("src/Imagenes/ChivoC1.jpg").getImage()));

        JPanel contenedor = panelRedondeado(new Color(230, 220, 200), 45, new BorderLayout(15, 0));
        contenedor.setBorder(new EmptyBorder(15, 15, 15, 15));
        contenedor.add(panelIzquierdo, BorderLayout.CENTER);
        contenedor.add(columnaDerecha, BorderLayout.EAST);

        JPanel margen = wrapConMargen(contenedor, 0, 15, 15, 15);
        margen.setBackground(BeigeB);
        return margen;
    }

    // =========================================================
    // HELPERS
    // =========================================================
    private JPanel crearPanelImagen(Image img) {
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setClip(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 40, 40));
                g2.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                g2.dispose();
            }
        };
        p.setOpaque(false);
        p.setBorder(new RoundedBorder(40, VerdeTarjeta, 4));
        return p;
    }

    private JPanel panelRedondeado(Color color, int radio, LayoutManager layout) {
        JPanel p = new JPanel(layout) {
            @Override
            protected void paintComponent(Graphics g) {
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
            @Override
            protected void paintComponent(Graphics g) {
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

    class RoundedBorder extends AbstractBorder {

        private final int radius, thickness;
        private final Color color;

        RoundedBorder(int radius, Color color, int thickness) {
            this.radius = radius;
            this.color = color;
            this.thickness = thickness;
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

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(10, 10, 10, 10);
        }
    }
}
