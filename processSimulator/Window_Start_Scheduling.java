package processSimulator;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class Window_Start_Scheduling extends Window_Start {

	private static final long serialVersionUID = -3769411600368361272L;
	
	private JButton btnFIFO;
	private JButton btnSJF;
	private JButton btnRR;
	private JButton btnEDF;
	
	private JPanel quantumoverload;
	private JPanel panelQuantum;
		private JLabel lblQuantum;
		private JTextField textQuantum;
	private JPanel panelOverload;
		private JLabel lblOverload;
		private JTextField textOverload;
	private JButton btnGo;
	
	public Window_Start_Scheduling() {		
		super();
		
		lblChoose.setText("Choose scheduling algorithm:");
		
		btnFIFO = new JButton("FIFO");
			btnFIFO.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Manager.algScheduling = "FIFO";
					new Window_Start_PageSwap();
					dispose();
				}
			});
		
		btnSJF = new JButton("SJF");
			btnSJF.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Manager.algScheduling = "SJF";
					new Window_Start_PageSwap();
					dispose();
				}
			});
			
		btnRR = new JButton("Round Robin");
			btnRR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Manager.algScheduling = "RR";
					askQuantumOverload();
				}
			});
		
		btnEDF = new JButton("EDF");
			btnEDF.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Manager.algScheduling = "EDF";
					askQuantumOverload();
				}
			});
			
		panelButtons.add(btnFIFO); panelButtons.add(Box.createRigidArea(new Dimension(15, 0)));
		panelButtons.add(btnSJF); panelButtons.add(Box.createRigidArea(new Dimension(15, 0)));
		panelButtons.add(btnRR); panelButtons.add(Box.createRigidArea(new Dimension(15, 0)));
		panelButtons.add(btnEDF);

	}
	
	public void askQuantumOverload() {
		
		quantumoverload = new JPanel();
			quantumoverload.setLayout(new BoxLayout(quantumoverload, BoxLayout.Y_AXIS));
			quantumoverload.setMaximumSize(new Dimension(200,100));
			quantumoverload.setAlignmentY(CENTER_ALIGNMENT);
			
		panelQuantum = new JPanel();
			panelQuantum.setLayout(new BoxLayout(panelQuantum, BoxLayout.X_AXIS));
			lblQuantum = new JLabel("Quantum:");
			textQuantum = new JTextField();
				textQuantum.setText("1");
				textQuantum.setMaximumSize(new Dimension(40, 20));
			
			panelQuantum.add(lblQuantum); panelQuantum.add(Box.createRigidArea(new Dimension(10, 0)));
			panelQuantum.add(textQuantum);
			panelQuantum.setAlignmentY(CENTER_ALIGNMENT);
			
		panelOverload = new JPanel();
			panelOverload.setLayout(new BoxLayout(panelOverload, BoxLayout.X_AXIS));
			lblOverload = new JLabel("Overload:");
			textOverload = new JTextField();
				textOverload.setText("1");
				textOverload.setMaximumSize(new Dimension(40, 20));
			
			panelOverload.add(lblOverload); panelOverload.add(Box.createRigidArea(new Dimension(10, 0)));
			panelOverload.add(textOverload);
			panelOverload.setAlignmentY(CENTER_ALIGNMENT);
			
		btnGo = new JButton("Go");
			btnGo.setAlignmentY(RIGHT_ALIGNMENT);
			btnGo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Manager.quantum = Integer.parseInt(textQuantum.getText());
					Manager.overload = Integer.parseInt(textOverload.getText());
					new Window_Start_PageSwap();
					dispose();
				}
			});
		
		quantumoverload.add(Box.createVerticalStrut(20));
		quantumoverload.add(panelQuantum);
		quantumoverload.add(Box.createVerticalStrut(3));
		quantumoverload.add(panelOverload);
		quantumoverload.add(Box.createVerticalStrut(3));
		quantumoverload.add(btnGo);
		
		contentPane.add(quantumoverload);
		Window_Start_Scheduling.this.setSize(new Dimension(437,205));
		Window_Start_Scheduling.this.setLocationRelativeTo(null);
	}
	
}
