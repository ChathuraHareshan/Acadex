package lk.tchk.component;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeChooserPanel extends JPanel {
    private JSpinner dateSpinner;
    private JComboBox<String> hourCombo, minuteCombo, ampmCombo;

    public DateTimeChooserPanel() {
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        setBackground(new Color(220, 240, 255));

        // DATE
        Calendar calendar = Calendar.getInstance();
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));

        // TIME
        String[] hours = new String[12];
        String[] minutes = new String[60];
        for (int i = 0; i < 12; i++) hours[i] = String.format("%02d", i + 1);
        for (int i = 0; i < 60; i++) minutes[i] = String.format("%02d", i);

        hourCombo = new JComboBox<>(hours);
        minuteCombo = new JComboBox<>(minutes);
        ampmCombo = new JComboBox<>(new String[]{"AM", "PM"});

        add(new JLabel("Date:"));
        add(dateSpinner);
        add(new JLabel("Time:"));
        add(hourCombo);
        add(new JLabel(":"));
        add(minuteCombo);
        add(ampmCombo);
    }

    // ✅ Return full SQL DATETIME string: "yyyy-MM-dd HH:mm:ss"
    public String getSqlDateTimeString() {
        // Get the selected date
        Date selectedDate = (Date) dateSpinner.getValue();

        // Get selected time parts
        int hour = Integer.parseInt((String) hourCombo.getSelectedItem());
        int minute = Integer.parseInt((String) minuteCombo.getSelectedItem());
        String ampm = (String) ampmCombo.getSelectedItem();

        // Convert to 24-hour format
        if ("PM".equals(ampm) && hour != 12) {
            hour += 12;
        } else if ("AM".equals(ampm) && hour == 12) {
            hour = 0;
        }

        // Set time to calendar
        Calendar cal = Calendar.getInstance();
        cal.setTime(selectedDate);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);

        // Format as SQL datetime
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }
}
