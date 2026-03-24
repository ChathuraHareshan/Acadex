package lk.tchk.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrimaryBtn extends JButton {

    private boolean isHover = false;

    public PrimaryBtn() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFont(new Font("SansSerif", Font.BOLD, 14));

        // Mouse hover detection
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHover = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Define normal and hover gradients
        Color startColor = isHover ? new Color(30, 85, 180) : new Color(25, 57, 138);
        Color endColor = isHover ? new Color(100, 150, 230) : new Color(76, 110, 196);

        GradientPaint gp = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
        g2.setPaint(gp);

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        super.paintComponent(g);
        g2.dispose();
    }

// Optional: keep updateUI to prevent FlatLaf reset if using custom UI themes
//    @Override
//    public void updateUI() {
//        setUI(new BasicButtonUI());
//    }
}
