import javax.swing.*;


public class Main {
    public final static int MENU_WIDTH        = 100;
    public final static int NUMBER_CASE_HEIGH = 60;
    public final static int NUMBER_CASE_WIDTH = 60;
    public final static int GRID_HEIGH        = NUMBER_CASE_HEIGH * 10;
    public final static int GRID_WIDTH        = NUMBER_CASE_WIDTH * 10;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rectangle");
        Grid   grid  = new Grid();
        /*JPanel menu = new JPanel();*/

        /*GridLayout mainLayout = new GridLayout(0,2);*/


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(GRID_WIDTH, GRID_HEIGH + 22);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        /*frame.setLayout(mainLayout);*/

        /*Dimension dimensionGrid = new Dimension(GRID_WIDTH, GRID_HEIGH);
        grid.setPreferredSize(dimensionGrid);*/
        grid.setSize(GRID_WIDTH, GRID_HEIGH);
        frame.add(grid);
        /*frame.getContentPane().add(grid);
        frame.getContentPane().add(menu);*/


/*
        menu.setSize(MENU_WIDTH,GRID_HEIGH);
        frame.add(menu);*/

    }

}
