package lk.tchk.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import lk.tchk.connection.MySQL;
import lk.tchk.dialog.AddOfficerDialog;
import lk.tchk.dialog.EditOfficerDialog;
import lk.tchk.gui.Home;

import java.util.logging.Logger;
import lk.tchk.logs.LMSLogger;

public class OfficerPanel extends javax.swing.JPanel {

    private static final Logger logger = LMSLogger.getLogger("officer");

    private final Home home;

    private int currentPage = 1;
    private final int rowsPerPage = 20;
    private int totalRows = 0;
    private int totalPages = 0;

    public OfficerPanel(Home parent) {
        initComponents();
        init();
        loadOfficerTableData();
        this.home = parent;
    }

    public void loadOfficerTableData() {

        try {

            int offset = (currentPage - 1) * rowsPerPage;

            ResultSet countRs = MySQL.execute("SELECT COUNT(*) AS total FROM `officer`");
            if (countRs.next()) {
                totalRows = countRs.getInt("total");
                totalPages = (int) Math.ceil((double) totalRows / rowsPerPage);
            }

            ResultSet rs = MySQL.execute("SELECT * FROM `officer` LIMIT " + rowsPerPage + " OFFSET " + offset);

            DefaultTableModel dtm = (DefaultTableModel) OfficerDataTable.getModel();
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

            OfficerDataTable.getColumnModel().getColumn(5).setMinWidth(0);
            OfficerDataTable.getColumnModel().getColumn(5).setMaxWidth(0);
            OfficerDataTable.getColumnModel().getColumn(5).setWidth(0);

            BtnPrev.setEnabled(currentPage > 1);
            BtnNext.setEnabled(currentPage < totalPages);

        } catch (SQLException e) {
            logger.severe("Failed to Load Officer Data.: " + e.getMessage());

        }

    }

    private void init() {

        searchField.putClientProperty("JTextField.placeholderText",
                "Search Officer from NIC or Email or Officer ID");

        HeaderPanel.setOpaque(false);

        searchPanel.setOpaque(false);
        searchPanel.setBackground(new Color(255, 255, 255, 20));

        OfficerDataTable.setGridColor(new Color(200, 200, 230));

        JTableHeader header = OfficerDataTable.getTableHeader();
        header.setFont(new Font("Verdana", Font.BOLD, 14));
        header.setBackground(new Color(102, 126, 234));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 40));

        OfficerDataTable.setSelectionBackground(new Color(102, 126, 234, 80));
        OfficerDataTable.setSelectionForeground(new Color(102, 126, 234));

        OfficerDataTable.setDefaultRenderer(Object.class,
                new javax.swing.table.DefaultTableCellRenderer() {
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
                    logger.warning("Failed to load Officer Data." + e.getMessage());
                }

                return c;
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new lk.tchk.component.RoundPanel();
        HeaderPanel = new lk.tchk.component.RoundPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        searchPanel = new lk.tchk.component.RoundPanel();
        searchField = new lk.tchk.component.inputField();
        jScrollPane = new javax.swing.JScrollPane();
        OfficerDataTable = new javax.swing.JTable();
        EditBtn = new lk.tchk.component.RoundBtn();
        AddStudentBtn = new lk.tchk.component.PrimaryBtn();
        GenarateBtn = new lk.tchk.component.SeconderyBtn();
        BtnNext = new lk.tchk.component.PrimaryBtn();
        BtnPrev = new lk.tchk.component.SeconderyBtn();

        MainPanel.setBackground(new java.awt.Color(248, 250, 252));

        HeaderPanel.setBackground(new java.awt.Color(0, 30, 108));

        jLabel6.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Welcome back, Admin");

        jLabel5.setFont(new java.awt.Font("Bell MT", 1, 28)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Officer Management");

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

        jScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setPreferredSize(new java.awt.Dimension(468, 500));

        OfficerDataTable.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        OfficerDataTable.setForeground(new java.awt.Color(51, 51, 51));
        OfficerDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Officer ID", "National ID", "Name", "Email", "Mobile", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        OfficerDataTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        OfficerDataTable.setPreferredSize(new java.awt.Dimension(375, 787));
        OfficerDataTable.setRowHeight(35);
        OfficerDataTable.setRowMargin(2);
        OfficerDataTable.setShowHorizontalLines(true);
        OfficerDataTable.setShowVerticalLines(true);
        OfficerDataTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane.setViewportView(OfficerDataTable);

        EditBtn.setBackground(new java.awt.Color(16, 185, 129));
        EditBtn.setForeground(new java.awt.Color(255, 255, 255));
        EditBtn.setText("Edit Officer");
        EditBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditBtnActionPerformed(evt);
            }
        });

        AddStudentBtn.setText("Add Officer");
        AddStudentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddStudentBtnActionPerformed(evt);
            }
        });

        GenarateBtn.setText("Genarate Report");
        GenarateBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        BtnNext.setText("Next");
        BtnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNextActionPerformed(evt);
            }
        });

        BtnPrev.setText("Previous");
        BtnPrev.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnPrev.setPreferredSize(new java.awt.Dimension(97, 30));
        BtnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrevActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(BtnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(HeaderPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1238, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(AddStudentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(GenarateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddStudentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GenarateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

    private void printSelectedOfficerId() {
        int selectedRow = OfficerDataTable.getSelectedRow();

        if (selectedRow != -1) {
            Object studentId = OfficerDataTable.getValueAt(selectedRow, 0);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row first.");

        }
    }

    private void searchOfficerFromDatabase(String keyword) {
        try {

            ResultSet rs = MySQL.execute("SELECT * FROM `officer` "
                    + "WHERE CONCAT(`fname`, ' ', `lname`) LIKE '%" + keyword + "%' "
                    + "OR `id` = '" + keyword + "'"
                    + "OR `fname` LIKE '%" + keyword + "%' "
                    + "OR `lname` LIKE '%" + keyword + "%' "
                    + "OR `nic` LIKE '%" + keyword + "%' "
                    + "OR `email` LIKE '%" + keyword + "%' "
                    + "OR `mobile` LIKE '%" + keyword + "%'");

            DefaultTableModel dtm = (DefaultTableModel) OfficerDataTable.getModel();
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
            logger.warning("Failed to search Officer data." + e.getMessage());

        }
    }


    private void EditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditBtnActionPerformed

        int selectedRow = OfficerDataTable.getSelectedRow();

        searchField.setText("");

        if (selectedRow != -1) {
            String officerID = OfficerDataTable.getValueAt(selectedRow, 0).toString();

            EditOfficerDialog editOfficerDialog = new EditOfficerDialog(this, true, officerID);
            editOfficerDialog.setLocationRelativeTo(this);
            editOfficerDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
            loadOfficerTableData();
        }
    }//GEN-LAST:event_EditBtnActionPerformed

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        String keyword = searchField.getText().trim();
        searchOfficerFromDatabase(keyword);
    }//GEN-LAST:event_searchFieldKeyReleased

    private void AddStudentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddStudentBtnActionPerformed
        searchField.setText("");

        AddOfficerDialog addOfficerDialog = new AddOfficerDialog(null, true, this);
        addOfficerDialog.setLocationRelativeTo(this);
        addOfficerDialog.setVisible(true);
    }//GEN-LAST:event_AddStudentBtnActionPerformed

    private void BtnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNextActionPerformed
        searchField.setText("");

        if (currentPage < totalPages) {
            currentPage++;
            loadOfficerTableData();
        }
    }//GEN-LAST:event_BtnNextActionPerformed

    private void BtnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrevActionPerformed
        searchField.setText("");

        if (currentPage > 1) {
            currentPage--;
            loadOfficerTableData();
        }
    }//GEN-LAST:event_BtnPrevActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.tchk.component.PrimaryBtn AddStudentBtn;
    private lk.tchk.component.PrimaryBtn BtnNext;
    private lk.tchk.component.SeconderyBtn BtnPrev;
    private lk.tchk.component.RoundBtn EditBtn;
    private lk.tchk.component.SeconderyBtn GenarateBtn;
    private lk.tchk.component.RoundPanel HeaderPanel;
    private lk.tchk.component.RoundPanel MainPanel;
    private javax.swing.JTable OfficerDataTable;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane;
    private lk.tchk.component.inputField searchField;
    private lk.tchk.component.RoundPanel searchPanel;
    // End of variables declaration//GEN-END:variables

}
