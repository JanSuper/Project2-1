package UI;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class BlackPiece extends Piece
{

    public BlackPiece()
    {
        super();

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.DARKGREY);
        setMaterial(material);

    }

    private void king()
    {
        PhongMaterial mt = new PhongMaterial();
        mt.setDiffuseMap(new Image(String.valueOf(getClass().getResource("/images/RedCrown.png"))));
        setMaterial(mt);
    }
}
