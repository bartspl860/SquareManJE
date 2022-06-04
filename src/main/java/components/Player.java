package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

public class Player extends Rectangle{
    public Dimension maxPosition = new Dimension(640,640);
    private enum Direction{UP,DOWN,LEFT,RIGHT,NONE}
    private Image sprite;
    private Direction vectorX = Direction.NONE;
    private Direction vectorY = Direction.NONE;
    private Direction vector = Direction.NONE;
    private float speed = 2;
    public Player() {
        super(280,300, 30,30);
        loadSprite("src/main/java/assets/sprites/square_man_normal.png");
    }
    public Player(int x, int y, int w, int h){
        this();
        this.x = x;
        this.y = x;
        this.width = w;
        this.height = h;
    }
    public Player(int x, int y){
        this();
        this.x = x;
        this.y = x;
    }
    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    private void loadSprite(String path) {
        ImageIcon ii = new ImageIcon(path);
        sprite = ii.getImage();
        width = sprite.getWidth(null);
        height = sprite.getHeight(null);
    }
    public void move() {
        switch (vectorX){
            case LEFT: x -= speed;break;
            case RIGHT: x += speed;break;
        }
        switch (vectorY){
            case UP: y += speed;break;
            case DOWN: y -= speed;break;
        }

        boolean gointAtAnAngle =
                (vectorX == Direction.RIGHT && (vectorY == Direction.UP || vectorY == Direction.DOWN)) ||
                (vectorX == Direction.LEFT && (vectorY == Direction.UP || vectorY == Direction.DOWN));

        x = Math.max(0, Math.min(maxPosition.width - 50, x));
        y = Math.max(0, Math.min(maxPosition.height - 50, y));

        changeLookingDirection();
    }
    private void changeLookingDirection(){
        switch (vector){
            case DOWN -> loadSprite("src/main/java/assets/sprites/square_man_going_up.png");
            case UP -> loadSprite("src/main/java/assets/sprites/square_man_going_down.png");
            case LEFT -> loadSprite("src/main/java/assets/sprites/square_man_normal_left.png");
            case RIGHT -> loadSprite("src/main/java/assets/sprites/square_man_normal.png");
        }
    }
    public Image getSprite() {

        return sprite;
    }
    private List<Integer> pressedKeys = new LinkedList<>();
    public void keyPressed(KeyEvent e) {

        var keycode = e.getKeyCode();
        if(!pressedKeys.contains(keycode))
            pressedKeys.add(keycode);

        if(keycode == KeyEvent.VK_A){
            vectorX = Direction.LEFT; vector = Direction.LEFT;
        }
        else if(keycode  == KeyEvent.VK_D){
            vectorX = Direction.RIGHT; vector = Direction.RIGHT;
        }
        else if(keycode  == KeyEvent.VK_W){
            vectorY = Direction.DOWN; vector = Direction.DOWN;
        }
        else if(keycode  == KeyEvent.VK_S){
            vectorY = Direction.UP; vector = Direction.UP;
        }
    }
    public void keyReleased(KeyEvent e) {
        var keycode = (Integer) e.getKeyCode();
        pressedKeys.remove(keycode);

        if(keycode == KeyEvent.VK_A){
            if(!pressedKeys.contains(KeyEvent.VK_D))
                vectorX = Direction.NONE;
        }
        else if(keycode == KeyEvent.VK_D){
            if(!pressedKeys.contains(KeyEvent.VK_A))
                vectorX = Direction.NONE;
        }
        else if(keycode == KeyEvent.VK_W){
            if(!pressedKeys.contains(KeyEvent.VK_S))
                vectorY = Direction.NONE;
        }
        else if(keycode == KeyEvent.VK_S){
            if(!pressedKeys.contains(KeyEvent.VK_W))
                vectorY = Direction.NONE;
        }

        if(pressedKeys.isEmpty()){
            vectorX = Direction.NONE;
            vectorY = Direction.NONE;
        }
    }
    public boolean isColliding(Rectangle rectangle){
        if(
            this.x < rectangle.x + rectangle.width &&
            this.y < rectangle.y + rectangle.height &&
            this.x + this.width > rectangle.x &&
            this.y + this.height  > rectangle.y
        )
        {
            return true;
        }
        return false;
    }
    public void resolveCollision(Rectangle rectangle){
        double vector_x, vector_y;

        // get the distance between center points
        vector_x = getCenterX() - rectangle.getCenterX();
        vector_y = getCenterY() - rectangle.getCenterY();

        // is the y vector longer than the x vector?
        if (Math.pow(vector_y, 2) > Math.pow(vector_x, 2)) {// square to remove negatives

            // is the y vector pointing down?
            if (vector_y > 0) {

                this.y = rectangle.y + rectangle.height;

            } else { // the y vector is pointing up

                this.y = rectangle.y - this.height;

            }
        } else { // the x vector is longer than the y vector

            // is the x vector pointing right?
            if (vector_x > 0) {

                this.x = rectangle.x + rectangle.width;

            } else { // the x vector is pointing left

                this.x = rectangle.x - this.width;

            }
        }
    }
}