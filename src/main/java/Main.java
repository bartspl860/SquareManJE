import com.sun.jdi.event.StepEvent;
import scripts.Board;
import scripts.Enemy;
import scripts.Level;
import scripts.Step;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JFrame {
    private Board board;
    public Main() {
        initUI();
        initLVLS();
    }
    private void initUI() {
        board = new Board(640,640);
        add(board);

        setTitle("Squareman 2 Java Edition");
        setSize(640, 640);

        var icon = new ImageIcon("src/main/java/assets/gui/cube.png");
        setIconImage(icon.getImage());

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void initLVLS(){
        var wallColor = new Color(101, 81, 215);

        var level = new Level("Start");
        level.addWall(0,3,15, Level.Axis.X,wallColor);
        level.addWall(0,6,10, Level.Axis.X,wallColor);
        level.addWall(15,3,8, Level.Axis.Y,wallColor);
        level.addWall(10,6,5, Level.Axis.Y,wallColor);
        level.addWall(1,11,10, Level.Axis.X,wallColor);
        level.addWall(15,11,5, Level.Axis.X,wallColor);
        level.addWall(1,18,19, Level.Axis.X,wallColor);
        level.addWall(19,12,6, Level.Axis.Y,wallColor);
        level.addWall(1,12,2, Level.Axis.Y,wallColor);
        level.addWall(1,16,2, Level.Axis.Y,wallColor);
        level.addFinish(1,14,1,2,Color.CYAN);
        level.setPlayerStartPosition(new Point(0,4));

        {
            var steps = new ArrayList<Step>();
            steps.add(new Step(Step.Alignment.HORIZONTAL, 3, 17));
            steps.add(new Step(Step.Alignment.VERTICAL, 13, 16));
            steps.add(new Step(Step.Alignment.HORIZONTAL, 17, 3));
            steps.add(new Step(Step.Alignment.VERTICAL, 16, 13));
            var enemy = new Enemy(3,13, steps, false);
            level.addEnemy(enemy);
        }
        {
            var steps = new ArrayList<Step>();
            steps.add(new Step(Step.Alignment.HORIZONTAL, 17, 3));
            steps.add(new Step(Step.Alignment.VERTICAL, 16, 13));
            steps.add(new Step(Step.Alignment.HORIZONTAL, 3, 17));
            steps.add(new Step(Step.Alignment.VERTICAL, 13, 16));
            var enemy = new Enemy(17,16, steps, false);
            level.addEnemy(enemy);
        }
        {
            var steps = new ArrayList<Step>();
            steps.add(new Step(Step.Alignment.HORIZONTAL, 14,11));
            steps.add(new Step(Step.Alignment.VERTICAL, 11,9));
            steps.add(new Step(Step.Alignment.HORIZONTAL, 11,14));
            steps.add(new Step(Step.Alignment.VERTICAL, 8,6));
            steps.add(new Step(Step.Alignment.HORIZONTAL, 14,11));
            var enemy = new Enemy(14,11,steps);
            level.addEnemy(enemy);
        }

        board.levels.add(level);

        var level2 = new Level("Poziom 2");
        level2.addWall(12,3,6, Level.Axis.Y,wallColor);
        level2.addWall(8,3,6, Level.Axis.Y,wallColor);
        level2.addWall(8,3,5, Level.Axis.X,wallColor);
        level2.addWall(2,8,6, Level.Axis.X,wallColor);
        level2.addWall(12,8,7, Level.Axis.X,wallColor);
        level2.addWall(12,13,6, Level.Axis.Y,wallColor);
        level2.addWall(8,13,6, Level.Axis.Y,wallColor);
        level2.addFinish(9,18,3,1,Color.CYAN);
        level2.addWall(2,12,7, Level.Axis.X,wallColor);
        level2.addWall(12,12,7, Level.Axis.X,wallColor);
        level2.addWall(2,9,3, Level.Axis.Y,wallColor);
        level2.addWall(18,9,3, Level.Axis.Y,wallColor);
        level2.setPlayerStartPosition(new Point(10,4));

        {
            var steps = new ArrayList<Step>();
            steps.add(new Step(Step.Alignment.HORIZONTAL, 9, 11));
            steps.add(new Step(Step.Alignment.VERTICAL, 9, 11));
            steps.add(new Step(Step.Alignment.HORIZONTAL, 11, 9));
            steps.add(new Step(Step.Alignment.VERTICAL, 11, 9));
            var enemy = new Enemy(10,9,steps,false);
            level2.addEnemy(enemy);
        }
        {
            var steps = new ArrayList<Step>();
            steps.add(new Step(Step.Alignment.VERTICAL, 9, 11));
            steps.add(new Step(Step.Alignment.HORIZONTAL, 8, 6));
            steps.add(new Step(Step.Alignment.VERTICAL, 11, 9));
            steps.add(new Step(Step.Alignment.HORIZONTAL, 6, 4));
            steps.add(new Step(Step.Alignment.VERTICAL, 9, 11));
            var enemy = new Enemy(8,9,steps);
            level2.addEnemy(enemy);
        }
        {
            var steps = new ArrayList<Step>();
            steps.add(new Step(Step.Alignment.VERTICAL, 11, 9));
            steps.add(new Step(Step.Alignment.HORIZONTAL, 12, 14));
            steps.add(new Step(Step.Alignment.VERTICAL, 9, 11));
            steps.add(new Step(Step.Alignment.HORIZONTAL, 14, 16));
            steps.add(new Step(Step.Alignment.VERTICAL, 11, 9));
            var enemy = new Enemy(12,11,steps);
            level2.addEnemy(enemy);
        }

        board.levels.add(level2);

        var level3 = new Level("Ostatni");
        level3.addWall(0,4,4, Level.Axis.X,wallColor);
        level3.addWall(6,4,6, Level.Axis.X,wallColor);
        level3.addWall(14,4,5, Level.Axis.X,wallColor);
        level3.addWall(3,5,4, Level.Axis.X,wallColor);
        level3.addWall(11,5,8, Level.Axis.X,wallColor);
        level3.addWall(20,4,17, Level.Axis.Y,wallColor);
        level3.addWall(0,14,3, Level.Axis.X,wallColor);
        level3.addWall(4,14,16, Level.Axis.X,wallColor);
        level3.addWall(15,7,7, Level.Axis.Y,wallColor);
        level3.addWall(17,6,1, Level.Axis.Y,wallColor);
        level3.addWall(17,8,2, Level.Axis.Y,wallColor);
        level3.addWall(17,11,2, Level.Axis.Y,wallColor);
        level3.addWall(18,6,1, Level.Axis.Y,wallColor);
        level3.addWall(18,8,2, Level.Axis.Y,wallColor);
        level3.addWall(18,11,2, Level.Axis.Y,wallColor);
        level3.addWall(5,6,5, Level.Axis.Y,wallColor);
        level3.addWall(5,12,2, Level.Axis.Y,wallColor);
        level3.addWall(1,9,4, Level.Axis.X,wallColor);
        level3.addWall(8,13,2, Level.Axis.X,wallColor);
        level3.addWall(9,12,2, Level.Axis.X,Color.GREEN);
        level3.addWall(10,11,2, Level.Axis.X,wallColor);
        level3.addWall(11,10,2, Level.Axis.X,wallColor);
        level3.addWall(12,9,2, Level.Axis.X,wallColor);
        level3.addWall(13,8,2, Level.Axis.X,wallColor);
        level3.addWall(14,7,1, Level.Axis.X,wallColor);
        level3.addWall(6,15,3, Level.Axis.Y,wallColor);
        level3.addWall(10,15,3, Level.Axis.Y,wallColor);
        level3.addWall(14,15,3, Level.Axis.Y,wallColor);
        level3.addWall(8,17,4, Level.Axis.Y,wallColor);
        level3.addWall(12,17,4, Level.Axis.Y,wallColor);
        level3.addWall(21,4,20, Level.Axis.Y,wallColor);
        level3.setPlayerStartPosition(new Point(0,2));
        level3.addFinish(19,16,1,3,Color.CYAN);

        board.levels.add(level3);

        var endLevel = new Level("END");
        endLevel.addWall(3,4,7, Level.Axis.Y,wallColor);
        endLevel.addWall(4,4,3, Level.Axis.X,wallColor);
        endLevel.addWall(4,7,2, Level.Axis.X,wallColor);
        endLevel.addWall(4,10,3, Level.Axis.X,wallColor);
        endLevel.addWall(8,4,7, Level.Axis.Y,wallColor);
        endLevel.addWall(12,4,7, Level.Axis.Y,wallColor);
        endLevel.addWall(10,7,1, Level.Axis.Y,wallColor);
        endLevel.addWall(9,6,1, Level.Axis.Y,wallColor);
        endLevel.addWall(11,8,1, Level.Axis.Y,wallColor);
        endLevel.addWall(14,4,7, Level.Axis.Y,wallColor);
        endLevel.addWall(17,5,5, Level.Axis.Y,wallColor);
        endLevel.addWall(15,4,2, Level.Axis.X,wallColor);
        endLevel.addWall(15,10,2, Level.Axis.X,wallColor);
        endLevel.addWall(9,12,2, Level.Axis.Y,wallColor);
        endLevel.addWall(11,12,2, Level.Axis.Y,wallColor);
        endLevel.addWall(9,16,3, Level.Axis.X,wallColor);
        endLevel.addWall(8,15,1, Level.Axis.X,wallColor);
        endLevel.addWall(12,15,1, Level.Axis.X,wallColor);
        endLevel.setPlayerStartPosition(new Point(10,14));
        endLevel.addFinish(9,19,3,1,Color.cyan);
        board.levels.add(endLevel);

        board.loadLevel(0);
    }
    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(() -> {
            Main ex = new Main();
            ex.setVisible(true);
        });
    }
}