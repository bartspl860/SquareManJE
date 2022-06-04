package components;

import java.awt.*;

public class Wall extends Rectangle{
    private Color color;
    public Wall(Point pos, Dimension dim, Color col){
        super(pos.x, pos.y, dim.width, dim.height);
        this.color = col;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
