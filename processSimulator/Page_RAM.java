package processSimulator;

public class Page_RAM extends Page {

	private static final long serialVersionUID = 4490173232602915197L;
	
	public int counter;
	
	public Page_RAM(int num) {
		super(num);
		
		counter = 0;
		
		/*
		JLabel lblage = new JLabel(Integer.toString(counter));
		lblage.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblage.setHorizontalAlignment(JLabel.CENTER);
		add(lblage);
		*/

	}
	
	public void setProcess(int newpid) {
		
		//System.out.println("setting process page " + num + " pid " + newpid);
		
		pid = newpid;
		if (pid == 0) lblProcess.setText("---");
		else {
			lblProcess.setText(Integer.toString(pid));
			counter = 256;
		}
		
	}
	
	public void age(boolean r) {
		
		if (counter == 256) {
			counter = 0;
		}
		
		counter = counter/2;
		
		if (r) counter += 128;
		
		//lblage.setText(Integer.toString(counter));
		
	}

}
