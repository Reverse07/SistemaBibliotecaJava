package Vista;

import Controlador.LibroDAO;
import Modelo.Libro;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class CarruselLibros extends JPanel {

    private InterLibros panelPadre;

    public CarruselLibros(InterLibros panelPadre) {
        this.panelPadre = panelPadre;
        setLayout(new BorderLayout());

        JPanel panelScroll = new JPanel();
        panelScroll.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelScroll.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(panelScroll);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        LibroDAO dao = new LibroDAO();
        List<Libro> lista = dao.obtenerTodos();
        System.out.println("📚 Libros encontrados: " + lista.size());


        for (Libro libro : lista) {
            JPanel tarjeta = crearTarjetaLibro(libro);
            panelScroll.add(tarjeta);
        }

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel crearTarjetaLibro(Libro libro) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(160, 260));
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Imagen del libro
        JLabel lblImagen = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/img/" + libro.getRutaImagen()));
            Image img = icon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblImagen.setText("Sin imagen");
            lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        }

        // Panel inferior con detalles
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel lblTitulo = new JLabel("<html><b>" + libro.getTitulo() + "</b></html>", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblAutor = new JLabel("Autor: " + libro.getAutor(), SwingConstants.CENTER);
        lblAutor.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblAutor.setForeground(Color.DARK_GRAY);
        lblAutor.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblCategoria = new JLabel("Categoría: " + (libro.getCategoria() != null ? libro.getCategoria().getNombre() : "N/A"));
        lblCategoria.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblCategoria.setForeground(new Color(100, 100, 100));
        lblCategoria.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblDescripcion = new JLabel("<html><div style='text-align: center;'>" + libro.getDescripcion() + "</div></html>");
        lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblDescripcion.setForeground(new Color(90, 90, 90));
        lblDescripcion.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar al panel info
        infoPanel.add(lblTitulo);
        infoPanel.add(lblAutor);
        infoPanel.add(lblCategoria);
        infoPanel.add(lblDescripcion);

        panel.add(lblImagen, BorderLayout.CENTER);
        panel.add(infoPanel, BorderLayout.SOUTH);

        // Evento clic
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelPadre.seleccionarLibro(libro);
            }
        });

        return panel;
    }
}
