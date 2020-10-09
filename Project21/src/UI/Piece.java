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
        rotate_in_right_position();
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
