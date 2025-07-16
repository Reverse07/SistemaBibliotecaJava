
package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class VentanaPrincipal extends JFrame {
    
     private JPanel panelMenu;
    private JPanel panelContenedor;

    public VentanaPrincipal() {
        initUI();
    }

    private void initUI() {
        setTitle("📚 Sistema de Gestión de Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700); // ✅ Ajusta el tamaño a tu gusto
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        crearMenuLateral();
        crearPanelContenedor();

        add(panelMenu, BorderLayout.WEST);
        add(panelContenedor, BorderLayout.CENTER);

        setVisible(true);
    }

    private void crearMenuLateral() {
        panelMenu = new JPanel();
        panelMenu.setBackground(new Color(26, 35, 126)); // Azul oscuro
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        panelMenu.setPreferredSize(new Dimension(200, getHeight()));

        JLabel lblTitulo = new JLabel("📖 Librería");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton btnPrincipal = crearBotonMenu("🏠 Principal");
        JButton btnPrestamos = crearBotonMenu("📄 Préstamos");
        JButton btnDevoluciones = crearBotonMenu("📦 Devoluciones");
        JButton btnUsuarios = crearBotonMenu("👤 Usuarios");
        JButton btnLibros = crearBotonMenu("📚 Libros");
        JButton btnReportes = crearBotonMenu("📊 Reportes");
        JButton btnBusqueda = crearBotonMenu("🔍 Búsqueda");

        // 👉 Añadir listeners
        btnPrincipal.addActionListener(e -> mostrarPanel(new JLabel("📌 Bienvenido a Principal", SwingConstants.CENTER)));
        btnPrestamos.addActionListener(e -> mostrarPanel(new JLabel("📌 Módulo de Préstamos", SwingConstants.CENTER)));
        btnDevoluciones.addActionListener(e -> mostrarPanel(new JLabel("📌 Módulo de Devoluciones", SwingConstants.CENTER)));
        btnUsuarios.addActionListener(e -> mostrarPanel(new JLabel("📌 Módulo de Usuarios", SwingConstants.CENTER)));
        btnLibros.addActionListener(e -> mostrarPanel(new JLabel("📌 Módulo de Libros", SwingConstants.CENTER)));
        btnReportes.addActionListener(e -> mostrarPanelReportes());
        btnBusqueda.addActionListener(e -> mostrarPanel(new JLabel("📌 Módulo de Búsqueda", SwingConstants.CENTER)));

        panelMenu.add(lblTitulo);
        panelMenu.add(btnPrincipal);
        panelMenu.add(btnPrestamos);
        panelMenu.add(btnDevoluciones);
        panelMenu.add(btnUsuarios);
        panelMenu.add(btnLibros);
        panelMenu.add(btnReportes);
        panelMenu.add(btnBusqueda);
    }

    private void crearPanelContenedor() {
        panelContenedor = new JPanel();
        panelContenedor.setLayout(new BorderLayout());
        panelContenedor.setBackground(Color.WHITE);
    }

    private JButton crearBotonMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(26, 35, 126));
        btn.setForeground(Color.WHITE);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(63, 81, 181)); // Hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(26, 35, 126)); // Default
            }
        });
        return btn;
    }

    private void mostrarPanel(JComponent panel) {
        panelContenedor.removeAll();
        panelContenedor.setLayout(new BorderLayout());
        panelContenedor.add(panel, BorderLayout.CENTER);
        panelContenedor.revalidate();
        panelContenedor.repaint();
    }

    private void mostrarPanelReportes() {
        InterReportes panelReportes = new InterReportes();
        mostrarPanel(panelReportes);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}

