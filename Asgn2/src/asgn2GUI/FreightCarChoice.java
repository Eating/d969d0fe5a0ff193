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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2Train.DepartingTrain;

public class FreightCarChoice extends JDialog {

	private final JPanel cmbGoodsType = new JPanel();
	private JTextField txfGwFreightCar;
	private JComboBox comboBox;
	private DepartingTrain theTrain;
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
			comboBox = new JComboBox();
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

				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txfGwFreightCar.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Please fill in the required field(s)", "Warning",JOptionPane.WARNING_MESSAGE); 
						else{
							FreightCar f;
							try {
								String goodsType = "" + FreightCarChoice.this.comboBox.getSelectedItem().toString().charAt(0);
								f = new FreightCar(Integer.parseInt(txfGwFreightCar.getText()), goodsType);
								FreightCarChoice.this.theTrain.addCarriage(f);
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
