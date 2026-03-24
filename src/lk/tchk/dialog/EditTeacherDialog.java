
package lk.tchk.dialog;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import lk.tchk.connection.MySQL;
import lk.tchk.panel.TeacherPanel;


public class EditTeacherDialog extends javax.swing.JDialog {
    
    private TeacherPanel TeacherPanel;
    
    private String teacherId;

   
    public EditTeacherDialog(TeacherPanel TeacherPanel, boolean modal, String teacherId) {
        super((java.awt.Frame) null, modal);
        initComponents();
        init();
        this.TeacherPanel = TeacherPanel;
        this.teacherId = teacherId;
        loadGender();
        loadStatus();
        loadTeacherData();
        
    }

    
      private Vector<Integer> statusIdList = new Vector<>();

    private void loadStatus() {

        try {
            ResultSet rs = MySQL.execute("SELECT * FROM `status`");

            Vector<String> statusNames = new Vector<>();

            while (rs.next()) {
                statusIdList.add(rs.getInt("status_id"));
                statusNames.add(rs.getString("status"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(statusNames);
            StatusValue.setModel(dcm);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
      private Vector<Integer> genderIdList = new Vector<>();

    private void loadGender() {
        try {
            ResultSet rs = MySQL.execute("SELECT * FROM `gender`");

            Vector<String> genderNames = new Vector<>();
            genderIdList.clear();

            while (rs.next()) {
                genderIdList.add(rs.getInt("gender_id"));
                genderNames.add(rs.getString("gender"));
            }

            DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<>(genderNames);
            GenderValue.setModel(dcm);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
      private void loadTeacherData() {
        try {
            ResultSet rs = MySQL.execute("SELECT * FROM `lecturer` WHERE `id` = '" + teacherId + "'");
            if (rs.next()) {
                StuId.setText(rs.getString("id"));
                FirstNameTxt.setText(rs.getString("fname"));
                LastNameTxt.setText(rs.getString("lname"));
                EmailTxt.setText(rs.getString("email"));
                MobileTxt.setText(rs.getString("mobile"));
                NicTxt.setText(rs.getString("nic"));
                DobTxt.setText(rs.getString("dob"));

                int TeacherGenderIdFromDB = rs.getInt("gender_id"); 

                for (int i = 0; i < genderIdList.size(); i++) {
                    if (genderIdList.get(i) == TeacherGenderIdFromDB) {
                        GenderValue.setSelectedIndex(i);
                        break;
                    }
                }

                int TeacherStatusIdFromDB = rs.getInt("status_id");

                for (int y = 0; y < statusIdList.size(); y++) {
                    if (statusIdList.get(y) == TeacherStatusIdFromDB) {
                        StatusValue.setSelectedIndex(y);
                        break;
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load student data.");
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

        MainPanel = new lk.tchk.component.RoundPanel();
        HeaderPanel = new lk.tchk.component.MainPanel();
        jLabel1 = new javax.swing.JLabel();
        closeIcon = new javax.swing.JLabel();
        FirstPanel = new lk.tchk.component.RoundPanel();
        label3 = new lk.tchk.component.Label();
        StuId = new lk.tchk.component.inputField();
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
        UpdateBtn = new lk.tchk.component.RoundBtn();
        SecondPanel = new lk.tchk.component.RoundPanel();
        label5 = new lk.tchk.component.Label();
        label6 = new lk.tchk.component.Label();
        EmailTxt = new lk.tchk.component.inputField();
        NicTxt = new lk.tchk.component.inputField();
        FirstPanel1 = new lk.tchk.component.RoundPanel();
        label9 = new lk.tchk.component.Label();
        label10 = new lk.tchk.component.Label();
        FirstNameTxt = new lk.tchk.component.inputField();
        LastNameTxt = new lk.tchk.component.inputField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        HeaderPanel.setBackground(new java.awt.Color(30, 64, 175));
        HeaderPanel.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Edit Lecturer");

        closeIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeIconMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout HeaderPanelLayout = new javax.swing.GroupLayout(HeaderPanel);
        HeaderPanel.setLayout(HeaderPanelLayout);
        HeaderPanelLayout.setHorizontalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 309, Short.MAX_VALUE)
                .addComponent(closeIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        HeaderPanelLayout.setVerticalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(closeIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        FirstPanel.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label3.setText("Lecturer ID");
        FirstPanel.add(label3);

        StuId.setEditable(false);
        StuId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        StuId.setEnabled(false);
        FirstPanel.add(StuId);

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

        UpdateBtn.setBackground(new java.awt.Color(30, 64, 175));
        UpdateBtn.setForeground(new java.awt.Color(255, 255, 255));
        UpdateBtn.setText("Update Teacher");
        UpdateBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        UpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBtnActionPerformed(evt);
            }
        });

        SecondPanel.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label5.setText("Email Address");
        SecondPanel.add(label5);

        label6.setText("NIC Number");
        SecondPanel.add(label6);
        SecondPanel.add(EmailTxt);
        SecondPanel.add(NicTxt);

        FirstPanel1.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label9.setText("First Name");
        FirstPanel1.add(label9);

        label10.setText("Last Name");
        FirstPanel1.add(label10);
        FirstPanel1.add(FirstNameTxt);
        FirstPanel1.add(LastNameTxt);

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ThirdPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FirstPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HeaderPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(UpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(FirstPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SecondPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FourthPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(FirstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(FirstPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SecondPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ThirdPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(FourthPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(UpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeIconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeIconMouseReleased
        this.dispose();
    }//GEN-LAST:event_closeIconMouseReleased

    private void UpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBtnActionPerformed
        
        String fname = FirstNameTxt.getText();
        String lname = LastNameTxt.getText();
        String email = EmailTxt.getText();
        String mobile = MobileTxt.getText();
        String nic = NicTxt.getText();
        String dob = DobTxt.getText();

        String gender = GenderValue.getSelectedItem().toString().trim();
        int selectedGenderIndex = GenderValue.getSelectedIndex();
        int genderId = genderIdList.get(selectedGenderIndex);

        String status = StatusValue.getSelectedItem().toString().trim();
        int selectedStatusIndex = StatusValue.getSelectedIndex();
        int statusId = statusIdList.get(selectedStatusIndex);

        try {

            ResultSet rs = MySQL.execute("UPDATE `lecturer` SET"
                + " `fname` = '" + fname + "',"
                + "`lname` = '" + lname + "',"
                + "`email` = '" + email + "',"
                + " `dob` = '" + dob + "',"
                + " `mobile` = '" + mobile + "', "
                + " `nic` = '" + nic + "',"
                + " `status_id` = '" + statusId + "',"
                + " `gender_id` = '" + genderId + "'"
                + "WHERE `id` = '"+ teacherId +"' ");

            loadTeacherData();
            
            TeacherPanel.loadTeacherTableData();


            JOptionPane.showConfirmDialog(this,  fname +" "  + lname + " " + "Updated Successfully.", "Updated Successfully." , JOptionPane.DEFAULT_OPTION);

//            TeacherPanel.loadTeacherTableData();
            this.dispose();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_UpdateBtnActionPerformed

    
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
//            java.util.logging.Logger.getLogger(EditTeacherDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(EditTeacherDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(EditTeacherDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(EditTeacherDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                EditTeacherDialog dialog = new EditTeacherDialog(new javax.swing.JFrame(), true);
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
    private lk.tchk.component.FormattedTextField DobTxt;
    private lk.tchk.component.inputField EmailTxt;
    private lk.tchk.component.inputField FirstNameTxt;
    private lk.tchk.component.RoundPanel FirstPanel;
    private lk.tchk.component.RoundPanel FirstPanel1;
    private lk.tchk.component.RoundPanel FourthPanel;
    private lk.tchk.component.ComboBox GenderValue;
    private lk.tchk.component.MainPanel HeaderPanel;
    private lk.tchk.component.inputField LastNameTxt;
    private lk.tchk.component.RoundPanel MainPanel;
    private lk.tchk.component.inputField MobileTxt;
    private lk.tchk.component.inputField NicTxt;
    private lk.tchk.component.RoundPanel SecondPanel;
    private lk.tchk.component.ComboBox StatusValue;
    private lk.tchk.component.inputField StuId;
    private lk.tchk.component.RoundPanel ThirdPanel;
    private lk.tchk.component.RoundBtn UpdateBtn;
    private javax.swing.JLabel closeIcon;
    private javax.swing.JLabel jLabel1;
    private lk.tchk.component.Label label1;
    private lk.tchk.component.Label label10;
    private lk.tchk.component.Label label2;
    private lk.tchk.component.Label label3;
    private lk.tchk.component.Label label5;
    private lk.tchk.component.Label label6;
    private lk.tchk.component.Label label7;
    private lk.tchk.component.Label label8;
    private lk.tchk.component.Label label9;
    // End of variables declaration//GEN-END:variables
}
