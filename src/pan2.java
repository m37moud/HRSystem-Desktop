
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;



//import net.sf.jasperreports.renderers.tableModel;

//import net.sf.jasperreports.renderers.tableModel;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.net.URL;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.SystemColor;
import java.awt.Window.Type;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import com.jgoodies.forms.factories.DefaultComponentFactory;

/*
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
*/
public class pan2 extends JFrame {

	static Value val;
	HashMap<Integer, String> listemp;

	ArrayList<String> listtst;

	idmap list;

	int checkmark = 0;

	private static DecimalFormat df = new DecimalFormat("# 0");
	private JScrollPane scrollPane;
	private JButton btnminsrow;
	private JButton btncancel;
	private JButton btnSave;
	private JButton btnaddrow;
	// private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_1;
	private static DBInfo db = new DBInfo();
	private JTable table = new JTable();
	Connection con = null;
	
	

	private JPanel panel;
	private int row = -1;
	private int rowcounter = 0;
	int numsavedrow = 0;
	private JMenuItem mntmEditEmployee;
	private JMenuItem mntmDeleteEmployee;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public pan2() throws Exception {
		setTitle("HR screen");
		setSize(new Dimension(938, 660));
		setResizable(false);

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	private void initialize() throws Exception {
		 
		setBounds(100, 100, 936, 644);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(936, 644);
		setLocationRelativeTo(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 75, 882, 530);
		getContentPane().add(tabbedPane);

		panel = new JPanel();
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tabbedPane.addTab("view employe", null, panel, null);

		///////////////////////
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(240, 12, 173, 35);
		panel.add(comboBox_1);
		comboBox_1.addItem("all Department");
		for (Depart dep : db.getDepartment()) {

			comboBox_1.addItem(dep.getDepartment());
		}

		scrollPane = new JScrollPane();
		scrollPane.setBounds(143, 84, 706, 375);

		panel.setLayout(null);
		panel.add(scrollPane);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(table);

		// setup model
		// table.setModel(new tablePanModel(db.getemploye()));

		table.setModel(new panModel(db.getemploye()));

		// is true the table uses the entire height of the container ,
		// even if the table doesn't have enough rows to use the whole vertical
		// space.
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);

		table.setEnabled(false);
		table.setBackground(UIManager.getColor("Button.highlight"));
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		table.setColumnSelectionAllowed(true);

		table.setRowHeight(25);
		table.setRowHeight(23);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//
		// table.getModel().addTableModelListener(table);

		//
		// initColumnSizes(table);
		alterTable();
		setImage();

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jTable1_mouseClicked(e);
			}

		});

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 5, 133, 402);
		panel.add(scrollPane_2);

		JTree tree = new JTree();

		scrollPane_2.setViewportView(tree);
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("mayorca") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode("\u0627\u0644\u0627\u062F\u0627\u0631\u0629");
				node_1.add(new DefaultMutableTreeNode("\u0627\u0644\u062A\u0643\u0627\u0644\u064A\u0641"));
				node_1.add(new DefaultMutableTreeNode("\u0627\u0644\u062D\u0633\u0627\u0628\u0627\u062A"));
				node_1.add(new DefaultMutableTreeNode("\u0627\u0644\u0645\u0631\u0627\u062C\u0639\u0629"));
				node_1.add(new DefaultMutableTreeNode(""));
				// getContentPane().add(node_1);
				node_1 = new DefaultMutableTreeNode("\u0627\u0644\u0645\u0635\u0646\u0639");
				node_1.add(new DefaultMutableTreeNode("\u0627\u0644\u062E\u0637\u0648\u0637"));
				node_1.add(new DefaultMutableTreeNode("\u0627\u0644\u062A\u062D\u0645\u064A\u0644"));
				node_1.add(new DefaultMutableTreeNode("\u0627\u0644\u0627\u0641\u0631\u0627\u0646"));
				node_1.add(new DefaultMutableTreeNode("\u0627\u0644\u0641\u0631\u0632"));
				// getContentPane().add(node_1);
			}
		}));

		JButton btnGetEmploye = new JButton("get employe");
		btnGetEmploye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ComboBoxModel<String> model_1 = comboBox_1.getModel();

				int depart = comboBox_1.getSelectedIndex();
				String sdepartment = (String) model_1.getElementAt(depart);
				if (!sdepartment.equals("all Department")) {
					btnaddrow.setEnabled(true);

					// table.getModel().equals(new tablePanModel(
					// db.getemployesamedepartment(sdepartment)));
					table.setModel(new panModel(db.getemployesamedepartment(sdepartment)));
					// panModel mod= (panModel) table.getModel();

					alterTable();

				} else {

					table.setModel(new panModel(db.getemploye()));
					btnaddrow.setEnabled(false);

					alterTable();
				}

			}
		});
		btnGetEmploye.setBounds(454, 11, 133, 35);
		panel.add(btnGetEmploye);

		btnaddrow = new JButton("+");
		btnaddrow.setToolTipText("insert new employe");
		btnaddrow.setForeground(new Color(0, 128, 0));
		btnaddrow.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnaddrow.setEnabled(false);
		btnaddrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ComboBoxModel<String> model = comboBox_1.getModel();
				int Dpart = comboBox_1.getSelectedIndex();
				String sdepartment = (String) model.getElementAt(Dpart);
				if (sdepartment.equals("all Department")) {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(table,
							"Select Department and press button ( + ) to Insert new Employe");
					return;

				}

				panModel mod = (panModel) table.getModel();
				row = mod.getRowCount();

				btnSave.setText("Save");
				String solve = "Solve";
				KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
				table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, solve);
				table.getActionMap().put(solve, new AbstractAction() {

					@Override
					public void actionPerformed(ActionEvent e) {
						save_emp();

					}
				});

				//
				int sid = 0;
				if (row == -1) {
					row = 0;
					sid = 0;
				} else if (row > 0)
					sid = (int) mod.getValueAt(row - 1, 1);

				// enable and
				mntmDeleteEmployee.setEnabled(false);
				mntmEditEmployee.setEnabled(false);
				btnminsrow.setEnabled(true);
				table.setEnabled(true);
				btnSave.setVisible(true);
				btncancel.setVisible(true);

				/*
				 * 
				 * table.addKeyListener(new KeyAdapter() {
				 * 
				 * @Override public void keyPressed(KeyEvent e) {
				 * if(e.getKeyCode() == e.VK_ENTER) { save_emp(); } } });
				 */

				// table.setEditingRow(table.getRowCount());
				// int sid = db.getLastid();

				mod.addNewEmptyRow();

				rowcounter++;

				mod.setValueAt(sid + 1, row, 1);

				// mod.setValueAt("chose Department", row, 5);

				ComboBoxModel<String> model_1 = comboBox_1.getModel();
				int partset = comboBox_1.getSelectedIndex();
				String departCell = (String) model_1.getElementAt(partset);
				mod.setValueAt(departCell, row, 6);

				DefaultTableCellRenderer crend = new DefaultTableCellRenderer();
				crend.setHorizontalAlignment(JLabel.CENTER);
				crend.add(new PopupMenu("wrong "));

				TableColumn column = null;
				column = table.getColumnModel().getColumn(6);
				crend.setHorizontalAlignment(JLabel.CENTER);
				crend.setToolTipText("Click for combo box");
				//
				// JTextField
				JComboBox combo = new JComboBox();
				combo.setEnabled(false);
				column.setCellEditor(new DefaultCellEditor(combo));

				for (Depart dep : db.getDepartment()) {
					combo.addItem(dep.getDepartment());
				}

				///
				combo.setFocusable(true);

				// combo.setAlignmentX(JLabel.CENTER);
				combo.setFont(new Font("Tahoma", Font.BOLD, 13));
				combo.setForeground(Color.BLACK);
				// combo.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

			}
		});
		btnaddrow.setBounds(143, 58, 49, 25);
		panel.add(btnaddrow);

		btnSave = new JButton();
		btnSave.setVisible(false);

		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// save operation

				save_emp();

				// update

				edit_emp1();

				// Delete function

				delete_Emp3();
			}

		});
		btnSave.setBounds(730, 470, 89, 23);
		panel.add(btnSave);

		btncancel = new JButton("cancel");
		btncancel.setVisible(false);
		btncancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				save_cancel();

				if (btnSave.getText().trim().equals("Update")) {

					/*
					 * for (int i = 0; i < listtst.size(); i++) {
					 * 
					 * String s = listtst.get(i); String[] d = s.split(":");
					 * 
					 * employee emp = db.getEmployeeid(Integer.parseInt(d[1]),
					 * d[2]);
					 * 
					 * 
					 * }
					 */
					int Dpart = comboBox_1.getSelectedIndex();
					ComboBoxModel<String> model = comboBox_1.getModel();
					String sdepartment = (String) model.getElementAt(Dpart);
					if (sdepartment.equals("all Department")) {

						table.setModel(new panModel(db.getemploye()));
						alterTable();
					} else {
						table.setModel(new panModel(db.getemployesamedepartment("" + table.getValueAt(0, 6))));
						alterTable();
					}
					mntmDeleteEmployee.setEnabled(true);

					table.setEnabled(false);
					btnSave.setVisible(false);
					btncancel.setVisible(false);
					row = -1;
					hidecol(0);
					showcol(1, 37);
				}
				if (btnSave.getText().trim().equals("Delete")) {
					int Dpart = comboBox_1.getSelectedIndex();
					ComboBoxModel<String> model = comboBox_1.getModel();
					String sdepartment = (String) model.getElementAt(Dpart);
					if (sdepartment.equals("all Department")) {

						table.setModel(new panModel(db.getemploye()));
						alterTable();
					} else {
						table.setModel(new panModel(db.getemployesamedepartment("" + table.getValueAt(0, 6))));
						alterTable();
					}
					
					//

					mntmEditEmployee.setEnabled(true);
					table.setEnabled(false);
					btnSave.setVisible(false);
					btncancel.setVisible(false);
					row = -1;
					hidecol(0);
					showcol(1, 37);
				}

			}

		});

		btncancel.setBounds(202, 470, 89, 23);
		panel.add(btncancel);

		btnminsrow = new JButton("-");
		btnminsrow.setEnabled(false);
		btnminsrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panModel model = (panModel) table.getModel();
				int r = table.getRowCount();
				if (rowcounter == 0) {

					table.setEnabled(false);
					btnSave.setVisible(false);
					btncancel.setVisible(false);
					btnminsrow.setEnabled(false);
					return;

				}

				if (!table.getValueAt(r - 1, 2).equals("")) {
					Toolkit.getDefaultToolkit().beep();
					if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(table,
							"there is employee entered are you sure you want to delete this row save", "Attention ",
							JOptionPane.YES_NO_OPTION)) {
						return;
					}

				}
				System.out.println(row);
				model.removeLastNewEmptyRow(r - 1);
				rowcounter--;
				r--;
				if (rowcounter == 0) {
					mntmDeleteEmployee.setEnabled(true);
					mntmEditEmployee.setEnabled(true);
				}
			}
		});

		btnminsrow.setToolTipText("delete added row");
		btnminsrow.setForeground(new Color(0, 128, 0));
		btnminsrow.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnminsrow.setBounds(193, 58, 49, 25);
		panel.add(btnminsrow);
		
		JLabel lblTest = DefaultComponentFactory.getInstance().createLabel("test");
		
		lblTest.addMouseListener(new MouseListener() {
			
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblTest.setForeground(Color.black);
				lblTest.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lblTest.setForeground(Color.blue);
				lblTest.setOpaque(false);
				
				lblTest.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		lblTest.setBounds(22, 429, 92, 14);
		panel.add(lblTest);

		JPanel panel_3 = new JPanel();
		
		panel_3.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
				Toolkit.getDefaultToolkit().beep();
				String name = (String) table.getValueAt(row, 6);
				RegisterAtendance departdialog = new RegisterAtendance("auditing"); 
				
				departdialog.setTitle("Register Attendance and Absence to employe in department (   )");
				
				departdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				departdialog.setLocationRelativeTo(null);

				departdialog.setVisible(true);
				

			}
		});
		tabbedPane.addTab("Register Attendance and Absence ", null, panel_3,
				"Register Attendance and Absence to all Employees");
		/*
		 * for (Depart dep : db.getDepartment()) {
		 * comboBox.addItem(dep.getDepartment()); }
		 */

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 21, 262, 14);
		getContentPane().add(panel_4);
		panel_4.setLayout(null);

		// compo box to get all employee and but them in table

		// ===========
		JLabel lblDatabaswStatue = new JLabel("database statue");
		lblDatabaswStatue.setBounds(0, 0, 103, 14);
		panel_4.add(lblDatabaswStatue);

		JLabel label = new JLabel("");
		label.setBounds(131, 0, 131, 14);
		panel_4.add(label);

		JSeparator separator = new JSeparator();
		separator.setBounds(112, 75, 1, 2);
		getContentPane().add(separator);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(SystemColor.windowText);
		menuBar.setBounds(0, 0, 930, 21);
		getContentPane().add(menuBar);

		JMenu mnHome = new JMenu("File");
		mnHome.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnHome.setForeground(SystemColor.windowText);
		menuBar.add(mnHome);

		JMenuItem mntmAddNewDepartment = new JMenuItem("Add new department");
		mnHome.add(mntmAddNewDepartment);
		mntmAddNewDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewDepartment(e);
			}

		});

		JMenuItem mntmAddNewEmployee = new JMenuItem("Add new employee");
		mnHome.add(mntmAddNewEmployee);

		mntmEditEmployee = new JMenuItem("Edit Employee");
		mntmEditEmployee.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));

		mnHome.add(mntmEditEmployee);
		/*
		 * stoped here method to update
		 */
		/// stoped here method to update
		mntmEditEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				initialize_edit_employe(e);

			}

		});

		mntmDeleteEmployee = new JMenuItem("delete employee");
		mntmDeleteEmployee.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		mntmDeleteEmployee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				initialize_delete_employe(e);

			}
		});

		mnHome.add(mntmDeleteEmployee);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmInfo = new JMenuItem("Info");
		mntmInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                about_actionPerformed(e);
            }
        });
		mnAbout.add(mntmInfo);
		if (!adminDB.getConnection().isClosed()) {
			label.setText("connected");
		} else {
			label.setText("Not connected");
		}
	}

	private void jTable1_mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			Point po = e.getPoint();
			int row = table.rowAtPoint(po);
			int col = table.columnAtPoint(po);
			// jTable1_mouseClicked(e);
			if (col == 2 && row >= 0 && row < table.getRowCount()) {
				Toolkit.getDefaultToolkit().beep();

				String dialogname = (String) table.getValueAt(row, col);
				insert dialog = new insert();
				dialog.setName(dialogname);
				
				
				dialog.setTitle("Register Attendance and Absence to ( " + dialogname + " )");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(table);

				dialog.setVisible(true);

			}
			else if(col == 6 && row >= 0 && row < table.getRowCount())
			{
				Toolkit.getDefaultToolkit().beep();
				String name = (String) table.getValueAt(row, col);
				RegisterAtendance departdialog = new RegisterAtendance(name); 
				
				departdialog.setTitle("Register Attendance and Absence to employe in department ( " + name + " )");
				
				departdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				departdialog.setLocationRelativeTo(null);

				departdialog.setVisible(true);
				
			}
			else

				JOptionPane.showMessageDialog(null, "please chose employee name to get employe details /n"
						+ "or chose department name to Register Attendance and Absence  ");
			// JOptionPane.showMessageDialog(null, ""+table.getValueAt(row,
			// col));
		}

	}

	private void master_componentShown(ComponentEvent e) {
		if (!db.Connect()) {
			Toolkit.getDefaultToolkit().beep();
			UIManager.put("OptionPane.okButtonText", "�������");
			JOptionPane.showMessageDialog(this, "��� ��� ��� �� ������� ������ ��������\n���� ����� ��������.",
					"�������������������", JOptionPane.ERROR_MESSAGE);
			this_windowClosing(null);// ----
		}

	}

	private void this_windowClosing(WindowEvent e) {

		// ********************

		UIManager.put("OptionPane.yesButtonText", "���");
		UIManager.put("OptionPane.noButtonText", "��");

		if (btnSave.isShowing()) {
			Toolkit.getDefaultToolkit().beep();
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "�� ���� ������ ���� ��� ��������� �",
					"����� �������", JOptionPane.YES_NO_OPTION)) {
				this.dispose();
			}
		} else {
			this.dispose();
		}
		db.Disconnect();
		this.dispose();// exit
		System.exit(0);// to close reports
	}

	private void alterTable() {

		list = new idmap(table.getRowCount());

		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(table);

		// is true the table uses the entire height of the container ,
		// even if the table doesn't have enough rows to use the whole vertical
		// space.
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);

		// table.setShowGrid(false);
		table.setEnabled(false);
		table.setBackground(UIManager.getColor("Button.highlight"));
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		table.setColumnSelectionAllowed(true);

		table.setRowHeight(25);
		table.setRowHeight(23);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JTableHeader renderer = table.getTableHeader();

		renderer.setUI(new BasicTableHeaderUI());
		renderer.setAlignmentY(CENTER_ALIGNMENT);
		
		renderer.setBackground(Color.WHITE);
		renderer.setFont(new Font("Tahoma", Font.BOLD, 12));
		renderer.setForeground(Color.blue);
		renderer.setPreferredSize(new Dimension(renderer.getSize().height, 30));
		

		renderer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		DefaultTableCellRenderer crend = new DefaultTableCellRenderer();
		crend.setHorizontalAlignment(JLabel.CENTER);
		crend.add(new JCheckBox());

		TableColumn column = null;

		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);

			if (i == 0) {

				column.setCellRenderer(new ValueRenderer());
				((AbstractButton) column.getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
				column.setMaxWidth(0);
				column.setMinWidth(0);
				column.setPreferredWidth(0);

			} else if (i == 1) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(50); // first column
			} else if (i == 2) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(250); // second column is bigger
			} else if (i == 3) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(150); // second column is bigger
			} else if (i == 5) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(100); // second column is bigger
			} else if (i == 6) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(200); // second column is bigger
			} else if (i == 7) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(100); // second column is bigger
			} else if (i == 8) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(150); // second column is bigger
			}

			else {
				column.setPreferredWidth(65);
				column.setCellRenderer(crend);
			}

		}

	}

	private void initial(JTable table) {
		panModel model = (panModel) table.getModel();
		TableColumn column = null;
		Component comp = null;
		int headerWidth = 0;
		int cellWidth = 0;
		Object[] longValues = model.columnType;
		TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();

		column = table.getColumnModel().getColumn(0);

		comp = headerRenderer.getTableCellRendererComponent(null, Value.class, false, false, 0, 0);
		// headerWidth = comp.getPreferredSize().width;

		comp = table.getDefaultRenderer(model.getColumnClass(0)).getTableCellRendererComponent(table, longValues[0],
				false, false, 0, 0);
		cellWidth = comp.getPreferredSize().width;

		column.setPreferredWidth(Math.max(150, 150));

	}

	private void initColumnSizes(JTable table) {
		tablePanModel model = (tablePanModel) table.getModel();
		TableColumn column = null;
		Component comp = null;
		int headerWidth = 0;
		int cellWidth = 0;
		Object[] longValues = model.longValues;
		TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();

		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);

			comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
			headerWidth = comp.getPreferredSize().width;

			comp = table.getDefaultRenderer(model.getColumnClass(i)).getTableCellRendererComponent(table, longValues[i],
					false, false, 0, i);
			cellWidth = comp.getPreferredSize().width;

			column.setPreferredWidth(Math.max(headerWidth, cellWidth));
		}
	}

	private void initialize_edit_employe(ActionEvent e) {

		panModel mod = (panModel) table.getModel();
		listtst = new ArrayList<>();
		// method to invoke action when key pressed 
		String solve = "Solve";
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, solve);
		table.getActionMap().put(solve, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				edit_emp1();

			}
		});
			//*************
		mod.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

				// TODO Auto-generated method stub
				int firstRow = e.getFirstRow();

				int lastRow = e.getLastRow();
				int index = e.getColumn();

				//

				Object data = mod.getValueAt(firstRow, index);
				// row = firstRow;

			}
		});
		int numeditrow =0;

		mntmDeleteEmployee.setEnabled(false);

		btnaddrow.setEnabled(false);
		btnSave.setVisible(true);
		btnSave.setText("Update");
		btncancel.setVisible(true);
		table.setEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		

		// add coulmn with check box mark

		JCheckBox ch = new JCheckBox();

		TableColumn checkboxcolumn = null;
		checkboxcolumn = table.getColumnModel().getColumn(0);
		// hidecol(1);
		showcol(0, 20);
		//table.setShowVerticalLines(false);

		// table.getColumnModel().removeColumn(checkboxcolumn);
		checkboxcolumn.setCellRenderer(new ValueRenderer());
		checkboxcolumn.sizeWidthToFit();
		checkboxcolumn.setCellEditor(new ValueEditor());

		TableColumn departcolumn = null;
		departcolumn = table.getColumnModel().getColumn(6);

		JComboBox combo = new JComboBox();
		departcolumn.setCellEditor(new DefaultCellEditor(combo));
		ComboBoxModel<String> model_1 = combo.getModel();
		int depart = combo.getSelectedIndex();
		String departCell = (String) model_1.getElementAt(depart);

		// combo.addItem(sdepartment);
		for (Depart dep : db.getDepartment()) {
			combo.addItem(dep.getDepartment());
		}

		//

	}

	private void initialize_delete_employe(ActionEvent e) {

		panModel mod = (panModel) table.getModel();
		listtst = new ArrayList<>();
		String solve = "Solve";
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, solve);
		table.getActionMap().put(solve, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delete_Emp3();

			}
		});

		mod.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

				// TODO Auto-generated method stub
				int firstRow = e.getFirstRow();

				int lastRow = e.getLastRow();
				int index = e.getColumn();

				//

				Object data = mod.getValueAt(firstRow, index);
				// row = firstRow;

			}
		});

		mntmEditEmployee.setEnabled(false);
		btnaddrow.setEnabled(false);
		btnSave.setVisible(true);
		btnSave.setText("Delete");
		btncancel.setVisible(true);
		table.setEnabled(true);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);

		// add coulmn with check box mark

		TableColumn checkboxcolumn = null;
		checkboxcolumn = table.getColumnModel().getColumn(0);

		// hidecol(1);
		showcol(0, 20);

		// table.getColumnModel().removeColumn(checkboxcolumn);
		checkboxcolumn.setCellRenderer(new ValueRenderer());
		checkboxcolumn.setCellEditor(new ValueEditor());

		//

	}

	private Object[][] getdata() {
		Object[][] obj = new Object[table.getRowCount()][table.getColumnCount()];

		return obj;
	}

	private employee addNewrow(int row) {
		int r = row;
		// row =table.getSelectedRow();

		String sid = String.valueOf(table.getValueAt(r, 1));
		String sfn = String.valueOf(table.getValueAt(r, 2));
		String stb = String.valueOf(table.getValueAt(r, 3));

		String sag = String.valueOf(table.getValueAt(r, 4));
		String ssalary = String.valueOf(table.getValueAt(r, 5));
		// ComboBoxModel<String> model = comboBox.getModel();
		// int depart = comboBox.getSelectedIndex();
		// String sdepartment = (String) model.getElementAt(depart);
		String sdepartment = String.valueOf(table.getValueAt(r, 6));
		String svac = String.valueOf(table.getValueAt(r, 7));
		String sbal = String.valueOf(table.getValueAt(r, 8));

		int total = Integer.parseInt(stb);

		int id = Integer.parseInt(sid);
		int age = Integer.parseInt(sag);
		float salary = Float.parseFloat(ssalary);
		int vac = Integer.parseInt(svac);
		int bal = Integer.parseInt(sbal);

		employee emp = new employee();
		emp.setId(id);
		emp.setFirstName(sfn);
		emp.settotal(total);
		emp.setAg(age);
		emp.setSalary(salary);
		emp.setDepartment(sdepartment);
		emp.setVacation(vac);
		emp.setVbalance(bal);

		// table.setModel(mod);

		// int statue = db.save(emp);

		return emp;

	}

	private void addNewDepartment(ActionEvent e) {
		DialoAddDepartment dt = new DialoAddDepartment();
		dt.setLocationRelativeTo(this);
		dt.setVisible(true);

	}

	private static class ValueRenderer extends JCheckBox implements TableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {

			Value v = (Value) value;
			this.setSelected(v.selected);
			// this.setText(df.format(v.value));
			// setHorizontalAlignment(CENTER);

			if (isSelected) {
				setForeground(Color.red);

				setBackground(table.getSelectionBackground());
			} else {
				setForeground(Color.black);
				setBackground(table.getBackground());
			}
			return this;
		}
	}

	private class ValueEditor extends AbstractCellEditor implements TableCellEditor, ItemListener {

		private ValueRenderer vr = new ValueRenderer();

		public ValueEditor() {
			vr.addItemListener(this);
		}

		@Override
		public Object getCellEditorValue() {
			return vr.isSelected();
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int r, int col) {
			val = (Value) value;
			// listemp = new HashMap<>();

			// arrid = new int[table.getRowCount()];

			if (!val.selected) {

				listtst.add("" + r + ":" + val.value + ":" + table.getValueAt(r, 6));

				vr.setSelected(val.selected);
				vr.setText(df.format(val.value));
				System.out.println(val.value);

				row = r;

			} else {
				val = null;
				row = -1;

			}

			return vr;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			this.fireEditingStopped();

		}
	}

	public int hidetoModel(JTable table, int vColIndex) {
		if (vColIndex >= table.getColumnCount()) {
			return -1;
		}
		return table.getColumnModel().getColumn(vColIndex).getModelIndex();
	}

	public void hidecol(int col) {
		TableColumn dcol = null;
		dcol = table.getColumnModel().getColumn(col);

		// ((AbstractButton)
		// checkboxcolumn.getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		dcol.setMaxWidth(0);
		dcol.setMinWidth(0);
		dcol.setPreferredWidth(0);
	}

	public void showcol(int col, int width) {
		TableColumn dcol = null;
		dcol = table.getColumnModel().getColumn(col);

		dcol.setMaxWidth(width);
		dcol.setMinWidth(width);
		dcol.setPreferredWidth(width);
	}

	private void save_emp() {
		if (btnSave.getText().trim().equals("Save")) {

			// row = table.getRowCount();
			System.out.println(row);
			int count = rowcounter;
			int firstrow = table.getRowCount() - count;
			int lastrow = table.getRowCount();

			listemp = new HashMap<>();
			int r = table.getRowCount() - 1;
			for (int roo = 0; roo < table.getRowCount(); roo++) {
				if (roo >= firstrow && roo < lastrow) {
					/*
					 * || table.getValueAt(roo, 3).equals("") ||
					 * table.getValueAt(roo, 4).equals("") ||
					 * table.getValueAt(roo, 5).equals("") ||
					 * table.getValueAt(roo, 7).equals("") ||
					 * table.getValueAt(roo, 8).equals("")
					 * 
					 * 
					 * 
					 */
					if (roo == firstrow && table.getValueAt(roo, 2).equals("")) {
						JOptionPane.showMessageDialog(null, " Please insert employee details ", "error",
								JOptionPane.ERROR_MESSAGE);

						return;
					} else if (table.getValueAt(roo, 2).equals("")) {

						break;
					}

					employee ee = addNewrow(roo);

					int sav = db.save(ee);
					if (sav <= 0) {
						listemp.put(ee.getId(), ee.getDepartment());
					} else {
						numsavedrow++;
					}

				}

			}

			numsavedrow = rowcounter - numsavedrow;

			System.out.println("num of un saved " + numsavedrow);
			panModel modl = (panModel) table.getModel();
			for (int i = 0; i < numsavedrow; i++) {
				System.out.println(i);
				modl.removeLastNewEmptyRow(r);
				r--;

			}

			if (listemp.size() == 0) {
				// System.out.println(" count mess" + listemp.size());

				// btnaddrow.setEnabled(false);
				mntmDeleteEmployee.setEnabled(true);
				mntmEditEmployee.setEnabled(true);
				btnminsrow.setEnabled(false);
				table.setEnabled(false);
				btnSave.setVisible(false);
				btncancel.setVisible(false);

				rowcounter = 0;
				row = -1;

				table.removeKeyListener(null);
				numsavedrow = 0;
				JOptionPane.showMessageDialog(table, "employe save sucsseful");
				int Dpart = comboBox_1.getSelectedIndex();
				ComboBoxModel<String> model = comboBox_1.getModel();
				String sdepartment = (String) model.getElementAt(Dpart);
				if (sdepartment.equals("all Department")) {

					table.setModel(new panModel(db.getemploye()));
					alterTable();
				} else {
					table.setModel(new panModel(db.getemployesamedepartment("" + table.getValueAt(0, 6))));
					alterTable();
				}
			} else {
				JOptionPane.showMessageDialog(null, "error ecourd in save", "Error ", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	private void save_cancel() {
		if (btnSave.getText().trim().equals("Save")) {
			int r = table.getRowCount() - 1;

			if (!table.getValueAt(r, 2).equals("")) {
				Toolkit.getDefaultToolkit().beep();
				if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(table,
						"are rou sure you want to cancel employe save?  ", "save allert", JOptionPane.YES_NO_OPTION)) {
					return;
				}
			}

			panModel model = (panModel) table.getModel();
			for (int i = 0; i < rowcounter; i++) {
				model.removeLastNewEmptyRow(r);
				r--;

			}
			rowcounter = 0;
			// disable buttons
			mntmDeleteEmployee.setEnabled(true);
			mntmEditEmployee.setEnabled(true);
			table.setEnabled(false);
			btnSave.setVisible(false);
			btncancel.setVisible(false);

			row = -1;
		}
	}

	private void edit_emp() {
		if (btnSave.getText().trim().equals("Update")) {

			// get all details in selected row

			panModel mod = (panModel) table.getModel();
			mod.addTableModelListener(table);

			int selectedrow = row;

			// editor.

			if (val != null && row >= 0) {

				int id = val.value;

				employee emp = addNewrow(row);
				// System.out.println(emp.toString());
				int update = db.update(emp);

				if (update > 0) {

					// disable and hiden buton
					table.removeKeyListener(null);
					mntmDeleteEmployee.setEnabled(true);

					btnminsrow.setEnabled(false);
					table.setEnabled(false);
					btnSave.setVisible(false);
					btncancel.setVisible(false);

					hidecol(0);
					showcol(1, 37);

					JOptionPane.showMessageDialog(table, "Employee Update sucsseful");

					int Dpart = comboBox_1.getSelectedIndex();
					ComboBoxModel<String> model = comboBox_1.getModel();
					String sdepartment = (String) model.getElementAt(Dpart);
					if (sdepartment.equals("all Department")) {

						table.setModel(new panModel(db.getemploye()));
						alterTable();
					} else {
						table.setModel(new panModel(db.getemployesamedepartment("" + table.getValueAt(0, 6))));
						alterTable();
					}
					// get all details in selected row

				} else {
					JOptionPane.showMessageDialog(table, "error ecourd in update", "Error ", JOptionPane.ERROR_MESSAGE);
				}
			} else
				JOptionPane.showMessageDialog(table, "please mark id to  edit employee", "Error ",
						JOptionPane.ERROR_MESSAGE);

		}
	}

	private void edit_emp1() {
		if (btnSave.getText().trim().equals("Update")) {

			System.out.println(listtst.toString() + listtst.size());

			// get all details in selected row

			panModel mod = (panModel) table.getModel();
			mod.addTableModelListener(table);

			int selectedrow = row;

			// editor.

			if (val != null && row >= 0) {

				int id = val.value;

				for (int i = 0; i < listtst.size(); i++) {

					String s = listtst.get(i);
					String[] d = s.split(":");

					// employee emp = db.getEmployeeid(Integer.parseInt(d[0]),
					// d[1]);
					employee emp = addNewrow(Integer.parseInt(d[0]));

					int update = db.update(emp);
					System.out.println(emp.toString() + " num updte" + update);

					if (update <= 0) {
						JOptionPane.showMessageDialog(table,
								"error ecourd in updatee mployee " + emp.getFirstName().toString() + "", "Error ",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					

					// get all details in selected row
					// listtst.remove(i);

				}

				// disable and hiden buton
				table.removeKeyListener(null);
				mntmDeleteEmployee.setEnabled(true);

				btnminsrow.setEnabled(false);
				table.setEnabled(false);
				btnSave.setVisible(false);
				btncancel.setVisible(false);

				hidecol(0);
				showcol(1, 37);

				JOptionPane.showMessageDialog(table, "Employee Update sucsseful");

				int Dpart = comboBox_1.getSelectedIndex();
				ComboBoxModel<String> model = comboBox_1.getModel();
				String sdepartment = (String) model.getElementAt(Dpart);
				if (sdepartment.equals("all Department")) {

					table.setModel(new panModel(db.getemploye()));
					alterTable();
				} else {
					table.setModel(new panModel(db.getemployesamedepartment("" + table.getValueAt(0, 6))));
					alterTable();
				}
				// get all details in selected row

			} else
				JOptionPane.showMessageDialog(table, "please mark id to  edit employee", "Error ",
						JOptionPane.ERROR_MESSAGE);

		}
	}

	private void delete_Emp() {

		if (btnSave.getText().trim().equals("Delete")) {

			System.out.println(listtst.toString() + listtst.size());
			list.printids(list);

			if (val != null && row >= 0) {

				employee emp = addNewrow(row);
				if (JOptionPane.NO_OPTION == JOptionPane
						.showConfirmDialog(table,
								"Are you want to delete  ( " + emp.getFirstName().toString() + " from Depart ( "
										+ emp.getDepartment().toString() + " ) ?",
								"Alert", JOptionPane.YES_NO_OPTION)) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}

				int id = val.value;

				int deleted = db.delete(id, emp.getDepartment());

				if (deleted > 0) {

					table.removeKeyListener(null);

					// disable and hiden buton

					btnminsrow.setEnabled(false);
					table.setEnabled(false);
					btnSave.setVisible(false);
					btncancel.setVisible(false);

					hidecol(0);
					showcol(1, 37);
					int Dpart = comboBox_1.getSelectedIndex();
					ComboBoxModel<String> model = comboBox_1.getModel();
					String sdepartment = (String) model.getElementAt(Dpart);
					if (sdepartment.equals("all Department")) {

						table.setModel(new panModel(db.getemploye()));
						alterTable();
					} else {
						table.setModel(new panModel(db.getemployesamedepartment("" + table.getValueAt(0, 6))));
						alterTable();
					}

					JOptionPane.showMessageDialog(table, "( " + emp.getFirstName().toString() + " ) Deleted sucsseful");

					// get all details in selected row

				} else {
					JOptionPane.showMessageDialog(table, "error ecourd in Delete", "Error ", JOptionPane.ERROR_MESSAGE);
				}
			} else
				JOptionPane.showMessageDialog(table, "please mark id to  first to Delete employee", "Error ",
						JOptionPane.ERROR_MESSAGE);

		}
	}

	private void delete_Emp3() {

		if (btnSave.getText().trim().equals("Delete")) {
			System.out.println(listtst.toString() + listtst.size());

			if (val != null && row >= 0) {
				employee emp = addNewrow(row);
				if (JOptionPane.NO_OPTION == JOptionPane
						.showConfirmDialog(table,
								"Are you want to delete  ( " + emp.getFirstName().toString() + " from Depart ( "
										+ emp.getDepartment().toString() + " ) ?",
								"Alert", JOptionPane.YES_NO_OPTION)) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}

				for (int i = 0; i < listtst.size(); i++) {
					String s = listtst.get(i);
					String[] d = s.split(":");

					int deleted = db.delete(Integer.parseInt(d[1]), d[2]);

					if (deleted <= 0) {
						JOptionPane.showMessageDialog(table, "error ecourd in Delete", "Error ",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// get all details in selected row

				}

				table.removeKeyListener(null);

				// disable and hiden buton

				mntmEditEmployee.setEnabled(true);

				btnminsrow.setEnabled(false);
				table.setEnabled(false);
				btnSave.setVisible(false);
				btncancel.setVisible(false);

				hidecol(0);
				showcol(1, 37);
				int Dpart = comboBox_1.getSelectedIndex();
				ComboBoxModel<String> model = comboBox_1.getModel();
				String sdepartment = (String) model.getElementAt(Dpart);
				if (sdepartment.equals("all Department")) {

					table.setModel(new panModel(db.getemploye()));
					alterTable();
				} else {
					table.setModel(new panModel(db.getemployesamedepartment("" + table.getValueAt(0, 6))));
					alterTable();
				}

				JOptionPane.showMessageDialog(table, " Deleted sucsseful");
				// JOptionPane.showMessageDialog(table, "( " + s + " ) Deleted
				// sucsseful");

				//
			} else
				JOptionPane.showMessageDialog(table, "please mark id to  first to Delete employee", "Error ",
						JOptionPane.ERROR_MESSAGE);

		}

	}
	 private void about_actionPerformed(ActionEvent e) {
	        about ddd=new about();
	        ddd.setLocationRelativeTo(this);
	        ddd.setVisible(true);
	    }
	 private void setImage() {
	        
	        URL url=getClass().getResource("img/exit.png");
	        
	        if(url != null){
	            this.setIconImage(new ImageIcon(url).getImage());
	        }
	               
	    }
	public static void main(String[] args) {

		 Locale.setDefault(new Locale("ar","EG"));
	        
	        System.setProperty("file.encoding","windows-1256");// Cp1256
	        System.setProperty("sun.jnu.encoding","Cp1256");
	        
	        try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				try {
					pan2 p = new pan2();
					p.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// p.setLocationRelativeTo(this);

			}
		});

	}
}
