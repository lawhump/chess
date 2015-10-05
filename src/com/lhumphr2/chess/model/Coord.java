package src.com.lhumphr2.chess.model;

/**
 * Analogous to a coordinate, (x,y) pair.
 */
public class Coord {
	private int xCoord;
	private int yCoord;


	public Coord() {
		xCoord = 0;
		yCoord = 0;
	}

	public Coord(int x, int y) {
		xCoord = x;
		yCoord = y;
	}

	public Coord(Coord c) {
		xCoord = c.xCoord;
		yCoord = c.yCoord;
	}

	public int getX() {
		return xCoord;
	}
	public int getY() {
		return yCoord;
	}

	public void setX(int x) {
		xCoord = x;
	}

	public void setY(int y) {
		yCoord = y;
	}
}