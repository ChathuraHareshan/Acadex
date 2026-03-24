package lk.tchk.component;


import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Color;

public class JCalander extends javax.swing.JPanel {

    public JCalander() {

        setLayout(new BorderLayout());
        setBackground(new Color(30, 64, 175)); // background for this panel

        JCalendar calendar = new JCalendar();

        // Set background for all internal parts
        calendar.setBackground(new Color(240, 240, 240));
        calendar.getDayChooser().setBackground(new Color(240, 240, 240));
        calendar.getMonthChooser().setBackground(new Color(240, 240, 240));
        calendar.getYearChooser().setBackground(new Color(240, 240, 240));

        add(calendar, BorderLayout.CENTER);
    }
}