package aima.ia.search.util;

public class Node{
	public int[][] state = null;
	public int[] action = null;
	public Node parent = null;
	public int cost;
	
	public Node(int[][] state, Node parent, int[] action){
		this(state, parent, action, 1);
	}

	public Node(int[][] state, Node parent, int[] action, int cost){
		this.state = state;
		this.parent = parent;
		this.action = action;
		this.cost = cost;
	}
}