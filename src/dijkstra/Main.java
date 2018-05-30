package dijkstra;

import dijkstra.controller.Grid;

import javax.swing.*;


public class Main {
    // =========
    public final static boolean IS_BIG_SCREEN = false;
    public final static int NB_PATHS = 1;
    // =========

    public final static int NUMBER_CASE_HEIGH = sizeScreen(IS_BIG_SCREEN)[0];
    public final static int NUMBER_CASE_WIDTH = sizeScreen(IS_BIG_SCREEN)[0];
    public final static int GRID_HEIGH        = NUMBER_CASE_HEIGH * sizeScreen(IS_BIG_SCREEN)[1];
    public final static int GRID_WIDTH        = NUMBER_CASE_WIDTH * sizeScreen(IS_BIG_SCREEN)[1];


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

    public static int[] sizeScreen(boolean isBig) {
        int[] big = new int[] {60, 12};
        int[] small = new int[] {12, 60};

        return isBig ? big : small;
    }

}
