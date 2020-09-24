package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class BoardView extends VBox
{
    private Canvas canvas;
    private final double WIDTH = 400;
    private final double HEIGHT = 400;

    public BoardView()
    {
        this.canvas = new Canvas(WIDTH,HEIGHT);

        this.getChildren().add(canvas);
    }

    public void draw(int[][] board)
    {
        GraphicsContext g = this.canvas.getGraphicsContext2D();

        //initialise board
        double width = this.canvas.getWidth();
        double height = this.canvas.getHeight();
        double w_step = width/4;
        double h_step = height/4;
        double tile_size = width/8;

        g.setFill(Color.BLACK);
        for(double x = tile_size; x < width; x+=w_step)
        {
            for (double y = 0; y < height; y+=h_step)
            {
                g.fillRect(x, y, tile_size, tile_size);
                g.fillRect(x-tile_size, y+tile_size, tile_size, tile_size);
            }
        }

        //add pieces according to board
        double piece_radius = 35;

        for(int i = 0; i < 8; i=i+1)
        {
            for(int j = 0; j < 8; j=j+1)
            {
                if (board[i][j] == 1)
                {
                    g.setFill(Color.SILVER);
                    g.fillOval(7.5+tile_size*j,7.5+tile_size*i, piece_radius,piece_radius);
                }
                if (board[i][j] == 2)
                {
                    g.setFill(Color.GRAY);
                    g.fillOval(7.5+tile_size*j,7.5+tile_size*i, piece_radius,piece_radius);
                }
            }
        }
    }
}
