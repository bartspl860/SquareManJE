package scripts;

public class Step {
    public enum Alignment {VERTICAL, HORIZONTAL}
    private Alignment dir;
    private int start;
    private int end;
    public Step(Alignment dir, int start, int end) {
        this.dir = dir;
        this.start = start * 30;
        this.end = end * 30;
    }
    public int getStart() {
        return start;
    }
    public int getEnd() {
        return end;
    }
    public Alignment getDir() {
        return dir;
    }
    public void reverse(){
        var temp = start;
        start = end;
        end = temp;
    }
    @Override
    public String toString() {
        return "Step{" +
                "dir=" + dir +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}