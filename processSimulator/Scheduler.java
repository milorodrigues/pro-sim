package processSimulator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Vector;

public class Scheduler {
	
	protected static int lastPID;
	public static int limit = 10;
	public static Process current;
	public static Integer currentpid;
	
	public static List<Process> list = new LinkedList<Process>();
	public static PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
	
	public Scheduler() {
		lastPID = 0;
		current = null;
	}
	
	public int generatePID() {
		
		return lastPID + 1;
	}
	
	public void increaseLastPID() {
		lastPID++;
	}
	
	public String addProcess(int duration, int deadline, int priority, int delay) {
		
		Process newprocess = new Process(this.generatePID(), duration, 0, priority, delay);
		
		String result = "";
		if (list.size() >= limit) result = "full";
		else {
			boolean success = list.add(newprocess);
			if (success) {
				success = queue.add(newprocess.pid);
			}
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
		
		Process newprocess = new Process(pid, duration, deadline, priority, delay);
		
		list.add(newprocess);
		queue.add(newprocess.pid); 
		
	}
	
	public Object[][] getProcessData(){
		
		Vector<Vector<Object>> dataVec = new Vector<Vector<Object>>(10, 10);
		Vector<Object> row = new Vector<Object>(5, 0);
		
		if (current != null) {
			row = new Vector<Object>(5, 0);
			row.add(current.pid);
			row.add(current.duration);
			row.add(current.timeleft); 
			if (Manager.algScheduling == "EDF") {
				row.add(current.deadline);
			} else {
				row.add("---");
			}
			row.add(current.priority);
			dataVec.add(row);
		}
		
		Iterator<Process> it = list.iterator();
		Process iteration;
		while (it.hasNext()) {			
			iteration = it.next();
			
			if (current != null && iteration.pid == current.pid) continue;
			
			row = new Vector<Object>(5, 0);
			row.add(iteration.pid);
			row.add(iteration.duration);
			row.add(iteration.timeleft);
			if (Manager.algScheduling == "EDF") {
				row.add(iteration.deadline);
			} else {
				row.add("---");
			}
			row.add(iteration.priority);
			dataVec.add(row);
		}
		
		Object[][] dataArray = Manager.utility.to2DimArray(dataVec);
		
		return dataArray;
	}
	
	public void updateCurrent() {
		if (current == null) {
			getNextProcess();
		}
		
		if (current != null) {
			if (current.timeleft <= 0) {
				endCurrentProcess();
			}
			current.timeleft--;
			Manager.swapper.pageLoop(current);
		}
	}
	
	public void getNextProcess() {
		current = null;
	}
	
	public void endCurrentProcess() {
		Manager.swapper.clearPagesFromDisk(current);
		
		int index = findProcess(current.pid);
		if (index != -1) list.remove(index);

		getNextProcess();
	}
	
	public static int findProcess(int pid) {
		
		ListIterator<Process> it;
		int it_index = 0;
		Process it_current = null;
		for (it = list.listIterator(); it.hasNext(); it_index++) {
			it_current = it.next();
			if (it_current.pid == pid) break;
		}
		
		if (it_index < list.size()) {
			return it_index;
		} else {
			return -1;
		}
		
	}
	
	public static Process getQueueHead() {
		Process next;
		
		Integer index;
		
		currentpid = queue.poll();
		if (currentpid == null) {
			next = null;
		} else {
			index = findProcess(currentpid);
			if (index == -1) {
				next = null;
			} else {
				next = list.get(index);
			}
		}
				
		return next;
	}
	
	public void updateCurrentQuantum() {
	}
	
	public void updateCurrentOverload() {
	}
	
	public void updateCurrentNext() {
	}
}
