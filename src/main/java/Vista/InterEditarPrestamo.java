
package Vista;

import Controlador.PrestamoDAO;
import Controlador.UsuarioDAO;
import Modelo.Prestamo;
import Modelo.Usuario;
import com.formdev.flatlaf.json.ParseException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class InterEditarPrestamo extends javax.swing.JDialog {
private JTextField txtFechaPrestamo, txtFechaDevolucion;
    private JComboBox<Usuario> comboUsuario;
    private JComboBox<String> comboEstado;
    private JButton btnGuardar, btnCancelar;

    private Prestamo prestamoOriginal;
    private List<Usuario> listaUsuarios = new UsuarioDAO().obtenerTodos(); // asegúrate de tener este DAO

    public InterEditarPrestamo(Frame parent, Prestamo prestamo) {
        super(parent, "✏️ Editar Préstamo", true);
        this.prestamoOriginal = prestamo;
        setSize(480, 360);
        setLocationRelativeTo(parent);
        initComponents2();
        cargarDatos();
    }

    private void initComponents2() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fuente = new Font("Segoe UI", Font.BOLD, 15);
        Color colorTexto = new Color(30, 30, 30);

        // Etiquetas
        JLabel lblUsuario = new JLabel("👤 Usuario:");
        lblUsuario.setFont(fuente); lblUsuario.setForeground(colorTexto);
        comboUsuario = new JComboBox<>();
        for (Usuario u : listaUsuarios) comboUsuario.addItem(u);

        JLabel lblFechaPrestamo = new JLabel("📅 Fecha Préstamo:");
        lblFechaPrestamo.setFont(fuente); lblFechaPrestamo.setForeground(colorTexto);
        txtFechaPrestamo = new JTextField();

        JLabel lblFechaDevolucion = new JLabel("📅 Fecha Devolución:");
        lblFechaDevolucion.setFont(fuente); lblFechaDevolucion.setForeground(colorTexto);
        txtFechaDevolucion = new JTextField();

        JLabel lblEstado = new JLabel("📌 Estado:");
        lblEstado.setFont(fuente); lblEstado.setForeground(colorTexto);
        comboEstado = new JComboBox<>(new String[]{"Activo", "Devuelto"});

        // Posicionamiento
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblUsuario, gbc);
        gbc.gridx = 1;
        panel.add(comboUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblFechaPrestamo, gbc);
        gbc.gridx = 1;
        panel.add(txtFechaPrestamo, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblFechaDevolucion, gbc);
        gbc.gridx = 1;
        panel.add(txtFechaDevolucion, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(lblEstado, gbc);
        gbc.gridx = 1;
        panel.add(comboEstado, gbc);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnGuardar = new RoundedButton("Guardar", cargarIcono("guardar.png", 20, 20));
        btnCancelar = new RoundedButton("Cancelar", cargarIcono("cancelarIcono.png", 20, 20));

        for (JButton b : new JButton[]{btnGuardar, btnCancelar}) {
            b.setFont(new Font("Segoe UI", Font.BOLD, 14));
            b.setBackground(new Color(33, 150, 243));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            b.setHorizontalTextPosition(SwingConstants.RIGHT);
            b.setIconTextGap(10);
        }

        btnGuardar.addActionListener(e -> {
            try {
                guardarCambios();
            } catch (java.text.ParseException ex) {
                Logger.getLogger(InterEditarPrestamo.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarDatos() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        txtFechaPrestamo.setText(sdf.format(prestamoOriginal.getFechaPrestamo()));
        txtFechaDevolucion.setText(sdf.format(prestamoOriginal.getFechaDevolucion()));
        comboEstado.setSelectedItem(prestamoOriginal.getEstado());

        // Seleccionar usuario en combo
        for (int i = 0; i < comboUsuario.getItemCount(); i++) {
            Usuario u = comboUsuario.getItemAt(i);
            if (u.getId() == prestamoOriginal.getUsuario().getId()) {
                comboUsuario.setSelectedIndex(i);
                break;
            }
        }
    }

    private void guardarCambios() throws java.text.ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaPrestamo = sdf.parse(txtFechaPrestamo.getText());
            Date fechaDevolucion = sdf.parse(txtFechaDevolucion.getText());
            Usuario usuario = (Usuario) comboUsuario.getSelectedItem();
            String estado = (String) comboEstado.getSelectedItem();

            Prestamo prestamoEditado = new Prestamo();
            prestamoEditado.setId(prestamoOriginal.getId());
            prestamoEditado.setUsuario(usuario);
            prestamoEditado.setFechaPrestamo(fechaPrestamo);
            prestamoEditado.setFechaDevolucion(fechaDevolucion);
            prestamoEditado.setEstado(estado);

            boolean actualizado = new PrestamoDAO().actualizar(prestamoEditado);

            if (actualizado) {
                JOptionPane.showMessageDialog(this, "✅ Préstamo actualizado correctamente.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al actualizar el préstamo.");
            }

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "❌ Formato de fecha inválido. Usa yyyy-MM-dd.");
        }
    }

    private ImageIcon cargarIcono(String archivo, int w, int h) {
        try {
            Image img = new ImageIcon(getClass().getResource("/img/" + archivo)).getImage();
            return new ImageIcon(img.getScaledInstance(w, h, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            System.err.println("⚠️ Error cargando ícono: " + archivo);
            return null;
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_fechaPrestamo = new javax.swing.JLabel();
        jLabel_FechaDevolucion = new javax.swing.JLabel();
        jLabel_estado = new javax.swing.JLabel();
        jLabel_usuario = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Prestamo");
        setPreferredSize(new java.awt.Dimension(500, 350));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Editar Prestamo");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 150, -1));

        jLabel_fechaPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_fechaPrestamo.setText("Fecha Prestamo:");
        jPanel1.add(jLabel_fechaPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel_FechaDevolucion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_FechaDevolucion.setText("Fecha Devolucion:");
        jPanel1.add(jLabel_FechaDevolucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel_estado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_estado.setText("Estado:");
        jPanel1.add(jLabel_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel_usuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_usuario.setText("Usuario:");
        jPanel1.add(jLabel_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 210, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 160, -1));
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 150, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 210, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_FechaDevolucion;
    private javax.swing.JLabel jLabel_estado;
    private javax.swing.JLabel jLabel_fechaPrestamo;
    private javax.swing.JLabel jLabel_usuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
