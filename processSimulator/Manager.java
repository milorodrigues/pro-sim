package processSimulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.Timer;

public class Manager {
	
	public static Utility utility = new Utility();
	
	public static String algScheduling = "null";
	public static String algPageSwap = "null";
	
	public static int time = 0;
	public static DecimalFormat timeformat = new DecimalFormat("0000");
	public static String timestr = timeformat.format(time);	
	public static Timer timer;
	
	public static int quantum = 1;
	public static int overload = 1;
	
	public static Scheduler scheduler;
	public static PageSwapper swapper;
	
	public static Window_Main mainWindow;
	
	public Manager() {
		configProgram();		
	}
	
	public static void configProgram() {
		new Window_Start_Scheduling();
	}
	
	public static void startProgram() {
		
		if (algScheduling == "FIFO") {
			scheduler = new Scheduler_FIFO();
		} else if (algScheduling == "SJF") {
			scheduler = new Scheduler_SJF();
		} else {
			scheduler = new Scheduler();
		}
		
		swapper = new PageSwapper();
		
		mainWindow = new Window_Main();
		
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runLoop();
			}
		});
		timer.setInitialDelay(1000);
		timer.start();
	}
	
	public static void runLoop(){		
			
		time++; timestr = timeformat.format(time);
		scheduler.updateCurrent();			
		mainWindow.loopWindow();
	}
}
