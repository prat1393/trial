package com.itko.dme2;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class inputpanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	
	private dme2parse test;

	/**
	 * Create the panel.
	 */
	public inputpanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(181, 62, 10, 10);
		add(panel);
		
		
		test=new dme2parse();
		
		textField = new JTextField();
		textField.setBounds(0, 40, 284, 603);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(324, 40, 273, 603);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Input");
		lblNewLabel.setBounds(66, 11, 101, 18);
		add(lblNewLabel);
		
		JLabel lblOutput = new JLabel("output");
		lblOutput.setBounds(425, 13, 46, 14);
		add(lblOutput);
		
		JButton btnNewButton = new JButton("Dme2json");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		
			}
		});
		btnNewButton.setBounds(0, 654, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("formatjson");
		btnNewButton_1.setBounds(127, 654, 89, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Json2XML");
		btnNewButton_2.setBounds(241, 654, 89, 23);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("XML2Json");
		btnNewButton_3.setBounds(371, 654, 89, 23);
		add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("jsontodme2");
		btnNewButton_4.setBounds(508, 654, 89, 23);
		add(btnNewButton_4);

	}
	
}
