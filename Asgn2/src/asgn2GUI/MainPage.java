package asgn2GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import asgn2Train.DepartingTrain;

public class MainPage {

	private JFrame frame;
	private DepartingTrain dptTrain = new DepartingTrain();
	private int grossWeight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 542, 349);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 526, 93);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 89, 495, -90);
		panel.add(scrollPane);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		scrollBar.setBounds(0, 76, 526, 17);
		panel.add(scrollBar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(0, 98, 471, 33);
		frame.getContentPane().add(panel_1);
		
		JButton btnNewButton = new JButton("Depart");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_1.setLayout(new GridLayout(1, 4, 0, 0));
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_10 = new JLabel("Power:");
		panel_1.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("TotalWeight:");
		panel_1.add(lblNewLabel_11);
		
		JLabel lblAlertMessage = new JLabel("\u8D85\u91CD\u65F6\u663E\u793A\u7684\u4FE1\u606F");
		panel_1.add(lblAlertMessage);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(0, 141, 169, 169);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(5, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Locomotive:");
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_5 = new JLabel("0");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_2 = new JLabel("PassengerCars:");
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("0");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("FreightCars:");
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("0");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_1 = new JLabel("Total Seats");
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_8 = new JLabel("0");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_8);
		
		JLabel lblNewLabel_7 = new JLabel("Available Seats:");
		panel_2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_9 = new JLabel("0");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_9);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(179, 141, 137, 169);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel lblAddCarriage = new JLabel("Add Carriage");
		panel_3.add(lblAddCarriage);
		
		JButton btnLocomotive = new JButton("Locomotive");
		btnLocomotive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocomotiveChoice locoChoice = new LocomotiveChoice(MainPage.this);
				locoChoice.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				locoChoice.setVisible(true);
				
				
			}
		});
		panel_3.add(btnLocomotive);
		
		JButton btnPassengercar = new JButton("PassengerCar");
		btnPassengercar.setEnabled(false);
		panel_3.add(btnPassengercar);
		
		JButton btnFreightcar = new JButton("FreightCar");
		btnFreightcar.setEnabled(false);
		panel_3.add(btnFreightcar);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(326, 141, 137, 169);
		frame.getContentPane().add(panel_4);
		
		JLabel lblRemoveCarriage = new JLabel("Remove Carriage");
		panel_4.add(lblRemoveCarriage);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setEnabled(false);
		panel_4.add(btnRemove);
	
	}
	public void setGrossWeight(int gw){
		grossWeight = gw;
	}
	public int getGrossWeight(){
		System.out.println(grossWeight);
		return grossWeight;
	}
}
