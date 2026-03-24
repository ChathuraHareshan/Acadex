package lk.tchk.dialog;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import lk.tchk.connection.MySQL;
import lk.tchk.panel.ScheduleSessionPanel;
import java.util.logging.Logger;
import lk.tchk.logs.LMSLogger;

public class AddSessionDialog extends javax.swing.JDialog {

    private static final Logger logger = LMSLogger.getLogger("addUsers");

    private ScheduleSessionPanel sessionPanel;

    public AddSessionDialog(java.awt.Frame parent, boolean modal, ScheduleSessionPanel scheduleSessionPanel) {
        super(parent, modal);
        this.sessionPanel = scheduleSessionPanel;
        initComponents();
        init();
        genarateSessionID();
    }

    private void genarateSessionID() {
        try {
            ResultSet rs = MySQL.execute("SELECT COUNT(*) AS total FROM session");

            if (rs.next()) {
                int count = rs.getInt("total") + 1;
                String newId = String.format("SES_%03d", count);
                sessionIdTxt.setText(newId);
            }
        } catch (SQLException e) {
            logger.severe("Failed to Genarate Session id." + e.getMessage());
        }
    }

    private void Refresh() {
        subjectTxt.setText("");
        lecturerNameTxt.setText("");
    }

    private void init() {
        searchField.putClientProperty("JTextField.placeholderText", "Search Subject or Lecturer from Email or Lecturer ID or Subject");

        closeIcon.setIcon(new FlatSVGIcon("lk/tchk/img/close.svg",
                closeIcon.getWidth(),
                closeIcon.getHeight()));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FourthPanel = new lk.tchk.component.RoundPanel();
        label7 = new lk.tchk.component.Label();
        label8 = new lk.tchk.component.Label();
        GenderValue = new lk.tchk.component.ComboBox();
        StatusValue = new lk.tchk.component.ComboBox();
        roundPanel1 = new lk.tchk.component.RoundPanel();
        HeaderPanel = new lk.tchk.component.MainPanel();
        closeIcon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        FirstPanel = new lk.tchk.component.RoundPanel();
        label3 = new lk.tchk.component.Label();
        label4 = new lk.tchk.component.Label();
        subjectTxt = new lk.tchk.component.inputField();
        lecturerNameTxt = new lk.tchk.component.inputField();
        FirstPanel1 = new lk.tchk.component.RoundPanel();
        label9 = new lk.tchk.component.Label();
        sessionIdTxt = new lk.tchk.component.inputField();
        FirstPanel2 = new lk.tchk.component.RoundPanel();
        searchField = new lk.tchk.component.inputField();
        FirstPanel3 = new lk.tchk.component.RoundPanel();
        label6 = new lk.tchk.component.Label();
        dateChooserPanel = new lk.tchk.component.DateChooserPanel();
        FirstPanel4 = new lk.tchk.component.RoundPanel();
        label5 = new lk.tchk.component.Label();
        label10 = new lk.tchk.component.Label();
        startTimeChooser = new lk.tchk.component.TimeChooserPanel();
        endTimeChooserPanel = new lk.tchk.component.TimeChooserPanel();
        AddSessionBtn = new lk.tchk.component.RoundBtn();

        FourthPanel.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label7.setText("Gender");
        FourthPanel.add(label7);

        label8.setText("Status");
        FourthPanel.add(label8);
        FourthPanel.add(GenderValue);
        FourthPanel.add(StatusValue);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        HeaderPanel.setBackground(new java.awt.Color(30, 64, 175));
        HeaderPanel.setOpaque(true);

        closeIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeIconMouseReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Add Session");

        javax.swing.GroupLayout HeaderPanelLayout = new javax.swing.GroupLayout(HeaderPanel);
        HeaderPanel.setLayout(HeaderPanelLayout);
        HeaderPanelLayout.setHorizontalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        HeaderPanelLayout.setVerticalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(HeaderPanelLayout.createSequentialGroup()
                        .addComponent(closeIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        FirstPanel.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label3.setText("Subject");
        FirstPanel.add(label3);

        label4.setText("Lecturer name");
        FirstPanel.add(label4);
        FirstPanel.add(subjectTxt);
        FirstPanel.add(lecturerNameTxt);

        FirstPanel1.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label9.setText("Session ID");
        FirstPanel1.add(label9);

        sessionIdTxt.setEditable(false);
        sessionIdTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FirstPanel1.add(sessionIdTxt);

        FirstPanel2.setLayout(new java.awt.GridLayout(1, 2, 50, 10));

        searchField.setPreferredSize(new java.awt.Dimension(68, 20));
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });
        FirstPanel2.add(searchField);

        FirstPanel3.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label6.setText("Date");
        FirstPanel3.add(label6);
        FirstPanel3.add(dateChooserPanel);

        FirstPanel4.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label5.setText("Start Time");
        FirstPanel4.add(label5);

        label10.setText("End Time");
        FirstPanel4.add(label10);
        FirstPanel4.add(startTimeChooser);
        FirstPanel4.add(endTimeChooserPanel);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FirstPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FirstPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(FirstPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100))
                    .addComponent(FirstPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FirstPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(HeaderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(FirstPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(FirstPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(FirstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(FirstPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(FirstPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        AddSessionBtn.setBackground(new java.awt.Color(30, 64, 175));
        AddSessionBtn.setForeground(new java.awt.Color(255, 255, 255));
        AddSessionBtn.setText("Add Session");
        AddSessionBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddSessionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddSessionBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddSessionBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(AddSessionBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeIconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeIconMouseReleased
        this.dispose();
    }//GEN-LAST:event_closeIconMouseReleased

    private void AddSessionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddSessionBtnActionPerformed

        String session_id = sessionIdTxt.getText().trim();
        String subject_name = subjectTxt.getText().trim();
        String lecturer_email = lecturerNameTxt.getText().trim();
        String date = dateChooserPanel.getSqlDateString();
        String startTime = startTimeChooser.getSqlTimeString();
        String endTime = endTimeChooserPanel.getSqlTimeString();

        if (subject_name.isBlank() || lecturer_email.isBlank() || date.isBlank() || startTime.isBlank() || endTime.isBlank()) {
            JOptionPane.showMessageDialog(this, "Failed to Add Session. Fill all Data.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            MySQL.execute("INSERT INTO `session` (`session_id`,`subject`,`date`,`start_time`,`end_time`,`status`) VALUES ('" + session_id + "','" + subject_name + "','" + date + "','" + startTime + "','" + endTime + "','1')");
            sessionPanel.Refresh();
            JOptionPane.showMessageDialog(this, "Success to Add Session...", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to Add Session. Already Addeed Session or Something went wrong. Please Check.", "Error", JOptionPane.ERROR_MESSAGE);
            logger.severe("Failed to Add Session. Already Addeed Session or Something went wrong." + e.getMessage());

        }

    }//GEN-LAST:event_AddSessionBtnActionPerformed

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased

        try {

            String keyword = searchField.getText();

            if (keyword.isBlank()) {
                Refresh();
                return;
            }

            ResultSet rs = MySQL.execute(
                    "SELECT * FROM `lecturer_has_subject` WHERE `lhs_id` LIKE '%" + keyword + "%' "
                    + "OR `subject` LIKE '%" + keyword + "%' "
                    + "OR `lecturer_email` LIKE '%" + keyword + "%'"
            );

            if (rs.next()) {

                String subject_name = rs.getString("subject");
                String lecturer_email = rs.getString("lecturer_email");

                subjectTxt.setText(subject_name);
                lecturerNameTxt.setText(lecturer_email);

            } else {
                JOptionPane.showMessageDialog(this, "No subject or lecturer found with that keyword.", "No Result", JOptionPane.INFORMATION_MESSAGE);

            }

        } catch (Exception e) {
            logger.severe("Failed to search session data." + e.getMessage());

        }

    }//GEN-LAST:event_searchFieldKeyReleased

    public static void main(String args[]) {

        FlatIntelliJLaf.setup();

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.tchk.component.RoundBtn AddSessionBtn;
    private lk.tchk.component.RoundPanel FirstPanel;
    private lk.tchk.component.RoundPanel FirstPanel1;
    private lk.tchk.component.RoundPanel FirstPanel2;
    private lk.tchk.component.RoundPanel FirstPanel3;
    private lk.tchk.component.RoundPanel FirstPanel4;
    private lk.tchk.component.RoundPanel FourthPanel;
    private lk.tchk.component.ComboBox GenderValue;
    private lk.tchk.component.MainPanel HeaderPanel;
    private lk.tchk.component.ComboBox StatusValue;
    private javax.swing.JLabel closeIcon;
    private lk.tchk.component.DateChooserPanel dateChooserPanel;
    private lk.tchk.component.TimeChooserPanel endTimeChooserPanel;
    private javax.swing.JLabel jLabel1;
    private lk.tchk.component.Label label10;
    private lk.tchk.component.Label label3;
    private lk.tchk.component.Label label4;
    private lk.tchk.component.Label label5;
    private lk.tchk.component.Label label6;
    private lk.tchk.component.Label label7;
    private lk.tchk.component.Label label8;
    private lk.tchk.component.Label label9;
    private lk.tchk.component.inputField lecturerNameTxt;
    private lk.tchk.component.RoundPanel roundPanel1;
    private lk.tchk.component.inputField searchField;
    private lk.tchk.component.inputField sessionIdTxt;
    private lk.tchk.component.TimeChooserPanel startTimeChooser;
    private lk.tchk.component.inputField subjectTxt;
    // End of variables declaration//GEN-END:variables
}
