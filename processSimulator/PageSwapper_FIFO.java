package processSimulator;

import java.util.LinkedList;
import java.util.Queue;

public class PageSwapper_FIFO extends PageSwapper {
	
	private static Queue<Integer> queue;
	private int victim;

	public PageSwapper_FIFO() {
		super();
		queue = new LinkedList<Integer>();
	}

	public void fetchPages(Process process) {
		
		if (process == null) return;
		
		if (checkPages(process)) return;
		
		int counter = Process.numPages;
		
		for (int i=0;i<50;i++) {
			if (pagesRAM.elementAt(i).pid == 0) {
				pagesRAM.elementAt(i).setProcess(process.pid);
				counter--;
				queue.add(i);
				if (counter == 0) break;
			}
		}
		
		if (counter == 0) return;
		
		else {
			while (counter > 0) {
				victim = queue.poll();
				pagesRAM.elementAt(victim).setProcess(process.pid);
				counter--;
				queue.add(victim);
			}
		}
		
	}
	
}
