import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

public class tablePanModel extends AbstractTableModel  {
	
	//util classs
	class employee_list{
		private ArrayList<employee> empList;
		
		public employee_list()
		{
			empList = new ArrayList<employee>();
			
		}
		public void add (employee emp)
		{
			empList.add(emp);
		}
		public ArrayList<employee> getemp()
		{
			return empList;
		}
		
		
	}

	private ArrayList<employee> alist = null;
	private String[] columnNames = null;
	private Object[][] data = null;
	
	
	public final Object[] longValues = {new Integer(1), "mahmoud aly",
			new Integer(21),new Integer(30),
			new Float(2000), "auditing",new Integer(0),new Integer(21)};
	

	public tablePanModel(ArrayList<employee> list) {

		this.alist = list;
		setHeaders();
	}

	
	private void setHeaders() {
		columnNames = new String[] { "id", "employe name", "totalballance", "age", "salary", "department", "vacation",
				"vbalance" };

		data = new Object[this.alist.size()][8];
		int i = 0;
		for (employee emp : alist) {
			data[i] = new Object[] { emp.getId(), emp.getFirstName(), emp.getTotalbalance(), emp.getAg(),
					emp.getSalary(), emp.getDepartment(), emp.getVacation(), emp.getVbalance() };
					i++;
		}
		

	}

	public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 1) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        //fireTableCellUpdated(row, col);
       // fireTableRowsUpdated(row, col);
        fireTableRowsInserted(row, col);
        
    }
    public void addRow(employee emp)
    {
    	data[alist.size()+1] = new Object[] { emp.getId(), emp.getFirstName(), emp.getTotalbalance(), emp.getAg(),
				emp.getSalary(), emp.getDepartment(), emp.getVacation(), emp.getVbalance() };
    	
    	//emp = new employee();
    	alist.add(emp);
    	
    }

}
