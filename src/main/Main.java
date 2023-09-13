package main;

public class Main {

	public static void main(String[] args) {
		GameState gs = new GameState();
		gs.initializeBoard();
		
		gs.board[1][4] = -1;
		gs.board[4][4] = -6;
		gs.board[3][3] = 3;
		gs.board[5][7] = 0;
		gs.board[6][7] = 0;
		gs.board[3][5] = 3;
		
		
		

		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				System.out.print(gs.board[j][i] + " ");
			}
			System.out.println();
		}
		
		System.out.println(gs.giveMoves(new Coordinate(1, 4)));
		System.out.println(gs.canBlackKingSideCastle());
	}

}
