

import java.awt.Color;
import java.awt.ComponentOrientation;
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
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
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
import javax.swing.table.TableColumn;
import javax.swing.border.BevelBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;



//import net.sf.jasperreports.renderers.tableModel;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;

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
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

/*
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
*/
public class pan extends JFrame {

	private JTable table = new JTable();
	private JScrollPane scrollPane;
	private JTextField tfFN;
	private JTextField tfLN;
	private JTextField tfAG;
	private JTextField tfSalary;
	private JTextField tfvac;
	private JTextField tfBalance;
	private JButton btnminsrow;
	private JButton btncancel;
	private JButton btnSave;
	private JButton btnReset;
	private JButton btnInsert;
	private JTextField tfID_2;
	private JPanel panel_2;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private DBInfo db = new DBInfo();
	Connection con = null;
	private insert dialog = new insert();
	private JPanel panel;
	private int row;
	private int rowcounter = 0;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public pan() throws Exception {
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
		// String modelcoulmn [] = {"id" , "first name", "lastname",
		// "age","salary","department","vacation","vbalance"};
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn(" employe name");
		model.addColumn("totalballance");
		model.addColumn("age");
		model.addColumn("salary");
		model.addColumn("department");
		model.addColumn("vacation");
		model.addColumn("vbalance");
		// model.addColumn("day");
		for (employee list : db.getemploye()) {

			model.addRow(new Object[] { list.getId(), list.getFirstName(), list.getTotalbalance(), list.getAg(),
					list.getSalary(), list.getDepartment(), list.getVacation(), list.getVbalance() });

		}
		
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

		int rowindex = 20;
		int coulmnindex = 20;
		boolean includingspace = true;
		panel.setLayout(null);
		panel.add(scrollPane);

		// alterTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.isCellSelected(e.getX(), e.getY())) {
					String s = String.valueOf(table.getCellRenderer(e.getX(), e.getY()));
					panel_2.setVisible(true);
					tfID_2.setText(s);
				}
			}
		});
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(table);

		//table.setModel(new tablePanModel(db.getemploye()));
		table.setModel(model);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);

		table.setEnabled(false);
		table.setBackground(UIManager.getColor("Button.highlight"));
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		table.setColumnSelectionAllowed(true);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(25);

		table.setRowHeight(23);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		DefaultTableCellRenderer crend = new DefaultTableCellRenderer();
		crend.setHorizontalAlignment(JLabel.CENTER);

		TableColumn column = null;

		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(30); // first column
			} else if (i == 1) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(250); // second column is bigger
			} else if (i == 2) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(150); // second column is bigger
			} else if (i == 4) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(100); // second column is bigger
			} else if (i == 5) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(200); // second column is bigger
			} else if (i == 6) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(100); // second column is bigger
			} else if (i == 7) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(150); // second column is bigger
			}

			else {
				column.setPreferredWidth(65);
				column.setCellRenderer(crend);
			}

		}

		JTableHeader renderer = table.getTableHeader();
		renderer.setBackground(Color.lightGray);
		renderer.setFont(new Font("Tahoma", Font.BOLD, 14));
		renderer.setForeground(Color.BLUE);
		renderer.setPreferredSize(new Dimension(renderer.getSize().height, 30));

		renderer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		// Rectangle rect = table.getCellRect(rowindex, coulmnindex,
		// includingspace);
		// table.scrollRectToVisible(rect);

		// table.setModel(model);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jTable1_mouseClicked(e);
			}

		});
		// create tree
		/*
		 * for(employee list : DBInfo.getemploye()) { model.addRow(new Object[]{
		 * list.getId(), list.getFirstName(), list.getLasttName(), list.getAg(),
		 * list.getSalary(), list.getDepartment(), list.getVacation(),
		 * list.getVbalance() }); }
		 */

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 5, 133, 482);
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

				DefaultTableModel modl = (DefaultTableModel) table.getModel();
				//modl.setRowCount(0);

				ComboBoxModel<String> model_1 = comboBox_1.getModel();

				int depart = comboBox_1.getSelectedIndex();
				String sdepartment = (String) model_1.getElementAt(depart);
				if (!sdepartment.equals("all Department")) {

					for (employee list : db.getemployesamedepartment(sdepartment)) {
						modl.addRow(new Object[] { list.getId(), list.getFirstName(), list.getTotalbalance(),
								list.getAg(), list.getSalary(), list.getDepartment(), list.getVacation(),
								list.getVbalance() });

					}
				} else {

					DefaultTableModel mod = (DefaultTableModel) table.getModel();
					mod.setRowCount(0);

					for (employee list : db.getemploye()) {
						mod.addRow(new Object[] { list.getId(), list.getFirstName(), list.getTotalbalance(),
								list.getAg(), list.getSalary(), list.getDepartment(), list.getVacation(),
								list.getVbalance() });

					}

				}

			}
		});
		btnGetEmploye.setBounds(454, 11, 133, 35);
		panel.add(btnGetEmploye);

		JButton btnaddrow = new JButton("+");
		btnaddrow.setToolTipText("insert new employe");
		btnaddrow.setForeground(new Color(0, 128, 0));
		btnaddrow.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnaddrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnSave.setText("Save");
				DefaultTableModel mod = (DefaultTableModel) table.getModel();
				mod.addRow(new Object[] { "", "", "", "", "", "", "", "" });

				// enable and
				btnminsrow.setEnabled(true);
				table.setEnabled(true);
				btnSave.setVisible(true);
				btncancel.setVisible(true);

				// table.setEditingRow(table.getRowCount());
				// int sid = db.getLastid();

				row = table.getRowCount() - 1;
				rowcounter++;
				int sid = (int) table.getValueAt(row - 1, 0);

				table.setValueAt(sid + 1, row, 0);
				table.setValueAt("chose Department", row, 5);
				JComboBox combo = new JComboBox();

				DefaultTableCellRenderer crend = new DefaultTableCellRenderer();
				crend.setHorizontalAlignment(JLabel.CENTER);
				crend.add(new PopupMenu("wrong "));

				TableColumn column = null;
				column = table.getColumnModel().getColumn(5);
				crend.setHorizontalAlignment(JLabel.CENTER);
				crend.setToolTipText("Click for combo box");

				column.setCellEditor(new DefaultCellEditor(combo));

				//
				ComboBoxModel<String> model_1 = combo.getModel();
				int depart = combo.getSelectedIndex();
				String departCell = (String) model_1.getElementAt(depart);

				//
				// ** table.setValueAt(departCell, r, 5);

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

				if (btnSave.getText().trim().equals("Save")) {
				String departmentchck = (String) table.getValueAt(row, 5);
				if (table.getValueAt(row, 1).equals("") || table.getValueAt(row, 2).equals("")
						|| table.getValueAt(row, 3).equals("") || table.getValueAt(row, 4).equals("")
						|| table.getValueAt(row, 6).equals("") || table.getValueAt(row, 7).equals("")) {
					JOptionPane.showMessageDialog(null, " Please insert employee details ", "error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!departmentchck.equals("chose Department")) {
					row = table.getRowCount();
					employee emp = addNewrow(row-1);
					int sav = db.save(emp);
					
					
					if (sav > 0) {

						// disable and hiden buton
						btnminsrow.setEnabled(false);
						table.setEnabled(false);
						btnSave.setVisible(false);
						btncancel.setVisible(false);
						rowcounter = 0;
						JOptionPane.showMessageDialog(table, "save sucsseful");

						
						// System.out.println(Arrays.toString(data));
					} else {
						JOptionPane.showMessageDialog(null, "error ecourd in save", "Error ",
								JOptionPane.ERROR_MESSAGE);
					}
				} else
					JOptionPane.showMessageDialog(null, " chose correct Department", "error",
							JOptionPane.ERROR_MESSAGE);
			}
				
				// update
				if (btnSave.getText().trim().equals("Update")) {
					
					// get all details in selected row
					DefaultTableModel mod = (DefaultTableModel) table.getModel();
					String data[] = new String[table.getColumnCount()];
					
					for (int i = 0; i < table.getColumnCount(); i++) {

						data[i] = mod.getValueAt(row, i).toString();
					}
					
					System.out.println(Arrays.toString(data));
					employee emp = addNewrow(row);
					int update = db.update(emp);
					
					if (update > 0) {

						// disable and hiden buton
						btnminsrow.setEnabled(false);
						table.setEnabled(false);
						
						btnSave.setVisible(false);
						btncancel.setVisible(false);
						
						JOptionPane.showMessageDialog(table, "Employee Update sucsseful");

						// get all details in selected row

					} else {
						JOptionPane.showMessageDialog(null, "error ecourd in save", "Error ",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnSave.setBounds(730, 470, 89, 23);
		panel.add(btnSave);

		btncancel = new JButton("cancel");
		btncancel.setVisible(false);
		btncancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!table.getValueAt(row, 1).equals("") || !table.getValueAt(row, 2).equals("")
						|| !table.getValueAt(row, 3).equals("") || !table.getValueAt(row, 4).equals("")
						|| !table.getValueAt(row, 6).equals("") || !table.getValueAt(row, 7).equals("")) {
					Toolkit.getDefaultToolkit().beep();
					if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(table, "Â·  —Ìœ «·€«¡ «·Õ›Ÿ   ø",
							" √ﬂÌœ «·⁄„·Ì…", JOptionPane.YES_NO_OPTION)) {
						return;
					}
				}

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				for (int i = 0; i < rowcounter; i++) {
					model.removeRow(row);
					row--;

				}
				rowcounter = 0;
				// disable buttons
				table.setEnabled(false);
				btnSave.setVisible(false);
				btncancel.setVisible(false);

			}
		});
		btncancel.setBounds(202, 470, 89, 23);
		panel.add(btncancel);

		btnminsrow = new JButton("-");
		btnminsrow.setEnabled(false);
		btnminsrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int r = row;
				if (rowcounter == 0) {

					table.setEnabled(false);
					btnSave.setVisible(false);
					btncancel.setVisible(false);
					btnminsrow.setEnabled(false);
					return;

				}
				if (!table.getValueAt(row, 1).equals("") || !table.getValueAt(row, 2).equals("")
						|| !table.getValueAt(row, 3).equals("") || !table.getValueAt(row, 4).equals("")
						|| !table.getValueAt(row, 6).equals("") || !table.getValueAt(row, 7).equals("")) {
					Toolkit.getDefaultToolkit().beep();
					if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(table, "Â·  —Ìœ «·€«¡ «·Õ›Ÿ   ø",
							" √ﬂÌœ «·⁄„·Ì…", JOptionPane.YES_NO_OPTION)) {
						return;
					}
				}
				System.out.println(row);
				model.removeRow(row);
				rowcounter--;
				row--;

			}
		});
		btnminsrow.setToolTipText("delete added row");
		btnminsrow.setForeground(new Color(0, 128, 0));
		btnminsrow.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnminsrow.setBounds(193, 58, 49, 25);
		panel.add(btnminsrow);

		// scrollPane.add(table);
		/*
		 * UtilDateModel dModel = new UtilDateModel(); JDatePanelImpl datepanel
		 * = new JDatePanelImpl(dModel); JDatePickerImpl datepicker = new
		 * JDatePickerImpl(datepanel); //datepicker.setBounds(213, 5, 202, 23);
		 */

		JPanel panel_3 = new JPanel();
		panel_3.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
				insert inserttoAll = new insert();

			}
		});
		tabbedPane.addTab("Register Attendance and Absence ", null, panel_3,
				"Register Attendance and Absence to all Employees");

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("insert new emplue", null, panel_1, null);
		panel_1.setLayout(null);
		// panel_1.add(datepicker);

		JLabel lbl = new JLabel("please insert all information");
		lbl.setBounds(37, 25, 221, 14);
		panel_1.add(lbl);

		JLabel lblNewLabel = new JLabel("first name");
		lblNewLabel.setBounds(37, 90, 68, 14);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("last name");
		lblNewLabel_1.setBounds(295, 90, 68, 14);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("age");
		lblNewLabel_2.setBounds(37, 181, 52, 14);
		panel_1.add(lblNewLabel_2);

		// ==========

		// ===============

		tfFN = new JTextField();
		tfFN.setBounds(115, 87, 143, 20);
		panel_1.add(tfFN);
		tfFN.setColumns(10);

		tfLN = new JTextField();
		tfLN.setBounds(369, 90, 143, 20);
		panel_1.add(tfLN);
		tfLN.setColumns(10);

		tfAG = new JTextField();
		tfAG.setBounds(108, 178, 52, 20);
		panel_1.add(tfAG);
		tfAG.setColumns(10);

		JLabel lblSalary = new JLabel("salary");
		lblSalary.setBounds(186, 178, 52, 14);
		panel_1.add(lblSalary);

		JLabel lblNewLabel_3 = new JLabel("Department");
		lblNewLabel_3.setBounds(352, 181, 82, 14);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("vacation");
		lblNewLabel_4.setBounds(37, 244, 82, 14);
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("balane");
		lblNewLabel_5.setBounds(37, 307, 44, 14);
		panel_1.add(lblNewLabel_5);

		tfSalary = new JTextField();
		tfSalary.setBounds(245, 175, 82, 20);
		panel_1.add(tfSalary);
		tfSalary.setColumns(10);

		tfvac = new JTextField();
		tfvac.setBounds(105, 241, 68, 20);
		panel_1.add(tfvac);
		tfvac.setColumns(10);

		tfBalance = new JTextField();
		tfBalance.setBounds(105, 304, 68, 20);
		panel_1.add(tfBalance);
		tfBalance.setColumns(10);
		/*
		 * JLabel lblNewLabel_6 = new JLabel(); lblNewLabel_6.setIcon(new
		 * ImageIcon(pan.class.getResource("/img/user%20group.png")));
		 * lblNewLabel_6.setBounds(273, 244, 286, 117);
		 * panel_1.add(lblNewLabel_6);
		 */
		btnInsert = new JButton("insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// panel.removeAll();
				// String sid = tfID.getText();
				String sfn = tfFN.getText();
				String stb = tfLN.getText();
				int total = Integer.parseInt(stb);
				String sag = tfAG.getText();
				String ssalary = tfSalary.getText();
				ComboBoxModel<String> model = comboBox.getModel();
				int depart = comboBox.getSelectedIndex();
				String sdepartment = (String) model.getElementAt(depart);

				String svac = tfvac.getText();
				String sbal = tfBalance.getText();

				// int id = Integer.parseInt(sid);
				int age = Integer.parseInt(sag);
				float salary = Float.parseFloat(ssalary);
				int vac = Integer.parseInt(svac);
				int bal = Integer.parseInt(sbal);
				employee emp = new employee();
				// emp.setId(id);
				emp.setFirstName(sfn);
				emp.settotal(total);
				emp.setAg(age);
				emp.setSalary(salary);
				emp.setDepartment(sdepartment);
				emp.setVacation(vac);
				emp.setVbalance(bal);

				int statue = db.save(emp);

				if (statue > 0) {
					// ›Ï Õ«·… ‰Ã«Õ «·⁄„·Ì… Ì „ «÷«›… «·’› «·ÃœÌœ

					DefaultTableModel mod = (DefaultTableModel) table.getModel();
					mod.setRowCount(0);

					for (employee list : db.getemploye()) {
						mod.addRow(new Object[] { list.getId(), list.getFirstName(), list.getTotalbalance(),
								list.getAg(), list.getSalary(), list.getDepartment(), list.getVacation(),
								list.getVbalance() });

					}

					JOptionPane.showMessageDialog(null, ("employe ( " + tfFN.getText() + " ) added secssefully"));

				} else {
					JOptionPane.showMessageDialog(null, "error", "error", JOptionPane.ERROR_MESSAGE);
					System.out.println("err");
				}

			}
		});
		btnInsert.setBounds(352, 372, 89, 23);
		panel_1.add(btnInsert);

		btnReset = new JButton("reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tfFN.setText("");
				tfLN.setText("");
				tfAG.setText("");
				tfSalary.setText("");

				tfvac.setText("");
				tfBalance.setText("");

			}
		});
		btnReset.setBounds(482, 372, 89, 23);
		panel_1.add(btnReset);

		panel_2 = new JPanel();
		tabbedPane.addTab("update employe / Delete", null, panel_2, null);
		panel_2.setLayout(null);

		// =========
		JTextField tfFN_2 = new JTextField();
		tfFN_2.setBounds(115, 87, 143, 20);
		panel_2.add(tfFN_2);
		tfFN_2.setColumns(10);

		JTextField tfLN_2 = new JTextField();
		tfLN_2.setBounds(369, 90, 213, 20);
		panel_2.add(tfLN_2);
		tfLN_2.setColumns(10);

		JTextField tfAG_2 = new JTextField();
		tfAG_2.setBounds(108, 178, 52, 20);
		panel_2.add(tfAG_2);
		tfAG_2.setColumns(10);

		JLabel lblSalary_2 = new JLabel("salary");
		lblSalary_2.setBounds(186, 178, 52, 14);
		panel_2.add(lblSalary_2);

		JLabel lblNewLabel_3_2 = new JLabel("Department");
		lblNewLabel_3_2.setBounds(352, 181, 82, 14);
		panel_2.add(lblNewLabel_3_2);

		JLabel lblNewLabel_4_2 = new JLabel("vacation");
		lblNewLabel_4_2.setBounds(37, 244, 82, 14);
		panel_2.add(lblNewLabel_4_2);

		JLabel lblNewLabel_5_2 = new JLabel("balane");
		lblNewLabel_5_2.setBounds(37, 307, 44, 14);
		panel_2.add(lblNewLabel_5_2);

		JTextField tfSalary_2 = new JTextField();
		tfSalary_2.setBounds(245, 175, 82, 20);
		panel_2.add(tfSalary_2);
		tfSalary_2.setColumns(10);

		JTextField tfDepartent_2 = new JTextField();
		tfDepartent_2.setBounds(444, 172, 138, 20);
		panel_2.add(tfDepartent_2);
		tfDepartent_2.setColumns(10);

		JTextField tfvac_2 = new JTextField();
		tfvac_2.setBounds(105, 241, 68, 20);
		panel_2.add(tfvac_2);
		tfvac_2.setColumns(10);

		JTextField tfBalance_2 = new JTextField();
		tfBalance_2.setBounds(105, 304, 68, 20);
		panel_2.add(tfBalance_2);

		JLabel lblFirstName = new JLabel("first name");
		lblFirstName.setBounds(37, 90, 68, 14);
		panel_2.add(lblFirstName);

		JLabel lblLastName = new JLabel("last name");
		lblLastName.setBounds(281, 93, 68, 14);
		panel_2.add(lblLastName);

		JLabel lblAge = new JLabel("age");
		lblAge.setBounds(37, 181, 46, 14);
		panel_2.add(lblAge);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(35, 34, 46, 31);
		panel_2.add(lblId);

		tfID_2 = new JTextField();
		tfID_2.setBounds(115, 31, 52, 34);
		panel_2.add(tfID_2);
		tfID_2.setColumns(10);

		JButton btnNewButton = new JButton("find");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = tfID_2.getText();
				int sid = Integer.parseInt(s);
				employee emp = db.getEmployeeid(sid);
				tfFN_2.setText(emp.getFirstName());
				tfLN_2.setText(String.valueOf(emp.getTotalbalance()));
				tfAG_2.setText(String.valueOf(emp.getAg()));
				tfSalary_2.setText(String.valueOf(emp.getSalary()));
				tfDepartent_2.setText(emp.getDepartment());
				tfvac_2.setText(String.valueOf(emp.getVacation()));
				tfBalance_2.setText(String.valueOf(emp.getVbalance()));

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(181, 30, 68, 35);
		panel_2.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("update employe");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = tfID_2.getText();
				int sid = Integer.parseInt(s);
				String sfn = tfFN_2.getText();
				String stb = tfLN_2.getText();
				int total = Integer.parseInt(stb);
				int sage = Integer.parseInt(tfAG_2.getText());
				float ssalary = Float.parseFloat(tfSalary_2.getText());
				String sDepart = tfDepartent_2.getText();
				int sval = Integer.parseInt(tfvac_2.getText());
				int sbal = Integer.parseInt(tfBalance_2.getText());
				employee emp = new employee();
				emp.setId(sid);
				emp.setFirstName(sfn);
				emp.settotal(total);
				emp.setAg(sage);
				emp.setSalary(ssalary);
				emp.setDepartment(sDepart);
				emp.setVacation(sval);
				emp.setVbalance(sbal);
				int st = db.update(emp);
				if (st > 0) {
					JOptionPane.showMessageDialog(null, ("employe ( " + tfFN_2.getText() + " ) update sucsseful "));
				} else {
					JOptionPane.showMessageDialog(null, "employe", "error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnNewButton_1.setBounds(495, 276, 124, 23);
		panel_2.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Delete employe");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfID_2.getText();
				int sid = Integer.parseInt(id);
				// DBInfo.delete(sid);

				int stat = db.delete(sid,"");
				if (stat > 0) {
					JOptionPane.showMessageDialog(null, ("employe ( " + tfFN_2.getText() + " ) Deleted sucsseful "));
				} else {
					JOptionPane.showMessageDialog(null, "an error has done ", "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_2.setBounds(495, 343, 124, 23);
		panel_2.add(btnNewButton_2);
		tfBalance.setColumns(10);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(444, 181, 138, 20);
		panel_1.add(comboBox);
		for (Depart dep : db.getDepartment()) {
			comboBox.addItem(dep.getDepartment());
		}

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(23, 50, 262, 14);
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

		JMenuItem mntmEditEmployee = new JMenuItem("Edit Employee");
		mntmEditEmployee.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		
		mnHome.add(mntmEditEmployee);
		/*
		 * stoped here method to update
		 */
		/// stoped here method to update
		mntmEditEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				edit_employe(e);
			
			}

		});

		JMenuItem mntmDeleteEmployee = new JMenuItem("delete employee");
		mntmDeleteEmployee.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		mnHome.add(mntmDeleteEmployee);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
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
			if (col == 1 && row >= 0 && row < table.getRowCount()) {
				Toolkit.getDefaultToolkit().beep();

				String dialogname = (String) table.getValueAt(row, col);
				dialog.setName(dialogname);
				dialog.setTitle("Register Attendance and Absence to ( " + dialogname + " )");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(table);

				dialog.setVisible(true);

			} else

				JOptionPane.showMessageDialog(null, "please chose employee name ");
			// JOptionPane.showMessageDialog(null, ""+table.getValueAt(row,
			// col));
		}

	}

	private void master_componentShown(ComponentEvent e) {
		if (!db.Connect()) {
			Toolkit.getDefaultToolkit().beep();
			UIManager.put("OptionPane.okButtonText", "„Ê«›‹‹ﬁ");
			JOptionPane.showMessageDialog(this, "·ﬁœ ÕœÀ Œÿ√ ›Ï «·« ’«· »ﬁ«⁄œ… «·»Ì«‰« \n”Ì „ «€·«ﬁ «·»—‰«„Ã.",
					"Œÿ‹‹‹‹‹‹‹‹‹‹‹‹‹‹‹‹√", JOptionPane.ERROR_MESSAGE);
			this_windowClosing(null);// ----
		}

	}

	private void this_windowClosing(WindowEvent e) {

		// ********************

		UIManager.put("OptionPane.yesButtonText", "‰⁄„");
		UIManager.put("OptionPane.noButtonText", "·«");

		if (btnSave.isShowing()) {
			Toolkit.getDefaultToolkit().beep();
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Â·  —Ìœ «·Œ—ÊÃ »œÊ‰ Õ›Ÿ «· €ÌÌ—«  ø",
					" √ﬂÌœ «·⁄„·Ì…", JOptionPane.YES_NO_OPTION)) {
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
		// important arrange

		table.setRowHeight(23);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JTableHeader renderer = table.getTableHeader();
		renderer.setBackground(Color.lightGray);
		renderer.setFont(new Font("Tahoma", Font.BOLD, 14));
		renderer.setForeground(Color.BLUE);
		renderer.setPreferredSize(new Dimension(renderer.getSize().width, 30));
		renderer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		DefaultTableCellRenderer crend = new DefaultTableCellRenderer();
		crend.setHorizontalAlignment(JLabel.CENTER);

		JComboBox combo = new JComboBox();
		combo.addItem("Õ«÷—");
		combo.addItem("€«∆»");
		combo.addItem("√Ã«“…");
		combo.addItem("€Ì«» „—÷Ï");
		combo.setFont(new Font("Tahoma", Font.BOLD, 13));
		combo.setForeground(Color.magenta);
		combo.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		TableColumn column = null;
		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 5) {
				column.setCellRenderer(crend);
				column.setPreferredWidth(250); // third column is bigger
			} else if (i == 4) {
				column.setCellEditor(new DefaultCellEditor(combo));
				DefaultTableCellRenderer comr = new DefaultTableCellRenderer();
				comr.setHorizontalAlignment(JLabel.CENTER);
				comr.setToolTipText("Click for combo box");
				column.setCellRenderer(comr);
				column.setPreferredWidth(80);
			} else {
				column.setPreferredWidth(65);
				column.setCellRenderer(crend);
			}

		}

	}
	private void edit_employe(ActionEvent e){
		
		btnSave.setVisible(true);
		btnSave.setText("Update");
		btncancel.setVisible(true);
		table.setEnabled(true);
		
		DefaultTableModel modl = (DefaultTableModel)table.getModel();
		modl.addColumn("");
		
		JComboBox combo = new JComboBox();

		DefaultTableCellRenderer crend = new DefaultTableCellRenderer();
		crend.setHorizontalAlignment(JLabel.CENTER);
		crend.add(new PopupMenu("wrong "));

		TableColumn column = null;
		column = table.getColumnModel().getColumn(5);
		crend.setHorizontalAlignment(JLabel.CENTER);
		crend.setToolTipText("Click for combo box");
		
		JCheckBox ch = new JCheckBox();
		TableColumn checkboxcolumn = null;
		checkboxcolumn = table.getColumnModel().getColumn(8);
		checkboxcolumn.setCellEditor(new DefaultCellEditor(ch));

		column.setCellEditor(new DefaultCellEditor(combo));
		ComboBoxModel<String> model_1 = combo.getModel();
		int depart = combo.getSelectedIndex();
		String departCell = (String) model_1.getElementAt(depart);

		//
		

		for (Depart dep : db.getDepartment()) {
			combo.addItem(dep.getDepartment());
		}

		boolean selected = false;
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 )
				{
					row =table.getSelectedRow();
				
				}
				
				System.out.println(row);
				
				
			}
		});
	
		
	
	}

	

	private employee addNewrow(int row) {
		int r = row;
	//	row =table.getSelectedRow();
		//DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		String sid =  String.valueOf(table.getValueAt(r , 0));
		String sfn =  String.valueOf( table.getValueAt(r , 1));
		String stb = String.valueOf(table.getValueAt(r , 2)) ;

		String sag = String.valueOf( table.getValueAt(r , 3));
		String ssalary =String.valueOf(table.getValueAt(r , 4));
		// ComboBoxModel<String> model = comboBox.getModel();
		// int depart = comboBox.getSelectedIndex();
		// String sdepartment = (String) model.getElementAt(depart);
		String sdepartment = String.valueOf( table.getValueAt(r, 5));
		String svac = String.valueOf( table.getValueAt(r , 6));
		String sbal = String.valueOf( table.getValueAt(r , 7));
		
		
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
	private LinkedList<employee> addNewrow2(int row) {
		LinkedList<employee> list = new LinkedList<>();
		
		int r = row;
		
		String sid =  String.valueOf(table.getValueAt(r , 0));
		String sfn =  String.valueOf( table.getValueAt(r , 1));
		String stb = String.valueOf(table.getValueAt(r , 2)) ;

		String sag = String.valueOf( table.getValueAt(r , 3));
		String ssalary =String.valueOf(table.getValueAt(r , 4));
		// ComboBoxModel<String> model = comboBox.getModel();
		// int depart = comboBox.getSelectedIndex();
		// String sdepartment = (String) model.getElementAt(depart);
		String sdepartment = String.valueOf( table.getValueAt(r, 5));
		String svac = String.valueOf( table.getValueAt(r , 6));
		String sbal = String.valueOf( table.getValueAt(r , 7));
		
		
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

		return list;

	}


	private void addNewDepartment(ActionEvent e) {
		DialoAddDepartment dt = new DialoAddDepartment();
		dt.setLocationRelativeTo(this);
		dt.setVisible(true);

	}

	public static void main(String[] args) {
		pan p;
		try {
			p = new pan();
			p.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// p.setLocationRelativeTo(this);

	}
}
