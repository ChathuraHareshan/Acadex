package lk.tchk.component;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.*;
import javax.swing.*;

public class GlassButton extends JButton {

    private boolean hovered = false;
    private boolean selected = false;

    public GlassButton() {
        super("Button");

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background color logic
        Color bgColor;
        if (selected) {
            bgColor = new Color(30, 144, 255, 120); // Active/selected
        } else if (hovered) {
            bgColor = new Color(100, 149, 237, 80); // Hover
        } else {
            bgColor = new Color(255, 255, 255, 40); // Default
        }

        g2.setColor(bgColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        g2.setColor(new Color(255, 255, 255, 50));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        super.paintComponent(g2);
        g2.dispose();
    }
}
