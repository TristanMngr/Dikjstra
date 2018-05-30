package dijkstra.view;

import dijkstra.Main;
import dijkstra.controller.Grid;

import java.awt.*;

public class Paint {
    public static final Color COLOR_GREY      = new Color(240, 240, 240);
    public static final Color COLOR_GREEN     = new Color(0, 100, 0);
    public static final Color COLOR_RED       = new Color(170, 0, 0);
    public static final Color COLOR_BLUE      = new Color(0, 100, 150);
    public static final Color COLOR_DARK_GREY = Color.DARK_GRAY;

    Grid grid;


    public Paint(Grid grid) {
        this.grid = grid;
    }

    /**
     * Override method to paint the map and the path
     *
     * @param g
     */
    public void paintGrid(Graphics g) {
        for (int i = 0; i < Main.NUMBER_CASE_HEIGH; i++) {
            for (int j = 0; j < Main.NUMBER_CASE_WIDTH; j++) {
                g.setColor(Color.BLACK);
                g.drawRect(j * grid.getSizeCaseWidth(), i * grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh());

                // GREY => map
                drawRectangle(g, i , j, 0, COLOR_GREY);

                // GREEN => start node
                drawRectangle(g, i , j, 1, COLOR_GREEN);

                // RED => wall
                drawRectangle(g, i , j, 2, COLOR_RED);

                // BLUE => end node
                drawRectangle(g, i , j, 3, COLOR_BLUE);

                // DARK GREY
                drawRectangle(g, i , j, 4, COLOR_DARK_GREY);

                int nbCase = 4;
                for (int item = 0; item < grid.getNbPaths(); item++) {
                    nbCase++;

                    if (grid.getGrid()[i][j] == nbCase) {
                        g.setColor(grid.getRandomColor()[item]);
                        g.fillRect(j * grid.getSizeCaseWidth(), i * grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh());
                    }
                }
                g.setColor(Color.BLACK);
                g.setFont(new Font("Sans Serif", Font.BOLD, 12));
                g.drawString(grid.getMessage(), 25, 25);
            }
        }
    }

    public void drawRectangle(Graphics g, int posX, int posY, int caseProperty, Color color) {
        if (grid.getGrid()[posX][posY] == caseProperty) {
            g.setColor(color);
            g.fillRect(posY * grid.getSizeCaseWidth(), posX * grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh());
        }
    }
}
