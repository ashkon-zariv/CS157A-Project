import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;

import java.awt.Component;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.TextArea;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.sql.*;
import java.text.NumberFormat;

import javax.swing.JList;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;



public class MainFrame {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://cs157a.cq50dnjomap4.us-west-2.rds.amazonaws.com:3306/mydb";

	//  Database credentials
	static final String USER = "guest";
	static final String PASS = "password";

	//  Database connection
	private static Connection conn = null;

	private JFrame frmApartmentManagementSystem;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField txtLocation;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_3;
	private JTextField textField_7;
	private JTextField requestField_1;
	private JTextField requestField_2;
	private JTextField requestField_3;
	private JTextField requestField_4;
	private JPasswordField passwordField_1;

	private String u_username;
	private String u_pass;
	private String u_bathrooms;
	private String u_bedrooms;

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private JTable table = new JTable();
	private JTextField bathroomsTextField;
	private JTextField minPriceTextField;
	private JTextField maxPriceTextField;
	private JTextField textField_2;
	private JTextField textField_4;

	private JPanel MainPanel;
	private JPanel SignInPanel;
	private JPanel SignUpPanel;
	private JPanel ReservationPanel;
	private JPanel AccountPanel;

	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_4;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;

	private JScrollPane scrollPane = new JScrollPane();
	int row = 200;
	int col = 7;
	int count = 0;
	Object[][] data = new Object[row][col];
	Object[][] data2;
	private JTable table_1;


	private static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
		return matcher.find();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Register JDBC driver
					Class.forName("com.mysql.jdbc.Driver");

					//Open a connection
					conn = DriverManager.getConnection(DB_URL,USER,PASS);

					MainFrame window = new MainFrame();
					window.frmApartmentManagementSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		SignInPanel();
		SignUpPanel();
		MainPanel();
		AccountPanel();
		ReservationPanel();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frmApartmentManagementSystem = new JFrame();
		frmApartmentManagementSystem.setTitle("KaoZariv Apartment Database - CS 157A");
		frmApartmentManagementSystem.setBounds(100, 100, 750, 420);
		frmApartmentManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmApartmentManagementSystem.getContentPane().setLayout(new CardLayout(0, 0));


	}
	public void MainPanel()
	{
		MainPanel = new JPanel();
		frmApartmentManagementSystem.getContentPane().add(MainPanel, "name_1072333189294654");
		MainPanel.setLayout(null);

		scrollPane.setBounds(10, 11, 513, 360);
		MainPanel.add(scrollPane);

		JLabel zipLabel = new JLabel("Zip Code");
		zipLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
		zipLabel.setBounds(554, 52, 86, 26);
		MainPanel.add(zipLabel);

		JLabel bedroomsLabel = new JLabel("# of Bedrooms");
		bedroomsLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
		bedroomsLabel.setBounds(554, 89, 122, 26);
		MainPanel.add(bedroomsLabel);

		JLabel bathroomsLabel = new JLabel("# of Bathrooms");
		bathroomsLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
		bathroomsLabel.setBounds(555, 126, 122, 26);
		MainPanel.add(bathroomsLabel);

		JLabel minPriceLabel = new JLabel("Min. Price   $");
		minPriceLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
		minPriceLabel.setBounds(533, 163, 122, 26);
		MainPanel.add(minPriceLabel);

		JLabel maxPriceLabel = new JLabel("Max Price   $");
		maxPriceLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
		maxPriceLabel.setBounds(533, 200, 122, 26);
		MainPanel.add(maxPriceLabel);

		bathroomsTextField = new JTextField();
		bathroomsTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bathroomsTextField.setColumns(10);
		bathroomsTextField.setBounds(675, 126, 49, 26);
		MainPanel.add(bathroomsTextField);

		minPriceTextField = new JTextField();
		minPriceTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		minPriceTextField.setColumns(10);
		minPriceTextField.setBounds(638, 165, 86, 26);
		MainPanel.add(minPriceTextField);

		maxPriceTextField = new JTextField();
		maxPriceTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		maxPriceTextField.setColumns(10);
		maxPriceTextField.setBounds(638, 203, 86, 24);
		MainPanel.add(maxPriceTextField);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(675, 91, 49, 26);
		MainPanel.add(textField_2);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(638, 52, 86, 26);
		MainPanel.add(textField_4);

		JLabel lblFilterResults = new JLabel("Filter Results");
		lblFilterResults.setForeground(Color.GRAY);
		lblFilterResults.setHorizontalAlignment(SwingConstants.CENTER);
		lblFilterResults.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		lblFilterResults.setBounds(554, 18, 157, 26);
		MainPanel.add(lblFilterResults);

		try
		{
			PreparedStatement dataStatement = conn.prepareStatement("SELECT * FROM apartments");
			ResultSet dataSet = dataStatement.executeQuery();
			int count = 0;

			while(count < row)
			{
				dataSet.next();

				String temp = dataSet.getString("price");
				int temp2 = dataSet.getInt("apartment_id");
				String temp3 = dataSet.getString("name");
				String temp4 = dataSet.getString("zip_code");
				int temp5 = dataSet.getInt("accommodates");
				String temp6 = dataSet.getString("bedrooms");
				String temp7 = dataSet.getString("bathrooms");

				data[count][6] = temp;
				data[count][0] = temp2;
				data[count][1] = temp3;
				data[count][2] = temp4;
				data[count][3] = temp5;
				data[count][4] = temp6;
				data[count][5] = temp7;

				count++;
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		createTable();
	}

	public void createTable()
	{
		table.setModel(new DefaultTableModel(
				data,
				new String[] {
						"Apt ID", "Description", "Zip Code", "# PPL", "Bath", "Bed", "Price"
				}
				) {
			Class<?>[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, Integer.class, String.class, String.class, String.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(55);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(55);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(55);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(75);

		scrollPane.setViewportView(table);

		JButton filterButton = new JButton("Filter");
		filterButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		filterButton.setBounds(533, 249, 191, 35);
		MainPanel.add(filterButton);

		filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int count2 = 0;
					StringBuffer sb = new StringBuffer("SELECT * FROM apartments WHERE");

					boolean query_b = false;
					String zip_q = String.valueOf(textField_4.getText());
					String minP_q = String.valueOf(minPriceTextField.getText());
					String maxP_q = String.valueOf(maxPriceTextField.getText());
					String bed_q = String.valueOf(textField_2.getText());
					String bath_q = String.valueOf(bathroomsTextField.getText());

					PreparedStatement dataStatement;
					ResultSet result;

					if(zip_q.length() == 5)
					{
						if(!query_b){
							sb.append(" zip_code = " + zip_q);
							query_b = true;
						}
						else{
							sb.append(" AND zip_code = " + zip_q);
						}
					}

					if(!minP_q.equals(""))
					{
						if(!query_b){
							sb.append(" price >= " + minP_q);
							query_b = true;
						}
						else{
							sb.append(" AND price >= " + minP_q);
						}
					}

					if(!maxP_q.equals(""))
					{
						if(!query_b){
							sb.append(" price <= " + maxP_q);
							query_b = true;
						}
						else{
							sb.append(" AND price <= " + maxP_q);
						}
					}

					if(!bed_q.equals(""))
					{
						if(!query_b){
							sb.append(" bedrooms = " + bed_q);
							query_b = true;
						}
						else{
							sb.append(" AND bedrooms = " + bed_q);
						}
					}

					if(!bath_q.equals(""))
					{
						if(!query_b){
							sb.append(" bathrooms = " + bath_q);
							query_b = true;
						}
						else{
							sb.append(" AND bathrooms = " + bath_q);
						}
					}

					if(query_b)
					{
						String sqlStatement = sb.toString();
						dataStatement = conn.prepareStatement(sqlStatement + " ORDER BY price"); 
						result = dataStatement.executeQuery();
					}
					else
					{
						dataStatement = conn.prepareStatement("SELECT * FROM apartments");
						result = dataStatement.executeQuery();
					}

					while(count2 < row)
					{
						result.next();
						String temp = result.getString("price");
						int temp2 = result.getInt("apartment_id");
						String temp3 = result.getString("name");
						String temp4 = result.getString("zip_code");
						int temp5 = result.getInt("accommodates");
						String temp7 = result.getString("bedrooms");
						String temp6 = result.getString("bathrooms");

						table.getModel().setValueAt(temp, count2, 6);
						table.getModel().setValueAt(temp2, count2, 0);
						table.getModel().setValueAt(temp3, count2, 1);
						table.getModel().setValueAt(temp4, count2, 2);
						table.getModel().setValueAt(temp5, count2, 3);
						table.getModel().setValueAt(temp6, count2, 4);
						table.getModel().setValueAt(temp7, count2, 5);
						count2++;

						if(result.isLast())
							break;
					}

					table.repaint();
				}
				catch(SQLException sx)
				{
					sx.printStackTrace();
				}
			}
		});

		JButton accountButton = new JButton("Account Status");
		accountButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		accountButton.setBounds(533, 295, 191, 58);
		MainPanel.add(accountButton);

		//Account Info - Main Panel
		accountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement ps;
				try{
					ps = conn.prepareStatement("SELECT * FROM customers WHERE email = ? AND password = ?");
					ps.setString(1, u_username);
					ps.setString(2, u_pass);

					ResultSet result = ps.executeQuery();
					result.next();

					lblNewLabel_4.setText("Name: " + result.getString("name"));
					label_7.setText("E-mail: " + result.getString("email"));
					label_8.setText("Address: " + result.getString("address"));
					label_9.setText("City: " + result.getString("city"));
					label_10.setText("State: " + result.getString("state"));
					label_11.setText("Zip Code: " + result.getString("zip_code"));

				} catch (SQLException ex) {
					ex.printStackTrace();
				}

				MainPanel.setVisible(false);
				AccountPanel.setVisible(true);

			}
		});

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				Point p = me.getPoint();
				int row = table.rowAtPoint(p);
				Object o = new Object();
				if(me.getClickCount() == 2)
				{
					for(int i = 0; i < col; i++)
					{
						o = table.getModel().getValueAt(row, i);
						table_1.getModel().setValueAt(o, count, i);
					}
					count++;
					MainPanel.setVisible(false);
					ReservationPanel.setVisible(true);
				}
			}
		});
	}

	//SignInPanel elements
	public void SignInPanel()
	{
		SignInPanel = new JPanel();
		frmApartmentManagementSystem.getContentPane().add(SignInPanel, "name_1059160628680983");
		SignInPanel.setLayout(null);
		SignInPanel.setVisible(true);

		Button button = new Button("Sign Up");
		button.setFont(new Font("Arial", Font.BOLD, 16));
		button.setBounds(148, 205, 177, 52);
		SignInPanel.add(button);

		Button button_1 = new Button("Sign In");
		button_1.setFont(new Font("Arial", Font.BOLD, 16));
		button_1.setBounds(392, 205, 185, 52);
		SignInPanel.add(button_1);

		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setBounds(178, 80, 389, 30);
		SignInPanel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
		passwordField.setBounds(177, 136, 390, 30);
		SignInPanel.add(passwordField);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setBounds(80, 83, 88, 20);
		SignInPanel.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblPassword.setBounds(80, 139, 88, 20);
		SignInPanel.add(lblPassword);

		lblNewLabel_1 = new JLabel("User added successfully!");
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(105, 297, 220, 30);
		SignInPanel.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);

		JLabel lblNewLabel_2 = new JLabel("User not found!");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(468, 297, 172, 30);
		SignInPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_5 = new JLabel("Apartment Finder Gateway");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Berlin Sans FB", Font.PLAIN, 24));
		lblNewLabel_5.setBounds(161, 22, 389, 30);
		SignInPanel.add(lblNewLabel_5);
		lblNewLabel_2.setVisible(false);

		//SignUp Button - SignIn Panel
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignInPanel.setVisible(false);
				SignUpPanel.setVisible(true);
			}
		});

		//SignIn Button - SignIn Panel
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement("SELECT email, password FROM customers WHERE email = ? AND password = ?");

					u_username = String.valueOf(textField.getText());
					u_pass = String.valueOf(passwordField.getPassword());

					ps.setString(1, u_username);
					ps.setString(2, u_pass);

					ResultSet result = ps.executeQuery();
					if(result.next()){
						JOptionPane.showMessageDialog(null, "Successfully logged in.");
						MainPanel.setVisible(true);
						SignInPanel.setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(null, "Invalid email or password");
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	public void SignUpPanel()
	{

		//SignUpPanel elements
		SignUpPanel = new JPanel();
		frmApartmentManagementSystem.getContentPane().add(SignUpPanel, "name_1059917700298584");
		SignUpPanel.setLayout(null);
		SignUpPanel.setVisible(false);

		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnNewButton.setBounds(256, 267, 191, 44);
		SignUpPanel.add(btnNewButton);

		textField_1 = new JTextField();
		textField_1.setBounds(146, 11, 370, 30);
		SignUpPanel.add(textField_1);
		textField_1.setColumns(10);

		txtLocation = new JTextField();
		txtLocation.setColumns(10);
		txtLocation.setBounds(146, 175, 370, 30);
		SignUpPanel.add(txtLocation);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(146, 134, 370, 30);
		SignUpPanel.add(textField_5);

		JLabel lblUsername_1 = new JLabel("E-mail\r\n");
		lblUsername_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblUsername_1.setBounds(99, 14, 86, 22);
		SignUpPanel.add(lblUsername_1);

		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblPassword_1.setBounds(74, 56, 86, 22);
		SignUpPanel.add(lblPassword_1);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(146, 93, 370, 30);
		SignUpPanel.add(textField_6);

		JLabel lblConfirmPassword = new JLabel("Name\r\n");
		lblConfirmPassword.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblConfirmPassword.setBounds(99, 93, 127, 22);
		SignUpPanel.add(lblConfirmPassword);

		JLabel lblMaxValue = new JLabel("Address\r\n");
		lblMaxValue.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblMaxValue.setBounds(88, 137, 127, 22);
		SignUpPanel.add(lblMaxValue);

		JLabel lblLocation = new JLabel("City");
		lblLocation.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblLocation.setBounds(109, 178, 127, 22);
		SignUpPanel.add(lblLocation);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(146, 216, 183, 30);
		SignUpPanel.add(textField_3);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(441, 216, 183, 30);
		SignUpPanel.add(textField_7);

		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblState.setBounds(99, 219, 127, 22);
		SignUpPanel.add(lblState);

		JLabel lblZipCode = new JLabel("ZIP Code");
		lblZipCode.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblZipCode.setBounds(371, 219, 127, 22);
		SignUpPanel.add(lblZipCode);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(146, 52, 370, 30);
		SignUpPanel.add(passwordField_1);

		final JLabel lblNewLabel = new JLabel("Error(s) found in marked text field(s).");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(21, 275, 257, 30);
		SignUpPanel.add(lblNewLabel);
		lblNewLabel.setVisible(false);

		//Error asterisks

		final JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(528, 19, 46, 14);
		SignUpPanel.add(label);
		label.setVisible(false);

		final JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_1.setBounds(528, 60, 46, 14);
		SignUpPanel.add(label_1);
		label_1.setVisible(false);

		final JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_2.setBounds(528, 101, 46, 14);
		SignUpPanel.add(label_2);
		label_2.setVisible(false);

		final JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_3.setBounds(528, 140, 46, 14);
		SignUpPanel.add(label_3);
		label_3.setVisible(false);

		final JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_4.setBounds(528, 183, 46, 14);
		SignUpPanel.add(label_4);
		label_4.setVisible(false);

		final JLabel label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_5.setBounds(336, 224, 46, 14);
		SignUpPanel.add(label_5);
		label_5.setVisible(false);

		final JLabel label_6 = new JLabel("*");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_6.setBounds(634, 224, 46, 14);
		SignUpPanel.add(label_6);
		label_6.setVisible(false);


		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				char[] password = passwordField_1.getPassword();
				String string_pass = new String(password);

				String email = "'" + textField_1.getText() + "', ";
				String pass = "'" + string_pass + "', ";
				String name = "'" + textField_6.getText()+ "', ";
				String address = "'" + textField_5.getText() + "', ";
				String city = "'" + txtLocation.getText() + "', ";
				String state = "'" + textField_3.getText() + "', ";
				String zip = "'" + textField_7.getText() + "'";

				if(!validate(textField_1.getText()))
				{
					label.setVisible(true);
					lblNewLabel.setVisible(true);
					textField_1.setText("");
				}
				else
				{
					label.setVisible(false);
				}

				if(password.length == 0)
				{
					label_1.setVisible(true);
					lblNewLabel.setVisible(true);
					passwordField_1.setText("");
				}
				else
				{
					label_1.setVisible(false);
				}

				if(textField_6.getText().length() == 0)
				{
					label_2.setVisible(true);
					lblNewLabel.setVisible(true);
					textField_6.setText("");
				}
				else
				{
					label_2.setVisible(false);
				}

				if(textField_5.getText().length() == 0)
				{
					label_3.setVisible(true);
					lblNewLabel.setVisible(true);
					textField_5.setText("");
				}
				else
				{
					label_3.setVisible(false);
				}

				if(txtLocation.getText().length() == 0)
				{
					label_4.setVisible(true);
					lblNewLabel.setVisible(true);
					txtLocation.setText("");
				}
				else
				{
					label_4.setVisible(false);
				}

				if(textField_3.getText().length() == 0)
				{
					label_5.setVisible(true);
					lblNewLabel.setVisible(true);
					textField_3.setText("");
				}
				else
				{
					label_5.setVisible(false);
				}

				if(textField_7.getText().length() != 5)
				{
					label_6.setVisible(true);
					lblNewLabel.setVisible(true);
					textField_7.setText("");
				}
				else
				{
					label_6.setVisible(false);
				}

				if(!label.isVisible() && !label_1.isVisible() && !label_2.isVisible()
						&& !label_3.isVisible() && !label_4.isVisible()
						&& !label_5.isVisible() && !label_6.isVisible())
				{
					try{
						Statement stmt = conn.createStatement();
						String sql = "INSERT INTO customers "
								+ "VALUES ('0', " + email + pass + name 
								+ address + city + state + zip + ");";
						stmt.executeUpdate(sql);
					}
					catch (Exception ex){
						ex.printStackTrace();
					}

					SignUpPanel.setVisible(false);
					SignInPanel.setVisible(true);
					lblNewLabel_1.setVisible(true);
				}
			}
		});
	}
	public void ReservationPanel()
	{
		ReservationPanel = new JPanel();
		frmApartmentManagementSystem.getContentPane().add(ReservationPanel, "name_1059165859655842");
		ReservationPanel.setLayout(null);
		ReservationPanel.setVisible(false);

		requestField_1 = new JTextField();
		requestField_1.setBounds(146, 91, 370, 30);
		ReservationPanel.add(requestField_1);
		requestField_1.setColumns(10);

		requestField_2 = new JTextField();
		requestField_2.setColumns(10);
		requestField_2.setBounds(146, 132, 370, 30);
		ReservationPanel.add(requestField_2);

		requestField_3 = new JTextField();
		requestField_3.setColumns(10);
		requestField_3.setBounds(146, 173, 370, 30);
		ReservationPanel.add(requestField_3);

		requestField_4 = new JTextField();
		requestField_4.setColumns(10);
		requestField_4.setBounds(146, 214, 370, 30);
		ReservationPanel.add(requestField_4);

		JButton searchButton = new JButton("Request");
		searchButton.setFont(new Font("Arial Black", Font.PLAIN, 16));
		searchButton.setBounds(256, 267, 191, 44);
		ReservationPanel.add(searchButton);

		JLabel location = new JLabel("Location\r\n");
		location.setFont(new Font("Arial Black", Font.PLAIN, 12));
		location.setBounds(79, 99, 57, 22);
		ReservationPanel.add(location);

		JLabel startDate = new JLabel("Start Date\r\n");
		startDate.setFont(new Font("Arial Black", Font.PLAIN, 12));
		startDate.setBounds(69, 135, 67, 22);
		ReservationPanel.add(startDate);

		JLabel endDate = new JLabel("End Date\r\n");
		endDate.setFont(new Font("Arial Black", Font.PLAIN, 12));
		endDate.setBounds(74, 176, 62, 22);
		ReservationPanel.add(endDate);

		JLabel guest = new JLabel("Number of Guests\r\n");
		guest.setFont(new Font("Arial Black", Font.PLAIN, 12));
		guest.setBounds(19, 217, 117, 22);
		ReservationPanel.add(guest);


		final JLabel requestLabel = new JLabel("Error(s) found in marked text field(s).");
		requestLabel.setForeground(Color.RED);
		requestLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		requestLabel.setBounds(21, 275, 257, 30);
		ReservationPanel.add(requestLabel);
		requestLabel.setVisible(false);

		//Error asterisks

		final JLabel requestLabel_0 = new JLabel("*");
		requestLabel_0.setForeground(Color.RED);
		requestLabel_0.setFont(new Font("Tahoma", Font.BOLD, 16));
		requestLabel_0.setBounds(526, 97, 46, 14);
		ReservationPanel.add(requestLabel_0);
		requestLabel_0.setVisible(false);

		final JLabel requestLabel_1 = new JLabel("*");
		requestLabel_1.setForeground(Color.RED);
		requestLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		requestLabel_1.setBounds(526, 138, 46, 14);
		ReservationPanel.add(requestLabel_1);
		requestLabel_1.setVisible(false);

		final JLabel requestLabel_2 = new JLabel("*");
		requestLabel_2.setForeground(Color.RED);
		requestLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		requestLabel_2.setBounds(526, 179, 46, 14);
		ReservationPanel.add(requestLabel_2);
		requestLabel_2.setVisible(false);

		final JLabel requestLabel_3 = new JLabel("*");
		requestLabel_3.setForeground(Color.RED);
		requestLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		requestLabel_3.setBounds(526, 220, 46, 14);
		ReservationPanel.add(requestLabel_3);
		requestLabel_3.setVisible(false);

		JLabel lblNewLabel_3 = new JLabel("Requests");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(302, 11, 117, 38);
		ReservationPanel.add(lblNewLabel_3);

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String location = "'" + requestField_1.getText() + "', ";
				String startDate = "'" + requestField_2.getText() + "', ";
				String endDate = "'" + requestField_3.getText()+ "', ";
				String guests = "'" + requestField_4.getText() + "'";
				if(requestField_1.getText().length() == 0)
				{
					requestLabel_0.setVisible(true);
					requestLabel.setVisible(true);
					requestField_1.setText("");
				}
				else
				{
					requestLabel_0.setVisible(false);
				}
				if(requestField_2.getText().length() == 0)
				{
					requestLabel_1.setVisible(true);
					requestLabel.setVisible(true);
					requestField_2.setText("");
				}
				else
				{
					requestLabel_1.setVisible(false);
				}
				if(requestField_3.getText().length() == 0)
				{
					requestLabel_2.setVisible(true);
					requestLabel.setVisible(true);
					requestField_3.setText("");
				}
				else
				{
					requestLabel_2.setVisible(false);
				}
				if(requestField_4.getText().length() == 0 || !(requestField_4.getText().matches("^[+-]?\\d+$")))
				{
					requestLabel_3.setVisible(true);
					requestLabel.setVisible(true);
					requestField_4.setText("");
				}
				else
				{
					requestLabel_3.setVisible(false);
				}
				if(!requestLabel_0.isVisible() && !requestLabel_1.isVisible() && !requestLabel_2.isVisible()
						&& !requestLabel_3.isVisible())
				{
					try{
						Statement stmt = conn.createStatement();
						String sql = "INSERT INTO requests "
								+ "VALUES ('0', '0', " + location + startDate + endDate 
								+ guests + ");";
						stmt.executeUpdate(sql);
						PreparedStatement ps;
						ps = conn.prepareStatement("SELECT * FROM customers WHERE email = ? AND password = ?");
						ps.setString(1, u_username);
						ps.setString(2, u_pass);

						ResultSet result = ps.executeQuery();
						result.next();

						lblNewLabel_4.setText("Name: " + result.getString("name"));
						label_7.setText("E-mail: " + result.getString("email"));
						label_8.setText("Address: " + result.getString("address"));
						label_9.setText("City: " + result.getString("city"));
						label_10.setText("State: " + result.getString("state"));
						label_11.setText("Zip Code: " + result.getString("zip_code"));
						
					}
					catch (Exception ex){
						ex.printStackTrace();
					}

					ReservationPanel.setVisible(false);
					AccountPanel.setVisible(true);
				}
			}
		});
	}
	public void AccountPanel()
	{
		AccountPanel = new JPanel();
		frmApartmentManagementSystem.getContentPane().add(AccountPanel, "name_77351655327903");
		AccountPanel.setLayout(null);

		//Account Panel
		JLabel lblNewLabel_31 = new JLabel("Your Account Information");
		lblNewLabel_31.setFont(new Font("Arial Black", Font.PLAIN, 24));
		lblNewLabel_31.setBounds(202, 11, 374, 35);
		AccountPanel.add(lblNewLabel_31);

		JButton btnNewButton_1 = new JButton("Back to Search");
		btnNewButton_1.setBounds(22, 17, 139, 35);
		AccountPanel.add(btnNewButton_1);

		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(22, 92, 200, 29);
		AccountPanel.add(lblNewLabel_4);

		label_7 = new JLabel("");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_7.setBounds(22, 132, 200, 29);
		AccountPanel.add(label_7);

		label_8 = new JLabel("");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_8.setBounds(22, 172, 200, 29);
		AccountPanel.add(label_8);

		label_9 = new JLabel("");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_9.setBounds(22, 212, 200, 29);
		AccountPanel.add(label_9);

		label_10 = new JLabel("");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_10.setBounds(22, 253, 200, 29);
		AccountPanel.add(label_10);

		label_11 = new JLabel("");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_11.setBounds(22, 293, 200, 29);
		AccountPanel.add(label_11);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(202, 57, 490, 314);
		AccountPanel.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
						"Apt ID", "Description", "Zip Code", "# PPL", "Bath", "Bed", "Price"
				}
				));
		scrollPane_1.setViewportView(table_1);

		//Back Button - Account Panel
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccountPanel.setVisible(false);
				MainPanel.setVisible(true);
			}
		});

	}
}