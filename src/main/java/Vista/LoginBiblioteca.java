
package Vista;

import Controlador.UsuarioDAO;
import Modelo.Usuario;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import org.mindrot.jbcrypt.BCrypt;


public class LoginBiblioteca extends javax.swing.JFrame {

     private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
 
      public LoginBiblioteca() {
        setTitle("Inicio de sesión - Biblioteca");
        setSize(650, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        crearUI();
    }

    private void crearUI() {
        // Imagen de fondo
        java.net.URL imgUrl = getClass().getResource("/img/fondoLogin.jpg");
        ImageIcon imagenFondo = imgUrl != null ? new ImageIcon(imgUrl) : null;
        final ImageIcon finalImagenFondo = imagenFondo;

        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalImagenFondo != null) {
                    g.drawImage(finalImagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
                // Overlay
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(0, 0, 0, 100));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };

        // ---- Tarjeta login (Glass + sombra)
        JPanel tarjeta = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 80));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                // sombra difusa
                g2.setColor(new Color(0, 0, 0, 40));
                g2.fillRoundRect(8, 8, getWidth(), getHeight(), 25, 25);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        tarjeta.setOpaque(false);
        tarjeta.setPreferredSize(new Dimension(380, 380));
        tarjeta.setLayout(new GridLayout(6, 1, 15, 15));
        tarjeta.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Logo / título
        JLabel lblTitulo = new JLabel("Biblioteca Reverse", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        tarjeta.add(lblTitulo);

        // TextField con placeholder
        txtUsuario = crearTextField("Usuario");
        tarjeta.add(txtUsuario);

        txtPassword = crearPasswordField("Contraseña");
        tarjeta.add(txtPassword);

        // Botón login pro
        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBackground(new Color(0, 120, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));

        // efecto hover
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(new Color(0, 100, 220));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(new Color(0, 120, 255));
            }
        });

        btnLogin.addActionListener(e -> autenticar());
        tarjeta.add(btnLogin);

        // Botón cerrar
        JButton btnCerrar = new JButton("✕");
        btnCerrar.setBackground(new Color(200, 0, 0));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.addActionListener(e -> System.exit(0));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        top.setOpaque(false);
        top.add(btnCerrar);

        panel.add(top, BorderLayout.NORTH);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(tarjeta);
        panel.add(centerWrapper, BorderLayout.CENTER);

        add(panel);
    }

    // Método para crear TextFields con placeholder
    private JTextField crearTextField(String placeholder) {
        JTextField field = new JTextField(placeholder);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setForeground(Color.GRAY);
        field.setBackground(new Color(255, 255, 255, 220));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
        return field;
    }

    private JPasswordField crearPasswordField(String placeholder) {
        JPasswordField field = new JPasswordField(placeholder);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setForeground(Color.GRAY);
        field.setBackground(new Color(255, 255, 255, 220));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (String.valueOf(field.getPassword()).isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
        return field;
    }

  private void autenticar() {
    String correo = txtUsuario.getText().trim();
    String contrasenaIngresada = new String(txtPassword.getPassword());

    if (correo.isEmpty() || contrasenaIngresada.isEmpty()) {
        JOptionPane.showMessageDialog(
            this, 
            "Por favor ingrese usuario y contraseña", 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // El DAO ya maneja si la contraseña es temporal o hash BCrypt
    Usuario usuario = usuarioDAO.autenticar(correo, contrasenaIngresada);

    if (usuario != null) {
        JOptionPane.showMessageDialog(
            this, 
            "✅ Bienvenido a nuestro sistema " + usuario.getNombre()
        );
        Inicio vp = new Inicio();
        vp.setVisible(true);
        this.dispose();
        
    } else {
        JOptionPane.showMessageDialog(
            this, 
            "❌ Usuario o contraseña incorrectos", 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginBiblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginBiblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginBiblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginBiblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

      try {
        // Activar FlatLaf para interfaz moderna
        UIManager.setLookAndFeel(new FlatIntelliJLaf());
    } catch (Exception e) {
        System.err.println("Error al aplicar tema: " + e.getMessage());
    }

    java.awt.EventQueue.invokeLater(() -> {
        new LoginBiblioteca().setVisible(true);
    });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
