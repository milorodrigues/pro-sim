package processSimulator;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Gantt_Unit extends JPanel {

	private static final long serialVersionUID = 8640442622682219045L;
	
	public Color color;
	
	public Gantt_Unit(Color color) {
		
		this.color = color;
		
		this.setPreferredSize(new Dimension(17,17));
		this.setMaximumSize(new Dimension(17,17));
		this.setMinimumSize(new Dimension(17,17));
		
		this.setBackground(color);
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		
		this.setVisible(true);
		
	}

}
