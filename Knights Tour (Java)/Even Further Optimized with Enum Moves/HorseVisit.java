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
	
	public enum Moves {
		move1(1, 2), move2(1, -2), move3(2, 1), move4(-2, 1),
		move5(-1, 2), move6(-1, -2), move7(2, -1), move8(-2, -1);
		
		private int col;
		private int row;
		
		Moves(int col, int row) {
			this.col = col;
			this.row = row;
		}
		
		private int getCol() {
			return col;
		}
		
		private int getRow() {
			return row;
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
		
		for(Moves m : Moves.values()) {
			int colMove = t.col + m.getCol();
			int rowMove = t.row + m.getRow();
			
			if(isPossible(colMove, rowMove)) {
				tiles.add(chessBoard[colMove][rowMove]);
			}
		}		
	
		for(int i = 0; i < tiles.size(); i++) {
			if(chessBoard[tiles.get(i).col][tiles.get(i).row].visited) {
				tiles.remove(i);
				i--;
			}
		}
		return tiles;
	}
	
	public boolean isPossible(int col, int row) {
		return col >= 0 && col < SIZE && row >= 0 && row < SIZE;
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

