package model;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheckersPanel extends JPanel {

    // Invariant: board != null

    Board board;
    Color squareBlack = new Color (0, 0, 0);
    Color squareWhite = new Color (255, 255, 255);
    Color pieceBlack = new Color (0, 0, 0);
    Color pieceWhite = new Color (255, 255, 255);

    // Pre: None
    // Post: Constructs the canvas
    public CheckersPanel () {
	super();
	board = new Board();
    }

    public Board getBoard() {return board;}

    // Pre: None
    // Post: Draws a visual representation of a Board corresponding
    //       to what board represents
    protected void paintComponent (Graphics g) {

	int squareWidth = getWidth() / board.numCols();
	int squareHeight = getHeight() / board.numRows();

	for (int row = board.minRow(); row <= board.maxRow(); ++row) {
	    for (int col = board.minCol(); col <= board.maxCol(); ++col) {
		if (board.isWhiteSquare (row, col)) {
		    g.setColor (squareWhite);
		} else {
		    g.setColor (squareBlack);
		}
		g.fillRect (col * squareWidth, row * squareHeight, 
			    squareWidth, squareHeight);

		if (!board.noPieceAt (row, col)) {
		    if (board.pieceAt (row, col, Board.WHITE)) {
			g.setColor (pieceWhite);

		    } else if (board.pieceAt (row, col, Board.BLACK)) {
			g.setColor (pieceBlack);
		    }
		    g.fillOval (col * squareWidth, row * squareHeight, 
				squareWidth, squareHeight);
		    g.setColor (Color.white);
		    g.drawOval (col * squareWidth, row * squareHeight, 
				squareWidth - 1, squareHeight - 1);
		    if (board.kingAt (row, col)) {
			g.drawOval (col * squareWidth + squareWidth / 4,
				    row * squareHeight + squareHeight / 4,
				    squareWidth / 2, squareHeight / 2);
		    }
		}
	    }
	}
    }

    // Pre: 0 <= yClick < getHeight()
    // Post: Returns corresponding board row
    public int getRow (int yClick) {
	return yClick * board.numRows() / getHeight(); 
    }

    // Pre: 0 <= xClick < getWidth()
    // Post: Returns corresponding board column
    public int getCol (int xClick) {
	return xClick * board.numCols() / getWidth();
    }
    
}
