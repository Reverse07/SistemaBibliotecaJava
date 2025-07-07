
package Vista;

import Controlador.DevolucionDAO;
import Modelo.Devolucion;
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


public class InterDevolucion extends javax.swing.JPanel {
private DefaultTableModel modeloTabla;

    public InterDevolucion() {
      initComponents2();
        cargarTabla();
        agregarEventos();
        personalizarTabla();
    }

    private void initComponents2() {
        setLayout(new BorderLayout());
        setBackground(new Color(227, 242, 253)); // azul bibliotecario

        // Título
        jLabel1 = new JLabel("📥 Gestión de Devoluciones", SwingConstants.CENTER);
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(jLabel1, BorderLayout.NORTH);

        // Panel tabla
        jPanel1 = new JPanel(new BorderLayout());
        jPanel1.setBackground(getBackground());

        modeloTabla = new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "ID Préstamo", "Fecha Devolución", "Observaciones"}
        );

        jTable_tablaDevoluciones = new JTable(modeloTabla);
        jTable_tablaDevoluciones.setRowHeight(25);
        jTable_tablaDevoluciones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jScrollPane1 = new JScrollPane(jTable_tablaDevoluciones);
        jPanel1.add(jScrollPane1, BorderLayout.CENTER);
        add(jPanel1, BorderLayout.CENTER);

        // Panel botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(getBackground());

        jButton_nuevo = new Vista.RoundedButton("Nuevo", cargarIcono("nuevo.png", 20, 20));
        jButton_actualizar = new Vista.RoundedButton("Actualizar", cargarIcono("actualizar.png", 20, 20));
        jButton_eliminar = new Vista.RoundedButton("Eliminar", cargarIcono("eliminar.png", 20, 20));

        panelBotones.add(jButton_nuevo);
        panelBotones.add(jButton_actualizar);
        panelBotones.add(jButton_eliminar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void personalizarTabla() {
        JTableHeader header = jTable_tablaDevoluciones.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(21, 101, 192));
        header.setForeground(Color.WHITE);

        jTable_tablaDevoluciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jTable_tablaDevoluciones.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(232, 240, 254));
                } else {
                    c.setBackground(new Color(197, 225, 252));
                }
                return c;
            }
        });
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<Devolucion> devoluciones = new DevolucionDAO().obtenerTodas();
        for (Devolucion d : devoluciones) {
            modeloTabla.addRow(new Object[]{
                d.getId(),
                d.getPrestamo().getId(),
                d.getFechaDevolucion(),
                d.getObservaciones()
            });
        }
    }

    private void agregarEventos() {
        jButton_actualizar.addActionListener(e -> {
            cargarTabla();
            JOptionPane.showMessageDialog(null, "🔄 Tabla actualizada.");
        });

        jButton_eliminar.addActionListener(e -> {
            int fila = jTable_tablaDevoluciones.getSelectedRow();
            if (fila != -1) {
                int id = (int) modeloTabla.getValueAt(fila, 0);
                int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Eliminar devolución con ID: " + id + "?",
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean eliminado = new DevolucionDAO().eliminar(id);
                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "✅ Devolución eliminada.");
                        cargarTabla();
                    } else {
                        JOptionPane.showMessageDialog(null, "❌ No se pudo eliminar.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "⚠️ Selecciona una devolución.");
            }
        });

        jButton_nuevo.addActionListener(e -> {
            Frame parent = (Frame) SwingUtilities.getWindowAncestor(InterDevolucion.this);
            InterNuevoDevolucion dialogo = new InterNuevoDevolucion(parent, true);
            dialogo.setVisible(true);
            cargarTabla();
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_tablaDevoluciones = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton_nuevo = new javax.swing.JButton();
        jButton_eliminar = new javax.swing.JButton();
        jButton_actualizar = new javax.swing.JButton();

        setToolTipText("");
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 51, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("                                          Registro de Devoluciones");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 10, 20, 10));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 795, -1));

        jTable_tablaDevoluciones.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable_tablaDevoluciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "ID Prestamo", "Fecha Devolucion", "Observaciones"
            }
        ));
        jTable_tablaDevoluciones.setRowHeight(25);
        jScrollPane1.setViewportView(jTable_tablaDevoluciones);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 760, 260));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton_nuevo.setText("Nuevo");
        jPanel1.add(jButton_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, -1));

        jButton_eliminar.setText("Eliminar");
        jButton_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, -1, -1));

        jButton_actualizar.setText("Actualizar");
        jPanel1.add(jButton_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 760, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_eliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_eliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_actualizar;
    private javax.swing.JButton jButton_eliminar;
    private javax.swing.JButton jButton_nuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_tablaDevoluciones;
    // End of variables declaration//GEN-END:variables
}
