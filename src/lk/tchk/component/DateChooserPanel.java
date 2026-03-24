package lk.tchk.component;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateChooserPanel extends JPanel {
    private JSpinner dateSpinner;

    public DateChooserPanel() {
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        setBackground(new Color(220, 240, 255));

        // DATE
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));

        add(new JLabel("Date:"));
        add(dateSpinner);
    }

    // ✅ Return SQL DATE string: "yyyy-MM-dd"
    public String getSqlDateString() {
        Date selectedDate = (Date) dateSpinner.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(selectedDate);
    }
    
    public void setSqlDateString(String dateStr) {
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateStr);
        dateSpinner.setValue(date);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
}
