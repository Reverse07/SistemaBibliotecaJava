package Vista;

import Controlador.LibroDAO;
import Modelo.Libro;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InterLibros extends javax.swing.JPanel {

    private Libro libroSeleccionado;

    public InterLibros() {
        initComponents();
        configurarInterfaz();
    }

    private void configurarInterfaz() {
        setLayout(new BorderLayout());
        setBackground(new Color(227, 242, 253));

        // Título
        jLabel1 = new JLabel("📚 Catálogo de Libros", JLabel.CENTER);
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 26));
        jLabel1.setBorder(BorderFactory.createEmptyBorder(25, 0, 15, 0));
        add(jLabel1, BorderLayout.NORTH);

        // Panel de botones
        jPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        jPanel1.setBackground(Color.WHITE);

        // Cargar íconos escalados
        ImageIcon iconNuevo = escalarIcono("/img/nuevo.png", 20, 20);
        ImageIcon iconEditar = escalarIcono("/img/editar.png", 20, 20);
        ImageIcon iconEliminar = escalarIcono("/img/eliminar.png", 20, 20);
        ImageIcon iconActualizar = escalarIcono("/img/actualizar.png", 20, 20);

        jButtonNuevo = new RoundedButton("Nuevo", iconNuevo);
        jButton_Editar = new RoundedButton("Editar", iconEditar);
        jButton_Eliminar = new RoundedButton("Eliminar", iconEliminar);
        jButton_Actualizar = new RoundedButton("Actualizar", iconActualizar);

        // Botones con tamaño uniforme
        Dimension btnSize = new Dimension(140, 40);
        for (JButton btn : new JButton[]{jButtonNuevo, jButton_Editar, jButton_Eliminar, jButton_Actualizar}) {
            btn.setPreferredSize(btnSize);
            jPanel1.add(btn);
        }

        add(jPanel1, BorderLayout.SOUTH);

        // Carrusel de libros
        jPanel2 = new CarruselLibros(this);
        jPanel2.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        add(jPanel2, BorderLayout.CENTER);

        agregarEventos();
    }

    private ImageIcon escalarIcono(String ruta, int ancho, int alto) {
        URL url = getClass().getResource(ruta);
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            Image imagenEscalada = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(imagenEscalada);
        } else {
            System.err.println("❌ Icono no encontrado: " + ruta);
            return null;
        }
    }

    private void agregarEventos() {
        jButtonNuevo.addActionListener(e -> {
            java.awt.Frame parentFrame = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
            InterNuevoLibroBiblio dialog = new InterNuevoLibroBiblio(parentFrame, true);
            dialog.setVisible(true);

            // Recargar el carrusel después de cerrar el diálogo (opcional)
            remove(jPanel2);
            jPanel2 = new CarruselLibros(this);
            add(jPanel2, BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        jButton_Editar.addActionListener(e -> {
            if (libroSeleccionado != null) {
                java.awt.Frame parentFrame = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
                InterNuevoLibroBiblio dialog = new InterNuevoLibroBiblio(parentFrame, true);
                dialog.setLibroAEditar(libroSeleccionado);  // clave para edición
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un libro para editar");
            }
        });

        jButton_Eliminar.addActionListener(e -> {
            if (libroSeleccionado != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar el libro seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean eliminado = new LibroDAO().eliminar(libroSeleccionado.getId());
                    if (eliminado) {
                        JOptionPane.showMessageDialog(this, "✅ Libro eliminado correctamente");
                        libroSeleccionado = null;

                        // Refrescar carrusel
                        remove(jPanel2);
                        jPanel2 = new CarruselLibros(this);
                        add(jPanel2, BorderLayout.CENTER);
                        revalidate();
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(this, "❌ Error al eliminar el libro");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un libro para eliminar");
            }
        });

        jButton_Actualizar.addActionListener(e -> {
            remove(jPanel2);
            jPanel2 = new CarruselLibros(this);
            add(jPanel2, BorderLayout.CENTER);
            revalidate();
            repaint();
        });
    }

    public void seleccionarLibro(Libro libro) {
        this.libroSeleccionado = libro;
        JOptionPane.showMessageDialog(this,
                "<html><h3>" + libro.getTitulo() + "</h3>"
                + "<p>ISBN: " + libro.getIsbn() + "</p>"
                + "<p>Año: " + libro.getAnio_publicacion() + "</p></html>",
                "📖 Detalles del Libro", JOptionPane.INFORMATION_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonNuevo = new javax.swing.JButton();
        jButton_Editar = new javax.swing.JButton();
        jButton_Actualizar = new javax.swing.JButton();
        jButton_Eliminar = new javax.swing.JButton();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("                       Libros Disponibles");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, -1));

        jButtonNuevo.setText("Nuevo");

        jButton_Editar.setText("Editar");

        jButton_Actualizar.setText("Actualizar");
        jButton_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ActualizarActionPerformed(evt);
            }
        });

        jButton_Eliminar.setText("Eliminar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButtonNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jButton_Editar)
                .addGap(18, 18, 18)
                .addComponent(jButton_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jButton_Eliminar)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNuevo)
                    .addComponent(jButton_Editar)
                    .addComponent(jButton_Actualizar)
                    .addComponent(jButton_Eliminar))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 410, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_Editar;
    private javax.swing.JButton jButton_Eliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
