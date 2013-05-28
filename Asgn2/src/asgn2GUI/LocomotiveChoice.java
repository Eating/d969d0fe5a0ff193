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
import asgn2RollingStock.Locomotive;
import asgn2Train.DepartingTrain;

public class LocomotiveChoice extends JDialog {

	/**
	 * Generated Serial ID
	 */
	private static final long serialVersionUID = -537534395088206795L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> cmbType;
	private DepartingTrain theTrain;
	private SpinnerNumberModel grossWeightModel = new SpinnerNumberModel();
	private SpinnerNumberModel powerModel = new SpinnerNumberModel();
	private JSpinner txfGwLoco;
	private JSpinner txfPwLoco;

	/**
	 * Create the dialog.
	 */
	public LocomotiveChoice(Frame owner, DepartingTrain theTrain) {
		super(owner, true);
		this.theTrain = theTrain;
		setTitle("Locomotive");
		setBounds(100, 100, 332, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblGrossweight = new JLabel("Grossweight:");
			lblGrossweight.setBounds(5, 6, 107, 32);
			contentPanel.add(lblGrossweight);
		}
		{
			JLabel lblNewLabel = new JLabel("Power:");
			lblNewLabel.setBounds(5, 38, 73, 32);
			contentPanel.add(lblNewLabel);
		}
		
		JLabel lblNewLabel_1 = new JLabel("Type:");
		lblNewLabel_1.setBounds(5, 80, 54, 18);
		contentPanel.add(lblNewLabel_1);
		
		cmbType = new JComboBox<String>();
		cmbType.setModel(new DefaultComboBoxModel<String>(new String[] {"Electric", "Diesel", "Stream"}));
		cmbType.setBounds(109, 79, 175, 21);
		contentPanel.add(cmbType);
		grossWeightModel.setMinimum(0);
		powerModel.setMinimum(1);
		powerModel.setMaximum(9);
		{
			txfGwLoco = new JSpinner(this.grossWeightModel);
			txfGwLoco.setBounds(109, 12, 175, 22);
			contentPanel.add(txfGwLoco);
		}
		{
			txfPwLoco = new JSpinner(this.powerModel);
			txfPwLoco.setBounds(109, 44, 175, 22);
			contentPanel.add(txfPwLoco);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txfGwLoco.getValue().equals("") || txfPwLoco.getValue().equals(""))
							JOptionPane.showMessageDialog(null, "Please fill in the required field(s)", "Warning",JOptionPane.WARNING_MESSAGE); 
						else{
							String classification = txfPwLoco.getValue().toString();
							classification += cmbType.getSelectedItem().toString().charAt(0);
							Locomotive l;
							try {
								l = new Locomotive(Integer.parseInt(txfGwLoco.getValue().toString()), classification);
								LocomotiveChoice.this.theTrain.addCarriage(l);
								System.out.println(LocomotiveChoice.this.theTrain.firstCarriage());
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
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
}
