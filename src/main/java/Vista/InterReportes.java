package Vista;

import Controlador.CategoriaDAO;
import Controlador.LibroDAO;
import Controlador.PrestamoDAO;
import Modelo.Prestamo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class InterReportes extends javax.swing.JPanel {

    public InterReportes() {
        setLayout(new BorderLayout());
        jPanel1 = new JPanel();
        jPanel1.setLayout(new BorderLayout());
        add(jPanel1, BorderLayout.CENTER);

        cargarPanelReportes();
    }

    private void cargarPanelReportes() {
    jPanel1.removeAll();

    // Cambia BoxLayout por BorderLayout
    JPanel contenido = new JPanel(new BorderLayout());
    contenido.setBackground(Color.WHITE);

    contenido.add(crearEncabezado(), BorderLayout.NORTH); // Header fijo
    contenido.add(crearContenidoCentral(), BorderLayout.CENTER); // Resto usa espacio restante

    JScrollPane scroll = new JScrollPane(contenido);
    scroll.setBorder(null);
    scroll.getVerticalScrollBar().setUnitIncrement(16); // Suaviza el scroll

    jPanel1.add(scroll, BorderLayout.CENTER);
    jPanel1.revalidate();
    jPanel1.repaint();
}

private JPanel crearEncabezado() {
    JPanel header = new JPanel(new BorderLayout());
    header.setPreferredSize(new Dimension(0, 70)); // 👈 Altura fija
    header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70)); // 🚨 Limita la altura máxima
    header.setBackground(new Color(25, 118, 210));

    JLabel titulo = new JLabel("📊 Panel de Reportes", SwingConstants.CENTER);
    titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
    titulo.setForeground(Color.WHITE);

    header.add(titulo, BorderLayout.CENTER);
    return header;
}

    private JPanel crearContenidoCentral() {
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBackground(Color.WHITE);

        // Indicadores
        JPanel indicadores = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        indicadores.setBackground(Color.WHITE);

        indicadores.add(crearTarjetaIndicador("📚 Libros Totales", obtenerTotalLibros(), new Color(33, 150, 243)));
        indicadores.add(crearTarjetaIndicador("🗂️ Categorías", obtenerTotalCategorias(), new Color(76, 175, 80)));
        indicadores.add(crearTarjetaIndicador("📖 Prestados", obtenerLibrosPrestados(), new Color(255, 193, 7)));
        indicadores.add(crearTarjetaIndicador("⚠️ Stock Bajo", obtenerLibrosStockBajo(), new Color(244, 67, 54)));

        // Gráficos
        JPanel graficos = new JPanel(new GridLayout(1, 2, 20, 20));
        graficos.setBackground(Color.WHITE);
        graficos.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        graficos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));

        graficos.add(crearGraficoBarrasCategorias());
        graficos.add(crearGraficoPieDisponibilidad());

        centro.add(indicadores);
        centro.add(graficos);

        return centro;
    }

    private JPanel crearTarjetaIndicador(String titulo, int valor, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setPreferredSize(new Dimension(200, 100));
        card.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.setToolTipText(titulo);
        card.setOpaque(true);

        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        labelTitulo.setForeground(Color.WHITE);

        JLabel labelValor = new JLabel(String.valueOf(valor));
        labelValor.setFont(new Font("Segoe UI", Font.BOLD, 34));
        labelValor.setForeground(Color.WHITE);
        labelValor.setHorizontalAlignment(SwingConstants.RIGHT);

        card.add(labelTitulo, BorderLayout.NORTH);
        card.add(labelValor, BorderLayout.CENTER);

        return card;
    }

    private ChartPanel crearGraficoBarrasCategorias() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<String, Integer> data = new CategoriaDAO().obtenerTodas();
        data.forEach((categoria, cantidad) -> dataset.addValue(cantidad, "Cantidad", categoria));

        JFreeChart chart = ChartFactory.createBarChart(
                "📚 Libros por Categoría", "Categoría", "Cantidad",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setPaint(new Color(33, 33, 33));
        chart.getCategoryPlot().getRenderer().setSeriesPaint(0, new Color(30, 136, 229));
        chart.getCategoryPlot().setBackgroundPaint(Color.WHITE);
        chart.getCategoryPlot().setOutlineVisible(false);

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(400, 300)); // 👈 Ajusta buen tamaño
        return panel;
    }

    private ChartPanel crearGraficoPieDisponibilidad() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int total = obtenerTotalLibros();
        int prestados = obtenerLibrosPrestados();
        int disponibles = total - prestados;

        dataset.setValue("📕 Prestados", prestados);
        dataset.setValue("📗 Disponibles", disponibles);

        JFreeChart chart = ChartFactory.createPieChart(
                "📈 Disponibilidad de Libros", dataset, false, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("📕 Prestados", new Color(255, 87, 34));
        plot.setSectionPaint("📗 Disponibles", new Color(76, 175, 80));
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setLabelFont(new Font("Segoe UI", Font.PLAIN, 14));

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(400, 300)); // 👈 Ajusta buen tamaño
        return panel;
    }

    private int obtenerTotalLibros() {
        return new LibroDAO().contarLibros();
    }

    private int obtenerTotalCategorias() {
        return new CategoriaDAO().contarCategorias();
    }

    private int obtenerLibrosPrestados() {
        return new PrestamoDAO().contarPrestamosActivos();
    }

    private int obtenerLibrosStockBajo() {
        return new LibroDAO().contarStockBajo(2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
