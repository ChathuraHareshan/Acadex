package lk.tchk.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.sql.ResultSet;
import java.sql.SQLException;
import lk.tchk.connection.MySQL;

import javax.swing.JOptionPane;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import lk.tchk.dialog.AddTeacherDialog;
import lk.tchk.dialog.EditTeacherDialog;
import lk.tchk.gui.Home;
import lk.tchk.logs.LMSLogger;

public final class TeacherPanel extends javax.swing.JPanel {

    private static final Logger logger = LMSLogger.getLogger("teacher");

    private final Home home;

    private int currentPage = 1;
    private final int rowsPerPage = 20;
    private int totalRows = 0;
    private int totalPages = 0;

    public TeacherPanel(Home parent) {
        this.home = parent;
        initComponents();
        init();
        loadTeacherTableData();
    }

    public void loadTeacherTableData() {

        try {

            int offset = (currentPage - 1) * rowsPerPage;

            ResultSet countRs = MySQL.execute("SELECT COUNT(*) AS total FROM `lecturer`");

            if (countRs.next()) {
                totalRows = countRs.getInt("total");
                totalPages = (int) Math.ceil((double) totalRows / rowsPerPage);
            }

            ResultSet rs = MySQL.execute("SELECT * FROM `lecturer` LIMIT " + rowsPerPage + " OFFSET " + offset);

            DefaultTableModel dtm = (DefaultTableModel) teacherDataTable.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector<String> data = new Vector<>();
                data.add(rs.getString("id"));
                data.add(rs.getString("nic"));
                data.add(rs.getString("fname") + " " + rs.getString("lname"));
                data.add(rs.getString("email"));
                data.add(rs.getString("mobile"));
                data.add(rs.getString("status_id"));

                dtm.addRow(data);

            }

            teacherDataTable.getColumnModel().getColumn(5).setMinWidth(0);
            teacherDataTable.getColumnModel().getColumn(5).setMaxWidth(0);
            teacherDataTable.getColumnModel().getColumn(5).setWidth(0);

            btnPrev.setEnabled(currentPage > 1);
            btnNext.setEnabled(currentPage < totalPages);

        } catch (SQLException e) {
            logger.severe("Failed to Load Teacher Data.: " + e.getMessage());
        }

    }

    private void init() {

        searchField.putClientProperty("JTextField.placeholderText",
                "Search Lecturer from NIC or Email or Lecturer ID");

        HeaderPanel.setOpaque(false);

        searchPanel.setOpaque(false);
        searchPanel.setBackground(new Color(255, 255, 255, 20));

        teacherDataTable.setGridColor(new Color(200, 200, 230));

        JTableHeader header = teacherDataTable.getTableHeader();
        header.setFont(new Font("Verdana", Font.BOLD, 14));
        header.setBackground(new Color(102, 126, 234));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 40));

        teacherDataTable.setSelectionBackground(new Color(102, 126, 234, 80));
        teacherDataTable.setSelectionForeground(new Color(102, 126, 234));

        teacherDataTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                java.awt.Component c = super.getTableCellRendererComponent(table,
                        value, isSelected, hasFocus, row, column);

                try {
                    String statusId = table.getValueAt(row, 5).toString();

                    if (isSelected) {
                        c.setBackground(table.getSelectionBackground());
                        c.setForeground(table.getSelectionForeground());
                    } else if ("2".equals(statusId)) {
                        c.setBackground(new Color(255, 230, 230));
                        c.setForeground(Color.BLACK);
                    } else {
                        c.setBackground(Color.WHITE);
                        c.setForeground(Color.BLACK);
                    }

                } catch (Exception e) {
                    logger.severe("Failed to Load Teacher Status.: " + e.getMessage());

                }

                return c;
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new lk.tchk.component.RoundPanel();
        jScrollPane = new javax.swing.JScrollPane();
        teacherDataTable = new javax.swing.JTable();
        HeaderPanel = new lk.tchk.component.RoundPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        searchPanel = new lk.tchk.component.RoundPanel();
        searchField = new lk.tchk.component.inputField();
        EditBtn = new lk.tchk.component.RoundBtn();
        GenarateBtn = new lk.tchk.component.SeconderyBtn();
        AddTeacherBtn = new lk.tchk.component.PrimaryBtn();
        btnNext = new lk.tchk.component.PrimaryBtn();
        btnPrev = new lk.tchk.component.SeconderyBtn();

        MainPanel.setBackground(new java.awt.Color(248, 250, 252));

        jScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setPreferredSize(new java.awt.Dimension(468, 500));

        teacherDataTable.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        teacherDataTable.setForeground(new java.awt.Color(51, 51, 51));
        teacherDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lecturer ID", "National ID", "Name", "Email", "Mobile", "status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        teacherDataTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        teacherDataTable.setPreferredSize(new java.awt.Dimension(375, 787));
        teacherDataTable.setRowHeight(35);
        teacherDataTable.setRowMargin(2);
        teacherDataTable.setShowHorizontalLines(true);
        teacherDataTable.setShowVerticalLines(true);
        teacherDataTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane.setViewportView(teacherDataTable);

        HeaderPanel.setBackground(new java.awt.Color(0, 30, 108));

        jLabel6.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Welcome back, Admin");

        jLabel5.setFont(new java.awt.Font("Bell MT", 1, 28)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Lecturer Management");

        javax.swing.GroupLayout HeaderPanelLayout = new javax.swing.GroupLayout(HeaderPanel);
        HeaderPanel.setLayout(HeaderPanelLayout);
        HeaderPanelLayout.setHorizontalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1226, Short.MAX_VALUE))
                .addContainerGap())
        );
        HeaderPanelLayout.setVerticalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        searchPanel.setPreferredSize(new java.awt.Dimension(1190, 60));

        searchField.setBackground(new java.awt.Color(248, 250, 252));
        searchField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchField.setPreferredSize(new java.awt.Dimension(68, 50));
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(searchField, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addGap(400, 400, 400))
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        EditBtn.setBackground(new java.awt.Color(16, 185, 129));
        EditBtn.setForeground(new java.awt.Color(255, 255, 255));
        EditBtn.setText("Edit Lecturer");
        EditBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditBtnActionPerformed(evt);
            }
        });

        GenarateBtn.setText("Genarate Report");
        GenarateBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        GenarateBtn.setPreferredSize(new java.awt.Dimension(145, 40));

        AddTeacherBtn.setText("Add Lecturer");
        AddTeacherBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddTeacherBtn.setPreferredSize(new java.awt.Dimension(145, 40));
        AddTeacherBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddTeacherBtnActionPerformed(evt);
            }
        });

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrev.setText("Previous");
        btnPrev.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrev.setPreferredSize(new java.awt.Dimension(97, 30));
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(HeaderPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1238, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(AddTeacherBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(GenarateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(AddTeacherBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(GenarateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        String keyword = searchField.getText().trim();
        searchTeacherFromDatabase(keyword);
    }//GEN-LAST:event_searchFieldKeyReleased

    private void searchTeacherFromDatabase(String keyword) {
        try {

            ResultSet rs = MySQL.execute("SELECT * FROM `lecturer` "
                    + "WHERE CONCAT(`fname`, ' ', `lname`) LIKE '%" + keyword + "%' "
                    + "OR `id` = '" + keyword + "'"
                    + "OR `fname` LIKE '%" + keyword + "%' "
                    + "OR `lname` LIKE '%" + keyword + "%' "
                    + "OR `nic` LIKE '%" + keyword + "%' "
                    + "OR `email` LIKE '%" + keyword + "%' "
                    + "OR `mobile` LIKE '%" + keyword + "%'");

            DefaultTableModel dtm = (DefaultTableModel) teacherDataTable.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {

                Vector<String> data = new Vector<>();
                data.add(rs.getString("id"));
                data.add(rs.getString("nic"));
                data.add(rs.getString("fname") + " " + rs.getString("lname"));
                data.add(rs.getString("email"));
                data.add(rs.getString("mobile"));
                data.add(rs.getString("status_id"));

                dtm.addRow(data);

            }

        } catch (SQLException e) {
            logger.severe("Failed to Search Teacher Data.: " + e.getMessage());

        }
    }

    private void EditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditBtnActionPerformed

        int selectedRow = teacherDataTable.getSelectedRow();

        searchField.setText("");

        if (selectedRow != -1) {
            String teacherId = teacherDataTable.getValueAt(selectedRow, 0).toString();

            EditTeacherDialog editTeacherDialog = new EditTeacherDialog(this, true, teacherId);
            editTeacherDialog.setLocationRelativeTo(this);
            editTeacherDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
            loadTeacherTableData();
        }
    }//GEN-LAST:event_EditBtnActionPerformed

    private void AddTeacherBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddTeacherBtnActionPerformed
        searchField.setText("");

        AddTeacherDialog addTeacherDialog = new AddTeacherDialog(null, true, this);
        addTeacherDialog.setLocationRelativeTo(this);
        addTeacherDialog.setVisible(true);
    }//GEN-LAST:event_AddTeacherBtnActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        searchField.setText("");

        if (currentPage < totalPages) {
            currentPage++;
            loadTeacherTableData();
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        searchField.setText("");

        if (currentPage > 1) {
            currentPage--;
            loadTeacherTableData();
        }
    }//GEN-LAST:event_btnPrevActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.tchk.component.PrimaryBtn AddTeacherBtn;
    private lk.tchk.component.RoundBtn EditBtn;
    private lk.tchk.component.SeconderyBtn GenarateBtn;
    private lk.tchk.component.RoundPanel HeaderPanel;
    private lk.tchk.component.RoundPanel MainPanel;
    private lk.tchk.component.PrimaryBtn btnNext;
    private lk.tchk.component.SeconderyBtn btnPrev;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane;
    private lk.tchk.component.inputField searchField;
    private lk.tchk.component.RoundPanel searchPanel;
    private javax.swing.JTable teacherDataTable;
    // End of variables declaration//GEN-END:variables

}
