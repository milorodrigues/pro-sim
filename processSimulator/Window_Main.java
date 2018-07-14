package processSimulator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window_Main extends Window_Mother {

	private static final long serialVersionUID = 7219300308707987032L;
	
	private JPanel panelLabels;	
		private JLabel lblTimeTitle;
		private JLabel lblTime;
		private JLabel lblSchedulingTitle;
		private JLabel lblScheduling;
		private JLabel lblSwappingTitle;
		private JLabel lblSwapping;
	
	private JPanel panelButtons;
		private JButton btnNewProcess;
		
	public Window_Main() {
		super();
		
		panelLabels = new JPanel();
			panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.X_AXIS));
		
			lblTimeTitle = new JLabel("Time:");
				lblTimeTitle.setFont(new Font("Verdana", Font.BOLD, 12));
				panelLabels.add(lblTimeTitle);
				panelLabels.add(Box.createRigidArea(new Dimension(10,0)));
				
			lblTime = new JLabel(Manager.timestr);
				lblTime.setFont(new Font("Verdana", Font.PLAIN, 12));
				panelLabels.add(lblTime);
				panelLabels.add(Box.createRigidArea(new Dimension(40,0)));
			
			lblSchedulingTitle = new JLabel("Scheduling algorithm:");
				lblSchedulingTitle.setFont(new Font("Verdana", Font.BOLD, 12));
				panelLabels.add(lblSchedulingTitle);
				panelLabels.add(Box.createRigidArea(new Dimension(10,0)));
				
			lblScheduling = new JLabel(Manager.algScheduling);
				lblScheduling.setFont(new Font("Verdana", Font.PLAIN, 12));
				panelLabels.add(lblScheduling);
				panelLabels.add(Box.createRigidArea(new Dimension(40,0)));
				
			lblSwappingTitle = new JLabel("Page swapping algorithm:");
				lblSwappingTitle.setFont(new Font("Verdana", Font.BOLD, 12));
				panelLabels.add(lblSwappingTitle);
				panelLabels.add(Box.createRigidArea(new Dimension(10,0)));
				
			lblSwapping = new JLabel(Manager.algPageSwap);
				lblSwapping.setFont(new Font("Verdana", Font.PLAIN, 12));
				panelLabels.add(lblSwapping);
				
		panelButtons = new JPanel();
			panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
			
			btnNewProcess = new JButton("Create process");
				btnNewProcess.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new Window_CreateProcess();
					}
				});
				panelButtons.add(btnNewProcess);
		
		contentPane.add(Box.createVerticalStrut(5));
		contentPane.add(panelLabels);
		contentPane.add(Box.createVerticalStrut(5));
		contentPane.add(panelButtons);		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(850,780);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		//Manager.time++; Manager.timestr = Manager.timeformat.format(Manager.time);
		//loopWindow();
		
	}
	
	public void loopWindow() {
		
		//JOptionPane.showMessageDialog(null, "in loopWindow()");
		
		lblTime.setText(Manager.timestr);
		//Window_Main.this.repaint();
	}

}
