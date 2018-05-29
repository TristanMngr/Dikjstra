import javax.swing.*;


public class Main {
    public final static int MENU_WIDTH        = 100;
    public final static int NUMBER_CASE_HEIGH = 60;
    public final static int NUMBER_CASE_WIDTH = 60;
    public final static int GRID_HEIGH        = NUMBER_CASE_HEIGH * 10;
    public final static int GRID_WIDTH        = NUMBER_CASE_WIDTH * 10;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dijkstra");
        Grid   grid  = new Grid();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(GRID_WIDTH, GRID_HEIGH + 22);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        grid.setSize(GRID_WIDTH, GRID_HEIGH);
        frame.add(grid);
    }

}
