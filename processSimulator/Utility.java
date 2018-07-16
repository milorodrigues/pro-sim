package processSimulator;

import java.util.Vector;

public class Utility {

	public Utility() {
	}
	
	public Object[][] to2DimArray (Vector<Vector<Object>> input){
		
		Object[][] out = new Object[0][0];
		
		if (input.isEmpty()) {
			return out;
		}
				
		out = new Object[input.size()][input.elementAt(0).size()];
		
		for (int i=0; i<input.size(); i++) {
			for (int j=0; j<input.elementAt(0).size(); j++) {
				out[i][j] = input.elementAt(i).elementAt(j);
			}
		}
		
        return out;
	}
	
	public void print2DimArray(Object[][] array) {
		for(int i = 0; i<array.length; i++) {
			System.out.print("i = " + i + " ");
			for (int j = 0;j<array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	public void printVector(Vector<Object> vec) {
		for (int i = 0; i<vec.size(); i++) {
			System.out.print(vec.elementAt(i) + " ");
		}
		System.out.println("");
	}

}
