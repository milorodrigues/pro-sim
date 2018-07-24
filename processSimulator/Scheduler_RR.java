package processSimulator;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Scheduler_RR extends Scheduler{

	private Comparator<Integer> comparator = new Comparator_PriorityComparator();
	
	public Scheduler_RR() {
		super();
		queue = new PriorityQueue<Integer>(10, comparator);
		
		/*
		add(1, 20, 0, 0, 0);
		add(2, 18, 0, 0, 0);
		add(3, 24, 0, 0, 0);
		add(4, 16, 0, 0, 0);
		add(5, 21, 0, 0, 0);
		add(6, 11, 0, 0, 0);
		*/
	}
	
	public void updateCurrentQuantum() {
		if (current == null) {
			Manager.startQuantum();
			current = getQueueHead();
			Manager.swapper.fetchPagesToDisk(current);
			if (current != null) {
				updateCurrentQuantum();
			}
		} else {
			Manager.swapper.fetchPages(current);			
			current.timeleft--;
			current.lastTime = Manager.time;
			System.out.println(current.pid + ".lastTime = " + current.lastTime);
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
				if (current.priority > 0) current.priority--;
				
				int index = findProcess(current.pid);
				if (index != -1) {
					list.set(index, current);
				}
				
				queue.add(current.pid);
			}
			current = getQueueHead();
			Manager.swapper.fetchPagesToDisk(current);
			Manager.swapper.fetchPages(current);
		}
	}

}
