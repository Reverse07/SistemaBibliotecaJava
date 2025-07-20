package Vista;

import Controlador.PrestamoDAO;
import Controlador.UsuarioDAO;
import Modelo.Prestamo;
import Modelo.RolesBiblioteca;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InterEditarUsuario extends javax.swing.JDialog {

    private List<RolesBiblioteca> listaRoles = new ArrayList<>();
    private Usuario usuarioOriginal;

    public InterEditarUsuario(Frame parent, Usuario usuario) {
        super(parent, "✏️ Editar Usuario", true);
        this.usuarioOriginal = usuario;
        setSize(500, 400);
        setLocationRelativeTo(parent);
        initComponents2();
        cargarRoles(); // Primero se cargan los roles
        cargarDatos(); // Luego se cargan los datos del usuario
    }

    private void initComponents2() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout()) {
            Image bg = new ImageIcon(getClass().getResource("/img/fondoLibreria.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fuente = new Font("Segoe UI", Font.BOLD, 15);
        Color colorTexto = new Color(30, 30, 30);

        jTextField_nombre = new JTextField();
        jTextField_apellido = new JTextField();
        jTextField_dni = new JTextField();
        jTextField_correo = new JTextField();
        jTextField_telefono = new JTextField();
        jComboBox1 = new JComboBox<>(); // Se usa JComboBox<String>, pero se llena con nombres

        String[] labels = {"Nombre:", "Apellido:", "DNI:", "Correo:", "Teléfono:", "Rol:"};
        JComponent[] fields = {
            jTextField_nombre, jTextField_apellido, jTextField_dni,
            jTextField_correo, jTextField_telefono, jComboBox1
        };

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(fuente);
            label.setForeground(colorTexto);
            gbc.gridx = 0;
            gbc.gridy = i;
            panel.add(label, gbc);

            gbc.gridx = 1;
            panel.add(fields[i], gbc);
        }

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        jButton_Guardar = new JButton("Guardar");
        jButton_Cancelar = new JButton("Cancelar");

        for (JButton b : new JButton[]{jButton_Guardar, jButton_Cancelar}) {
            b.setFont(new Font("Segoe UI", Font.BOLD, 14));
            b.setBackground(new Color(33, 150, 243));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        jButton_Guardar.addActionListener(e -> guardarCambios());
        jButton_Cancelar.addActionListener(e -> dispose());

        panelBotones.add(jButton_Guardar);
        panelBotones.add(jButton_Cancelar);

        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarRoles() {
        listaRoles.clear();
        jComboBox1.removeAllItems();

        listaRoles.add(new RolesBiblioteca(1, "Administrador"));
        listaRoles.add(new RolesBiblioteca(2, "Bibliotecario"));
        listaRoles.add(new RolesBiblioteca(3, "Lector"));

        for (RolesBiblioteca rol : listaRoles) {
            jComboBox1.addItem(rol.getNombre()); // Solo nombres, pero guardamos los roles aparte
        }
    }

    private void cargarDatos() {
        jTextField_nombre.setText(usuarioOriginal.getNombre());
        jTextField_apellido.setText(usuarioOriginal.getApellido());
        jTextField_dni.setText(String.valueOf(usuarioOriginal.getDni()));
        jTextField_correo.setText(usuarioOriginal.getCorreo());
        jTextField_telefono.setText(String.valueOf(usuarioOriginal.getTelefono()));

        for (int i = 0; i < listaRoles.size(); i++) {
            if (listaRoles.get(i).getId() == usuarioOriginal.getRol().getId()) {
                jComboBox1.setSelectedIndex(i);
                break;
            }
        }
    }

    private void guardarCambios() {
        String nombre = jTextField_nombre.getText().trim();
        String apellido = jTextField_apellido.getText().trim();
        String dniStr = jTextField_dni.getText().trim();
        String correo = jTextField_correo.getText().trim();
        String telefonoStr = jTextField_telefono.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || dniStr.isEmpty() || correo.isEmpty() || telefonoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int dni, telefono;
        try {
            dni = Integer.parseInt(dniStr);
            telefono = Integer.parseInt(telefonoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "❌ DNI y Teléfono deben ser numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "❌ Correo inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int selectedIndex = jComboBox1.getSelectedIndex();
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "❌ Seleccione un rol válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        RolesBiblioteca rolSeleccionado = listaRoles.get(selectedIndex);

        Usuario usuarioEditado = new Usuario();
        usuarioEditado.setId(usuarioOriginal.getId());
        usuarioEditado.setNombre(nombre);
        usuarioEditado.setApellido(apellido);
        usuarioEditado.setDni(dni);
        usuarioEditado.setCorreo(correo);
        usuarioEditado.setTelefono(telefono);
        usuarioEditado.setRol(rolSeleccionado);

        boolean actualizado = new UsuarioDAO().actualizar(usuarioEditado);

        if (actualizado) {
            JOptionPane.showMessageDialog(this, "✅ Usuario actualizado correctamente.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "❌ Error al actualizar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
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
        jLabel_apellido = new javax.swing.JLabel();
        jLabel_DNI = new javax.swing.JLabel();
        jLabel_rol = new javax.swing.JLabel();
        jLabel_nombre = new javax.swing.JLabel();
        jTextField_apellido = new javax.swing.JTextField();
        jTextField_dni = new javax.swing.JTextField();
        jTextField_nombre = new javax.swing.JTextField();
        jTextField_telefono = new javax.swing.JTextField();
        jLabel_correo = new javax.swing.JLabel();
        jTextField_correo = new javax.swing.JTextField();
        jLabel_telefono = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton_Cancelar = new javax.swing.JButton();
        jButton_Guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Prestamo");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Editar Usuario");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 150, -1));

        jLabel_apellido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_apellido.setText("Apellido:");
        jPanel1.add(jLabel_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel_DNI.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_DNI.setText("DNI:");
        jPanel1.add(jLabel_DNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel_rol.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_rol.setText("Rol:");
        jPanel1.add(jLabel_rol, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        jLabel_nombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_nombre.setText("Nombre:");
        jPanel1.add(jLabel_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));
        jPanel1.add(jTextField_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 210, -1));
        jPanel1.add(jTextField_dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 210, -1));
        jPanel1.add(jTextField_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 210, -1));
        jPanel1.add(jTextField_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 210, -1));

        jLabel_correo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_correo.setText("Correo:");
        jPanel1.add(jLabel_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));
        jPanel1.add(jTextField_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 210, -1));

        jLabel_telefono.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_telefono.setText("Telefono:");
        jPanel1.add(jLabel_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 210, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        jButton_Cancelar.setText("Cancelar");
        jButton_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CancelarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, -1, -1));

        jButton_Guardar.setText("Guardar");
        getContentPane().add(jButton_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_CancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Guardar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_DNI;
    private javax.swing.JLabel jLabel_apellido;
    private javax.swing.JLabel jLabel_correo;
    private javax.swing.JLabel jLabel_nombre;
    private javax.swing.JLabel jLabel_rol;
    private javax.swing.JLabel jLabel_telefono;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField_apellido;
    private javax.swing.JTextField jTextField_correo;
    private javax.swing.JTextField jTextField_dni;
    private javax.swing.JTextField jTextField_nombre;
    private javax.swing.JTextField jTextField_telefono;
    // End of variables declaration//GEN-END:variables
}
