package processSimulator;

import java.util.Comparator;

public class Comparator_PriorityComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer A, Integer B) {
		
		Integer aPriority = Scheduler.list.get(Scheduler.findProcess(A)).priority;
		Integer bPriority = Scheduler.list.get(Scheduler.findProcess(B)).priority;
		
		if (aPriority == bPriority) {
			return A - B;
		}
		
		return Scheduler.list.get(Scheduler.findProcess(B)).priority - Scheduler.list.get(Scheduler.findProcess(A)).priority; 
	}

}
