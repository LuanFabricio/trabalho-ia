package aima.ia.search.util;

import java.util.ArrayList;
import java.util.List;

public class QueueFrontier{
	private List<Node> values;
	
	public QueueFrontier(){
		this.values = new ArrayList<>();
	}
	
	public void add(Node n) {
		values.add(n);
	}
	
	public Node pop() {
		int lastItem = values.size()-1;
		Node n = values.remove(lastItem);
		return n;
	}
	
	public Boolean contains(int[][] state) {
		for(int k = 0 ; k < values.size() ; k++) {
			Boolean equal = true;
			for(int i = 0 ; i < 8 ; i++) {
				for(int j = 0 ; j < 8 ; j++) {
					int[][] currentState = values.get(k).state;
					if(state[i][j] != currentState[i][j]) {
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
	
	public Node nodeContains(int[][] state) {
		for(int k = 0 ; k < values.size() ; k++) {
			Boolean equal = true;
			for(int i = 0 ; i < 8 ; i++) {
				for(int j = 0 ; j < 8 ; j++) {
					int[][] currentState = values.get(k).state;
					if(state[i][j] != currentState[i][j]) {
						equal = false;
						break;
					}
				}
			}
			if(equal) {
				return values.get(k);
			}
		}
		return null;	
	}
}
