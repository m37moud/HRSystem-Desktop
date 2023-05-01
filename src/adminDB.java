

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class adminDB {
	
	static Connection con = null;
	
	public static Connection getConnection()
	{
		
			try {
				Class.forName("org.sqlite.JDBC");
				String url = "jdbc:sqlite:D:\\D\\user.sqlite";
				con = DriverManager.getConnection(url);
				//System.out.println("connected");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		
		
		return con;
	}
	
	public static ArrayList<admins> getadmin()
	{
		ArrayList<admins> list = new ArrayList<admins>();
		try
		{
			String sql = "SELECT * FROM user where 1";
			Connection con = new adminDB().getConnection();
			;
			Statement st= con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				admins adm = new admins();
				//adm.setId(rs.getInt(1));
				adm.setUser(rs.getString(2));
				adm.setPass(rs.getString(3));
				list.add(adm);
			}
			con.close();
		}catch(SQLException e )
		{
			e.printStackTrace();
		}
		return list;
	}
	

}
