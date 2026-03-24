package lk.tchk.component;

import java.awt.*;
import javax.swing.JPanel;

public class SplashPanel extends JPanel {

    
    public SplashPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Draw a soft background (optional)
        g2.setColor(new Color(255, 255, 255, 80)); // light transparent background
        g2.fillRoundRect(0, 0, width, height, 30, 30); // rounded panel

        // Draw decorative circles
        drawCircleDesigns(g2, width, height);

        g2.dispose();
        super.paintComponent(g);
    }

    private void drawCircleDesigns(Graphics2D g2, int width, int height) {
        Color circleColor = new Color(0, 0, 0, 30); // translucent black circles
        g2.setColor(circleColor);

        int[][] circles = {
            {10, 10, 30},
            {width - 50, 20, 40},
            {20, height - 60, 50},
            {width - 70, height - 70, 35}
        };

        for (int[] c : circles) {
            g2.fillOval(c[0], c[1], c[2], c[2]);
        }
    }
}
