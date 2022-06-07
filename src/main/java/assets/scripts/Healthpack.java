package assets.scripts;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Healthpack extends Rectangle {
    public Image sprite;
    public Healthpack(int x, int y){
        super(x,y,15,15);
        loadSprite("src/main/java/assets/sprites/healthpack.png");
    }
    public void loadSprite(String path){
        ImageIcon ii = new ImageIcon(path);
        sprite = ii.getImage();
        width = sprite.getWidth(null);
        height = sprite.getHeight(null);
    }

    public Image getSprite() {
        return sprite;
    }
}
