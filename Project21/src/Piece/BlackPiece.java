package Piece;

import java.awt.Image;

public class BlackPiece extends CheckersPiece{
	
	public BlackPiece() {
		//this.setVisual(databasePointer);
	}
	
	@Override
	public BlackPiece clone() {
		return new BlackPiece();
	}
	
	@Override
	public Image getVisual() {
		return super.getVisual();
	}
	
	public void isKing() {
		//this.setVisual(databasePointerKing);
	}

}

