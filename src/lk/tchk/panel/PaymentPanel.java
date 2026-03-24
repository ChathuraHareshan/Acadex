package lk.tchk.panel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Vector;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import lk.tchk.connection.MySQL;

import java.util.logging.Logger;
import lk.tchk.logs.LMSLogger;

public class PaymentPanel extends javax.swing.JPanel {

    private static final Logger logger = LMSLogger.getLogger("student");

    public static final int TotalPayment = 50000;

    public PaymentPanel() {
        initComponents();
        init();
        loadCompleteStudent();
        loadPendingteStudent();
        loadEmptyStudent();
    }

    private void Refresh() {
        loadEmptyStudent();
        loadCompleteStudent();
        loadPendingteStudent();
        studentEmailTxt.setText("");
        studentIdTxt.setText("");
        studentNameTxt.setText("");
        studentnicTxt.setText("");
        BalancePaymentTxt.setText("");
        CurrentPaymentTxt.setText("");
        SearchField.setText("");
    }

    private void loadEmptyStudent() {

        try {

            ResultSet rs = MySQL.execute("SELECT COUNT(*) AS total"
                    + " FROM `student` "
                    + "WHERE NOT EXISTS "
                    + "(SELECT * FROM `student_payment`"
                    + " WHERE student_payment.student_id = student.id)");

            if (rs.next()) {
                int count = rs.getInt("total");
                UnpaidStudent.setText(String.valueOf(count));

                int unrecievedAmount = TotalPayment * count;

                NumberFormat format = NumberFormat.getNumberInstance(new Locale("en", "us"));
                String FormattedAmount = format.format(unrecievedAmount);

                UnrecivedAmount.setText("Rs." + FormattedAmount + ".00");

            }

        } catch (SQLException e) {
            logger.severe("Failed to Load Empty Student Data.: " + e.getMessage());

        }

    }

    private void loadPendingteStudent() {
        try {
            int totalDue = 0;
            int pendingCount = 0;

            ResultSet rs1 = MySQL.execute("SELECT SUM(`due_payment`) AS total_due FROM `student_payment` WHERE `due_payment` > 0");
            if (rs1.next()) {
                totalDue = rs1.getInt("total_due");
            }

            ResultSet rs2 = MySQL.execute("SELECT COUNT(*) AS total_pending FROM `student_payment` WHERE `due_payment` > 0");
            if (rs2.next()) {
                pendingCount = rs2.getInt("total_pending");
            }

            NumberFormat format = NumberFormat.getNumberInstance(new Locale("en", "us"));
            String FormattedAmount = format.format(totalDue);

            totalDueTxt.setText("Rs." + FormattedAmount + ".00");

            PendingStudent.setText(String.valueOf(pendingCount));

        } catch (SQLException e) {
            logger.severe("Failed to Load Pending Student Data.: " + e.getMessage());

        }

    }

    private void loadCompleteStudent() {

        try {

            ResultSet rs = MySQL.execute("SELECT COUNT(*) AS total FROM `student_payment` WHERE `payment` = '" + TotalPayment + "'");

            if (rs.next()) {
                int count = rs.getInt("total");
                CompleteStudent.setText(String.valueOf(count));
                int totalAmount = TotalPayment * count;

                NumberFormat Cformat = NumberFormat.getNumberInstance(new Locale("en", "us"));
                String FormattedCAmount = Cformat.format(totalAmount);

                AmountRecieved.setText("Rs." + FormattedCAmount + ".00");

            } else {
                CompleteStudent.setText("0");
            }

        } catch (SQLException e) {
            logger.severe("Failed to Load Complete Student Data.: " + e.getMessage());

        }

    }

    private void init() {

        DetailsPanel2.setBackground(new Color(255, 255, 255, 40));
        DetailsPanel2.setBorder(new EmptyBorder(5, 10, 5, 10));

        DetailsPanel1.setBackground(new Color(255, 255, 255, 40));
        DetailsPanel1.setBorder(new EmptyBorder(5, 10, 5, 10));

        DetailsPanel3.setBackground(new Color(255, 255, 255, 40));
        DetailsPanel3.setBorder(new EmptyBorder(5, 10, 5, 10));

        DetailsPanel4.setBackground(new Color(255, 255, 255, 40));
        DetailsPanel4.setBorder(new EmptyBorder(5, 10, 5, 10));

        DetailsPanel5.setBorder(new EmptyBorder(10, 10, 10, 10));

        TotalPaymetTxt.setText("Rs." + String.format("%,d", TotalPayment) + ".00");

        addPaymetPanelParent.setBorder(new EmptyBorder(15, 15, 15, 15));
        PaymentSummaryPanelParent.setBorder(new EmptyBorder(15, 15, 15, 15));

        AddPaymentPanel.setBackground(new Color(255, 255, 255, 40));

        PaymentSummaryPanel.setBackground(new Color(255, 255, 255, 40));

        paymentSummaryLabel.setIcon(new FlatSVGIcon("lk/tchk/img/Payment/summary.svg",
                30,
                30));

        addPaymentLabel.setIcon(new FlatSVGIcon("lk/tchk/img/Payment/card.svg",
                30,
                30));

        SearchField.putClientProperty("JTextField.placeholderText",
                "Search student from NIC or Email or Student ID");

        completeCard.setBackground(new Color(76, 175, 80, 38));
        completeCard.setBorder(new LineBorder(new Color(76, 175, 80, 102), 2, true));

        pendingCard.setBackground(new Color(255, 152, 0, 38));
        pendingCard.setBorder(new LineBorder(new Color(255, 152, 0, 102), 2));

        emptyCard.setBackground(new Color(244, 67, 54, 38));
        emptyCard.setBorder(new LineBorder(new Color(244, 67, 54, 102), 2));

        CardPanel.setBackground(new Color(255, 255, 255, 0));
        jScrollPane2.setBackground(new Color(255, 255, 255, 0));

        StudentTable.setGridColor(new Color(200, 200, 230));

        JTableHeader header = StudentTable.getTableHeader();
        header.setFont(new Font("Verdana", Font.BOLD, 14));
        header.setBackground(new Color(102, 126, 234));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 40));

        StudentTable.setSelectionBackground(new Color(102, 126, 234, 80));
        StudentTable.setSelectionForeground(new Color(102, 126, 234));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new lk.tchk.component.RoundPanel();
        HeadPanel = new lk.tchk.component.RoundPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        FirstPanel = new lk.tchk.component.RoundPanel();
        addPaymetPanelParent = new lk.tchk.component.RoundPanel();
        AddPaymentPanel = new lk.tchk.component.RoundPanel();
        addPaymentLabel = new javax.swing.JLabel();
        SearchField = new lk.tchk.component.inputField();
        DetailsPanel1 = new lk.tchk.component.RoundPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        studentIdTxt = new lk.tchk.component.inputField();
        studentNameTxt = new lk.tchk.component.inputField();
        DetailsPanel2 = new lk.tchk.component.RoundPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        studentnicTxt = new lk.tchk.component.inputField();
        studentEmailTxt = new lk.tchk.component.inputField();
        DetailsPanel3 = new lk.tchk.component.RoundPanel();
        jLabel9 = new javax.swing.JLabel();
        TotalPaymetTxt = new lk.tchk.component.inputField();
        DetailsPanel4 = new lk.tchk.component.RoundPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        CurrentPaymentTxt = new lk.tchk.component.inputField();
        BalancePaymentTxt = new lk.tchk.component.inputField();
        DetailsPanel5 = new lk.tchk.component.RoundPanel();
        primaryBtn1 = new lk.tchk.component.PrimaryBtn();
        seconderyBtn1 = new lk.tchk.component.SeconderyBtn();
        PaymentSummaryPanelParent = new lk.tchk.component.RoundPanel();
        PaymentSummaryPanel = new lk.tchk.component.RoundPanel();
        paymentSummaryLabel = new javax.swing.JLabel();
        CardPanel = new javax.swing.JPanel();
        completeCard = new lk.tchk.component.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        CompleteStudent = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        AmountRecieved = new javax.swing.JLabel();
        pendingCard = new lk.tchk.component.RoundPanel();
        jLabel26 = new javax.swing.JLabel();
        PendingStudent = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        totalDueTxt = new javax.swing.JLabel();
        emptyCard = new lk.tchk.component.RoundPanel();
        jLabel22 = new javax.swing.JLabel();
        UnpaidStudent = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        UnrecivedAmount = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        StudentTable = new javax.swing.JTable();

        HeadPanel.setBackground(new java.awt.Color(0, 30, 108));

        jLabel6.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Welcome back, Admin");

        jLabel5.setFont(new java.awt.Font("Bell MT", 1, 28)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(" Payment manager");

        javax.swing.GroupLayout HeadPanelLayout = new javax.swing.GroupLayout(HeadPanel);
        HeadPanel.setLayout(HeadPanelLayout);
        HeadPanelLayout.setHorizontalGroup(
            HeadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HeadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        FirstPanel.setLayout(new java.awt.GridLayout(1, 2, 15, 15));

        addPaymetPanelParent.setBackground(new java.awt.Color(0, 30, 108));

        AddPaymentPanel.setBackground(new java.awt.Color(153, 153, 153));

        addPaymentLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addPaymentLabel.setForeground(new java.awt.Color(255, 255, 255));
        addPaymentLabel.setText("Add / Update Payment");

        SearchField.setForeground(new java.awt.Color(102, 102, 102));
        SearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchFieldKeyReleased(evt);
            }
        });

        DetailsPanel1.setBackground(new java.awt.Color(0, 0, 153));
        DetailsPanel1.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Student ID");
        DetailsPanel1.add(jLabel7);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Student Name");
        DetailsPanel1.add(jLabel8);
        DetailsPanel1.add(studentIdTxt);
        DetailsPanel1.add(studentNameTxt);

        DetailsPanel2.setBackground(new java.awt.Color(0, 0, 153));
        DetailsPanel2.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Student NIC");
        DetailsPanel2.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Student Email");
        DetailsPanel2.add(jLabel4);
        DetailsPanel2.add(studentnicTxt);
        DetailsPanel2.add(studentEmailTxt);

        DetailsPanel3.setBackground(new java.awt.Color(0, 0, 153));
        DetailsPanel3.setLayout(new java.awt.GridLayout(2, 0));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Total Payment");
        DetailsPanel3.add(jLabel9);

        TotalPaymetTxt.setEditable(false);
        TotalPaymetTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotalPaymetTxt.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        DetailsPanel3.add(TotalPaymetTxt);

        DetailsPanel4.setBackground(new java.awt.Color(0, 0, 153));
        DetailsPanel4.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Current Payment Amount (Rs.)");
        DetailsPanel4.add(jLabel10);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Balance to be Paid Later (Rs.)");
        DetailsPanel4.add(jLabel11);

        CurrentPaymentTxt.setForeground(new java.awt.Color(0, 0, 0));
        CurrentPaymentTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CurrentPaymentTxt.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        CurrentPaymentTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CurrentPaymentTxtKeyReleased(evt);
            }
        });
        DetailsPanel4.add(CurrentPaymentTxt);

        BalancePaymentTxt.setEditable(false);
        BalancePaymentTxt.setForeground(new java.awt.Color(255, 0, 51));
        BalancePaymentTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        BalancePaymentTxt.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        DetailsPanel4.add(BalancePaymentTxt);

        DetailsPanel5.setBackground(new java.awt.Color(255, 255, 255));
        DetailsPanel5.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        primaryBtn1.setText("Add Payment");
        primaryBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primaryBtn1ActionPerformed(evt);
            }
        });
        DetailsPanel5.add(primaryBtn1);

        seconderyBtn1.setText("Update Payment");
        seconderyBtn1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        seconderyBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seconderyBtn1ActionPerformed(evt);
            }
        });
        DetailsPanel5.add(seconderyBtn1);

        javax.swing.GroupLayout AddPaymentPanelLayout = new javax.swing.GroupLayout(AddPaymentPanel);
        AddPaymentPanel.setLayout(AddPaymentPanelLayout);
        AddPaymentPanelLayout.setHorizontalGroup(
            AddPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddPaymentPanelLayout.createSequentialGroup()
                .addGroup(AddPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddPaymentPanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(addPaymentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(AddPaymentPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(DetailsPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(AddPaymentPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(DetailsPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE))
                    .addGroup(AddPaymentPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(DetailsPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(AddPaymentPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(DetailsPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(AddPaymentPanelLayout.createSequentialGroup()
                .addGroup(AddPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddPaymentPanelLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddPaymentPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(DetailsPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(33, 33, 33))
        );
        AddPaymentPanelLayout.setVerticalGroup(
            AddPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddPaymentPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(addPaymentLabel)
                .addGap(18, 18, 18)
                .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(DetailsPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DetailsPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DetailsPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DetailsPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(DetailsPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout addPaymetPanelParentLayout = new javax.swing.GroupLayout(addPaymetPanelParent);
        addPaymetPanelParent.setLayout(addPaymetPanelParentLayout);
        addPaymetPanelParentLayout.setHorizontalGroup(
            addPaymetPanelParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AddPaymentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addPaymetPanelParentLayout.setVerticalGroup(
            addPaymetPanelParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPaymetPanelParentLayout.createSequentialGroup()
                .addComponent(AddPaymentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 81, Short.MAX_VALUE))
        );

        FirstPanel.add(addPaymetPanelParent);

        PaymentSummaryPanelParent.setBackground(new java.awt.Color(0, 30, 108));

        PaymentSummaryPanel.setBackground(new java.awt.Color(153, 153, 153));

        paymentSummaryLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        paymentSummaryLabel.setForeground(new java.awt.Color(255, 255, 255));
        paymentSummaryLabel.setText("Payment Summary");

        CardPanel.setLayout(new java.awt.GridLayout(1, 3, 10, 0));

        completeCard.setBackground(new java.awt.Color(204, 255, 204));
        completeCard.setPreferredSize(new java.awt.Dimension(200, 130));
        completeCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                completeCardMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Sans Serif Collection", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(76, 175, 80));
        jLabel1.setText("Student Paid in Full");

        CompleteStudent.setFont(new java.awt.Font("Sans Serif Collection", 1, 36)); // NOI18N
        CompleteStudent.setForeground(new java.awt.Color(255, 255, 255));
        CompleteStudent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CompleteStudent.setText("10");

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Amount Received");

        AmountRecieved.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AmountRecieved.setForeground(new java.awt.Color(255, 255, 255));
        AmountRecieved.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AmountRecieved.setText("50,000");

        javax.swing.GroupLayout completeCardLayout = new javax.swing.GroupLayout(completeCard);
        completeCard.setLayout(completeCardLayout);
        completeCardLayout.setHorizontalGroup(
            completeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(completeCardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(completeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(CompleteStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AmountRecieved, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        completeCardLayout.setVerticalGroup(
            completeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(completeCardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CompleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AmountRecieved, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        CardPanel.add(completeCard);

        pendingCard.setBackground(new java.awt.Color(255, 204, 204));
        pendingCard.setPreferredSize(new java.awt.Dimension(200, 130));
        pendingCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pendingCardMouseClicked(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Sans Serif Collection", 1, 13)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 152, 0));
        jLabel26.setText("Student on Installment Plan");

        PendingStudent.setFont(new java.awt.Font("Sans Serif Collection", 1, 36)); // NOI18N
        PendingStudent.setForeground(new java.awt.Color(255, 255, 255));
        PendingStudent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PendingStudent.setText("5");

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Amount Due");

        totalDueTxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        totalDueTxt.setForeground(new java.awt.Color(255, 255, 255));
        totalDueTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalDueTxt.setText("50,000");

        javax.swing.GroupLayout pendingCardLayout = new javax.swing.GroupLayout(pendingCard);
        pendingCard.setLayout(pendingCardLayout);
        pendingCardLayout.setHorizontalGroup(
            pendingCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingCardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pendingCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(PendingStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalDueTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pendingCardLayout.setVerticalGroup(
            pendingCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingCardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PendingStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalDueTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        CardPanel.add(pendingCard);

        emptyCard.setBackground(new java.awt.Color(255, 102, 153));
        emptyCard.setPreferredSize(new java.awt.Dimension(200, 130));
        emptyCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emptyCardMouseClicked(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Sans Serif Collection", 1, 13)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(244, 67, 54));
        jLabel22.setText("Unpaid Student");

        UnpaidStudent.setFont(new java.awt.Font("Sans Serif Collection", 1, 36)); // NOI18N
        UnpaidStudent.setForeground(new java.awt.Color(255, 255, 255));
        UnpaidStudent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        UnpaidStudent.setText("21");

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Unreceived Amount");

        UnrecivedAmount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        UnrecivedAmount.setForeground(new java.awt.Color(255, 255, 255));
        UnrecivedAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        UnrecivedAmount.setText("50,000");

        javax.swing.GroupLayout emptyCardLayout = new javax.swing.GroupLayout(emptyCard);
        emptyCard.setLayout(emptyCardLayout);
        emptyCardLayout.setHorizontalGroup(
            emptyCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emptyCardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(emptyCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UnpaidStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(UnrecivedAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        emptyCardLayout.setVerticalGroup(
            emptyCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emptyCardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UnpaidStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UnrecivedAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        CardPanel.add(emptyCard);

        StudentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "NIC", "Name", "Amount Paid", "Due Payment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        StudentTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(StudentTable);
        if (StudentTable.getColumnModel().getColumnCount() > 0) {
            StudentTable.getColumnModel().getColumn(0).setResizable(false);
            StudentTable.getColumnModel().getColumn(1).setResizable(false);
            StudentTable.getColumnModel().getColumn(2).setResizable(false);
            StudentTable.getColumnModel().getColumn(3).setResizable(false);
            StudentTable.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout PaymentSummaryPanelLayout = new javax.swing.GroupLayout(PaymentSummaryPanel);
        PaymentSummaryPanel.setLayout(PaymentSummaryPanelLayout);
        PaymentSummaryPanelLayout.setHorizontalGroup(
            PaymentSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaymentSummaryPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PaymentSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                    .addComponent(paymentSummaryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
            .addGroup(PaymentSummaryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        PaymentSummaryPanelLayout.setVerticalGroup(
            PaymentSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaymentSummaryPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(paymentSummaryLabel)
                .addGap(27, 27, 27)
                .addComponent(CardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PaymentSummaryPanelParentLayout = new javax.swing.GroupLayout(PaymentSummaryPanelParent);
        PaymentSummaryPanelParent.setLayout(PaymentSummaryPanelParentLayout);
        PaymentSummaryPanelParentLayout.setHorizontalGroup(
            PaymentSummaryPanelParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PaymentSummaryPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PaymentSummaryPanelParentLayout.setVerticalGroup(
            PaymentSummaryPanelParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaymentSummaryPanelParentLayout.createSequentialGroup()
                .addComponent(PaymentSummaryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 83, Short.MAX_VALUE))
        );

        FirstPanel.add(PaymentSummaryPanelParent);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FirstPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(FirstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1367, Short.MAX_VALUE)
                    .addComponent(HeadPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HeadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void CurrentPaymentTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CurrentPaymentTxtKeyReleased

        try {

            int CPayment = Integer.parseInt(CurrentPaymentTxt.getText());

            String stuid = studentIdTxt.getText();

            ResultSet rs = MySQL.execute("SELECT * FROM `student_payment`"
                    + " WHERE `student_id` = '" + stuid + "'  ");

            if (rs.next()) {
                int stuBalance = rs.getInt("due_payment");
                int sbalance = stuBalance - CPayment;
                BalancePaymentTxt.setText(String.valueOf(sbalance));

            } else {
                int balance = TotalPayment - CPayment;
                BalancePaymentTxt.setText(String.valueOf(balance));
            }

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter only numbers for the current payment.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);

            CurrentPaymentTxt.setText("");
            BalancePaymentTxt.setText("");

            logger.severe("Failed to set Current payment Details and Balance.: " + e.getMessage());

        }


    }//GEN-LAST:event_CurrentPaymentTxtKeyReleased

    private void SearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchFieldKeyReleased

        CurrentPaymentTxt.setText("");

        try {

            String keyword = SearchField.getText().trim();

            if (keyword.isEmpty()) {
                Refresh();
                return;
            }

            ResultSet rs = MySQL.execute(
                    "SELECT * FROM `student` WHERE `id` LIKE '%" + keyword + "%' "
                    + "OR `email` LIKE '%" + keyword + "%' "
                    + "OR `nic` LIKE '%" + keyword + "%'"
            );

            if (rs.next()) {
                String stuId = rs.getString("id");
                String email = rs.getString("email");
                String nic = rs.getString("nic");
                String name = rs.getString("fname") + " " + rs.getString("lname");

                ResultSet rs1 = MySQL.execute("SELECT * FROM `student_payment` WHERE `student_id` = '" + stuId + "'");

                if (rs1.next()) {
                    int stuBalance = rs1.getInt("due_payment");
                    BalancePaymentTxt.setText(String.valueOf(stuBalance));
                } else {
                    BalancePaymentTxt.setText("");

                }

                studentIdTxt.setText(stuId);
                studentEmailTxt.setText(email);
                studentNameTxt.setText(name);
                studentnicTxt.setText(nic);
            } else {
                JOptionPane.showMessageDialog(this, "No student found with that keyword.", "No Result", JOptionPane.INFORMATION_MESSAGE);
                logger.warning("Not have searched Student Data," + keyword + "similar.");

            }

        } catch (SQLException | NumberFormatException e) {
            logger.severe("Failed to Load Student Data.: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_SearchFieldKeyReleased

    private void primaryBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primaryBtn1ActionPerformed
        try {

            String StuId = studentIdTxt.getText();
            String currentPaymentText = CurrentPaymentTxt.getText().trim();

            if (StuId.isEmpty() || currentPaymentText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Student ID or Current Payment is empty.",
                        "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ResultSet rs = MySQL.execute("SELECT * FROM `student_payment` WHERE `"
                    + "student_id` = '" + StuId + "'");

            if (!rs.next()) {

                int Payment = Integer.parseInt(CurrentPaymentTxt.getText());
                int balance = Integer.parseInt(BalancePaymentTxt.getText());

                MySQL.execute("INSERT INTO `student_payment` (`student_id`,`payment`,`due_payment`) "
                        + "VALUES ('" + StuId + "','" + Payment + "','" + balance + "')");
                logger.info("Success to add payment.");

                JOptionPane.showMessageDialog(this, "Payment addes Succefully.",
                        "Payment Success!", JOptionPane.INFORMATION_MESSAGE);
                Refresh();

            } else {

                JOptionPane.showMessageDialog(this, "Can't create Payment, Update Student Payment.",
                        "Create Payment Error.", JOptionPane.ERROR_MESSAGE);
                logger.warning("Can't create Payment, Update Student Payment.");

                Refresh();

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(this, "Student ID or Current Payment is empty",
                    "Create Payment Error.", JOptionPane.ERROR_MESSAGE);
            Refresh();

            logger.severe("Payment Create Error.");

        }
    }//GEN-LAST:event_primaryBtn1ActionPerformed

    private void seconderyBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seconderyBtn1ActionPerformed

        try {
            String StuId = studentIdTxt.getText().trim();
            String currentPaymentText = CurrentPaymentTxt.getText().trim();

            if (StuId.isEmpty() || currentPaymentText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Student ID or Current Payment is empty.",
                        "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int CurrentPayment = Integer.parseInt(currentPaymentText);

            ResultSet rs = MySQL.execute("SELECT * FROM `student_payment` WHERE "
                    + "`student_id` = '" + StuId + "'");

            if (rs.next()) {
                int PastPayment = rs.getInt("payment");
                int balance = Integer.parseInt(BalancePaymentTxt.getText().trim());

                int NewAllPayment = PastPayment + CurrentPayment;

                MySQL.execute("UPDATE `student_payment` SET `payment` = '" + NewAllPayment + "',"
                        + " `due_payment` = '" + balance + "' WHERE `student_id` = '" + StuId + "'");

                JOptionPane.showMessageDialog(this, "Payment Updated Successfully.",
                        "Payment Success!", JOptionPane.INFORMATION_MESSAGE);
                Refresh();

            } else {
                JOptionPane.showMessageDialog(this, "Student Payment record not found.",
                        "Payment update Error", JOptionPane.ERROR_MESSAGE);
                logger.warning("Student Payment record not found.");

            }

        } catch (NumberFormatException | SQLException e) {
            logger.severe("Student Payment updated Error.");
        }


    }//GEN-LAST:event_seconderyBtn1ActionPerformed

    private void completeCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_completeCardMouseClicked

        try {

            ResultSet rs = MySQL.execute("SELECT * FROM `student_payment` WHERE `payment` = '" + TotalPayment + "'");

            DefaultTableModel dtm = (DefaultTableModel) StudentTable.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector<String> data = new Vector<>();

                String id = rs.getString("student_id");
                int payment = rs.getInt("payment");
                int duePayment = rs.getInt("due_payment");

                data.add(id);

                ResultSet rs1 = MySQL.execute("SELECT * FROM `student` WHERE `id` = '" + id + "' ");

                if (rs1.next()) {
                    String name = rs1.getString("fname") + " " + rs1.getString("lname");
                    String nic = rs1.getString("nic");

                    data.add(nic);
                    data.add(name);
                }
                data.add(String.valueOf(payment));
                data.add(String.valueOf(duePayment));

                dtm.addRow(data);

            }

        } catch (SQLException e) {
            logger.warning("Failed to load Complete Payment Student Data,");
        }

    }//GEN-LAST:event_completeCardMouseClicked

    private void pendingCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendingCardMouseClicked
        try {

            ResultSet rs = MySQL.execute("SELECT * FROM `student_payment` WHERE `due_payment` > '0'");

            DefaultTableModel dtm = (DefaultTableModel) StudentTable.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector<String> data = new Vector<>();

                String id = rs.getString("student_id");

                int payment = rs.getInt("payment");
                int duePayment = rs.getInt("due_payment");

                data.add(id);

                ResultSet rs1 = MySQL.execute("SELECT * FROM `student` WHERE `id` = '" + id + "' ");

                if (rs1.next()) {
                    String name = rs1.getString("fname") + " " + rs1.getString("lname");
                    String nic = rs1.getString("nic");

                    data.add(nic);
                    data.add(name);
                }

                data.add(String.valueOf(payment));
                data.add(String.valueOf(duePayment));

                dtm.addRow(data);

            }

        } catch (SQLException e) {
            logger.warning("Failed to load Pending Payment Student Data,");

        }
    }//GEN-LAST:event_pendingCardMouseClicked

    private void emptyCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emptyCardMouseClicked
        try {

            ResultSet rs = MySQL.execute("SELECT * "
                    + " FROM `student` "
                    + "WHERE NOT EXISTS "
                    + "(SELECT * FROM `student_payment`"
                    + " WHERE student_payment.student_id = student.id)");

            DefaultTableModel dtm = (DefaultTableModel) StudentTable.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector<String> data = new Vector<>();

                String id = rs.getString("id");

                String name = rs.getString("fname") + " " + rs.getString("lname");
                String nic = rs.getString("nic");

                data.add(id);
                data.add(nic);
                data.add(name);

                data.add("Not Yet");
                data.add("Not Yet");

                dtm.addRow(data);

            }

        } catch (SQLException e) {
            logger.warning("Failed to load Empty Payment Student Data,.");

        }
    }//GEN-LAST:event_emptyCardMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.tchk.component.RoundPanel AddPaymentPanel;
    private javax.swing.JLabel AmountRecieved;
    private lk.tchk.component.inputField BalancePaymentTxt;
    private javax.swing.JPanel CardPanel;
    private javax.swing.JLabel CompleteStudent;
    private lk.tchk.component.inputField CurrentPaymentTxt;
    private lk.tchk.component.RoundPanel DetailsPanel1;
    private lk.tchk.component.RoundPanel DetailsPanel2;
    private lk.tchk.component.RoundPanel DetailsPanel3;
    private lk.tchk.component.RoundPanel DetailsPanel4;
    private lk.tchk.component.RoundPanel DetailsPanel5;
    private lk.tchk.component.RoundPanel FirstPanel;
    private lk.tchk.component.RoundPanel HeadPanel;
    private lk.tchk.component.RoundPanel MainPanel;
    private lk.tchk.component.RoundPanel PaymentSummaryPanel;
    private lk.tchk.component.RoundPanel PaymentSummaryPanelParent;
    private javax.swing.JLabel PendingStudent;
    private lk.tchk.component.inputField SearchField;
    private javax.swing.JTable StudentTable;
    private lk.tchk.component.inputField TotalPaymetTxt;
    private javax.swing.JLabel UnpaidStudent;
    private javax.swing.JLabel UnrecivedAmount;
    private javax.swing.JLabel addPaymentLabel;
    private lk.tchk.component.RoundPanel addPaymetPanelParent;
    private lk.tchk.component.RoundPanel completeCard;
    private lk.tchk.component.RoundPanel emptyCard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel paymentSummaryLabel;
    private lk.tchk.component.RoundPanel pendingCard;
    private lk.tchk.component.PrimaryBtn primaryBtn1;
    private lk.tchk.component.SeconderyBtn seconderyBtn1;
    private lk.tchk.component.inputField studentEmailTxt;
    private lk.tchk.component.inputField studentIdTxt;
    private lk.tchk.component.inputField studentNameTxt;
    private lk.tchk.component.inputField studentnicTxt;
    private javax.swing.JLabel totalDueTxt;
    // End of variables declaration//GEN-END:variables
}
