package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.RollingStock;
import asgn2Train.DepartingTrain;

public class MainPage {

	private JFrame frame;
	private DepartingTrain theTrain = new DepartingTrain();
	private JButton btnRemove;
	private JButton btnLocomotive;
	private JButton btnFreightcar;
	private JButton btnPassengercar;
	private JButton btnDepart;
	private JLabel lblOverweight;
	
	private int passengerAmount = 0;
	private int totalSeats = 0;
	private int power = 0;
	private int totalWeight = 0;
	private void reconfigureLayouts(){
		RollingStock r = theTrain.firstCarriage();
		RollingStock lastCarriage = r;
		System.out.println(r);
		while (r != null){
			lastCarriage = r;
			r = theTrain.nextCarriage();
			System.out.println(r);
		}
		if (lastCarriage == null){
			btnLocomotive.setEnabled(true);
			btnFreightcar.setEnabled(false);
			btnPassengercar.setEnabled(false);
			btnRemove.setEnabled(false);
		}
		else if (lastCarriage.getClass().getName() == "asgn2RollingStock.Locomotive"){
			btnLocomotive.setEnabled(false);
			btnFreightcar.setEnabled(true);
			btnPassengercar.setEnabled(true);
			btnRemove.setEnabled(true);
		}
		else if (lastCarriage.getClass().getName() == "asgn2RollingStock.PassengerCar"){
			btnLocomotive.setEnabled(false);
			btnFreightcar.setEnabled(true);
			btnPassengercar.setEnabled(true);
			btnRemove.setEnabled(true);
		}
		else if (lastCarriage.getClass().getName() == "asgn2RollingStock.FreightCar"){
			btnLocomotive.setEnabled(false);
			btnFreightcar.setEnabled(true);
			btnPassengercar.setEnabled(false);
			btnRemove.setEnabled(true);
		}
		
		if (theTrain.trainCanMove()){
			btnDepart.setEnabled(true);
			lblOverweight.setText("");
		}
		else{
			btnDepart.setEnabled(false);
			lblOverweight.setText("Train overweight and cannot move. Please reconfigure.");
			lblOverweight.setForeground(Color.RED);
		}
	}
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
		panel.setLayout(new BorderLayout());
		frame.getContentPane().add(panel);
		
		JPanel trainChart = new JPanel();
		trainChart.setLayout(new BorderLayout());
		JLabel tmpLabel = new JLabel("abcdefg");
		tmpLabel.setForeground(Color.BLACK);
		tmpLabel.setOpaque(true);
		tmpLabel.setPreferredSize(new Dimension(150, 20));
		tmpLabel.setBackground(Color.ORANGE);
		trainChart.add(tmpLabel, BorderLayout.WEST);
		trainChart.setAutoscrolls(true);
		JScrollPane scrollPane = new JScrollPane(trainChart);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 89, 495, -90);
		panel.add(scrollPane, BorderLayout.WEST);
//		JScrollBar scrollBar = new JScrollBar();
//		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
//		scrollBar.setBounds(0, 76, 526, 17);
//		panel.add(scrollBar);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(0, 98, 471, 33);
		frame.getContentPane().add(panel_1);

		btnDepart = new JButton("Depart");
		btnDepart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_1.setLayout(new GridLayout(1, 4, 0, 0));
		panel_1.add(btnDepart);
		
		JLabel lblNewLabel_10 = new JLabel("Power:");
		panel_1.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("TotalWeight:");
		panel_1.add(lblNewLabel_11);
		
		lblOverweight = new JLabel("");
		panel_1.add(lblOverweight);
		
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
		
		btnLocomotive = new JButton("Locomotive");
		btnLocomotive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LocomotiveChoice locoChoice = new LocomotiveChoice(MainPage.this.frame, theTrain);
				locoChoice.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				locoChoice.setVisible(true);
				reconfigureLayouts();
			}
		});
		panel_3.add(btnLocomotive);
		
		btnPassengercar = new JButton("PassengerCar");
		btnPassengercar.setEnabled(false);
		btnPassengercar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PassengerCarChoice passChoice = new PassengerCarChoice(MainPage.this.frame, theTrain);
				passChoice.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				passChoice.setVisible(true);
				reconfigureLayouts();
			}
		});
		panel_3.add(btnPassengercar);
		
		btnFreightcar = new JButton("FreightCar");
		btnFreightcar.setEnabled(false);
		btnFreightcar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FreightCarChoice freiChoice = new FreightCarChoice(MainPage.this.frame, theTrain);
				freiChoice.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				freiChoice.setVisible(true);
				reconfigureLayouts();
			}
		});
		panel_3.add(btnFreightcar);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(326, 141, 137, 169);
		frame.getContentPane().add(panel_4);
		
		JLabel lblRemoveCarriage = new JLabel("Remove Carriage");
		panel_4.add(lblRemoveCarriage);
		
		btnRemove = new JButton("Remove");
		btnRemove.setEnabled(false);
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					theTrain.removeCarriage();
				} catch (TrainException e1) {
					e1.printStackTrace();
				}
				reconfigureLayouts();
			}
		});
		panel_4.add(btnRemove);
	
	}
}
