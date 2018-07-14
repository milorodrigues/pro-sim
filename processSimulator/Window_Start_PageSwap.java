package processSimulator;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;

public class Window_Start_PageSwap extends Window_Start {

	private static final long serialVersionUID = 3736708375870576487L;
	
	private JButton btnFIFO;
	private JButton btnLRU;

	public Window_Start_PageSwap() {
		super();
		
		lblChoose.setText("Choose page swapping algorithm:");
		
		btnFIFO = new JButton("FIFO");
			btnFIFO.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Manager.algPageSwap = "FIFO";
					Manager.startProgram();
					dispose();
				}
			});
		
		btnLRU = new JButton("LRU");
			btnLRU.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Manager.algPageSwap = "LRU";
					Manager.startProgram();
					dispose();
				}
			});
		
		panelButtons.add(btnFIFO); panelButtons.add(Box.createRigidArea(new Dimension(15, 0)));
		panelButtons.add(btnLRU);
	}
	

}
