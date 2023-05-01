

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class tableModel extends javax.swing.table.AbstractTableModel{
    
    private ArrayList<String> alist = null;
    private String[] c_headers = null;
    private Object[][] data = null;
    private Date sec=null;
    private Date fir=null;
    
    
    public tableModel(ArrayList<String> list,Date firstval,Date secondval) {
         
         this.fir = firstval;
         this.sec = secondval;
         this.alist = list;
         setHeaders();
    }
    
    private void setHeaders(){
        
        c_headers=new String[]{"name","Status","timeroll","InTime","OutTime","in","out"};
        
        data=new Object[this.alist.size()][7];
        
        int i=0;
        for(i=0; i< alist.size();i++){
            
            data[i] = new Object[]{alist.get(i),"attend","first","","","0:0","0:0"};
            
            
        }
        
    }

    public int getRowCount() {
        return data.length;
    }

    public int getColumnCount() {
        return c_headers.length;
    }

    public String getColumnName(int col){      
        return c_headers[col];
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
    
    public Class getColumnClass(int column){       
        return getValueAt(0,column).getClass();
    }
    
    public boolean isCellEditable(int row,int col){
        
        if(col==1||col==2){
        	return true;
        }
        
        return false;
    }
    
    public void setValueAt(Object value,int row,int col){
        
        if((value+"").equals("absent")){
        	setValueAt("Absent",row,2);
            this.setValueAt("0:00",row,3);
            this.setValueAt("0:00",row,4);
            this.setValueAt("0:00",row,5);
            this.setValueAt("0:00",row,6);
            
        }
       
        
        
        
        data[row][col]=value;
        this.fireTableCellUpdated(row,col);
        
    }
    
    
}
