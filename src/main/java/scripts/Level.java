package scripts;

import java.awt.*;
import java.util.ArrayList;

public class Level {
    private String name;
    private int number;
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Point playerStartPosition = new Point(0,0);
    private Finish finish;
    public void addWall(Wall w){
        walls.add(w);
    }
    public enum Axis{X,Y}
    public void addWall(int x, int y, int it, Axis ax, Color c){
        x *= 30;
        y *= 30;
        if(ax == Axis.X){
            for(int i=0;i<it*30;i+=30){
                this.addWall(new Wall(
                        new Point(x+i,y),
                        new Dimension(30,30),
                        c));
            }
        }
        else{
            for(int i=0;i<it*30;i+=30) {
                this.addWall(new Wall(
                        new Point(x, y + i),
                        new Dimension(30, 30),
                        c));
            }
        }
    }
    public Wall getWall(int n){
        return walls.get(n);
    }
    public void replaceWall(Wall w, int n){
        walls.remove(n);
        walls.add(w);
    }
    public void addFinish(int x, int y, int w, int h, Color c){
        finish = new Finish(x*30,y*30,w*30,h*30,c);
    }
    public void addEnemy(Enemy e){
        enemies.add(e);
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public ArrayList<Wall> getWalls() {
        return walls;
    }
    public int getNumber() {
        return number;
    }
    public Finish getFinish() {
        return finish;
    }
    private static int instanceCounter;
    static {
        instanceCounter = 1;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Level(){
        number = instanceCounter++;
        this.addWall(0,0,22, Level.Axis.X,Color.WHITE);
        this.addWall(0,1,22, Level.Axis.X,Color.WHITE);
    }
    public Level(String name) {
        this();
        this.name = name;
    }
    public void setPlayerStartPosition(Point sp){
        this.playerStartPosition = new Point(sp.x*30, sp.y*30);
    }
    public Point getPlayerStartPosition() {
        return playerStartPosition;
    }
}
