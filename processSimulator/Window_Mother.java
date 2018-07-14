package processSimulator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window_Mother extends JFrame {
	
	private static final long serialVersionUID = -4077669822657877281L;
	
	protected JPanel contentPane;
	
	public Window_Mother() {
		super("Process simulator");
		
		contentPane = new JPanel();
			contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
			contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		this.setContentPane(contentPane);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

}
