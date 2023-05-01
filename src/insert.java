import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ListSelectionModel;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import java.awt.Cursor;

public class insert extends JDialog implements ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static DBInfo db = new DBInfo();
	Connection con = null;
	private final JPanel contentPanel = new JPanel();
	private JButton save = new JButton("save day");
	private JPanel panel = new JPanel();
	private JPanel panel_1 = new JPanel();
	private JTable jTable1 = new JTable();
	/////////////*************
	private JRadioButton holiday = new JRadioButton("holiday");
	private JRadioButton go = new JRadioButton("daysearlygo");

	/////*********************
	Calendar calen = Calendar.getInstance();
	SimpleDateFormat formatter = null;
	private JSpinner date = new JSpinner();
	
	//private JDateChooser date = new JDateChooser();
	
	
	private JSpinner fromt = new JSpinner();
	private JSpinner endt = new JSpinner();
	private JSpinner tospinner = new JSpinner();
	private Date finaldate = null;
	private JTextField numofdaysremain;
	private final JScrollPane scrollPane = new JScrollPane();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		 Locale.setDefault(new Locale("ar","EG"));
	        
	        System.setProperty("file.encoding","windows-1256");// Cp1256
	        System.setProperty("sun.jnu.encoding","Cp1256");
		insert i = new insert();
		i.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		i.setLocationRelativeTo(null);
		i.setVisible(true);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
	/*
		Date datechanger = db.settime("02/06/2020");
		String[] datt = "02/06/2020".split("/");	
		System.out.println(Arrays.toString(datt));
		System.out.println("02/06/2020");
		System.out.println(formatter.format(datechanger));
		*/
		
	}

	/**
	 * Create the dialog.
	 */
	public insert() {
		setResizable(false);

		// -----------------------------------------
		calen = Calendar.getInstance();
		calen.set(Calendar.HOUR_OF_DAY, 0);
		calen.set(Calendar.MINUTE, 0);
		calen.set(Calendar.SECOND, 0);
		calen.set(Calendar.MILLISECOND, 0);
		this.finaldate = calen.getTime();
		// ------------------------------------------
		setupModel();
		spinnersmod();
		setSpinnerval();
		formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());

		// ------------------------------------------

		
		setBounds(100, 100, 772, 490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(666, 11, -654, 134);
		contentPanel.add(panel);
		panel.setLayout(null);

		date.setBounds(62, 29, 199, 34);
		date.setBackground(Color.white);
		date.setToolTipText("select up or down to chose date ");
		date.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		// panel.add(date);
		contentPanel.add(date);

		// panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(20, 164, 736, 232);

		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Date");
		model.addColumn("Status");
		model.addColumn("arraive time");
		model.addColumn("arraive time");
		scrollPane.setBounds(10, 24, 716, 197);

		panel_1.add(scrollPane);

		
		jTable1.setModel(model);
		scrollPane.setViewportView(jTable1);
		jTable1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		jTable1.setRowHeight(23);
		jTable1.setFont(new Font("Tahoma", Font.BOLD, 12));
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable1.setFillsViewportHeight(true);

		///////////

		jTable1.setFillsViewportHeight(true);
		jTable1.setCellSelectionEnabled(true);

		jTable1.setEnabled(false);
		jTable1.setBackground(UIManager.getColor("Button.highlight"));
		jTable1.setSurrendersFocusOnKeystroke(true);
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		jTable1.setColumnSelectionAllowed(true);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable1.setRowHeight(25);

		jTable1.setRowHeight(23);
		jTable1.setFont(new Font("Tahoma", Font.BOLD, 12));
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		


		JTableHeader renderer = jTable1.getTableHeader();
		renderer.setBackground(Color.lightGray);
		renderer.setFont(new Font("Tahoma", Font.BOLD, 14));
		renderer.setForeground(Color.BLUE);
		renderer.setPreferredSize(new Dimension(renderer.getSize().width, 30));
		renderer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		DefaultTableCellRenderer crend = new DefaultTableCellRenderer();
		crend.setHorizontalAlignment(JLabel.CENTER);
		

		TableColumn column = null;

		for (int i = 0; i < jTable1.getColumnCount(); i++) {
			column = jTable1.getColumnModel().getColumn(i);
			if (i == 0) {
				crend.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);
				column.setCellRenderer(crend);
				column.setPreferredWidth(200); // third column is bigger
			}
			
			else {
				column.setPreferredWidth(50);
				column.setCellRenderer(crend);
			}

		}

		holiday.setBounds(278, 30, 68, 34);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(holiday);
		bg.add(go);
		
		holiday.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if (holiday.isSelected()) {
		 			save.setEnabled(true);
				}
		 		
				else if (holiday.isSelected() && go.isSelected()) {
					JOptionPane.showMessageDialog(null, "please select one option action to do ");
					save.setEnabled(false);
				} else
					JOptionPane.showMessageDialog(null, "please check action to do ");
		 		
		 	}
		 });
		
		
		go.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if (go.isSelected()) {
					save.setEnabled(true);
				}
				else if (holiday.isSelected() && go.isSelected()) {
					save.setEnabled(false);
					JOptionPane.showMessageDialog(null, "please select one option action to do ");
					
				} else if(!go.isSelected() || !go.isSelected()){
					save.setEnabled(false);
					JOptionPane.showMessageDialog(null, "please check action to do ");
				}
		 	}
		 });
		
		

		contentPanel.add(holiday);

		JButton viewdays = new JButton("view days");
	//	viewdays.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewdays.setForeground(Color.BLACK);
		
		/*
		 * viewdays.setUI(new BasicButtonUI() {
		});
		 */
		viewdays.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
				viewdays.setForeground(Color.BLACK);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				viewdays.setForeground(Color.blue);
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
			}
		});
		viewdays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				DefaultTableModel mod = (DefaultTableModel) jTable1.getModel();
				mod.setRowCount(0);
				
				
				SimpleDateFormat timeform = new SimpleDateFormat("YYYY-MM-dd");
			
				System.out.println(timeform.format((Date) date.getValue()));
				for (Object[] data : db.viewempDays(getName(), holiday.isSelected(), go.isSelected(),timeform.format((Date) date.getValue()) , timeform.format((Date) tospinner.getValue()))) {
					
					mod.addRow(data);
				}
				//mod.addRow(data);
				//int dayscount = db.vacationCount(getName(), holiday.isSelected(), go.isSelected());
				int dayscount = mod.getRowCount();
				if(dayscount > 0){
				numofdaysremain.setText(String.valueOf(dayscount));
				}
				else{
					if(holiday.isSelected()){
					JOptionPane.showMessageDialog(jTable1, "there is no absent day to this  " + getName() + "" );
					}else{
						numofdaysremain.setText(String.valueOf(dayscount));
						JOptionPane.showMessageDialog(jTable1, "there is no late time to this  " + getName() + "" );

						
					}
					}
			}
		});
		viewdays.setBounds(22, 108, 106, 23);
		contentPanel.add(viewdays);

		JLabel lblDaysRemain = new JLabel("Number of days");
		lblDaysRemain.setBounds(138, 112, 121, 14);
		contentPanel.add(lblDaysRemain);

		numofdaysremain = new JTextField();
		numofdaysremain.setForeground(Color.BLACK);
		numofdaysremain.setFont(new Font("Tahoma", Font.BOLD, 16));
		numofdaysremain.setBounds(289, 109, 86, 20);
		contentPanel.add(numofdaysremain);
		numofdaysremain.setColumns(10);
		numofdaysremain.setEditable(false);

		go.setBounds(386, 30, 86, 34);
		contentPanel.add(go);

		// JSpinner fromt = new JSpinner();
		fromt.setBounds(439, 11, 61, 34);
		fromt.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		fromt.addChangeListener(this);
	//	contentPanel.add(fromt);

		// JSpinner endt = new JSpinner();
		endt.setBounds(554, 11, 61, 34);
		endt.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		endt.addChangeListener(this);
		//contentPanel.add(endt);
		
		
		tospinner.setToolTipText("????? ?????? ??????? ?????? ");
		tospinner.setFont(new Font("Tahoma", Font.BOLD, 13));
		tospinner.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		tospinner.setBackground(Color.WHITE);
		tospinner.setBounds(557, 29, 199, 34);
		contentPanel.add(tospinner);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(517, 51, -31, -25);
		contentPanel.add(layeredPane);
		
		JLabel lblNewLabel = new JLabel("to");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(500, 29, 30, 34);
		contentPanel.add(lblNewLabel);
		
		JLabel lblFrom = new JLabel("from");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFrom.setBounds(10, 29, 42, 34);
		contentPanel.add(lblFrom);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{

				save.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {

							
							save_actionPerformed(e);
							if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null,
									"?? ????? ???????? ???? ?????  ?????  \n?? ???? ????? ??? ???  ?",
									"??????????????????", JOptionPane.YES_NO_OPTION)) {
								dispose();
								// return; // **********

							}

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
				save.setActionCommand("OK");
				
				save.setEnabled(false);
				
				buttonPane.add(save);
				getRootPane().setDefaultButton(save);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void setupModel() {

		calen = Calendar.getInstance();
		
		calen.set(Calendar.DAY_OF_MONTH,20);
		calen.set(Calendar.MONTH,calen.get(Calendar.MONTH)-1);
		Date initdate = calen.getTime();
		
		calen.add(Calendar.YEAR, -100);
		Date earlestd = calen.getTime();
		calen.add(Calendar.YEAR, 200);
		Date latestd = calen.getTime();
		calen.set(Calendar.DAY_OF_MONTH,20);
		SpinnerModel smodel = new SpinnerDateModel(initdate, earlestd, latestd, Calendar.YEAR);
		
		
		this.date.setModel(smodel);
		this.date.setEditor(new JSpinner.DateEditor(date, "dd/MM/yyyy - EEEEE"));
		
		

		JComponent deditor = date.getEditor();
		JTextField dfield = ((JSpinner.DefaultEditor) deditor).getTextField();
		dfield.setHorizontalAlignment(JTextField.LEFT);
		dfield.setForeground(new Color(198, 0, 0));

		this.date.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.date.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		//
		
		SpinnerModel tomodel = new SpinnerDateModel(initdate, earlestd, latestd, Calendar.YEAR);
		
		this.tospinner.setModel(tomodel);
		this.tospinner.setEditor(new JSpinner.DateEditor(tospinner, "dd/MM/yyyy - EEEEE"));

		JComponent toditor = tospinner.getEditor();
		JTextField tofield = ((JSpinner.DefaultEditor) toditor).getTextField();
		tofield.setHorizontalAlignment(JTextField.LEFT);
		tofield.setForeground(new Color(198, 0, 0));
		
		this.tospinner.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.tospinner.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		//
		
		 calen=Calendar.getInstance();
	        int lastday=this.getLastDay(calen.get(Calendar.MONTH),calen.get(Calendar.YEAR));
	        calen.set(Calendar.DAY_OF_MONTH,21);
		this.tospinner.setValue(calen.getTime());

	}

	private void setSpinnerval() {

		calen = Calendar.getInstance();
		calen.setTime(this.finaldate);

		calen.set(Calendar.HOUR_OF_DAY, 8);
		
		this.fromt.setValue(calen.getTime());

		calen.set(Calendar.HOUR_OF_DAY, 4);
		this.endt.setValue(calen.getTime());
	}

	private void spinnersmod() {

		calen = Calendar.getInstance();
		Date initdate = calen.getTime();
		calen.add(Calendar.YEAR, -100);
		Date earlestd = calen.getTime();
		calen.add(Calendar.YEAR, 200);
		Date latestd = calen.getTime();

		SpinnerModel smodel = new SpinnerDateModel(initdate, earlestd, latestd, Calendar.YEAR);
		this.fromt.setModel(smodel);
		this.fromt.setEditor(new JSpinner.DateEditor(fromt, "HH:mm"));

		JComponent deditor = fromt.getEditor();
		JTextField dfield = ((JSpinner.DefaultEditor) deditor).getTextField();
		dfield.setHorizontalAlignment(JTextField.CENTER);
		dfield.setForeground(new Color(198, 0, 0));

		this.fromt.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.fromt.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		// ---------------------------------------------------------------------------------------

		SpinnerModel emodel = new SpinnerDateModel(initdate, earlestd, latestd, Calendar.YEAR);
		this.endt.setModel(emodel);
		this.endt.setEditor(new JSpinner.DateEditor(endt, "HH:mm"));

		JComponent editor = endt.getEditor();
		JTextField efield = ((JSpinner.DefaultEditor) editor).getTextField();
		efield.setHorizontalAlignment(JTextField.CENTER);
		efield.setForeground(new Color(198, 0, 0));

		this.endt.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.endt.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		// -------------------------------------------------------------------------------------

	}

	private void this_windowClosing(WindowEvent e) {

		UIManager.put("OptionPane.yesButtonText", "yes");
		UIManager.put("OptionPane.noButtonText", "no");

		if (this.save.isEnabled()) {
			Toolkit.getDefaultToolkit().beep();
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "?? ???? ?????? ???? ??? ????????? ?",
					"????? ???????", JOptionPane.YES_NO_OPTION)) {
				this.dispose();
			}
		} else {
			this.dispose();
		}
	}

	private void save_actionPerformed(ActionEvent e) throws SQLException {
		
		// old formate **  formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		formatter = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
		SimpleDateFormat timeform = new SimpleDateFormat("YYYY-MM-dd HH:MM", Locale.getDefault());

		if (db.dayAlreadysaved(formatter.format((Date) this.date.getValue()), this.getName())) {
			// System.out.println(formatter.format((Date)this.date.getValue()));

			UIManager.put("OptionPane.yesButtonText", "???");
			UIManager.put("OptionPane.noButtonText", "??");

			Toolkit.getDefaultToolkit().beep();
			if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(this,
					"??? ??????? ?? ????? ??????? ?? ??? \n?? ???? ????????? ???? ????????", "??????????????????",
					JOptionPane.YES_NO_OPTION)) {
				return;// **********
			}

		}

		// -----------------------------------
		formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm", Locale.getDefault());
		if (this.holiday.isSelected() || this.go.isSelected()) {
			save.setEnabled(true);
			/*
			db.saveOrder_r(formatter.format((Date) this.date.getValue()),"", this.getName(), this.holiday.isSelected(),
					this.go.isSelected(), timeform.format((Date) this.fromt.getValue()),
					timeform.format((Date) this.endt.getValue()));
			*/
			int dayscount = db.vacationCount(getTitle(),"", holiday.isSelected(),
					go.isSelected());
			
			
			numofdaysremain.setText(String.valueOf(dayscount));
		}
		else if (this.holiday.isSelected() && this.go.isSelected()) {
			JOptionPane.showMessageDialog(null, "please select one option action to do ");
			this.save.setEnabled(false);
		} else
			JOptionPane.showMessageDialog(null, "please check action to do ");
	}
	 private int getLastDay(int month,int year){
	        month++;// ********
	        int day=28;
	        
	        if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
	            day = 31;
	        }else if(month==4||month==6||month==9||month==11){
	            day = 30;
	        }else if(month==2 && year%4==0){
	            day = 29;
	        }
	        
	        return day;
	    }
	@Override
	public void stateChanged(ChangeEvent e) {
		

	}
}
