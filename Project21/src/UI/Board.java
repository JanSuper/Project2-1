package UI;

public class Board
{
    private int[][] board;

    public Board()
    {
        board = starting_board();
    }

    private int[][] starting_board()
    {
        int[][] new_board = new int[8][8];

        //empty board
        for(int[] i : new_board)
        {
            for(int j : i)
                j = 0;
        }

        //place player's pieces
        for(int i = 0; i < 8; i=i+2)
        {
            new_board[1][i] = 2;
            new_board[5][i] = 1;
            new_board[7][i] = 1;
        }

        for(int i = 1; i < 8; i=i+2)
        {
            new_board[0][i] = 2;
            new_board[2][i] = 2;
            new_board[6][i] = 1;
        }


        return new_board;
    }

    public int[][] getBoard() {
        return this.board;
    }
}
