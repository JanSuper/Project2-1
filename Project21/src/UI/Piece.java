package UI;


import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

public class Piece extends Cylinder
{
    private static final double HEIGHT = 20;
    private static final double RADIUS = 33.3;
    private double moveSceneX, moveSceneY;

    public Piece()
    {
        super(RADIUS, HEIGHT);
        translateXProperty().set(150);
        translateYProperty().set(50);
        translateZProperty().set(this.getTranslateZ()-20);
        rotate_in_right_position();
        addEvents();
    }

    private void rotate_in_right_position()
    {
        Rotate r = new Rotate(90, Rotate.X_AXIS);
        Transform t = new Rotate();
        t = t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().add(t);

    }
/**********************************************************************************************************************/
    private void addEvents()
    {
        this.setOnMousePressed((t) -> {
                    moveSceneX = t.getSceneX();
                    moveSceneY = t.getSceneY();

                    this.toFront();
                }
        );

        this.setOnMouseDragged((t) ->
        {
            double offsetX = t.getSceneX() - moveSceneX;
            double offsetY = t.getSceneY() - moveSceneY;


            this.setTranslateX(this.getTranslateX() + offsetX);
            this.setTranslateY(this.getTranslateY() + offsetY);

            moveSceneX = t.getSceneX();
            moveSceneY = t.getSceneY();
        });

        this.setOnMouseClicked(t ->{
            this.setTranslateX((int) (this.getTranslateX()/100)*100 + 50);
            this.setTranslateY((int) (this.getTranslateY()/100)*100 + 50);
        });
    }
/***********************************************************************************************************************/

    private int get_xCoordinate()
    {
        return (int)(((this.getTranslateX()-150)/100)+1);
    }

    private int get_yCoordinate()
    {
        return (int)((this.getTranslateY()-50)/100);
    }
}
