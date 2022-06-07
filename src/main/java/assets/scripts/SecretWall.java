package assets.scripts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecretWall extends Rectangle implements ActionListener {
    public static boolean soundEffectActive = false;
    public static boolean soundHasBeenPlayed = false;
    public boolean transparentEffectActive = false;
    private Color color;
    private Timer timer;
    public SecretWall(int x, int y, Color c){
        super(x,y,30,30);
        this.color = c;
    }
    public Color getColor() {
        return color;
    }

    private int counter = 0;
    private int visibility = 255;
    public void makeTransparent() {
        if(!transparentEffectActive){
            timer = new Timer(50, this);
            timer.start();
        }
    }

    public void makeVisible(){
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), visibility);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(),
                    Math.max(0, Math.min(visibility-=15,255)));
            if(visibility <= 0 || !transparentEffectActive){
                timer.stop();
                visibility = 255;
            }

    }
}
