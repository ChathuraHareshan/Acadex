package lk.tchk.component;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

import lk.tchk.connection.MySQL;
import org.knowm.xchart.*;
import org.knowm.xchart.style.PieStyler;

public class StatusChartPanel extends JPanel {

    private Timer refreshTimer;

    public StatusChartPanel() {
        initComponents();
        setLayout(new GridLayout(1, 3, 15, 15)); // 3 charts side by side
        reload(); // load initial charts

        // Auto-refresh every 5 seconds
        refreshTimer = new Timer();
        refreshTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> reload());
            }
        }, 5000, 5000);
    }

    private JPanel createPie(String title, String table) {
        int active = getCountByStatus(table, "1");
        int inactive = getCountByStatus(table, "2");
        int total = active + inactive;

        PieChart chart = new PieChartBuilder()
                .width(400)
                .height(300)
                .title(title + " Status (" + total + ")")
                .build();

        // Styling
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.getStyler().setPlotBackgroundColor(Color.WHITE);
        chart.getStyler().setPlotBorderVisible(false);
        chart.getStyler().setChartTitleFont(new Font("Arial", Font.BOLD, 16));
        chart.getStyler().setStartAngleInDegrees(90);
//        chart.getStyler().setAnnotationType(PieStyler.AnnotationType.LabelAndPercentage);
//        chart.getStyler().setAnnotationDistance(1.15);

        // Optional styling if supported by XChart 3.8.8
        try {
//            chart.getStyler().setDrawAllAnnotations(true);
            chart.getStyler().setDecimalPattern("###");
            chart.getStyler().setToolTipsEnabled(true);
//            chart.getStyler().setAnnotationFont(new Font("Arial", Font.PLAIN, 12));
        } catch (Exception ignored) {}

        // Custom Colors
        Color blue = new Color(52, 144, 220);
        Color orange = new Color(255, 159, 64);
        chart.getStyler().setSeriesColors(new Color[]{blue, orange});

        // Add series
        chart.addSeries("Active", active);
        chart.addSeries("Inactive", inactive);

        // Chart panel
        XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));

        // Total label
        JLabel label = new JLabel("Total " + title + "s: " + total, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(60, 60, 60));

        // Wrap chart + label
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.add(chartPanel, BorderLayout.CENTER);
        wrapper.add(label, BorderLayout.SOUTH);

        return wrapper;
    }

    private int getCountByStatus(String table, String statusId) {
        int count = 0;
        try {
            ResultSet rs = MySQL.execute("SELECT COUNT(*) AS total FROM `" + table + "` WHERE status_id = '" + statusId + "'");
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void reload() {
        this.removeAll();
        add(createPie("Student", "student"));
        add(createPie("Lecturer", "lecturer"));
        add(createPie("Officer", "officer"));
        revalidate();
        repaint();
    }

    private void initComponents() {
        setBackground(new Color(248, 250, 252));
        setPreferredSize(new Dimension(1200, 400));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
}
