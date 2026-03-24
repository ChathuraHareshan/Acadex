package lk.tchk.component;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class PrimaryPanel extends JPanel {

    private Color colorStart = new Color(0, 30, 108);  // Top color
    private Color colorEnd = new Color(0, 123, 255);   // Bottom color
    
    
   

    public PrimaryPanel() {
        setOpaque(false); // Allows custom painting
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Create gradient from top (colorStart) to bottom (colorEnd)
        GradientPaint gp = new GradientPaint(0, 0, colorStart, 0, height, colorEnd);
        g2.setPaint(gp);
        g2.fillRect(0, 0, width, height);

        g2.dispose();
        super.paintComponent(g);
    }

    // Optional: allow setting custom gradient colors
    public void setGradientColors(Color start, Color end) {
        this.colorStart = start;
        this.colorEnd = end;
        repaint();
    }
}
