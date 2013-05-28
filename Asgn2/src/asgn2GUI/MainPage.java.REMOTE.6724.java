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
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Font;

public class MainPage {

	private JFrame frame;
	private DepartingTrain theTrain = new DepartingTrain();
	private JButton btnRemove;
	private JButton btnLocomotive;
	private JButton btnFreightcar;
	private JButton btnPassengercar;
	
	private int passengerAmount = 0;
	private int totalSeats = 0;
	private int power = 0;
	private int totalWeight = 0;
	private JTextField txfToBoard;
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
		frame.getContentPane().setBackground(new Color(250, 235, 215));
		frame.setResizable(false);
		frame.setBounds(100, 100, 832, 488);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 806, 120);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel trainChart = new JPanel();
		FlowLayout fl_trainChart = new FlowLayout(FlowLayout.CENTER, 5, 5);
		trainChart.setLayout(fl_trainChart);
		JLabel tmpLabel = new JLabel(" ");
		tmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tmpLabel.setForeground(Color.BLACK);
		tmpLabel.setOpaque(true);
		tmpLabel.setPreferredSize(new Dimension(150, 90));
		tmpLabel.setBackground(Color.ORANGE);
		trainChart.add(tmpLabel);
		trainChart.setAutoscrolls(true);
		JScrollPane scrollPane = new JScrollPane(trainChart);
		
		JLabel lblA = new JLabel("a");
		trainChart.add(lblA);
		
		JLabel lblNewLabel_12 = new JLabel("abacdfs");
		trainChart.add(lblNewLabel_12);
		
		JLabel lblAsdfasdfasd = new JLabel("asdfasdfasd");
		trainChart.add(lblAsdfasdfasd);
		
		JLabel lblAsdfasdfasdfsdfs = new JLabel("asdfasdfasdfsdfs");
		trainChart.add(lblAsdfasdfasdfsdfs);
		
		JLabel lblDsfjsljdfljsdlfjalie = new JLabel("dsfjsljdfljsdlfjalie");
		trainChart.add(lblDsfjsljdfljsdlfjalie);
		
		JLabel lblSjdfkjslkjdfplasd = new JLabel("sjdfkjslkjdfplasd");
		trainChart.add(lblSjdfkjslkjdfplasd);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 806, 120);
		panel.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(0, 145, 248, 280);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(9, 2, 0, 0));
		
		JLabel lblInfomationOf = new JLabel("Infomation of ");
		lblInfomationOf.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		panel_2.add(lblInfomationOf);
		
		JLabel lblTrain = new JLabel("this train");
		lblTrain.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		panel_2.add(lblTrain);
		
		JLabel lblPower = new JLabel("Power:");
		lblPower.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel_2.add(lblPower);
		
		JLabel lablePower = new JLabel("0");
		lablePower.setHorizontalAlignment(SwingConstants.CENTER);
		lablePower.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel_2.add(lablePower);
		
		JLabel lblTotalWeight = new JLabel("Total Weight:");
		lblTotalWeight.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel_2.add(lblTotalWeight);
		
		JLabel label_1 = new JLabel("0");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel_2.add(label_1);
		
		JLabel lblNewLabel = new JLabel("Locomotive:");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel_2.add(lblNewLabel);
		
		JLabel lblNumOfLoco = new JLabel("0");
		lblNumOfLoco.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblNumOfLoco.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNumOfLoco);
		
		JLabel lblNewLabel_2 = new JLabel("PassengerCars:");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNumOfPassCar = new JLabel("0");
		lblNumOfPassCar.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblNumOfPassCar.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNumOfPassCar);
		
		JLabel lblNewLabel_4 = new JLabel("FreightCars:");
		lblNewLabel_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNumOfFreiCar = new JLabel("0");
		lblNumOfFreiCar.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblNumOfFreiCar.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNumOfFreiCar);
		
		JLabel lblNewLabel_1 = new JLabel("Total Seats");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNumOfTotalSeats = new JLabel("0");
		lblNumOfTotalSeats.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblNumOfTotalSeats.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNumOfTotalSeats);
		
		JLabel lblNewLabel_7 = new JLabel("Available Seats:");
		lblNewLabel_7.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_7);
		
		JLabel lblNumOfAvaiSeats = new JLabel("0");
		lblNumOfAvaiSeats.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblNumOfAvaiSeats.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNumOfAvaiSeats);
		
		JLabel lblOnboard = new JLabel("OnBoard:");
		lblOnboard.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel_2.add(lblOnboard);
		
		JLabel lblNumOfOnboard = new JLabel("0");
		lblNumOfOnboard.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblNumOfOnboard.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNumOfOnboard);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(522, 312, 137, 56);
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
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(531, 130, 161, 134);
		frame.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblHowManyTo = new JLabel("How many to board");
		lblHowManyTo.setBounds(17, 5, 102, 15);
		panel_5.add(lblHowManyTo);
		
		JButton btnBoard = new JButton("Board");
		btnBoard.setBounds(0, 30, 76, 23);
		panel_5.add(btnBoard);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.setBounds(0, 63, 76, 23);
		panel_5.add(btnNewButton);
		
		txfToBoard = new JTextField();
		txfToBoard.setBounds(83, 31, 54, 21);
		panel_5.add(txfToBoard);
		txfToBoard.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("cannot board:");
		lblNewLabel_3.setBounds(0, 93, 82, 25);
		panel_5.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("0");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(92, 98, 54, 15);
		panel_5.add(lblNewLabel_5);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(258, 145, 184, 183);
		frame.getContentPane().add(panel_3);
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(null);
		
		JLabel lblAddCarriage = new JLabel("Add Carriage");
		lblAddCarriage.setBounds(0, 0, 97, 35);
		panel_3.add(lblAddCarriage);
		
		btnLocomotive = new JButton("Locomotive");
		btnLocomotive.setBounds(0, 35, 111, 35);
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
		btnPassengercar.setBounds(0, 80, 111, 35);
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
		btnFreightcar.setBounds(0, 127, 111, 35);
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
	
	}
}