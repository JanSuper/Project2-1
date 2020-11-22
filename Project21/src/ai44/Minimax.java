package ai44;

import java.util.Set;

public class Minimax {

	static boolean gameOver(Board44 b) {
		return b.gameOver();
	}
	
	static int minimax(Board44 b, int depth, boolean isMax, int maxDepth) {
		
		int score = evaluate(b);
		
		if (gameOver(b)) return score;
		
		if (depth == maxDepth) return score;
		
		Board44 copy = b.copyGame();
		
		if (isMax) {
			int best = Integer.MIN_VALUE;
			Set<Move> legal = copy.getCurrentPlayerMoves();
			
			for (Move m : legal) {
				if (m != null && !copy.gameOver())
		            copy.move(m);
				best = Math.max(best, minimax(copy, depth + 1, !isMax, maxDepth));
			}
			return best;
		} else {
			int best = Integer.MAX_VALUE;
			Set<Move> legal = copy.getCurrentPlayerMoves();
			
			for (Move m : legal) {
				if (m != null && !copy.gameOver())
		            copy.move(m);
				best = Math.min(best, minimax(copy, depth + 1, !isMax, maxDepth));
			}
			return best;
		}
	}
	
	static Move bestMove(Board44 b, int depth, boolean isMax, int maxDepth) {
		Board44 copy = b.copyGame();
		Move bestMove = null;
		int bestVal = Integer.MAX_VALUE;
		if (isMax) {
			bestVal = Integer.MIN_VALUE;
		}
		
		if (isMax) {
			
			int best = -1000;
			Set<Move> legal = copy.getCurrentPlayerMoves();
			
			for (Move m : legal) {
				if (m != null && !copy.gameOver())
		            copy.move(m);
				best = Math.max(best, minimax(copy, depth + 1, !isMax, maxDepth));
				if (best > bestVal) {
					bestMove = m;
				}
			}
			return bestMove;
		} else {
			int best = 1000;
			Set<Move> legal = copy.getCurrentPlayerMoves();
			
			for (Move m : legal) {
				if (m != null && !copy.gameOver())
		            copy.move(m);
				best = Math.min(best, minimax(copy, depth + 1, !isMax, maxDepth));
				if (best < bestVal) {
					bestMove = m;
				}
			}
			return bestMove;
		}
	}
	
	static int evaluate(Board44 b) {
		int score = 2*b.numKingsOf(Board44.BLACK) + b.numPiecesOf(Board44.BLACK) 
					- 2*b.numKingsOf(Board44.WHITE) - b.numPiecesOf(Board44.WHITE);
		return score;
	}
	
}
