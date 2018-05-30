package dijkstra.controller.Listener;

import dijkstra.controller.Grid;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomKeyListener implements KeyListener {
    Grid grid;

    public void addKeyListener() {
        grid.addKeyListener(this);
    }

    public CustomKeyListener(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        grid.setKeyCode(e.getKeyCode());

        if (grid.getKeyCode() == KeyEvent.VK_A) {
            grid.setMessage("Choose Start Point");
        } else if (grid.getKeyCode() == KeyEvent.VK_Z) {
            grid.setMessage("Choose Node");
        } else if (grid.getKeyCode() == KeyEvent.VK_E) {
            grid.setMessage("Choose End");
        } else if (grid.getKeyCode() == KeyEvent.VK_R) {
            grid.initGrid(grid.getDijkstra());
            grid.setMessage(grid.getStartMessage());
        } else if (grid.getKeyCode() == KeyEvent.VK_S) {
            if (grid.isStartNodeChoosen() && grid.isEndNodeChoosen()) {
                grid.runDjikstra(grid.getDijkstra());
            } else {
                grid.setMessage("Please chose a Start(A key) and End(E key) Node");
            }
        } else if (grid.getKeyCode() == KeyEvent.VK_G) {
            grid.generateRandomGrid();
            grid.setMessage(grid.getStartMessage());
        } else if (grid.getKeyCode() == KeyEvent.VK_C) {
            grid.setRandomColor(grid.generateRandomColor());
        }

        grid.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
