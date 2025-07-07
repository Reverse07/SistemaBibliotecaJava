/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista;


public class InterDetallesLibrosBiblioteca extends javax.swing.JDialog {


    public InterDetallesLibrosBiblioteca(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImagenLibro = new javax.swing.JLabel();
        lblAutor = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        lblAnio = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalles del Libro");
        setModal(true);
        setPreferredSize(new java.awt.Dimension(500, 450));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImagenLibro.setText("jLabel1");
        getContentPane().add(lblImagenLibro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        lblAutor.setText("Autor:");
        getContentPane().add(lblAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        lblCategoria.setText("Categoria:");
        getContentPane().add(lblCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        lblAnio.setText("Año Publicacion:");
        getContentPane().add(lblAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        lblTitulo.setText("jLabel2");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        lblStock.setText("Stock:");
        getContentPane().add(lblStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        jLabel5.setText("Descripcion:");
        jScrollPane1.setViewportView(jLabel5);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, -1, -1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 100, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 200, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblAnio;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblImagenLibro;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
