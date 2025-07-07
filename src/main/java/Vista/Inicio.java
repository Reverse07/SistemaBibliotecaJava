package Vista;

import Controlador.CategoriaDAO;
import Controlador.LibroDAO;
import Controlador.PrestamoDAO;
import Controlador.UsuarioDAO;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import Vista.InterPrestamo;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.Timer;

public class Inicio extends javax.swing.JFrame {

    private JPanel jPanelMenu;
    private JPanel jPanelContenido;
    private JLabel jLabelTitulo;
    private CardLayout cardLayout;
    private final Map<String, String> iconos = new HashMap<>();
    private ButtonGroup grupoBotones = new ButtonGroup();
    private JToggleButton botonSeleccionado = null;

    public Inicio() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf()); // Tema moderno
        } catch (Exception e) {
            System.err.println("❌ No se pudo aplicar FlatLaf");
        }

        cargarIconos();
        verificarIconos();
        initComponents2();
        setTitle("📚 Sistema de Gestión de Biblioteca");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
    }

    private void cargarIconos() {
        iconos.put("Principal", "home.png");
        iconos.put("Préstamos", "prestamos.png");
        iconos.put("Devoluciones", "devoluciones.png");
        iconos.put("Usuarios", "usuarios.png");
        iconos.put("Libros", "libros.png");
        iconos.put("Reportes", "reportes.png");
    }

    private void initComponents2() {
        background = new JPanel(new BorderLayout());

        // Menú lateral
        jPanelMenu = new JPanel();
        jPanelMenu.setBackground(new Color(40, 53, 147)); // Azul profundo
        jPanelMenu.setPreferredSize(new Dimension(220, 700));
        jPanelMenu.setLayout(new BoxLayout(jPanelMenu, BoxLayout.Y_AXIS));

        JLabel tituloMenu = new JLabel("Librería");
        tituloMenu.setFont(new Font("Segoe UI", Font.BOLD, 26));
        tituloMenu.setForeground(Color.WHITE);
        tituloMenu.setBorder(BorderFactory.createEmptyBorder(20, 10, 30, 10));
        jPanelMenu.add(tituloMenu);

        String[] opciones = {"Principal", "Préstamos", "Devoluciones", "Usuarios", "Libros", "Reportes"};
        for (String opcion : opciones) {
            jPanelMenu.add(crearBotonMenu(opcion));
        }

        // Panel de contenido
        cardLayout = new CardLayout();
        jPanelContenido = new JPanel(cardLayout);
        jPanelContenido.setBackground(Color.WHITE);

        // Panel de bienvenida
        jPanelContenido.add(panelBienvenida(), "Bienvenida");

        // Paneles funcionales
        for (String opcion : opciones) {
            switch (opcion) {
                case "Préstamos" ->
                    jPanelContenido.add(new InterPrestamo(), opcion);
                // Agrega aquí los demás paneles reales cuando estén listos:

                case "Devoluciones" ->
                    jPanelContenido.add(new InterDevolucion(), opcion);
                case "Usuarios" ->
                    jPanelContenido.add(new InterUsuario(), opcion);
                case "Libros" ->
                    jPanelContenido.add(new InterLibros(), opcion);
                default ->
                    jPanelContenido.add(panel(opcion), opcion);  // Temporal
            }
        }

        background.add(jPanelMenu, BorderLayout.WEST);
        background.add(jPanelContenido, BorderLayout.CENTER);
        setContentPane(background);
        setSize(1200, 700);
        cardLayout.show(jPanelContenido, "Bienvenida");
    }

    private JToggleButton crearBotonMenu(String nombre) {
        JToggleButton boton = new JToggleButton(nombre);
        grupoBotones.add(boton);
        boton.setAlignmentX(Component.LEFT_ALIGNMENT);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(true);
        boton.setBackground(new Color(40, 53, 147));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        boton.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 10));
        boton.setHorizontalAlignment(SwingConstants.LEFT);

        String archivoIcono = iconos.get(nombre);
        ImageIcon icon = cargarIconoDesdeResources(archivoIcono);
        if (icon != null) {
            Image scaled = icon.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(scaled));
        }

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(63, 81, 181));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!boton.isSelected()) {
                    boton.setBackground(new Color(40, 53, 147));
                }
            }
        });

        boton.addActionListener(e -> {
            if (botonSeleccionado != null) {
                botonSeleccionado.setBackground(new Color(40, 53, 147));
            }
            boton.setBackground(new Color(25, 35, 100));
            botonSeleccionado = boton;

            mostrarContenido(nombre);
        });

        return boton;
    }

    private ImageIcon cargarIconoDesdeResources(String nombreArchivo) {
        try {
            return new ImageIcon(getClass().getResource("/img/" + nombreArchivo));
        } catch (Exception e) {
            System.err.println("❌ No se encontró el icono: /img/" + nombreArchivo);
            return null;
        }
    }

    private void mostrarContenido(String seccion) {
        if (seccion.equals("Principal") || seccion.equals("Bienvenida")) {
            // Elimina el panel "Bienvenida" si existe previamente
            for (Component comp : jPanelContenido.getComponents()) {
                if (comp.getName() != null && comp.getName().equals("Bienvenida")) {
                    jPanelContenido.remove(comp);
                    break;
                }
            }

            // Crear uno nuevo actualizado
            JPanel nuevoPanel = panelBienvenida();
            nuevoPanel.setName("Bienvenida"); // ← importante para identificarlo luego

            // Insertarlo y mostrar
            jPanelContenido.add(nuevoPanel, "Bienvenida");
            jPanelContenido.revalidate();
            jPanelContenido.repaint();
            cardLayout.show(jPanelContenido, "Bienvenida");
        } else {
            cardLayout.show(jPanelContenido, seccion);
        }
    }

    private JPanel panel(String nombre) {
        JPanel p = new JPanel(new BorderLayout());
        JLabel l = new JLabel("Vista de: " + nombre, SwingConstants.CENTER);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        l.setForeground(new Color(60, 60, 60));
        p.add(l, BorderLayout.CENTER);
        return p;
    }

    private void verificarIconos() {
        System.out.println("⏳ Verificando íconos...");
        for (String nombreArchivo : iconos.values()) {
            java.net.URL url = getClass().getResource("/img/" + nombreArchivo);
            System.out.println(nombreArchivo + " → " + (url != null ? "✅ ENCONTRADO" : "❌ NO ENCONTRADO"));
        }
    }

    private ImageIcon cargarIcono(String nombreArchivo, int ancho, int alto) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/img/" + nombreArchivo));
            Image imagen = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(imagen);
        } catch (Exception e) {
            System.err.println("❌ No se pudo cargar el ícono: " + nombreArchivo);
            return null;
        }
    }

    private JPanel panelBienvenida() {
        int totalLibros = LibroDAO.contarLibros();
        int totalUsuarios = UsuarioDAO.contarUsuarios();
        int prestamosActivos = PrestamoDAO.contarPrestamosActivos();
        int totalCategorias = CategoriaDAO.contarCategorias();
        System.out.println(" Total categorias : " + totalCategorias);

        JPanel p = new JPanel();
        p.setBackground(new Color(227, 242, 253));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("📚 Sistema de Gestión de Biblioteca");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 30, 10));
        p.add(titulo);

        // Panel para las tarjetas en cuadrícula 2x2
        JPanel panelResumen = new JPanel(new GridLayout(2, 2, 20, 20));
        panelResumen.setBackground(new Color(227, 242, 253));
        panelResumen.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        panelResumen.setMaximumSize(new Dimension(800, 400));
        panelResumen.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tarjetas
        panelResumen.add(crearTarjetaAnimada("📚", "Libros Totales", totalLibros, new Color(33, 150, 243)));
        panelResumen.add(crearTarjetaAnimada("👤", "Usuarios", totalUsuarios, new Color(0, 172, 193)));
        panelResumen.add(crearTarjetaAnimada("🔁", "Préstamos Activos", prestamosActivos, new Color(255, 143, 0)));
        panelResumen.add(crearTarjetaAnimada("📦", "Categorías", totalCategorias, new Color(121, 85, 72)));

        p.add(panelResumen);
        p.setName("Bienvenida");
        return p;
    }

    private JPanel crearTarjetaAnimada(String icono, String titulo, int valorFinal, Color colorTexto) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        tarjeta.setSize(270, 160);
        tarjeta.setPreferredSize(new Dimension(270, 160));

        JLabel lblIcono = new JLabel(icono);
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 38));
        lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblValor = new JLabel("0");
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 34));
        lblValor.setForeground(colorTexto);
        lblValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblValor.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblTitulo.setForeground(Color.DARK_GRAY);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(lblIcono);
        tarjeta.add(lblValor);
        tarjeta.add(lblTitulo);
        tarjeta.add(Box.createVerticalGlue());

        // Contenedor para permitir animación flotante
        JPanel contenedor = new JPanel(null);
        contenedor.setPreferredSize(new Dimension(280, 170));
        contenedor.setBackground(new Color(0, 0, 0, 0));
        contenedor.add(tarjeta);
        tarjeta.setLocation(5, 5);

        tarjeta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        tarjeta.addMouseListener(new MouseAdapter() {
            Timer timerArriba, timerAbajo;

            public void mouseEntered(MouseEvent e) {
                if (timerAbajo != null && timerAbajo.isRunning()) {
                    timerAbajo.stop();
                }
                timerArriba = new Timer(10, null);
                timerArriba.addActionListener(ev -> {
                    Point p = tarjeta.getLocation();
                    if (p.y > 2) {
                        tarjeta.setLocation(p.x, p.y - 1);
                    } else {
                        timerArriba.stop();
                    }
                });
                timerArriba.start();

                tarjeta.setBackground(new Color(245, 245, 245));
                tarjeta.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(30, 136, 229), 2),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
            }

            public void mouseExited(MouseEvent e) {
                if (timerArriba != null && timerArriba.isRunning()) {
                    timerArriba.stop();
                }
                timerAbajo = new Timer(10, null);
                timerAbajo.addActionListener(ev -> {
                    Point p = tarjeta.getLocation();
                    if (p.y < 5) {
                        tarjeta.setLocation(p.x, p.y + 1);
                    } else {
                        timerAbajo.stop();
                    }
                });
                timerAbajo.start();

                tarjeta.setBackground(Color.WHITE);
                tarjeta.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
            }
        });

        // Animación de número
       // Animación de número
new Thread(() -> {
    int valorActual = 0;
    int delay = 70;
    int paso = Math.max(1, valorFinal / 25);
    while (valorActual < valorFinal) {
        valorActual = Math.min(valorActual + paso, valorFinal);
        final int mostrar = valorActual;
        SwingUtilities.invokeLater(() -> lblValor.setText(String.valueOf(mostrar)));
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}).start();
        return contenedor;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(13, 71, 161));
        jPanel1.setPreferredSize(new java.awt.Dimension(270, 640));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(750, 150));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Bienvenidos a la Biblioteca");

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(589, 589, 589))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addGap(486, 486, 486))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new Inicio().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
