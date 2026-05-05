package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;
import estadio.*;

public class MenuPrincipal extends JFrame
{

    static final Color VerdeB = new Color(76, 175, 80);
    static final Color BeigeB = new Color(243, 232, 211);
    static final Color VerdeTarjeta = new Color(27, 94, 32);

    static final Color COLOR_VIP = new Color(0, 0, 142);
    static final Color COLOR_PREF = new Color(120, 0, 142);
    static final Color COLOR_GEN = new Color(125, 124, 121);
    static final Color COLOR_GRATIS = new Color(236, 239, 241);

    private EstadioClass estadio;
    private JButton[][] botonesAsientos;
    private Categoria categoriaSeleccionada = Categoria.GENERAL;

    private Login login = new Login();

    public JButton btnEventoPrincipal, btnInfoGeneral, btnIngresa, btnComprar, btnSalir, btnModificar;
    public JPanel panelImagen, panelLateral;
    private boolean Admin = false;

    private JPanel margenCentral;
    private JPanel navPanel;      // referencia para poder reemplazarlo
    private JPanel panelBotones;  // referencia para actualizar botones
    
    
    public Evento eventoPrincipoal; //esta clase ya contiene todas las estructuras de datos que vamos a ocupar

    public MenuPrincipal()
    {
        setTitle("Menu Principal Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 700));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(getToolkit().getImage(getClass().getResource("/Imagenes/Logo.jpg")));
        this.estadio = new EstadioClass();
        this.eventoPrincipoal=new Evento("eventoPrincipal", estadio);
        this.botonesAsientos = new JButton[10][30];
        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                revalidate();
                repaint();
            }
        });
        setLayout(new BorderLayout());
        getContentPane().setBackground(BeigeB);
    }

    public void ContenedorPrincipal()
    {
        margenCentral = new JPanel(new BorderLayout());
        margenCentral.setBackground(BeigeB);

        actualizarNavbar(); // construye y agrega la navbar inicial

        add(margenCentral, BorderLayout.CENTER);
        mostrarContenido(ContenedorPPrincipal());

        login.Contenedor();
    }

    // Reconstruye y reemplaza la navbar según el estado de Admin
    private void actualizarNavbar()
    {

        // --- FONDO NAVBAR ---
        JPanel panelFondo = panelRedondeado(VerdeTarjeta, 40, new BorderLayout());
        panelFondo.setBorder(new EmptyBorder(10, 15, 10, 15));

        // Logo
        Image imagenLogo = new ImageIcon("src/Imagenes/Logo.jpg").getImage();
        JLabel lblLogo = new JLabel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
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

        // --- BOTONES según rol ---
        btnEventoPrincipal = crearBotonNav("Evento Principal");
        btnInfoGeneral = crearBotonNav("Informacion General");
        btnIngresa = crearBotonNav("Ingresa");
        btnModificar = crearBotonNav("Modificar Evento");
        btnSalir = crearBotonNav("Cerrar sesion");

        if (Admin)
        {
            // 4 botones: Evento | Info | Modificar | Cerrar sesion
            panelBotones = new JPanel(new GridLayout(1, 4, 15, 0));
            panelBotones.setOpaque(false);
            panelBotones.setPreferredSize(new Dimension(850, 45));
            panelBotones.add(btnEventoPrincipal);
            panelBotones.add(btnInfoGeneral);
            panelBotones.add(btnModificar);
            panelBotones.add(btnSalir);
        } else
        {
            // 3 botones: Evento | Info | Ingresa
            panelBotones = new JPanel(new GridLayout(1, 3, 15, 0));
            panelBotones.setOpaque(false);
            panelBotones.setPreferredSize(new Dimension(650, 45));
            panelBotones.add(btnEventoPrincipal);
            panelBotones.add(btnInfoGeneral);
            panelBotones.add(btnIngresa);
        }

        panelFondo.add(izquierda, BorderLayout.WEST);
        panelFondo.add(panelBotones, BorderLayout.CENTER);

        // Reemplaza navPanel en NORTH
        if (navPanel != null)
        {
            remove(navPanel);
        }
        navPanel = wrapConMargen(panelFondo, 15, 15, 10, 15);
        navPanel.setBackground(BeigeB);
        add(navPanel, BorderLayout.NORTH);
        revalidate();
        repaint();

        // --- LISTENERS ---
        btnEventoPrincipal.addActionListener(e -> mostrarContenido(ContenedorPPrincipal()));
        btnInfoGeneral.addActionListener(e -> mostrarContenido(ContenedorInfo()));
        btnModificar.addActionListener(e->mostrarContenido(ContenedorModEvento()));

        if (Admin)
        {
            btnModificar.addActionListener(e ->
            {
                /* acción modificar */ });
            btnSalir.addActionListener(e ->
            {
                Admin = false;
                actualizarNavbar();             // vuelve a navbar de usuario
                mostrarContenido(ContenedorPPrincipal());
            });
        } else
        {
            btnIngresa.addActionListener(e ->
            {
                login.setVisible(true);
                // Evita acumular listeners con un array de un elemento como flag
                ActionListener[] ls = login.btnLogin.getActionListeners();
                for (ActionListener l : ls)
                {
                    login.btnLogin.removeActionListener(l);
                }

                login.btnLogin.addActionListener(f ->
                {
                    if (login.txtUser.getText().equals(login.usuario)
                            && login.txtPass.getText().equals(login.contra))
                    {
                        login.setVisible(false);
                        Admin = true;
                        actualizarNavbar();     // reconstruye con botones de admin
                        mostrarContenido(ContenedorPPrincipal());
                    }
                });
            });
        }
    }

    public JPanel ContenedorModEvento()
    {
        JPanel panelContenedorAddEvento = new JPanel(new BorderLayout(0, 0));
        panelContenedorAddEvento.setBackground(BeigeB);
        panelContenedorAddEvento.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel panelIzquierdo = panelRedondeado(VerdeB, 30, new BorderLayout());
        panelIzquierdo.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelIzquierdo.setPreferredSize(new Dimension(320, 0));
        panelIzquierdo.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(20, VerdeTarjeta, 4),
                new EmptyBorder(15, 15, 15, 15)));

        JPanel panelConfiguracion = panelRedondeado(BeigeB, 20, null);
        panelConfiguracion.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelConfiguracion.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(20, VerdeB, 5),
                new EmptyBorder(15, 15, 15, 15)));

        Font fontLabel = new Font("Arial", Font.PLAIN, 16);
        int x = 20;
        int w = 240;
        Font fontTxt = new Font("Arial", Font.PLAIN, 16);

        JLabel lblNombre = new JLabel("Nombre del Evento");
        lblNombre.setFont(fontLabel);
        lblNombre.setBounds(x, 25, w, 20);
        JTextField txtNombre = new JTextField();
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

        JCheckBox chkGratis = new JCheckBox("Gratis");
        chkGratis.setOpaque(false);
        chkGratis.setFont(new Font("Arial", Font.PLAIN, 16));
        chkGratis.setBounds(160, 190, 100, 25);

        JLabel lblCosto = new JLabel("Costo Boleto:");
        lblCosto.setFont(fontLabel);
        lblCosto.setBounds(x, 160, w, 20);
        JTextField txtCosto = new JTextField(String.valueOf(Categoria.GENERAL.getPrecio()));
        txtCosto.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyReleased(KeyEvent e)
            {
                if (!chkGratis.isSelected())
                {
                    try
                    {
                        double nuevoPrecio = Double.parseDouble(txtCosto.getText());
                        categoriaSeleccionada.setPrecio(nuevoPrecio);
                    } catch (NumberFormatException ex)
                    {
                    }
                }
            }
        });
        txtCosto.setFont(fontTxt);
        txtCosto.setBounds(x, 190, 110, 25);

        comboCategoria.addActionListener(e ->
        {
            categoriaSeleccionada = (Categoria) comboCategoria.getSelectedItem();
            if (!chkGratis.isSelected())
            {
                txtCosto.setText(String.valueOf(categoriaSeleccionada.getPrecio()));
            }
        });

        chkGratis.addActionListener(e ->
        {
            if (chkGratis.isSelected())
            {
                txtCosto.setText("0.0");
                txtCosto.setEnabled(false);
                comboCategoria.setEnabled(false);
                for (int f = 0; f < 10; f++)
                {
                    for (int c = 0; c < 30; c++)
                    {
                        botonesAsientos[f][c].setBackground(COLOR_GRATIS);
                    }
                }
            } else
            {
                txtCosto.setText(String.valueOf(categoriaSeleccionada.getPrecio()));
                txtCosto.setEnabled(true);
                comboCategoria.setEnabled(true);
                for (int f = 0; f < 10; f++)
                {
                    for (int c = 0; c < 30; c++)
                    {
                        Asientos asiento = estadio.getAsiento(f, c);
                        actualizarColorBoton(botonesAsientos[f][c], asiento.getCategoria());
                    }
                }
            }
        });

        JLabel lblFecha = new JLabel("Fecha");
        lblFecha.setFont(fontLabel);
        lblFecha.setBounds(x, 235, w, 20);
        JTextField txtFecha = new JTextField();
        txtFecha.setFont(fontTxt);
        txtFecha.setBounds(x, 255, w, 25);

        JLabel lblDesc = new JLabel("Descripcion");
        lblDesc.setFont(fontLabel);
        lblDesc.setBounds(x, 290, w, 20);
        JTextArea txtDesc = new JTextArea();
        txtDesc.setFont(fontTxt);
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(txtDesc);
        scrollDesc.setBounds(x, 310, w, 130);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(fontTxt);
        btnGuardar.setBackground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBounds(x + 70, 460, 100, 30);

        panelConfiguracion.add(lblNombre);
        panelConfiguracion.add(txtNombre);
        panelConfiguracion.add(lblSeleccionar);
        panelConfiguracion.add(comboCategoria);
        panelConfiguracion.add(lblCosto);
        panelConfiguracion.add(txtCosto);
        panelConfiguracion.add(chkGratis);
        panelConfiguracion.add(lblFecha);
        panelConfiguracion.add(txtFecha);
        panelConfiguracion.add(lblDesc);
        panelConfiguracion.add(scrollDesc);
        panelConfiguracion.add(btnGuardar);

        panelIzquierdo.add(panelConfiguracion, BorderLayout.CENTER);

        JPanel panelEstadioCentral = new JPanel(new BorderLayout(0, 4));
        panelEstadioCentral.setOpaque(false);
        panelEstadioCentral.setBorder(new EmptyBorder(10, 15, 10, 15));

        JPanel panelFilas = new JPanel(new GridLayout(10, 1, 0, 3));
        panelFilas.setOpaque(false);
        panelFilas.setBorder(new EmptyBorder(6, 0, 6, 8));
        for (int f = 9; f >= 0; f--)
        {
            JLabel lbl = new JLabel("F" + (f + 1), SwingConstants.RIGHT);
            lbl.setFont(fontLabel);
            panelFilas.add(lbl);
        }

        JPanel panelMatriz = panelRedondeado(VerdeB, 20, new GridLayout(10, 30, 3, 3));
        panelMatriz.setBorder(new EmptyBorder(6, 6, 6, 6));
        panelMatriz.setOpaque(false);
        panelMatriz.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(20, VerdeTarjeta, 5),
                new EmptyBorder(6, 6, 6, 6)));

        for (int f = 9; f >= 0; f--)
        {
            for (int c = 0; c < 30; c++)
            {
                Asientos asiento = estadio.getAsiento(f, c);
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(25, 25));
                btn.setFocusPainted(false);
                btn.setMargin(new Insets(0, 0, 0, 0));

                actualizarColorBoton(btn, asiento.getCategoria());

                final int fila = f;
                final int col = c;
                btn.addActionListener(e ->
                {
                    if (!chkGratis.isSelected())
                    {
                        estadio.asignarCategoria(fila, col, categoriaSeleccionada);
                        actualizarColorBoton(btn, categoriaSeleccionada);
                    }
                });

                botonesAsientos[f][c] = btn;
                panelMatriz.add(btn);
            }
        }

        JPanel columnas = new JPanel(new BorderLayout());
        columnas.setOpaque(false);
        JPanel panelNumCols = new JPanel(new GridLayout(1, 30, 3, 0));
        panelNumCols.setOpaque(false);
        for (int c = 0; c < 30; c++)
        {
            JLabel lblCol = new JLabel(String.valueOf(c + 1), SwingConstants.CENTER);
            lblCol.setFont(new Font("Arial", Font.BOLD, 16));
            panelNumCols.add(lblCol);
        }

        JLabel separadorEsquinaCol = new JLabel("Fila 10");
        separadorEsquinaCol.setFont(new Font("Arial", Font.BOLD, 12));
        separadorEsquinaCol.setForeground(new Color(0, 0, 0, 0));
        separadorEsquinaCol.setBorder(new EmptyBorder(0, 0, 0, 8));
        columnas.add(separadorEsquinaCol, BorderLayout.WEST);
        columnas.add(panelNumCols, BorderLayout.CENTER);

        JPanel panelEscenario = panelRedondeado(VerdeB, 15, new GridLayout());
        panelEscenario.setOpaque(false);
        panelEscenario.setPreferredSize(new Dimension(0, 44));
        JLabel lblEscenario = new JLabel("E S C E N A R I O", SwingConstants.CENTER);
        lblEscenario.setForeground(Color.WHITE);
        lblEscenario.setFont(new Font("Arial", Font.BOLD, 18));
        panelEscenario.add(lblEscenario);

        JPanel panelSur = new JPanel(new BorderLayout(0, 10));
        panelSur.setOpaque(false);
        panelSur.setBorder(new EmptyBorder(5, 0, 0, 0));
        panelSur.add(columnas, BorderLayout.NORTH);
        panelSur.add(panelEscenario, BorderLayout.CENTER);

        panelEstadioCentral.add(panelFilas, BorderLayout.WEST);
        panelEstadioCentral.add(panelMatriz, BorderLayout.CENTER);
        panelEstadioCentral.add(panelSur, BorderLayout.SOUTH);

        panelContenedorAddEvento.add(panelIzquierdo, BorderLayout.WEST);
        panelContenedorAddEvento.add(panelEstadioCentral, BorderLayout.CENTER);
        return panelContenedorAddEvento;
    }

    private void actualizarColorBoton(JButton btn, Categoria cat)
    {
        switch (cat)
        {
            case VIP ->
                btn.setBackground(COLOR_VIP);
            case PREFERENCIAL ->
                btn.setBackground(COLOR_PREF);
            case GENERAL ->
                btn.setBackground(COLOR_GEN);
        }
    }

    public void mostrarContenido(JPanel contenido)
    {
        margenCentral.removeAll();
        margenCentral.add(contenido, BorderLayout.CENTER);
        margenCentral.revalidate();
        margenCentral.repaint();
    }

    public JPanel ContenedorPPrincipal()
    {
        Image imagenEvento = new ImageIcon("src/Imagenes/Logo.jpg").getImage();
        panelImagen = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
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

    public JPanel ContenedorInfo()
    {
        JPanel panelIzquierdo = panelRedondeado(BeigeB, 40, new BorderLayout());
        panelIzquierdo.setBorder(new RoundedBorder(40, VerdeTarjeta, 4));

        JTextArea textInfo = new JTextArea();
        textInfo.setFont(new Font("Arial", Font.PLAIN, 32));
        textInfo.setForeground(Color.BLACK);
        textInfo.setOpaque(false);
        textInfo.setLineWrap(true);
        textInfo.setWrapStyleWord(true);
        textInfo.setEditable(false);
        textInfo.setBorder(new EmptyBorder(15, 15, 15, 15));
        textInfo.setText("El Estadio Universitario Alberto “Chivo” Córdoba es uno de los recintos deportivos más representativos de la Universidad Autónoma del Estado de México (UAEMéx). Se encuentra ubicado dentro de Ciudad Universitaria, en Toluca, Estado de México, y es utilizado principalmente para eventos de fútbol, atletismo y fútbol americano. Fue inaugurado el 5 de noviembre de 1964 y cuenta con una capacidad aproximada para 32,000 espectadores.\n"
                + "\n"
                + "El estadio recibe su nombre en honor a Alberto “Chivo” Córdoba, destacado entrenador de fútbol americano universitario que impulsó el desarrollo deportivo de la UAEMéx. A lo largo de su historia, este recinto ha sido sede de importantes competencias universitarias y partidos profesionales, incluyendo encuentros de los Potros UAEM y del Deportivo Toluca durante la remodelación del Estadio Nemesio Diez en 2016.\n"
                + "\n"
                + "Uno de los aspectos más llamativos del estadio es su diseño arquitectónico y artístico. En la parte de las gradas se encuentra el mural “Aratmósfera”, creado por el reconocido artista mexiquense Leopoldo Flores entre 1974 y 1976. Esta obra de gran tamaño convierte al estadio en uno de los recintos deportivos más originales de México y del mundo, integrando arte y deporte en un mismo espacio.\n"
                + "\n"
                + "El estadio también destaca por sus instalaciones deportivas, ya que cuenta con una pista de atletismo certificada, cancha de pasto natural y áreas destinadas para competencias universitarias y eventos masivos. Gracias a sus remodelaciones y mantenimiento, continúa siendo uno de los espacios deportivos más importantes del Estado de México.\n"
                + "\n"
                + "Ubicado sobre Paseo General Vicente Guerrero, dentro de Ciudad Universitaria de la UAEMéx, el Estadio “Chivo” Córdoba forma parte de la identidad universitaria y cultural de la institución, siendo un símbolo histórico para estudiantes, deportistas y aficionados al deporte universitario.");

        JScrollPane scroll = new JScrollPane(textInfo);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        panelIzquierdo.add(scroll, BorderLayout.CENTER);

        Image imgArriba = new ImageIcon("src/Imagenes/ChivoCordoba1.jpeg").getImage();
        JPanel panelImgArriba = crearPanelImagen(imgArriba);

        Image imgAbajo = new ImageIcon("src/Imagenes/Estadio2_1.jpg").getImage();
        JPanel panelImgAbajo = crearPanelImagen(imgAbajo);

        JPanel columnaDerecha = new JPanel(new GridLayout(2, 1, 10, 10));
        columnaDerecha.setOpaque(false);
        columnaDerecha.setPreferredSize(new Dimension(360, 0));
        columnaDerecha.add(panelImgArriba);
        columnaDerecha.add(panelImgAbajo);

        JPanel contenedor = panelRedondeado(new Color(230, 220, 200), 45, new BorderLayout(15, 0));
        contenedor.setBorder(new EmptyBorder(15, 15, 15, 15));
        contenedor.add(panelIzquierdo, BorderLayout.CENTER);
        contenedor.add(columnaDerecha, BorderLayout.EAST);

        JPanel margen = wrapConMargen(contenedor, 0, 15, 15, 15);
        margen.setBackground(BeigeB);
        return margen;
    }

    // Helper para paneles con imagen redondeada (evita repetición en ContenedorInfo)
    private JPanel crearPanelImagen(Image img)
    {
        JPanel p = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
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

    public void ContenedorComprar()
    {
    }

    // --- HELPERS ---
    private JPanel panelRedondeado(Color color, int radio, LayoutManager layout)
    {
        JPanel p = new JPanel(layout)
        {
            @Override
            protected void paintComponent(Graphics g)
            {
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

    private JPanel wrapConMargen(JPanel inner, int top, int left, int bottom, int right)
    {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(new EmptyBorder(top, left, bottom, right));
        wrapper.add(inner, BorderLayout.CENTER);
        return wrapper;
    }

    private JButton crearBotonNav(String texto)
    {
        JButton btn = crearBotonRedondeado(texto, VerdeB);
        btn.setFont(new Font("Arial", Font.BOLD, 15));
        return btn;
    }

    private JButton crearBotonRedondeado(String texto, Color color)
    {
        JButton btn = new JButton(texto)
        {
            @Override
            protected void paintComponent(Graphics g)
            {
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
        btn.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                btn.setBackground(color.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                btn.setBackground(color);
            }
        });
        return btn;
    }

    class RoundedBorder extends AbstractBorder
    {

        private final int radius, thickness;
        private final Color color;

        RoundedBorder(int radius, Color color, int thickness)
        {
            this.radius = radius;
            this.color = color;
            this.thickness = thickness;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
        {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            g2.draw(new RoundRectangle2D.Double(x + 1, y + 1, width - 3, height - 3, radius, radius));
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c)
        {
            return new Insets(10, 10, 10, 10);
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            MenuPrincipal ventana = new MenuPrincipal();
            ventana.ContenedorPrincipal();
            ventana.setVisible(true);
        });
    }
}
