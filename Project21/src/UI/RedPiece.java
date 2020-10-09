package UI;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class RedPiece extends Piece
{
	
	private double moveSceneX, moveSceneY;
	
    public RedPiece()
    {
        super();
        
        translateXProperty().set(150);
        translateYProperty().set(50);
        translateZProperty().set(this.getTranslateZ()-20);
        addEvents();
        
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.DARKRED);
        setMaterial(material);
    }

    private void king()
    {
        PhongMaterial mt = new PhongMaterial();
        mt.setDiffuseMap(new Image(String.valueOf(getClass().getResource("/images/RedCrown.png"))));
        setMaterial(mt);
    }
    
    private void addEvents()
    {
        this.setOnMousePressed((t) -> {
        	if(Main.getSingleton().whiteTurn) {
                    moveSceneX = t.getSceneX();
                    moveSceneY = t.getSceneY();

                    this.toFront();
        		}
              }
        );
        

        this.setOnMouseDragged((t) ->
        {
        	if(Main.getSingleton().whiteTurn) {
        		double offsetX = t.getSceneX() - moveSceneX;
            	double offsetY = t.getSceneY() - moveSceneY;


            	this.setTranslateX(this.getTranslateX() + offsetX);
            	this.setTranslateY(this.getTranslateY() + offsetY);

            	moveSceneX = t.getSceneX();
            	moveSceneY = t.getSceneY();
        	}
        });

        this.setOnMouseClicked(t ->{
        	if(Main.getSingleton().whiteTurn) {
        		this.setTranslateX((int) (this.getTranslateX()/100)*100 + 50);
        		this.setTranslateY((int) (this.getTranslateY()/100)*100 + 50);
        		Main.getSingleton().whiteTurn = false;
        	}
        });
    }
}
