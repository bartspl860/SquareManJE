package assets.scripts;

import javax.swing.*;
import java.awt.*;

public class SurpriseItem extends Rectangle {
    private Image sprite;
    public Image getSprite() {
        return sprite;
    }
    public SurpriseItem(int x , int y){
        super(x,y,15,15);
        loadSprite("src/main/java/assets/sprites/watch.png");
    }
    private void loadSprite(String path) {
        ImageIcon ii = new ImageIcon(path);
        sprite = ii.getImage();
        width = sprite.getWidth(null) + 10;
        height = sprite.getHeight(null) + 10;
    }
}
