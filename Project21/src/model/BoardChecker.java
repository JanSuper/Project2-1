package model;

import java.util.Set;

public interface BoardChecker {
	
	void reset();
	boolean gameOver();
	Board copyGame();
	
	int minRow();
    int maxRow();
    int minCol();
    int maxCol();
    int numRows();
    int numCols();
	
	int getOpponent(int player);
	void validPlayer(int player);
	int getCurrentPlayer(int player);
	boolean isTurnOf(int player);
	boolean isWinner(int player);
	
	boolean legal(int row, int col);
	boolean isBlackSquare(int row, int col); // pieces are only at black square
	boolean isWhiteSquare(int row, int col);
	boolean blackPieceAt(int row, int col);
	boolean whitePieceAt(int row, int col);
	boolean pieceAt(int row, int col, int piece);
	boolean noPieceAt(int row, int col);
	boolean kingAt(int row, int col);
	
	int numPiecesOf(int color);
	int numKingsOf(int color);
	boolean toBeKing(int row, int col); // one move to be king
	void makeKing(int row, int col);
	
	int getIndex(int row, int col); // 64 indices
	int getRow(int index); // [0...7]
	int getCol(int index); // [0...7]
	
	Set<Move> getLegalMoves(int player);
	
}
