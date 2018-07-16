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
	
	private JPanel panelRow1;	
		private JLabel lblTimeTitle;
		private JLabel lblTime;
		private JLabel lblSchedulingTitle;
		private JLabel lblScheduling;
		private JLabel lblSwappingTitle;
		private JLabel lblSwapping;
	
	private JPanel panelRow2;
		private JButton btnNewProcess;
	
	private JPanel panelRow3;
		private Table_Processes processTable;
		private Panel_CPU panelCPU;
		
	public Window_Main() {
		super();
		
		processTable = new Table_Processes();
		
		panelRow1 = new JPanel();
			panelRow1.setLayout(new BoxLayout(panelRow1, BoxLayout.X_AXIS));
		
			lblTimeTitle = new JLabel("Time:");
				lblTimeTitle.setFont(new Font("Verdana", Font.BOLD, 12));
				panelRow1.add(lblTimeTitle);
				panelRow1.add(Box.createRigidArea(new Dimension(10,0)));
				
			lblTime = new JLabel(Manager.timestr);
				lblTime.setFont(new Font("Verdana", Font.PLAIN, 12));
				panelRow1.add(lblTime);
				panelRow1.add(Box.createRigidArea(new Dimension(40,0)));
			
			lblSchedulingTitle = new JLabel("Scheduling algorithm:");
				lblSchedulingTitle.setFont(new Font("Verdana", Font.BOLD, 12));
				panelRow1.add(lblSchedulingTitle);
				panelRow1.add(Box.createRigidArea(new Dimension(10,0)));
				
			lblScheduling = new JLabel(Manager.algScheduling);
				lblScheduling.setFont(new Font("Verdana", Font.PLAIN, 12));
				panelRow1.add(lblScheduling);
				panelRow1.add(Box.createRigidArea(new Dimension(40,0)));
				
			lblSwappingTitle = new JLabel("Page swapping algorithm:");
				lblSwappingTitle.setFont(new Font("Verdana", Font.BOLD, 12));
				panelRow1.add(lblSwappingTitle);
				panelRow1.add(Box.createRigidArea(new Dimension(10,0)));
				
			lblSwapping = new JLabel(Manager.algPageSwap);
				lblSwapping.setFont(new Font("Verdana", Font.PLAIN, 12));
				panelRow1.add(lblSwapping);
				
		panelRow2 = new JPanel();
			panelRow2.setLayout(new BoxLayout(panelRow2, BoxLayout.X_AXIS));
			
			btnNewProcess = new JButton("Create process");
				btnNewProcess.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new Window_CreateProcess();
					}
				});
				panelRow2.add(btnNewProcess);
				
		panelRow3 = new JPanel();
			panelRow3.setLayout(new BoxLayout(panelRow3, BoxLayout.X_AXIS));
			
			panelRow3.add(processTable);
			panelRow3.add(Box.createHorizontalStrut(10));
			
			panelCPU = new Panel_CPU();
			panelRow3.add(panelCPU);
		
		contentPane.add(Box.createVerticalStrut(5));
		contentPane.add(panelRow1);
		contentPane.add(Box.createVerticalStrut(5));
		contentPane.add(panelRow2);
		contentPane.add(Box.createVerticalStrut(20));
		contentPane.add(panelRow3);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(850,780);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	public void loopWindow() {
		
		//JOptionPane.showMessageDialog(null, "in loopWindow()");
		
		lblTime.setText(Manager.timestr);
		
		processTableRefreshData();
		
		if (Manager.scheduler.current == null) {
			panelCPU.refreshPanel("---");
		} else {
			panelCPU.refreshPanel(Integer.toString(Manager.scheduler.current.pid));
		}
		Window_Main.this.repaint();
	}
	
	public void processTableRefreshData() {
		processTable.model.refreshData();
	}

}
