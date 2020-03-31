package com.btp400.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.border.BevelBorder;

import com.btp400.controller.Controller;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class WindowApp {

	private JFrame frmAirmap;
	private JTextField textField_lat;
	private JTextField textField_long;
	private JButton btnTryAgain = new JButton("Try Again");
	private JButton btnDone = new JButton("Get Result!");
	private JComboBox comboBox = new JComboBox();
	int comboBoxState = 0;
	
	private static Controller db = new Controller();
	private JTextField textField_location;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowApp window = new WindowApp();
					window.frmAirmap.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAirmap = new JFrame();
		frmAirmap.getContentPane().setBackground(new Color(240, 248, 255));
		frmAirmap.setForeground(Color.WHITE);
		frmAirmap.setTitle("AirMap v0.1.1");
		frmAirmap.setBackground(Color.PINK);
		frmAirmap.setBounds(100, 100, 452, 774);
		frmAirmap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAirmap.getContentPane().setLayout(null);
		
		
		JLabel lblResult = new JLabel("New label");
		lblResult.setVisible(false);
		lblResult.setVerticalAlignment(SwingConstants.TOP);
		lblResult.setBounds(47, 82, 341, 750);
		frmAirmap.getContentPane().add(lblResult);
		
		JLabel lblTitle = new JLabel("WELCOME TO AIRMAP 0.1.1!");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(107, 24, 218, 47);
		frmAirmap.getContentPane().add(lblTitle);
		
		JLabel lblHeading = new JLabel("Look up air quality by:");
		lblHeading.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHeading.setHorizontalAlignment(SwingConstants.LEFT);
		lblHeading.setBounds(37, 84, 176, 14);
		frmAirmap.getContentPane().add(lblHeading);
		
		textField_lat = new JTextField();
		textField_lat.setBounds(204, 170, 140, 20);
		textField_lat.setVisible(false);
		frmAirmap.getContentPane().add(textField_lat);
		textField_lat.setColumns(10);
		
		JLabel lbl_lat = new JLabel("Latitude:");
		lbl_lat.setVisible(false);
		lbl_lat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_lat.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_lat.setBounds(37, 173, 96, 14);
		frmAirmap.getContentPane().add(lbl_lat);
		
		JLabel lbl_long = new JLabel("Longtitude: ");
		lbl_long.setVisible(false);
		lbl_long.setBackground(Color.LIGHT_GRAY);
		lbl_long.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_long.setForeground(Color.BLACK);
		lbl_long.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_long.setBounds(37, 223, 70, 14);
		frmAirmap.getContentPane().add(lbl_long);
		
		textField_long = new JTextField();
		textField_long.setVisible(false);
		textField_long.setColumns(10);
		textField_long.setBounds(204, 220, 140, 20);
		frmAirmap.getContentPane().add(textField_long);
		
		textField_location = new JTextField();
		textField_location.setBounds(37, 201, 366, 20);
		frmAirmap.getContentPane().add(textField_location);
		textField_location.setColumns(10);
		
		JLabel lblLocation = new JLabel("Enter Location:");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocation.setBounds(37, 162, 96, 14);
		frmAirmap.getContentPane().add(lblLocation);
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Location name (Default)", "Coordinates"}));
		comboBox.setSelectedIndex(comboBoxState);
		comboBox.setBounds(37, 112, 366, 20);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (comboBox.getSelectedIndex()) {
				case 0:
					textField_location.setVisible(true);
					lblLocation.setVisible(true);
					textField_lat.setVisible(false);
					textField_long.setVisible(false);
					lbl_lat.setVisible(false);
					lbl_long.setVisible(false);
					comboBoxState = 0;
					break;
				case 1:
					textField_location.setVisible(false);
					lblLocation.setVisible(false);
					textField_lat.setVisible(true);
					textField_long.setVisible(true);
					lbl_lat.setVisible(true);
					lbl_long.setVisible(true);
					comboBoxState = 1;
					break;
				default:
					textField_location.setVisible(false);
					lblLocation.setVisible(false);
					textField_lat.setVisible(false);
					textField_long.setVisible(false);
					lbl_lat.setVisible(false);
					lbl_long.setVisible(false);
					break;
				}
			}
		});
		frmAirmap.getContentPane().add(comboBox);
		
		btnTryAgain.setToolTipText("Click here re-enter the coordinates");
		btnTryAgain.setVisible(false);
		btnTryAgain.setForeground(Color.BLACK);
		btnTryAgain.setBackground(new Color(255, 250, 205));
		btnTryAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResult.setVisible(false);
				btnTryAgain.setVisible(false);
				lblHeading.setVisible(true);
				lblHeading.setText("Look up air quality by: ");
				comboBox.setSelectedIndex(comboBoxState);
				comboBox.setVisible(true);
				btnDone.setVisible(true);
			}
		});
		
		btnTryAgain.setBounds(144, 690, 128, 23);
		frmAirmap.getContentPane().add(btnTryAgain);
		
		
		
		
		
		
		btnDone.setToolTipText("Click here to submit");
		btnDone.setForeground(Color.BLACK);
		btnDone.setBackground(new Color(255, 250, 205));
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblHeading.setText("Loading the result...");
				String result = "";
				if (comboBox.getSelectedIndex() == 0)
				{
					String input = textField_location.getText();
					result = "<html>" + db.displayStats(input).replaceAll("\n", "<br>") + "</html>";
				}
				else {
					try {
						double _lat = Double.parseDouble(textField_lat.getText().equals("") ? "0" : textField_lat.getText());
						double _long = Double.parseDouble(textField_long.getText().equals("") ? "0" : textField_long.getText());
						result = "<html>" + db.displayStats(_lat, _long).replaceAll("\n", "<br>") + "</html>";
					} catch (NumberFormatException excep) {
						result = "<html>Invalid Entry! Latitude and longtitude must be numbers</html>";
					}
				}
				textField_lat.setVisible(false);
				textField_long.setVisible(false);
				textField_location.setVisible(false);
				lbl_lat.setVisible(false);
				lbl_long.setVisible(false);
				lblLocation.setVisible(false);
				comboBox.setVisible(false);
				comboBox.setSelectedIndex(-1);
				//frmAirmap.setBounds(100, 100, 450, 750);
				lblResult.setText(result);
				btnDone.setVisible(false);
				btnTryAgain.setVisible(true);
				lblHeading.setVisible(false);
				lblResult.setVisible(true);
			}
		});
		btnDone.setBounds(144, 280, 128, 23);
		frmAirmap.getContentPane().add(btnDone);
		
		
	}
}
