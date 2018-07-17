package processSimulator;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Vector;

public class Scheduler_EDF extends Scheduler{
	
	private Comparator<Process> comparator = new DeadlineComparator();
	private static PriorityQueue<Process> queue;

	public Scheduler_EDF() {
		super();
		queue = new PriorityQueue<Process>(10, comparator);
		

		add(1, 20, 40, 0, 0);
		add(2, 18, 36, 0, 0);
		add(3, 24, 48, 0, 0);
		add(4, 16, 32, 0, 0);
		add(5, 21, 42, 0, 0);
		add(6, 11, 22, 0, 0);

	}
	
	public String addProcess(int duration, int deadline, int priority, int delay) {
		
		Process newprocess = new Process(this.generatePID(), duration, (Manager.time + deadline), 0, delay);
		
		String result = "";
		if (queue.size() >= limit) result = "full";
		else {
			boolean success = queue.add(newprocess);
			if (success) {
				this.increaseLastPID();
				result = "ok";
			} else {
				result = "fail";
			}
			
			Manager.mainWindow.processTableRefreshData();
		}
		
		return result;
	}
	
	public void add(int pid, int duration, int deadline, int priority, int delay) {
		
		Process newprocess = new Process(pid, duration, (Manager.time + deadline), 0, delay);
		
		queue.add(newprocess); 
		
	}
	
	public void updateCurrentQuantum() {
		if (current == null) {
			Manager.startQuantum();
			current = queue.poll();
			Manager.swapper.fetchPagesToDisk(current);
			if (current != null) {
				updateCurrentQuantum();			
			}
		} else {
			Manager.swapper.fetchPages(current);
			
			current.timeleft--;
			Manager.swapper.pageLoop(current);
			
			if (current.timeleft <= 0) {
				endCurrentProcess();
				Manager.startOverload();
			}
		}
	}
	
	public void updateCurrentOverload() {
		if (Manager.overload == Manager.roundOverload) {
			if (current != null) {
				queue.add(current);
			}
			current = queue.poll();
			Manager.swapper.fetchPagesToDisk(current);
			Manager.swapper.fetchPages(current);
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

	public class DeadlineComparator implements Comparator<Process>{

		@Override
		public int compare(Process A, Process B) {
			return A.deadline - B.deadline;
		}
		
	}
}
