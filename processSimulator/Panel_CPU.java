package processSimulator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Panel_CPU extends JPanel {

	private static final long serialVersionUID = -1921552106413736589L;
	
	private JLabel lblCpu;
	private JLabel lblPid;
	private JLabel lblOverloading;
	private JPanel panelPid;

	public Panel_CPU() {
		super();
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(Box.createVerticalStrut(15));
		
		lblCpu = new JLabel("CPU");
			lblCpu.setFont(new Font("Verdana", Font.BOLD, 16));
			lblCpu.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(lblCpu);
			
		add(Box.createVerticalStrut(15));
		
		panelPid = new JPanel();
			panelPid.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			panelPid.setMaximumSize(new Dimension(100, 50));			
			panelPid.setLayout(null);
					
			lblPid = new JLabel("---");
				lblPid.setHorizontalAlignment(SwingConstants.CENTER);
				lblPid.setBounds(33, 11, 34, 28);
				lblPid.setFont(new Font("Verdana", Font.PLAIN, 14));
				panelPid.add(lblPid);
				
			add(panelPid);
			
		add(Box.createVerticalStrut(10));
			
		lblOverloading = new JLabel("Overloading!");
			lblOverloading.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblOverloading.setVisible(false);
			add(lblOverloading);
			
		add(Box.createVerticalStrut(10));
		this.setMaximumSize(new Dimension(200, 300));
		this.setMinimumSize(new Dimension(200, 300));
	
	}
	
	public void refreshPanel(String current) {
		
		if (Manager.inQuantum == false) {
			lblOverloading.setVisible(true);
			lblPid.setText("---");
		} else {
			lblOverloading.setVisible(false);
			lblPid.setText(current);
		}
		
		repaint();
	}
}
