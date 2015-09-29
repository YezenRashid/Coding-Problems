import java.util.*;

public class HorseVisit {
	public static final int SIZE = 5;
	private Tile[][] chessBoard = new Tile[SIZE][SIZE];
	
	public class Tile {
		boolean visited;
		int col;
		int row;
		
		Tile() {
			col = 0;
			row = 0;
			visited = false;
		}
		
		Tile(int col, int row, boolean visited) {
			this.col = col;
			this.row = row;
			this.visited = visited;
		}
	}
	
	HorseVisit(int initialCol, int initialRow) {
		//Intialize chessBoard
		for(int row = 0; row < chessBoard.length; row++) {
			for(int col = 0; col < chessBoard[0].length; col++) {
				chessBoard[col][row] = new Tile(col, row, false);
			}
		}
		
		Stack<Tile> path = new Stack<Tile>();
		chessBoard[initialCol][initialRow].visited = true;
		path.push(chessBoard[initialCol][initialRow]);
		
		if(horsePath(chessBoard[initialCol][initialRow], path)) {
			System.out.println(path.size());
			printPath(path);
		} else {
			System.out.println("Could not find a possible path");
		}
	}
	
	public boolean horsePath(Tile t, Stack<Tile> path) {
		if(path.size() >= SIZE*SIZE) {
			return true;
		} else {
			ArrayList<Tile> availableMoves = possibleMoves(t, path);
			if(availableMoves.isEmpty()) {
				return false;
			}
			for(int i = 0; i < availableMoves.size(); i++) {
				path.push(availableMoves.get(i));
				chessBoard[availableMoves.get(i).col][availableMoves.get(i).row].visited = true;
				if(horsePath(availableMoves.get(i), path)) {
					return true;
				}
				path.pop();
				chessBoard[availableMoves.get(i).col][availableMoves.get(i).row].visited = false;
			}
		}
		
		return false;
	}
	
	public ArrayList<Tile> possibleMoves(Tile t, Stack<Tile> path) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		
		if(t.col + 1 < SIZE && t.row + 2 < SIZE) {
			tiles.add(chessBoard[t.col + 1][t.row + 2]);
		}
		
		if(t.col + 1 < SIZE && t.row - 2 >= 0) {
			tiles.add(chessBoard[t.col + 1][t.row - 2]);
		}
		
		if(t.col + 2 < SIZE && t.row + 1 < SIZE) {
			tiles.add(chessBoard[t.col + 2][t.row + 1]);
		}
		
		if(t.col + 2 < SIZE && t.row - 1 >= 0) {
			tiles.add(chessBoard[t.col + 2][t.row - 1]);
		}
		
		if(t.col - 1 >= 0 && t.row + 2 < SIZE) {
			tiles.add(chessBoard[t.col - 1][t.row + 2]);
		}
		
		if(t.col - 1 >= 0 && t.row - 2 >= 0) {
			tiles.add(chessBoard[t.col - 1][t.row - 2]);
		}
		
		if(t.col - 2 >= 0 && t.row + 1 < SIZE) {
			tiles.add(chessBoard[t.col - 2][t.row + 1]);
		}
		
		if(t.col - 2 >= 0 && t.row - 1 >= 0) {
			tiles.add(chessBoard[t.col - 2][t.row - 1]);
		}
		
	
		for(int i = 0; i < tiles.size(); i++) {
			if(chessBoard[tiles.get(i).col][tiles.get(i).row].visited) {
				tiles.remove(i);
				i--;
			}
		}
		return tiles;
	}
	
	public void printPath(Stack<Tile> path) {
		Queue<Tile> q = new LinkedList<Tile>();
		
		while(!path.isEmpty()) {
			q.add(path.pop());
		}
		
		while(!q.isEmpty()) {
			path.push(q.remove());
		}
		
		while(!path.isEmpty()) {
			Tile t = path.pop();
			System.out.print("(" + t.col + ", " + t.row + ") ->");
		}
	}
}

