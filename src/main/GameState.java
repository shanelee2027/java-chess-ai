package main;

import java.util.*;

public class GameState {
	int[][] board = new int[8][8];

	int whitePieceCount = 39;
	int blackPieceCount = 39;
	int whiteCenterScore;
	int blackCenterScore;
	
	static boolean whiteCanCastle = true;
	static boolean blackCanCastle = true;

	public GameState() {

	}

	public void initializeBoard() {
		setPieces();

	}

	public void setPieces() {
		// white is positive

		// empty = 0
		// pawn = 1
		// rook = 2
		// knight = 3
		// bishop = 4
		// queen = 5
		// king = 6
		for (int i = 0; i < 8; i++) {
			board[i][1] = 1;
			board[i][6] = -1;
		}
		// rooks
		board[0][0] = 2;
		board[7][0] = 2;
		board[0][7] = -2;
		board[7][7] = -2;

		// knights
		board[1][0] = 3;
		board[6][0] = 3;
		board[1][7] = -3;
		board[6][7] = -3;

		// bishops
		board[2][0] = 4;
		board[5][0] = 4;
		board[2][7] = -4;
		board[5][7] = -4;

		// queens
		board[3][0] = 5;
		board[4][7] = -5;

		// kings
		board[4][0] = 6;
		board[3][7] = -6;
	}

	public void calculatePieceCount() {

	}

	public void calculateCenterScores() {
		int[][] centerMultipliers = new int[8][8];
	}

	public ArrayList<Coordinate> giveMoves(Coordinate pos) {
		int pieceType = Math.abs(board[pos.x][pos.y]);

		switch (pieceType) {
		case 1:
			return givePawnMoves(pos);
		case 2:
			return giveRookMoves(pos);
		case 3:
			return giveKnightMoves(pos);
		case 4:
			return giveBishopMoves(pos);
		case 5:
			return giveQueenMoves(pos);
		case 6:
			return giveKingMoves(pos);
		}
		return null;
	}
	
	/*
	 * 
	 * TODO: Add Promotion
	 * 
	 */

	public ArrayList<Coordinate> givePawnMoves(Coordinate pos) {
		int x = pos.x;
		int y = pos.y;
		boolean whiteColor = board[x][y] > 0;

		ArrayList<Coordinate> moves = new ArrayList<>();

		if (whiteColor) {
			if (y == 1) {
				if (board[x][y + 1] == 0 && board[x][y + 2] == 0)
					moves.add(new Coordinate(x, y + 2));
			}
			if (board[x][y + 1] == 0)
				moves.add(new Coordinate(x, y + 1));
			if (!oob(x-1, y+1) && board[x - 1][y + 1] < 0)
				moves.add(new Coordinate(x - 1, y + 1));
			if (!oob(x+1, y+1) && board[x + 1][y + 1] < 0)
				moves.add(new Coordinate(x + 1, y + 1));
		} else {
			if (y == 6) {
				if (board[x][y - 1] == 0 && board[x][y - 2] == 0)
					moves.add(new Coordinate(x, y - 2));
			}
			if (board[x][y - 1] == 0)
				moves.add(new Coordinate(x, y - 1));
			if (!oob(x-1, y-1) && board[x - 1][y - 1] > 0)
				moves.add(new Coordinate(x - 1, y - 1));
			if (!oob(x+1, y-1) && board[x + 1][y - 1] > 0)
				moves.add(new Coordinate(x + 1, y - 1));
		}

		return moves;
	}

	public ArrayList<Coordinate> giveRookMoves(Coordinate pos) {
		int x = pos.x;
		int y = pos.y;
		boolean whiteColor = board[x][y] > 0;

		ArrayList<Coordinate> moves = new ArrayList<>();

		if (whiteColor) {
			int currentX = x;
			int currentY = y;
			// +x
			while (true) {
				currentX++;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] > 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] < 0)
					break;
			}
			currentX = x;
			currentY = y;
			// -x
			while (true) {
				currentX--;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] > 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] < 0)
					break;
			}
			currentX = x;
			currentY = y;
			// +y
			while (true) {
				currentY++;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] > 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] < 0)
					break;
			}
			currentX = x;
			currentY = y;
			// -y
			while (true) {
				currentY--;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] > 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] < 0)
					break;
			}
		} else {
			int currentX = x;
			int currentY = y;
			// +x
			while (true) {
				currentX++;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] < 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] > 0)
					break;
			}
			currentX = x;
			currentY = y;
			// -x
			while (true) {
				currentX--;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] < 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] > 0)
					break;
			}
			currentX = x;
			currentY = y;
			// +y
			while (true) {
				currentY++;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] < 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] > 0)
					break;
			}
			currentX = x;
			currentY = y;
			// -y
			while (true) {
				currentY--;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] < 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] > 0)
					break;
			}
		}

		return moves;
	}

	public ArrayList<Coordinate> giveKnightMoves(Coordinate pos) {
		int x = pos.x;
		int y = pos.y;
		boolean whiteColor = board[x][y] > 0;

		ArrayList<Coordinate> moves = new ArrayList<>();
		
		if (whiteColor) {
			for(int i = 0; i < 8; i++) {
				int newX = x + Constants.knightMoves[i][0];
				int newY = y + Constants.knightMoves[i][1];
				if(oob(newX, newY)) continue;
				if(board[newX][newY] > 0) continue;
				moves.add(new Coordinate(newX, newY));
			}
		} else {
			for(int i = 0; i < 8; i++) {
				int newX = x + Constants.knightMoves[i][0];
				int newY = y + Constants.knightMoves[i][1];
				if(oob(newX, newY)) continue;
				if(board[newX][newY] < 0) continue;
				moves.add(new Coordinate(newX, newY));
			}
		}

		return moves;
	}

	public ArrayList<Coordinate> giveBishopMoves(Coordinate pos) {
		int x = pos.x;
		int y = pos.y;
		boolean whiteColor = board[x][y] > 0;

		ArrayList<Coordinate> moves = new ArrayList<>();

		if (whiteColor) {
			int currentX = x;
			int currentY = y;
			// +x +y
			while (true) {
				currentX++;
				currentY++;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] > 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] < 0)
					break;
			}
			currentX = x;
			currentY = y;
			// -x +y
			while (true) {
				currentX--;
				currentY++;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] > 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] < 0)
					break;
			}
			currentX = x;
			currentY = y;
			// +x -y
			while (true) {
				currentX++;
				currentY--;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] > 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] < 0)
					break;
			}
			currentX = x;
			currentY = y;
			// -x -y
			while (true) {
				currentX--;
				currentY--;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] > 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] < 0)
					break;
			}
		} else {
			int currentX = x;
			int currentY = y;
			// +x +y
			while (true) {
				currentX++;
				currentY++;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] < 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] > 0)
					break;
			}
			currentX = x;
			currentY = y;
			// -x +y
			while (true) {
				currentX--;
				currentY++;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] < 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] > 0)
					break;
			}
			currentX = x;
			currentY = y;
			// +x -y
			while (true) {
				currentX++;
				currentY--;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] < 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] > 0)
					break;
			}
			currentX = x;
			currentY = y;
			// -x -y
			while (true) {
				currentX--;
				currentY--;
				if (oob(currentX, currentY))
					break;
				if (board[currentX][currentY] < 0)
					break;
				moves.add(new Coordinate(currentX, currentY));
				if (board[currentX][currentY] > 0)
					break;
			}
		}

		return moves;
	}

	public ArrayList<Coordinate> giveQueenMoves(Coordinate pos) {
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		moves.addAll(giveRookMoves(pos));
		moves.addAll(giveBishopMoves(pos));
		return moves;
	}

	public ArrayList<Coordinate> giveKingMoves(Coordinate pos) {
		int x = pos.x;
		int y = pos.y;
		boolean whiteColor = board[x][y] > 0;

		ArrayList<Coordinate> moves = new ArrayList<>();

		if (whiteColor) {

		}

		return moves;
	}
	
	// assume that the king in the current board state is not existent, and is instead replaced by the one at pos
	public boolean whiteKingInCheck(Coordinate pos) {
		int x = pos.x;
		int y = pos.y;
		
		int newX;
		int newY;
		for(int i = 0; i < 8; i++) {
			newX = x + Constants.knightMoves[i][0];
			newY = y + Constants.knightMoves[i][1];
			if(oob(newX, newY)) continue;
			if(board[newX][newY] == -3 ) return true;	
		}
		
		
		
		return false;
	}
	
	public boolean oob(int x, int y) {
		if(x > 7 || x < 0 || y > 7 || y < 0) return true;
		return false;
	}

}
