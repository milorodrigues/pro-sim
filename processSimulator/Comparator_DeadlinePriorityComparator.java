package processSimulator;

import java.util.Comparator;

public class Comparator_DeadlinePriorityComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer A, Integer B) {
		
		Integer aPriority = Scheduler.list.get(Scheduler.findProcess(A)).priority;
		Integer bPriority = Scheduler.list.get(Scheduler.findProcess(B)).priority;
		
		Integer aDeadline = Scheduler.list.get(Scheduler.findProcess(A)).deadline;
		Integer bDeadline = Scheduler.list.get(Scheduler.findProcess(B)).deadline;
		
		Integer aLast = Scheduler.list.get(Scheduler.findProcess(A)).lastTime;
		Integer bLast = Scheduler.list.get(Scheduler.findProcess(B)).lastTime;
		
		Integer ret;
		
		ret = bPriority - aPriority;
		if (ret == 0) {
			ret = aDeadline - bDeadline;
		}
		if (ret == 0) {
			ret = aLast - bLast;
		}
		
		return ret;
		
	}
}
