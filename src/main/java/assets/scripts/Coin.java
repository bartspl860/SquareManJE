package assets.scripts;

import javax.swing.*;
import java.awt.*;

public class Coin extends Rectangle {
    private Image sprite;
    public Coin(int x, int y){
        super(x,y,15,15);
        loadSprite("src/main/java/assets/sprites/coin.png");
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
