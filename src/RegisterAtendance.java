
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import java.io.File;

import java.net.URL;
import java.nio.Buffer;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

public class RegisterAtendance extends JDialog implements ChangeListener {

	static DBInfo db = new DBInfo();
	private JPanel jPanel1 = new JPanel();
	private BorderLayout borderLayout1 = new BorderLayout();
	private JScrollPane jScrollPane1 = new JScrollPane();
	private JTable jTable1 = new JTable();
	private JLabel jLabel1 = new JLabel();
	//private JSpinner date = new JSpinner();
	private JDateChooser date = new JDateChooser();
	
	private JLabel jLabel2 = new JLabel();
	private JSpinner fromt = new JSpinner();
	private JComboBox<String> comb = new JComboBox<>();
	Calendar calen = Calendar.getInstance();
	SimpleDateFormat formatter = null;
	private JSpinner endt = new JSpinner();
	private JLabel jLabel3 = new JLabel();
	private Date firstdat = null;
	private Date lastdat = null;
	private String timeroll = null;
	
	private JButton save = new JButton();
	private JButton exit = new JButton();
	private JSeparator jSeparator1 = new JSeparator();
	private JSeparator jSeparator2 = new JSeparator();
	private int cou = 0;
	private boolean showed = false;
	private Date finaldate = null;
	private JSeparator jSeparator3 = new JSeparator();
	private JCheckBox holiday = new JCheckBox();
	private int daycheck;

	public RegisterAtendance(String name) {
		// -----------------------------------------
		calen = Calendar.getInstance();
		calen.set(Calendar.HOUR_OF_DAY, 0);
		calen.set(Calendar.MINUTE, 0);
		calen.set(Calendar.SECOND, 0);
		calen.set(Calendar.MILLISECOND, 0);
		this.finaldate = calen.getTime();
		this.setName(name);
		// ------------------------------------------

		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setResizable(false);
		setupModel();// date format
		setSpinnerval();// --------
		formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
	}

	private void jbInit() throws Exception {

		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setImage();// ---------------

		// this.setName("aud");

		this.getContentPane().setLayout(borderLayout1);
		this.setSize(new Dimension(919, 492));
		this.setModal(true);

		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				this_componentShown(e);
			}
		});
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				this_windowClosing(e);
			}
		});
		jPanel1.setSize(new Dimension(870, 60));
		jPanel1.setLayout(null);
		jPanel1.setPreferredSize(new Dimension(870, 60));
		jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		jTable1.setAutoCreateRowSorter(true);
		jTable1.setFillsViewportHeight(true);
		jTable1.setSelectionForeground(new Color(231, 0, 0));
		jTable1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		jTable1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jTable1_mouseClicked(e);
			}
		});
		jLabel1.setText("Date");
		jLabel1.setBounds(new Rectangle(10, 17, 32, 25));
		jLabel1.setFont(new Font("Tahoma", 1, 14));
		jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		date.setBounds(new Rectangle(52, 15, 172, 30));
		date.setBackground(Color.white);
		date.setToolTipText("chose day to register ");
		date.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		date.addInputMethodListener(new InputMethodListener() {
			
			@Override
			public void inputMethodTextChanged(InputMethodEvent event) {
				save.setEnabled(true);
				
			}
			
			@Override
			public void caretPositionChanged(InputMethodEvent event) {
				save.setEnabled(true);
				
			}
		});
		

		jLabel2.setText("come time");
		jLabel2.setBounds(new Rectangle(326, 20, 72, 20));
		jLabel2.setFont(new Font("Tahoma", 1, 12));
		jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		fromt.setBounds(new Rectangle(408, 15, 85, 30));
		fromt.setSize(new Dimension(85, 30));
		fromt.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		fromt.addChangeListener(this);
		endt.setBounds(new Rectangle(567, 15, 85, 30));
		endt.setSize(new Dimension(85, 30));
		endt.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		//comb.setBounds(249, 15, 72, 26);
		comb.setBounds(new Rectangle(249, 15, 85, 30));
		comb.setSize(new Dimension(85, 30));
		comb.addItem("first");
		comb.addItem("second");
		comb.addItem("third");
		comb.setFont(new Font("Tahoma", Font.BOLD, 13));
		comb.setForeground(Color.red);
		comb.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		comb.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
	this.comb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				int Dpart = comb.getSelectedIndex();
			
				ComboBoxModel<String> model = comb.getModel();
				//timeroll = model.getElementAt(Dpart); 
				timeroll = (String) comb.getSelectedItem();
				if(timeroll.equals("first"))setSpinnerval(); // restart table
				if(timeroll.equals("second"))setSpinnerval_2(); // restart table
				if(timeroll.equals("third"))setSpinnerval_3(); // restart table

				int rows = jTable1.getRowCount();

				for (int i = 0; i < rows; i++) {
					jTable1.setValueAt("Attend", i, 1);
					jTable1.setValueAt(timeroll, i, 2);
				}
				
				
			}
		});
		
		
		endt.addChangeListener(this);
		jLabel3.setText("go time");
		jLabel3.setBounds(new Rectangle(503, 20, 54, 20));
		jLabel3.setFont(new Font("Tahoma", 1, 12));
		jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
		save.setText("Save");
		save.setBounds(new Rectangle(767, 13, 63, 35));
		save.setToolTipText("register attendant and absent");
		save.setFont(new Font("Tahoma", 1, 12));
		save.setForeground(new Color(0, 49, 148));
		save.setFocusPainted(false);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save_actionPerformed(e);
			}
		});
		exit.setText("Exit");
		exit.setBounds(new Rectangle(840, 13, 63, 35));
		exit.setFont(new Font("Tahoma", 1, 12));
		exit.setForeground(new Color(0, 49, 148));
		exit.setFocusPainted(false);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit_actionPerformed(e);
			}
		});
		jSeparator1.setBounds(new Rectangle(234, 0, 5, 60));
		jSeparator1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		jSeparator1.setOpaque(true);
		jSeparator2.setBounds(new Rectangle(749, 0, 5, 60));
		jSeparator2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		jSeparator2.setOpaque(true);
		jSeparator3.setBounds(new Rectangle(662, 0, 5, 60));
		jSeparator3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		jSeparator3.setOpaque(true);
		holiday.setText("absent");
		holiday.setBounds(new Rectangle(676, 15, 63, 30));
		holiday.setFont(new Font("Tahoma", 1, 12));
		holiday.setHorizontalAlignment(SwingConstants.LEFT);
		holiday.setHorizontalTextPosition(SwingConstants.RIGHT);
		holiday.setFocusPainted(false);
		holiday.setForeground(new Color(198, 0, 0));
		holiday.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		holiday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				holiday_actionPerformed(e);
			}
		});
		jPanel1.add(holiday, null);
		jPanel1.add(jSeparator3, null);
		jPanel1.add(jSeparator2, null);
		jPanel1.add(jSeparator1, null);
		jPanel1.add(exit, null);
		jPanel1.add(save, null);
		jPanel1.add(jLabel3, null);
		jPanel1.add(endt, null);
		jPanel1.add(fromt, null);
		jPanel1.add(comb, null);
		
		jPanel1.add(jLabel2, null);
		jPanel1.add(date, null);
		jPanel1.add(jLabel1, null);
		this.getContentPane().add(jPanel1, BorderLayout.NORTH);
		
		
		
		jScrollPane1.setViewportView(jTable1);
		this.getContentPane().add(jScrollPane1, BorderLayout.CENTER);
		alterTable();// -----------
	}

	private void alterTable() {
		spinnersmod();// important arrange

		String depart = (String) getName();

		ArrayList<String> list = db.getemployesnamedepartment(depart);

		jTable1.setModel(new tableModel(list, (Date) this.fromt.getValue(), (Date) this.endt.getValue()));

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

		JComboBox combo = new JComboBox();
		combo.addItem("Attend");
		combo.addItem("absent");
		 combo.addItem("vacation");
		// combo.addItem("ÛíÇÈ ãÑÖì");
		combo.setFont(new Font("Tahoma", Font.BOLD, 13));
		combo.setForeground(Color.magenta);
		combo.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		JComboBox co = new JComboBox();
		co.addItem("first");
		co.addItem("second");
		co.addItem("third");
		
		co.setFont(new Font("Tahoma", Font.BOLD, 13));
		co.setForeground(Color.red);
		co.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		TableColumn column = null;
		for (int i = 0; i < jTable1.getColumnCount(); i++) {
			column = jTable1.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(250); // third column is bigger
			} else if (i == 1) {
				column.setCellEditor(new DefaultCellEditor(combo));
				DefaultTableCellRenderer comr = new DefaultTableCellRenderer();
				comr.setHorizontalAlignment(JLabel.CENTER);
				comr.setToolTipText("Click for combo box");
				column.setCellRenderer(comr);
				column.setPreferredWidth(80);
				
			}else if (i == 2) {
				column.setCellEditor(new DefaultCellEditor(co));
				DefaultTableCellRenderer comr = new DefaultTableCellRenderer();
				comr.setHorizontalAlignment(JLabel.CENTER);
				comr.setToolTipText("Click for combo box");
				column.setCellRenderer(comr);
				column.setPreferredWidth(80);
				
			}
			else {
				column.setPreferredWidth(65);
				column.setCellRenderer(crend);
			}

		}

	}

	private void setupModel() {

		calen = Calendar.getInstance();
		Date initdate = calen.getTime();
		calen.add(Calendar.YEAR, -100);
		Date earlestd = calen.getTime();
		calen.add(Calendar.YEAR, 200);
		Date latestd = calen.getTime();

		SpinnerModel smodel = new SpinnerDateModel(initdate, earlestd, latestd, Calendar.YEAR);
		//this.date.setModel(smodel);
	
		date.setDateFormatString("EEEEE dd/MM/yyyy");
	//	this.date.setEditor(new JSpinner.DateEditor(date, "EEEEE dd/MM/yyyy"));

		//JComponent deditor = date.getEditor();
	//	JTextField dfield = ((JSpinner.DefaultEditor) deditor).getTextField();
		//dfield.setHorizontalAlignment(JTextField.RIGHT);
		this.date.setDate(initdate);
		this.date.setAlignmentY(date.RIGHT_ALIGNMENT);
		//dfield.setForeground(new Color(198, 0, 0));
		this.date.setForeground(new Color(198, 0, 0));

		this.date.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.date.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

	}

	private void setSpinnerval() {

		calen = Calendar.getInstance();
		calen.setTime(this.finaldate);

		calen.set(Calendar.HOUR_OF_DAY, 8);
		this.fromt.setValue(calen.getTime());

		calen.set(Calendar.HOUR_OF_DAY, 4);

		this.endt.setValue(calen.getTime());
		
	}
	private void setSpinnerval_2() {

		calen = Calendar.getInstance();
		calen.setTime(this.finaldate);

		calen.set(Calendar.HOUR_OF_DAY, 4);
		this.fromt.setValue(calen.getTime());

		calen.set(Calendar.HOUR_OF_DAY, 11);

		this.endt.setValue(calen.getTime());
		
	}
	private void setSpinnerval_3() {

		calen = Calendar.getInstance();
		calen.setTime(this.finaldate);

		calen.set(Calendar.HOUR_OF_DAY, 11);
		this.fromt.setValue(calen.getTime());

		calen.set(Calendar.HOUR_OF_DAY, 8);

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

	private void exit_actionPerformed(ActionEvent e) {

		this_windowClosing(null);

	}

	private void jTable1_mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {// double click

			Point po = e.getPoint();
			int rowi = jTable1.rowAtPoint(po);
			int coli = jTable1.columnAtPoint(po);

			if (coli == 3 && rowi >= 0 && rowi < jTable1.getRowCount()) {
				Toolkit.getDefaultToolkit().beep();
				this.showDialog(po, rowi, coli, "Attend time");
			} else if (coli == 4 && rowi >= 0 && rowi < jTable1.getRowCount()) {
				Toolkit.getDefaultToolkit().beep();
				this.showDialog(po, rowi, coli, "go time");
			}

		}
	}

	private void showDialog(Point point, int rowindex, int columnindex, String title) {

		String value = jTable1.getValueAt(rowindex, columnindex) + "";
		String[] parts = value.split(":");
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.finaldate);
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[0]));
		cal.set(Calendar.MINUTE, Integer.parseInt(parts[1]));

		Editor dt = new Editor(null, title, true, cal.getTime());
		dt.setLocation(point.x - 15, point.y + 100);
		dt.setVisible(true);

		if (dt.agree) {
			if ((jTable1.getValueAt(rowindex, 1) + "").equals("Attend")) {
				jTable1.setValueAt(dt.getValue(), rowindex, columnindex);
				changeTotal();// calculate
			}

		}

	}
	 private void changeTotal(){
	        
	        formatter = new SimpleDateFormat("HH:mm",Locale.getDefault());
	        String status = "";
	        
	        calen=Calendar.getInstance();
	        
	        calen.setTime((Date)this.fromt.getValue());
	        Date indate=calen.getTime();
	        int inhours = calen.get(Calendar.HOUR_OF_DAY);
	        int inmints = calen.get(Calendar.MINUTE);
	        
	        calen.setTime((Date)this.endt.getValue());       
	        Date outdate=calen.getTime();
	        int ouhours = calen.get(Calendar.HOUR_OF_DAY);
	        int oumints = calen.get(Calendar.MINUTE);
	        
	// ----------------------------------------------------        
	        String comed="";
	        String god="";
	        
	        String[] comevals = null;
	        String[] govals = null;
	        
	        int comehours=0;
	        int comemints=0;
	        
	        int gohours=0;
	        int gomints=0;
	        
	        int newhh=0;
	        int newmm=0;
	        
	        Date incold=null;
	        Date oucold=null;
	// -----------------------------------------------------
	        
	        Date result=calen.getTime();
	        for(cou =0;cou < jTable1.getRowCount(); cou++){
	            
	            status = jTable1.getValueAt(cou,1)+""; // *******
	            
	            calen.setTime(indate);           
	            comed = jTable1.getValueAt(cou,3)+""; 
	            comevals = comed.split(":");
	            calen.set(Calendar.HOUR_OF_DAY,Integer.parseInt(comevals[0]));
	            calen.set(Calendar.MINUTE,Integer.parseInt(comevals[1]));
	            incold=calen.getTime();
	            comehours = calen.get(Calendar.HOUR_OF_DAY);
	            comemints = calen.get(Calendar.MINUTE);
	    // -------------------------------------------
	            calen.setTime(outdate);
	            god = jTable1.getValueAt(cou,4)+""; 
	            govals = god.split(":");
	            calen.set(Calendar.HOUR_OF_DAY,Integer.parseInt(govals[0]));
	            calen.set(Calendar.MINUTE,Integer.parseInt(govals[1]));
	            oucold=calen.getTime();
	            gohours = calen.get(Calendar.HOUR_OF_DAY);
	            gomints = calen.get(Calendar.MINUTE);
	    // -------------------------------------------
	            
	            if(incold.after(indate) && oucold.after(outdate) && status.equals("Attend")){ // 1 > a && 2 > b 
	                calen.setTime(incold);
	                calen.add(Calendar.HOUR_OF_DAY , -inhours);
	                calen.add(Calendar.MINUTE , -inmints);

	                jTable1.setValueAt(formatter.format(calen.getTime()),cou,5); // fill minus col
	         // -------------------------------2
	                calen.setTime(oucold);         
	                calen.add(Calendar.HOUR_OF_DAY , -ouhours);
	                calen.add(Calendar.MINUTE , -oumints);
	                
	                jTable1.setValueAt(formatter.format(calen.getTime()),cou,6); // fill plus col
	                
	            }else if(incold.after(indate) && oucold.equals(outdate) && status.equals("Attend")){ // 1 > a && 2==b
	                
	                calen.setTime(incold);
	                calen.add(Calendar.HOUR_OF_DAY , -inhours);
	                calen.add(Calendar.MINUTE , -inmints);

	                jTable1.setValueAt(formatter.format(calen.getTime()),cou,5); // fill minus col
	                // ------------------------------- 2
	                
	                jTable1.setValueAt("0:00",cou,6); // fill plus col
	                
	            }else if(incold.after(indate) && oucold.before(outdate) && status.equals("Attend")){ // 1 > a && 2 < b          
	                
	                calen.setTime(outdate);
	                calen.add(Calendar.HOUR_OF_DAY , -gohours);
	                calen.add(Calendar.MINUTE , -gomints);
	                newhh=calen.get(Calendar.HOUR_OF_DAY);
	                newmm=calen.get(Calendar.MINUTE);
	               // -------------------------------- 1
	                
	                calen.setTime(incold);
	                calen.add(Calendar.HOUR_OF_DAY , -inhours);
	                calen.add(Calendar.MINUTE , -inmints);
	               // ------------------------------- 2
	                calen.add(Calendar.HOUR_OF_DAY , newhh);
	                calen.add(Calendar.MINUTE , newmm);
	               // ------------------------------- 1+2
	                jTable1.setValueAt(formatter.format(calen.getTime()),cou,5); // fill minus col
	               
	                jTable1.setValueAt("0:00",cou,6); // fill plus col
	                
	            }else if(incold.before(indate) && oucold.after(outdate) && status.equals("Attend"))
	            { // 1 < a && 2 > b
	            	
	                
	                calen.setTime(indate);
	                calen.add(Calendar.HOUR_OF_DAY , -comehours);
	                calen.add(Calendar.MINUTE , -comemints);
	                newhh=calen.get(Calendar.HOUR_OF_DAY);
	                newmm=calen.get(Calendar.MINUTE);
	                // -------------------------------
	                
	                calen.setTime(oucold);
	                calen.add(Calendar.HOUR_OF_DAY , -ouhours);
	                calen.add(Calendar.MINUTE , -oumints);
	                calen.add(Calendar.HOUR_OF_DAY,newhh);
	                calen.add(Calendar.MINUTE,newmm);
	                
	                jTable1.setValueAt("0:00",cou,5); // fill minus col
	                
	                jTable1.setValueAt(formatter.format(calen.getTime()),cou,6); // fill plus col
	            
	            }else if(incold.before(indate) && oucold.equals(outdate) && status.equals("Attend"))
	            {
	            	// 1 < a && 2==b
	                calen.setTime(indate);
	                calen.add(Calendar.HOUR_OF_DAY , -comehours);
	                calen.add(Calendar.MINUTE , -comemints);
	                
	                jTable1.setValueAt("0:00",cou,5); // fill minus col
	                
	                jTable1.setValueAt(formatter.format(calen.getTime()),cou,6); // fill plus col
	                
	            }else if(incold.before(indate) && oucold.before(outdate) && status.equals("Attend"))
	            { // 1 < a && 2 < b
	                calen.setTime(indate);
	                calen.add(Calendar.HOUR_OF_DAY , -comehours);
	                calen.add(Calendar.MINUTE , -comemints);
	                
	                jTable1.setValueAt(formatter.format(calen.getTime()),cou,6); // fill plus col
	                
	        // -------------------------------------------------- 
	                calen.setTime(outdate);
	                calen.add(Calendar.HOUR_OF_DAY , -gohours);
	                calen.add(Calendar.MINUTE , -gomints);
	                
	                jTable1.setValueAt(formatter.format(calen.getTime()),cou,5); // fill minus col
	                
	            }else if(incold.equals(indate) && oucold.after(outdate) && status.equals("Attend"))
	            { // 1==a && 2 > b
	                calen.setTime(oucold);
	                calen.add(Calendar.HOUR_OF_DAY , -ouhours);
	                calen.add(Calendar.MINUTE , -oumints);
	                
	                jTable1.setValueAt("0:00",cou,5); // fill minus col
	                
	                jTable1.setValueAt(formatter.format(calen.getTime()),cou,6); // fill plus col
	                
	            }else if(incold.equals(indate) && oucold.equals(outdate) && status.equals("Attend"))
	            { // 1==a && 2==b
	                
	                jTable1.setValueAt("0:00",cou,5); // fill minus col
	                jTable1.setValueAt("0:00",cou,6); // fill plus col
	                
	            }else if(incold.equals(indate) && oucold.before(outdate) && status.equals("Attend"))
	            { // 1==a && 2 < b
	                calen.setTime(outdate);
	                calen.add(Calendar.HOUR_OF_DAY , -gohours);
	                calen.add(Calendar.MINUTE , -gomints);
	                
	                jTable1.setValueAt(formatter.format(calen.getTime()),cou,5); // fill minus col
	                jTable1.setValueAt("0:00",cou,6); // fill plus col
	            }
	            
	        }
	        
	        if(this.showed) this.save.setEnabled(true);// ************
	    }

	private void this_componentShown(ComponentEvent e) {
		changeTotal(); // -----------
		this.showed = true; // active spinner listener
	}

	private void holiday_actionPerformed(ActionEvent e) {
		if (this.holiday.isSelected()) {

			for (int i = 0; i < jTable1.getRowCount(); i++) {
				jTable1.setValueAt("absent", i, 1);
				jTable1.setValueAt("absent", i, 2);
				/*
				 * jTable1.setValueAt("0:00", i, 3);
				jTable1.setValueAt("0:00", i, 4);
				jTable1.setValueAt("0:00", i, 5);
				jTable1.setValueAt("0:00", i, 6);
				 */
			}

			this.save.setEnabled(true);
			this.fromt.setValue(this.finaldate);
			this.endt.setValue(this.finaldate);
			this.fromt.setEnabled(false);
			this.endt.setEnabled(false);
			this.comb.setEnabled(false);
			return;// exit code
		}
		// ----------- unchecked
		
		for (int i = 0; i < jTable1.getRowCount(); i++) {
			jTable1.setValueAt("Attend", i, 1);
			int Dpart = comb.getSelectedIndex();
			ComboBoxModel<String> model = comb.getModel();
			timeroll = model.getElementAt(Dpart); 
			jTable1.setValueAt(timeroll, i, 2);
		
			this.fromt.setEnabled(true);
			this.endt.setEnabled(true);
			this.comb.setEnabled(true);
			if(timeroll.equals("first"))setSpinnerval(); // restart table
			if(timeroll.equals("second"))setSpinnerval_2(); // restart table
			if(timeroll.equals("third"))setSpinnerval_3(); // restart table
		
		}
	}

	private void this_windowClosing(WindowEvent e) {

		UIManager.put("OptionPane.yesButtonText", "yes");
		UIManager.put("OptionPane.noButtonText", "no");

		if (this.save.isEnabled()) {
			Toolkit.getDefaultToolkit().beep();
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this,
					"Are you sure you want to exit without save !!?", "Allert", JOptionPane.YES_NO_OPTION)) {
				this.dispose();
			}
		} else {
			this.dispose();
		}
	}

	private void setImage() {

		URL url = getClass().getResource("img/exit.png");

		if (url != null) {
			this.setIconImage(new ImageIcon(url).getImage());
		}

	}

	

    private void save_actionPerformed(ActionEvent e) {
       formatter = new SimpleDateFormat("YYYY-MM-dd",Locale.getDefault());
       SimpleDateFormat timeform=new SimpleDateFormat("YYYY-MM-dd",Locale.getDefault());
      
       if(db.dayAlreadysaved_r(formatter.format(this.date.getDate()),this.getName() )){
           
               UIManager.put("OptionPane.yesButtonText","Yes");
               UIManager.put("OptionPane.noButtonText","No");
               
               Toolkit.getDefaultToolkit().beep();
               if(JOptionPane.NO_OPTION== JOptionPane.showConfirmDialog(this,"this day allready found \nDo you want to change day?","Allert",JOptionPane.YES_NO_OPTION)){
                   return;// **********
               }
          
               
       }
       // -----------------------------------
       formatter = new SimpleDateFormat("YYYY-MM-dd",Locale.getDefault());
       db.saveOrder_r( formatter.format(this.date.getDate()),this.getName() ,this.holiday.isSelected(),timeform.format((Date)this.fromt.getValue()),timeform.format((Date)this.endt.getValue()),this.jTable1);
       daycheck = this.date.getDate().getDay();
       this.save.setEnabled(false);
    }


	public void stateChanged(ChangeEvent e) {

		if ((JSpinner) e.getSource() == this.fromt) {
			if (this.holiday.isSelected()) {
				return;
			}

			firstdat = (Date) fromt.getValue();

			formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());

			int rows = this.jTable1.getRowCount();

			for (int i = 0; i < rows; i++) {
				jTable1.setValueAt("Attend", i, 1);
				jTable1.setValueAt(formatter.format(firstdat), i, 3);
			}

		} else if ((JSpinner) e.getSource() == this.endt) {
			if (this.holiday.isSelected()) {
				return;
			}

			lastdat = (Date) endt.getValue();

			formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());

			int rows = this.jTable1.getRowCount();

			for (int i = 0; i < rows; i++) {
				jTable1.setValueAt("Attend", i, 1);
				jTable1.setValueAt(formatter.format(lastdat), i, 4);
			}
			

		}
		else if ((JDateChooser) e.getSource() == this.date) {
			if(daycheck!=date.getDate().getDay())
			
					save.setEnabled(true);

		}
		

		if (this.showed)
			changeTotal(); // -----------
	}
}
