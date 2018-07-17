package processSimulator;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PageSwapper_LRU extends PageSwapper {
	
	private Comparator<Integer> comparator = new AgeComparator();
	private static PriorityQueue<Integer> queue;
	private int victim;

	public PageSwapper_LRU() {
		super();
		queue = new PriorityQueue<Integer>(50, comparator);
	}
	
	public void fetchPages(Process process) {
		
		if (process == null) return;
		
		if (checkPages(process)) return;
		
		//System.out.println("fetching pages for " + process.pid);
		
		int counter = 0;
		
		for (int i=0;i<50;i++) {
			
			if (pagesRAM.elementAt(i).pid == 0) {
				pagesRAM.elementAt(i).setProcess(process.pid);
				counter++;
				queue.add(i);
			} else if (pagesRAM.elementAt(i).pid == process.pid) {
				pagesRAM.elementAt(i).setProcess(process.pid);
				counter++;
			}
			
			if (counter == Process.numPages) break;
		}
		
		if (counter == Process.numPages) return;
		
		//System.out.println("going to while");
		
		while (counter < Process.numPages) {
			victim = queue.poll();
			pagesRAM.elementAt(victim).setProcess(process.pid);
			counter++;
			queue.add(victim);
		}		
	}
	
	public void pageLoop(Process process) {
		agePages(process);
	}
	
	public void agePages(Process process) {
		
		if (process == null) return;
		
		for (int i = 0; i<50;i++) {
			if (pagesRAM.elementAt(i).pid == process.pid) {
				pagesRAM.elementAt(i).age(true);
			} else if (pagesRAM.elementAt(i).pid != 0){
				pagesRAM.elementAt(i).age(false);
			}
		}
	}
	
	public class AgeComparator implements Comparator<Integer>{

		@Override
		public int compare(Integer A, Integer B) {
			
			if (pagesRAM.elementAt(A).counter == pagesRAM.elementAt(B).counter) {
				return A - B;
			}
			
			return pagesRAM.elementAt(A).counter - pagesRAM.elementAt(B).counter;
		}
		
	}
	
}
