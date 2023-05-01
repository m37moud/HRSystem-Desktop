

public class admins {
	private int id;
	private String user;
	private String pass;
	
	public admins()
	{
		
	}
	public admins(int pID , String pUser , String pPass)
	{
		this .id = pID;
		this.user = pUser;
		this.pass = pPass;
	}
	
	public void setId(int id )
	{
		 this.id = id;
	}
	public int getId()
	{
		return this.id;
	}
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public void setUser(String us)
	{
		this.user = us;
	}
	public String getUser()
	{
		return user;
	}
	
	

}
