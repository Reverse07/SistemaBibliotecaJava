package Vista;


import Controlador.UsuarioDAO;
import Modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class InterUsuario extends javax.swing.JPanel {
private DefaultTableModel modeloTabla;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public InterUsuario() {
        initComponents2();
        modeloTabla = (DefaultTableModel) tablaUsuarios.getModel();
        configurarTabla();
        agregarEventos();
        cargarTablaUsuarios();
    }

    private void initComponents2() {
        setLayout(new BorderLayout());
        setBackground(new Color(227, 242, 253)); // Azul bibliotecario

        // Título
        jLabel1 = new JLabel("👤 Gestión de Usuarios", SwingConstants.CENTER);
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(jLabel1, BorderLayout.NORTH);

        // Panel tabla
        jPanel1 = new JPanel(new BorderLayout());
        jPanel1.setBackground(getBackground());

        tablaUsuarios = new JTable(new DefaultTableModel(
            new Object[][]{}, new String[]{"ID", "Nombre", "Apellido", "DNI", "Correo", "Teléfono", "Rol"}
        ));
        tablaUsuarios.setRowHeight(25);
        tablaUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JTableHeader header = tablaUsuarios.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(21, 101, 192));
        header.setForeground(Color.WHITE);

        tablaUsuarios.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(232, 240, 254));
                } else {
                    c.setBackground(new Color(197, 225, 252));
                }
                return c;
            }
        });

        jScrollPane2 = new JScrollPane(tablaUsuarios);
        jPanel1.add(jScrollPane2, BorderLayout.CENTER);
        add(jPanel1, BorderLayout.CENTER);

        // Panel botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(getBackground());

        jButton_Nuevo = new RoundedButton("Nuevo", cargarIcono("nuevo.png", 20, 20));
        jButton_Editar = new RoundedButton("Editar", cargarIcono("editar.png", 20, 20));
        jButton_Actualizar = new RoundedButton("Actualizar", cargarIcono("actualizar.png", 20, 20));
        jButton_Eliminar = new RoundedButton("Eliminar", cargarIcono("eliminar.png", 20, 20));

        panelBotones.add(jButton_Nuevo);
        panelBotones.add(jButton_Editar);
        panelBotones.add(jButton_Actualizar);
        panelBotones.add(jButton_Eliminar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void configurarTabla() {
        tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void cargarTablaUsuarios() {
        modeloTabla.setRowCount(0);
        List<Usuario> usuarios = usuarioDAO.obtenerTodos();
        for (Usuario u : usuarios) {
            modeloTabla.addRow(new Object[]{
                u.getId(),
                u.getNombre(),
                u.getApellido(),
                u.getDni(),
                u.getCorreo(),
                u.getTelefono(),
                u.getRol().getNombre()
            });
        }
    }

    private void agregarEventos() {
        jButton_Actualizar.addActionListener(e -> {
            cargarTablaUsuarios();
            JOptionPane.showMessageDialog(this, "🔄 Tabla actualizada.");
        });

        jButton_Nuevo.addActionListener(e -> {
             java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
            InterNuevoUsuario nuevo = new InterNuevoUsuario((java.awt.Frame) parentWindow, true);
            nuevo.setVisible(true);
        });

        jButton_Editar.addActionListener(e -> {
           int fila = tablaUsuarios.getSelectedRow();
    if (fila != -1) {
        int id = (int) modeloTabla.getValueAt(fila, 0);  // ID del usuario
        Usuario usuario = usuarioDAO.obtenerPorId(id);
        if (usuario != null) {
            java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
            InterEditarUsuario editar = new InterEditarUsuario((java.awt.Frame) parentWindow, usuario);
            editar.setVisible(true);

            // Recargar tabla después de edición
            cargarTablaUsuarios();
        } else {
            JOptionPane.showMessageDialog(this, "❌ Usuario no encontrado.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "⚠️ Selecciona un usuario para editar.");
    }
});

        jButton_Eliminar.addActionListener(e -> {
            int fila = tablaUsuarios.getSelectedRow();
            if (fila != -1) {
                int id = (int) modeloTabla.getValueAt(fila, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar usuario con ID: " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean eliminado = usuarioDAO.eliminar(id);
                    if (eliminado) {
                        JOptionPane.showMessageDialog(this, "✅ Usuario eliminado.");
                        cargarTablaUsuarios();
                    } else {
                        JOptionPane.showMessageDialog(this, "❌ No se pudo eliminar.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Selecciona un usuario para eliminar.");
            }
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton_Nuevo = new javax.swing.JButton();
        jButton_Editar = new javax.swing.JButton();
        jButton_Eliminar = new javax.swing.JButton();
        jButton_Actualizar = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(0, 0, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Lista de Prestamos");
        jPanel1.add(jLabel1);

        tablaUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        tablaUsuarios.setForeground(new java.awt.Color(0, 51, 255));
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Apellido", "DNI", "Correo", "Telefono", "Rol"
            }
        ));
        jScrollPane2.setViewportView(tablaUsuarios);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton_Nuevo.setText("Nuevo");
        jPanel3.add(jButton_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 90, -1));

        jButton_Editar.setText("Actualizar");
        jPanel3.add(jButton_Editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 90, -1));

        jButton_Eliminar.setText("Editar");
        jPanel3.add(jButton_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 90, -1));

        jButton_Actualizar.setText("Eliminar");
        jPanel3.add(jButton_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 90, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_Editar;
    private javax.swing.JButton jButton_Eliminar;
    private javax.swing.JButton jButton_Nuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaUsuarios;
    // End of variables declaration//GEN-END:variables
}
