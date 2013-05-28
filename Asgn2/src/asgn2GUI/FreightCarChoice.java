/**
 * @author Yiting Zhang  
 * 
 * @studentNumber 8779210
 * 
 * @version 1.0
 */
package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2Train.DepartingTrain;

public class FreightCarChoice extends JDialog {

	/**
	 * Generated Serial ID
	 */
	private static final long serialVersionUID = -1792322993888846189L;

	private final JPanel cmbGoodsType = new JPanel();
	private JComboBox<String> comboBox;
	private DepartingTrain theTrain;
	private SpinnerNumberModel grossWeightModel = new SpinnerNumberModel();

	/**
	 * Create the dialog.
	 */
	public FreightCarChoice(Frame owner, DepartingTrain theTrain) {
		super(owner, true);
		this.theTrain = theTrain;
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
			JLabel lblGoodsType = new JLabel("Goods Type:");
			lblGoodsType.setBounds(10, 85, 73, 15);
			cmbGoodsType.add(lblGoodsType);
		}
		{
			comboBox = new JComboBox<String>();
			comboBox.setModel(new DefaultComboBoxModel<String>(
					new String[] { "General Goods", "Refrigerated Goods",
							"Dangerous Material" }));
			comboBox.setBounds(103, 82, 146, 21);
			cmbGoodsType.add(comboBox);
		}

		grossWeightModel.setMinimum(0);
		final JSpinner txfGwFreightCar = new JSpinner(grossWeightModel);
		txfGwFreightCar.setBounds(103, 30, 146, 22);
		cmbGoodsType.add(txfGwFreightCar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");

				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txfGwFreightCar.getValue().equals(""))
							JOptionPane.showMessageDialog(null,
									"Please fill in the required field(s)",
									"Warning", JOptionPane.WARNING_MESSAGE);
						else {
							FreightCar f;
							try {
								String goodsType = ""
										+ FreightCarChoice.this.comboBox
												.getSelectedItem().toString()
												.charAt(0);
								f = new FreightCar(Integer
										.parseInt(txfGwFreightCar.getValue()
												.toString()), goodsType);
								FreightCarChoice.this.theTrain.addCarriage(f);
								dispose();
							} catch (NumberFormatException | TrainException e1) {
								JOptionPane.showMessageDialog(null,
										"Please input legal value(s)",
										"Warning", JOptionPane.WARNING_MESSAGE);
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
