
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.JTable;

public class DBInfo {
	private static Connection con = null;
	// private static PreparedStatement pst = null;
	private ResultSet rs = null;
	private Statement stmt = null;

	public Connection getConnection() {
		
		return con;
	}

	public Connection getConnection_1() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:D:\\D\\user.sqlite";
			con = DriverManager.getConnection(url);
			con.setAutoCommit(false);
			// System.out.println("connected");
		}

		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

		}
		
		
		return con;
	}

	public boolean Connect() {

		if (con == null) {

			try {

				con = DriverManager.getConnection("jdbc:sqlite:D:\\D\\user.sqlite");
				con.setAutoCommit(false);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		return false;
	}

	public boolean Disconnect() {

		if (con != null) {

			try {
				con.setAutoCommit(true);
				con.close();
				con = null;
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public int save(employee emp) {
		int st = 0;

		PreparedStatement pst = null;
		
		try {

			con = getConnection_1();
			String sql = "INSERT INTO 'employe' ('id','fname','totaldays','age','salary','department','vacanition','vbalance') VALUES (?,?,?,?,?,?,?,?) ";
			// con = DBInfo.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, emp.getId());
			pst.setString(2, emp.getFirstName());
			pst.setInt(3, emp.getTotalbalance());
			pst.setInt(4, emp.getAg());
			pst.setFloat(5, emp.getSalary());
			pst.setString(6, emp.getDepartment());
			pst.setInt(7, emp.getVacation());
			pst.setInt(8, emp.getVbalance());

			st = pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return st;
	}

	public int save2(employee emp, JTable table) {
		int st = 0;

		PreparedStatement pst = null;
		try {

			con = getConnection_1();
			String sql = "INSERT INTO 'employe' ('id','fname','totaldays','age','salary','department','vacanition','vbalance') VALUES (?,?,?,?,?,?,?,?) ";
			// con = DBInfo.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, emp.getId());
			pst.setString(2, emp.getFirstName());
			pst.setInt(3, emp.getTotalbalance());
			pst.setInt(4, emp.getAg());
			pst.setFloat(5, emp.getSalary());
			pst.setString(6, emp.getDepartment());
			pst.setInt(7, emp.getVacation());
			pst.setInt(8, emp.getVbalance());

			for (int i = 0; i < table.getRowCount(); i++) {
				pst.setInt(1, emp.getId());
				pst.setString(2, emp.getFirstName());
				pst.setInt(3, emp.getTotalbalance());
				pst.setInt(4, emp.getAg());
				pst.setFloat(5, emp.getSalary());
				pst.setString(6, emp.getDepartment());
				pst.setInt(7, emp.getVacation());
				pst.setInt(8, emp.getVbalance());
			}

			st = pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return st;
	}

	public int update(employee emp) {
		int st = 0;

		// INSERT INTO 'employe'
		// ('id','fname','totaldays','age','salary','department','vacanition','vbalance')
		// VALUES (?,?,?,?,?,?,?,?)
		String sql = "UPDATE employe SET fname = ?, totaldays = ? , age = ? , salary = ? ,department = ? , vacanition = ? , vbalance = ? WHERE id = ? and  department = ?";

		PreparedStatement pst = null;
		con = getConnection_1();
		try {

			pst = con.prepareStatement(sql);
			pst.setString(1, emp.getFirstName());
			pst.setInt(2, emp.getTotalbalance());
			pst.setInt(3, emp.getAg());
			pst.setFloat(4, emp.getSalary());
			pst.setString(5, emp.getDepartment());
			pst.setInt(6, emp.getVacation());
			pst.setInt(7, emp.getVbalance());
			pst.setInt(8, emp.getId());
			pst.setString(9, emp.getDepartment());
			st = pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return st;

	}

	public int editemployee(employee emp, int id, String depart) {
		int st = 0;

		// INSERT INTO 'employe'
		// ('id','fname','totaldays','age','salary','department','vacanition','vbalance')
		// VALUES (?,?,?,?,?,?,?,?)
		String sql = "UPDATE employe SET fname = ?, totaldays = ? , age = ? , salary = ? , vacanition = ? , vbalance = ? WHERE id = ?  and department =  '"
				+ depart + "'";

		PreparedStatement pst = null;
		con = getConnection_1();
		try {

			pst = con.prepareStatement(sql);
			pst.setString(1, emp.getFirstName());
			pst.setInt(2, emp.getTotalbalance());
			pst.setInt(3, emp.getAg());
			pst.setFloat(4, emp.getSalary());
			pst.setString(5, emp.getDepartment());
			pst.setInt(6, emp.getVacation());
			pst.setInt(7, emp.getVbalance());
			pst.setInt(8, emp.getId());
			st = pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return st;

	}

	public int delete(int id, String depart) {
		int st = 0;
		String sql = "DELETE FROM employe WHERE id= " + id + " and department = '" + depart + "' ;";
		con = getConnection_1();

		try {

			con = getConnection_1();
			PreparedStatement pst = con.prepareStatement(sql);
			// pst.setInt(1, id);
			st = pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return st;
	}

	public employee getEmployeeid(int id) {
		employee em = new employee();
		String sql = "SELECT * FROM employe WHERE id=?";
		// con = DBInfo.getConnection();
		PreparedStatement pst = null;
		con = getConnection_1();
		try {

			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				// ('fname','lname','age','salary','department','vacanition','vbalance')
				em.setId(rs.getInt(1));
				em.setFirstName(rs.getString(2));
				em.settotal(rs.getInt(3));
				em.setAg(rs.getInt(4));
				em.setSalary(rs.getFloat(5));
				em.setDepartment(rs.getString(6));
				em.setVacation(rs.getInt(7));
				em.setVbalance(rs.getInt(8));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return em;
	}

	public employee getEmployeeid(int id, String depart) {
		employee em = new employee();
		String sql = "SELECT * FROM employe WHERE id=? and  department = ?";
		// con = DBInfo.getConnection();
		PreparedStatement pst = null;
		con = getConnection_1();
		try {

			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, depart);
			rs = pst.executeQuery();
			if (rs.next()) {
				// ('fname','lname','age','salary','department','vacanition','vbalance')
				em.setId(rs.getInt(1));
				em.setFirstName(rs.getString(2));
				em.settotal(rs.getInt(3));
				em.setAg(rs.getInt(4));
				em.setSalary(rs.getFloat(5));
				em.setDepartment(rs.getString(6));
				em.setVacation(rs.getInt(7));
				em.setVbalance(rs.getInt(8));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return em;
	}
	// start department methods

	public ArrayList<employee> getemploye() {
		ArrayList<employee> list = new ArrayList<employee>();
		String sql = "SELECT * FROM employe order by department  ";
		// PreparedStatement pst = null;

		try {
			con = getConnection_1();

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			// vacation = ;

			while (rs.next()) {

				list.add(new employee(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	public ArrayList<employee> getemployesamedepartment(String departmt) {

		ArrayList<employee> list = new ArrayList<employee>();
		PreparedStatement pst = null;

		try {
			con = getConnection_1();
			String sql = "SELECT * FROM employe WHERE department = '" + departmt + "' ORDER BY id ASC ";
			// con = DBInfo.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new employee(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8)));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<String> getemployesnamedepartment(String departmt) {

		ArrayList<String> list = new ArrayList<String>();

		PreparedStatement pst = null;

		try {
			con = getConnection_1();
			String sql = "SELECT fname FROM employe WHERE department = '" + departmt + "' ORDER BY id ASC ";
			
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				 list.add(rs.getString(1));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Depart> getDepartment() {
		ArrayList<Depart> list = new ArrayList<Depart>();

		try {
			con = getConnection_1();
			String sql = "SELECT * FROM Department ORDER BY department ASC";
			// con = DBInfo.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new Depart(rs.getString(2)));

			}
			con.close();
		} catch (Exception e) {
			e.getMessage();
		}

		return list;
	}

	public int departmentInsert(String dname) {
		int check = 0;
		con = getConnection_1();
		String sql = "insert into Department (department) VALUES (?)";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dname);

			check = ps.executeUpdate();
			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return check;
	}

	public boolean departAlreadyfound(String dname) {

		try {
			con = getConnection_1();
			String sql = "SELECT  department  FROM Department where department = '" + dname + "'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				return true;
			}

			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return false;
	}

	// end department methods
	
	public ArrayList<String> getDays(String name) {

		ArrayList<String> list = new ArrayList<String>();

		String sql = "SELECT ODATE FROM Master where emp_name = '" + name + "'  ORDER BY ODATE ASC";

		try {
			con = getConnection_1();
			// con = DBInfo.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				list.add(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
/*
	public void saveOrder(String date, String name, boolean holiday, boolean go, String intime, String outtime)
			throws SQLException {

		deleteOrder(date, name);
		String status = "";
		if (go) {
			status = "early";
		}

		else if (holiday) {
			status = "absent";
		} else {
			status = "absent";
		}
		int holi = 0;

		if (holiday) {
			holi = 1;
		}

		String sql = "INSERT INTO Master(emp_name,ODATE,holiday,enter_time,go_time) VALUES(?,?,?,?,?)";
		PreparedStatement pst = null;
		try {
			con = getConnection_1();

			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, date);
			pst.setString(3, status);
			pst.setString(4, intime);
			pst.setString(5, outtime);

			pst.executeUpdate();

			// ---------
			con.commit();

			// ----------------
			updateHolidayBalance(name, holiday, go);

		} catch (SQLException e) {
			e.printStackTrace();
			// System.out.println(e.getMessage());
		}
		try {
			con.rollback();
		} catch (SQLException f) {
			System.out.println(f.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
*/

	public void deleteOrder(String sdate, String emp_name) throws SQLException {
		String sql = "DELETE  FROM Master WHERE ODATE=? and emp_name =?";
		try {
			con = getConnection_1();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, sdate);
			pst.setString(2, emp_name);
			pst.executeUpdate();
			con.commit();
			// con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public boolean dayAlreadysaved(String daydate, String name) {

		try {
			con = getConnection_1();
			String sql = "SELECT  ODATE  FROM Master where ODATE = '" + daydate + "' and emp_name = '" + name + "'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				return true;
			}

			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return false;
	}

	// methood to get number of vaction
	
	public int vacationCount(String emp_name,String depart, boolean holiday, boolean go) {
		int count = 0;
		String status = "";
		if (go) {
			status = "early";
		}

		else if (holiday) {
			status = "absent";
		} else {
			status = "absent";
		}
		String sql = "SELECT  count (emp_name)  FROM Details where emp_name = '" + emp_name + "' and department='" + depart + "' and status = '"
				+ status + "'";
		
		//	con = getConnection_1();

		try {
			if(con == null)
				con = getConnection_1();
			
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 

		return count;
	}
	public int vacationCount_conn(String emp_name,String depart, boolean holiday, boolean go) {
		int count = 0;
		String status = "";
		if (go) {
			status = "early";
		}

		else if (holiday) {
			status = "absent";
		} else {
			status = "absent";
		}
		String sql = "SELECT  count (emp_name)  FROM Details where emp_name = '" + emp_name + "' and department='" + depart + "' and status = '"
				+ status + "'";
		
		//	con = getConnection_1();

		try {
			
				con = getConnection_1();
			
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return count;
	}

	public void updateHolidayBalance(String emp_name,String depart , boolean holiday, boolean go) {

		// ***********
		String status = "";
		if (go) {
			status = "early";
		}

		else if (holiday) {
			status = "absent";
		} else {
			status = "absent";
		}
		// ***********
		String sql = "SELECT totaldays FROM employe WHERE fname='" + emp_name + "'and department='" + depart + "'";
		
			
		int newbalnce = 0;
		int vacation = vacationCount(emp_name,depart, holiday, go);
		PreparedStatement pst = null;
		try {
			if(con == null)
				con = getConnection_1();
			if (emp_name.equals("")) {
				return;
			}

			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				newbalnce = rs.getInt(1);

			}
			newbalnce = newbalnce - vacation;

			String sql_1 = "UPDATE employe SET  vacanition = ? , vbalance = ? WHERE fname = ? and department=?";

			pst = con.prepareStatement(sql_1);
			pst.setInt(1, vacation);
			pst.setInt(2, newbalnce);
			pst.setString(3, emp_name);
			pst.setString(4, depart);

			pst.executeUpdate();
			con.commit();

		} catch (SQLException e) {
			e.printStackTrace();

		} 

	}

	public ArrayList<Object[]> viewempDays(String name, boolean holiday, boolean go , String from , String to) {
		ArrayList<Object[]> list = new ArrayList<>();

		String status = "";
		if (go) {
			status = "early";
		}

		else if (holiday) {
			status = "absent";
		} else {
			status = "absent";
		}
		// String sql = "SELECT ODATE , holiday , enter_time , go_time FROM
		// Master WHERE emp_name =? and holiday=? ORDER BY ODATE DESC";
		//SELECT emp_name ,  strftime('%d/%m/%Y', ODATE)as date 
		//FROM Details where emp_name = 'acc3' 
		//and ODATE BETWEEN  '2020-06-10' and '2020-07-14' order by ODATE asc
		String sql = "SELECT strftime('%d/%m/%Y', ODATE) , status , in_time , out_time FROM Details WHERE emp_name =? and status=? and ODATE BETWEEN ? and ? order by ODATE asc";
		try {
			con = getConnection_1();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, status);
			pst.setString(3, from);
			pst.setString(4, to);
			rs = pst.executeQuery();

			while (rs.next()) {
				String odatechange = (String) rs.getObject(1);
				// System.out.println(rs.getDate(1));

				Date datechanger = timeStringtoDate(odatechange);

				SimpleDateFormat formatter = new SimpleDateFormat("EEEEE,dd/MM/yyyy", Locale.getDefault());
				String odatechanged = formatter.format(datechanger);

				String[] comevals = ((String) rs.getObject(3)).split(" ");
				String[] govals = ((String) rs.getObject(4)).split(" ");
				if (go) {

					list.add(new Object[] { odatechanged, status, comevals[1], govals[1] });

				} else if (holiday) {

					list.add(new Object[] { odatechanged, status, "00:00", "00:00" });

				}

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} 
		/*
		 * finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		 */
		return list;
	}

	public static Date timeStringtoDate(String dat) {
		Date d = null;
		String[] datt = dat.split("/");

		Calendar calen = Calendar.getInstance();
		calen.set(Calendar.DAY_OF_MONTH, Integer.parseInt(datt[0]));
		calen.set(Calendar.MONTH, Integer.parseInt(datt[1]) - 1);
		calen.set(Calendar.YEAR, Integer.parseInt(datt[2]));

		d = calen.getTime();
		return d;
	}

	// to get all id num from selected department
	public LinkedList getLastidspesDepart(String name) {
		LinkedList<Integer> lis = new LinkedList<>();
		int num = 0;

		String sql = "select id from employe where department = '" + name + "'";

		try {
			con = getConnection_1();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				lis.add(rs.getInt(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lis;
	}

	public int getLastid(String name) {
		int num = 0;

		String sql = "select id from employe order by id desc";

		try {
			con = getConnection_1();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			num = rs.getInt(1);
			System.out.println(num);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return num;
	}

	  
    public void saveOrder_r(String date,String depart,boolean holiday,String intime,String outtime,JTable table){
        deleteOrder_r(date,depart);
        String holi="";
        
        if(holiday){
            holi="Absent";
        }
            
        PreparedStatement ps=null;
        
       String sql="INSERT INTO Master(department,ODATE,holiday,enter_time,go_time) VALUES(?,?,?,?,?)";
     
       String details= "INSERT INTO Details(ODATE,emp_name,department,status,in_time,out_time,late,early) "+
                        "VALUES(?,?,?,?,?,?,?,?)";

        try {
        	
        	con = getConnection_1();
            ps = con.prepareStatement(sql);
            ps.setString(1,depart);
            ps.setString(2,date);
            ps.setString(3,holi);
            ps.setString(4,intime);
            ps.setString(5,outtime);
            
            ps.executeUpdate();
            // ----------------
            ps = con.prepareStatement(details);
            ps.setString(1,date);
            ps.setString(3,depart);
            for(int i=0;i<table.getRowCount() ;i++){
                
                ps.setString(2,table.getValueAt(i,0)+""); 
                ps.setString(4,table.getValueAt(i,1)+"");
                ps.setString(5,table.getValueAt(i,3)+"");
                ps.setString(6,table.getValueAt(i,4)+"");
                ps.setString(7,table.getValueAt(i,5)+"");
                ps.setString(8,table.getValueAt(i,6)+"");
               
               
                ps.executeUpdate();
                updateHolidayBalance(table.getValueAt(i,0)+"",depart, holiday, false);
                
            }
            con.commit();     
           
            //----------------
           
            
            
            

        }  catch (SQLException e) {
			e.printStackTrace();
			// System.out.println(e.getMessage());
		}
		try {
			con.rollback();
		} catch (SQLException f) {
			System.out.println(f.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

    }
   
    public boolean dayAlreadysaved_r(String daydate,String depart){
    	String sql = "SELECT  ODATE  FROM Master where ODATE = '" + daydate + "' and department ='"+depart + "'";
        
       //String sql= "SELECT datetime(Master.ODATE,'dd/mm/yyyy') FROM Master WHERE ((Master.ODATE)="+ daydate +")";
        
        try{
        	con = getConnection_1();
            stmt = con.createStatement();
            rs=stmt.executeQuery(sql);

            while(rs.next()){                
                return true;
            } 
            
            
        }catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        
        return false;
    }
    
    public void deleteOrder_r(String sdate ,String depart){
    	String sql = "DELETE  FROM Master WHERE ODATE=? and department =?";
    	String details = "DELETE  FROM Details WHERE ODATE=? and department =?";
    	PreparedStatement pst = null;
		try {
			con = getConnection_1();
			pst = con.prepareStatement(sql);
			pst.setString(1, sdate);
			pst.setString(2, depart);


		
			pst.executeUpdate();
			pst = con.prepareStatement(details);
			pst.setString(1, sdate);
			pst.setString(2, depart);
		
			pst.executeUpdate();
			con.commit();
			// con.close();
		}  catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	
    }
    public static void fixID(Connection conn, String table) {

        try {
            Statement myStmt = conn.createStatement();
            ResultSet myRs;
            int i = 1, id = 1, n = 0;
            boolean b;
            String sql;

            myRs = myStmt.executeQuery("select max(id) from " + table);
            if (myRs.next()) {
                n = myRs.getInt(1);
            }
            while (i <= n) {
                b = false;
                myRs = null;
                while (!b) {
                    myRs = myStmt.executeQuery("select id from " + table + " where id=" + id);
                    if (!myRs.next()) {
                        id++;
                    } else {
                        b = true;
                    }
                }

                sql = "UPDATE " + table + " set id =" + i + " WHERE id=" + id;
                myStmt.execute(sql);
                i++;
                id++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
