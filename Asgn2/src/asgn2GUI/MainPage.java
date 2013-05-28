package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;
import asgn2Train.DepartingTrain;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.JSpinner;

public class MainPage {

	private JFrame frame;
	private DepartingTrain theTrain = new DepartingTrain();
	private JButton btnRemove;
	private JButton btnLocomotive;
	private JButton btnFreightcar;
	private JButton btnPassengercar;
//	private JButton btnDepart;
//	private JLabel lblOverweight;
	private JPanel trainChart;
	private List<JLabel> labelList = new ArrayList<JLabel>();
	
	private int passengersOnBoard = 0;
	private int totalSeats = 0;
	private int power = 0;
	private int totalWeight = 0;
	
	private void reconfigure(){
		this.totalSeats = 0;
		this.totalWeight = 0;
		this.passengersOnBoard = 0;
		RollingStock r = theTrain.firstCarriage();
		RollingStock lastCarriage = r;
		System.out.println(r);
		while (r != null){
			lastCarriage = r;
			if (r.getClass().getName() == "asgn2RollingStock.Locomotive"){
				this.power = ((Locomotive)r).power();
				this.totalWeight += r.getGrossWeight();
			}
			else if (r.getClass().getName() == "asgn2RollingStock.PassengerCar"){
				this.passengersOnBoard += ((PassengerCar)r).numberOnBoard();
				this.totalSeats += ((PassengerCar)r).numberOfSeats();
				this.totalWeight += r.getGrossWeight();
			}
			else if (r.getClass().getName() == "asgn2RollingStock.FreightCar"){
				this.totalWeight += r.getGrossWeight();
			}
			r = theTrain.nextCarriage();
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
//			btnDepart.setEnabled(true);
//			lblOverweight.setText("");
		}
		else{
			//
//			btnDepart.setEnabled(false);
//			lblOverweight.setText("Train overweight and cannot move. Please reconfigure.");
//			lblOverweight.setForeground(Color.RED);
		}
		
		for (JLabel l : this.labelList){
			trainChart.remove(l);
		}
		makeLabels();
		for (JLabel l : this.labelList){
			trainChart.add(l);
		}
		trainChart.repaint();
		trainChart.revalidate();
	}
	private void makeLabels(){
		List<JLabel> labels = new ArrayList<JLabel>();
		RollingStock r = theTrain.firstCarriage();
		if (r == null){
			this.labelList = labels;
		}
		else while (r != null){
			JLabel label = new JLabel("<html>"+ r.toString() +"<br>"+ r.getGrossWeight() +"</html>");
			label.setPreferredSize(new Dimension(160, 90));
			label.setOpaque(true);
			label.setBackground(Color.ORANGE);
			label.setHorizontalAlignment(CENTER);
			if (r.getClass().getName() == "asgn2RollingStock.Locomotive"){
				label.setBackground(Color.ORANGE);
			}
			else if (r.getClass().getName() == "asgn2RollingStock.PassengerCar"){
				label.setBackground(Color.GREEN);
			}
			else if (r.getClass().getName() == "asgn2RollingStock.FreightCar"){
				label.setBackground(Color.BLUE);
			}
			labels.add(label);
			r = theTrain.nextCarriage();
		}
		this.labelList = labels;
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
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setBounds(100, 100, 832, 488);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel trainChartPanel = new JPanel();
		trainChartPanel.setBackground(Color.LIGHT_GRAY);
		trainChartPanel.setBounds(0, 0, 826, 120);
		frame.getContentPane().add(trainChartPanel);
		trainChartPanel.setLayout(null);
		
		trainChart = new JPanel();
		trainChart.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		trainChart.setAutoscrolls(true);
		JScrollPane scrollPane = new JScrollPane(trainChart);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 826, 120);
		trainChartPanel.add(scrollPane);
		JPanel driverPanel = new JPanel();
		driverPanel.setBackground(SystemColor.control);
		driverPanel.setBounds(0, 130, 398, 285);
		frame.getContentPane().add(driverPanel);
		driverPanel.setLayout(null);
		
		JLabel lblInfomationOf = new JLabel("Driver");
		lblInfomationOf.setEnabled(false);
		lblInfomationOf.setBounds(0, 0, 50, 18);
		lblInfomationOf.setFont(new Font("Arial", Font.BOLD, 15));
		driverPanel.add(lblInfomationOf);
		
		btnLocomotive = new JButton("Add Locomotive");
		btnLocomotive.setBounds(248, 31, 140, 30);
		driverPanel.add(btnLocomotive);
		
		btnPassengercar = new JButton("Add Passenger Car");
		btnPassengercar.setBounds(248, 71, 140, 30);
		driverPanel.add(btnPassengercar);
		btnPassengercar.setEnabled(false);
		
		btnFreightcar = new JButton("Add Freight Car");
		btnFreightcar.setBounds(248, 111, 140, 30);
		driverPanel.add(btnFreightcar);
		btnFreightcar.setEnabled(false);
		
		btnRemove = new JButton("Remove Carriage");
		btnRemove.setBounds(248, 214, 140, 30);
		driverPanel.add(btnRemove);
		btnRemove.setEnabled(false);
		
		JTextPane textDriver = new JTextPane();
		textDriver.setBounds(10, 28, 228, 216);
		driverPanel.add(textDriver);
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					theTrain.removeCarriage();
				} catch (TrainException e1) {
					e1.printStackTrace();
				}
				reconfigure();
			}
		});
		btnFreightcar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FreightCarChoice freiChoice = new FreightCarChoice(MainPage.this.frame, theTrain);
				freiChoice.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				freiChoice.setVisible(true);
				reconfigure();
			}
		});
		btnPassengercar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PassengerCarChoice passChoice = new PassengerCarChoice(MainPage.this.frame, theTrain);
				passChoice.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				passChoice.setVisible(true);
				reconfigure();
			}
		});
		btnLocomotive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LocomotiveChoice locoChoice = new LocomotiveChoice(MainPage.this.frame, theTrain);
				locoChoice.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				locoChoice.setVisible(true);
				reconfigure();
			}
		});
		
		JPanel conductorPanel = new JPanel();
		conductorPanel.setBounds(414, 130, 412, 285);
		frame.getContentPane().add(conductorPanel);
		conductorPanel.setLayout(null);
		
		JLabel lblHowManyTo = new JLabel("Conductor");
		lblHowManyTo.setEnabled(false);
		lblHowManyTo.setFont(new Font("Arial", Font.BOLD, 15));
		lblHowManyTo.setBounds(0, 0, 102, 15);
		conductorPanel.add(lblHowManyTo);
		
		JButton btnBoard = new JButton("Board");
		btnBoard.setBounds(262, 160, 140, 30);
		conductorPanel.add(btnBoard);
		
		JTextPane textConductor = new JTextPane();
		textConductor.setBounds(10, 28, 228, 216);
		conductorPanel.add(textConductor);
		
		JLabel lblToBoard = new JLabel("To board:");
		lblToBoard.setFont(new Font("Arial", Font.PLAIN, 14));
		lblToBoard.setBounds(262, 99, 74, 15);
		conductorPanel.add(lblToBoard);
		
		JSpinner spinnerToBoard = new JSpinner();
		spinnerToBoard.setBounds(328, 96, 74, 22);
		conductorPanel.add(spinnerToBoard);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 424, 826, 35);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(318, 4, 198, 28);
		panel.add(btnReset);
	
	}
}
