package intact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;

/**
 *
 * @author Vikrant Pandey
 */
public class power {
      public Connection conn;
      public Connection getconnection(){  
          
        try{            
            conn= DriverManager.getConnection(path.db_connection_query);      
            }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"problem inside loginanddatabase.getconnction"+e);
            }
         return conn;    
        }
      
      public ResultSet SearchSpecialBook(int index, String search_key,String search_column,String search_table) throws ParseException{
            conn= getconnection();
            ResultSet rs= null;
            String sql="";
            
            if(search_table.equals("issue_book")){
                switch(index){
                  
                    case 2: {search_column="books.name";
                    
                    System.out.println("case 2= bname " + index);
                                  sql= "select  books.book_id as b_id, books.name as b_name,books.writer as writer,"+
                     " students.student_id as s_id, students.name as s_name, students.class,"+
                     " issue_book.issue_date from issue_book inner join books"+
                     " on books.book_id= issue_book.book_id inner join students on"+
                     " students.student_id= issue_book.student_id"+" where "+search_column+
                     " like '%"+search_key+"%';";
                    break;}
                     
                  //  case 3 : search_table= "books";
                   // break;
                    //case 4: search_table="books";
                    
                    
                    case 5: {search_column="students.name";
                    System.out.println("case 5 sname" + index);
                      sql= "select  books.book_id as b_id, books.name as b_name,books.writer as writer,"+
                     " students.student_id as s_id, students.name as s_name, students.class,"+
                     " issue_book.issue_date from issue_book inner join books"+
                     " on books.book_id= issue_book.book_id inner join students on"+
                     " students.student_id= issue_book.student_id"+" where "+search_column+
                     " like '%"+search_key+"%';";
                    break;}
                    
                    case 6: search_table= "students";
                                
                        sql= "select  books.book_id as b_id, books.name as b_name,books.writer as writer,"+
                     " students.student_id as s_id, students.name as s_name, students.class,"+
                     " issue_book.issue_date from issue_book inner join books"+
                     " on books.book_id= issue_book.book_id inner join students on"+
                     " students.student_id= issue_book.student_id"+" where "+search_table+"."+search_column+
                     " like '%"+search_key+"%';";
                    break;
                    case 7: { 
                        System.out.println("case 7 sname" + index);
                                SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
                                Date iDate=  df.parse(search_key);
                                  sql= "select  books.book_id as b_id, books.name as b_name,books.writer as writer, students.student_id as s_id, students.name as s_name, students.class, issue_book.issue_date from issue_book inner join books on books.book_id= issue_book.book_id inner join students on students.student_id= issue_book.student_id"+" where "+search_column+"='"+iDate+"';";
                    break;    
                    }
                    default:   
                      search_table= "books";
                        sql= "select  books.book_id as b_id, books.name as b_name,books.writer as writer,"+
                     " students.student_id as s_id, students.name as s_name, students.class,"+
                     " issue_book.issue_date from issue_book inner join books"+
                     " on books.book_id= issue_book.book_id inner join students on"+
                     " students.student_id= issue_book.student_id"+" where "+search_table+"."+search_column+
                     " like '%"+search_key+"%';";
                    break;
                }
            }
            
            if(search_table.equals("return_book")){
                switch(index){
                     case 1:  search_column="rr.book_id";
                    sql=  "select  b.book_id as b_id, b.name as b_name, b.writer as writer, "+
                   " s.student_id as s_id, s.name as s_name, s.class,rr.return_date,rr.issue_date"+
                  " from return_book rr inner join books b on rr.book_id= b.book_id"+
                 " inner join students s on s.student_id= rr.student_id"+"  where "+search_column+
                     " like '%"+search_key+"%';";
                    break;
                    case 2:  search_column="b.name";
                    sql=  "select  b.book_id as b_id, b.name as b_name, b.writer as writer, "+
                   " s.student_id as s_id, s.name as s_name, s.class,rr.return_date,rr.issue_date"+
                  " from return_book rr inner join books b on rr.book_id= b.book_id"+
                 " inner join students s on s.student_id= rr.student_id"+"  where "+search_column+
                     " like '%"+search_key+"%';";
                    break;
                    
                     case 3:  search_column="b.writer";
                    sql=  "select  b.book_id as b_id, b.name as b_name, b.writer as writer, "+
                   " s.student_id as s_id, s.name as s_name, s.class,rr.return_date,rr.issue_date"+
                  " from return_book rr inner join books b on rr.book_id= b.book_id"+
                 " inner join students s on s.student_id= rr.student_id"+"  where "+search_column+
                     " like '%"+search_key+"%';";
                    break;
                    
                     case 4:  search_column="rr.student_id";
                    sql=  "select  b.book_id as b_id, b.name as b_name, b.writer as writer, "+
                   " s.student_id as s_id, s.name as s_name, s.class,rr.return_date,rr.issue_date"+
                  " from return_book rr inner join books b on rr.book_id= b.book_id"+
                 " inner join students s on s.student_id= rr.student_id"+"  where "+search_column+
                     " like '%"+search_key+"%';";
                    break;
                    
                    case 5:  search_column="s.name";
                    sql=  "select  b.book_id as b_id, b.name as b_name, b.writer as writer, "+
                   " s.student_id as s_id, s.name as s_name, s.class,rr.return_date,rr.issue_date"+
                  " from return_book rr inner join books b on rr.book_id= b.book_id"+
                 " inner join students s on s.student_id= rr.student_id"+"  where "+search_column+
                     " like '%"+search_key+"%';";
                    break;
                    
                     case 6:  search_column="s.class";
                    sql=  "select  b.book_id as b_id, b.name as b_name, b.writer as writer, "+
                   " s.student_id as s_id, s.name as s_name, s.class,rr.return_date,rr.issue_date"+
                  " from return_book rr inner join books b on rr.book_id= b.book_id"+
                 " inner join students s on s.student_id= rr.student_id"+"  where "+search_column+
                     " like '%"+search_key+"%';";
                    break;
                    
                    /*
                    
                   */
                }
            }
            try{
                     Statement st= conn.createStatement();
                     rs= st.executeQuery(sql);
                 }catch(Exception e){
                        JOptionPane.showMessageDialog(null,"iratation from execute querey in  power "+e);
                    }
             return rs;
        }
      
    public ResultSet SpecialDateSearch(int index, String search_date,String search_column,String search_table) throws ParseException{
          conn= getconnection();
            ResultSet rs= null;
            //SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
            //Date iDate=  df.parse(search_date);
            
            String sql="";
             sql= "select  books.book_id as b_id, books.name as b_name,books.writer as writer, students.student_id as s_id, students.name as s_name, students.class, issue_book.issue_date from issue_book inner join books on books.book_id= issue_book.book_id inner join students on students.student_id= issue_book.student_id"+" where "+search_column+"='"+search_date+"';";
              
              try{
                     Statement st= conn.createStatement();
                     rs= st.executeQuery(sql);
                 }catch(Exception e){
                        JOptionPane.showMessageDialog(null,"iratation from execute querey in  power "+e);
                    }
             return rs;
    }

public ResultSet ReturnDateSearch(int index, String search_date,String search_column,String search_table) throws ParseException{
    conn= getconnection();
            ResultSet rs= null;
            String sql="";
            
  if(index==7){
                                 
                  sql=  "select  b.book_id as b_id, b.name as b_name, b.writer as writer, "+
                   " s.student_id as s_id, s.name as s_name, s.class,rr.return_date,rr.issue_date"+
                  " from return_book rr inner join books b on rr.book_id= b.book_id"+
                 " inner join students s on s.student_id= rr.student_id"+"  where rr."+search_column+"= '"+search_date+"';";
  }else if(index==8){
                               sql=  "select  b.book_id as b_id, b.name as b_name, b.writer as writer, "+
                   " s.student_id as s_id, s.name as s_name, s.class,rr.return_date,rr.issue_date"+
                  " from return_book rr inner join books b on rr.book_id= b.book_id"+
                 " inner join students s on s.student_id= rr.student_id"+"  where rr."+search_column+"= '"+search_date+"';";             
  }         
            
    try{
                     Statement st= conn.createStatement();
                     rs= st.executeQuery(sql);
                 }catch(Exception e){
                        JOptionPane.showMessageDialog(null,"iratation from execute querey in  power "+e);
                    }
             return rs;
}
}
         
    
    
    

