package intact;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

/**
 *
 * @author Vikrant Pandey
 */
public class adddata {
    public int Late;
    public Connection conn;
      public Connection getconnection(){    //method is tested and okay
        try{
            
       // Class.forName("com.mysql.jdbc.Driver");
        
        conn= DriverManager.getConnection(path.db_connection_query);
      
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"problem inside loginanddatabase.getconnction"+e);
    }
      return conn;
    
}//getconnection ends here
    
    public void insert_book(String a,String b,String c,String d,String f,String g){
       
        try{
            conn= getconnection();
            Statement st= conn.createStatement();
             String sql = "insert into books(name,publisher,writer,edition,category,price) values ('"+a+"','"+b+"','"+c+"','"+d+"','"+f+"','"+g+"');";
            st.execute(sql);
            JOptionPane.showMessageDialog(null,"book added successfully");
             conn.close();
          
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    
     
    }//insert book ends here
    
    public void insert_student(String a,String b,String c,String d,String f,String photoName){
        
    String sql= "insert into students(student_id,name, fname,"+
                " class, branch, admission_year, student_photo) values "+
                "(?,?,?,?,?,?,?);";
    try{
    conn= getconnection();
    PreparedStatement pst=conn.prepareStatement(sql);
    pst.setString(1,null);
    pst.setString(2, a);
    pst.setString(3, b);
    pst.setString(4,c);
    pst.setString(5, d);
    pst.setString(6, f);
    pst.setString(7,photoName);
    pst.execute();
    pst.close();
    JOptionPane.showMessageDialog(null,"student added successfully");
    conn.close(); 
    }catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
    }
}//insert book ends here
   
public String get_id(String column,String table){
    String id= null;
   try{
    conn= getconnection();
     Statement st= conn.createStatement();
     String sql= "select max("+column+"_id) from "+table+";";
     
    ResultSet rs= st.executeQuery(sql);
    int i;
    rs.next();
    i = rs.getInt(1);
    i++;
    id=""+i;
    conn.close();
   
         
   }catch(Exception e){
        JOptionPane.showMessageDialog(null,"Problem in get_id = adddata"+e);
   }
   return id;
}// get_id ends

public ResultSet get_details(String table, String id,String column){
    ResultSet rs= null;
     try{
    conn= getconnection();
     Statement st= conn.createStatement();
     String sql= "select * from "+table+" where "+column+"_id= '"+id+"';";
     
    rs= st.executeQuery(sql);
   
   
         
   }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
   }
     
   return rs;
    
}// get_details ends

public void issue_book(String book, String student, String date){
    try{
    conn= getconnection();
     Statement st= conn.createStatement();
     String sql=  "insert into issue_book(book_id,student_id,issue_date) values ('"+book+"','"+student+"','"+date+"');";
     st.execute(sql);
      JOptionPane.showMessageDialog(null,"Book Issued"); 
     
   
   
    conn.close();
         
   }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
   }

}// ends Issue_book

public ResultSet get_issue_info(String book_id){
    ResultSet rs= null, rs2=null;
     try{
    conn= getconnection();
     Statement st= conn.createStatement();
     String sql= "select books.book_id, books.name as book_name,books.publisher,"+
             " books.writer,books.edition,books.category,books.price,students.student_id, "+
                "students.name as student_name,students.fname,students.branch, students.class,"+
              "students.admission_year, issue_book.issue_date, issue_book.issue_id from"+ 
                " ((students inner join issue_book on students.student_id= issue_book.student_id)"+
             " inner join books on books.book_id= issue_book.book_id) where books.book_id= '"+book_id+"';";
     
    rs= st.executeQuery(sql);
    /*Date d= rs.getDate(14);
   sql= "GETDATE()-d;";
   rs2= st.executeQuery(sql);
   rs2.next();
   Late= rs2.getInt(0);
   */
         
   }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
   }
     
   return rs;
    
}// get_issue_info ends

public void return_book(String book, String student,String issue_date,String date){
     try{
    conn= getconnection();
     Statement st= conn.createStatement();
     String sql=  "insert into return_book(book_id,student_id,return_date,issue_date) values ('"+book+"','"+student+"','"+date+"','"+issue_date+"');";
     st.execute(sql);
     sql= "delete from issue_book where (book_id= '"+book+"' and student_id= '"+student+"');";
     st.execute(sql);
      JOptionPane.showMessageDialog(null,"Book Returned"); 
     
   
   
    conn.close();
         
   }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
   }

}// ends return_book

public boolean valid_issue(String book_id){
    boolean answer=true;
     try{
    conn= getconnection();
     Statement st= conn.createStatement();
     ResultSet rs;
     String sql=  "select issue_id from issue_book where book_id= '"+book_id+"';";
     rs= st.executeQuery(sql);
     
     if(rs.next()){
         answer= false;
     }
    
    conn.close();
         
   }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
   }
     return answer;
}// ends valid_issue

public void insert_category(String name,String des, String status){
    conn= getconnection();
    String sql= "insert into category (category_id, category_name,"+
                " category_description, category_status) values "+
                "(?,?,?,?);";
    try{
    PreparedStatement pst=conn.prepareStatement(sql);
    pst.setString(1, null);
    pst.setString(2, name);
    pst.setString(3, des);
    pst.setString(4, status);
    pst.execute();
    pst.close();
    conn.close();
     JOptionPane.showMessageDialog(null,"category added");
     
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
    
}// insert_category ends here
        
public ResultSet search_book(String search_key, String search_column, String search_table){
   conn= getconnection();
   ResultSet rs= null;
   String sql;
       sql=  "select * from "+search_table+" where "+search_column+" like '%"+search_key+"%';";
       
       //if(search_table.equals("issue_book")){
             //  sql= "select  b.book_id as b_id, b.name as b_name,b.writer as writer, s.student_id as s_id, s.name as s_name, s.class, iss.issue_date from issue_book iss inner join books b on b.book_id= iss.book_id inner join students s on s.student_id= iss.student_id"+" where "+search_column+" like '%"+search_key+"%';";


   
   try{
   Statement st= conn.createStatement();
    
    
     rs= st.executeQuery(sql);
    
    
   }catch(Exception e){
        JOptionPane.showMessageDialog(null,"iratation from execute querey"+e);
   }
   
   return rs;
}// search_book ends here


public void update_books(int book_id, String name, String publisher, String writer,  String edition, String category, String price){
    try{
    conn= getconnection();
     Statement st= conn.createStatement();
     String sql= "update books set name= '"+name+"', publisher ='"+publisher+"', writer ='"+writer+"', edition="+
             " '"+edition+"', category ='"+category+"', price ='"+price+"' where book_id= "+book_id+";";
     st.execute(sql);
    
     JOptionPane.showMessageDialog(null,"Book details updated"); 
     
   
   
    conn.close();
         
   }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
   }
    
    
}// update_books ends here
}
