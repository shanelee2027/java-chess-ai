package main;

public class Main {

	public static void main(String[] args) {
		GameState gs = new GameState();
		gs.initializeBoard();
		
		gs.board[1][4] = 3;

		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				System.out.print(gs.board[j][i] + " ");
			}
			System.out.println();
		}
		
		System.out.println(gs.giveMoves(new Coordinate(1, 4)));
	}

}
