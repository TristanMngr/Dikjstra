package dijkstra.controller.Listener;

import dijkstra.controller.Grid;
import dijkstra.model.Vertex;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomMouseListener implements MouseListener{
    Grid grid;

    public CustomMouseListener(Grid grid) {
        this.grid = grid;
    }

    public void addMouseListener() {
        grid.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int    gridCase[] = grid.getGridCase(e.getY(), e.getX());
        Vertex vertex     = grid.getDijkstra().searchVertexFromCoordinates(gridCase[0], gridCase[1], grid.getDijkstra().getUnvisitedVertexes());
        if (grid.getKeyCode() == KeyEvent.VK_A) {
            if (!grid.isEndNodeChoosen()) {
                grid.getGrid()[gridCase[0]][gridCase[1]] = 1;

                grid.getDijkstra().setStartVertex(vertex);
                grid.getDijkstra().setCurrentVertex(vertex);
                vertex.setShortestPath(0);
                grid.setStartNodeChoosen(true);
            } else {
                grid.setMessage("Start node already present");
            }
        }
        if (grid.getKeyCode() == KeyEvent.VK_Z) {
            grid.getGrid()[gridCase[0]][gridCase[1]] = 2;
            vertex.setWeight(100);
        }
        if (grid.getKeyCode() == KeyEvent.VK_E) {
            if (!grid.isEndNodeChoosen()) {
                grid.getGrid()[gridCase[0]][gridCase[1]] = 3;

                grid.getDijkstra().setEndVertex(vertex);
                grid.setEndNodeChoosen(true);
            } else {
                grid.setMessage("End node already present");
            }
        }
        grid.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
