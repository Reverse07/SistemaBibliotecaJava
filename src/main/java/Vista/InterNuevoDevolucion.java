package Vista;

import Controlador.DevolucionDAO;
import Controlador.PrestamoDAO;
import Controlador.UsuarioDAO;
import Modelo.Devolucion;
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

public class InterNuevoDevolucion extends javax.swing.JDialog {

    private JComboBox<String> comboUsuario;
    private JTextField txtFechaPrestamo;
    private JTextField txtFechaDevolucion;
    private JComboBox<String> comboEstado;
    private JButton btnGuardar, btnCancelar;
    private ImageIcon iconGuardar, iconCancelar;
    private InterDevolucion panelDevolucion;

    public InterNuevoDevolucion(java.awt.Frame parent, boolean modal, InterDevolucion panelDevolucion) {
        super(parent, modal);
        this.panelDevolucion = panelDevolucion;
        setTitle("Nueva Devolución");
        setSize(500, 350);
        setLocationRelativeTo(parent);
        setResizable(false);
        initComponents2();
        cargarPrestamosActivos();
    }

    private void initComponents2() {
        jPanel1 = new JPanel(new GridBagLayout()) {
            Image bg = new ImageIcon(getClass().getResource("/img/fondoLibreria.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        jPanel1.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fuente = new Font("Segoe UI", Font.BOLD, 15);
        Color colorTexto = new Color(30, 30, 30);

        // Etiqueta: Préstamo
        jLabel_prestamo = new JLabel(" ID Préstamo:", cargarIcono("prestamos.png", 20, 20), JLabel.LEFT);
        jLabel_prestamo.setFont(fuente);
        jLabel_prestamo.setForeground(colorTexto);

        jComboBox1 = new JComboBox<>();
        jComboBox1.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Etiqueta: Fecha Devolución
        jLabel_fechaDevolucion = new JLabel(" Fecha Devolución:", cargarIcono("calendario.png", 20, 20), JLabel.LEFT);
        jLabel_fechaDevolucion.setFont(fuente);
        jLabel_fechaDevolucion.setForeground(colorTexto);

        txt_FechaDevolucion = new JTextField();
        txt_FechaDevolucion.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Etiqueta: Observaciones
        jLabel_observaciones = new JLabel(" Observaciones:", cargarIcono("observaciones.png", 20, 20), JLabel.LEFT);
        jLabel_observaciones.setFont(fuente);
        jLabel_observaciones.setForeground(colorTexto);

        txt_FechaDevolucion1 = new JTextField();
        txt_FechaDevolucion1.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Botones
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

        // Layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        jPanel1.add(jLabel_prestamo, gbc);
        gbc.gridx = 1;
        jPanel1.add(jComboBox1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        jPanel1.add(jLabel_fechaDevolucion, gbc);
        gbc.gridx = 1;
        jPanel1.add(txt_FechaDevolucion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        jPanel1.add(jLabel_observaciones, gbc);
        gbc.gridx = 1;
        jPanel1.add(txt_FechaDevolucion1, gbc);

        // Panel de botones
        jPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        jPanel2.setOpaque(false);
        jPanel2.add(jButton_Guardar);
        jPanel2.add(jButton_Cancelar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        jPanel1.add(jPanel2, gbc);

        setContentPane(jPanel1);

        // Eventos
        jButton_Cancelar.addActionListener(e -> dispose());
        jButton_Guardar.addActionListener(e -> jButton_GuardarActionPerformed(null));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel_prestamo = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel_observaciones = new javax.swing.JLabel();
        txt_FechaDevolucion = new javax.swing.JTextField();
        jLabel_fechaDevolucion = new javax.swing.JLabel();
        txt_FechaDevolucion1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton_Guardar = new javax.swing.JButton();
        jButton_Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Prestamo");
        setBackground(new java.awt.Color(0, 153, 153));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel_prestamo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_prestamo.setText("Prestamo :");

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione usuario:", "item1", "item2", "item3" }));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Registrar nueva devolución");

        jLabel_observaciones.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_observaciones.setText("Observaciones: ");

        jLabel_fechaDevolucion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_fechaDevolucion.setText("Fecha Devolucion: ");

        jButton_Guardar.setText("Guardar");
        jButton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GuardarActionPerformed(evt);
            }
        });

        jButton_Cancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel_observaciones)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_FechaDevolucion))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel_prestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton_Guardar)
                                .addGap(72, 72, 72)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_Cancelar))
                                .addGap(19, 19, 19))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel_fechaDevolucion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_FechaDevolucion1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
                .addGap(79, 79, 79))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_prestamo)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_FechaDevolucion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_fechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_FechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_observaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Guardar)
                    .addComponent(jButton_Cancelar))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GuardarActionPerformed
        String prestamoSeleccionado = (String) jComboBox1.getSelectedItem();
        String fechaTexto = txt_FechaDevolucion.getText().trim();
        String observaciones = txt_FechaDevolucion1.getText().trim();

        // Validación básica
        if (prestamoSeleccionado == null || prestamoSeleccionado.isEmpty() || fechaTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Completa todos los campos requeridos.");
            return;
        }

        // Extraer ID del préstamo (antes del guion)
        int idPrestamo;
        try {
            String[] partes = prestamoSeleccionado.split(" - ");
            idPrestamo = Integer.parseInt(partes[0]);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error al obtener el ID del préstamo.");
            return;
        }

        // Validar formato de fecha
        java.sql.Date fechaSQL;
        try {
            fechaSQL = java.sql.Date.valueOf(fechaTexto); // formato: yyyy-MM-dd
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "❌ Formato de fecha inválido. Usa yyyy-MM-dd.");
            return;
        }

        // Crear y registrar la devolución
        Devolucion d = new Devolucion();
        d.setPrestamo(new Prestamo(idPrestamo));
        d.setFechaDevolucion(fechaSQL);
        d.setObservaciones(observaciones);

        boolean exito = new DevolucionDAO().registrarDevolucion(d);
        if (exito) {
            JOptionPane.showMessageDialog(this, "✅ Devolución registrada correctamente.");
            dispose(); // Cierra el diálogo
        } else {
            JOptionPane.showMessageDialog(this, "❌ Error al guardar la devolución.");
        }
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

    private void cargarPrestamosActivos() {
        PrestamoDAO dao = new PrestamoDAO();
        List<Prestamo> lista = dao.obtenerPrestamosActivos();

        jComboBox1.removeAllItems();
        for (Prestamo p : lista) {
            jComboBox1.addItem(p.getId() + " - " + p.getUsuario().getNombre() + " " + p.getUsuario().getApellido());
        }
    }//GEN-LAST:event_jButton_GuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Guardar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_fechaDevolucion;
    private javax.swing.JLabel jLabel_observaciones;
    private javax.swing.JLabel jLabel_prestamo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txt_FechaDevolucion;
    private javax.swing.JTextField txt_FechaDevolucion1;
    // End of variables declaration//GEN-END:variables
}
