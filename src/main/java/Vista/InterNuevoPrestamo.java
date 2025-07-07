package Vista;

import Controlador.PrestamoDAO;
import Controlador.UsuarioDAO;
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

    private JComboBox<String> comboUsuario;
    private JTextField txtFechaPrestamo;
    private JTextField txtFechaDevolucion;
    private JComboBox<String> comboEstado;
    private JButton btnGuardar, btnCancelar;
    private ImageIcon iconGuardar, iconCancelar;

    public InterNuevoPrestamo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Nuevo Préstamo");
        setSize(500, 350);
        setLocationRelativeTo(parent);
        setResizable(false);
        initComponents2();
        cargarUsuarios();
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

        // Botones redondeados con íconos
        btnGuardar = new RoundedButton("Guardar", cargarIcono("guardar.png", 20, 20));
        btnCancelar = new RoundedButton("Cancelar", cargarIcono("cancelarIcono.png", 20, 20));

        // Efecto hover (opcional)
        for (JButton b : new JButton[]{btnGuardar, btnCancelar}) {
            b.setFont(new Font("Segoe UI", Font.BOLD, 14));
            b.setBackground(new Color(33, 150, 243)); // Azul claro profesional
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            b.setHorizontalTextPosition(SwingConstants.RIGHT);
            b.setIconTextGap(10);

            b.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    b.setBackground(new Color(30, 136, 229)); // más oscuro
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

        // Panel botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setOpaque(false);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        setContentPane(panel);

        // Eventos
        btnCancelar.addActionListener(e -> dispose());
        btnGuardar.addActionListener(e -> jButton_GuardarActionPerformed(null));
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Prestamo");
        setBackground(new java.awt.Color(0, 153, 153));
        setPreferredSize(new java.awt.Dimension(450, 300));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(65, 115, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(4, 4, 4)
                            .addComponent(jComboBox_estado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel_fechaDevolucion)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_FechaDevolucion))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel_fechaPrestamo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_FechaDevolucion1))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton_Guardar)
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Cancelar))
                        .addGap(19, 19, 19)))
                .addGap(79, 79, 79))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Guardar)
                    .addComponent(jButton_Cancelar))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GuardarActionPerformed
        String usuarioStr = (String) jComboBox1.getSelectedItem();
        String fechaPrestamoStr = jLabel_fechaPrestamo.getText().trim();
        String fechaDevolucionStr = txt_FechaDevolucion1.getText().trim();
        String estado = (String) jComboBox_estado.getSelectedItem();

        if (usuarioStr == null || fechaPrestamoStr.isEmpty() || fechaDevolucionStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Por favor, complete todos los campos.",
                    "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 1. Parsear fechas
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaPrestamo = sdf.parse(fechaPrestamoStr);
            Date fechaDevolucion = sdf.parse(fechaDevolucionStr);

            // 2. Obtener usuario
            int idUsuario = Integer.parseInt(usuarioStr.split(" - ")[0]);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.obtenerPorId(idUsuario);

            if (usuario == null) {
                JOptionPane.showMessageDialog(this, "❌ No se encontró el usuario.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. Crear préstamo
            Prestamo nuevoPrestamo = new Prestamo();
            nuevoPrestamo.setUsuario(usuario);
            nuevoPrestamo.setFechaPrestamo(fechaPrestamo);
            nuevoPrestamo.setFechaDevolucion(fechaDevolucion);
            nuevoPrestamo.setEstado(estado);

            // 4. Insertar
            PrestamoDAO prestamoDAO = new PrestamoDAO();
            boolean exito = prestamoDAO.insertar(nuevoPrestamo);

            if (exito) {
                JOptionPane.showMessageDialog(this, "✅ Préstamo registrado con éxito.");
                dispose(); // Cierra ventana
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
        for (Usuario u : lista) {
            comboUsuario.addItem(u.getId() + " - " + u.getNombre() + " " + u.getApellido());
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Guardar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox_estado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_fechaDevolucion;
    private javax.swing.JLabel jLabel_fechaPrestamo;
    private javax.swing.JLabel jLabel_usuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txt_FechaDevolucion;
    private javax.swing.JTextField txt_FechaDevolucion1;
    // End of variables declaration//GEN-END:variables
}
