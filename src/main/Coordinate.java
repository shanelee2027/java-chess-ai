package main;

public class Coordinate {
	int x;
	int y;
	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
