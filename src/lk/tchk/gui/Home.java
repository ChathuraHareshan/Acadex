package lk.tchk.gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.CardLayout;
import java.awt.Color;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import lk.tchk.component.GlassButton;
import lk.tchk.panel.ScheduleSessionPanel;
import lk.tchk.panel.OfficerPanel;
import lk.tchk.panel.StudentPanel;
import lk.tchk.panel.TeacherPanel;
import lk.tchk.panel.DashboardPanel;
import lk.tchk.panel.PaymentPanel;
import lk.tchk.panel.ReportPanel;
import lk.tchk.panel.SettingPanel;

import java.util.logging.Logger;
import lk.tchk.logs.LMSLogger;

public class Home extends javax.swing.JFrame {

    private static final Logger logger = LMSLogger.getLogger("dashboard");

    private DashboardPanel dashboardPanel;
    private StudentPanel StudentPanel;
    private TeacherPanel TeacherPanel;
    private OfficerPanel OfficerPanel;
    private ReportPanel reportPanel;
    private CardLayout MainPanelLayout;
    private SettingPanel settingPanel;
    private PaymentPanel paymentPanel;
    private ScheduleSessionPanel scheduleSessionPanel;

    public Home() {
        initComponents();
        init();
        loadPanels();
    }

    private void setActiveButton(GlassButton activeBtn) {
        for (GlassButton btn : Arrays.asList(dashboardBtn, scheduleSessionBtn, addLecturerBtn, addOfficerBtn, settingBtn, reportBtn, paymentBtn, scheduleSessionBtn)) {
            btn.setSelected(btn == activeBtn);
        }
    }

    private void loadPanels() {

        if (MainPanelLayout == null && MainPanel.getLayout() instanceof CardLayout) {
            this.MainPanelLayout = (CardLayout) MainPanel.getLayout();
        }

        this.dashboardPanel = new DashboardPanel();
        this.StudentPanel = new StudentPanel(this);
        this.TeacherPanel = new TeacherPanel(this);
        this.OfficerPanel = new OfficerPanel(this);
        this.reportPanel = new ReportPanel();
        this.settingPanel = new SettingPanel();
        this.paymentPanel = new PaymentPanel();
        this.scheduleSessionPanel = new ScheduleSessionPanel(this);

        this.dashboardPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.StudentPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.TeacherPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.OfficerPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.reportPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.settingPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.paymentPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.scheduleSessionPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");

        this.MainPanel.add(dashboardPanel, "dashboard_panel");
        this.MainPanel.add(StudentPanel, "addStudent_panel");
        this.MainPanel.add(TeacherPanel, "addTeacher_panel");
        this.MainPanel.add(OfficerPanel, "addOfficer_panel");
        this.MainPanel.add(reportPanel, "report_panel");
        this.MainPanel.add(settingPanel, "setting_panel");
        this.MainPanel.add(paymentPanel, "payment_panel");
        this.MainPanel.add(scheduleSessionPanel, "scheduleSession_panel");

        SwingUtilities.updateComponentTreeUI(MainPanel);

    }

    private void init() {

        MainPanel.setOpaque(false);
        MainPanel.setBackground(new Color(255, 255, 255, 30));
        MainPanel.putClientProperty(FlatClientProperties.STYLE, "arc:30");

        logo.setIcon(new FlatSVGIcon("lk/tchk/img/logo.svg",
                115,
                115)
        );

        dashboardBtn.setIcon(new FlatSVGIcon("lk/tchk/img/d6.svg",
                35,
                35)
        );
        dashboardBtn.setIconTextGap(10);

        addStudentBtn.setIcon(new FlatSVGIcon("lk/tchk/img/student.svg",
                35,
                35)
        );
        addStudentBtn.setIconTextGap(10);

        addLecturerBtn.setIcon(new FlatSVGIcon("lk/tchk/img/teacher.svg",
                35,
                35)
        );
        addLecturerBtn.setIconTextGap(10);

        scheduleSessionBtn.setIcon(new FlatSVGIcon("lk/tchk/img/l.svg",
                35,
                35)
        );
        scheduleSessionBtn.setIconTextGap(10);

        addOfficerBtn.setIcon(new FlatSVGIcon("lk/tchk/img/a1.svg",
                35,
                35)
        );
        addOfficerBtn.setIconTextGap(10);

        reportBtn.setIcon(new FlatSVGIcon("lk/tchk/img/r1.svg",
                35,
                35)
        );
        reportBtn.setIconTextGap(10);

        settingBtn.setIcon(new FlatSVGIcon("lk/tchk/img/setting.svg",
                35,
                35)
        );
        settingBtn.setIconTextGap(10);

        paymentBtn.setIcon(new FlatSVGIcon("lk/tchk/img/payment.svg",
                35,
                35)
        );
        paymentBtn.setIconTextGap(10);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SidebarPanel = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        SubText = new javax.swing.JLabel();
        dashboardBtn = new lk.tchk.component.GlassButton();
        scheduleSessionBtn = new lk.tchk.component.GlassButton();
        addLecturerBtn = new lk.tchk.component.GlassButton();
        addOfficerBtn = new lk.tchk.component.GlassButton();
        reportBtn = new lk.tchk.component.GlassButton();
        settingBtn = new lk.tchk.component.GlassButton();
        jLabel1 = new javax.swing.JLabel();
        paymentBtn = new lk.tchk.component.GlassButton();
        addStudentBtn = new lk.tchk.component.GlassButton();
        MainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Acadex - Home");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        SidebarPanel.setBackground(new java.awt.Color(0, 30, 108));

        logo.setBackground(new java.awt.Color(255, 255, 255));
        logo.setForeground(new java.awt.Color(255, 255, 255));
        logo.setAlignmentX(0.5F);
        logo.setAutoscrolls(true);
        logo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        logo.setPreferredSize(new java.awt.Dimension(115, 115));

        name.setBackground(new java.awt.Color(60, 100, 255));
        name.setFont(new java.awt.Font("Segoe UI Emoji", 1, 36)); // NOI18N
        name.setForeground(new java.awt.Color(255, 255, 255));
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setText("Acadex");
        name.setAlignmentX(0.5F);

        SubText.setFont(new java.awt.Font("Comic Sans MS", 0, 15)); // NOI18N
        SubText.setForeground(new java.awt.Color(255, 255, 255));
        SubText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SubText.setText("Academic Management System");
        SubText.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        dashboardBtn.setText("Dashboard");
        dashboardBtn.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        dashboardBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        dashboardBtn.setSelected(true);
        dashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBtnActionPerformed(evt);
            }
        });

        scheduleSessionBtn.setText("Schedule Session");
        scheduleSessionBtn.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        scheduleSessionBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        scheduleSessionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleSessionBtnActionPerformed(evt);
            }
        });

        addLecturerBtn.setText("Lecturer Management");
        addLecturerBtn.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        addLecturerBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        addLecturerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLecturerBtnActionPerformed(evt);
            }
        });

        addOfficerBtn.setText("Officer Management");
        addOfficerBtn.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        addOfficerBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        addOfficerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOfficerBtnActionPerformed(evt);
            }
        });

        reportBtn.setText("Reports");
        reportBtn.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        reportBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        reportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportBtnActionPerformed(evt);
            }
        });

        settingBtn.setText("Setting");
        settingBtn.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        settingBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        settingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingBtnActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Acadex Academic Management System v1.0");

        paymentBtn.setText("Payment");
        paymentBtn.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        paymentBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        paymentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentBtnActionPerformed(evt);
            }
        });

        addStudentBtn.setText("Student Management");
        addStudentBtn.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        addStudentBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        addStudentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SidebarPanelLayout = new javax.swing.GroupLayout(SidebarPanel);
        SidebarPanel.setLayout(SidebarPanelLayout);
        SidebarPanelLayout.setHorizontalGroup(
            SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidebarPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paymentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(settingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(addLecturerBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(scheduleSessionBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dashboardBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addStudentBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(addOfficerBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(SidebarPanelLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
            .addGroup(SidebarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SubText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        SidebarPanelLayout.setVerticalGroup(
            SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidebarPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SubText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(dashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(scheduleSessionBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(addStudentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(addLecturerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(addOfficerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(reportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(paymentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(settingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        MainPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainPanel.setForeground(new java.awt.Color(255, 255, 255));
        MainPanel.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        MainPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SidebarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SidebarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtnActionPerformed
        this.MainPanelLayout.show(MainPanel, "dashboard_panel");
        setActiveButton(dashboardBtn);
    }//GEN-LAST:event_dashboardBtnActionPerformed

    private void scheduleSessionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleSessionBtnActionPerformed
        this.MainPanelLayout.show(MainPanel, "scheduleSession_panel");
        setActiveButton(scheduleSessionBtn);

    }//GEN-LAST:event_scheduleSessionBtnActionPerformed

    private void addLecturerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLecturerBtnActionPerformed
        this.MainPanelLayout.show(MainPanel, "addTeacher_panel");
        setActiveButton(addLecturerBtn);

    }//GEN-LAST:event_addLecturerBtnActionPerformed

    private void addOfficerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOfficerBtnActionPerformed
        this.MainPanelLayout.show(MainPanel, "addOfficer_panel");
        setActiveButton(addOfficerBtn);

    }//GEN-LAST:event_addOfficerBtnActionPerformed

    private void reportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBtnActionPerformed
        this.MainPanelLayout.show(MainPanel, "report_panel");
        setActiveButton(reportBtn);

    }//GEN-LAST:event_reportBtnActionPerformed

    private void settingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingBtnActionPerformed
        this.MainPanelLayout.show(MainPanel, "setting_panel");
        setActiveButton(settingBtn);

    }//GEN-LAST:event_settingBtnActionPerformed

    private void paymentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentBtnActionPerformed
        this.MainPanelLayout.show(MainPanel, "payment_panel");
        setActiveButton(paymentBtn);
    }//GEN-LAST:event_paymentBtnActionPerformed

    private void addStudentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentBtnActionPerformed
        this.MainPanelLayout.show(MainPanel, "addStudent_panel");
        setActiveButton(addStudentBtn);
    }//GEN-LAST:event_addStudentBtnActionPerformed

    public static void main(String args[]) {

        FlatIntelliJLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel SidebarPanel;
    private javax.swing.JLabel SubText;
    private lk.tchk.component.GlassButton addLecturerBtn;
    private lk.tchk.component.GlassButton addOfficerBtn;
    private lk.tchk.component.GlassButton addStudentBtn;
    private lk.tchk.component.GlassButton dashboardBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel name;
    private lk.tchk.component.GlassButton paymentBtn;
    private lk.tchk.component.GlassButton reportBtn;
    private lk.tchk.component.GlassButton scheduleSessionBtn;
    private lk.tchk.component.GlassButton settingBtn;
    // End of variables declaration//GEN-END:variables

}
