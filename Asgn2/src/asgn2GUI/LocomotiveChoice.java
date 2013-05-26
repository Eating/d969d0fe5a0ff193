package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LocomotiveChoice extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txfGwLoco;
	private JTextField txfPwLoco;
	private int grossWeight;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LocomotiveChoice dialog = new LocomotiveChoice();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LocomotiveChoice() {
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
		
		JComboBox cmbType = new JComboBox();
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
							JOptionPane.showMessageDialog(null, "please complete blank", "warning",JOptionPane.WARNING_MESSAGE); 
						else{
							
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
