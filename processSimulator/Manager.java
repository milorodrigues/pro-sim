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
	
	public static boolean running;
	public static int quantum = 0; public static int roundQuantum; public static boolean inQuantum = true;
	public static int overload = 0; public static int roundOverload;
	
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
		
		running = true;
		
		if (algScheduling == "FIFO") {
			scheduler = new Scheduler_FIFO();
		} else if (algScheduling == "SJF") {
			scheduler = new Scheduler_SJF();
		} else if (algScheduling == "RR") {
			scheduler = new Scheduler_RR();
			roundQuantum = quantum; inQuantum = true;
			roundOverload = overload;
		} else if (algScheduling == "EDF") {
			scheduler = new Scheduler_EDF();
			roundQuantum = quantum; inQuantum = true;
			roundOverload = overload;
		} else {
			scheduler = new Scheduler();
		}
		
		if (algPageSwap == "FIFO") {
			swapper = new PageSwapper_FIFO();
		} else if (algPageSwap == "LRU") {
			swapper = new PageSwapper_LRU();
		} else {
			swapper = new PageSwapper();
		}
		
		mainWindow = new Window_Main();
		
		if (algScheduling == "RR" || algScheduling == "EDF") {
			timer = new Timer(1000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					runLoopPreemptive();
				}
			});
		} else {
			timer = new Timer(1000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					runLoop();
				}
			});
		}
		timer.setInitialDelay(1000);
		timer.start();
	}
	
	public static void runLoop(){		
			
		time++; timestr = timeformat.format(time);
		scheduler.updateCurrent();			
		mainWindow.loopWindow();
	}
	
	public static void runLoopPreemptive() {
		
		time++; timestr = timeformat.format(time);
				
		if (inQuantum) {
			scheduler.updateCurrentQuantum();
			roundQuantum--;
			if (roundQuantum <= 0) {
				startOverload();
			}
		} else {
			scheduler.updateCurrentOverload();
			roundOverload--;
			if (roundOverload <= 0) {
				startQuantum();
			}
		}
		
		mainWindow.loopWindow();
	}
	
	public static void startQuantum() {
		roundQuantum = quantum;
		inQuantum = true;
	}
	
	public static void startOverload() {
		roundOverload = overload;
		inQuantum = false;
	}
	
	public static void timerSwitch() {
		if (running) {
			timer.stop();
			running = false;
		} else {
			timer.start();
			running = true;
		}
		
		mainWindow.pauseSwitch();
	}
}
