package processSimulator;

import java.util.Vector;

public class PageSwapper {
	
	public Vector<Page> pagesDisk;
	public Vector<Page_RAM> pagesRAM;

	public PageSwapper() {
		
		pagesDisk = new Vector<Page>(100, 0);
		for (int i=0; i<100; i++) {
			pagesDisk.add(new Page(i));
		}
		
		pagesRAM = new Vector<Page_RAM>(50, 0);
		for (int i=0;i<50;i++) {
			pagesRAM.add(new Page_RAM(i));
		}
	}
	
	public boolean checkPages(Process process) {
		
		if (process == null) return true;
		
		int counter = 0;
		
		for (int i=0;i<50;i++) {
			if (Manager.swapper.pagesRAM.elementAt(i).pid == process.pid) {
				counter++;
				if (counter == Process.numPages) break;
			}
		}
		
		if (counter == Process.numPages) return true;
		else return false;
	}
	
	public void fetchPages(Process process) {
		
	}
	
	public void pageLoop (Process process) {
			
	}
	
	public void fetchPagesToDisk(Process process) {
		
		if (process == null) return;
		
		if (checkPagesOnDisk(process)) return;
		
		int counter = 0;
		
		for (int i=0;i<100;i++) {
			if (Manager.swapper.pagesDisk.elementAt(i).pid == process.pid) {
				counter++;
				if (counter == Process.numPages) break;
			}
		}
		
		if (counter == Process.numPages) return;
		
		for (int i=0;i<100;i++) {
			if(Manager.swapper.pagesDisk.elementAt(i).pid == 0) {
				Manager.swapper.pagesDisk.elementAt(i).setProcess(process.pid);
				counter++;
				if (counter == Process.numPages) break;
			}
		}
		
		if (counter == Process.numPages) return;
		else fetchPagesToDisk(process);
		
	}
	
	public boolean checkPagesOnDisk(Process process) {
		
		int counter = 0;
		
		for (int i=0;i<100;i++) {
			if (Manager.swapper.pagesDisk.elementAt(i).pid == process.pid) {
				counter++;
				if (counter == Process.numPages) break;
			}
		}
		
		if (counter == Process.numPages) return true;
		else return false;
	}
	
	public void clearPagesFromDisk(Process process) {
		
		if (process == null) return;
		
		for (int i=0;i<100;i++) {
			if(Manager.swapper.pagesDisk.elementAt(i).pid == process.pid) {
				Manager.swapper.pagesDisk.elementAt(i).setProcess(0);
			}
		}
		
	}
}
