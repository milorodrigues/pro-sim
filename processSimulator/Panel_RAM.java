package processSimulator;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class Panel_RAM extends JPanel {

	private static final long serialVersionUID = 4647133043724112979L;
	
	private JLabel title;
	private JPanel table;

	public Panel_RAM() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		title = new JLabel("RAM");
		title.setFont(new Font("Verdana", Font.BOLD, 14));
			title.setAlignmentY(Component.CENTER_ALIGNMENT);
			add(title);
			
		add(Box.createVerticalStrut(10));
		
		table = new JPanel();
			table.setAlignmentY(Component.CENTER_ALIGNMENT);
			table.setLayout(new GridLayout(5, 10, -1, -1));
			table.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
			
			for (int i=0;i<50;i++) {
				table.add(Manager.swapper.pagesRAM.elementAt(i));
			}
			
		add(table);
		
		this.setVisible(true);
		
	}

}
