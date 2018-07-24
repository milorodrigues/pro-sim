package processSimulator;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		private Table_Processes processTable;
		private JPanel panelBtn;
			private JButton btnNewProcess;
			private JButton btnSwitch;
		
	private JPanel panelRow3;
		private Panel_CPU panelCPU;
		private Panel_RAM panelRAM;
		
	private JPanel panelRow4;
		private Panel_Disk panelDisk;
		
	private JPanel panelRow5;
		private Panel_Gantt panelGantt;
		
	public Window_Main() {
		super();
		
		processTable = new Table_Processes();
		panelGantt = new Panel_Gantt();
		
		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
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
			
			panelRow2.add(Box.createHorizontalStrut(20));
			panelRow2.add(processTable);
			
				panelBtn = new JPanel();
					panelBtn.setLayout(new BoxLayout(panelBtn, BoxLayout.Y_AXIS));
					panelBtn.setPreferredSize(new Dimension(140, 100));
					panelBtn.setMaximumSize(new Dimension(140, 100));
					panelBtn.setMinimumSize(new Dimension(140, 100));
			
					btnNewProcess = new JButton("Create process");
						btnNewProcess.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								new Window_CreateProcess();
							}
						});
						btnNewProcess.setPreferredSize(new Dimension(140, 30));
						btnNewProcess.setMaximumSize(new Dimension(140, 30));
						btnNewProcess.setMinimumSize(new Dimension(140, 30));
						btnNewProcess.setAlignmentY(CENTER_ALIGNMENT);
						panelBtn.add(btnNewProcess);
			
					if (Manager.running) {
						btnSwitch = new JButton("Pause");
					} else {
						btnSwitch = new JButton("Continue");
					}
						btnSwitch.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Manager.timerSwitch();
							}
						});
						btnSwitch.setPreferredSize(new Dimension(140, 30));
						btnSwitch.setMaximumSize(new Dimension(140, 30));
						btnSwitch.setMinimumSize(new Dimension(140, 30));
						btnSwitch.setAlignmentY(CENTER_ALIGNMENT);
						panelBtn.add(Box.createVerticalStrut(10));
						panelBtn.add(btnSwitch);
				
			panelRow2.add(Box.createHorizontalStrut(20));
			panelRow2.add(panelBtn);
			panelRow2.add(Box.createHorizontalStrut(20));
			
		panelRow3 = new JPanel();
			panelRow3.setLayout(new BoxLayout(panelRow3, BoxLayout.X_AXIS));
			panelRow3.setPreferredSize(new Dimension(800, 200));
			panelRow3.setMaximumSize(new Dimension(800, 200));
			panelRow3.setMinimumSize(new Dimension(800, 200));
			
			panelCPU = new Panel_CPU();
			panelRow3.add(panelCPU);
			panelRow3.add(Box.createHorizontalStrut(60));
			
			panelRAM = new Panel_RAM();
			panelRow3.add(panelRAM);
			
		panelRow4 = new JPanel();
			panelRow4.setLayout(new BoxLayout(panelRow4, BoxLayout.X_AXIS));
			
			panelDisk = new Panel_Disk();
			panelRow4.add(panelDisk);
			
		panelRow5 = new JPanel();
			panelRow5.setLayout(new BoxLayout(panelRow5, BoxLayout.X_AXIS));
			
			panelRow5.add(panelGantt);
		
		contentPane.add(panelRow1);
		contentPane.add(Box.createVerticalStrut(20));
		contentPane.add(panelRow2);
		contentPane.add(Box.createVerticalStrut(20));
		contentPane.add(panelRow3);
		contentPane.add(Box.createVerticalStrut(20));
		contentPane.add(panelRow4);
		//contentPane.add(Box.createVerticalStrut(20));
		//contentPane.add(panelRow5);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(950,850);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	public void loopWindow() {
		
		//JOptionPane.showMessageDialog(null, "in loopWindow()");
		
		lblTime.setText(Manager.timestr);
		
		processTableRefreshData();
		
		panelGantt.passTime();
		
		if (Scheduler.current == null) {
			panelCPU.refreshPanel("---");
		} else {
			panelCPU.refreshPanel(Integer.toString(Scheduler.current.pid));
		}
		Window_Main.this.repaint();
	}
	
	public void processTableRefreshData() {
		processTable.model.refreshData();
	}
	
	public void pauseSwitch() {
		
		if (Manager.running) btnSwitch.setText("Pause");
		else btnSwitch.setText("Continue");
		
		panelCPU.pauseSwitch();
		
	}

}
