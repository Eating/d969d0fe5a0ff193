/**
 * @author Tian XIN
 * 
 * @studentNumber 8779295
 * 
 * @version 1.0
 * 
 */
package asgn2GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import asgn2Exceptions.TrainException;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;
import asgn2Train.DepartingTrain;

public class MainPage {

	private JFrame frame;
	private DepartingTrain theTrain = new DepartingTrain();
	private JButton btnRemove;
	private JButton btnLocomotive;
	private JButton btnFreightcar;
	private JButton btnPassengercar;
	private JButton btnBoard;
	private JButton btnReset;
	private JTextPane textDriver;
	private JTextPane textConductor;
	private JSpinner spinnerToBoard;
	private SpinnerNumberModel boardNumberModel = new SpinnerNumberModel();
	private JPanel trainChart;
	private List<JPanel> labelList = new ArrayList<JPanel>();

	private int passengersOnBoard = 0;
	private int totalSeats = 0;
	private int power = 0;
	private int totalWeight = 0;
	private int passengersRefused = 0;

	/**
	 * Build a string for drivers to read, return specific information for
	 * drivers
	 * 
	 * @return Information for drivers
	 */
	private String buildDriversText() {
		String ret = "=========Drivers' Summary========\n";
		ret += "Haulage Capacity:\n";
		ret += " - Used/Available: = " + totalWeight + " / " + power;
		ret += "\n";
		ret += "\n";
		ret += "Train Composition:\n";
		RollingStock r = theTrain.firstCarriage();
		if (r != null) {
			ret += " - " + r.toString() + "\n";
		} else {
			ret += " - None\n";
		}

		int freightCars = 0;
		int passengerCars = 0;
		while (r != null) {
			r = theTrain.nextCarriage();
			if (r != null) {
				if (r.getClass().getName() == "asgn2RollingStock.PassengerCar") {
					passengerCars++;
				} else if (r.getClass().getName() == "asgn2RollingStock.FreightCar") {
					freightCars++;
				}
			}
		}
		ret += "     |-" + passengerCars + " Passenger Cars\n";
		ret += "     |-" + freightCars + " Freight Cars\n";
		ret += "\n";
		ret += "Allowable Carriage Additions:\n";
		if (passengersOnBoard > 0) {
			ret += " - Passenger(s) on board\n";
		} else {
			ret += " - No passenger on board\n";
		}
		if (!theTrain.trainCanMove()) {
			ret += " - Train cannot move due to large weight";
		}
		else if (theTrain.firstCarriage()!=null){
			ret += " - Ready for departure";
		}
		return ret;
	}

	/**
	 * Build a string for conductors to read, return specific information for
	 * conductors
	 * 
	 * @return Information for conductors
	 */
	private String buildConductorsText() {
		String ret = "=======Conductors' Summary======\n";
		ret += "\n";
		ret += "Seating Capacity:\n";
		ret += "  - " + passengersOnBoard + " / " + totalSeats + "\n";
		if (passengersOnBoard == totalSeats){
			ret += "  - Full loaded\n";
		}
		ret += "\n";
		ret += "Passengers Refused:\n";
		ret += "  - " + passengersRefused;
		return ret;
	}

	/**
	 * Disable all the components related with boarding.
	 */
	private void disableBoardingArea() {
		btnBoard.setEnabled(false);
		spinnerToBoard.setEnabled(false);
	}

	/**
	 * Enable all the components related with boarding.
	 */
	private void enableBoardingArea() {
		btnBoard.setEnabled(true);
		spinnerToBoard.setEnabled(true);
	}

	/**
	 * Disable all the components related with train composition.
	 */
	private void disableCompositionArea() {
		btnLocomotive.setEnabled(false);
		btnFreightcar.setEnabled(false);
		btnPassengercar.setEnabled(false);
		btnRemove.setEnabled(false);
	}

	/**
	 * Reconfigure the data and GUI layouts after changing composition of the
	 * train
	 */
	private void reconfigure() {
		this.power = 0;
		this.totalSeats = 0;
		this.totalWeight = 0;
		this.passengersOnBoard = 0;
		RollingStock r = theTrain.firstCarriage();
		RollingStock lastCarriage = r;
		while (r != null) {
			lastCarriage = r;
			if (r.getClass().getName() == "asgn2RollingStock.Locomotive") {
				this.power = ((Locomotive) r).power();
				this.totalWeight += r.getGrossWeight();
			} else if (r.getClass().getName() == "asgn2RollingStock.PassengerCar") {
				this.passengersOnBoard += ((PassengerCar) r).numberOnBoard();
				this.totalSeats += ((PassengerCar) r).numberOfSeats();
				this.totalWeight += r.getGrossWeight();
			} else if (r.getClass().getName() == "asgn2RollingStock.FreightCar") {
				this.totalWeight += r.getGrossWeight();
			}
			r = theTrain.nextCarriage();
		}
		if (lastCarriage == null) {
			btnLocomotive.setEnabled(true);
			btnFreightcar.setEnabled(false);
			btnPassengercar.setEnabled(false);
			btnRemove.setEnabled(false);
			disableBoardingArea();
		} else if (lastCarriage.getClass().getName() == "asgn2RollingStock.Locomotive") {
			btnLocomotive.setEnabled(false);
			btnFreightcar.setEnabled(true);
			btnPassengercar.setEnabled(true);
			btnRemove.setEnabled(true);
			disableBoardingArea();
		} else if (lastCarriage.getClass().getName() == "asgn2RollingStock.PassengerCar") {
			btnLocomotive.setEnabled(false);
			btnFreightcar.setEnabled(true);
			btnPassengercar.setEnabled(true);
			btnRemove.setEnabled(true);
			enableBoardingArea();
		} else if (lastCarriage.getClass().getName() == "asgn2RollingStock.FreightCar") {
			btnLocomotive.setEnabled(false);
			btnFreightcar.setEnabled(true);
			btnPassengercar.setEnabled(false);
			btnRemove.setEnabled(true);
			if (totalSeats > 0) {
				enableBoardingArea();
			} else {
				disableBoardingArea();
			}
		}

		if (!theTrain.trainCanMove()) {
			btnBoard.setEnabled(false);
			btnLocomotive.setEnabled(false);
			btnFreightcar.setEnabled(false);
			btnPassengercar.setEnabled(false);
			btnRemove.setEnabled(true);
		}

		for (JPanel l : this.labelList) {
			trainChart.remove(l);
		}
		makeLabels();
		for (JPanel l : this.labelList) {
			trainChart.add(l);
		}
		trainChart.repaint();
		trainChart.revalidate();
		textDriver.setText(buildDriversText());
		textConductor.setText(buildConductorsText());
		if (passengersOnBoard > 0) {
			disableCompositionArea();
		}
	}

	/**
	 * Draw the labels according to the train's situation
	 */
	private void makeLabels() {
		List<JPanel> labels = new ArrayList<JPanel>();
		RollingStock r = theTrain.firstCarriage();
		if (r == null) {
			this.labelList = labels;
		} else
			while (r != null) {
				JPanel tmpPanel = new JPanel();
				tmpPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null,
						null, null, null));

				JLabel label = new JLabel("<html>" + r.toString() + "<br><br>"
						+ r.getGrossWeight() + " tonnes</html>");
				label.setPreferredSize(new Dimension(160, 90));
				label.setOpaque(true);
				label.setHorizontalAlignment(JLabel.CENTER);
				if (r.getClass().getName() == "asgn2RollingStock.Locomotive") {

					JProgressBar progressBar = new JProgressBar();
					tmpPanel.setBackground(Color.ORANGE);
					label.setBackground(Color.ORANGE);
					progressBar
							.setValue((int) (((double) totalWeight / (double) power) * 100));
					tmpPanel.setLayout(new MigLayout("", "[146px]",
							"[61px][14px]"));
					tmpPanel.add(label, "cell 0 0,grow");
					tmpPanel.add(progressBar, "cell 0 1,alignx left,aligny top");
				} else if (r.getClass().getName() == "asgn2RollingStock.PassengerCar") {
					JProgressBar progressBar = new JProgressBar();
					tmpPanel.setBackground(Color.GREEN);
					label.setBackground(Color.GREEN);
					progressBar
							.setValue((int) ((double) ((PassengerCar) r)
									.numberOnBoard()
									/ (double) ((PassengerCar) r)
											.numberOfSeats() * 100));
					tmpPanel.setLayout(new MigLayout("", "[146px]",
							"[61px][14px]"));
					tmpPanel.add(label, "cell 0 0,grow");
					tmpPanel.add(progressBar, "cell 0 1,alignx left,aligny top");
				} else if (r.getClass().getName() == "asgn2RollingStock.FreightCar") {
					tmpPanel.setBackground(Color.GRAY);
					label.setBackground(Color.GRAY);
					tmpPanel.setLayout(new MigLayout("", "[146px]",
							"[61px][14px]"));
					tmpPanel.add(label, "cell 0 0,grow");
				}
				labels.add(tmpPanel);
				r = theTrain.nextCarriage();
			}
		this.labelList = labels;
	}

	/**
	 * Set action listeners for all the buttons on the interface
	 */
	private void setListeners() {
		/*
		 * The action listener for remove carriage button
		 */
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

		/*
		 * The action listener for add freight car button
		 */
		btnFreightcar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FreightCarChoice freiChoice = new FreightCarChoice(
						MainPage.this.frame, theTrain);
				freiChoice.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				freiChoice.setVisible(true);
				reconfigure();
			}
		});

		/*
		 * The action listener for add passenger car button
		 */
		btnPassengercar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PassengerCarChoice passChoice = new PassengerCarChoice(
						MainPage.this.frame, theTrain);
				passChoice.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				passChoice.setVisible(true);
				reconfigure();
			}
		});

		/*
		 * The action listener for add locomotive button
		 */
		btnLocomotive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LocomotiveChoice locoChoice = new LocomotiveChoice(
						MainPage.this.frame, theTrain);
				locoChoice.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				locoChoice.setVisible(true);
				reconfigure();
			}
		});
		/*
		 * The action listener for reset button
		 */
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				passengersOnBoard = 0;
				totalSeats = 0;
				power = 0;
				totalWeight = 0;
				passengersRefused = 0;
				theTrain = new DepartingTrain();
				reconfigure();

			}
		});
		/*
		 * The action listener for board button
		 */
		btnBoard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int toBoard = Integer.parseInt((spinnerToBoard.getValue()
						.toString()));
				RollingStock r = theTrain.firstCarriage();
				int remaining = (totalSeats - passengersOnBoard); 			// now, remaining == seats available
				remaining = remaining > toBoard ? toBoard : remaining; 		// now, remaining == number to board
				passengersRefused += toBoard - remaining;
				passengersOnBoard += remaining;

				r = theTrain.nextCarriage();
				while (r != null
						&& r.getClass().getName() == "asgn2RollingStock.PassengerCar"
						&& remaining > 0) {
					if (remaining > ((PassengerCar) r).numberOfSeats()
							- ((PassengerCar) r).numberOnBoard()) {
						try {
							int tmpBoarding = ((PassengerCar) r)
									.numberOfSeats()
									- ((PassengerCar) r).numberOnBoard();
							((PassengerCar) r).board(tmpBoarding);
							remaining -= tmpBoarding;
						} catch (TrainException e1) {
							e1.printStackTrace();
						}
					} else {
						try {
							((PassengerCar) r).board(remaining);
							remaining = 0;
						} catch (TrainException e1) {
							e1.printStackTrace();
						}
					}
					r = theTrain.nextCarriage();
				}
				reconfigure();
			}
		});

	}

	/**
	 * Initialize the contents of the frame; And configure the initialization of
	 * data.
	 */
	public MainPage() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.control);
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
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 826, 120);
		trainChartPanel.add(scrollPane);
		JPanel driverPanel = new JPanel();
		driverPanel.setBorder(new TitledBorder(null, "Driver",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		driverPanel.setBackground(SystemColor.controlShadow);
		driverPanel.setBounds(0, 130, 398, 285);
		frame.getContentPane().add(driverPanel);
		driverPanel.setLayout(null);
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
		btnRemove.setBounds(248, 245, 140, 30);
		driverPanel.add(btnRemove);
		btnRemove.setEnabled(false);
		textDriver = new JTextPane();
		textDriver.setBounds(10, 28, 228, 247);
		textDriver.setEditable(false);
		driverPanel.add(textDriver);
		JPanel conductorPanel = new JPanel();
		conductorPanel.setBackground(SystemColor.controlShadow);
		conductorPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Conductor",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		conductorPanel.setBounds(414, 130, 412, 285);
		frame.getContentPane().add(conductorPanel);
		conductorPanel.setLayout(null);
		btnBoard = new JButton("Board");
		btnBoard.setBounds(262, 81, 140, 30);
		conductorPanel.add(btnBoard);
		textConductor = new JTextPane();
		textConductor.setBounds(10, 28, 228, 247);
		textConductor.setEditable(false);
		conductorPanel.add(textConductor);
		JLabel lblToBoard = new JLabel("To board:");
		lblToBoard.setFont(new Font("Arial", Font.PLAIN, 14));
		lblToBoard.setBounds(262, 46, 74, 15);
		conductorPanel.add(lblToBoard);
		boardNumberModel.setMinimum(0);
		spinnerToBoard = new JSpinner(boardNumberModel);
		spinnerToBoard.setBounds(328, 43, 74, 22);
		conductorPanel.add(spinnerToBoard);
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlShadow);
		panel.setBounds(0, 424, 826, 35);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		btnReset = new JButton("Reset");
		btnReset.setBounds(318, 4, 198, 28);
		panel.add(btnReset);
		/*
		 * Set action listeners for the buttons
		 */
		setListeners();
		/*
		 * Configure data and layouts
		 */
		reconfigure();
	}

	/**
	 * Application start method
	 * 
	 * @param args
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
}
