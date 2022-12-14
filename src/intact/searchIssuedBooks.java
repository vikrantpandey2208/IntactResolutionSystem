/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intact;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vikrant Pandey
 */
public class searchIssuedBooks extends javax.swing.JDialog {
    Connection conn;
     adddata add= new adddata();
     int EventCounter =0
             ;
    /**
     * Creates new form searchIssuedBooks
     */
    public searchIssuedBooks(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Image icon=  Toolkit.getDefaultToolkit().getImage(path.icon);
       setIconImage(icon);
        jTextField1.disable();
        jLabel1.setVisible(false);
        jButton4.setVisible(false);
        viewIssuedBook();
    }
    
     public void viewIssuedBook(){
    try{
    conn= add.getconnection();
    Statement st= conn.createStatement();
    String sql= "select  b.book_id as b_id, b.name as b_name,b.writer as writer, s.student_id as s_id, s.name as s_name, s.class, iss.issue_date from issue_book iss inner join books b on b.book_id= iss.book_id inner join students s on s.student_id= iss.student_id;";
    ResultSet rs;
    
    rs= st.executeQuery(sql);
    
    ResultSetMetaData rsmd= rs.getMetaData();
    int c= rsmd.getColumnCount();
    int serial=1;
    DefaultTableModel dm= (DefaultTableModel)jTable2.getModel();
    dm.setRowCount(0);
     
    {  DefaultTableCellRenderer center_render= new DefaultTableCellRenderer();   
  center_render.setHorizontalAlignment(SwingConstants.LEFT);
  jTable2.setDefaultRenderer(String.class, center_render);
   jTable2.setDefaultRenderer(Integer.class, center_render);
    }
    
    while(rs.next()){
      
         SimpleDateFormat df= new SimpleDateFormat("dd-MMM-yyyy");
        Vector v2= new Vector();
        for(int i=0; i<=c; i++){
            v2.add(serial);
             v2.add(rs.getString("b_id"));
            v2.add(rs.getString("b_name"));
             v2.add(rs.getString("writer"));
            v2.add(rs.getString("s_id"));
            v2.add(rs.getString("s_name"));
            v2.add(rs.getString("class"));            
            v2.add(df.format(rs.getDate("issue_date")));
       
        }
        dm.addRow(v2);
        serial++;
    }
    
    conn.close();
    }catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
    }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel14.setFont(new java.awt.Font("Century Schoolbook", 3, 36)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("Search Issued Book");

        jTextField1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTextField1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Sitka Banner", 1, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 51, 51));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "book_id", "Book Name", "writer", "student_id", "Student Name", "class", "issue_date" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Serif", 3, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(102, 0, 0));
        jButton3.setText("Export to excel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Serif", 3, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(102, 0, 0));
        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Serif", 3, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(102, 0, 0));
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable2.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        jTable2.setForeground(new java.awt.Color(0, 0, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Serial", "Book Id", "Book Name", "Book Writer", "Student Id", "Student Name", "Class", "Issue Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setGridColor(new java.awt.Color(0, 255, 0));
        jTable2.setShowGrid(true);
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setText("Date Format- YYYY-MM-DD");

        jButton4.setFont(new java.awt.Font("Century Schoolbook", 3, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 255));
        jButton4.setText("Search by Date");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jButton2)
                        .addGap(27, 27, 27)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 381, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(81, 81, 81)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                    .addGap(81, 81, 81)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTextField1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1AncestorAdded

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        String search_key= jTextField1.getText();

        String search_column = (String) jComboBox1.getSelectedItem();
        String search_table= "issue_book";
         int index= jComboBox1.getSelectedIndex();
   if(index!=7 ){      

        try{
            power obb = new power();
            
            ResultSet rs= obb.SearchSpecialBook(index, search_key,search_column,search_table);
            
            DefaultTableModel dm= (DefaultTableModel)jTable2.getModel();
            dm.setRowCount(0);            
             int serial=1;
             SimpleDateFormat df= new SimpleDateFormat("dd-MMM-yyyy");
            
             
             {DefaultTableCellRenderer center_render= new DefaultTableCellRenderer();
            center_render.setHorizontalAlignment(SwingConstants.LEFT);
            jTable2.setDefaultRenderer(String.class, center_render);
            jTable2.setDefaultRenderer(Integer.class, center_render);
             }

            while(rs.next()){
                Vector v2= new Vector();
                for(int i=0; i<=7; i++){
                    v2.add(serial);
                    v2.add(rs.getString("b_id"));
                    v2.add(rs.getString("b_name"));
                    v2.add(rs.getString("writer"));
                    v2.add(rs.getString("s_id"));
                    v2.add(rs.getString("s_name"));
                    v2.add(rs.getString("class"));            
                    v2.add(df.format(rs.getDate("issue_date")));
                }
                dm.addRow(v2);
                serial++;
            }
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"problem in event "+e);
        }
   }
  
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        String search_column = (String) jComboBox1.getSelectedItem();
        if(search_column.equals("All")){
            viewIssuedBook();
            jTextField1.disable();
            jLabel1.hide();
            jButton4.hide();

        }else if(search_column.equals("issue_date")){
            System.out.println("item = "+search_column);
            jLabel1.setVisible(true);
            jTextField1.enable();
            // jLabel1.show();
           jButton4.setVisible(true);
            
        } else{
        jTextField1.enable();
        jLabel1.hide();
        jButton4.setVisible(false);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String file_name = JOptionPane.showInputDialog("Please input File name:");
        try{
            excel_exporter exp= new excel_exporter();
            exp.export(this.jTable2, new File("C:\\Users\\user\\Desktop\\"+file_name+".xls"));
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"problem in excel Exporter "+e);
            }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{
            jTable2.print(JTable.PrintMode.FIT_WIDTH);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"problem in print "+e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         String search_key= jTextField1.getText();

        String search_column = "issue_date";
        String search_table= "issue_book";
         int index= 7;
        try{
            power obb = new power();
            
            ResultSet rs= obb.SpecialDateSearch(index, search_key,search_column,search_table);
            
            DefaultTableModel dm= (DefaultTableModel)jTable2.getModel();
            dm.setRowCount(0);            
             int serial=1;
             SimpleDateFormat df= new SimpleDateFormat("dd-MMM-yyyy");
             
             {DefaultTableCellRenderer center_render= new DefaultTableCellRenderer();
            center_render.setHorizontalAlignment(SwingConstants.LEFT);
            jTable2.setDefaultRenderer(String.class, center_render);
            jTable2.setDefaultRenderer(Integer.class, center_render);
             }

            while(rs.next()){
                Vector v2= new Vector();
                for(int i=0; i<=7; i++){
                    v2.add(serial);
                    v2.add(rs.getString("b_id"));
                    v2.add(rs.getString("b_name"));
                    v2.add(rs.getString("writer"));
                    v2.add(rs.getString("s_id"));
                    v2.add(rs.getString("s_name"));
                    v2.add(rs.getString("class"));            
                    v2.add(df.format(rs.getDate("issue_date")));
                }
                dm.addRow(v2);
                serial++;
            }
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"problem in event "+e);
        }
   
        
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(searchIssuedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(searchIssuedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(searchIssuedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(searchIssuedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                searchIssuedBooks dialog = new searchIssuedBooks(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
