package intact;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class login extends javax.swing.JFrame {
   
    loginanddatabase ob_log= new loginanddatabase();
    boolean set_create= true;
    String autoLoginUserName, UserNameToPrint;
    
    public login(){
        initComponents();
        Image icon=  Toolkit.getDefaultToolkit().getImage(path.icon);
        setIconImage(icon);
        jTextField1.requestFocus();
        /*-
        if(!autoLogin())
            this.setVisible(true);
        else
            System.out.println("login constructor");
        */  
        
    } // constructor ends
    
    private void ReloginByFace(){
        try{     
            ProcessBuilder pb = new ProcessBuilder(path.python_scripts_path+"main", "name");            
            Process p = pb.start();
            
            try {
                p.waitFor();
            }catch (Exception e) {
                System.out.println("Error in getting name from python inside faceLOgin"+e);
            }
            BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            
            while ((autoLoginUserName = bfr.readLine()) == null){
                System.out.println("caught username from python "+autoLoginUserName);
            }
            if(autoLoginUserName.equals("1001") || autoLoginUserName.equals("1002")||autoLoginUserName.equals("1003") )
               JOptionPane.showMessageDialog(null, "Can't login by face, Try again");
            else{
                this.dispose();
                new home().setVisible(true);    
            }
        }catch(Exception e){
                    System.out.println("problem created in reLogin by face button "+e); 
                    System.gc();
        }  
    }
    
    public boolean autoLogin(){
        boolean go = false; 
        
            
        return go;
    }
    
    public String getLogin() throws SQLException{
        UserNameToPrint= ob_log.getAutoLoginName(autoLoginUserName);
        System.out.println("user Name from database= "+UserNameToPrint);
        System.out.println("Global Photo name = "+autoLoginUserName);
        if(!UserNameToPrint.equals(""))
            return UserNameToPrint;
        return "";
           
    }
    
    public void login(){
        String uname= jTextField1.getText();
        String psz= jPasswordField1.getText();
       
        /* Developer super user backdoor
        if(uname.equals("v")){ // *********here backdoor
            home home_ob= new home();
           
            setVisible(false);
            home_ob.setVisible(true);
            
            this.dispose();
        }else // backdoor ends here remove this code after development
        */   
        if(3>jTextField1.getText().length() || 15<jTextField1.getText().length()){
           JOptionPane.showMessageDialog(null,"Wrong Username");
           jTextField1.setText(null);
           jTextField1.requestFocus();
           
        }else if(8>jPasswordField1.getText().length()){
            JOptionPane.showMessageDialog(null,"here Wrong Password");
            jPasswordField1.setText(null);
            jTextField1.requestFocus();
        }
        else if(ob_log.check_user(uname,psz)){
            
            try{
            new home().setVisible(true);
            this.dispose();
            }catch(Exception e){
                
            JOptionPane.showMessageDialog(null,e);
        }
            
        }else{
            JOptionPane.showMessageDialog(null,"Username and password not matched");
            jTextField1.setText(null);
            jPasswordField1.setText(null);
            jTextField1.requestFocus();
        }
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Intact Resolution System : Login");
        setResizable(false);
        setSize(new java.awt.Dimension(600, 400));
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 150, 153));
        jLabel2.setFont(new java.awt.Font("Mangal", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 255));
        jLabel2.setText("User Name:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 138, -1));

        jButton2.setBackground(new java.awt.Color(255, 150, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intact/resolution/system/Create new user.png"))); // NOI18N
        jButton2.setText("Create New");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, -1, -1));

        jButton3.setBackground(new java.awt.Color(255, 150, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intact/resolution/system/Exit.png"))); // NOI18N
        jButton3.setText("Exit");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, -1, -1));

        jButton4.setBackground(new java.awt.Color(255, 150, 153));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intact/resolution/system/Forgot-password-icon.png"))); // NOI18N
        jButton4.setText("Forgot Password");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, -1, -1));

        jPasswordField1.setBackground(new java.awt.Color(255, 128, 161));
        jPasswordField1.setForeground(new java.awt.Color(255, 0, 255));
        jPasswordField1.setOpaque(false);
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 195, -1));

        jTextField1.setBackground(new java.awt.Color(255, 128, 161));
        jTextField1.setForeground(new java.awt.Color(255, 0, 255));
        jTextField1.setNextFocusableComponent(jLabel6);
        jTextField1.setOpaque(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 195, -1));

        jLabel4.setBackground(new java.awt.Color(255, 150, 153));
        jLabel4.setFont(new java.awt.Font("Mangal", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 255));
        jLabel4.setText("Password:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 118, -1));

        jLabel5.setBackground(new java.awt.Color(102, 102, 255));
        jLabel5.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 255, 0));
        jLabel5.setText("Login");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 96, 43));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 590, -1));

        jLabel1.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 51));
        jLabel1.setText("Intact Resolution System");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        jLabel3.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 204, 51));
        jLabel3.setText("All Rights Reserved");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 200, -1));

        jLabel7.setFont(new java.awt.Font("Serif", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 255, 51));
        jLabel7.setText("Programmer ");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, 90, -1));

        jLabel8.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 255, 0));
        jLabel8.setText("Vikrant Pandey");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
        });
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, 190, -1));

        jLabel9.setForeground(new java.awt.Color(102, 255, 102));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 340, 20));

        jButton5.setBackground(new java.awt.Color(255, 150, 153));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 0));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intact/resolution/system/Login.png"))); // NOI18N
        jButton5.setText("Login");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 100, -1));

        jButton6.setText("Login By Face");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 130, -1));

        jLabel6.setForeground(new java.awt.Color(204, 255, 51));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intact/resolution/system/Irs 1.jpg"))); // NOI18N
        jLabel6.setOpaque(true);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 180, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.login();
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jPasswordField1.requestFocus();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null,"Do you really want to exit","Confirm",JOptionPane.YES_NO_OPTION);
        if(a==0){
            System.exit(0);
        }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        
        Object[] options = { "OK", "CANCEL" };
              int a=  JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
        if(a==0){
            if(true){
                new create_new().setVisible(true);
            }
        
            else{
                JOptionPane.showMessageDialog(null, "You can create 3 admin only", "Alert", JOptionPane.ERROR_MESSAGE);
            } 
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        dispose();
        new forgot_psz().setVisible(true);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
        jLabel9.setText("The Complete and smart solution");
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        // TODO add your handling code here:
        jLabel9.setText("rights @VPandey");
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        // TODO add your handling code here:
       JOptionPane.showMessageDialog(null, "Made by Vikrant Pandey ", "Alert", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        // TODO add your handling code here:
        jLabel9.setText("");
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        // TODO add your handling code here:
        jLabel9.setText("");
    }//GEN-LAST:event_jLabel3MouseExited

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        this.login();
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        jButton6.setText("Try Again...");
        this.ReloginByFace();
    }//GEN-LAST:event_jButton6ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (true) {
                    javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                    System.out.println("set look and feel in login");
                    break;
                }                 
                   
            }
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null,"problem in UIManager");
        } 
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new login().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jButton2;
    private javax.swing.JLabel jButton3;
    private javax.swing.JLabel jButton4;
    private javax.swing.JLabel jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
