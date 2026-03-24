package lk.tchk.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import lk.tchk.connection.MySQL;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import lk.tchk.logs.LMSLogger;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ReportPanel extends javax.swing.JPanel {

    private static final Logger logger = LMSLogger.getLogger("report");

    public ReportPanel() {
        initComponents();
        init();
    }

    private void init() {
        ReportDataTable.setGridColor(new Color(200, 200, 230));

        JTableHeader header = ReportDataTable.getTableHeader();
        header.setFont(new Font("Verdana", Font.BOLD, 14));
        header.setBackground(new Color(102, 126, 234));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 40));

        ReportDataTable.setSelectionBackground(new Color(102, 126, 234, 80));
        ReportDataTable.setSelectionForeground(new Color(102, 126, 234));

    }

    private String getSelectedReportType() {
        if (StudentTypeBtn.isSelected()) {
            return "student";
        }
        if (TeacherTypeBtn.isSelected()) {
            return "teacher";
        }
        if (OfficerTypeBtn.isSelected()) {
            return "officer";
        }
        return null;
    }

    private String getStatusFilter() {
        if (ActiveCheckBox.isSelected() && !InactiveCheckbox.isSelected()) {
            return "1";
        } else if (!ActiveCheckBox.isSelected() && InactiveCheckbox.isSelected()) {
            return "2";
        } else if (ActiveCheckBox.isSelected() && InactiveCheckbox.isSelected()) {
            return "both";
        }
        return null;
    }

    private String getGenderFilter() {
        if (MaleCheckbox.isSelected() && !FemaleCheckbox.isSelected()) {
            return "male";
        } else if (!MaleCheckbox.isSelected() && FemaleCheckbox.isSelected()) {
            return "female";
        } else if (MaleCheckbox.isSelected() && FemaleCheckbox.isSelected()) {
            return "both";
        }
        return null;
    }

    private String buildQuery() {
        String table = getSelectedReportType();
        if (table == null) {
            return null;
        }

        StringBuilder query = new StringBuilder("SELECT * FROM `" + table + "` WHERE 1=1");

        String status = getStatusFilter();
        if (status != null) {
            if (status.equals("both")) {
                query.append(" AND (status_id = '1' OR status_id = '2')");
            } else if (status == "1") {
                query.append(" AND (status_id = '1' )");
            } else if (status == "2") {
                query.append(" AND (status_id = '2' )");

            }
        }

        String gender = getGenderFilter();
        if (gender != null) {
            if (gender.equals("both")) {
                query.append(" AND (gender_id = '1' OR gender_id = '2')");
            } else if (gender == "male") {
                query.append(" AND (gender_id = '1' )");
            } else if (gender == "female") {
                query.append(" AND (gender_id = '2' )");

            }
        }

        return query.toString();
    }

    private void generateReport() {
        String query = buildQuery();
        if (query == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a report type first");
            return;
        }

        try {
            ResultSet rs = MySQL.execute(query);

            DefaultTableModel dtm = (DefaultTableModel) ReportDataTable.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("id"));
                row.add(rs.getString("fname") + " " + rs.getString("lname"));
                row.add(rs.getString("email"));
                row.add(rs.getString("dob"));
                row.add(rs.getString("mobile"));
                row.add(rs.getString("nic"));

                String statusId = rs.getString("status_id");
                String statusText;
                if (statusId.equals("1")) {
                    statusText = "Active";
                } else if (statusId.equals("2")) {
                    statusText = "Inactive";
                } else {
                    statusText = "Unknown";
                }
                row.add(statusText);

                String genderId = rs.getString("gender_id");
                String genderText;
                if (genderId.equals("1")) {
                    genderText = "Male";
                } else if (genderId.equals("2")) {
                    genderText = "Female";
                } else {
                    genderText = "Other";
                }
                row.add(genderText);

                dtm.addRow(row);
            }

            JOptionPane.showMessageDialog(this,
                    "Report generated successfully with " + " " + " records\n\n"
                    + getFilterDescription());

        } catch (SQLException e) {
            logger.severe("Failed to Genarate Report Data.: " + e.getMessage());

            JOptionPane.showMessageDialog(this,
                    "Failed to generate report: " + e.getMessage());
        }
    }

    private String getFilterDescription() {
        StringBuilder desc = new StringBuilder("Current Filters:\n");

        String reportType = getSelectedReportType();
        desc.append("Report Type: ").append(reportType != null ? reportType.toUpperCase() : "Not selected").append("\n");

        String status = getStatusFilter();
        if (status != null) {
            if (status.equals("both")) {
                desc.append("Status: Active & Inactive\n");
                ReportStatusTxt.setText("Status : Active & Inactive\n");
            } else {
                desc.append("Status: ").append(status.equals("1") ? "Active" : "Inactive").append("\n");
                ReportStatusTxt.setText("Status : " + (status.equals("1") ? "Active" : "Inactive"));

            }
        } else {
            desc.append("Status: All\n");
            ReportStatusTxt.setText("Status : All ");
        }

        String gender = getGenderFilter();
        if (gender != null) {
            if (gender.equals("both")) {
                desc.append("Gender: Male & Female\n");
                ReportGenderTxt.setText("Gender : Male & Female");
            } else {
                desc.append("Gender: ").append(gender).append("\n");
                ReportGenderTxt.setText("Gender : " + gender);

            }
        } else {
            desc.append("Gender: All\n");
            ReportGenderTxt.setText("Gender : All");
        }

        return desc.toString();
    }

    private void reset() {
        StudentTypeBtn.setSelected(false);
        TeacherTypeBtn.setSelected(false);
        OfficerTypeBtn.setSelected(false);

        ActiveCheckBox.setSelected(false);
        InactiveCheckbox.setSelected(false);
        MaleCheckbox.setSelected(false);
        FemaleCheckbox.setSelected(false);

        DefaultTableModel dtm = (DefaultTableModel) ReportDataTable.getModel();
        dtm.setRowCount(0);

        getFilterDescription();

    }

    private void updateTableColumns(String type) {
        ReportTypeTxt.setText(capitalize(type));
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reportTypeGroup = new javax.swing.ButtonGroup();
        MainPanel = new lk.tchk.component.RoundPanel();
        HeadPanel = new lk.tchk.component.RoundPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        reporttype = new javax.swing.JLabel();
        roundPanel2 = new lk.tchk.component.RoundPanel();
        StudentTypeBtn = new javax.swing.JRadioButton();
        roundPanel3 = new lk.tchk.component.RoundPanel();
        TeacherTypeBtn = new javax.swing.JRadioButton();
        roundPanel4 = new lk.tchk.component.RoundPanel();
        OfficerTypeBtn = new javax.swing.JRadioButton();
        roundPanel5 = new lk.tchk.component.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        FemaleCheckbox = new javax.swing.JCheckBox();
        InactiveCheckbox = new javax.swing.JCheckBox();
        ActiveCheckBox = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        MaleCheckbox = new javax.swing.JCheckBox();
        roundPanel6 = new lk.tchk.component.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        ReportTypeTxt = new javax.swing.JLabel();
        ReportStatusTxt = new javax.swing.JLabel();
        ReportGenderTxt = new javax.swing.JLabel();
        roundPanel7 = new lk.tchk.component.RoundPanel();
        jLabel11 = new javax.swing.JLabel();
        exportToExcelBtn = new lk.tchk.component.RoundBtn();
        exportToPdfBtn = new lk.tchk.component.RoundBtn();
        exportToHtmlBtn = new lk.tchk.component.RoundBtn();
        exportToWordBtn = new lk.tchk.component.RoundBtn();
        jScrollPane1 = new javax.swing.JScrollPane();
        ReportDataTable = new javax.swing.JTable();
        btnGenerateReport = new lk.tchk.component.PrimaryBtn();
        btnViewReport = new lk.tchk.component.SeconderyBtn();
        roundBtn3 = new lk.tchk.component.RoundBtn();
        jSeparator1 = new javax.swing.JSeparator();

        HeadPanel.setBackground(new java.awt.Color(0, 30, 108));

        jLabel6.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Welcome back, Admin");

        jLabel5.setFont(new java.awt.Font("Bell MT", 1, 28)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(" Report Generator");

        javax.swing.GroupLayout HeadPanelLayout = new javax.swing.GroupLayout(HeadPanel);
        HeadPanel.setLayout(HeadPanelLayout);
        HeadPanelLayout.setHorizontalGroup(
            HeadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HeadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        HeadPanelLayout.setVerticalGroup(
            HeadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeadPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        reporttype.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        reporttype.setText("Report Type");

        roundPanel2.setBackground(new java.awt.Color(204, 204, 255));

        reportTypeGroup.add(StudentTypeBtn);
        StudentTypeBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        StudentTypeBtn.setSelected(true);
        StudentTypeBtn.setText("Students");
        StudentTypeBtn.setPreferredSize(new java.awt.Dimension(120, 40));
        StudentTypeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudentTypeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(StudentTypeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(StudentTypeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel3.setBackground(new java.awt.Color(204, 204, 255));

        reportTypeGroup.add(TeacherTypeBtn);
        TeacherTypeBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TeacherTypeBtn.setText("Lecturer");
        TeacherTypeBtn.setPreferredSize(new java.awt.Dimension(120, 40));
        TeacherTypeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TeacherTypeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(TeacherTypeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TeacherTypeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel4.setBackground(new java.awt.Color(204, 204, 255));

        reportTypeGroup.add(OfficerTypeBtn);
        OfficerTypeBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        OfficerTypeBtn.setText("Officers");
        OfficerTypeBtn.setPreferredSize(new java.awt.Dimension(120, 40));
        OfficerTypeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OfficerTypeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(OfficerTypeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(OfficerTypeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel5.setBackground(new java.awt.Color(201, 232, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Filters");

        FemaleCheckbox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        FemaleCheckbox.setSelected(true);
        FemaleCheckbox.setText("Female");
        FemaleCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FemaleCheckboxActionPerformed(evt);
            }
        });

        InactiveCheckbox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        InactiveCheckbox.setText("Inactive");
        InactiveCheckbox.setContentAreaFilled(false);
        InactiveCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InactiveCheckboxActionPerformed(evt);
            }
        });

        ActiveCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ActiveCheckBox.setSelected(true);
        ActiveCheckBox.setText("Active");
        ActiveCheckBox.setContentAreaFilled(false);
        ActiveCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActiveCheckBoxActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Status");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("Gender");

        MaleCheckbox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MaleCheckbox.setSelected(true);
        MaleCheckbox.setText("Male");
        MaleCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaleCheckboxActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setText("Current Filter Setting");

        ReportTypeTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ReportTypeTxt.setForeground(new java.awt.Color(30, 64, 175));
        ReportTypeTxt.setText("Student");

        ReportStatusTxt.setText("Active");

        ReportGenderTxt.setText("Male & Female");

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ReportTypeTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ReportStatusTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ReportGenderTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ReportTypeTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ReportStatusTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ReportGenderTxt)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setText("Export Options");

        exportToExcelBtn.setBackground(new java.awt.Color(0, 153, 102));
        exportToExcelBtn.setForeground(new java.awt.Color(255, 255, 255));
        exportToExcelBtn.setText("Excel");
        exportToExcelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportToExcelBtnActionPerformed(evt);
            }
        });

        exportToPdfBtn.setBackground(new java.awt.Color(102, 102, 255));
        exportToPdfBtn.setForeground(new java.awt.Color(255, 255, 255));
        exportToPdfBtn.setText("PDF");
        exportToPdfBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportToPdfBtnActionPerformed(evt);
            }
        });

        exportToHtmlBtn.setBackground(new java.awt.Color(255, 204, 0));
        exportToHtmlBtn.setForeground(new java.awt.Color(255, 255, 255));
        exportToHtmlBtn.setText("HTML");
        exportToHtmlBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportToHtmlBtnActionPerformed(evt);
            }
        });

        exportToWordBtn.setBackground(new java.awt.Color(204, 0, 102));
        exportToWordBtn.setForeground(new java.awt.Color(255, 255, 255));
        exportToWordBtn.setText("Docs");
        exportToWordBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportToWordBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel7Layout = new javax.swing.GroupLayout(roundPanel7);
        roundPanel7.setLayout(roundPanel7Layout);
        roundPanel7Layout.setHorizontalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addGroup(roundPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel7Layout.createSequentialGroup()
                                .addComponent(exportToPdfBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(exportToExcelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundPanel7Layout.createSequentialGroup()
                                .addComponent(exportToWordBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(exportToHtmlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)))
                .addContainerGap())
        );
        roundPanel7Layout.setVerticalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportToPdfBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportToExcelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportToWordBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportToHtmlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(roundPanel5Layout.createSequentialGroup()
                                        .addComponent(MaleCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(FemaleCheckbox))
                                    .addGroup(roundPanel5Layout.createSequentialGroup()
                                        .addComponent(ActiveCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(InactiveCheckbox))))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addComponent(roundPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ActiveCheckBox)
                    .addComponent(InactiveCheckbox))
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaleCheckbox)
                    .addComponent(FemaleCheckbox))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        ReportDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email Address", "Date of Birth", "Mobile Number", "NIC Numbner", "Status", "Gender"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ReportDataTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(ReportDataTable);

        btnGenerateReport.setText("Genarate Report");
        btnGenerateReport.setPreferredSize(new java.awt.Dimension(151, 40));
        btnGenerateReport.setRequestFocusEnabled(false);
        btnGenerateReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateReportActionPerformed(evt);
            }
        });

        btnViewReport.setText("View Report");
        btnViewReport.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnViewReport.setPreferredSize(new java.awt.Dimension(97, 40));
        btnViewReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewReportActionPerformed(evt);
            }
        });

        roundBtn3.setBackground(new java.awt.Color(204, 204, 204));
        roundBtn3.setForeground(new java.awt.Color(51, 51, 51));
        roundBtn3.setText("Reset");
        roundBtn3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        roundBtn3.setPreferredSize(new java.awt.Dimension(90, 40));
        roundBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundBtn3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HeadPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(reporttype, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(btnGenerateReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnViewReport, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(roundBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(roundPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HeadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reporttype, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerateReport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnViewReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void StudentTypeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentTypeBtnActionPerformed
        updateTableColumns("student");
    }//GEN-LAST:event_StudentTypeBtnActionPerformed

    private void TeacherTypeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TeacherTypeBtnActionPerformed
        updateTableColumns("teacher");
    }//GEN-LAST:event_TeacherTypeBtnActionPerformed

    private void OfficerTypeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OfficerTypeBtnActionPerformed
        updateTableColumns("officer");
    }//GEN-LAST:event_OfficerTypeBtnActionPerformed

    private void ActiveCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActiveCheckBoxActionPerformed
        getFilterDescription();
        generateReport();
    }//GEN-LAST:event_ActiveCheckBoxActionPerformed

    private void InactiveCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InactiveCheckboxActionPerformed
        getFilterDescription();
        generateReport();
    }//GEN-LAST:event_InactiveCheckboxActionPerformed

    private void MaleCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaleCheckboxActionPerformed
        getFilterDescription();
        generateReport();
    }//GEN-LAST:event_MaleCheckboxActionPerformed

    private void FemaleCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FemaleCheckboxActionPerformed
        getFilterDescription();
        generateReport();
    }//GEN-LAST:event_FemaleCheckboxActionPerformed

    private void btnGenerateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateReportActionPerformed
        generateReport();
    }//GEN-LAST:event_btnGenerateReportActionPerformed

    private void btnViewReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewReportActionPerformed

        if (ReportDataTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No data available to generate the report!",
                    "Empty Table", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            InputStream filePath = getClass().getClassLoader().
                    getResourceAsStream("lk/tchk/report/AcadexFilter.jasper");
            HashMap<String, Object> parameters = new HashMap<>();

            String reportType = getSelectedReportType();
            parameters.put("REPORT_TYPE", reportType != null ? capitalize(reportType) : "Not Selected");

            String status = getStatusFilter();
            String statusText;
            if (status == null) {
                statusText = "All";
            } else if (status.equals("both")) {
                statusText = "Active & Inactive";
            } else {
                statusText = status.equals("1") ? "Active" : "Inactive";
            }
            parameters.put("STATUS_FILTER", statusText);

            String gender = getGenderFilter();
            String genderText;
            if (gender == null) {
                genderText = "All";
            } else if (gender.equals("both")) {
                genderText = "Male & Female";
            } else {
                genderText = capitalize(gender);
            }
            parameters.put("GENDER_FILTER", genderText);

            JRTableModelDataSource jRTableModelDataSource = new JRTableModelDataSource(ReportDataTable.getModel());

            JasperPrint fileReport = JasperFillManager.fillReport(filePath, parameters, jRTableModelDataSource);
            JasperViewer.viewReport(fileReport, false);

        } catch (JRException e) {
            logger.severe("Failed to View Report Data.: " + e.getMessage());

            JOptionPane.showMessageDialog(this, "Error Viewing report: " + e.getMessage(), "Report Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnViewReportActionPerformed

    private void roundBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundBtn3ActionPerformed
        reset();
    }//GEN-LAST:event_roundBtn3ActionPerformed

    private void exportToPdfBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportToPdfBtnActionPerformed
        if (ReportDataTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "No data available to export!",
                    "Empty Table",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Report as PDF");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF Files", "pdf"));

            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File saveFile = fileChooser.getSelectedFile();
            if (!saveFile.getAbsolutePath().toLowerCase().endsWith(".pdf")) {
                saveFile = new File(saveFile.getAbsolutePath() + ".pdf");
            }

            HashMap<String, Object> parameters = new HashMap<>();
            String reportType = getSelectedReportType();
            parameters.put("REPORT_TYPE", reportType != null ? capitalize(reportType) : "Not Selected");

            String status = getStatusFilter();
            String statusText;
            if (status == null) {
                statusText = "All";
            } else if (status.equals("both")) {
                statusText = "Active & Inactive";
            } else {
                statusText = status.equals("1") ? "Active" : "Inactive";
            }
            parameters.put("STATUS_FILTER", statusText);

            String gender = getGenderFilter();
            String genderText;
            if (gender == null) {
                genderText = "All";
            } else if (gender.equals("both")) {
                genderText = "Male & Female";
            } else {
                genderText = capitalize(gender);
            }
            parameters.put("GENDER_FILTER", genderText);

            InputStream filePath = getClass().getClassLoader()
                    .getResourceAsStream("lk/tchk/report/AcadexFilter.jasper");

            JRTableModelDataSource jRTableModelDataSource = new JRTableModelDataSource(ReportDataTable.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(filePath, parameters, jRTableModelDataSource);

            JasperExportManager.exportReportToPdfFile(jasperPrint, saveFile.getAbsolutePath());

            JOptionPane.showMessageDialog(this,
                    "Report exported successfully to:\n" + saveFile.getAbsolutePath(),
                    "Export Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | JRException e) {
            logger.severe("Failed to Export Report: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error exporting report: " + e.getMessage(),
                    "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_exportToPdfBtnActionPerformed

    private void exportToExcelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportToExcelBtnActionPerformed
        if (ReportDataTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "No data available to export!",
                    "Empty Table",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Report as Excel");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel Files", "xlsx"));

            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File saveFile = fileChooser.getSelectedFile();
            if (!saveFile.getAbsolutePath().toLowerCase().endsWith(".xlsx")) {
                saveFile = new File(saveFile.getAbsolutePath() + ".xlsx");
            }

            HashMap<String, Object> parameters = new HashMap<>();
            String reportType = getSelectedReportType();
            parameters.put("REPORT_TYPE", reportType != null ? capitalize(reportType) : "Not Selected");

            String status = getStatusFilter();
            String statusText;
            if (status == null) {
                statusText = "All";
            } else if (status.equals("both")) {
                statusText = "Active & Inactive";
            } else {
                statusText = status.equals("1") ? "Active" : "Inactive";
            }
            parameters.put("STATUS_FILTER", statusText);

            String gender = getGenderFilter();
            String genderText;
            if (gender == null) {
                genderText = "All";
            } else if (gender.equals("both")) {
                genderText = "Male & Female";
            } else {
                genderText = capitalize(gender);
            }
            parameters.put("GENDER_FILTER", genderText);

            InputStream filePath = getClass().getClassLoader()
                    .getResourceAsStream("lk/tchk/report/AcadexFilter.jasper");

            JRTableModelDataSource jRTableModelDataSource = new JRTableModelDataSource(ReportDataTable.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(filePath, parameters, jRTableModelDataSource);

            // Excel Exporter
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(saveFile));
            exporter.exportReport();

            JOptionPane.showMessageDialog(this,
                    "Report exported successfully to:\n" + saveFile.getAbsolutePath(),
                    "Export Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | JRException e) {
            logger.severe("Failed to Export Report: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error exporting report: " + e.getMessage(),
                    "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_exportToExcelBtnActionPerformed

    private void exportToHtmlBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportToHtmlBtnActionPerformed
        if (ReportDataTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "No data available to export!",
                    "Empty Table",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Report as HTML");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("HTML Files", "html"));

            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File saveFile = fileChooser.getSelectedFile();
            if (!saveFile.getAbsolutePath().toLowerCase().endsWith(".html")) {
                saveFile = new File(saveFile.getAbsolutePath() + ".html");
            }

            // Prepare report parameters
            HashMap<String, Object> parameters = new HashMap<>();
            String reportType = getSelectedReportType();
            parameters.put("REPORT_TYPE", reportType != null ? capitalize(reportType) : "Not Selected");

            String status = getStatusFilter();
            String statusText;
            if (status == null) {
                statusText = "All";
            } else if (status.equals("both")) {
                statusText = "Active & Inactive";
            } else {
                statusText = status.equals("1") ? "Active" : "Inactive";
            }
            parameters.put("STATUS_FILTER", statusText);

            String gender = getGenderFilter();
            String genderText;
            if (gender == null) {
                genderText = "All";
            } else if (gender.equals("both")) {
                genderText = "Male & Female";
            } else {
                genderText = capitalize(gender);
            }
            parameters.put("GENDER_FILTER", genderText);

            // Load Jasper file
            InputStream filePath = getClass().getClassLoader()
                    .getResourceAsStream("lk/tchk/report/AcadexFilter.jasper");

            JRTableModelDataSource jRTableModelDataSource = new JRTableModelDataSource(ReportDataTable.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(filePath, parameters, jRTableModelDataSource);

            // HTML Exporter
            HtmlExporter exporter = new HtmlExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleHtmlExporterOutput(saveFile));
            exporter.exportReport();

            JOptionPane.showMessageDialog(this,
                    "Report exported successfully to:\n" + saveFile.getAbsolutePath(),
                    "Export Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | JRException e) {
            logger.severe("Failed to Export Report: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error exporting report: " + e.getMessage(),
                    "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_exportToHtmlBtnActionPerformed

    private void exportToWordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportToWordBtnActionPerformed
        if (ReportDataTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "No data available to export!",
                    "Empty Table",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Report as Word");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Word Files", "docx"));

            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File saveFile = fileChooser.getSelectedFile();
            if (!saveFile.getAbsolutePath().toLowerCase().endsWith(".docx")) {
                saveFile = new File(saveFile.getAbsolutePath() + ".docx");
            }

            // Prepare report parameters
            HashMap<String, Object> parameters = new HashMap<>();
            String reportType = getSelectedReportType();
            parameters.put("REPORT_TYPE", reportType != null ? capitalize(reportType) : "Not Selected");

            String status = getStatusFilter();
            String statusText;
            if (status == null) {
                statusText = "All";
            } else if (status.equals("both")) {
                statusText = "Active & Inactive";
            } else {
                statusText = status.equals("1") ? "Active" : "Inactive";
            }
            parameters.put("STATUS_FILTER", statusText);

            String gender = getGenderFilter();
            String genderText;
            if (gender == null) {
                genderText = "All";
            } else if (gender.equals("both")) {
                genderText = "Male & Female";
            } else {
                genderText = capitalize(gender);
            }
            parameters.put("GENDER_FILTER", genderText);

            // Load Jasper file
            InputStream filePath = getClass().getClassLoader()
                    .getResourceAsStream("lk/tchk/report/AcadexFilter.jasper");

            JRTableModelDataSource jRTableModelDataSource = new JRTableModelDataSource(ReportDataTable.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(filePath, parameters, jRTableModelDataSource);

            // Word Exporter
            JRDocxExporter exporter = new JRDocxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(saveFile));
            exporter.exportReport();

            JOptionPane.showMessageDialog(this,
                    "Report exported successfully to:\n" + saveFile.getAbsolutePath(),
                    "Export Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | JRException e) {
            logger.severe("Failed to Export Report: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error exporting report: " + e.getMessage(),
                    "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_exportToWordBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ActiveCheckBox;
    private javax.swing.JCheckBox FemaleCheckbox;
    private lk.tchk.component.RoundPanel HeadPanel;
    private javax.swing.JCheckBox InactiveCheckbox;
    private lk.tchk.component.RoundPanel MainPanel;
    private javax.swing.JCheckBox MaleCheckbox;
    private javax.swing.JRadioButton OfficerTypeBtn;
    private javax.swing.JTable ReportDataTable;
    private javax.swing.JLabel ReportGenderTxt;
    private javax.swing.JLabel ReportStatusTxt;
    private javax.swing.JLabel ReportTypeTxt;
    private javax.swing.JRadioButton StudentTypeBtn;
    private javax.swing.JRadioButton TeacherTypeBtn;
    private lk.tchk.component.PrimaryBtn btnGenerateReport;
    private lk.tchk.component.SeconderyBtn btnViewReport;
    private lk.tchk.component.RoundBtn exportToExcelBtn;
    private lk.tchk.component.RoundBtn exportToHtmlBtn;
    private lk.tchk.component.RoundBtn exportToPdfBtn;
    private lk.tchk.component.RoundBtn exportToWordBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.ButtonGroup reportTypeGroup;
    private javax.swing.JLabel reporttype;
    private lk.tchk.component.RoundBtn roundBtn3;
    private lk.tchk.component.RoundPanel roundPanel2;
    private lk.tchk.component.RoundPanel roundPanel3;
    private lk.tchk.component.RoundPanel roundPanel4;
    private lk.tchk.component.RoundPanel roundPanel5;
    private lk.tchk.component.RoundPanel roundPanel6;
    private lk.tchk.component.RoundPanel roundPanel7;
    // End of variables declaration//GEN-END:variables

}
