package Vista;

import Controlador.Conexion;
import Controlador.LibroDAO;
import Modelo.Categoria;
import Modelo.Libro;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InterNuevoLibroBiblio extends javax.swing.JDialog {

    private JLabel jLabel_isbn;
    private JTextField jTextField_isbn;
    private Libro libroEditando = null;

    private JLabel jLabel_stock;
    private JTextField jTextField_stock;

    public InterNuevoLibroBiblio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents2();
    }

    private void initComponents2() {
        setTitle("📚 Nuevo Libro");
        setSize(500, 480);
        setLocationRelativeTo(null);
        setResizable(false);

        jPanel1 = new JPanel(new GridBagLayout()) {
            Image bg = new ImageIcon(getClass().getResource("/img/fondoLibreria.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        jPanel1.setOpaque(false);

        // CONFIG GENERAL DE ESTILO
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fuenteLabel = new Font("Segoe UI", Font.BOLD, 15);
        Color colorTexto = new Color(30, 30, 30);

// 🔷 TÍTULO PRINCIPAL
        jLabel_tituloLibro = new JLabel("📘 Registro de Nuevo Libro", SwingConstants.CENTER);
        jLabel_tituloLibro.setFont(new Font("Segoe UI", Font.BOLD, 22));
        jLabel_tituloLibro.setForeground(new Color(0, 51, 102));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        jPanel1.add(jLabel_tituloLibro, gbc);
        gbc.gridwidth = 1;

// 🔹 TÍTULO
        jLabel_titulo = new JLabel(" Título:", cargarIcono("titulo.png", 20, 20), JLabel.LEFT);
        jLabel_titulo.setFont(fuenteLabel);
        jLabel_titulo.setForeground(colorTexto);
        jTextField_titulo = new JTextField();

        gbc.gridy = 1;
        gbc.gridx = 0;
        jPanel1.add(jLabel_titulo, gbc);
        gbc.gridx = 1;
        jPanel1.add(jTextField_titulo, gbc);

// 🔹 AUTOR
        jLabel3 = new JLabel(" Autor:", cargarIcono("autor.png", 20, 20), JLabel.LEFT);
        jLabel3.setFont(fuenteLabel);
        jLabel3.setForeground(colorTexto);
        jTextField_autor = new JTextField();

        gbc.gridy = 2;
        gbc.gridx = 0;
        jPanel1.add(jLabel3, gbc);
        gbc.gridx = 1;
        jPanel1.add(jTextField_autor, gbc);

// 🔹 AÑO DE PUBLICACIÓN
        jLabel_anio = new JLabel(" Año de Publicación:", cargarIcono("calendario.png", 20, 20), JLabel.LEFT);
        jLabel_anio.setFont(fuenteLabel);
        jLabel_anio.setForeground(colorTexto);
        jTextField_anio = new JTextField();

        gbc.gridy = 3;
        gbc.gridx = 0;
        jPanel1.add(jLabel_anio, gbc);
        gbc.gridx = 1;
        jPanel1.add(jTextField_anio, gbc);

// 🔹 DESCRIPCIÓN
        jLabel_descripcion = new JLabel(" Descripción:", cargarIcono("descripcion.png", 20, 20), JLabel.LEFT);
        jLabel_descripcion.setFont(fuenteLabel);
        jLabel_descripcion.setForeground(colorTexto);
        jTextField_descripcion = new JTextField();

        gbc.gridy = 4;
        gbc.gridx = 0;
        jPanel1.add(jLabel_descripcion, gbc);
        gbc.gridx = 1;
        jPanel1.add(jTextField_descripcion, gbc);

// 🔹 RUTA IMAGEN
        jLabel_rutaImagen = new JLabel(" Ruta Imagen:", cargarIcono("ruta.png", 20, 20), JLabel.LEFT);
        jLabel_rutaImagen.setFont(fuenteLabel);
        jLabel_rutaImagen.setForeground(colorTexto);
        jTextField_rutaImagen = new JTextField();

        gbc.gridy = 5;
        gbc.gridx = 0;
        jPanel1.add(jLabel_rutaImagen, gbc);
        gbc.gridx = 1;
        jPanel1.add(jTextField_rutaImagen, gbc);

// 🔹 CATEGORÍA
        jLabel_Categoria = new JLabel(" Categoría:", cargarIcono("categoria.png", 20, 20), JLabel.LEFT);
        jLabel_Categoria.setFont(fuenteLabel);
        jLabel_Categoria.setForeground(colorTexto);
        jComboBox_categoria = new JComboBox<>(new String[]{"Ficción", "Historia", "Ciencia", "Programación", "Romance", "Otro"});

        gbc.gridy = 6;
        gbc.gridx = 0;
        jPanel1.add(jLabel_Categoria, gbc);
        gbc.gridx = 1;
        jPanel1.add(jComboBox_categoria, gbc);

// 🔹 ISBN
        jLabel_isbn = new JLabel(" ISBN:", cargarIcono("isbn.png", 20, 20), JLabel.LEFT);
        jLabel_isbn.setFont(fuenteLabel);
        jLabel_isbn.setForeground(colorTexto);
        jTextField_isbn = new JTextField();

        gbc.gridy = 7;
        gbc.gridx = 0;
        jPanel1.add(jLabel_isbn, gbc);
        gbc.gridx = 1;
        jPanel1.add(jTextField_isbn, gbc);

// 🔹 STOCK
        jLabel_stock = new JLabel(" Stock:", cargarIcono("stock.jpg", 20, 20), JLabel.LEFT);
        jLabel_stock.setFont(fuenteLabel);
        jLabel_stock.setForeground(colorTexto);
        jTextField_stock = new JTextField();

        gbc.gridy = 8;
        gbc.gridx = 0;
        jPanel1.add(jLabel_stock, gbc);
        gbc.gridx = 1;
        jPanel1.add(jTextField_stock, gbc);

// 🔹 BOTONES
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

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setOpaque(false);
        panelBotones.add(jButton_Guardar);
        panelBotones.add(jButton_Cancelar);

        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        jPanel1.add(panelBotones, gbc);

// AÑADIR PANEL AL FORMULARIO PRINCIPAL
        setContentPane(jPanel1);

// EVENTOS
        jButton_Cancelar.addActionListener(e -> dispose());
        jButton_Guardar.addActionListener(e -> guardarLibro());
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

    private void guardarLibro() {
        try {
            String titulo = jTextField_titulo.getText().trim();
            String autor = jTextField_autor.getText().trim();
            String anioTexto = jTextField_anio.getText().trim();
            String descripcion = jTextField_descripcion.getText().trim();
            String ruta = jTextField_rutaImagen.getText().trim();
            String isbn = jTextField_isbn.getText().trim();
            String stockTexto = jTextField_stock.getText().trim();
            String nombreCategoria = (String) jComboBox_categoria.getSelectedItem();

            // Validaciones
            if (titulo.isEmpty() || autor.isEmpty() || anioTexto.isEmpty() || descripcion.isEmpty()
                    || ruta.isEmpty() || isbn.isEmpty() || stockTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠️ Completa todos los campos.");
                return;
            }

            int anio;
            try {
                anio = Integer.parseInt(anioTexto);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "⚠️ El año debe ser numérico.");
                return;
            }

            int stock;
            try {
                stock = Integer.parseInt(stockTexto);
                if (stock < 0) {
                    JOptionPane.showMessageDialog(this, "⚠️ El stock no puede ser negativo.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "⚠️ El stock debe ser numérico.");
                return;
            }

            File imagen = new File("src/main/resources/img/" + ruta);
            if (!imagen.exists()) {
                JOptionPane.showMessageDialog(this, "⚠️ La imagen no existe: " + imagen.getPath());
                return;
            }

            Categoria categoria = buscarCategoriaPorNombre(nombreCategoria);
            if (categoria == null) {
                JOptionPane.showMessageDialog(this, "❌ No se encontró la categoría: " + nombreCategoria);
                return;
            }

            if (libroEditando != null) {
                // Modo edición
                libroEditando.setTitulo(titulo);
                libroEditando.setAutor(autor);
                libroEditando.setAnio_publicacion(anio);
                libroEditando.setDescripcion(descripcion);
                libroEditando.setRutaImagen(ruta);
                libroEditando.setCategoria(categoria);
                libroEditando.setStock(stock);

                boolean actualizado = new LibroDAO().actualizar(libroEditando);
                if (actualizado) {
                    JOptionPane.showMessageDialog(this, "✅ Libro actualizado correctamente.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Error al actualizar el libro.");
                }
            } else {
                // Modo nuevo
                Libro nuevoLibro = new Libro();
                nuevoLibro.setTitulo(titulo);
                nuevoLibro.setAutor(autor);
                nuevoLibro.setAnio_publicacion(anio);
                nuevoLibro.setDescripcion(descripcion);
                nuevoLibro.setRutaImagen(ruta);
                nuevoLibro.setCategoria(categoria);
                nuevoLibro.setIsbn(isbn);
                nuevoLibro.setStock(stock);

                boolean insertado = new LibroDAO().insertar(nuevoLibro);
                if (insertado) {
                    JOptionPane.showMessageDialog(this, "✅ Libro guardado correctamente.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Error al guardar el libro (verifica si el ISBN ya existe).");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Categoria buscarCategoriaPorNombre(String nombre) {
        String sql = "SELECT * FROM categorias WHERE nombre = ?";
        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Categoria(rs.getInt("id"), rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void setLibroAEditar(Libro libro) {
        this.libroEditando = libro;

        jLabel_tituloLibro.setText("✏️ Editar Libro");
        jButton_Guardar.setText("Actualizar");

        jTextField_titulo.setText(libro.getTitulo());
        jTextField_autor.setText(libro.getAutor());
        jTextField_anio.setText(String.valueOf(libro.getAnio_publicacion()));
        jTextField_descripcion.setText(libro.getDescripcion());
        jTextField_rutaImagen.setText(libro.getRutaImagen());
        jTextField_isbn.setText(libro.getIsbn());
        jTextField_stock.setText(String.valueOf(libro.getStock()));
        jTextField_isbn.setEditable(false); // si ISBN es clave primaria

        jComboBox_categoria.setSelectedItem(libro.getCategoria().getNombre());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel_titulo = new javax.swing.JLabel();
        jLabel_tituloLibro = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel_anio = new javax.swing.JLabel();
        jLabel_descripcion = new javax.swing.JLabel();
        jLabel_Categoria = new javax.swing.JLabel();
        jLabel_rutaImagen = new javax.swing.JLabel();
        jTextField_autor = new javax.swing.JTextField();
        jTextField_anio = new javax.swing.JTextField();
        jTextField_titulo = new javax.swing.JTextField();
        jComboBox_categoria = new javax.swing.JComboBox<>();
        jTextField_descripcion = new javax.swing.JTextField();
        jTextField_rutaImagen = new javax.swing.JTextField();
        jButton_Guardar = new javax.swing.JButton();
        jButton_Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_titulo.setText("Incorporar Nuevo Libro");

        jLabel_tituloLibro.setText("Titulo:");

        jLabel3.setText("Autor:");

        jLabel_anio.setText("Año:");

        jLabel_descripcion.setText("Descripcion:");

        jLabel_Categoria.setText("Categoria:");

        jLabel_rutaImagen.setText("Ruta de la imagen:");

        jTextField_anio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_anioActionPerformed(evt);
            }
        });

        jTextField_titulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_tituloActionPerformed(evt);
            }
        });

        jComboBox_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel_titulo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel_anio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_anio))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_tituloLibro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_autor, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel_Categoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_categoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel_descripcion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_descripcion))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel_rutaImagen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_rutaImagen)))))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_autor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_tituloLibro)
                    .addComponent(jTextField_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_anio)
                    .addComponent(jTextField_anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Categoria)
                    .addComponent(jComboBox_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_descripcion)
                    .addComponent(jTextField_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_rutaImagen)
                    .addComponent(jTextField_rutaImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        jButton_Guardar.setText("Guardar");
        getContentPane().add(jButton_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, -1, -1));

        jButton_Cancelar.setText("Cancelar");
        getContentPane().add(jButton_Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 80, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_anioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_anioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_anioActionPerformed

    private void jTextField_tituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_tituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_tituloActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Guardar;
    private javax.swing.JComboBox<String> jComboBox_categoria;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_Categoria;
    private javax.swing.JLabel jLabel_anio;
    private javax.swing.JLabel jLabel_descripcion;
    private javax.swing.JLabel jLabel_rutaImagen;
    private javax.swing.JLabel jLabel_titulo;
    private javax.swing.JLabel jLabel_tituloLibro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField_anio;
    private javax.swing.JTextField jTextField_autor;
    private javax.swing.JTextField jTextField_descripcion;
    private javax.swing.JTextField jTextField_rutaImagen;
    private javax.swing.JTextField jTextField_titulo;
    // End of variables declaration//GEN-END:variables
}
