package processSimulator;

import java.util.Comparator;

public class Comparator_PriorityComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer A, Integer B) {
		
		Integer aPriority = Scheduler.list.get(Scheduler.findProcess(A)).priority;
		Integer bPriority = Scheduler.list.get(Scheduler.findProcess(B)).priority;
		
		Integer aLast = Scheduler.list.get(Scheduler.findProcess(A)).lastTime;
		Integer bLast = Scheduler.list.get(Scheduler.findProcess(B)).lastTime;
		
		Integer ret;
		ret = aPriority - bPriority;
		if (ret == 0) {
			ret = aLast - bLast;
		}
		if (ret == 0) {
			ret = A-B;
		}
		
		return ret;
	}

}
