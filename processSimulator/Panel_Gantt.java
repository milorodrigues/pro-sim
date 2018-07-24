package processSimulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Panel_Gantt extends JPanel {

	private static final long serialVersionUID = -6073059082828965738L;
	
	private JScrollPane scrollMain;
		private JPanel panelMain;
			private JPanel panelRow1;
				private JLabel lblProcesses;
				private JPanel timeRow;
			private JPanel panelRow2;
				private JPanel panelIDs;
				private JPanel panelGraph;
				
	private int processNum = Scheduler.list.size();
	private int rowSize = 45;
	
	private LinkedList<LinkedList<Gantt_Unit>> cells;
	
	public Panel_Gantt() {
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		initializeCells();
		
		scrollMain = new JScrollPane();
			
			panelMain = new JPanel();
				panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
				
				panelRow1 = new JPanel();
					panelRow1.setLayout(new BoxLayout(panelRow1, BoxLayout.X_AXIS));
					
					lblProcesses = new JLabel("Processes");
						lblProcesses.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
					panelRow1.add(lblProcesses);
					
					timeRow = buildTimeRow(0);
					panelRow1.add(timeRow);
				
				panelRow2 = new JPanel();
					panelRow2.setLayout(new BoxLayout(panelRow2, BoxLayout.X_AXIS));
				
					panelIDs = new JPanel();
						panelIDs.setLayout(new BoxLayout(panelIDs, BoxLayout.Y_AXIS));
						initializePanelIDs();
					panelRow2.add(panelIDs);
					
					panelGraph = new JPanel();
						panelGraph = buildPanelGraph();
					panelRow2.add(panelGraph);
				
				panelMain.add(panelRow1);
				panelMain.add(panelRow2);
			
			scrollMain.setViewportView(panelMain);
			
		add(scrollMain);
		
		this.setPreferredSize(new Dimension(880, 200));
		this.setMaximumSize(new Dimension(880, 200));
		this.setMinimumSize(new Dimension(880, 200));
		
	}
	
	public void initializePanelIDs() {
		
		for (int i=0; i<Scheduler.list.size(); i++) {
			JLabel pid = new JLabel(Integer.toString(Scheduler.list.get(i).pid));
			
			int borderThickness = lblProcesses.getPreferredSize().width - pid.getPreferredSize().width;
				
			pid.setBorder(BorderFactory.createEmptyBorder(0, borderThickness - 4, 0, 4));
			
			panelIDs.add(pid);
		}
		
	}
	
	public void initializeCells() {
		
		cells = new LinkedList<LinkedList<Gantt_Unit>>();
		
		for (int i=0;i<processNum;i++) {
			LinkedList<Gantt_Unit> row = new LinkedList<Gantt_Unit>();
			for (int j=0; j<rowSize; j++) {
				row.add(new Gantt_Unit(Color.WHITE));
			}
			cells.add(row);
		}
		
	}
	
	public JPanel buildPanelGraph() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(processNum, rowSize, -1, -1));
		
		for (int i=0;i<processNum;i++) {
			for (int j=0; j<rowSize; j++) {
				panel.add(cells.get(i).get(j));
			}
		}
		
		return panel;
	}
	
	public JPanel buildTimeRow(int last) {
		
		JPanel row = new JPanel();
			row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
		
		for (int i=0;i<rowSize;i++) {
			JLabel t = new JLabel(Integer.toString(last));
			if (last > 0) last--;
			row.add(t);
		}
		
		return row;		
	}
	
	public void passTime() {
		
		timeRow = buildTimeRow(Manager.time);
		
		for (int i=0;i<cells.size(); i++) {
			cells.get(i).poll();
			cells.get(i).add(new Gantt_Unit(Color.BLUE));
		}
		
		panelGraph = buildPanelGraph();
		
		repaint();
	}
}
