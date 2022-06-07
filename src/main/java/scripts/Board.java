package scripts;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.*;

public class Board extends JPanel implements ActionListener {

    private AudioManager audioManager;
    private Timer timer;
    private final int DELAY = 10;
    private Dimension boardSize;
    private Player player;
    public ArrayList<Level> levels = new ArrayList<>();
    private int currentLevel = 0;
    public void loadLevel(int level){
        Point playerStartPos;
        if(level >= levels.size() || level < 0){
            currentLevel = 0;
            resetGame();
        }
        else{
            currentLevel = level;
        }
        if(currentLevel == levels.size()-1)
        {
            audioManager.playClip("applause");
        }
        playerStartPos = levels.get(currentLevel).getPlayerStartPosition();
        player.x = playerStartPos.x;
        player.y = playerStartPos.y;
    }
    public Board(int width, int height) {
        boardSize = new Dimension(width,height);
        initBoard();
    }
    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(new Color(238,130,238));
        setFocusable(true);

        player = new Player();
        timer = new Timer(DELAY, this);
        timer.start();

        initAudioManager();
    }
    private void initAudioManager(){
        audioManager = new AudioManager();
        audioManager.addClip("ukulele", "src/main/java/assets/sounds/ukulele.wav");
        audioManager.addClip("damage", "src/main/java/assets/sounds/damage.wav");
        audioManager.addClip("beep", "src/main/java/assets/sounds/beep.wav");
        audioManager.addClip("air", "src/main/java/assets/sounds/air.wav");
        audioManager.addClip("applause", "src/main/java/assets/sounds/applause.wav");
        audioManager.addClip("laugh", "src/main/java/assets/sounds/enemy_laugh.wav");
        audioManager.addClip("coin", "src/main/java/assets/sounds/coin.wav");
        audioManager.playLoopClip("ukulele");
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawNet(g);
        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();

        repaint();
    }
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawPlayer(g2d);
        drawEnemies(g2d);
        drawWalls(g2d);
        drawFinish(g2d);
        drawHealthpacks(g2d);
        drawCoins(g2d);
        drawGUI(g2d);
    }
    private void drawNet(Graphics g){
        g.setFont(new Font("TimesRoman", Font.PLAIN, 8));
        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;

        for(int i=0;i < boardSize.height;i+=30){
            g2d.drawLine(0,i,boardSize.width, i);
        }
        for(int i=0;i < boardSize.width;i+=30){
            g2d.drawLine(i,0,i, boardSize.height);
        }
        for(int i=0;i<21;i++){
            for(int j=0;j<21;j++){
                g2d.drawString("("+i+","+j+")",i*30+7,j*30+15);
            }
        }
    }
    private void drawGUI(Graphics2D g2d){
        var logoIcon = new ImageIcon("src/main/java/assets/gui/squareman2.png");
        var logo = logoIcon.getImage();
        g2d.drawImage(logo,10,0,307,54, this);

        var heartIcon = new ImageIcon("src/main/java/assets/gui/health.png");
        var heart = heartIcon.getImage();
        for(int i=0;i<player.getHealth();i++){
            g2d.drawImage(heart,430+i*30,17,40,40, this);
        }

        g2d.setFont(new Font("Arial Rounded", Font.BOLD, 25));
        g2d.setColor(Color.BLACK);

        g2d.drawString("Level: "+(currentLevel+1),330,46);

        var coinIcon = new ImageIcon("src/main/java/assets/sprites/coin.png");
        var coin =coinIcon.getImage();
        g2d.drawImage(coin, 540, 12, 40,40, this);
        g2d.drawString(""+player.getCoins(), 585, 42);
    }
    private void drawFinish(Graphics2D g2d){
        var finish = levels.get(currentLevel).getFinish();
        if(finish==null) return;
        g2d.setColor(Color.BLACK);
        g2d.drawRect(finish.x-1,finish.y-1,finish.width+1,finish.height+1);
        g2d.setColor(finish.getColor());
        g2d.fillRect(finish.x,finish.y,finish.width,finish.height);
    }
    private void drawPlayer(Graphics2D g2d){
        g2d.drawImage(
                player.getSprite(),
                player.x,
                player.y,
                player.width,
                player.height,
                this);
    }
    private void drawWalls(Graphics2D g2d){
        for(var wall : levels.get(currentLevel).getWalls()) {
            g2d.setColor(Color.BLACK);
            g2d.drawRect(
                    wall.x-1,
                    wall.y-1,
                    wall.width+1,
                    wall.height+1
            );
        }
        for(var wall : levels.get(currentLevel).getWalls()){
            g2d.setColor(wall.getColor());
            g2d.fillRect(
                    wall.x,
                    wall.y,
                    wall.width,
                    wall.height
            );
        }
    }
    private void drawEnemies(Graphics2D g2d){
        for(var enemy : levels.get(currentLevel).getEnemies()){
            g2d.drawImage(
                    enemy.getSprite(),
                    enemy.x,
                    enemy.y,
                    enemy.width,
                    enemy.height,
                    this);
        }
    }
    private void drawHealthpacks(Graphics2D g2d){
        for(var healthpack : levels.get(currentLevel).getHealthpacks()){
            g2d.drawImage(
                    healthpack.getSprite(),
                    healthpack.x,
                    healthpack.y,
                    healthpack.width,
                    healthpack.height,
                    this);
        }
    }
    private void drawCoins(Graphics2D g2d){
        for(var coin : levels.get(currentLevel).getCoins()){
            g2d.drawImage(
                    coin.getSprite(),
                    coin.x,
                    coin.y,
                    coin.width,
                    coin.height,
                    this);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        var level = levels.get(currentLevel);

        player.move();

        for(var enemy : level.getEnemies()){
            enemy.move();
            if(player.isColliding(enemy)){
                var startPosition = level.getPlayerStartPosition();
                player.x = startPosition.x;
                player.y = startPosition.y;
                player.getDamage();
                audioManager.playClip("damage");
                audioManager.playClip("laugh");
                var eh = new EffectHandler(200,
                        ()->setBackground(Color.RED),
                        ()->setBackground(new Color(238,130,238)));
                if(player.getHealth() < 1){
                    loadLevel(0);
                    resetGame();
                }
            }
        }

        for (var wall : level.getWalls()){
           if(player.isColliding(wall)){
               player.resolveCollision(wall);
           }
        }

        var finish = level.getFinish();
        if(finish!=null && player.isColliding(finish)){
            loadLevel(currentLevel+1);
            audioManager.playClip("air");
        }

        var healthpackIterator = level.getHealthpacks().iterator();
        while(healthpackIterator.hasNext()){
            var healthpack = healthpackIterator.next();
            if(player.isColliding(healthpack)){
                healthpackIterator.remove();
                player.heal();
                audioManager.playClip("beep");
                var eh = new EffectHandler(200,
                        ()->setBackground(Color.GREEN),
                        ()->setBackground(new Color(238,130,238)));
            }
        }

        var coinIterator = level.getCoins().iterator();
        while(coinIterator.hasNext()){
            var coin = coinIterator.next();
            if(player.isColliding(coin)){
                coinIterator.remove();
                player.addCoin();
                audioManager.playClip("coin");
                var eh = new EffectHandler(200,
                                ()->setBackground(Color.YELLOW),
                                ()->setBackground(new Color(238,130,238)));
            }
        }

    }
    public void resetGame(){
        for(var localLevel : levels){
            localLevel.restoreHealthpacks();
            localLevel.restoreCoins();
            player.restoreHealth();
            player.resetCoins();
        }
    }
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
            switch (e.getKeyCode()){
                case KeyEvent.VK_1 -> loadLevel(0);
                case KeyEvent.VK_2 -> loadLevel(1);
                case KeyEvent.VK_3 -> loadLevel(2);
                case KeyEvent.VK_4 -> loadLevel(3);
            }
        }
    }
    private class EffectHandler implements ActionListener{
        private int effectCounter = 0;
        private Timer timer;
        private Delegate action;
        private Delegate reaction;

        public EffectHandler(int miliseconds, Delegate action, Delegate reaction){
            timer = new Timer(miliseconds, this);
            this.action = action;
            this.reaction = reaction;
            action.execute();
            timer.start();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            reaction.execute();
            timer.stop();
        }
        interface Delegate{
            void execute();
        }
    }
}