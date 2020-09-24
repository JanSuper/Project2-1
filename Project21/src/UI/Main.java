package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    Scene scene;
    Board board;
    BoardView bv;

    @Override
    public void start(Stage stage) throws Exception
    {
        bv = new BoardView();
        scene = new Scene(bv);


        stage.setScene(scene);
        stage.setTitle("title");
        stage.show();

        board = new Board();
        bv.draw(board.getBoard());
        
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
