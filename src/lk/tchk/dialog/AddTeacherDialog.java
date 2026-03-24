package lk.tchk.dialog;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import lk.tchk.connection.MySQL;
import lk.tchk.panel.TeacherPanel;
import lk.tchk.validation.validater;

public class AddTeacherDialog extends javax.swing.JDialog {

        private TeacherPanel TeacherPanel;

    
    public AddTeacherDialog(java.awt.Frame parent, boolean modal, TeacherPanel teacherPanel) {
        super(parent, modal);
        this.TeacherPanel = teacherPanel;
        initComponents();
        init();
        loadGender();
        loadStatus();
        generateTeacherID();
    }

    private Vector<Integer> statusIdList = new Vector<>();

    private void loadStatus() {

        try {
            ResultSet rs = MySQL.execute("SELECT * FROM `status`");

            Vector<String> statusName = new Vector<>();

            while (rs.next()) {
                statusIdList.add(rs.getInt("status_id"));
                statusName.add(rs.getString("status"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(statusName);
            StatusValue.setModel(dcm);

        } catch (SQLException ex) {
            Logger.getLogger(AddStudentDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Vector<Integer> genderIdList = new Vector<>();

    private void loadGender() {

        try {

            ResultSet rs = MySQL.execute("SELECT * FROM `gender`");

            Vector<String> genderName = new Vector<>();

            while (rs.next()) {
                genderIdList.add(rs.getInt("gender_id"));
                genderName.add(rs.getString("gender"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(genderName);
            GenderValue.setModel(dcm);

        } catch (SQLException e) {
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

        roundPanel1 = new lk.tchk.component.RoundPanel();
        HeaderPanel = new lk.tchk.component.MainPanel();
        closeIcon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        FirstPanel = new lk.tchk.component.RoundPanel();
        label3 = new lk.tchk.component.Label();
        label4 = new lk.tchk.component.Label();
        FirstNameTxt = new lk.tchk.component.inputField();
        LastNameTxt = new lk.tchk.component.inputField();
        SecondPanel = new lk.tchk.component.RoundPanel();
        label5 = new lk.tchk.component.Label();
        label6 = new lk.tchk.component.Label();
        EmailTxt = new lk.tchk.component.inputField();
        NicTxt = new lk.tchk.component.inputField();
        ThirdPanel = new lk.tchk.component.RoundPanel();
        label1 = new lk.tchk.component.Label();
        label2 = new lk.tchk.component.Label();
        DobTxt = new lk.tchk.component.FormattedTextField();
        MobileTxt = new lk.tchk.component.inputField();
        FourthPanel = new lk.tchk.component.RoundPanel();
        label7 = new lk.tchk.component.Label();
        label8 = new lk.tchk.component.Label();
        GenderValue = new lk.tchk.component.ComboBox();
        StatusValue = new lk.tchk.component.ComboBox();
        FirstPanel1 = new lk.tchk.component.RoundPanel();
        label9 = new lk.tchk.component.Label();
        TeacherID = new lk.tchk.component.inputField();
        AddTeacherBtn = new lk.tchk.component.RoundBtn();

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
        jLabel1.setText("Add Lecturer");

        javax.swing.GroupLayout HeaderPanelLayout = new javax.swing.GroupLayout(HeaderPanel);
        HeaderPanel.setLayout(HeaderPanelLayout);
        HeaderPanelLayout.setHorizontalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
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
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );

        FirstPanel.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label3.setText("First Name");
        FirstPanel.add(label3);

        label4.setText("Last Name");
        FirstPanel.add(label4);
        FirstPanel.add(FirstNameTxt);
        FirstPanel.add(LastNameTxt);

        SecondPanel.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label5.setText("Email Address");
        SecondPanel.add(label5);

        label6.setText("NIC Number");
        SecondPanel.add(label6);
        SecondPanel.add(EmailTxt);
        SecondPanel.add(NicTxt);

        ThirdPanel.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label1.setText("Date of Birth");
        ThirdPanel.add(label1);

        label2.setText("Mobile");
        ThirdPanel.add(label2);

        try {
            DobTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ThirdPanel.add(DobTxt);
        ThirdPanel.add(MobileTxt);

        FourthPanel.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label7.setText("Gender");
        FourthPanel.add(label7);

        label8.setText("Status");
        FourthPanel.add(label8);
        FourthPanel.add(GenderValue);
        FourthPanel.add(StatusValue);

        FirstPanel1.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label9.setText("Lecturer ID");
        FirstPanel1.add(label9);

        TeacherID.setEditable(false);
        TeacherID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FirstPanel1.add(TeacherID);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FirstPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ThirdPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HeaderPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SecondPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FourthPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FirstPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FirstPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(FirstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SecondPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ThirdPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(FourthPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        AddTeacherBtn.setBackground(new java.awt.Color(30, 64, 175));
        AddTeacherBtn.setForeground(new java.awt.Color(255, 255, 255));
        AddTeacherBtn.setText("Add Teacher");
        AddTeacherBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddTeacherBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddTeacherBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(481, Short.MAX_VALUE)
                .addComponent(AddTeacherBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(AddTeacherBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeIconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeIconMouseReleased
        this.dispose();
    }//GEN-LAST:event_closeIconMouseReleased

    private void generateTeacherID() {
    try {
        ResultSet rs = MySQL.execute("SELECT COUNT(*) AS total FROM lecturer");
        if (rs.next()) {
            int count = rs.getInt("total") + 1;
            String newId = String.format("TCH_%03d", count); // e.g., stu_001, stu_002
            TeacherID.setText(newId);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Failed to generate Teacher ID", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    
    private void AddTeacherBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddTeacherBtnActionPerformed
        String teacherID = TeacherID.getText().trim();
        String fname = FirstNameTxt.getText().trim();
        String lname = LastNameTxt.getText().trim();
        String email = EmailTxt.getText().trim();
        String mobile = MobileTxt.getText().trim();
        String nic = NicTxt.getText().trim();
        String dob = DobTxt.getText().trim();

        if (fname.isBlank() || lname.isBlank() || email.isBlank() || mobile.isBlank() || nic.isBlank() || dob.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (GenderValue.getSelectedIndex() == -1 || StatusValue.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Please select gender and status.", "Missing Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(!validater.isEmailValid(email)){
            return;
        }
        
        if(!validater.isMobileValid(mobile)){
            return;
        }

        int genderId = genderIdList.get(GenderValue.getSelectedIndex());
        int statusId = statusIdList.get(StatusValue.getSelectedIndex());

        try {
            ResultSet rs = MySQL.execute(
                    "SELECT * FROM `lecturer` WHERE `email` = '" + email + "' OR `nic` = '" + nic + "' OR `mobile` = '" + mobile + "'"
            );

            if (rs.next()) {
                String existingFields = "";
                if (email.equals(rs.getString("email"))) {
                    existingFields = "Email ";
                }
                if (nic.equals(rs.getString("nic"))) {
                    existingFields = "NIC ";
                }
                if (mobile.equals(rs.getString("mobile"))) {
                    existingFields = "Mobile ";
                }

                JOptionPane.showMessageDialog(this, "Duplicate entry found for: " + existingFields.trim(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            MySQL.execute("INSERT INTO `lecturer` "
                    + "(`id`,`fname`, `lname`, `email`, `dob`, `mobile`, `nic`, `status_id`, `gender_id`) "
                    + "VALUES "
                    + "('"+teacherID+"','" + fname + "', '" + lname + "', '" + email + "', '" + dob + "', '" + mobile + "', '" + nic + "', '" + statusId + "', '" + genderId + "')");

            TeacherPanel.loadTeacherTableData();
            
            JOptionPane.showMessageDialog(this, fname + " " + lname + " added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while adding student.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_AddTeacherBtnActionPerformed

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AddTeacherDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AddTeacherDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AddTeacherDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AddTeacherDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                AddTeacherDialog dialog = new AddTeacherDialog(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.tchk.component.RoundBtn AddTeacherBtn;
    private lk.tchk.component.FormattedTextField DobTxt;
    private lk.tchk.component.inputField EmailTxt;
    private lk.tchk.component.inputField FirstNameTxt;
    private lk.tchk.component.RoundPanel FirstPanel;
    private lk.tchk.component.RoundPanel FirstPanel1;
    private lk.tchk.component.RoundPanel FourthPanel;
    private lk.tchk.component.ComboBox GenderValue;
    private lk.tchk.component.MainPanel HeaderPanel;
    private lk.tchk.component.inputField LastNameTxt;
    private lk.tchk.component.inputField MobileTxt;
    private lk.tchk.component.inputField NicTxt;
    private lk.tchk.component.RoundPanel SecondPanel;
    private lk.tchk.component.ComboBox StatusValue;
    private lk.tchk.component.inputField TeacherID;
    private lk.tchk.component.RoundPanel ThirdPanel;
    private javax.swing.JLabel closeIcon;
    private javax.swing.JLabel jLabel1;
    private lk.tchk.component.Label label1;
    private lk.tchk.component.Label label2;
    private lk.tchk.component.Label label3;
    private lk.tchk.component.Label label4;
    private lk.tchk.component.Label label5;
    private lk.tchk.component.Label label6;
    private lk.tchk.component.Label label7;
    private lk.tchk.component.Label label8;
    private lk.tchk.component.Label label9;
    private lk.tchk.component.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
