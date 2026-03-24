package lk.tchk.dialog;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import lk.tchk.connection.MySQL;
import lk.tchk.panel.ScheduleSessionPanel;

public class EditSessionDialog extends javax.swing.JDialog {

    private ScheduleSessionPanel scheduleSessionPanel;
    private String sessionId;

    public EditSessionDialog(ScheduleSessionPanel scheduleSessionPanel, boolean modal, String sessionId) {
        super((java.awt.Frame) null, modal);
        initComponents();
        init();
        this.scheduleSessionPanel = scheduleSessionPanel;
        this.sessionId = sessionId;
        loadStatusData();
        loadSessionData();

    }

    private Vector<Integer> statusIdList = new Vector<>();

    private void loadStatusData() {
        statusIdList.clear();

        try {
            ResultSet rs = MySQL.execute("SELECT * FROM `status`");

            Vector<String> statusNames = new Vector<>();

            while (rs.next()) {
                statusIdList.add(rs.getInt("status_id"));
                statusNames.add(rs.getString("status"));
            }

            DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<>(statusNames);
            StatusValue.setModel(dcm);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSessionData() {

        try {

            ResultSet rs = MySQL.execute("SELECT * FROM `session` WHERE `session_id` = '" + sessionId + "'");

            if (rs.next()) {

                String subject = rs.getString("subject");

                sessionIdTxt.setText(rs.getString("session_id"));
                subjectTxt.setText(subject);

                ResultSet rs1 = MySQL.execute("SELECT * FROM `lecturer_has_subject` WHERE `subject` = '" + subject + "'");
                if (rs1.next()) {
                    String lecturerEmail = rs1.getString("lecturer_email");
                    lecturerNameTxt.setText(lecturerEmail);
                }

                dateChooserPanel.setSqlDateString(rs.getString("date"));
                startTimeChooser.setSqlTimeString(rs.getString("start_time"));
                endTimeChooserPanel.setSqlTimeString(rs.getString("end_time"));

                int statusIdFromDB = rs.getInt("status");

                for (int y = 0; y < statusIdList.size(); y++) {
                    if (statusIdList.get(y).intValue() == statusIdFromDB) {
                        StatusValue.setSelectedIndex(y);
                        break;
                    }

                }

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to load Session data.");
            e.printStackTrace();

        }

    }

    private void init() {

        closeIcon.setIcon(new FlatSVGIcon("lk/tchk/img/close.svg",
                closeIcon.getWidth(),
                closeIcon.getHeight()));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EditSessionBtn = new lk.tchk.component.RoundBtn();
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
        FirstPanel5 = new lk.tchk.component.RoundPanel();
        label7 = new lk.tchk.component.Label();
        StatusValue = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        EditSessionBtn.setBackground(new java.awt.Color(30, 64, 175));
        EditSessionBtn.setForeground(new java.awt.Color(255, 255, 255));
        EditSessionBtn.setText("Update Session");
        EditSessionBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EditSessionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditSessionBtnActionPerformed(evt);
            }
        });

        HeaderPanel.setBackground(new java.awt.Color(30, 64, 175));
        HeaderPanel.setOpaque(true);

        closeIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeIconMouseReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Edit Session");

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

        FirstPanel5.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label7.setText("Status");
        FirstPanel5.add(label7);

        FirstPanel5.add(StatusValue);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HeaderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(FirstPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FirstPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addGap(18, 18, 18)
                .addComponent(FirstPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(EditSessionBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(EditSessionBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EditSessionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditSessionBtnActionPerformed

        String subject = subjectTxt.getText().trim();
        String date = dateChooserPanel.getSqlDateString();
        String startTime = startTimeChooser.getSqlTimeString();
        String endTime = endTimeChooserPanel.getSqlTimeString();

        String status = StatusValue.getSelectedItem().toString().trim();
        int selectedStatusIndex = StatusValue.getSelectedIndex();
        int statusId = statusIdList.get(selectedStatusIndex);

        try {

            MySQL.execute("UPDATE `session` SET "
                    + " `subject` = '" + subject + "', "
                    + "`date` = '" + date + "',"
                    + "`start_time` = '" + startTime + "',"
                    + "`end_time` = '" + endTime + "',"
                    + "`status` = '" + statusId + "' WHERE `session_id` = '"+sessionId+"' ");

            scheduleSessionPanel.Refresh();

            JOptionPane.showConfirmDialog(this, "Session Updated Successfully.", "Updated Successfully.", JOptionPane.DEFAULT_OPTION);
            this.dispose();

        } catch (SQLException e) {
            e.printStackTrace();
                        JOptionPane.showConfirmDialog(this, "Something Went wrong.", "Error" , JOptionPane.ERROR);


        }


    }//GEN-LAST:event_EditSessionBtnActionPerformed

    private void closeIconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeIconMouseReleased
        this.dispose();
    }//GEN-LAST:event_closeIconMouseReleased

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased

        try {

            String keyword = searchField.getText();

            if (keyword.isBlank()) {
                loadSessionData();
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

        }
    }//GEN-LAST:event_searchFieldKeyReleased

    public static void main(String args[]) {

        FlatIntelliJLaf.setup();
//
//        java.awt.EventQueue.invokeLater(new Runnable() {

    
    ////            @Override
////            public void run() {
////                EditSessionDialog dialog = new EditSessionDialog(new javax.swing.JFrame(), true);
////                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
////                    @Override
////                    public void windowClosing(java.awt.event.WindowEvent e) {
////                        System.exit(0);
////                    }
////                });
////                dialog.setVisible(true);
////            }
////        });
//    }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.tchk.component.RoundBtn EditSessionBtn;
    private lk.tchk.component.RoundPanel FirstPanel;
    private lk.tchk.component.RoundPanel FirstPanel1;
    private lk.tchk.component.RoundPanel FirstPanel2;
    private lk.tchk.component.RoundPanel FirstPanel3;
    private lk.tchk.component.RoundPanel FirstPanel4;
    private lk.tchk.component.RoundPanel FirstPanel5;
    private lk.tchk.component.MainPanel HeaderPanel;
    private javax.swing.JComboBox<String> StatusValue;
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
    private lk.tchk.component.Label label9;
    private lk.tchk.component.inputField lecturerNameTxt;
    private lk.tchk.component.RoundPanel roundPanel1;
    private lk.tchk.component.inputField searchField;
    private lk.tchk.component.inputField sessionIdTxt;
    private lk.tchk.component.TimeChooserPanel startTimeChooser;
    private lk.tchk.component.inputField subjectTxt;
    // End of variables declaration//GEN-END:variables
}
