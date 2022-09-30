package intact;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Vikrant Pandey
 */

public class excel_exporter {
  
    public void export(JTable table,File file) throws IOException{
        TableModel model= table.getModel();
        FileWriter out= new FileWriter(file);
        BufferedWriter bw= new BufferedWriter(out);
        
        for(int i=0; i<model.getColumnCount(); i++){
            bw.write(model.getColumnName(i)+"\t");
            
        }
        bw.write("\n");
        for(int i=0; i<model.getRowCount(); i++){
            for(int j=0; j<model.getColumnCount(); j++){
                bw.write(model.getValueAt(i,j)+"\t");
            }
            bw.write("\n");
        }
        bw.close();
        JOptionPane.showMessageDialog(null,"Exported successfully on Desktop directory");
    }
    
}
