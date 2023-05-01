
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;
	Connection con = null;
	static DBInfo db = new DBInfo();

	/**
	 * Launch the application.
	 */
	private void master_componentShown(ComponentEvent e) {
	       if(!db.Connect()){           
	            Toolkit.getDefaultToolkit().beep();
	            UIManager.put("OptionPane.okButtonText","�������");
	            JOptionPane.showMessageDialog(frame,"��� ��� ��� �� ������� ������ ��������\n���� ����� ��������.","�������������������",JOptionPane.ERROR_MESSAGE);
	            this_windowClosing(null);//----
	        }
	        
	    }

	private void this_windowClosing(WindowEvent e) {

		// ********************
		db.Disconnect();
		frame.dispose();// exit
		System.exit(0);// to close reports
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					login window = new login();
					window.frame.setVisible(true);
					window.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
					if (window.frame.getDefaultCloseOperation() == 0) {
						window.frame.dispose();
						db.Disconnect();
						// adminDB.getConnection().close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {

				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public login() {
		try {
			initialize();
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, e.getMessage());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {

		
		frame = new JFrame("demo");
		frame.setBounds(100, 100, 450, 345);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		// -------

		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	


		
		
		//-------
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "user login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(63, 40, 304, 200);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(23, 33, 75, 14);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(23, 75, 75, 14);
		panel.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(108, 30, 105, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(108, 72, 105, 20);
		panel.add(passwordField);
		

		lblNewLabel = new JLabel("not Connected");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(121, 266, 88, 14);
		frame.getContentPane().add(lblNewLabel);
		if(adminDB.getConnection() !=null)
		{
			lblNewLabel.setText("connected");
			lblNewLabel.setForeground(Color.GREEN);
		}
		
		
				
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e)  {
			
				
				ArrayList<admins> list = adminDB.getadmin();
				HashMap<String, String> map = new HashMap<String , String>();
				
				for(admins a : list)
				{
					map.put(a.getUser(), a.getPass());
				}
				if(map.containsKey(textField.getText()))
				{
					String s = map.get(textField.getText());
					if(s.equals(passwordField.getText()))
					{
						
						SwingUtilities.invokeLater(new Runnable() {
							
							@Override
							public void run() {
								try {
									
									pan2 p = new pan2();
									p.setLocationRelativeTo(frame);
									p.setVisible(true);
									
									frame.setVisible(false);
									
									  /*
									   * if(!db.Connect()){           
							            Toolkit.getDefaultToolkit().beep();
							            UIManager.put("OptionPane.okButtonText","�������");
							            JOptionPane.showMessageDialog(frame,"��� ��� ��� �� ������� ������ ��������\n���� ����� ��������.","�������������������",JOptionPane.ERROR_MESSAGE);
							            this_windowClosing(null);//----
							        }
									   */
									 
									} catch (Exception ee) {
										// TODO Auto-generated catch block
										ee.printStackTrace();
									}
								
							}
						});
						
					
					
					
					}else
					{
						JOptionPane.showMessageDialog(null, "password wrong " , "err", JOptionPane.ERROR_MESSAGE);
					}
				}else
					JOptionPane.showMessageDialog(null, "username not found" , "err", JOptionPane.ERROR_MESSAGE);
			}
		});
	
		btnNewButton.setBounds(125, 135, 72, 23);
		panel.add(btnNewButton);
		
		JCheckBox chckbxShowPassword = new JCheckBox("show password");
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxShowPassword.isSelected())
				{
					passwordField.setEchoChar('\0');
				}
				else
					passwordField.setEchoChar('*');
			}
		});
		chckbxShowPassword.setBounds(23, 108, 134, 23);
		panel.add(chckbxShowPassword);
		
		JLabel label = new JLabel("");
		label.setBounds(45, 175, 46, 14);
		panel.add(label);
		
		JLabel lblDbstatue = new JLabel("DBstatue :");
		lblDbstatue.setBounds(39, 266, 72, 14);
		frame.getContentPane().add(lblDbstatue);
		

	}

}
