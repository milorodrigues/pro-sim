package processSimulator;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window_Start_Scheduling extends Window_Start {

	private static final long serialVersionUID = -3769411600368361272L;
	
	private JButton btnFIFO;
	private JButton btnSJF;
	private JButton btnRR;
	private JButton btnEDF;

	public Window_Start_Scheduling() {		
		super();
		
		lblChoose.setText("Choose scheduling algorithm:");
		
		btnFIFO = new JButton("FIFO");
			btnFIFO.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.algScheduling = "FIFO";
					new Window_Start_PageSwap();
					dispose();
				}
			});
		
		btnSJF = new JButton("SJF");
			btnSJF.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.algScheduling = "SJF";
					new Window_Start_PageSwap();
					dispose();
				}
			});
			
		btnRR = new JButton("Round Robin");
			btnRR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.algScheduling = "RR";
					new Window_Start_PageSwap();
					dispose();
				}
			});
		
		btnEDF = new JButton("EDF");
			btnEDF.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.algScheduling = "EDF";
					new Window_Start_PageSwap();
					dispose();
				}
			});
			
		panelButtons.add(btnFIFO); panelButtons.add(Box.createRigidArea(new Dimension(15, 0)));
		panelButtons.add(btnSJF); panelButtons.add(Box.createRigidArea(new Dimension(15, 0)));
		panelButtons.add(btnRR); panelButtons.add(Box.createRigidArea(new Dimension(15, 0)));
		panelButtons.add(btnEDF);
	}
	
}
