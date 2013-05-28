package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.PassengerCar;
import asgn2Train.DepartingTrain;

public class PassengerCarChoice extends JDialog {

	/**
	 * Generated Serial ID
	 */
	private static final long serialVersionUID = 2681701330896429107L;
	private final JPanel contentPanel = new JPanel();
	private SpinnerNumberModel grossWeightModel = new SpinnerNumberModel();
	private SpinnerNumberModel seatsModel = new SpinnerNumberModel();
	private DepartingTrain theTrain;
	private JSpinner txfGwPassengerCar;
	private JSpinner txfSeatsPassengerCar;
	/**
	 * Create the dialog.
	 */
	public PassengerCarChoice(Frame owner, DepartingTrain theTrain) {
		super(owner, true);
		this.theTrain = theTrain;
		setBounds(100, 100, 270, 203);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Gross Weight:");
			lblNewLabel.setBounds(21, 25, 87, 23);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblSeats = new JLabel("Seats:");
			lblSeats.setBounds(21, 58, 81, 23);
			contentPanel.add(lblSeats);
		}
		{
			grossWeightModel.setMinimum(0);
			txfGwPassengerCar = new JSpinner(grossWeightModel);
			txfGwPassengerCar.setBounds(107, 26, 137, 22);
			contentPanel.add(txfGwPassengerCar);
		}
		{
			seatsModel.setMinimum(0);
			txfSeatsPassengerCar = new JSpinner(seatsModel);
			txfSeatsPassengerCar.setBounds(107, 58, 137, 22);
			contentPanel.add(txfSeatsPassengerCar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txfGwPassengerCar.getValue().equals("") || txfSeatsPassengerCar.getValue().equals(""))
							JOptionPane.showMessageDialog(null, "Please fill in the required field(s)", "Warning",JOptionPane.WARNING_MESSAGE); 
						else{
							PassengerCar p;
							try {
								p = new PassengerCar(Integer.parseInt(txfGwPassengerCar.getValue().toString()), 
										Integer.parseInt(txfSeatsPassengerCar.getValue().toString()));
								PassengerCarChoice.this.theTrain.addCarriage(p);
								dispose();
							} catch (NumberFormatException | TrainException e1) {
								JOptionPane.showMessageDialog(null, "Please input legal value(s)", "Warning",JOptionPane.WARNING_MESSAGE); 
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
