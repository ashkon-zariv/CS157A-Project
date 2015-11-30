import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;

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
import java.awt.TextArea;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.sql.*;
import javax.swing.table.DefaultTableModel;


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
	private JTextField searchField_1;
	private JTextField searchField_2;
	private JTextField searchField_3;
	private JTextField searchField_4;
	private JPasswordField passwordField_1;

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private JTable table;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frmApartmentManagementSystem = new JFrame();
		frmApartmentManagementSystem.setTitle("KaoZariv Apartment Database - CS 157A");
		frmApartmentManagementSystem.setBounds(100, 100, 676, 371);
		frmApartmentManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmApartmentManagementSystem.getContentPane().setLayout(new CardLayout(0, 0));


		final JPanel SignInPanel = new JPanel();
		frmApartmentManagementSystem.getContentPane().add(SignInPanel, "name_1059160628680983");
		SignInPanel.setLayout(null);
		SignInPanel.setVisible(true);

		final JPanel SignUpPanel = new JPanel();
		frmApartmentManagementSystem.getContentPane().add(SignUpPanel, "name_1059917700298584");
		SignUpPanel.setLayout(null);
		SignUpPanel.setVisible(false);

		final JPanel SearchPanel = new JPanel();
		frmApartmentManagementSystem.getContentPane().add(SearchPanel, "name_1059165859655842");
		SearchPanel.setLayout(null);
		SearchPanel.setVisible(false);

		JPanel MainPanel = new JPanel();
		frmApartmentManagementSystem.getContentPane().add(MainPanel, "name_1072333189294654");
		MainPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 452, 310);
		MainPanel.add(scrollPane);

		table = new JTable();
		int row = 100;
		int col = 7;
		Object[][] data = new Object[row][col];
		try
		{
			PreparedStatement aptid = conn.prepareStatement("SELECT apartment_id FROM apartments");
			PreparedStatement name = conn.prepareStatement("SELECT name FROM apartments");
			PreparedStatement zip = conn.prepareStatement("SELECT zip_code FROM apartments");
			PreparedStatement acc = conn.prepareStatement("SELECT accommodates FROM apartments");
			PreparedStatement bed = conn.prepareStatement("SELECT bedrooms FROM apartments");
			PreparedStatement bath = conn.prepareStatement("SELECT bathrooms FROM apartments");
			PreparedStatement price = conn.prepareStatement("SELECT price FROM apartments");

			ResultSet ps = price.executeQuery();
			ResultSet as = aptid.executeQuery();
			ResultSet ns = name.executeQuery();
			ResultSet zs = zip.executeQuery();
			ResultSet acs = acc.executeQuery();
			ResultSet bs = bed.executeQuery();
			ResultSet bas = bath.executeQuery();
			
			int count = 0;
			int count2 = 0;
			int count3 = 0;
			int count4 = 0;
			int count5 = 0;
			int count6 = 0;
			int count7 = 0;
			while(count < row)
			{
				ps.next();
				String tester = ps.getString("price");
				data[count][6] = tester;
				count++;
			}
			while(count2 < row)
			{
				as.next();
				int tester2 = as.getInt("apartment_id");
				data[count2][0] = tester2;
				count2++;
			}
			while(count3 < row)
			{
				ns.next();
				String tester2 = ns.getString("name");
				data[count3][1] = tester2;
				count3++;
			}
			while(count4 < row)
			{
				zs.next();
				String tester2 = zs.getString("zip_code");
				data[count4][2] = tester2;
				count4++;
			}
			while(count5 < row)
			{
				acs.next();
				int tester2 = acs.getInt("accommodates");
				data[count5][3] = tester2;
				count5++;
			}
			while(count6 < row)
			{
				bs.next();
				String tester2 = bs.getString("bedrooms");
				data[count6][5] = tester2;
				count6++;
			}
			while(count7 < row)
			{
				bas.next();
				String tester2 = bas.getString("bathrooms");
				data[count7][4] = tester2;
				count7++;
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}

		table.setModel(new DefaultTableModel(data,
				new String[] {
						"Apt ID", "Name", "Zip Code", "Accommodates", "Bathrooms", "Bedrooms", "Price"
		}
				) {
			Class[] columnTypes = new Class[] {
					String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
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
		table.getColumnModel().getColumn(0).setPreferredWidth(41);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(55);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(61);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(57);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(43);
		scrollPane.setViewportView(table);
		MainPanel.setVisible(false);

		//SignInPanel elements

		Button button = new Button("Sign Up");
		button.setFont(new Font("Arial", Font.BOLD, 16));
		button.setBounds(179, 185, 158, 48);
		SignInPanel.add(button);

		Button button_1 = new Button("Sign In");
		button_1.setFont(new Font("Arial", Font.BOLD, 16));
		button_1.setBounds(388, 185, 172, 48);
		SignInPanel.add(button_1);

		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 11));
		textField.setBounds(178, 58, 389, 30);
		SignInPanel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 11));
		passwordField.setBounds(178, 117, 390, 30);
		SignInPanel.add(passwordField);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setBounds(90, 62, 88, 20);
		SignInPanel.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblPassword.setBounds(90, 120, 88, 20);
		SignInPanel.add(lblPassword);

		JLabel lblNewLabel_1 = new JLabel("User added successfully!");
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(83, 259, 220, 30);
		SignInPanel.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);

		JLabel lblNewLabel_2 = new JLabel("User not found!");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(453, 259, 172, 30);
		SignInPanel.add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);

		//SignUpPanel elements

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

		JLabel lblNewLabel = new JLabel("Error(s) found in marked text field(s).");
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

		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_1.setBounds(528, 60, 46, 14);
		SignUpPanel.add(label_1);
		label_1.setVisible(false);

		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_2.setBounds(528, 101, 46, 14);
		SignUpPanel.add(label_2);
		label_2.setVisible(false);

		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_3.setBounds(528, 140, 46, 14);
		SignUpPanel.add(label_3);
		label_3.setVisible(false);

		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_4.setBounds(528, 183, 46, 14);
		SignUpPanel.add(label_4);
		label_4.setVisible(false);

		JLabel label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_5.setBounds(336, 224, 46, 14);
		SignUpPanel.add(label_5);
		label_5.setVisible(false);

		JLabel label_6 = new JLabel("*");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_6.setBounds(634, 224, 46, 14);
		SignUpPanel.add(label_6);
		label_6.setVisible(false);

		//SignIn Button - SignIn Panel
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement("SELECT email, password FROM customers WHERE email = ? AND password = ?");
					ps.setString(1, String.valueOf(textField.getText()));
					ps.setString(2, String.valueOf(passwordField.getPassword()));
					ResultSet result = ps.executeQuery();
					if(result.next()){
						JOptionPane.showMessageDialog(null, "Successfully logged in.");
						SearchPanel.setVisible(true);
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
		searchField_1 = new JTextField();
		searchField_1.setBounds(146, 91, 370, 30);
		SearchPanel.add(searchField_1);
		searchField_1.setColumns(10);

		searchField_2 = new JTextField();
		searchField_2.setColumns(10);
		searchField_2.setBounds(146, 132, 370, 30);
		SearchPanel.add(searchField_2);

		searchField_3 = new JTextField();
		searchField_3.setColumns(10);
		searchField_3.setBounds(146, 173, 370, 30);
		SearchPanel.add(searchField_3);

		searchField_4 = new JTextField();
		searchField_4.setColumns(10);
		searchField_4.setBounds(146, 214, 370, 30);
		SearchPanel.add(searchField_4);

		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Arial Black", Font.PLAIN, 16));
		searchButton.setBounds(256, 267, 191, 44);
		SearchPanel.add(searchButton);

		JLabel location = new JLabel("Location\r\n");
		location.setFont(new Font("Arial Black", Font.PLAIN, 12));
		location.setBounds(79, 99, 57, 22);
		SearchPanel.add(location);

		JLabel startDate = new JLabel("Start Date\r\n");
		startDate.setFont(new Font("Arial Black", Font.PLAIN, 12));
		startDate.setBounds(69, 135, 67, 22);
		SearchPanel.add(startDate);

		JLabel endDate = new JLabel("End Date\r\n");
		endDate.setFont(new Font("Arial Black", Font.PLAIN, 12));
		endDate.setBounds(74, 176, 62, 22);
		SearchPanel.add(endDate);

		JLabel guest = new JLabel("Number of Guests\r\n");
		guest.setFont(new Font("Arial Black", Font.PLAIN, 12));
		guest.setBounds(19, 217, 117, 22);
		SearchPanel.add(guest);


		JLabel searchLabel = new JLabel("Error(s) found in marked text field(s).");
		searchLabel.setForeground(Color.RED);
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchLabel.setBounds(21, 275, 257, 30);
		SignUpPanel.add(searchLabel);
		searchLabel.setVisible(false);

		//Error asterisks

		final JLabel searchLabel_0 = new JLabel("*");
		searchLabel_0.setForeground(Color.RED);
		searchLabel_0.setFont(new Font("Tahoma", Font.BOLD, 16));
		searchLabel_0.setBounds(526, 97, 46, 14);
		SearchPanel.add(searchLabel_0);
		searchLabel_0.setVisible(false);

		JLabel searchLabel_1 = new JLabel("*");
		searchLabel_1.setForeground(Color.RED);
		searchLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		searchLabel_1.setBounds(526, 138, 46, 14);
		SearchPanel.add(searchLabel_1);
		searchLabel_1.setVisible(false);

		JLabel searchLabel_2 = new JLabel("*");
		searchLabel_2.setForeground(Color.RED);
		searchLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		searchLabel_2.setBounds(526, 179, 46, 14);
		SearchPanel.add(searchLabel_2);
		searchLabel_2.setVisible(false);

		JLabel searchLabel_3 = new JLabel("*");
		searchLabel_3.setForeground(Color.RED);
		searchLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		searchLabel_3.setBounds(526, 220, 46, 14);
		SearchPanel.add(searchLabel_3);

		JLabel lblNewLabel_3 = new JLabel("Requests");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(302, 11, 117, 38);
		SearchPanel.add(lblNewLabel_3);
		searchLabel_3.setVisible(false);

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String location = "'" + searchField_1.getText() + "', ";
				String startDate = "'" + searchField_2.getText() + "', ";
				String endDate = "'" + searchField_3.getText()+ "', ";
				String guests = "'" + searchField_4.getText() + "'";
				if(searchField_1.getText().length() == 0)
				{
					searchLabel_0.setVisible(true);
					searchLabel.setVisible(true);
					searchField_1.setText("");
				}
				else
				{
					searchLabel_0.setVisible(false);
				}
				if(searchField_2.getText().length() == 0)
				{
					searchLabel_1.setVisible(true);
					searchLabel.setVisible(true);
					searchField_2.setText("");
				}
				else
				{
					searchLabel_2.setVisible(false);
				}
				if(searchField_3.getText().length() == 0)
				{
					searchLabel_2.setVisible(true);
					searchLabel.setVisible(true);
					searchField_3.setText("");
				}
				else
				{
					searchLabel_2.setVisible(false);
				}
				int num = Integer.parseInt(searchField_4.getText()); // not working 
				if(searchField_4.getText().length() == 0 && num > 1)
				{
					searchLabel_3.setVisible(true);
					searchLabel.setVisible(true);
					searchField_4.setText("");
				}
				else
				{
					searchLabel_3.setVisible(false);
				}

				if(!searchLabel.isVisible() && !searchLabel_1.isVisible() && !searchLabel_2.isVisible()
						&& !searchLabel_3.isVisible())
				{
					try{
						Statement stmt = conn.createStatement();
						String sql = "INSERT INTO requests "
								+ "VALUES ('0', '0', " + location + startDate + endDate 
								+ guests + ");";
						stmt.executeUpdate(sql);
					}
					catch (Exception ex){
						ex.printStackTrace();
					}

					SearchPanel.setVisible(false);
					MainPanel.setVisible(true);
				}
			}
		});

		//SignUp Button - SignIn Panel
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignInPanel.setVisible(false);
				SignUpPanel.setVisible(true);
			}
		});

		//SignUp Button - SignUpPanel
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
}
