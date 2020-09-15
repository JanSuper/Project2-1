package Test;

import Piece.*;

public class testPieces {
	
	public static void main(String[] args) {
		Piece testpiece = new BlackPiece();
		Piece testpiece2 = new WhitePiece();
		
		System.out.println(testpiece.getVisual());
		System.out.println(testpiece2.getVisual());
	}

}
