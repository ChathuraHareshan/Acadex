package lk.tchk.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import lk.tchk.connection.MySQL;
import lk.tchk.dialog.AddSessionDialog;
import lk.tchk.dialog.EditSessionDialog;
import lk.tchk.gui.Home;

import java.util.logging.Logger;
import lk.tchk.logs.LMSLogger;

public class ScheduleSessionPanel extends javax.swing.JPanel {

    private static final Logger logger = LMSLogger.getLogger("session");

    private final Home home;

    private int currentPage = 1;
    private final int rowsPerPage = 20;
    private int totalRows = 0;
    private int totalPages = 0;

    public ScheduleSessionPanel(Home parent) {
        this.home = parent;
        initComponents();
        init();
        loadSessionTableData();
    }

    public void Refresh() {
        loadSessionTableData();
        searchField.setText("");
    }

    private void loadSessionTableData() {
        try {
            int offset = (currentPage - 1) * rowsPerPage;

            ResultSet countRs = MySQL.execute("SELECT COUNT(*) AS total FROM `session`");
            if (countRs.next()) {
                totalRows = countRs.getInt("total");
                totalPages = (int) Math.ceil((double) totalRows / rowsPerPage);
            }

            String query = "SELECT s.*, lhs.lecturer_email "
                    + "FROM `session` s "
                    + "LEFT JOIN `lecturer_has_subject` lhs ON s.subject = lhs.subject "
                    + "ORDER BY s.date DESC, s.start_time ASC, s.session_id ASC "
                    + "LIMIT " + rowsPerPage + " OFFSET " + offset;

            ResultSet rs = MySQL.execute(query);

            DefaultTableModel dtm = (DefaultTableModel) sesseionTable.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector<String> data = new Vector<>();

                data.add(rs.getString("session_id"));
                data.add(rs.getString("subject"));

                // Get lecturer email directly from the joined query
                String lecturerEmail = rs.getString("lecturer_email");
                data.add(lecturerEmail != null ? lecturerEmail : "N/A");

                data.add(rs.getString("date"));
                data.add(rs.getString("start_time"));
                data.add(rs.getString("end_time"));
                data.add(rs.getString("status"));

                dtm.addRow(data);
            }

            sesseionTable.getColumnModel().getColumn(6).setMinWidth(0);
            sesseionTable.getColumnModel().getColumn(6).setMaxWidth(0);
            sesseionTable.getColumnModel().getColumn(6).setWidth(0);

            btnPrev.setEnabled(currentPage > 1);
            btnNext.setEnabled(currentPage < totalPages);

        } catch (SQLException e) {
            logger.severe("Failed to Load Session Data.: " + e.getMessage());
        }
    }

    private void init() {

        searchField.putClientProperty("JTextField.placeholderText",
                "Search Lecturer from NIC or Email or Lecturer ID");

        HeaderPanel.setOpaque(false);

        searchPanel.setOpaque(false);
        searchPanel.setBackground(new Color(255, 255, 255, 20));

        sesseionTable.setGridColor(new Color(200, 200, 230));

        JTableHeader header = sesseionTable.getTableHeader();
        header.setFont(new Font("Verdana", Font.BOLD, 14));
        header.setBackground(new Color(102, 126, 234));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 40));

        sesseionTable.setSelectionBackground(new Color(102, 126, 234, 80));
        sesseionTable.setSelectionForeground(new Color(102, 126, 234));

        sesseionTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                java.awt.Component c = super.getTableCellRendererComponent(table,
                        value, isSelected, hasFocus, row, column);

                try {
                    Object cellValue = table.getValueAt(row, 6);
                    String statusId = (cellValue != null) ? cellValue.toString() : "";

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
                    logger.severe("Failed to Load Session Status.: " + e.getMessage());

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
        sesseionTable = new javax.swing.JTable();
        HeaderPanel = new lk.tchk.component.RoundPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        searchPanel = new lk.tchk.component.RoundPanel();
        searchField = new lk.tchk.component.inputField();
        EditBtn = new lk.tchk.component.RoundBtn();
        AddStudentBtn = new lk.tchk.component.PrimaryBtn();
        btnPrev = new lk.tchk.component.SeconderyBtn();
        btnNext = new lk.tchk.component.PrimaryBtn();

        setPreferredSize(new java.awt.Dimension(1250, 980));

        MainPanel.setBackground(new java.awt.Color(248, 250, 252));
        MainPanel.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N

        jScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setPreferredSize(new java.awt.Dimension(468, 500));

        sesseionTable.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        sesseionTable.setForeground(new java.awt.Color(51, 51, 51));
        sesseionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Session ID", "Subject", "Lecturer Name", "Date", "Start Time", "End Time", "status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sesseionTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sesseionTable.setPreferredSize(new java.awt.Dimension(375, 787));
        sesseionTable.setRowHeight(35);
        sesseionTable.setRowMargin(2);
        sesseionTable.setShowHorizontalLines(true);
        sesseionTable.setShowVerticalLines(true);
        sesseionTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane.setViewportView(sesseionTable);
        if (sesseionTable.getColumnModel().getColumnCount() > 0) {
            sesseionTable.getColumnModel().getColumn(0).setResizable(false);
            sesseionTable.getColumnModel().getColumn(1).setResizable(false);
            sesseionTable.getColumnModel().getColumn(2).setResizable(false);
            sesseionTable.getColumnModel().getColumn(3).setResizable(false);
            sesseionTable.getColumnModel().getColumn(4).setResizable(false);
            sesseionTable.getColumnModel().getColumn(5).setResizable(false);
            sesseionTable.getColumnModel().getColumn(6).setResizable(false);
        }

        HeaderPanel.setBackground(new java.awt.Color(0, 30, 108));

        jLabel6.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Welcome back, Admin");

        jLabel5.setFont(new java.awt.Font("Bell MT", 1, 28)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Scheduled Sessions");

        javax.swing.GroupLayout HeaderPanelLayout = new javax.swing.GroupLayout(HeaderPanel);
        HeaderPanel.setLayout(HeaderPanelLayout);
        HeaderPanelLayout.setHorizontalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        EditBtn.setText("Edit Session");
        EditBtn.setFocusPainted(false);
        EditBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditBtnActionPerformed(evt);
            }
        });

        AddStudentBtn.setText("Add Session");
        AddStudentBtn.setBorderPainted(true);
        AddStudentBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddStudentBtn.setPreferredSize(new java.awt.Dimension(145, 40));
        AddStudentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddStudentBtnActionPerformed(evt);
            }
        });

        btnPrev.setText("Previous");
        btnPrev.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setText("Next");
        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNext.setPreferredSize(new java.awt.Dimension(76, 30));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
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
                        .addGap(30, 30, 30)
                        .addComponent(AddStudentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(HeaderPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddStudentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPrev, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(9, 9, 9))
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

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        String keyword = searchField.getText().trim();
        searchStudentFromDatabase(keyword);
    }//GEN-LAST:event_searchFieldKeyReleased

    private void searchStudentFromDatabase(String keyword) {

        try {
            ResultSet rs = MySQL.execute(
                    "SELECT s.*, lhs.lecturer_email "
                    + "FROM `session` s "
                    + "INNER JOIN `lecturer_has_subject` lhs ON s.subject = lhs.subject "
                    + "WHERE s.`session_id` LIKE '%" + keyword + "%' "
                    + "OR s.`subject` LIKE '%" + keyword + "%' "
                    + "OR s.`date` LIKE '%" + keyword + "%' "
                    + "OR s.`start_time` LIKE '%" + keyword + "%' "
                    + "OR s.`end_time` LIKE '%" + keyword + "%' "
                    + "OR lhs.`lecturer_email` LIKE '%" + keyword + "%'"
            );

            DefaultTableModel dtm = (DefaultTableModel) sesseionTable.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector<String> data = new Vector<>();

                data.add(rs.getString("session_id"));
                data.add(rs.getString("subject"));
                data.add(rs.getString("lecturer_email")); // from INNER JOIN
                data.add(rs.getString("date"));
                data.add(rs.getString("start_time"));
                data.add(rs.getString("end_time"));
                data.add(rs.getString("status"));

                dtm.addRow(data);
            }

        } catch (SQLException e) {
            logger.severe("Failed to Search Session Data.: " + e.getMessage());
        }

    }


    private void EditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditBtnActionPerformed
        int selectedRow = sesseionTable.getSelectedRow();

        searchField.setText("");

        if (selectedRow != -1) {
            String sessionId = sesseionTable.getValueAt(selectedRow, 0).toString();

            EditSessionDialog editSessionDialog = new EditSessionDialog(this, true, sessionId);
            editSessionDialog.setLocationRelativeTo(this);
            editSessionDialog.setVisible(true);
        }


    }//GEN-LAST:event_EditBtnActionPerformed

    private void AddStudentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddStudentBtnActionPerformed

        searchField.setText("");

        AddSessionDialog addSessionDialog = new AddSessionDialog(null, true, this);
        addSessionDialog.setLocationRelativeTo(this);
        addSessionDialog.setVisible(true);

    }//GEN-LAST:event_AddStudentBtnActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        searchField.setText("");

        if (currentPage > 1) {
            currentPage--;
            loadSessionTableData();
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        searchField.setText("");

        if (currentPage < totalPages) {
            currentPage++;
            loadSessionTableData();
        }
    }//GEN-LAST:event_btnNextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.tchk.component.PrimaryBtn AddStudentBtn;
    private lk.tchk.component.RoundBtn EditBtn;
    private lk.tchk.component.RoundPanel HeaderPanel;
    private lk.tchk.component.RoundPanel MainPanel;
    private lk.tchk.component.PrimaryBtn btnNext;
    private lk.tchk.component.SeconderyBtn btnPrev;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane;
    private lk.tchk.component.inputField searchField;
    private lk.tchk.component.RoundPanel searchPanel;
    private javax.swing.JTable sesseionTable;
    // End of variables declaration//GEN-END:variables
}
