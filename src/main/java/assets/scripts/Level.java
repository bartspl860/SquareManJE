package assets.scripts;

import java.awt.*;
import java.util.ArrayList;

public class Level {
    private String name;
    private int number;
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    private ArrayList<SecretWall> secretWalls = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Healthpack> healthpacks = new ArrayList<>();
    private ArrayList<Healthpack> hiddenHealthpacks = new ArrayList<>();
    private ArrayList<Coin> coins = new ArrayList<>();
    private ArrayList<Coin> hiddenCoins = new ArrayList<>();
    private ArrayList<SupriseItem> suprises = new ArrayList<>();
    private ArrayList<SupriseItem> hiddenSuprises = new ArrayList<>();
    private Point playerStartPosition = new Point(0,0);
    private Finish finish;
    public enum Axis{X,Y}
    public void addWall(Wall w){
        walls.add(w);
    }
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
    public void addSecretWall(int x, int y, int it, Axis ax, Color c){
        x *= 30;
        y *= 30;
        if(ax == Axis.X){
            for(int i=0;i<it*30;i+=30){
                secretWalls.add(new SecretWall(x+i,y,c));
            }
        }
        else{
            for(int i=0;i<it*30;i+=30) {
                secretWalls.add(new SecretWall(x,y+i,c));
            }
        }
    }
    public void addFinish(int x, int y, int w, int h, Color c){
        finish = new Finish(x*30,y*30,w*30,h*30,c);
    }
    public void addEnemy(Enemy e){
        enemies.add(e);
    }
    public void addHealthPack(int x, int y){
        healthpacks.add(new Healthpack(x*30+7,y*30+7));
        hiddenHealthpacks.add(new Healthpack(x*30+7,y*30+7));
    }
    public void addCoin(int x, int y){
        coins.add(new Coin(x*30+7,y*30+7));
        hiddenCoins.add(new Coin(x*30+7,y*30+7));
    }
    public Finish getFinish() {
        return finish;
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public ArrayList<Wall> getWalls() {
        return walls;
    }
    public ArrayList<SupriseItem> getSuprises() { return suprises; }
    public ArrayList<SecretWall> getSecretWalls() {
        return secretWalls;
    }
    public ArrayList<Healthpack> getHealthpacks() {
        return healthpacks;
    }
    public void restoreHealthpacks(){
        healthpacks.clear();
        for(var hp : hiddenHealthpacks){
            healthpacks.add(new Healthpack(hp.x,hp.y));
        }
    }
    public void restoreCoins(){
        coins.clear();
        for(var coin : hiddenCoins){
            coins.add(new Coin(coin.x,coin.y));
        }
    }
    public void restoreSuprises(){
        suprises.clear();
        for(var item : hiddenSuprises){
            suprises.add(item);
        }
    }
    public ArrayList<Coin> getCoins() {
        return coins;
    }
    public void addSuprise(int x, int y){
        x=x*30;y=y*30;
        var sup = new SupriseItem(x,y);
        hiddenSuprises.add(sup);
        suprises.add(sup);
    }
    private static int instanceCounter;
    static {
        instanceCounter = 1;
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
