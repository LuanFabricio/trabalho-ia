package aima.ia.search.util;

import java.util.ArrayList;
import java.util.List;

public class SetExplored{
	private List<int[][]> values;
	
	public SetExplored(){
		values = new ArrayList<>();		
	}
	
	public void add(int m[][]) {
		values.add(m);
	}
	
	public Boolean contains(int m[][]) {
		for(int k = 0 ; k < values.size() ; k++) {
			int[][] currentState = values.get(k);
			Boolean equal = true;
			for(int i = 0 ; i < 8 ; i++) {
				for(int j = 0 ; j < 8 ; j++) {
					if(m[i][j] != currentState[i][j]) {
						equal = false;
						break;
					}
				}
			}
			if(equal) {
				return true; 
			}
		}
		return false;
	}
}

