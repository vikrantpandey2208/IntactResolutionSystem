package intact;

import java.awt.HeadlessException;
import java.sql.*;
import javax.crypto.*;
import java.security.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class loginanddatabase {
    public Connection conn;
      public Connection getconnection(){   
        try{
            
        //Class.forName("com.mysql.jdbc.Driver");
        String psz= "admin";
        conn= DriverManager.getConnection(path.db_connection_query);
        System.out.println("Connected to DB");      
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"problem inside loginanddatabase.getconnction"+ e);
    }
      return conn;
    
}//getconnection ends here
    
    public void crypt(){
        try{
            Signature sign= Signature.getInstance("SHA256withRSA");
        KeyPairGenerator keygen= KeyPairGenerator.getInstance("RSA");
        keygen.initialize(2048);
        KeyPair pair= keygen.generateKeyPair();
        PublicKey publickey= pair.getPublic();
        Cipher cipher= Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE,publickey);
        byte[] input= "welcome too java".getBytes();
        cipher.update(input);
        byte[] ciphertext= cipher.doFinal();
        JOptionPane.showMessageDialog(null,new String(ciphertext,"UTF8"));
        // decrypt
        cipher.init(Cipher.DECRYPT_MODE,pair.getPrivate());
        
        byte[] normaltext= cipher.doFinal(ciphertext);
        JOptionPane.showMessageDialog(null,new String(normaltext));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
}
  
    public void make_table(){ 
        
        try{
            getconnection();
        Statement st= conn.createStatement();
       
        st.execute("create table user (rowid INTEGER primary key,name text(30) unique,username Text(30),psz Text(10),sec_que Text(20), answer Text(40));");
        st.close();      
        
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null,"username already used");
        }
    }// ends make_db()
    
    public void insert_data(String a,String b,String c,String d,String f,String photo){
        try{
            conn= getconnection();
            Statement st= conn.createStatement();
            String sql= ("insert into user values("+null+",'"+a+"','"+b+"','"+c+"','"+d+"','"+f+"','"+photo+"');");
        st.execute(sql);
       
        conn.close();
        
       JOptionPane.showMessageDialog(null,"user created");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,"problem in insert_data"+e);
        }
        
    }// ends insert_data
  
//==============================================================
//   Login password checking
//==============================================================
   
    public boolean check_user(String uname,String psz){
       boolean ans= false;
        try{
            
        conn= getconnection(); 
        Statement st= conn.createStatement();
        String sql= ("select * from user where username='"+uname+"' and psz= '"+psz+"';");
        ResultSet res= st.executeQuery(sql);
        
        ans= res.next();
        conn.close();
        
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
       
      return ans; 
    }// ends check user
    
    ResultSet show_name_question(String uname) {
        ResultSet res=null;
     try{
       conn= getconnection();
         Statement st= conn.createStatement();
        String sql= ("select * from user where username ='"+uname+"';");
         res= st.executeQuery(sql);
         
     }catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
            try {
                conn.close();
            }catch (SQLException ex) {
                Logger.getLogger(loginanddatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
     }
    
     return res;
    }// ends show_name_question

    boolean check_answer(String uname, String answer) {
       conn= getconnection();
        ResultSet res;
        boolean decision=false;
        try{
         Statement st= conn.createStatement();
        
        String sql= ("select * from user where username='"+uname+"' and answer= '"+answer+"';");
         res= st.executeQuery(sql);
           
         decision= res.next();
         conn.close();
         
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,"problem in check_answer");
        }
        return decision;
        
    }// ends check_answer

    void update_user(String a, String b, String c, String d, String f) {
        try{
            conn= getconnection();
            Statement st= conn.createStatement();
        
            String sql= ("update user set psz= '"+c+"', sec_que= '"+d+"', answer= '"+f+"' where (username= '"+a+"' and name= '"+b+"');");
            st.execute(sql);
            conn.close();
        
        JOptionPane.showMessageDialog(null,"Password updated");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }// ends update_user
    
    public boolean check_number_of_admin(){
        int i;
        boolean ans= false;
   try{
    conn= getconnection();
     Statement st= conn.createStatement();
     String sql= "select max(row_id) from user;";
     
    ResultSet rs= st.executeQuery(sql);
    
    rs.next();
    i = rs.getInt(1);
    i++;
    
    if(i<10)
        ans=true;
   
    conn.close();
   
         
   }catch(Exception e){
        JOptionPane.showMessageDialog(null,"Problem in get_id = adddata"+e);
   }
   return ans;
    }// check_number_of_admin ends here
    
    public String getAutoLoginName(String photo) throws SQLException{
        System.out.println("in java class "+photo);
        conn= getconnection();
        String UName=null;
        Statement st= conn.createStatement();
        String sql= "select name from user where userImage= '"+photo+"' ;";
        
        ResultSet rs= st.executeQuery(sql);
        if(rs.next())
            UName= rs.getString("name");
        System.out.println("found userName in coreJava file "+UName);
        conn.close();
        return  UName;  
   
    }
    
    
}// class def ends