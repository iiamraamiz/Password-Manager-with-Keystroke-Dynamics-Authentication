import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

@SuppressWarnings("unused")
public class Userpasswords extends JFrame 
{
	Connection con = DBConnection.getDBConnection();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	public void Refreshtable(String Username)
	{
		try 
		{
			String query="select * from "+Username+";";
			PreparedStatement pst=con.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		}
		catch (Exception a)
		{
			a.printStackTrace();
		}
	}
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Userpasswords frame = new Userpasswords();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	public Userpasswords(String Username) 
	{
		setTitle("Password Manager");
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\SRU ACADEMIC\\SRU SEM-6\\Mini Project\\Keystroke-Biometrics-Identification-System-master\\Keystroke Identification\\Images\\icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User : ");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel.setBounds(20, 10, 35, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(Username);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_1.setBounds(65, 13, 74, 13);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 32, 550, 105);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		
		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon("D:\\SRU ACADEMIC\\SRU SEM-6\\Mini Project\\Keystroke-Biometrics-Identification-System-master\\Keystroke Identification\\Images\\export.gif"));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyStrokeUI exit=new KeyStrokeUI();
				exit.Exit();
			}
		});
		btnExit.setForeground(Color.BLACK);
		btnExit.setBackground(Color.ORANGE);
		btnExit.setBounds(428, 287, 117, 21);
		contentPane.add(btnExit);
		
		JButton btnNewButton = new JButton("Load Data");
		btnNewButton.setIcon(new ImageIcon("D:\\SRU ACADEMIC\\SRU SEM-6\\Mini Project\\Keystroke-Biometrics-Identification-System-master\\Keystroke Identification\\Images\\Open.GIF"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					String query="select * from "+Username+";";
					PreparedStatement pst=con.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
				}
				catch (Exception a)
				{
					a.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(196, 5, 158, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Domain");
		lblNewLabel_2.setBounds(72, 169, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(162, 166, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Username");
		lblNewLabel_2_1.setBounds(72, 198, 67, 13);
		contentPane.add(lblNewLabel_2_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(162, 195, 96, 19);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Password");
		lblNewLabel_2_1_1.setBounds(72, 227, 55, 13);
		contentPane.add(lblNewLabel_2_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(162, 224, 96, 19);
		contentPane.add(textField_2);
		
		JButton btnAddPassword = new JButton("Add Data");
		btnAddPassword.setIcon(new ImageIcon("D:\\SRU ACADEMIC\\SRU SEM-6\\Mini Project\\Keystroke-Biometrics-Identification-System-master\\Keystroke Identification\\Images\\add.gif"));
		btnAddPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					String query="insert into "+Username+"(Domain,Username,Password) values(?,?,?);";
					PreparedStatement pst=con.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, "Data Inserted");
					pst.close();
				}
				catch (Exception a)
				{
					a.printStackTrace();
				}
				Refreshtable(Username);
			}
		});
		btnAddPassword.setBounds(94, 257, 153, 21);
		contentPane.add(btnAddPassword);
		
		JLabel lblNewLabel_2_2 = new JLabel("Domain");
		lblNewLabel_2_2.setBounds(318, 169, 45, 13);
		contentPane.add(lblNewLabel_2_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(373, 166, 96, 19);
		contentPane.add(textField_3);
		
		JButton btnDeletePassword = new JButton("Delete Data");
		btnDeletePassword.setIcon(new ImageIcon("D:\\SRU ACADEMIC\\SRU SEM-6\\Mini Project\\Keystroke-Biometrics-Identification-System-master\\Keystroke Identification\\Images\\delete.gif"));
		btnDeletePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					String query="delete from "+Username+" where Domain='"+textField_3.getText()+"';";
					PreparedStatement pst=con.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Data Deleted");
					pst.close();
				}
				catch (Exception a)
				{
					a.printStackTrace();
				}
				Refreshtable(Username);
			}
		});
		btnDeletePassword.setBounds(328, 194, 131, 21);
		contentPane.add(btnDeletePassword);
		
		JButton btnUpdateData = new JButton("Update Data");
		btnUpdateData.setIcon(new ImageIcon("D:\\SRU ACADEMIC\\SRU SEM-6\\Mini Project\\Keystroke-Biometrics-Identification-System-master\\Keystroke Identification\\Images\\refresh.gif"));
		btnUpdateData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					String query="Update "+Username+" set Domain='"+textField.getText()+"', Username='"+textField_1.getText()+"', Password='"+textField_2.getText()+"' where Domain='"+textField.getText()+"';";
					PreparedStatement pst=con.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Data Updated");
					pst.close();
				}
				catch (Exception a)
				{
					a.printStackTrace();
				}
				Refreshtable(Username);
			}
		});
		btnUpdateData.setBounds(94, 287, 153, 21);
		contentPane.add(btnUpdateData);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setIcon(new ImageIcon("D:\\SRU ACADEMIC\\SRU SEM-6\\Mini Project\\Keystroke-Biometrics-Identification-System-master\\Keystroke Identification\\Images\\Home.gif"));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnLogout.setForeground(Color.BLACK);
		btnLogout.setBackground(Color.ORANGE);
		btnLogout.setBounds(287, 287, 131, 21);
		contentPane.add(btnLogout);
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.setIcon(new ImageIcon("D:\\SRU ACADEMIC\\SRU SEM-6\\Mini Project\\Keystroke-Biometrics-Identification-System-master\\Keystroke Identification\\Images\\Basket.gif"));
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					int val=0;
					String selq="select ID from users where username='"+Username+"';";
					String query="drop table "+Username+";";
					String query1="delete from users where username='"+Username+"';";
					Statement pst=con.createStatement();
					PreparedStatement pst1=con.prepareStatement(query);
					PreparedStatement pst2=con.prepareStatement(query1);
					ResultSet rs=pst.executeQuery(selq);
					while(rs.next())
					{
						val=rs.getInt(1);
					}
					String query2="delete from signature_profile where USER_ID='"+val+"';";
					PreparedStatement pst3=con.prepareStatement(query2);
					pst1.execute();
					pst2.execute();
					pst3.execute();
					JOptionPane.showMessageDialog(null, "Account Deleted");
					pst.close();
					pst1.close();
					pst2.close();
					pst3.close();
					dispose();
				}
				catch (Exception a)
				{
					a.printStackTrace();
				}
			}
		});
		btnDeleteAccount.setForeground(Color.BLACK);
		btnDeleteAccount.setBackground(Color.RED);
		btnDeleteAccount.setBounds(365, 5, 180, 21);
		contentPane.add(btnDeleteAccount);
	}
}