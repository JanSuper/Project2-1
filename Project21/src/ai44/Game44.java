package ai44;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game44 extends JFrame {
	
	private JButton startGame;
    private CheckersPanel44 board;
    private JLabel message;

	public Game44() {
        setTitle("Checkers");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        
        board = new CheckersPanel44();
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
        if (board.getBoard().isWinner(Board44.BLACK)) {
            message.setText ("Black wins!");
        } else if (board.getBoard().isWinner(Board44.WHITE)) {
            message.setText ("White wins!");
        } else if (board.getBoard().isTurnOf(Board44.WHITE)) {
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
//            if (!awaitingRelease) {
//                JOptionPane.showMessageDialog(null, "Illegal move");
//            }
            boolean isMax = (board.getBoard().getCurrentPlayer() == Board44.BLACK);
            
            int optimal = Minimax.minimax(board.getBoard(), 0, isMax, 5);
            Move bestMove = Minimax.bestMove(board.getBoard(), 0, isMax, 5);
            System.out.println("Estimated optimal: " + optimal);
            System.out.println("Suggestive move: " + bestMove);
        }
        
        public void mouseReleased (MouseEvent e) {
            if (awaitingRelease) {
                Set<Move> legal = board.getBoard().getCurrentPlayerMoves();
                Move mouseMove = new Move(board.getBoard(), rowStart, colStart,
                board.getRow (e.getY()), 
                board.getCol (e.getX()));
                
                System.out.println("Mouse: " + mouseMove);
                
                for (Move move : legal) {
					System.out.println(move);
				}
                
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
        Game44 c = new Game44();
        c.setVisible(true);
        
    }
}
