package processSimulator;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Vector;

public class Scheduler_SJF extends Scheduler{
	
	private Comparator<Process> comparator = new DurationComparator();
	private static PriorityQueue<Process> queue;

	public Scheduler_SJF() {
		super();
		queue = new PriorityQueue<Process>(10, comparator);
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
			current = queue.poll();
			if (current != null) {
				current.timeleft--;
			}
		}
		else if (current.timeleft <= 0) {
			current = queue.poll();
			updateCurrent();
		}
		else {
			current.timeleft--;
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
	
	public class DurationComparator implements Comparator<Process>{

		@Override
		public int compare(Process A, Process B) {
			return A.duration - B.duration;
		}
		
	}

}
