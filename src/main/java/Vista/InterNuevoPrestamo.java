package Vista;

import Controlador.DetallePrestamoDAO;
import Controlador.LibroDAO;
import Controlador.PrestamoDAO;
import Controlador.UsuarioDAO;
import Modelo.DetallePrestamo;
import Modelo.Libro;
import Modelo.Prestamo;
import Modelo.Usuario;
import com.formdev.flatlaf.json.ParseException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class InterNuevoPrestamo extends javax.swing.JDialog {

    private JComboBox<Usuario> comboUsuario;
    private JTextField txtFechaPrestamo;
    private JTextField txtFechaDevolucion;
    private JComboBox<String> comboEstado;
    private ImageIcon iconGuardar, iconCancelar;
    private JComboBox<Libro> comboLibro;

    public InterNuevoPrestamo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Nuevo Préstamo");
        setSize(500, 350);
        setLocationRelativeTo(parent);
        setResizable(false);
        initComponents2();
        cargarUsuarios();
        cargarLibrosDisponibles();
    }

    private void initComponents2() {
        JPanel panel = new JPanel(new GridBagLayout()) {
            Image bg = new ImageIcon(getClass().getResource("/img/fondoLibreria.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fuente = new Font("Segoe UI", Font.BOLD, 15);
        Color colorTexto = new Color(30, 30, 30);

        JLabel lblUsuario = new JLabel(" Usuario:", cargarIcono("usuarios.png", 20, 20), JLabel.LEFT);
        lblUsuario.setFont(fuente);
        lblUsuario.setForeground(colorTexto);

        comboUsuario = new JComboBox<>();
        comboUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblFechaPrestamo = new JLabel(" Fecha Préstamo:", cargarIcono("calendario.png", 20, 20), JLabel.LEFT);
        lblFechaPrestamo.setFont(fuente);
        lblFechaPrestamo.setForeground(colorTexto);

        txtFechaPrestamo = new JTextField();
        txtFechaPrestamo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblFechaDevolucion = new JLabel(" Fecha Devolución:", cargarIcono("calendario.png", 20, 20), JLabel.LEFT);
        lblFechaDevolucion.setFont(fuente);
        lblFechaDevolucion.setForeground(colorTexto);

        txtFechaDevolucion = new JTextField();
        txtFechaDevolucion.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblEstado = new JLabel(" Estado:", cargarIcono("estado.jpg", 20, 20), JLabel.LEFT);
        lblEstado.setFont(fuente);
        lblEstado.setForeground(colorTexto);

        comboEstado = new JComboBox<>(new String[]{"Activo", "Devuelto"});
        comboEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblLibro = new JLabel(" Libros:", cargarIcono("libros.png", 20, 20), JLabel.LEFT);
        lblLibro.setFont(fuente);
        lblLibro.setForeground(colorTexto);

        comboLibro = new JComboBox<>();
        comboLibro.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jButton_Guardar = new RoundedButton("Guardar", cargarIcono("guardar.png", 20, 20));
        jButton_Cancelar = new RoundedButton("Cancelar", cargarIcono("cancelarIcono.png", 20, 20));

        for (JButton b : new JButton[]{jButton_Guardar, jButton_Cancelar}) {
            b.setFont(new Font("Segoe UI", Font.BOLD, 14));
            b.setBackground(new Color(33, 150, 243));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            b.setHorizontalTextPosition(SwingConstants.RIGHT);
            b.setIconTextGap(10);

            b.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    b.setBackground(new Color(30, 136, 229));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    b.setBackground(new Color(33, 150, 243));
                }
            });
        }

        // Posicionar componentes
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblUsuario, gbc);
        gbc.gridx = 1;
        panel.add(comboUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblFechaPrestamo, gbc);
        gbc.gridx = 1;
        panel.add(txtFechaPrestamo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblFechaDevolucion, gbc);
        gbc.gridx = 1;
        panel.add(txtFechaDevolucion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblEstado, gbc);
        gbc.gridx = 1;
        panel.add(comboEstado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblLibro, gbc);
        gbc.gridx = 1;
        panel.add(comboLibro, gbc);

        // Panel botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setOpaque(false);
        panelBotones.add(jButton_Guardar);
        panelBotones.add(jButton_Cancelar);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        setContentPane(panel);

        // Eventos
        jButton_Cancelar.addActionListener(e -> dispose());
        jButton_Guardar.addActionListener(e -> jButton_GuardarActionPerformed(null));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel_usuario = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel_fechaPrestamo = new javax.swing.JLabel();
        txt_FechaDevolucion = new javax.swing.JTextField();
        jLabel_fechaDevolucion = new javax.swing.JLabel();
        txt_FechaDevolucion1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_estado = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jButton_Guardar = new javax.swing.JButton();
        jButton_Cancelar = new javax.swing.JButton();
        jLabel_libro = new javax.swing.JLabel();
        jComboBox_libros = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Prestamo");
        setBackground(new java.awt.Color(0, 153, 153));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel_usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_usuario.setText("Usuario :");

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione usuario:", "item1", "item2", "item3" }));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Nuevo Prestamo");

        jLabel_fechaPrestamo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_fechaPrestamo.setText("Fecha Prestamo: ");

        jLabel_fechaDevolucion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_fechaDevolucion.setText("Fecha Devolucion: ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Estado:");

        jComboBox_estado.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox_estado.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Estado:", "Habilitado", "Prestado" }));

        jButton_Guardar.setText("Guardar");
        jButton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GuardarActionPerformed(evt);
            }
        });

        jButton_Cancelar.setText("Cancelar");

        jLabel_libro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_libro.setText("Libro: ");

        jComboBox_libros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_fechaPrestamo)
                            .addComponent(jLabel_fechaDevolucion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_FechaDevolucion)
                            .addComponent(txt_FechaDevolucion1)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton_Guardar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel_libro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton_Cancelar)
                                .addGap(31, 31, 31))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox_libros, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(77, 77, 77))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_usuario)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_fechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_FechaDevolucion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_fechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_FechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_libro, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jComboBox_libros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Guardar)
                    .addComponent(jButton_Cancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GuardarActionPerformed
        String fechaPrestamoStr = txtFechaPrestamo.getText().trim();
        String fechaDevolucionStr = txtFechaDevolucion.getText().trim();
        String estado = (String) comboEstado.getSelectedItem();
        Usuario usuario = (Usuario) comboUsuario.getSelectedItem();
        Libro libroSeleccionado = (Libro) comboLibro.getSelectedItem();

        if (usuario == null || libroSeleccionado == null || fechaPrestamoStr.isEmpty() || fechaDevolucionStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Por favor, complete todos los campos.",
                    "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaPrestamo = sdf.parse(fechaPrestamoStr);
            Date fechaDevolucion = sdf.parse(fechaDevolucionStr);

            if (libroSeleccionado.getStock() <= 0) {
                JOptionPane.showMessageDialog(this, "❌ No hay stock disponible para el libro seleccionado.",
                        "Sin stock", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 1. Crear el préstamo y guardar en la base de datos
            Prestamo nuevoPrestamo = new Prestamo();
            nuevoPrestamo.setUsuario(usuario);
            nuevoPrestamo.setFechaPrestamo(fechaPrestamo);
            nuevoPrestamo.setFechaDevolucion(fechaDevolucion);
            nuevoPrestamo.setEstado(estado);

            PrestamoDAO prestamoDAO = new PrestamoDAO();
            boolean exito = prestamoDAO.insertar(nuevoPrestamo);

            if (exito) {
                // 2. Crear el detalle del préstamo
                DetallePrestamo detalle = new DetallePrestamo();
                detalle.setPrestamo(nuevoPrestamo);
                detalle.setLibro(libroSeleccionado);
                detalle.setCantidad(1); // si manejas cantidad fija

                DetallePrestamoDAO detalleDAO = new DetallePrestamoDAO();
                boolean exitoDetalle = detalleDAO.insertar(detalle);

                if (exitoDetalle) {
                    JOptionPane.showMessageDialog(this, "✅ Préstamo registrado con éxito.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "❌ El préstamo se guardó pero no se registró el detalle.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al guardar el préstamo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (ParseException pe) {
            JOptionPane.showMessageDialog(this, "❌ Formato de fecha inválido. Usa yyyy-MM-dd.",
                    "Error de fecha", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error inesperado: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarUsuarios() {
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> lista = dao.obtenerTodos();

        comboUsuario.removeAllItems();
        for (Usuario u : lista) {
            comboUsuario.addItem(u);
        }
    }//GEN-LAST:event_jButton_GuardarActionPerformed

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

    private void cargarLibrosDisponibles() {
        LibroDAO libroDAO = new LibroDAO();
        List<Libro> listaLibros = libroDAO.obtenerTodos();

        comboLibro.removeAllItems(); // ✅ aquí está el cambio
        for (Libro l : listaLibros) {
            comboLibro.addItem(l);   // ✅ aquí también
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Guardar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox_estado;
    private javax.swing.JComboBox<String> jComboBox_libros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_fechaDevolucion;
    private javax.swing.JLabel jLabel_fechaPrestamo;
    private javax.swing.JLabel jLabel_libro;
    private javax.swing.JLabel jLabel_usuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txt_FechaDevolucion;
    private javax.swing.JTextField txt_FechaDevolucion1;
    // End of variables declaration//GEN-END:variables
}
