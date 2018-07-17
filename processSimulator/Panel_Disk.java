package processSimulator;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel_Disk extends JPanel {

	private static final long serialVersionUID = 8573936053797222407L;
	
	private JLabel title;
	private JPanel table;

	public Panel_Disk() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		title = new JLabel("Disk");
		title.setFont(new Font("Verdana", Font.BOLD, 14));
			title.setAlignmentY(Component.CENTER_ALIGNMENT);
			add(title);
			
		add(Box.createVerticalStrut(10));
		
		table = new JPanel();
			table.setAlignmentY(Component.CENTER_ALIGNMENT);
			table.setLayout(new GridLayout(5, 10, -1, -1));
			table.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
			
			for (int i=0;i<100;i++) {
				table.add(Manager.swapper.pagesDisk.elementAt(i));
			}
			
		add(table);
		
		this.setPreferredSize(new Dimension(780, 200));
		this.setVisible(true);
		
	}

}
