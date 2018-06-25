package com.itko.dme2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONException;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class frame extends JFrame {

	private JPanel contentPane;
	JRadioButton rdbtnNewRadioButton ;
	int c=0;
	Dme2Json dj=new Dme2Json();
	formatJson fj=new formatJson();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame frame = new frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 716);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInput = new JLabel("INPUT");
		lblInput.setBounds(83, 18, 89, 14);
		contentPane.add(lblInput);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setBounds(425, 18, 46, 14);
		contentPane.add(lblOutput);

		
		rdbtnNewRadioButton = new JRadioButton("Dme2json");
	
		rdbtnNewRadioButton.setBounds(6, 592, 109, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Json2XML");
		rdbtnNewRadioButton_1.setBounds(244, 592, 109, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("XML2Json");
		rdbtnNewRadioButton_2.setBounds(362, 592, 109, 23);
		contentPane.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("jsontodme2");
		rdbtnNewRadioButton_3.setBounds(489, 592, 109, 23);
		contentPane.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("formatjson");
		/*rdbtnNewRadioButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});*/
		rdbtnNewRadioButton_4.setBounds(121, 592, 109, 23);
		contentPane.add(rdbtnNewRadioButton_4);
		
		JButton btnNewButton = new JButton("OK");
		
		btnNewButton.setBounds(244, 652, 89, 23);
		contentPane.add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(22, 59, 227, 492);
		contentPane.add(textArea);
		
		
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(329, 59, 227, 492);
		  JScrollPane scroll = new JScrollPane ( textArea_1 );
		    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		
		contentPane.add(scroll);
		contentPane.add(textArea_1);
		
		/*rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c=1;
			}
		});
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c=1;
			}
		});
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c=1;
			}
		});
	
		rdbtnNewRadioButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c=1;
			}
		});
		rdbtnNewRadioButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c=2;
			}
		});*/
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (rdbtnNewRadioButton.isSelected()) {
					String d=dj.dme2parse(textArea.getText());
					textArea_1.setText(d);
					
				}
				else if(rdbtnNewRadioButton_4.isSelected())
				{
					String f;
					try {
						f = fj.format(textArea.getText());
						textArea_1.setText(f);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			}
			
		});
		
	}
}
