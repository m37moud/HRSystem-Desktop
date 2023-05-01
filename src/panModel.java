import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;



public class panModel extends AbstractTableModel {
	
	private DBInfo db = new DBInfo();
	 Class columnType[]  = { Value.class, Integer.class,  String.class,
			 Integer.class,Integer.class,
			Float.class,String.class,Integer.class,Integer.class};
	
	private ArrayList<employee> alist = new ArrayList<>();
	private ArrayList<Value> ls = new ArrayList<>();
	//private String[] columnNames = null;
	private List<Object> columnNames = new ArrayList<>();;
	
	
	    public panModel() {
	        super();

			 
	    }

	public panModel(ArrayList<employee> list) {
	//	columnNames = new String[] { "id", "employe name", "totalballance", "age", "salary", "department", "vacation",
		//		"vbalance" };
		
		setHeaders();
		this.alist = list;
		for (int i = 0; i < alist.size(); i++) {
            ls.add(new Value(false, alist.get(i).getId()));
        }
		
	}
	

	public int getColumnCount() {
		return columnNames.size();
	}

	public int getRowCount() {
		int size;
		if (alist == null ) {
			size = 0;
		} else {
			size = alist.size();
		}
		return size;
	}

	public String getColumnName(int col) {
		return (String) columnNames.get(col);
	}

	public Object getValueAt(int row, int col) {

		Object temp = null;
		
		
		if (col == 0) { 
			
			
			temp = ls.get(row);
			//temp = new Value(false , alist.get(row).getId());
		}
		else if (col == 1) {
			temp = new Integer(alist.get(row).getId());
		} else if (col == 2) {
			temp = alist.get(row).getFirstName();
		} else if (col == 3) {
			temp = new Integer(alist.get(row).getTotalbalance());
		} else if (col == 4) {
			temp = new Integer(alist.get(row).getAg());
		} else if (col == 5) {
			temp = new Float(alist.get(row).getSalary());
		} else if (col == 6) {
			temp = alist.get(row).getDepartment();
		} else if (col == 7) {
			
			int i = db.vacationCount_conn(alist.get(row).getFirstName(), alist.get(row).getDepartment(), true, false);
			temp = new Integer(i);
			
			//db.updateHolidayBalance(alist.get(row).getFirstName(), alist.get(row).getDepartment(), true, false);
			//temp = new Integer(alist.get(row).getVacation());
		} else if (col == 8) {
			if(db.vacationCount_conn(alist.get(row).getFirstName(), alist.get(row).getDepartment(), true, false) == 0 ){
				temp = new Integer(alist.get(row).getTotalbalance());
			}else
			temp = new Integer(alist.get(row).getVbalance());

		}

		return temp;
	}

	public Class<?> getColumnClass(int c) {
		//return getValueAt(0, c).getClass();
		
		    if (c > 0) {
             return getValueAt(0, c).getClass();
         }else if (c == 0) {
             return Value.class;
         } 
		  else {
             return null;
         }
		
	}
	

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col ) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
		
		if (col ==1  && getColumnName(1).equals("id")) {
			return false;
		} 
		
			return true;
		
	}
	

	/*
	 * Don't need to implement this method unless your table's data can change.
	 */
	public void setValueAt(Object value, int row, int col) {

		/*
		 * { emp.getId(), emp.getFirstName(), emp.getTotalbalance(),
		 * emp.getAg(), emp.getSalary(), emp.getDepartment(), emp.getVacation(),
		 * emp.getVbalance() }
		 */
		
		if (col == 0) {
/*
				val.selected = (boolean) value;
				alist.get(row).setId((Integer) val.value);
			*/
			
			//ls.add(row).selected = (Boolean) value;
			
			ls.get(row).selected = (Boolean) value;
		}
		
	else if (col == 1) {
		
			alist.get(row).setId((int) value);
		

			

		} else if (col == 2) {
			alist.get(row).setFirstName((String) value);
		} else if (col == 3) {
			alist.get(row).settotal((int) value);
		} else if (col == 4) {
			alist.get(row).setAg((int) value);
		} else if (col == 5) {
			alist.get(row).setSalary((float) value);
		} else if (col == 6) {
			alist.get(row).setDepartment((String) value);
		} else if (col == 7) {
			alist.get(row).setVacation((int) value);
		} else if (col == 8) {
			alist.get(row).setVbalance((int) value);
		}

		fireTableCellUpdated(row, col);

	}

	public void addNewEmptyRow() {

		alist.add(new employee());
		fireTableRowsInserted(0, getRowCount());

	}

	public void removeLastNewEmptyRow(int row) {
		
		alist.remove(row);

		
		fireTableRowsDeleted(0, getRowCount());

	}
	  private void setHeaders(){
		
		  	columnNames.add( "");
			columnNames.add( "id");
			columnNames.add( "employe name");
			columnNames.add( "totalballance");
			columnNames.add( "age");
			columnNames.add( "salary");
			columnNames.add( "department");
			columnNames.add( "vacation");
			columnNames.add( "vbalance");
			
			
			
		
			//
			
			
		
			
	        
	    }
	public void addCoulmn(int index ,String name , ArrayList<employee> lis)
	{
		 columnNames.add(index , name);
		   
		    fireTableStructureChanged();

		
	}
	public void remove(int index)
	{
		//ArrayList<employee> li = new ArrayList<>();
		
		
		//mod.columnNames.add(name);
		
	    columnNames.remove(index);
	    fireTableStructureChanged();
		
	
		
	}
	public int hidetoModel(JTable table, int vColIndex) {
	    if (vColIndex >= table.getColumnCount()) {
	      return -1;
	    }
	    return table.getColumnModel().getColumn(vColIndex).getModelIndex();
	  }
}
