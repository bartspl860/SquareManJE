package assets.scripts;

import javax.swing.*;
import java.awt.*;

public class SupriseItem extends Rectangle {
    private Image sprite;
    public Image getSprite() {
        return sprite;
    }
    public SupriseItem(int x ,int y, int w, int h){
        super(x,y,w,h);
        loadSprite("");
    }
    private void loadSprite(String path) {
        ImageIcon ii = new ImageIcon(path);
        sprite = ii.getImage();
        width = sprite.getWidth(null);
        height = sprite.getHeight(null);
    }
}
