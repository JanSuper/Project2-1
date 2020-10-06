import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class RedPiece extends Piece
{
    public RedPiece()
    {
        super();

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
}
