package components;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private final int DELAY = 10;
    private Dimension boardSize;
    private Player player;

    @JsonProperty("Levels")
    public ArrayList<Level> levels = new ArrayList<>();
    private int currentLevel = 1;

    public void loadLevel(int level){
        currentLevel = level;
        var sp = levels.get(currentLevel-1).getPlayerStartPosition();
        player.x = sp.x;
        player.y = sp.y;
    }

    public Board(int width, int height) {
        boardSize = new Dimension(width,height);
        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.white);
        setFocusable(true);

        player = new Player(30,30);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawNet(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawNet(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        for(int i=0;i < boardSize.height;i+=30){
            g.drawLine(0,i,boardSize.width, i);
        }
        for(int i=0;i < boardSize.width;i+=30){
            g.drawLine(i,0,i, boardSize.height);
        }
    }
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(
                player.getSprite(),
                player.x,
                player.y,
                player.width,
                player.height,
                this);

        for(var wall : levels.get(currentLevel-1).getWalls()){
            g2d.setColor(wall.getColor());
            g2d.fillRect(
                    wall.x,
                    wall.y,
                    wall.width,
                    wall.height
            );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move();

        for (var wall : levels.get(currentLevel-1).getWalls()){
           if(player.isColliding(wall)){
               player.resolveCollision(wall);
           }
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
}