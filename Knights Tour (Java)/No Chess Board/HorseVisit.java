import java.util.*;

public class HorseVisit {
	public static final int SIZE = 5;
	
	public class Tile {
		boolean visited;
		int x;
		int y;
		
		Tile() {
			x = 0;
			y = 0;
			visited = false;
		}
		
		Tile(int x, int y, boolean visited) {
			this.x = x;
			this.y = y;
			this.visited = visited;
		}
	}
	
	HorseVisit(int initialX, int initialY) {
		
		Stack<Tile> path = new Stack<Tile>();
		Tile start = new Tile(initialX, initialY, false);
		path.push(start);
		start.visited = true;
		if(horsePath(start, path)) {
			Queue<Tile> q = new LinkedList<Tile>();
			System.out.println(path.size());
			while(!path.isEmpty()) {
				q.add(path.pop());
			}
			
			while(!q.isEmpty()) {
				path.push(q.remove());
			}
			
			while(!path.isEmpty()) {
				Tile t = path.pop();
				System.out.print("(" + t.x + ", " + t.y + ") ->");
			}
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
				chessBoard[availableMoves.get(i).x][availableMoves.get(i).y].visited = true;
				if(horsePath(availableMoves.get(i), path)) {
					return true;
				}
				path.pop();
				chessBoard[availableMoves.get(i).x][availableMoves.get(i).y].visited = false;
			}
		}
		
		return false;
	}
	
	public ArrayList<Tile> possibleMoves(Tile t, Stack<Tile> path) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		
		if(t.x + 1 < SIZE && t.y + 2 < SIZE) {
			tiles.add(new Tile(t.x + 1, t.y + 2, false));
		}
		
		if(t.x + 1 < SIZE && t.y - 2 >= 0) {
			tiles.add(new Tile(t.x + 1, t.y - 2, false));
		}
		
		if(t.x + 2 < SIZE && t.y + 1 < SIZE) {
			tiles.add(new Tile(t.x + 2, t.y + 1, false));
		}
		
		if(t.x + 2 < SIZE && t.y - 1 >= 0) {
			tiles.add(new Tile(t.x + 2, t.y - 1, false));
		}
		
		if(t.x - 1 >= 0 && t.y + 2 < SIZE) {
			tiles.add(new Tile(t.x - 1, t.y + 2, false));
		}
		
		if(t.x - 1 >= 0 && t.y - 2 >= 0) {
			tiles.add(new Tile(t.x - 1, t.y - 2, false));
		}
		
		if(t.x - 2 >= 0 && t.y + 1 < SIZE) {
			tiles.add(new Tile(t.x - 2, t.y + 1, false));
		}
		
		if(t.x - 2 >= 0 && t.y - 1 >= 0) {
			tiles.add(new Tile(t.x - 2, t.y - 1, false));
		}
		
		for(int i = tiles.size()-1;i>=0; i--) {
			for(int j = 0; j < path.size(); j++) {
				if(tiles.get(i).x == path.get(j).x && tiles.get(i).y == path.get(j).y) {
					tiles.remove(i);
					break;
				}
			}
		}
		return tiles;
	}
}

