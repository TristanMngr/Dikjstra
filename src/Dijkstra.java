import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Dijkstra {
    private List<Vertex>       visitedVertexes;
    private List<Vertex>       unvisitedVertexes;
    private List<Vertex>       copyUnvisitedVertexes;
    private List<List<Vertex>> workingPaths;
    private List<Vertex>       workingPath;
    private Vertex             currentVertex;

    private boolean run;
    private Vertex  endVertex;
    private Vertex  startVertex;

    public Dijkstra() {
        this.visitedVertexes = new ArrayList<>();
        this.unvisitedVertexes = new ArrayList<>();
        this.workingPaths = new ArrayList<>();
    }

    public void run(Grid grid, int nbPaths) {
        this.run = true;
        int k = 0;


        while (k < nbPaths) {

            copyUnvisitedVertexes = new ArrayList<>(unvisitedVertexes);

            while (!unvisitedVertexes.isEmpty()) {

                this.currentVertex = searchVertexShortestPath(unvisitedVertexes);

                if (grid.getGridValue(currentVertex.getPosI(), currentVertex.getPosJ()) != 2) {
                    // 2 => red
                    // 4 => grey
                    grid.setGrid(currentVertex.getPosI(), currentVertex.getPosJ(), 4);
                }

                List<Vertex> neighbourVertexes = currentVertex.searchNeighbourVertexes(this);

                for (int neighbour = 0; neighbour < neighbourVertexes.size(); neighbour++) {
                    neighbourVertexes.get(neighbour).calculateNewShortestPath(currentVertex);
                }

                this.swapUnvisitedToVisitedList(currentVertex);
            }

            System.out.println("Current/Previous :");
            printCurrentPrevious();


            // on reinitialise les unvisitedNode
            this.unvisitedVertexes = new ArrayList<>();
            this.unvisitedVertexes = copyUnvisitedVertexes;

            this.workingPath = new ArrayList<>();
            findPath(endVertex, grid);
            this.workingPaths.add(workingPath);

            for (Vertex vertexCopy : this.unvisitedVertexes) {
                if (!vertexCopy.equals(startVertex)) {
                    vertexCopy.reinit();
                }
            }

            this.visitedVertexes = new ArrayList<>();

            k++;

        }

        this.paint(grid);
        this.run = false;
    }

    public void printCurrentPrevious() {
        Collections.sort(this.visitedVertexes, Comparator.comparingInt(Vertex::getId));
        for (Vertex vertex : this.visitedVertexes) {
            System.out.println("| current => " + vertex.getId() + " previous => " + vertex.getPreviousId() + " path => " + vertex.getShortestPath() + " weight => " + vertex.getWeight() + " ");
        }
        System.out.println("==================");
    }


    public void findPath(Vertex previous, Grid grid) {
        if (previous.getId() == startVertex.getId()) {
            return;
        }
        for (int vertex = 0; vertex < visitedVertexes.size(); vertex++) {
            if (previous.getPreviousId() == visitedVertexes.get(vertex).getId()) {
                this.workingPath.add(visitedVertexes.get(vertex));
                findPath(visitedVertexes.get(vertex), grid);
                break;
            }
        }
        return;
    }


    public void paint(Grid grid) {
        int color = 5;
        for (List<Vertex> list : this.workingPaths) {
            for (Vertex vertex : list) {
                grid.setGrid(vertex.getPosI(), vertex.getPosJ(), color);
            }
            color ++;
        }
        grid.setGrid(startVertex.getPosI(), startVertex.getPosJ(), 1);
        grid.setGrid(endVertex.getPosI(), endVertex.getPosJ(), 3);
    }


    public Vertex searchVertexFromCoordinates(int posI, int posJ, List<Vertex> listVertexes) {
        for (int item = 0; item < listVertexes.size(); item++) {
            if (posI == listVertexes.get(item).getPosI() && posJ == listVertexes.get(item).getPosJ()) {
                return listVertexes.get(item);
            }
        }
        return null;
    }


    // récupère le vertex avec le plus petit shortest path
    public Vertex searchVertexShortestPath(List<Vertex> listVertexes) {
        int    shortestPath = 10000;
        Vertex vertex       = listVertexes.get(0);

        Collections.shuffle(listVertexes);
        for (int item = 0; item < listVertexes.size(); item++) {
            /*if (isPathTaken(listVertexes.get(item))) {
                continue;
            }*/
            if (listVertexes.get(item).getShortestPath() < shortestPath) {
                vertex = listVertexes.get(item);
                shortestPath = listVertexes.get(item).getShortestPath();
            }
        }
        return vertex;
    }

    public void swapUnvisitedToVisitedList(Vertex vertex) {
        int    vertexId = vertex.getId();
        Vertex vertexToMove;

        for (int item = 0; item < this.unvisitedVertexes.size(); item++) {
            vertexToMove = unvisitedVertexes.get(item);
            if (vertexToMove.getId() == vertexId) {
                this.unvisitedVertexes.remove(vertexToMove);
                this.visitedVertexes.add(vertexToMove);
                break;

            }
        }
    }

    public void addUnvisitedVertex(Vertex newVertex) {
        this.unvisitedVertexes.add(newVertex);
    }

    public void setCurrentVertex(Vertex currentVertex) {
        this.currentVertex = currentVertex;
    }

    public List<Vertex> getUnvisitedVertexes() {
        return this.unvisitedVertexes;
    }

    public void setEndVertex(Vertex endVertex) {
        this.endVertex = endVertex;
    }

    public void reinitDijkstra() {
        this.visitedVertexes = new ArrayList<>();
        this.unvisitedVertexes = new ArrayList<>();
        this.startVertex = null;
        this.endVertex = null;
        this.currentVertex = null;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public boolean isRun() {
        return run;
    }
}
