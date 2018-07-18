package processSimulator;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Scheduler_FIFO extends Scheduler {
	
	private Comparator<Integer> comparator = new Comparator_PriorityComparator();
	
	public Scheduler_FIFO() {
		super();
		queue = new PriorityQueue<Integer>(10, comparator);
		
		add(1, 3, 0, 0, 0);
		add(2, 18, 0, 0, 0);
		add(3, 5, 0, 4, 0);
		add(4, 16, 0, 0, 0);
		add(5, 21, 0, 2, 0);
		add(6, 11, 0, 0, 0);

	}
	
	public void getNextProcess() {
		current = getQueueHead();
		Manager.swapper.fetchPagesToDisk(current);
		if (!Manager.swapper.checkPages(current)) {
			Manager.swapper.fetchPages(current);
		}
	}

}
