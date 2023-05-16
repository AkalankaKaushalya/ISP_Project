/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isp_porject;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author anura
 */
public class Inventory_Managemant extends javax.swing.JFrame {

    /**
     * Creates new form Inventory_Managemant
     */
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String role;
    public Inventory_Managemant(String value) {
        initComponents();
        conn = DBConnect.connect();
        role=value;
        MIdcomboLoad();
        tableLode();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // Member Uid loade//
    private void MIdcomboLoad() {
        try {
            String sql = "SELECT `id` FROM `supplier`";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                jComboBox1.addItem(rs.getString("id"));
            }

        } catch (Exception e) {
            jComboBox1.setVisible(false);
        }
    }
    
     private void clear() {
         jTextField2.setText("");
         jComboBox1.setSelectedIndex(0);
         jTextArea1.setText("");
         jTextField3.setText("");
         jTextField4.setText("");
         jTextField5.setText("");
         searchBar.setText("");
         jComboBox2.setSelectedIndex(0);
    }
     
      public void generatePdfReport() {
        try {
    // Create a new PDF document
    com.itextpdf.text.Document document = new com.itextpdf.text.Document();
    PdfWriter.getInstance(document, new FileOutputStream("Inventory_report.pdf"));
    document.open();
    
    // Create a table with the appropriate number of columns
    PdfPTable table = new PdfPTable(8);
    
    // Set column widths
    table.setWidthPercentage(100);
    float[] columnWidths = {1, 2, 2, 2, 1, 3, 2, 2};
    table.setWidths(columnWidths);
    
    // Add table headers
    PdfPCell cell = new PdfPCell(new Phrase("Inventory Report"));
    cell.setBorder(Rectangle.BOX);
    cell.setColspan(8);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);
    table.addCell("ID");
    table.addCell("Iteam Name");
    table.addCell("Supller ID");
    table.addCell("Description");
    table.addCell("Quntity");
    table.addCell("Date");
    table.addCell("Price");
    table.addCell("Inventory Code");
    
    
    // Retrieve data from the database
    String sql = "SELECT `id`, `iname`, `sid`, `description`, `qut`, `date`, `price`, `icode` FROM `inventory`";
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    
    // Add data to the table
    while (rs.next()) {
        table.addCell(rs.getString("id"));
        table.addCell(rs.getString("iname"));
        table.addCell(rs.getString("sid"));
        table.addCell(rs.getString("description"));
        table.addCell(rs.getString("qut"));
        table.addCell(rs.getString("date"));
        table.addCell(rs.getString("price"));
        table.addCell(rs.getString("icode"));
    }
    
    // Add table to the document
    document.add(table);
    
    // Close the document
    document.close();
    
    JOptionPane.showMessageDialog(null, "Report generated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Error generating report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        searchBar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField3 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 3, 25)); // NOI18N
        jLabel1.setText("INVENTORY DETAILS MANAGEMANT SYSTEM ");

        jPanel2.setBackground(new java.awt.Color(255, 255, 51));

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("SEARCH ID :");

        jButton1.setForeground(new java.awt.Color(255, 153, 153));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-28.png"))); // NOI18N
        jButton1.setText("FIND");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        searchBar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(51, 204, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Inventory Name :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Supplier ID :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Inventory Description  :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Inventory Qty :");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel4.setBackground(new java.awt.Color(204, 102, 255));

        jButton2.setBackground(new java.awt.Color(255, 153, 153));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-remove-28.png"))); // NOI18N
        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 204, 51));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-sync-28.png"))); // NOI18N
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(102, 255, 102));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-28.png"))); // NOI18N
        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 153));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-broom-28.png"))); // NOI18N
        jButton7.setText("Clear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Supplier Id" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Inventory purchase date  :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Inventory per price  :");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Inventory Code", "1000", "1002", "1003", "1004", "1005" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Inventory Code :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField5)
                    .addComponent(jTextField4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Customer_ID", "Customer_Name", "Customer_Address", "Customer_Number", "Regis_Date"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jButton6.setText("Dashbord");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-health-graph-28.png"))); // NOI18N
        jButton5.setText("Report");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(259, 259, 259))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           String serarch = searchBar.getText();
            if(serarch.isEmpty())
            {
                JOptionPane.showMessageDialog(rootPane, "Enter Inventory Id And find");
            }
            else
            {
                try {
                String q = "SELECT * FROM `inventory` WHERE `id`=?";
                pst = conn.prepareStatement(q);
                pst.setString(1, serarch);
                rs = pst.executeQuery();
    
                if (rs.next()) {
                    String iname = rs.getString("iname");
                    String sid = rs.getString("sid");
                    String description = rs.getString("description");
                    String qut = rs.getString("qut");
                    String date = rs.getString("date");
                    String price = rs.getString("price");
                    String icode = rs.getString("icode");
                    jTextField2.setText(iname);
                    jComboBox1.setSelectedItem(sid);
                    jTextArea1.setText(description);
                    jTextField3.setText(qut);
                    jTextField4.setText(date);
                    jTextField5.setText(price);
                    jComboBox2.setSelectedItem(icode);
                } else {
                    JOptionPane.showMessageDialog(null, "This Id Not Inventory", "Error", JOptionPane.ERROR_MESSAGE);
                }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String serarch = searchBar.getText();
        if(serarch.isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Enter Inventory Id And find");
        }
        else
        {
            try {
                String sql = "DELETE FROM `inventory` WHERE `id` = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, serarch);
                pst.executeUpdate();
                ImageIcon icon = new ImageIcon("src/icon/ok.png");
                JOptionPane.showMessageDialog(null, "Inventory deleted successfully", "Successfully", JOptionPane.INFORMATION_MESSAGE, icon);
                tableLode();
                clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String serarch = searchBar.getText();
        String iname = jTextField2.getText();
        String sid = jComboBox1.getSelectedItem().toString();
        String description = jTextArea1.getText();
        String qut = jTextField3.getText();
        String date = jTextField4.getText();
        String price = jTextField5.getText();
        String icode = jComboBox2.getSelectedItem().toString();

        if(iname.isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Enter Name");
        }
        else if (sid.equals("Select Supplier Id"))
        {
            JOptionPane.showMessageDialog(rootPane, "Select Supplier ID");
        }
        else if (icode.equals("Select Inventory Code"))
        {
            JOptionPane.showMessageDialog(rootPane, "Select Inventory Code");
        }
        else if (description.isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Enter Descriptio");
        }
        else if (qut.isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Enter Quntity");
        }
        else if (date.isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Enter Date");
        }
        else if (price.isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Enter Price");
        }
        else
        {
            try {
                String sql = "UPDATE `inventory` SET `iname` = ?, `sid` = ?, `description` = ?, `qut` = ?, `date` = ?, `price` = ?,`icode` = ? WHERE `id` = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, iname);
                pst.setString(2, sid);
                pst.setString(3, description);
                pst.setString(4, qut);
                pst.setString(5, date);
                pst.setString(6, price);
                pst.setString(7, icode);
                pst.setString(8, serarch);// replace customerId with the actual customer ID value
                pst.executeUpdate();
                ImageIcon icon = new ImageIcon("src/icon/ok.png");
                JOptionPane.showMessageDialog(null, "Inventory Update successfully", "Successfully", JOptionPane.INFORMATION_MESSAGE, icon);
                tableLode();
                clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        String iname = jTextField2.getText();
        String sid = jComboBox1.getSelectedItem().toString();
        String description = jTextArea1.getText();
        String qut = jTextField3.getText();
        String date = jTextField4.getText();
        String price = jTextField5.getText();
        String icode = jComboBox2.getSelectedItem().toString();

        if(iname.isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Enter Name");
        }
        else if (description.isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Enter Descriptio");
        }
        else if (icode.equals("Select Inventory Code"))
        {
            JOptionPane.showMessageDialog(rootPane, "Select Inventory Code");
        }
        else if (description.equals("Select Supplier Id"))
        {
            JOptionPane.showMessageDialog(rootPane, "Select Supplier ID");
        }
        else if (qut.isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Enter Quntity");
        }
        else if (date.isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Enter Date");
        }
        else if (price.isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Enter Price");
        }

        else
        {
            try {
                String q = "INSERT INTO `inventory`(`iname`, `sid`, `icode`, `description`, `qut`, `date`, `price`) VALUES ('" + iname + "','" + sid + "','" + icode + "','" + description + "','" + qut + "','" + date + "','" + price + "')";
                pst = conn.prepareStatement(q);
                pst.execute();
                //JOptionPane.showMessageDialog(rootPane, "Member added successfully");
                ImageIcon icon = new ImageIcon("src/icon/ok.png");
                JOptionPane.showMessageDialog(null, "Inventory Add successfully", "Successfully", JOptionPane.INFORMATION_MESSAGE, icon);
                tableLode();
                clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(role.equals("manager"))
        {
            Manager_Dashbord maneger = new Manager_Dashbord();
            maneger.setVisible(true);
            this.dispose();
        }
        else
        {
            Staff_Dashbord staff = new Staff_Dashbord();
            staff.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        clear();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
         String id = jComboBox1.getSelectedItem().toString();
         try {
            String sql = "SELECT `sname` FROM `supplier` WHERE `id`=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
    
            if (rs.next()) {
                String name = rs.getString("sname");
                jTextField2.setText(name);
            } else {
                jTextField2.setText("");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            //JOptionPane.showConfirmDialog(rootPane, ex);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        generatePdfReport();
    }//GEN-LAST:event_jButton5ActionPerformed

    public void tableLode() {
        try {
            String sql = "SELECT `id` as 'Inventory_Id', `icode` as 'Inventory_Code', `iname` as 'Inventory_Name', `sid` as 'Supplier_ID', `description` as 'Inventory_Description', `qut` as 'Inventory_Quutity', `date` as 'Inventory_Date', `price` as 'Inventory_Price' FROM `inventory`";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            clear();

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inventory_Managemant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventory_Managemant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventory_Managemant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventory_Managemant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String value = ""; // Example value
                Inventory_Managemant imm = new Inventory_Managemant(value);
                imm.setVisible(true);
                //new Inventory_Managemant().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField searchBar;
    // End of variables declaration//GEN-END:variables
}
