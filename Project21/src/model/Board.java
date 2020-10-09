package model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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
    
    private boolean turnRepeating;
    private int repeatingRow;
    private int repeatingCol;
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
		Board dup = new Board();
        for (int i = 0; i < numSquares; ++i) {
            dup.board[i] = board[i];
        }
        dup.currentPlayer = currentPlayer;
        dup.repeatingRow = repeatingRow;
        dup.repeatingCol = repeatingCol;
        dup.turnRepeating = turnRepeating;
//        dup.moveSequence = new ArrayList<Move>();
//        for (Move m: moveSequence) {dup.moveSequence.add(m);}
        return dup;
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
	public int getCurrentPlayer() {
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
		 validPlayer(player);
       Set<Move> captureMoves = allCaptureMoves (player);
       if (captureMoves.size() > 0) {return captureMoves;}
       return allRegularMoves (player);
	}
	
	public Set<Move> allCaptureMoves (int player) {
        Set<Move> captureMoves = new LinkedHashSet<Move>();
        if (turnRepeating) {
            addCaptureMoves (captureMoves, repeatingRow, repeatingCol);
            
        } else {
            for (int i = 0; i < numSquares; ++i) {
                int row = getRow(i);
                int col = getCol(i);
                if (pieceAt (row, col, player)) {
                    addCaptureMoves (captureMoves, row, col);
                }
            }
        }
        
        return captureMoves;
    }
	
	public Set<Move> allRegularMoves (int player) {
		Set<Move> regularMoves = new LinkedHashSet<Move>();
		if (!turnRepeating) {
			for (int i = 0; i < numSquares; ++i) {
				int row = getRow(i);
				int col = getCol(i);
				if (pieceAt(row, col, player)) {
					addRegularMoves(regularMoves, row, col);
				}
			}
		}
        return regularMoves;
    }
	
	private void addRegularMoves (Set<Move> regularMoves, int row, int col) {
        Set<Move> candidates = getCandidateRegularMoves (row, col);
        for (Move m: candidates) {
            if (legal (m.getDestinationRow(), m.getDestinationCol()) &&
            noPieceAt (m.getDestinationRow(), m.getDestinationCol())) {
                regularMoves.add (m);
            }
        }
    }
	
	private Set<Move> getCandidateRegularMoves (int row, int col) {
        Move upLeft = new Move (this, row, col, row - 1, col - 1);
        Move upRight = new Move (this, row, col, row - 1, col + 1);
        Move downLeft = new Move (this, row, col, row + 1, col - 1);
        Move downRight = new Move (this, row, col, row + 1, col + 1);
        return getCandidateMoves (row, col, upLeft, upRight, 
        downLeft, downRight);
    }

	public void move (Move move) {
        int start = getIndex (move.getSourceRow(), move.getSourceCol());
        int end = getIndex (move.getDestinationRow(), move.getDestinationCol());
        board[end] = board[start];
        board[start] = empty;
        if (!kingAt (move.getDestinationRow(), move.getDestinationCol()) &&
	    toBeKing (move.getDestinationRow(), move.getDestinationCol())) {
            makeKing (move.getDestinationRow(), move.getDestinationCol());
        }
        boolean changeTurn = !move.isCapture();
        
        if (!changeTurn) {
            int captureRow = getCaptureRow (move);
            int captureCol = getCaptureCol (move);
            board[getIndex (captureRow, captureCol)] = empty;
            
            if (pieceCanStillCapture (move.getDestinationRow(), move.getDestinationCol())) {
                repeatingRow = move.getDestinationRow();
                repeatingCol = move.getDestinationCol();
                turnRepeating = true;
                
            } else {
                changeTurn = true;
            }
            
        } 
        
        if (changeTurn) {
            currentPlayer = (currentPlayer == BLACK) ? WHITE : BLACK;
            turnRepeating = false;
        }
//        
//        moveSequence.add(move);
    }
	
	private int getCaptureRow (Move move) {
        return (move.getSourceRow() + move.getDestinationRow()) / 2;
    }
    
    private int getCaptureCol (Move move) {
        return (move.getSourceCol() + move.getDestinationCol()) / 2;
    }
	
    public boolean pieceCanStillCapture (int row, int col) {
        if (!pieceAt (row, col, getCurrentPlayer())) {return false;}
        Set<Move> captureMoves = new LinkedHashSet<Move>();
        addCaptureMoves (captureMoves, row, col);
        return (captureMoves.size() > 0);
    }
    
    private void addCaptureMoves (Set<Move> captureMoves, int row, int col) {
        Set<Move> candidates = getCandidateCaptures (row, col);
        for (Move m: candidates) {
            int captureRow = getCaptureRow (m);
            int captureCol = getCaptureCol (m);
            if (legal (m.getDestinationRow(), m.getDestinationCol()) &&
                noPieceAt (m.getDestinationRow(), m.getDestinationCol()) &&
            ((blackPieceAt (m.getSourceRow(), m.getSourceCol()) &&
            whitePieceAt (captureRow, captureCol)) ||
            (whitePieceAt (m.getDestinationRow(), m.getDestinationCol()) &&
            blackPieceAt (captureRow, captureCol)))) {
                captureMoves.add (m);
            }		
        }
    }
    
    private Set<Move> getCandidateCaptures (int row, int col) {
        Move upLeft = new Move (this, row, col, row - 2, col - 2);
        Move upRight = new Move (this, row, col, row - 2, col + 2);
        Move downLeft = new Move (this, row, col, row + 2, col - 2);
        Move downRight = new Move (this, row, col, row + 2, col + 2);
        return getCandidateMoves (row, col, upLeft, upRight, 
        downLeft, downRight);
    }
    
    private Set<Move> getCandidateMoves (int row, int col, Move upLeft,
    	    Move upRight, Move downLeft,
    	    Move downRight) {
    	        Set<Move> moves = new LinkedHashSet<Move>();
    	        if (kingAt (row, col)) {
    	            moves.add (downRight);
    	            moves.add (downLeft);
    	            moves.add (upLeft);
    	            moves.add (upRight);
    	            
    	        } else if (blackPieceAt (row, col)) {
    	            moves.add (downRight);
    	            moves.add (downLeft);
    	            
    	        } else if (whitePieceAt (row, col)) {
    	            moves.add (upRight);
    	            moves.add (upLeft);
    	            
    	        } else {
    	            System.out.println ("Player does not exist at row: " + row 
    	            + " col: " + col);
    	        }
    	        return moves;
    	    }
    
	public Set<Move> getCurrentPlayerMoves () {
        return getLegalMoves (getCurrentPlayer());
    }
	
}
