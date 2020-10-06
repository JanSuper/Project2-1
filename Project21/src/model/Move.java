package model;

public class Move {

	private int srcRow, srcCol, desRow, desCol;

	public Move(Board c, int sRow, int sCol, int eRow, int eCol) {
		srcRow = sRow;
		srcCol = sCol;
		desRow = eRow;
		desCol = eCol;
	}

	public int getSourceRow() {
		return srcRow;
	}

	public int getSourceCol() {
		return srcCol;
	}

	public int getDestinationRow() {
		return desRow;
	}

	public int getDestinationCol() {
		return desCol;
	}

	public boolean equals(Object o) {
		if (o instanceof Move) {
			Move m = (Move) o;
			return (srcRow == m.srcRow && srcCol == m.srcCol && desRow == m.desRow && desCol == m.desCol);
		} else {
			return false;
		}
	}

	public String toString() {
		return "(" + srcRow + ", " + srcCol + ") to (" + desRow + ", " + desCol + ")";
	}

	public boolean isCapture() {
		return (Math.abs(desRow - srcRow) == 2) && (Math.abs(desCol - srcCol) == 2);
	}
}
