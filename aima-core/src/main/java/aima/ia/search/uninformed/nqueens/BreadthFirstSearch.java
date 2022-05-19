package aima.ia.search.uninformed.nqueens;

import java.util.List;
import java.util.ArrayList;
import aima.ia.search.util.*;

public class BreadthFirstSearch {
	int[][] board = new int[8][8];
	
	BreadthFirstSearch(int[][] board){
		this.board = board;
	}
	
	private List<int[]> getActions(Node node) {
		List<int[]> actions = new ArrayList<>();
		int[][] state = node.state;
		
		for(int i = 0 ; i < 8 ; i++) {
			for(int j = 0 ; j < 8 ; j++) {
				if(state[i][j] == 0) {
					int[] coords = { i, j };
					actions.add(coords);
				}
			}
		}
		
		return actions;
	}
	
	private int[][] setQueen(int[][] oldState, int[] action){
		int[][] state = new int[8][8];
		int x, y, i, j;
		for(i = 0 ; i < 8 ; i++) {
			for(j = 0 ; j < 8 ; j++) {
				state[i][j] = oldState[i][j];
			}
		}
		x = action[0];
		y = action[1];
		state[x][y] = 8;
		i = 1; j = 1;
		while(x+i < 8 || x-i >= 0) {
			if(x+i < 8) {
				state[x+i][y] = 1;
			}
			if(x-i >= 0) {
				state[x-i][y] = 1;
			}
			i++;
		}
		while(y+j < 8 || y-j >= 0) {
			if(y+j < 8) {
				state[x][y+j] = 1;
			}
			if(y-j >= 0) {
				state[x][y-j] = 1;
			}
			j++;
		}
		i = 1; j = 1;
		while(x+i < 8 || x-i >= 0 || y+j < 8 || y-j >= 0) {
			if(x+i < 8 && y+j < 8) {
				state[x+i][y+j] = 1;
			}
			if(x-i >= 0 && y-j >= 0) {
				state[x-i][y-j] = 1;
			}
			if(x+i < 8 && y-j >= 0) {
				state[x+i][y-j] = 1;
			}
			if(x-i >= 0 && y+j < 8) {
				state[x-i][y+j] = 1;
			}
			j++;
			i++;
		}
		return state;
	}
	
	public Boolean checkGoal(int[][] state) {
		int queens = 0;
		for(int i = 0 ; i < 8 ; i++) {
			for(int j = 0 ; j < 8 ; j++) {
				if(state[i][j] == 8) {
					queens++;
				}
			}
		}
		return queens == 8;
	}
	
	public void solve() { 
		Node n = new Node(this.board, null, null);
		QueueFrontier frontier = new QueueFrontier();
		SetExplored explored = new SetExplored();
		List<int[]> actions = getActions(n);
		explored.add(n.state);
		for(int i = 0 ; i < actions.size() ; i++) {
			int[][] newState = setQueen(n.state, actions.get(i));
			Node child = new Node(newState, n, actions.get(i));
			frontier.add(child);
		}
		while(true){
			n = frontier.pop();
			if(checkGoal(n.state)) {
				for(int i = 0 ; i < 8 ; i++) {
					for(int j = 0 ; j < 8 ; j++) {
						System.out.printf("%d ", n.state[i][j]);
					}
					System.out.print("\n");
				}
				System.out.println("Done!");
				return;
			}
			explored.add(n.state);
			actions = getActions(n);
			for(int i = 0 ; i < actions.size() ; i++) {
				int[] currentAction = actions.get(i);
				int[][] newState = setQueen(n.state, currentAction);
				if(!frontier.contains(newState) && !explored.contains(newState)) {
					Node child = new Node(newState, n, currentAction);
					frontier.add(child);
				}
			}
		}
	}
}

//class Main{
//	public static void main(String[] args) {
//		int[][] board = {
//				{ 0, 0, 0, 0, 0, 0, 0, 0 },
//				{ 0, 0, 0, 0, 0, 0, 0, 0 },
//				{ 0, 0, 0, 0, 0, 0, 0, 0 },
//				{ 0, 0, 0, 0, 0, 0, 0, 0 },
//				{ 0, 0, 0, 0, 0, 0, 0, 0 },
//				{ 0, 0, 0, 0, 0, 0, 0, 0 },
//				{ 0, 0, 0, 0, 0, 0, 0, 0 },
//				{ 0, 0, 0, 0, 0, 0, 0, 0 }
//		}; 
//		BreadthFirstSearch p = new BreadthFirstSearch(board);
//		p.solve();
//	}
//}
