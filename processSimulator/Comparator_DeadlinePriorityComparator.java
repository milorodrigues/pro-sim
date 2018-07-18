package processSimulator;

import java.util.Comparator;

public class Comparator_DeadlinePriorityComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer A, Integer B) {
		
		Integer aPriority = Scheduler.list.get(Scheduler.findProcess(A)).priority;
		Integer bPriority = Scheduler.list.get(Scheduler.findProcess(B)).priority;
		
		Integer aDeadline = Scheduler.list.get(Scheduler.findProcess(A)).deadline;
		Integer bDeadline = Scheduler.list.get(Scheduler.findProcess(B)).deadline;
		
		Integer ret;
		
		ret = bPriority - aPriority;
		if (ret == 0) {
			ret = aDeadline - bDeadline;
		}
		
		return ret;
		
	}
}
