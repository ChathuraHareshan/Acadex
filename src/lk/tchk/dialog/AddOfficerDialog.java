package lk.tchk.dialog;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.JOptionPane;
import lk.tchk.connection.MySQL;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import lk.tchk.panel.OfficerPanel;
import lk.tchk.validation.validater;
import java.util.logging.Logger;
import lk.tchk.logs.LMSLogger;

public class AddOfficerDialog extends javax.swing.JDialog {

    private static final Logger logger = LMSLogger.getLogger("addUsers");

    private OfficerPanel OfficerPanel;

    public AddOfficerDialog(java.awt.Frame parent, boolean modal, OfficerPanel officerPanel) {
        super(parent, modal);
        this.OfficerPanel = officerPanel;
        initComponents();
        init();
        loadGender();
        loadStatus();
        generateOfficerID();
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

        } catch (SQLException e) {
            logger.severe("Failed to load Officer Status." + e.getMessage());

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
            logger.severe("Failed to load Officer gender." + e.getMessage());

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
        FourthPanel = new lk.tchk.component.RoundPanel();
        label7 = new lk.tchk.component.Label();
        label8 = new lk.tchk.component.Label();
        GenderValue = new lk.tchk.component.ComboBox();
        StatusValue = new lk.tchk.component.ComboBox();
        ThirdPanel = new lk.tchk.component.RoundPanel();
        label1 = new lk.tchk.component.Label();
        label2 = new lk.tchk.component.Label();
        DobTxt = new lk.tchk.component.FormattedTextField();
        MobileTxt = new lk.tchk.component.inputField();
        FirstPanel1 = new lk.tchk.component.RoundPanel();
        label9 = new lk.tchk.component.Label();
        OfficerId = new lk.tchk.component.inputField();
        AddOfficertBtn = new lk.tchk.component.RoundBtn();

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
        jLabel1.setText("Add Officer");

        javax.swing.GroupLayout HeaderPanelLayout = new javax.swing.GroupLayout(HeaderPanel);
        HeaderPanel.setLayout(HeaderPanelLayout);
        HeaderPanelLayout.setHorizontalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
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

        FourthPanel.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label7.setText("Gender");
        FourthPanel.add(label7);

        label8.setText("Status");
        FourthPanel.add(label8);
        FourthPanel.add(GenderValue);
        FourthPanel.add(StatusValue);

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

        FirstPanel1.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        label9.setText("Officer ID");
        FirstPanel1.add(label9);

        OfficerId.setEditable(false);
        OfficerId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FirstPanel1.add(OfficerId);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ThirdPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(SecondPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FourthPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HeaderPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FirstPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FirstPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FirstPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        AddOfficertBtn.setBackground(new java.awt.Color(30, 64, 175));
        AddOfficertBtn.setForeground(new java.awt.Color(255, 255, 255));
        AddOfficertBtn.setText("Add Officer");
        AddOfficertBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddOfficertBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddOfficertBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddOfficertBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AddOfficertBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeIconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeIconMouseReleased
        this.dispose();
    }//GEN-LAST:event_closeIconMouseReleased

    private void generateOfficerID() {
        try {
            ResultSet rs = MySQL.execute("SELECT COUNT(*) AS total FROM student");
            if (rs.next()) {
                int count = rs.getInt("total") + 1;
                String newId = String.format("OFC_%03d", count);
                OfficerId.setText(newId);
            }
        } catch (SQLException e) {
            logger.severe("Failed to Genarate Officer id." + e.getMessage());
        }
    }


    private void AddOfficertBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddOfficertBtnActionPerformed
        String OfID = OfficerId.getText().trim();
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

        if (!validater.isEmailValid(email)) {
            return;
        }

        if (!validater.isMobileValid(mobile)) {
            return;
        }

        int genderId = genderIdList.get(GenderValue.getSelectedIndex());
        int statusId = statusIdList.get(StatusValue.getSelectedIndex());

        try {
            ResultSet rs = MySQL.execute(
                    "SELECT * FROM `officer` WHERE `email` = '" + email + "' OR `nic` = '" + nic + "' OR `mobile` = '" + mobile + "'"
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

            MySQL.execute("INSERT INTO `officer` "
                    + "(`id`,`fname`, `lname`, `email`, `dob`, `mobile`, `nic`, `status_id`, `gender_id`) "
                    + "VALUES "
                    + "('" + OfID + "','" + fname + "', '" + lname + "', '" + email + "', '" + dob + "', '" + mobile + "', '" + nic + "', '" + statusId + "', '" + genderId + "')");

            OfficerPanel.loadOfficerTableData();

            JOptionPane.showMessageDialog(this, fname + " " + lname + " added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        } catch (SQLException e) {
            logger.severe("An error occurred while adding Officer." + e.getMessage());
            JOptionPane.showMessageDialog(this, "An error occurred while adding Officer.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_AddOfficertBtnActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.tchk.component.RoundBtn AddOfficertBtn;
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
    private lk.tchk.component.inputField OfficerId;
    private lk.tchk.component.RoundPanel SecondPanel;
    private lk.tchk.component.ComboBox StatusValue;
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
