package model;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Set;

public class Game extends JFrame {
	
	private JButton startGame;
    private CheckersPanel board;
    private JLabel message;

	public Game() {
        setTitle("Checkers");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        
        board = new CheckersPanel();
        board.addMouseListener(new CheckerListener());
        pane.add(board, BorderLayout.CENTER);
        
        JPanel top = new JPanel(new GridLayout(4,1));
        pane.add(top, BorderLayout.NORTH);
        
        JPanel buttons = new JPanel(new FlowLayout());
        top.add(buttons);
        
        startGame = new JButton("Start new game");
        startGame.addActionListener(new GameStarter());
        buttons.add(startGame);
//        
//        computerMove = new JButton("Computer move");
//        computerMove.addActionListener(new ComputerMover());
//        buttons.add(computerMove);
//        
        message = new JLabel();
        buttons.add(message);
	}
	
	private class GameStarter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            restart();
        }
    }
	
	private void restart() {
        board.getBoard().reset();
        board.repaint();
       	changeTurn();
    }
	
	private void changeTurn () {
        if (board.getBoard().isWinner(Board.BLACK)) {
            message.setText ("Black wins!");
        } else if (board.getBoard().isWinner(Board.WHITE)) {
            message.setText ("White wins!");
        } else if (board.getBoard().isTurnOf(Board.WHITE)) {
            message.setText ("White's turn");
        } else {
            message.setText ("Black's turn");
        }
    }
	
	private class CheckerListener extends MouseAdapter {
        private int colStart, rowStart;
        private boolean awaitingRelease;
        
        public void mousePressed (MouseEvent e) {
            colStart = board.getCol(e.getX());
            rowStart = board.getRow(e.getY());
            awaitingRelease = !board.getBoard().noPieceAt(rowStart, colStart);
            if (!awaitingRelease) {
                JOptionPane.showMessageDialog(null, "Illegal move");
            }
        }
        
        public void mouseReleased (MouseEvent e) {
            if (awaitingRelease) {
                Set<Move> legal = board.getBoard().getCurrentPlayerMoves();
                Move mouseMove = new Move(board.getBoard(), rowStart, colStart,
                board.getRow (e.getY()), 
                board.getCol (e.getX()));
                
                if (legal.contains(mouseMove)) {
                    makeMove(mouseMove);
                } else {
                    JOptionPane.showMessageDialog(null, "Illegal move");
                }
            } 
        }
    }
	
	private void makeMove(Move m) {
        if (m != null && !board.getBoard().gameOver()) {
            board.getBoard().move(m);
            board.repaint();
            changeTurn();
        }
    }
	
	public static void main(String[] args) {
        Game c = new Game();
        c.setVisible(true);
    }
}
