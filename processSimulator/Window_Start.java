package processSimulator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class Window_Start extends JFrame {

	private static final long serialVersionUID = 731475443218717382L;
	
	protected JPanel contentPane;
	protected JLabel lblChoose;
	protected JPanel panelButtons;

	public Window_Start() {
		super("Process Simulator");
		this.setLocationRelativeTo(null);
		
		contentPane = new JPanel();
			contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		lblChoose = new JLabel("Choose");
		lblChoose.setFont(new Font("Verdana", Font.PLAIN, 16));
			lblChoose.setAlignmentX(CENTER_ALIGNMENT);
		
		panelButtons = new JPanel();
			panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
		
		contentPane.add(Box.createVerticalStrut(17));
		contentPane.add(lblChoose);
		contentPane.add(Box.createVerticalStrut(8));
		contentPane.add(panelButtons);
		this.setContentPane(contentPane);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(50, 50, 437, 137);
		this.setVisible(true);
	}
	
}
