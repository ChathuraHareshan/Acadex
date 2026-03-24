package lk.tchk.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SeconderyBtn extends JButton {

    private boolean hover = false;

    public SeconderyBtn() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(new Color(0, 30, 108)); // default text color
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                setForeground(Color.WHITE); // change text color to white
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                setForeground(new Color(0, 30, 108)); // revert text color
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background color based on hover
        if (hover) {
            g2.setColor(new Color(0, 30, 108, 200)); 
        } else {
            g2.setColor(new Color(0, 30, 108, 10)); 
        }

        // Draw rounded background
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // Border
        g2.setColor(new Color(0, 30, 108));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);

        g2.dispose();
        super.paintComponent(g);
    }
}
