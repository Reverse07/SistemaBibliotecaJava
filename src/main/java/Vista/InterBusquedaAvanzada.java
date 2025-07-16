package Vista;

import Controlador.CategoriaDAO;
import Controlador.LibroDAO;
import Modelo.Categoria;
import Modelo.Libro;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class InterBusquedaAvanzada extends javax.swing.JPanel {

    private final LibroDAO libroDAO = new LibroDAO();

    public InterBusquedaAvanzada() {
        initComponents2();
        cargarCategorias();
        agregarEventos();
    }

    private void initComponents2() {
        setLayout(new BorderLayout());
        setBackground(new Color(227, 242, 253)); // Fondo suave

        // 🔷 Título
        jLabel1 = new JLabel("🔎 Búsqueda Avanzada de Libros", SwingConstants.CENTER);
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 26));
        jLabel1.setForeground(new Color(25, 118, 210)); // Azul elegante
        jLabel1.setBorder(new EmptyBorder(20, 10, 20, 10));
        add(jLabel1, BorderLayout.NORTH);

        jPanel1 = new JPanel();
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel1.setBackground(getBackground());
        jPanel1.setBorder(new EmptyBorder(10, 50, 10, 50));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 15);

        // 🔹 ISBN
        JPanel panelISBN = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelISBN.setBackground(getBackground());
        jLabel2 = new JLabel("ISBN:");
        jLabel2.setFont(labelFont);
        jTextField1 = new JTextField(20);
        jTextField1.setFont(inputFont);
        panelISBN.add(jLabel2);
        panelISBN.add(jTextField1);
        jPanel1.add(panelISBN);

        // 🔹 Título
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTitulo.setBackground(getBackground());
        jLabel3 = new JLabel("Título:");
        jLabel3.setFont(labelFont);
        jTextField2 = new JTextField(20);
        jTextField2.setFont(inputFont);
        panelTitulo.add(jLabel3);
        panelTitulo.add(jTextField2);
        jPanel1.add(panelTitulo);

        // 🔹 Categoría
        JPanel panelCategoria = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCategoria.setBackground(getBackground());
        lblBuscar = new JLabel("Categoría:");
        lblBuscar.setFont(labelFont);
        jComboBox1 = new JComboBox<>();
        jComboBox1.setPreferredSize(new Dimension(220, 25));
        jComboBox1.setFont(inputFont);
        panelCategoria.add(lblBuscar);
        panelCategoria.add(jComboBox1);
        jPanel1.add(panelCategoria);

        // 🔹 Botón
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(getBackground());
        btnBuscar = new RoundedButton("Buscar", cargarIcono("Buscar.png", 20, 20));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBackground(new Color(25, 118, 210));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnBuscar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelBoton.add(btnBuscar);
        jPanel1.add(Box.createVerticalStrut(10));
        jPanel1.add(panelBoton);

        add(jPanel1, BorderLayout.CENTER);

        // 🔹 Área de resultados
        jTextArea1 = new JTextArea(8, 50);
        jTextArea1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        jTextArea1.setEditable(false);
        jTextArea1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(144, 164, 174)),
                new EmptyBorder(10, 10, 10, 10)
        ));
        jScrollPane1 = new JScrollPane(jTextArea1);
        jScrollPane1.setBorder(new EmptyBorder(10, 50, 20, 50));
        add(jScrollPane1, BorderLayout.SOUTH);
    }

    private void cargarCategorias() {
        List<Categoria> categorias = CategoriaDAO.obtenerTodasCategorias();
        for (Categoria cat : categorias) {
            jComboBox1.addItem(cat.getNombre());
        }
    }

    private void agregarEventos() {
        btnBuscar.addActionListener((ActionEvent e) -> {
            String isbn = jTextField1.getText().trim();
            String titulo = jTextField2.getText().trim();
            String categoria = (String) jComboBox1.getSelectedItem();
            StringBuilder resultado = new StringBuilder();

            boolean seRealizoBusqueda = false;

            if (!isbn.isEmpty()) {
                Libro l = libroDAO.buscarPorISBN(isbn);
                seRealizoBusqueda = true;
                if (l != null) {
                    resultado.append("✔️ Resultado por ISBN:\n").append(l).append("\n");
                } else {
                    resultado.append("❌ No se encontró libro con ese ISBN.\n");
                }
            }

            if (!titulo.isEmpty()) {
                List<Libro> lista = libroDAO.buscarPorTitulo(titulo);
                seRealizoBusqueda = true;
                if (!lista.isEmpty()) {
                    resultado.append("\n📚 Resultados por título:\n");
                    for (Libro l : lista) {
                        resultado.append(l).append("\n");
                    }
                } else {
                    resultado.append("\n❌ No se encontraron libros con ese título.\n");
                }
            }

            if (categoria != null && !categoria.isBlank()) {
                List<Libro> lista = libroDAO.buscarPorCategoria(categoria);
                seRealizoBusqueda = true;
                if (!lista.isEmpty()) {
                    resultado.append("\n📦 Resultados por categoría:\n");
                    for (Libro l : lista) {
                        resultado.append(l).append("\n");
                    }
                } else {
                    resultado.append("\n❌ No se encontraron libros en esa categoría.\n");
                }
            }

            if (!seRealizoBusqueda) {
                resultado.append("⚠️ Ingresa algún dato para buscar.");
            }

            jTextArea1.setText(resultado.toString());
        });
    }
    
       private ImageIcon cargarIcono(String nombreArchivo, int ancho, int alto) {
        try {
            ImageIcon icono = new ImageIcon(getClass().getResource("/img/" + nombreArchivo));
            Image imagen = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(imagen);
        } catch (Exception e) {
            System.err.println("❌ No se pudo cargar el ícono: " + nombreArchivo);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Busqueda de Libros");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        lblBuscar.setText("Buscar por ISBN:");
        jPanel1.add(lblBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 250, -1));

        btnBuscar.setText("Filtrar");
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 240, -1));

        jLabel2.setText("Buscar por titulo:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 250, -1));

        jLabel3.setText("Filtrar por Categoría:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 220, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblBuscar;
    // End of variables declaration//GEN-END:variables
}
