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
import asgn2RollingStock.Locomotive;
import asgn2Train.DepartingTrain;

public class LocomotiveChoice extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txfGwLoco;
	private JTextField txfPwLoco;
	private JComboBox cmbType;
	private int grossWeight;
	private DepartingTrain theTrain;

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
			txfGwLoco = new JTextField();
			txfGwLoco.setBounds(109, 16, 82, 21);
			contentPanel.add(txfGwLoco);
			txfGwLoco.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Power:");
			lblNewLabel.setBounds(5, 38, 73, 32);
			contentPanel.add(lblNewLabel);
		}
		
		txfPwLoco = new JTextField();
		txfPwLoco.setColumns(10);
		txfPwLoco.setBounds(109, 44, 82, 21);
		contentPanel.add(txfPwLoco);
		
		JLabel lblNewLabel_1 = new JLabel("Type:");
		lblNewLabel_1.setBounds(5, 80, 54, 18);
		contentPanel.add(lblNewLabel_1);
		
		cmbType = new JComboBox();
		cmbType.setModel(new DefaultComboBoxModel(new String[] {"Electric", "Diesel", "Stream"}));
		cmbType.setBounds(109, 79, 82, 21);
		contentPanel.add(cmbType);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txfGwLoco.getText().equals("") || txfPwLoco.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Please fill in the required field(s)", "Warning",JOptionPane.WARNING_MESSAGE); 
						else{
							String classification = txfPwLoco.getText();
							classification += cmbType.getSelectedItem().toString().charAt(0);
							Locomotive l;
							try {
								l = new Locomotive(Integer.parseInt(txfGwLoco.getText()), classification);
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
