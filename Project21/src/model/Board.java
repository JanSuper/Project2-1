package model;

import java.util.Set;

public class Board implements BoardChecker {
	
	public final static int BLACK = 1;
    public final static int WHITE = 2;
    
    private final int empty = 0;
    private final int pieceMask = 3;
    private final int addKing = 3;
    private final int numSquares = 32;
    private final int numBlacks = 12;
    private int board[]; // retrieve by index [0...31]
    
    private int currentPlayer;

    public Board() {
        board = new int[numSquares];
        reset();
    }
    
	@Override
	public void reset() {
		// black pieces are at first 3 rows
		for (int i = 0; i < numBlacks; ++i) {
            board[i] = BLACK;
        }
		// no pieces are at 2 middle rows
        int whiteStart = numSquares - numBlacks;
        for (int i = numBlacks; i < whiteStart; ++i) {
            board[i] = empty;
        }
        // white pieces are at last 3 rows
        for (int i = whiteStart; i < numSquares; ++i) {
            board[i] = WHITE;
        }
        currentPlayer = BLACK;
	}

	@Override
	public boolean gameOver() {
		return isWinner(WHITE) || isWinner(BLACK);
	}

	@Override
	public Board copyGame() {
		return null;
	}

	@Override
	public int minRow() {
		return 0;
	}

	@Override
	public int maxRow() {
		return 7;
	}

	@Override
	public int minCol() {
		return 0;
	}

	@Override
	public int maxCol() {
		return 7;
	}

	@Override
	public int numRows() {
		return 8;
	}

	@Override
	public int numCols() {
		return 8;
	}

	@Override
	public int getOpponent(int player) {
		validPlayer(player);
        return (player == WHITE) ? BLACK : WHITE;
	}

	@Override
	public void validPlayer(int player) {
		if (player != WHITE && player != BLACK) {
            throw new IllegalArgumentException("A player must be WHITE or BLACK");
        }
	}

	@Override
	public int getCurrentPlayer(int player) {
		return currentPlayer;
	}

	@Override
	public boolean isTurnOf(int player) {
		validPlayer(player);
        return (currentPlayer == player);
	}

	@Override
	public boolean isWinner(int player) {
		validPlayer(player);
        int otherPlayer = getOpponent(player);
        return isTurnOf(otherPlayer) && (getLegalMoves(otherPlayer).size() == 0); //opponent has no legal moves
	}

	@Override
	public boolean legal(int row, int col) {
		return (row >= minRow() && row <= maxRow() && 
				col >= minCol() && col <= maxCol());
	}

	@Override
	public boolean isBlackSquare(int row, int col) {
		return (row + col) % 2 == 1;
	}

	@Override
	public boolean isWhiteSquare(int row, int col) {
		return (row + col) % 2 == 0;
	}

	@Override
	public boolean blackPieceAt(int row, int col) {
		return pieceAt (row, col, BLACK);
	}

	@Override
	public boolean whitePieceAt(int row, int col) {
		return pieceAt (row, col, WHITE);
	}

	@Override
	public boolean pieceAt(int row, int col, int piece) {
		return isBlackSquare(row, col) && 
			    board[getIndex(row, col)] % pieceMask == piece; // {0, 1, 2} = {empty, black, white}
	}

	@Override
	public boolean noPieceAt(int row, int col) {
		return isWhiteSquare(row, col) || board[getIndex (row, col)] == empty;
	}

	@Override
	public boolean kingAt(int row, int col) {
		return !noPieceAt (row, col) && 
			    board[getIndex (row, col)] / pieceMask > 0;
	}

	@Override
	public int numPiecesOf(int color) {
		validPlayer(color);
        int count = 0;
        for (int row = minRow(); row <= maxRow(); ++row) {
            for (int col = minCol(); col <= maxCol(); ++col) {
                if (pieceAt (row, col, color)) {++count;}
            }
        }
        return count;
	}

	@Override
	public int numKingsOf(int color) {
		validPlayer(color);
        int count = 0;
        for (int row = minRow(); row <= maxRow(); ++row) {
            for (int col = minCol(); col <= maxCol(); ++col) {
                if (pieceAt (row, col, color) && kingAt (row, col)) {++count;}
            }
        }
        return count;
	}

	@Override
	public boolean toBeKing(int row, int col) {
		return (row == maxRow() && blackPieceAt (row, col)) || 
			    (row == minRow() && whitePieceAt (row, col)) && legal (row, col);
	}

	@Override
	public void makeKing(int row, int col) {
		board[getIndex (row, col)] += addKing;
	}

	@Override
	public int getIndex(int row, int col) {
		return (row * 8 + col) / 2;
	}

	@Override
	public int getRow(int index) {
		return index / 4;
	}

	@Override
	public int getCol(int index) {
		return ((index % 4) * 2) + (1 - (getRow (index) % 2));
	}

	@Override
	public Set<Move> getLegalMoves(int player) {
		return null;
	}

}
