package processSimulator;

import java.util.Comparator;

public class Comparator_DurationPriorityComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer A, Integer B) {
		
		Integer aPriority = Scheduler.list.get(Scheduler.findProcess(A)).priority;
		Integer bPriority = Scheduler.list.get(Scheduler.findProcess(B)).priority;
		
		Integer aDuration = Scheduler.list.get(Scheduler.findProcess(A)).duration;
		Integer bDuration = Scheduler.list.get(Scheduler.findProcess(B)).duration;
		
		Integer ret;
		
		ret = bPriority - aPriority;
		if (ret == 0) {
			ret = aDuration - bDuration;
		}
		
		return ret;
		
	}
}
