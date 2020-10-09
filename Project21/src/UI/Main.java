package UI;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Main singleton = null;
	
    Board board = new Board();
    SmartStage stage = new SmartStage(board);

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    
    boolean whiteTurn = true;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(board, WIDTH, HEIGHT, true);
        scene.setCamera(camera);

        stage.setTitle("title");
        stage.setScene(scene);
        stage.show();
    }
    
    public static Main getSingleton() {
    	if(singleton == null) {
    		singleton = new Main();
    	}
    	return singleton;
    }

    public static void main(String[] args) {
        launch(args);
    }

}