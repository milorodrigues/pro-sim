package processSimulator;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class Table_Processes extends JPanel{
	
	private static final long serialVersionUID = -4707911277427140470L;
	
	public TableModel_Processes model;
	public JTable table;

	public Table_Processes() {
		super(new GridLayout(1,0));
		
		model = new TableModel_Processes();
		table = new JTable(model);
		//table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
        this.setMaximumSize(new Dimension(600, 300));
	}
	
	class TableModel_Processes extends AbstractTableModel{

		private static final long serialVersionUID = -7264052063418155379L;
		
		private String[] columnNames = {"Process ID", "Duration left", "Deadline", "Priority"};
		private Object[][] data = Manager.scheduler.getProcessData();
		
		public void refreshData() {
			data = Manager.scheduler.getProcessData();
			fireTableDataChanged();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public Object getValueAt(int arg0, int arg1) {
			return data[arg0][arg1];
		}
		
		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}
		
	}
	
}
