package processSimulator;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Dimension;

public class Window_CreateProcess extends Window_Mother {

	private static final long serialVersionUID = 4393429108883536338L;
	
	private JPanel panelFields;
	
	private JLabel lblPID;
	private JLabel fieldPID;
	private JLabel lblDuration;
	private JTextField fieldDuration;
	private JLabel lblPriority;
	private JTextField fieldPriority;
	private JLabel lblDeadline;
	private JTextField fieldDeadline;
	private JLabel lblEntryDelay;
	private JTextField fieldEntryDelay;
	
	private JButton btnCreate;

	public Window_CreateProcess() {
		
		panelFields = new JPanel();
			panelFields.setLayout(new GridLayout(5, 2, 5, 5));
			
			lblPID = new JLabel("Process ID:");	
				lblPID.setFont(new Font("Verdana", Font.BOLD, 12));
				
			lblDuration = new JLabel("Duration:");	
				lblDuration.setFont(new Font("Verdana", Font.BOLD, 12));
			
			lblPriority = new JLabel("Priority:");	
				lblPriority.setFont(new Font("Verdana", Font.BOLD, 12));
				
			lblDeadline = new JLabel("Deadline:");	
				lblDeadline.setFont(new Font("Verdana", Font.BOLD, 12));
				
			lblEntryDelay = new JLabel("Delay entry by:");	
				lblEntryDelay.setFont(new Font("Verdana", Font.BOLD, 12));
			
			fieldPID = new JLabel(Integer.toString(Manager.scheduler.generatePID()));
			
			fieldDuration = new JTextField("1");
			
			fieldPriority = new JTextField("1");
			
			fieldDeadline = new JTextField("1");
				if (Manager.algScheduling == "FIFO" || Manager.algScheduling == "SJF") {
					fieldPriority.setText(""); fieldPriority.setEnabled(false);
					fieldDeadline.setText(""); fieldDeadline.setEnabled(false);
				}
				
			fieldEntryDelay = new JTextField("0");
			
		panelFields.add(lblPID);
		panelFields.add(fieldPID);
		panelFields.add(lblDuration);
		panelFields.add(fieldDuration);
		panelFields.add(lblPriority);
		panelFields.add(fieldPriority);
		panelFields.add(lblDeadline);
		panelFields.add(fieldDeadline);
		panelFields.add(lblEntryDelay);
		panelFields.add(fieldEntryDelay);
		
		btnCreate = new JButton("Create");
			btnCreate.setAlignmentX(CENTER_ALIGNMENT);
			btnCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String response;
					if (Manager.algScheduling == "FIFO" || Manager.algScheduling == "SJF" || Manager.algScheduling == "RR") {
						try {
							response = Manager.scheduler.addProcess(Integer.parseInt(fieldDuration.getText()), -1, -1, Integer.parseInt(fieldEntryDelay.getText()));
							if (response == "full") {
								JOptionPane.showMessageDialog(null, "Process queue is full!");
							} else if (response == "fail") {
								JOptionPane.showMessageDialog(null, "Error creating process");
							}
						} catch(NumberFormatException nfe) {
							JOptionPane.showMessageDialog(null, "Invalid input: " + nfe.getMessage());
							new Window_CreateProcess();
						} finally {
							dispose();
						}
					} else if (Manager.algScheduling == "EDF") {
						try {
							response = Manager.scheduler.addProcess(Integer.parseInt(fieldDuration.getText()), Integer.parseInt(fieldDeadline.getText()), -1, Integer.parseInt(fieldEntryDelay.getText()));
							if (response == "full") {
								JOptionPane.showMessageDialog(null, "Process queue is full!");
							} else if (response == "fail") {
								JOptionPane.showMessageDialog(null, "Error creating process");
							}
						} catch(NumberFormatException nfe) {
							JOptionPane.showMessageDialog(null, "Invalid input: " + nfe.getMessage());
							new Window_CreateProcess();
						} finally {
							dispose();
						}
					}
				}
			});
		
		contentPane.add(panelFields);
		contentPane.add(Box.createVerticalStrut(5));
		contentPane.add(btnCreate);
		
		this.setSize(new Dimension(266, 223));
		this.setLocationRelativeTo(null);
		this.setVisible(true);		
	}

}
