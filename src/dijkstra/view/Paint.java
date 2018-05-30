package dijkstra.view;

import dijkstra.Main;
import dijkstra.controller.Grid;
import java.awt.*;

public class Paint {
    Grid grid;


    public Paint(Grid grid) {
        this.grid = grid;
    }

    /**
     * Override method to paint the map and the path
     * @param g
     */
    public void paintGrid(Graphics g) {
        for (int i = 0; i < Main.NUMBER_CASE_HEIGH; i++) {
            for (int j = 0; j < Main.NUMBER_CASE_WIDTH; j++) {
                g.setColor(Color.BLACK);
                g.drawRect(j * grid.getSizeCaseWidth(), i * grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh());

                // GREY
                if (grid.getGrid()[i][j] == 0) {
                    g.setColor(new Color(240, 240, 240));
                    g.fillRect(j * grid.getSizeCaseWidth(), i * grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh());
                }

                // GREEN
                if (grid.getGrid()[i][j] == 1) {
                    g.setColor(new Color(0, 100, 0));
                    g.fillRect(j * grid.getSizeCaseWidth(), i * grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh());
                }

                // RED
                if (grid.getGrid()[i][j] == 2) {
                    g.setColor(new Color(170, 0, 0));
                    g.fillRect(j * grid.getSizeCaseWidth(), i * grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh());
                }

                // BLUE
                if (grid.getGrid()[i][j] == 3) {
                    g.setColor(new Color(0, 100, 150));
                    g.fillRect(j * grid.getSizeCaseWidth(), i * grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh());
                }

                // DARK GREY
                if (grid.getGrid()[i][j] == 4) {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(j * grid.getSizeCaseWidth(), i * grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh(), grid.getSizeCaseHeigh());
                }

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
}
