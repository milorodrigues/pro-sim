package processSimulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

public class Page extends JPanel {

	private static final long serialVersionUID = -1561289966217256183L;
	
	protected JLabel lblNum;
	protected JLabel lblProcess;
	
	public int num;
	public int pid;

	public Page(int num) {
		setBorder(new CompoundBorder(
					BorderFactory.createLineBorder(Color.BLACK),
					BorderFactory.createEmptyBorder(2,2,2,2)
				));
		setLayout(new GridLayout(1,2));
		setBackground(Color.WHITE);
		
		pid = 0;
		this.num = num;
		
		lblNum = new JLabel(Integer.toString(num));
			lblNum.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNum.setHorizontalAlignment(JLabel.CENTER);
			add(lblNum);
			
		lblProcess = new JLabel("---");
			lblProcess.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblProcess.setHorizontalAlignment(JLabel.CENTER);
			setProcess(pid);
			add(lblProcess);
		
		this.setPreferredSize(new Dimension(15,10));
	}
	
	public void setProcess(int newpid) {
		pid = newpid;
		if (pid == 0) lblProcess.setText("---");
		else {
			lblProcess.setText(Integer.toString(pid));
		}
		
	}
}
