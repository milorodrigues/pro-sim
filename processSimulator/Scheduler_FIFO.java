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
	
	public void updateCurrent() {
		if (current == null) {
			//System.out.println("current = null");
			current = queue.peek();
			if (current != null) {
				current.duration--;
				//System.out.println("current = " + current.duration);
			}
		}
		else if (current.duration <= 0) {
			queue.poll();
			current = queue.peek();
			//System.out.println("current ended");
			updateCurrent();
		}
		else {
			current.duration--;
			//System.out.println("current = " + current.duration);
		}
	}
	
	public Object[][] getProcessData(){
			
		Vector<Vector<Object>> dataVec = new Vector<Vector<Object>>(10, 10);
		Vector<Object> row = new Vector<Object>(4, 0);
		
		Iterator<Process> it = queue.iterator();
		Process iteration;
		while (it.hasNext()) {			
			iteration = it.next();
			row = new Vector<Object>(4, 0);
			//System.out.println("pid = " + iteration.pid + " duration = " + iteration.duration + " deadline = " + iteration.deadline + " priority = " + iteration.priority);
			row.add(iteration.pid); row.add(iteration.duration); row.add(iteration.deadline); row.add(iteration.priority);
			dataVec.add(row);
		}
		
		Object[][] dataArray = Manager.utility.to2DimArray(dataVec);
		
		return dataArray;
	}

}
