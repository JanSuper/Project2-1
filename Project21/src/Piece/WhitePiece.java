package Piece;

import java.awt.Image;

public class WhitePiece extends CheckersPiece{
	
	public WhitePiece() {
		//this.setVisual(databasePointer);
	}
	
	@Override
	public WhitePiece clone() {
		return new WhitePiece();
	}
	
	@Override
	public Image getVisual() {
		return super.getVisual();
	}
	
	public void isKing() {
		//this.setVisual(databasePointerKing);
	}

}
