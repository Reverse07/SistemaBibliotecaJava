
package Vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.border.AbstractBorder;


public class DropShadowBorder extends  AbstractBorder {
     private int size = 8;
    private float opacity = 0.2f;

    public DropShadowBorder() {}
    public DropShadowBorder(int size, float opacity) {
        this.size = size;
        this.opacity = opacity;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(size, size, size, size);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        int shade = 0;
        for (int i = 0; i < size; i++) {
            g2.setColor(new Color(shade, shade, shade, (int) (opacity * 255 * (1.0 - i / (float) size))));
            g2.drawRoundRect(x + i, y + i, width - i * 2 - 1, height - i * 2 - 1, 20, 20);
        }
        g2.dispose();
    }
}
    
