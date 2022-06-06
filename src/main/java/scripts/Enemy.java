package scripts;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class Enemy extends Rectangle {
    public boolean isRevarsable = true;
    private int speed = 2;
    private Image sprite;
    private ArrayList<Step> steps = new ArrayList<>();
    private int currentStep;
    public Enemy(int x, int y){
        super(x*30,y*30,30,30);
        loadSprite("src/main/java/assets/sprites/enemy_normal.png");
    }
    public Enemy(int x, int y, ArrayList<Step> s){
        this(x,y);
        this.steps = s;
        currentStep = 0;
    }
    public Enemy(int x, int y, ArrayList<Step> s, boolean r){
        this(x,y,s);
        isRevarsable = r;
    }
    private void loadSprite(String path) {
        ImageIcon ii = new ImageIcon(path);
        sprite = ii.getImage();
        width = sprite.getWidth(null);
        height = sprite.getHeight(null);
    }
    public void addStep(Step s){
        this.steps.add(s);
    }
    public int getSpeed() {
        return speed;
    }
    public Image getSprite() {
        return sprite;
    }
    private boolean isReversed = false;
    public void reverseSteps(){
        for(var step : steps){
            step.reverse();
        }
    }
    private boolean stepEnded = false;
    public void move() {
        if (steps.isEmpty()) return;

        var step = steps.get(currentStep);

        switch (step.getDir()) {
            case VERTICAL -> {
                if (step.getStart() < step.getEnd()) {
                    this.loadSprite("src/main/java/assets/sprites/enemy_going_down.png");
                    if (y < step.getEnd()) {
                        y += speed;
                    } else {
                        stepEnded = true;
                    }
                } else {
                    this.loadSprite("src/main/java/assets/sprites/enemy_going_up.png");
                    if (y > step.getEnd()) {
                        y -= speed;
                    } else {
                        stepEnded = true;
                    }
                }
            }
            case HORIZONTAL -> {
                if (step.getStart() < step.getEnd()) {
                    this.loadSprite("src/main/java/assets/sprites/enemy_right.png");
                    if (x < step.getEnd()) {
                        x += speed;
                    } else {
                        stepEnded = true;
                    }
                } else {
                    if (x > step.getEnd()) {
                        this.loadSprite("src/main/java/assets/sprites/enemy_left.png");
                        x -= speed;
                    } else {
                        stepEnded = true;
                    }
                }
            }
        }
        if (stepEnded) {
            if(!isRevarsable){
                currentStep++;
                if (currentStep > steps.size() - 1) {
                    currentStep = 0;
                }
                stepEnded = false;
                return;
            }

            if (!isReversed) {
                currentStep++;
                if (currentStep > steps.size() - 1) {
                    currentStep = steps.size() - 1;
                    reverseSteps();
                    isReversed = true;
                }
            } else {
                currentStep--;
                if (currentStep < 0) {
                    currentStep = 0;
                    reverseSteps();
                    isReversed = false;
                }
            }
            stepEnded = false;
        }
    }
    @Override
    public String toString() {
        return "Enemy{" +
                "steps=" + steps +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
