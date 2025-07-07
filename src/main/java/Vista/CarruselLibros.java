
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;


public class CarruselLibros extends JPanel {
    
    public CarruselLibros() {
        setLayout(new BorderLayout());
        
        // Contenedor de tarjetas
        JPanel panelScroll = new JPanel();
        panelScroll.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelScroll.setBackground(Color.WHITE);

        // Scroll horizontal
        JScrollPane scrollPane = new JScrollPane(panelScroll);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        // Cargar libros desde DAO
        LibroDAO dao = new LibroDAO();
        List<Libro> lista = dao.obtenerTodos();

        for (Libro libro : lista) {
            JPanel tarjeta = crearTarjetaLibro(libro);
            panelScroll.add(tarjeta);
        }

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel crearTarjetaLibro(Libro libro) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(140, 200));
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Cargar imagen
        JLabel lblImagen = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/img/" + libro.getRutaImagen()));
            Image img = icon.getImage().getScaledInstance(140, 160, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblImagen.setText("Sin imagen");
            lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        }

        JLabel lblTitulo = new JLabel(libro.getTitulo(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTitulo.setForeground(Color.DARK_GRAY);

        panel.add(lblImagen, BorderLayout.CENTER);
        panel.add(lblTitulo, BorderLayout.SOUTH);

        // Evento clic para mostrar detalles
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new InterLibros(libro).setVisible(true);
            }
        });

        return panel;
    }
}

