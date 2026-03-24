
package lk.tchk.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;


public class RoundCard extends JPanel{
    
     
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g.create();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Color darkBlue = new Color(25, 57, 138);
        Color lightBlue = new Color(76, 110, 196);
        GradientPaint gradient = new GradientPaint(0, 0, darkBlue, getWidth(), getHeight(), lightBlue);
        
        g2d.setPaint(gradient);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        
       
        
        g2d.dispose();
    }
    
    
}
