import View.SimulationFrame;
import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simulation Parameters");
        frame.setContentPane(new SimulationFrame());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}










