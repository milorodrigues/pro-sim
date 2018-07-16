package processSimulator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class Scheduler_FIFO extends Scheduler {
	
	private static Queue<Process> queue;
	
	public Scheduler_FIFO() {
		super();
		queue = new LinkedList<Process>();
	}
	
	public boolean addProcess(int duration, int deadline, int priority, int delay) {
		
		Process newprocess = new Process(this.generatePID(), duration, 0, 0, delay);
		
		boolean success = queue.add(newprocess); 
		if (success) {
			this.increaseLastPID();
		}
		
		Manager.mainWindow.processTableRefreshData();
		
		return success;
	}
	
	public void updateCurrentQuantum() {
		if (current == null) {
			//System.out.println("current = null");
			current = queue.poll();
			if (current != null) {
				current.timeleft--;
				//System.out.println("current = " + current.duration);
			}
		}
		else if (current.timeleft <= 0) {
			current = queue.poll();
			//System.out.println("current ended");
			updateCurrent();
		}
		else {
			current.timeleft--;
			//System.out.println("current = " + current.duration);
		}
	}
	
	public Object[][] getProcessData(){
			
		Vector<Vector<Object>> dataVec = new Vector<Vector<Object>>(10, 10);
		Vector<Object> row = new Vector<Object>(5, 0);
		
		if (current != null) {
			row = new Vector<Object>(5, 0);
			row.add(current.pid); row.add(current.duration); row.add(current.timeleft); row.add(current.deadline); row.add(current.priority);
			dataVec.add(row);
		}
		
		Iterator<Process> it = queue.iterator();
		Process iteration;
		while (it.hasNext()) {			
			iteration = it.next();
			row = new Vector<Object>(5, 0);
			row.add(iteration.pid); row.add(iteration.duration); row.add(iteration.timeleft); row.add(iteration.deadline); row.add(iteration.priority);
			dataVec.add(row);
		}
		
		Object[][] dataArray = Manager.utility.to2DimArray(dataVec);
		
		return dataArray;
	}

}
