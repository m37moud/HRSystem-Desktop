
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.DateFormatter;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import java.awt.Window.Type;
import java.awt.Dimension;

public class DialoAddDepartment extends JDialog {
	private JButton ok = new JButton("ok");
	private JTextField tf = new JTextField();
	private DBInfo db = new DBInfo();
	private final JSeparator separator = new JSeparator();
	private final JPanel panel = new JPanel();

	public DialoAddDepartment() {
		setType(Type.POPUP);
		setSize(new Dimension(250, 78));
		setResizable(false);
		init();
	}

	public void init() {
		this.setTitle("Insert New Department");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		panel.setBounds(0, 0, 244, 50);

		getContentPane().add(panel);
		panel.setLayout(null);
		ok.setBounds(0, 27, 244, 23);
		panel.add(ok);
		tf.setBounds(0, 0, 244, 20);
		tf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e)
			{
				id_keypress(e);
			}
		});
		
		panel.add(tf);
		separator.setBounds(0, 23, 244, 10);
		panel.add(separator);
		ok.setEnabled(false);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				save(e);

			}
		});

	}

	private void save(ActionEvent e) {
		
		if (tf.getText().trim().length()>=5){
			ok.setEnabled(true);
		}
		if (!tf.getText().trim().equals("")) {

			String name = tf.getText().toLowerCase();
			if (db.departAlreadyfound(name)) {

				UIManager.put("OptionPane.yesButtonText", "äÚã");
				UIManager.put("OptionPane.noButtonText", "áÇ");
				Toolkit.getDefaultToolkit().beep();

				if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null,
						" depart ( " + name + " ) already created \n do you want to add new one ?", "öAlert",
						JOptionPane.YES_NO_OPTION)) {
					this.dispose();
					return;
					// **********
				} else {
					tf.setText("");
					return;
				}

			}
			int check = db.departmentInsert(name);

			if (check > 0) {

				if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(this,
						" Department ( " + tf.getText() + " ) save sucsseful  \n do you want to add new one ? ",
						"öAlert", JOptionPane.YES_NO_OPTION)) {
					this.dispose();

					// *********
				}

			} else {
				JOptionPane.showMessageDialog(null, "error ecourd in save", "Error ", JOptionPane.ERROR_MESSAGE);
			}

		} else
			JOptionPane.showMessageDialog(this, "please insert proper Depart name", "Error ", JOptionPane.ERROR_MESSAGE);
	}
	private void id_keypress(KeyEvent e)
	{
		char key =e.getKeyChar();
		if(((JTextField)e.getSource()).getText().length()>=5)
		{
			ok.setEnabled(true);;
		}
	}
	

	public static void main(String[] args) {

		DialoAddDepartment dt = new DialoAddDepartment();
		dt.setLocationRelativeTo(null);
		dt.setVisible(true);

	}
}
