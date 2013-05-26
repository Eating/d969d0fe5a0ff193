package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FreightCarChoice extends JDialog {

	private final JPanel cmbGoodsType = new JPanel();
	private JTextField txfGwFreightCar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FreightCarChoice dialog = new FreightCarChoice();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FreightCarChoice() {
		setBounds(100, 100, 296, 249);
		getContentPane().setLayout(new BorderLayout());
		cmbGoodsType.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(cmbGoodsType, BorderLayout.CENTER);
		cmbGoodsType.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Gross Weight:");
			lblNewLabel.setBounds(10, 27, 83, 26);
			cmbGoodsType.add(lblNewLabel);
		}
		{
			txfGwFreightCar = new JTextField();
			txfGwFreightCar.setBounds(103, 30, 106, 21);
			cmbGoodsType.add(txfGwFreightCar);
			txfGwFreightCar.setColumns(10);
		}
		{
			JLabel lblGoodsType = new JLabel("Goods Type:");
			lblGoodsType.setBounds(10, 85, 73, 15);
			cmbGoodsType.add(lblGoodsType);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"General Goods", "Refrigerated Goods", "Dangerous Material"}));
			comboBox.setBounds(103, 82, 106, 21);
			cmbGoodsType.add(comboBox);
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
