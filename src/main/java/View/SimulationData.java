package View;

import Controller.*;
import Model.Task;
import View.SimulationFrame;
import View.ManagingQueues;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import org.apache.commons.lang3.math.*;

public class SimulationData implements ActionListener {

    private SimulationFrame simulationFrame;
    private Integer numOfTasks;
    private Integer numOfQueues;
    private Integer simulationTime;
    private Integer minArrivalTime;
    private Integer maxArrivalTime;
    private Integer minServiceTime;
    private Integer maxServiceTime;
    private SelectionPolicy policy;
    private List<Task> tasks = new ArrayList<>();
    private JButton GenerateSimulation;

    public SimulationData(SimulationFrame simulationFrame) {
        this.simulationFrame = simulationFrame;
        simulationFrame.getGenerateSimulation().addActionListener(this);
    }

    public void setStrategy(String selectedStrategy) {
        if (selectedStrategy.equals("shortest time")) {
            policy = SelectionPolicy.SHORTEST_TIME;
        } else if (selectedStrategy.equals("shortest queue")) {
            policy = SelectionPolicy.SHORTEST_QUEUE;
        }
    }

    private boolean isValid(String numOfQueues, String numOfTasks, String simulationTime, String minArrivalTime,
                         String maxArrivalTime, String minServiceTime, String maxServiceTime){

        if (numOfTasks.isEmpty() || numOfQueues.isEmpty() || simulationTime.isEmpty() || minArrivalTime.isEmpty() || maxArrivalTime.isEmpty() || minServiceTime.isEmpty() || minServiceTime.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete all the fields", "Empty inputs", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!NumberUtils.isNumber(numOfQueues) || !NumberUtils.isNumber(numOfTasks) || !NumberUtils.isNumber(simulationTime) || !NumberUtils.isNumber(minArrivalTime) || !NumberUtils.isNumber(maxArrivalTime) || !NumberUtils.isNumber(minServiceTime) || !NumberUtils.isNumber(maxServiceTime)) {
            JOptionPane.showMessageDialog(null, "All inputs must be integers", "Invalid inputs", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (Integer.parseInt(minArrivalTime) > Integer.parseInt(maxArrivalTime) || Integer.parseInt(minServiceTime) > Integer.parseInt(minServiceTime)) {
            JOptionPane.showMessageDialog(null, "Intervals aren't valid", "Invalid time stamps", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (Integer.parseInt(minArrivalTime) > Integer.parseInt(simulationTime) || Integer.parseInt(maxArrivalTime) > Integer.parseInt(simulationTime) || Integer.parseInt(minServiceTime) > Integer.parseInt(simulationTime) || Integer.parseInt(maxServiceTime) > Integer.parseInt(simulationTime)) {
            JOptionPane.showMessageDialog(null, "Things can't happen after the simulation time", "Exceeded simulation time", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == simulationFrame.getGenerateSimulation()) {
            String numOfTasks_str = simulationFrame.getNrOfTasks().getText();
            String numOfQueues_str = simulationFrame.getNrOfQueues().getText();
            String simulationTime_str = simulationFrame.getSimulationInterval().getText();
            String minArrivalTime_str = simulationFrame.getMinimArrivalTime().getText();
            String maxArrivalTime_str = simulationFrame.getMaximArrivalTime().getText();
            String minServiceTime_str = simulationFrame.getMinimServiceTime().getText();
            String maxServiceTime_str = simulationFrame.getMaximServiceTime().getText();

            //open new frame with managing queues
            //validate the inputs
            //open manager with inputs
            if(isValid(numOfQueues_str, numOfTasks_str, simulationTime_str, minArrivalTime_str, maxArrivalTime_str, minServiceTime_str, maxServiceTime_str)){
                numOfQueues = Integer.parseInt(simulationFrame.getNrOfQueues().getText());
                numOfTasks = Integer.parseInt(simulationFrame.getNrOfTasks().getText());
                simulationTime = Integer.parseInt(simulationFrame.getSimulationInterval().getText());
                minArrivalTime = Integer.parseInt(simulationFrame.getMinimArrivalTime().getText());
                maxArrivalTime = Integer.parseInt(simulationFrame.getMaximArrivalTime().getText());
                minServiceTime = Integer.parseInt(simulationFrame.getMinimServiceTime().getText());
                maxServiceTime = Integer.parseInt(simulationFrame.getMaximServiceTime().getText());

                String selectedStrategy = (String) simulationFrame.getStrategyType().getSelectedItem();
                setStrategy(selectedStrategy);

                JFrame frame = new JFrame("Managing Queues System");
                frame.setContentPane(new ManagingQueues(this));
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                SimulationManager simulationManager = new SimulationManager(this);

            }
        }
    }

    //getters
    public SimulationFrame getSimulationFrame() {
        return simulationFrame;
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }

    public int getNumOfQueues() {
        return numOfQueues;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public int getMinServiceTime() {
        return minServiceTime;
    }

    public int getMaxServiceTime() {
        return maxServiceTime;
    }

    public SelectionPolicy getSelectionPolicy() { return policy; }

    public List<Task> getTasks() {
        return tasks;
    }

}

