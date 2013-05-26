package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PassengerCarChoice extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txfGwPassengerCar;
	private JTextField txfSeatsPassengerCar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PassengerCarChoice dialog = new PassengerCarChoice();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PassengerCarChoice() {
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
			txfGwPassengerCar = new JTextField();
			txfGwPassengerCar.setBounds(107, 26, 104, 21);
			contentPanel.add(txfGwPassengerCar);
			txfGwPassengerCar.setColumns(10);
		}
		{
			JLabel lblSeats = new JLabel("Seats:");
			lblSeats.setBounds(21, 58, 81, 23);
			contentPanel.add(lblSeats);
		}
		{
			txfSeatsPassengerCar = new JTextField();
			txfSeatsPassengerCar.setColumns(10);
			txfSeatsPassengerCar.setBounds(107, 59, 104, 21);
			contentPanel.add(txfSeatsPassengerCar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
