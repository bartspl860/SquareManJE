import com.fasterxml.jackson.databind.ObjectMapper;
import components.Board;
import components.Level;
import components.Player;
import components.Wall;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    private Board board;
    public Main() {
        initUI();
        initLVLS();
    }
    private void initUI() {

        board = new Board(640,640);
        add(board);

        setTitle("SQUAREMAN Java Edition");
        setSize(640, 640);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void initLVLS(){
        var level = new Level("Start");

        var wallColor = Color.blue;

        level.addWall(0,1,15, Level.Axis.X,Color.blue);
        level.addWall(0,4,10, Level.Axis.X,Color.blue);
        level.addWall(15,1,10, Level.Axis.Y,Color.blue);
        level.addWall(10,4,7, Level.Axis.Y,Color.blue);
        level.addWall(1,11,10, Level.Axis.X,Color.blue);
        level.addWall(15,11,5, Level.Axis.X,Color.blue);
        level.addWall(1,18,19, Level.Axis.X,Color.blue);
        level.addWall(19,12,6, Level.Axis.Y,Color.blue);




        level.setPlayerStartPosition(new Point(0,80));

        board.levels.add(level);
        board.loadLevel(1);
    }

    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(() -> {
            Main ex = new Main();
            ex.setVisible(true);
        });
    }
}