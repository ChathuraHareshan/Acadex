package lk.tchk.component;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeChooserPanel extends JPanel {
    private JComboBox<String> hourCombo, minuteCombo, ampmCombo;

    public TimeChooserPanel() {
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        setBackground(new Color(220, 240, 255));

        // Hours 1-12
        String[] hours = new String[12];
        for (int i = 0; i < 12; i++) {
            hours[i] = String.format("%02d", i + 1);
        }

        // Minutes 00-59
        String[] minutes = new String[60];
        for (int i = 0; i < 60; i++) {
            minutes[i] = String.format("%02d", i);
        }

        // AM/PM
        String[] ampm = {"AM", "PM"};

        hourCombo = new JComboBox<>(hours);
        minuteCombo = new JComboBox<>(minutes);
        ampmCombo = new JComboBox<>(ampm);

        add(new JLabel("Time:"));
        add(hourCombo);
        add(new JLabel(":"));
        add(minuteCombo);
        add(ampmCombo);
    }

    // ✅ Return SQL TIME string: "HH:mm:ss"
    public String getSqlTimeString() {
        int hour = Integer.parseInt((String) hourCombo.getSelectedItem());
        int minute = Integer.parseInt((String) minuteCombo.getSelectedItem());
        String ampm = (String) ampmCombo.getSelectedItem();

        // Convert to 24-hour format
        if ("PM".equals(ampm) && hour != 12) {
            hour += 12;
        } else if ("AM".equals(ampm) && hour == 12) {
            hour = 0;
        }

        // Build time string
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
    
    
public void setSqlTimeString(String timeStr) {
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        java.util.Date date = sdf.parse(timeStr);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int hour24 = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        // Convert 24-hour to 12-hour format for combo boxes
        String ampm;
        int hour12;
        if (hour24 == 0) {
            hour12 = 12;
            ampm = "AM";
        } else if (hour24 < 12) {
            hour12 = hour24;
            ampm = "AM";
        } else if (hour24 == 12) {
            hour12 = 12;
            ampm = "PM";
        } else {
            hour12 = hour24 - 12;
            ampm = "PM";
        }

        hourCombo.setSelectedItem(String.format("%02d", hour12));
        minuteCombo.setSelectedItem(String.format("%02d", minute));
        ampmCombo.setSelectedItem(ampm);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
