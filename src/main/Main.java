package main;

public class Main {

	public static void main(String[] args) {
		GameState gs = new GameState();
		gs.initializeBoard();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(gs.board[j][i] + " ");
			}
			System.out.println();
		}
	}

	public static class GameState {
		int[][] board = new int[8][8];

		int whitePieceCount = 39;
		int blackPieceCount = 39;
		int whiteCenterScore;
		int blackCenterScore;

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
		
		public void calculateCenterScores() {
			int[][] centerMultipliers = new int[8][8];
		}
	}
}
