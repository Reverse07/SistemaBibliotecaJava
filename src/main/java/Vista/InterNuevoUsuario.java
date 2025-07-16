package Vista;

import Controlador.PrestamoDAO;
import Controlador.UsuarioDAO;
import Modelo.Prestamo;
import Modelo.RolesBiblioteca;
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

public class InterNuevoUsuario extends javax.swing.JDialog {


    public InterNuevoUsuario(java.awt.Frame parent, boolean modal) {
      super(parent, modal);
        setTitle("Registrar Nuevo Usuario");
        setSize(600, 480);
        setLocationRelativeTo(parent);
        setResizable(false);
        initComponents2();
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

        Font fuente = new Font("Segoe UI", Font.BOLD, 15);
        Color colorTexto = new Color(30, 30, 30);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        jLabel1 = new JLabel("Registrar Nuevo Usuario");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        jLabel1.setForeground(Color.BLACK);

        jLabel_nombre = new JLabel("Nombre:");
        jLabel_nombre.setFont(fuente);
        jLabel_nombre.setForeground(colorTexto);
        txt_nombre = new JTextField(20);

        jLabel_apellido = new JLabel("Apellido:");
        jLabel_apellido.setFont(fuente);
        jLabel_apellido.setForeground(colorTexto);
        txt_apellido = new JTextField(20);

        jLabel_DNI = new JLabel("DNI:");
        jLabel_DNI.setFont(fuente);
        jLabel_DNI.setForeground(colorTexto);
        txt_DNI = new JTextField(20);

        jLabel_correo = new JLabel("Correo:");
        jLabel_correo.setFont(fuente);
        jLabel_correo.setForeground(colorTexto);
        txt_correo = new JTextField(20);

        jLabel_telefono = new JLabel("Teléfono:");
        jLabel_telefono.setFont(fuente);
        jLabel_telefono.setForeground(colorTexto);
        txt_telefono = new JTextField(20);

        jLabel_Rol = new JLabel("Rol:");
        jLabel_Rol.setFont(fuente);
        jLabel_Rol.setForeground(colorTexto);
        jComboBox2 = new JComboBox<>(new String[]{"Administrador", "Bibliotecario", "Lector"});

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

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        jPanel1.add(jLabel1, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        jPanel1.add(jLabel_nombre, gbc);
        gbc.gridx = 1;
        jPanel1.add(txt_nombre, gbc);

        gbc.gridx = 0; gbc.gridy++;
        jPanel1.add(jLabel_apellido, gbc);
        gbc.gridx = 1;
        jPanel1.add(txt_apellido, gbc);

        gbc.gridx = 0; gbc.gridy++;
        jPanel1.add(jLabel_DNI, gbc);
        gbc.gridx = 1;
        jPanel1.add(txt_DNI, gbc);

        gbc.gridx = 0; gbc.gridy++;
        jPanel1.add(jLabel_correo, gbc);
        gbc.gridx = 1;
        jPanel1.add(txt_correo, gbc);

        gbc.gridx = 0; gbc.gridy++;
        jPanel1.add(jLabel_telefono, gbc);
        gbc.gridx = 1;
        jPanel1.add(txt_telefono, gbc);

        gbc.gridx = 0; gbc.gridy++;
        jPanel1.add(jLabel_Rol, gbc);
        gbc.gridx = 1;
        jPanel1.add(jComboBox2, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setOpaque(false);
        panelBotones.add(jButton_Guardar);
        panelBotones.add(jButton_Cancelar);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        jPanel1.add(panelBotones, gbc);

        setContentPane(jPanel1);

        jButton_Cancelar.addActionListener(e -> dispose());
        jButton_Guardar.addActionListener(e -> jButton_GuardarActionPerformed(null));
    }

    private ImageIcon cargarIcono(String nombre, int ancho, int alto) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/" + nombre));
        Image img = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel_nombre = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_apellido = new javax.swing.JLabel();
        txt_DNI = new javax.swing.JTextField();
        jLabel_DNI = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel_correo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton_Guardar = new javax.swing.JButton();
        jButton_Cancelar = new javax.swing.JButton();
        txt_apellido = new javax.swing.JTextField();
        txt_correo = new javax.swing.JTextField();
        jLabel_telefono = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        jLabel_Rol = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Prestamo");
        setBackground(new java.awt.Color(0, 153, 153));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel_nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_nombre.setText("Nombre :");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Nuevo Usuario");

        jLabel_apellido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_apellido.setText("Apellido: ");

        jLabel_DNI.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_DNI.setText("DNI:");

        jLabel_correo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_correo.setText("Correo:");

        jButton_Guardar.setText("Guardar");
        jButton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GuardarActionPerformed(evt);
            }
        });

        jButton_Cancelar.setText("Cancelar");

        jLabel_telefono.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_telefono.setText("Telefono:");

        jLabel_Rol.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Rol.setText("Rol:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(76, 125, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton_Guardar)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel_Rol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton_Cancelar)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel_telefono)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel_correo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_correo))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel_DNI)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_DNI))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_nombre))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel_apellido)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_nombre)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_DNI, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_DNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_correo)
                            .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_telefono))
                    .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Rol)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Guardar)
                    .addComponent(jButton_Cancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GuardarActionPerformed
    // 1. Obtener datos desde los campos
    String nombre = txt_nombre.getText().trim();
    String apellido = txt_apellido.getText().trim();
    String dniStr = txt_DNI.getText().trim();
    String correo = txt_correo.getText().trim();
    String telefonoStr = txt_telefono.getText().trim();
    String rolSeleccionado = (String) jComboBox2.getSelectedItem();

    // 2. Validación de campos vacíos
    if (nombre.isEmpty() || apellido.isEmpty() || dniStr.isEmpty() || 
        correo.isEmpty() || telefonoStr.isEmpty() || rolSeleccionado == null) {
        
        JOptionPane.showMessageDialog(this, "⚠️ Por favor, complete todos los campos.",
                "Campos vacíos", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 3. Validación de formato numérico
    int dni, telefono;
    try {
        dni = Integer.parseInt(dniStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "❌ El DNI debe contener solo números.",
                "DNI inválido", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        telefono = Integer.parseInt(telefonoStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "❌ El teléfono debe contener solo números.",
                "Teléfono inválido", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // 4. Validación de correo electrónico
    if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
        JOptionPane.showMessageDialog(this, "❌ Ingrese un correo válido.",
                "Correo inválido", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // 5. Crear objeto Usuario
    Usuario nuevoUsuario = new Usuario();
    nuevoUsuario.setNombre(nombre);
    nuevoUsuario.setApellido(apellido);
    nuevoUsuario.setDni(dni);
    nuevoUsuario.setCorreo(correo);
    nuevoUsuario.setTelefono(telefono);

  
    // Supongamos que tienes:
RolesBiblioteca rol = new RolesBiblioteca();
if (rolSeleccionado.equals("Administrador")) {
    rol.setId(1); // Suponiendo que 1 es el id del rol "Estudiante"
} else if (rolSeleccionado.equals("Bibliotecario")) {
    rol.setId(2); // Suponiendo que 2 es el id del rol "Administrador"
} else if (rolSeleccionado.equals("Lector")){
    rol.setId(3);
}
rol.setNombre(rolSeleccionado);
nuevoUsuario.setRol(rol);


    // 6. Insertar en base de datos
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    boolean exito = usuarioDAO.insertar(nuevoUsuario);

    if (exito) {
        JOptionPane.showMessageDialog(this, "✅ Usuario registrado correctamente.");
        dispose(); // Cierra la ventana actual
    } else {
        JOptionPane.showMessageDialog(this, "❌ Error al guardar el usuario.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton_GuardarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Guardar;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_DNI;
    private javax.swing.JLabel jLabel_Rol;
    private javax.swing.JLabel jLabel_apellido;
    private javax.swing.JLabel jLabel_correo;
    private javax.swing.JLabel jLabel_nombre;
    private javax.swing.JLabel jLabel_telefono;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txt_DNI;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_correo;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}
