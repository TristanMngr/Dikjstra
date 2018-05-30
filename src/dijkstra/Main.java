package dijkstra;

import dijkstra.controller.Grid;

import javax.swing.*;
import java.util.Scanner;


public class Main {
    // =========
    public static boolean IS_BIG_SCREEN;
    public static int NB_PATHS;

    public static int NUMBER_CASE_HEIGH;
    public static int NUMBER_CASE_WIDTH;
    public static int GRID_HEIGH;
    public static int GRID_WIDTH;


    public static void main(String[] args) {
        System.out.println("============= DIJKSTRA ==============\n");
        Scanner scan = new Scanner(System.in);
        System.out.println("Tapez 1 pour une grande grille, 2 pour une petite");
        int nextint = scan.nextInt();
        if (nextint == 1)
            IS_BIG_SCREEN = true;
        else if (nextint == 2)
            IS_BIG_SCREEN = false;

        System.out.println("Entrez le nombre de chemins voulus");
        NB_PATHS = scan.nextInt();

        scan.close();

        defineConstants();

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

    public static void defineConstants(){
        NUMBER_CASE_HEIGH = sizeScreen(IS_BIG_SCREEN)[0];
        NUMBER_CASE_WIDTH = sizeScreen(IS_BIG_SCREEN)[0];
        GRID_HEIGH        = NUMBER_CASE_HEIGH * sizeScreen(IS_BIG_SCREEN)[1];
        GRID_WIDTH        = NUMBER_CASE_WIDTH * sizeScreen(IS_BIG_SCREEN)[1];
    }
}
