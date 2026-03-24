package lk.tchk.panel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import lk.tchk.connection.MySQL;

import java.util.logging.Logger;
import lk.tchk.logs.LMSLogger;

public class DashboardPanel extends javax.swing.JPanel {

    private static final Logger logger = LMSLogger.getLogger("dashboard");

    public DashboardPanel(){
        initComponents();
        init();
        loadCount();
    }

    private void loadCount() {
        loadStudentCount();
        loadTeacherCount();
        loadOfficerCount();
        loadAdminCount();

    }

    private void loadStudentCount() {
        try {
            ResultSet rs = MySQL.execute("SELECT COUNT(*) AS total FROM `student`");
            if (rs.next()) {
                String StuCount = rs.getString("total");
                studentCount.setText(StuCount);
            }
        } catch (SQLException e) {
            logger.severe("Failed to load student Count." + e.getMessage());

        }
    }

    private void loadTeacherCount() {
        try {
            ResultSet rs = MySQL.execute("SELECT COUNT(*) AS total FROM `lecturer`");
            if (rs.next()) {
                String TeacCount = rs.getString("total");
                teacherCount.setText(TeacCount);
            }
        } catch (SQLException e) {
            logger.severe("Failed to load teacher Count." + e.getMessage());

        }
    }

    private void loadOfficerCount() {
        try {
            ResultSet rs = MySQL.execute("SELECT COUNT(*) AS total FROM `officer`");
            if (rs.next()) {
                String OffiCount = rs.getString("total");
                officerCount.setText(OffiCount);
            }
        } catch (SQLException e) {
            logger.severe("Failed to load officer Count." + e.getMessage());

        }
    }

    private void loadAdminCount() {
        try {
            ResultSet rs = MySQL.execute("SELECT COUNT(*) AS total FROM `admin`");
            if (rs.next()) {
                String count = rs.getString("total");
                adminCount.setText(count);
            }
        } catch (SQLException e) {
            logger.severe("Failed to load admin Count." + e.getMessage());

        }
    }

    private void init() {

        jScrollPane2.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane2.getHorizontalScrollBar().setUnitIncrement(16);

        HeadPanel.setOpaque(false);

        MainPanel.setOpaque(false);
        MainPanel.setBackground(new Color(255, 255, 255, 10));

        CardPanel.setBackground(new Color(255, 255, 255, 20));
        CardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        CardPanel.setOpaque(false);

        StudentCard.setIcon(new FlatSVGIcon("lk/tchk/img/student.svg",
                60, 60));
        StudentCard.setIconTextGap(10);

        TeacherCard.setIcon(new FlatSVGIcon("lk/tchk/img/teacher.svg",
                60, 60));
        TeacherCard.setIconTextGap(10);

        OfficerCard.setIcon(new FlatSVGIcon("lk/tchk/img/a1.svg",
                60, 60));
        OfficerCard.setIconTextGap(10);

        AdminCard.setIcon(new FlatSVGIcon("lk/tchk/img/admin.svg",
                60, 60));
        AdminCard.setIconTextGap(10);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new lk.tchk.component.RoundPanel();
        HeadPanel = new lk.tchk.component.RoundPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        roundPanel2 = new lk.tchk.component.RoundPanel();
        CardPanel = new lk.tchk.component.RoundPanel();
        studentCardPanel = new lk.tchk.component.RoundCard();
        StudentCard = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        studentCount = new javax.swing.JLabel();
        teacherCardPanel = new lk.tchk.component.RoundCard();
        TeacherCard = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        teacherCount = new javax.swing.JLabel();
        officerCardPanel = new lk.tchk.component.RoundCard();
        OfficerCard = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        officerCount = new javax.swing.JLabel();
        adminCardPanel = new lk.tchk.component.RoundCard();
        AdminCard = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        adminCount = new javax.swing.JLabel();
        roundCard1 = new lk.tchk.component.RoundCard();
        jCalander1 = new lk.tchk.component.JCalander();
        roundCard2 = new lk.tchk.component.RoundCard();
        statusChartPanel1 = new lk.tchk.component.StatusChartPanel();

        MainPanel.setBackground(new java.awt.Color(204, 204, 255));

        HeadPanel.setBackground(new java.awt.Color(0, 30, 108));

        jLabel6.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Welcome back, Admin");

        jLabel5.setFont(new java.awt.Font("Castellar", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(" Dashboard Overview");

        javax.swing.GroupLayout HeadPanelLayout = new javax.swing.GroupLayout(HeadPanel);
        HeadPanel.setLayout(HeadPanelLayout);
        HeadPanelLayout.setHorizontalGroup(
            HeadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HeadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1495, Short.MAX_VALUE))
                .addContainerGap())
        );
        HeadPanelLayout.setVerticalGroup(
            HeadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeadPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        CardPanel.setLayout(new java.awt.GridLayout(1, 4, 20, 10));

        StudentCard.setBackground(new java.awt.Color(255, 255, 255));
        StudentCard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StudentCard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Trajan-Regular", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total Students");

        studentCount.setFont(new java.awt.Font("SimSun-ExtG", 0, 36)); // NOI18N
        studentCount.setForeground(new java.awt.Color(255, 255, 255));
        studentCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        studentCount.setText("11");

        javax.swing.GroupLayout studentCardPanelLayout = new javax.swing.GroupLayout(studentCardPanel);
        studentCardPanel.setLayout(studentCardPanelLayout);
        studentCardPanelLayout.setHorizontalGroup(
            studentCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentCardPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(StudentCard, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(studentCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(studentCardPanelLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(studentCount, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(studentCardPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        studentCardPanelLayout.setVerticalGroup(
            studentCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentCardPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(studentCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(studentCardPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentCount, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(StudentCard, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        CardPanel.add(studentCardPanel);

        TeacherCard.setBackground(new java.awt.Color(255, 255, 255));
        TeacherCard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TeacherCard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Trajan-Regular", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total Teachers");

        teacherCount.setFont(new java.awt.Font("SimSun-ExtG", 0, 36)); // NOI18N
        teacherCount.setForeground(new java.awt.Color(255, 255, 255));
        teacherCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teacherCount.setText("11");

        javax.swing.GroupLayout teacherCardPanelLayout = new javax.swing.GroupLayout(teacherCardPanel);
        teacherCardPanel.setLayout(teacherCardPanelLayout);
        teacherCardPanelLayout.setHorizontalGroup(
            teacherCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teacherCardPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(TeacherCard, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(teacherCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(teacherCardPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addGroup(teacherCardPanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(teacherCount, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        teacherCardPanelLayout.setVerticalGroup(
            teacherCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teacherCardPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(teacherCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(teacherCardPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(teacherCount, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TeacherCard, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        CardPanel.add(teacherCardPanel);

        OfficerCard.setBackground(new java.awt.Color(255, 255, 255));
        OfficerCard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OfficerCard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel7.setFont(new java.awt.Font("Trajan-Regular", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Officers");

        officerCount.setFont(new java.awt.Font("SimSun-ExtG", 0, 36)); // NOI18N
        officerCount.setForeground(new java.awt.Color(255, 255, 255));
        officerCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        officerCount.setText("11");

        javax.swing.GroupLayout officerCardPanelLayout = new javax.swing.GroupLayout(officerCardPanel);
        officerCardPanel.setLayout(officerCardPanelLayout);
        officerCardPanelLayout.setHorizontalGroup(
            officerCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(officerCardPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(OfficerCard, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(officerCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(officerCardPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addGroup(officerCardPanelLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(officerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        officerCardPanelLayout.setVerticalGroup(
            officerCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(officerCardPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(officerCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(officerCardPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(officerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(OfficerCard, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        CardPanel.add(officerCardPanel);

        AdminCard.setBackground(new java.awt.Color(255, 255, 255));
        AdminCard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AdminCard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Trajan-Regular", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Total Admins");

        adminCount.setFont(new java.awt.Font("SimSun-ExtG", 0, 36)); // NOI18N
        adminCount.setForeground(new java.awt.Color(255, 255, 255));
        adminCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminCount.setText("11");

        javax.swing.GroupLayout adminCardPanelLayout = new javax.swing.GroupLayout(adminCardPanel);
        adminCardPanel.setLayout(adminCardPanelLayout);
        adminCardPanelLayout.setHorizontalGroup(
            adminCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminCardPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(AdminCard, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(adminCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adminCardPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addGroup(adminCardPanelLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(adminCount, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        adminCardPanelLayout.setVerticalGroup(
            adminCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminCardPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(adminCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adminCardPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adminCount, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AdminCard, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        CardPanel.add(adminCardPanel);

        javax.swing.GroupLayout roundCard1Layout = new javax.swing.GroupLayout(roundCard1);
        roundCard1.setLayout(roundCard1Layout);
        roundCard1Layout.setHorizontalGroup(
            roundCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundCard1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jCalander1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        roundCard1Layout.setVerticalGroup(
            roundCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundCard1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jCalander1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout roundCard2Layout = new javax.swing.GroupLayout(roundCard2);
        roundCard2.setLayout(roundCard2Layout);
        roundCard2Layout.setHorizontalGroup(
            roundCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundCard2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(statusChartPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        roundCard2Layout.setVerticalGroup(
            roundCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundCard2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(statusChartPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundCard1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1471, Short.MAX_VALUE)
                    .addComponent(roundCard2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(CardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(roundCard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(roundCard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(239, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(roundPanel2);

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HeadPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1519, Short.MAX_VALUE)
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HeadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AdminCard;
    private lk.tchk.component.RoundPanel CardPanel;
    private lk.tchk.component.RoundPanel HeadPanel;
    private lk.tchk.component.RoundPanel MainPanel;
    private javax.swing.JLabel OfficerCard;
    private javax.swing.JLabel StudentCard;
    private javax.swing.JLabel TeacherCard;
    private lk.tchk.component.RoundCard adminCardPanel;
    private javax.swing.JLabel adminCount;
    private lk.tchk.component.JCalander jCalander1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private lk.tchk.component.RoundCard officerCardPanel;
    private javax.swing.JLabel officerCount;
    private lk.tchk.component.RoundCard roundCard1;
    private lk.tchk.component.RoundCard roundCard2;
    private lk.tchk.component.RoundPanel roundPanel2;
    private lk.tchk.component.StatusChartPanel statusChartPanel1;
    private lk.tchk.component.RoundCard studentCardPanel;
    private javax.swing.JLabel studentCount;
    private lk.tchk.component.RoundCard teacherCardPanel;
    private javax.swing.JLabel teacherCount;
    // End of variables declaration//GEN-END:variables
}
