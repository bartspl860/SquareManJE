package assets.scripts;

import java.awt.*;

public class Finish extends Rectangle {
    private Color color;
    public Finish(int x, int y, int w, int h, Color c){
        super(x,y,w,h);
        this.color = c;
    }

    public Color getColor() {
        return color;
    }
}
