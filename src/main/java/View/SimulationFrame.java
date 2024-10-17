package View;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SimulationFrame extends JPanel{

         //data inputs
         JTextField nrOfQueues;
        JTextField nrOfTasks;
        JTextField simulationInterval;
        JTextField minimArrivaltime;
        JTextField maximArrivaltime;
        JTextField minimServicetime;
        JTextField maximServicetime;
        String[] options = {"shortest time", "shortest queue"};
        JComboBox<String> strategyType = new JComboBox<>(options);

        //fields labels
        JLabel queues;
        JLabel clients;
        JLabel interval;
        JLabel minArrival;
        JLabel maxArrival;
        JLabel minService;
        JLabel maxService;
        JLabel strategy;
        public JButton GenerateSimulation;
        SimulationData simulationData;

    public SimulationFrame() {
        setLayout();
        createElements();
        addToPanel();
        simulationData = new SimulationData(this);
    }

    private void setLayout() {
        //create
        setLayout(null);
        setBackground(new Color(171, 150, 253));
        setBorder(BorderFactory.createBevelBorder(1, new Color(131, 47, 211), new Color(131, 47, 211), new Color(131, 47, 211), new Color(131, 47, 211)));
        setPreferredSize(new Dimension(570, 570));
    }

    private void createElements() {
        createTextFields();
        createLabels();
        createButtons();
    }

    private void createTextFields() {
        //create
        nrOfTasks = new JTextField();
        nrOfQueues = new JTextField();
        simulationInterval = new JTextField();
        maximArrivaltime = new JTextField();
        minimArrivaltime = new JTextField();
        maximServicetime = new JTextField();
        minimServicetime = new JTextField();

        //color
        nrOfTasks.setBackground(new Color(207, 182, 255));
        nrOfQueues.setBackground(new Color(207, 182, 255));
        simulationInterval.setBackground(new Color(207, 182, 255));
        minimArrivaltime.setBackground(new Color(207, 182, 255));
        maximArrivaltime.setBackground(new Color(207, 182, 255));
        minimServicetime.setBackground(new Color(207, 182, 255));
        maximServicetime.setBackground(new Color(207, 182, 255));
        strategyType.setBackground(new Color(207, 182, 255));
    }


    private void createLabels() {
        Font font = new Font("Arial", Font.BOLD, 12);

        JLabel titleLabel = new JLabel("Simulation input:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setForeground(new Color(42, 42, 49));
        add(titleLabel);
        titleLabel.setBounds(180, 20, 300, 30);

        queues = new JLabel("number of queues  = ");
        queues.setFont(font);
        queues.setForeground(new Color(0x2A2A31));

        clients = new JLabel("number of clients  = ");
        queues.setFont(font);
        queues.setForeground(new Color(0x2A2A31));

        interval = new JLabel("simulation interval  = ");
        queues.setFont(font);
        queues.setForeground(new Color(0x2A2A31));

        minService = new JLabel("minimum service time  = ");
        queues.setFont(font);
        queues.setForeground(new Color(0x2A2A31));

        maxService = new JLabel("maximum service time  = ");
        queues.setFont(font);
        queues.setForeground(new Color(0x2A2A31));

        minArrival = new JLabel("minimum arrival time  = ");
        queues.setFont(font);
        queues.setForeground(new Color(0x2A2A31));

        maxArrival = new JLabel("maximum arrival time  = ");
        queues.setFont(font);
        queues.setForeground(new Color(0x2A2A31));

        strategy = new JLabel("strategy type  = ");
        strategy.setFont(font);
        strategy.setForeground(new Color(0x2A2A31));
    }

    private void createButtons() {
        Font font = new Font("Arial", Font.BOLD, 12);
       GenerateSimulation = new JButton ("generate simulation");
       GenerateSimulation.setFont(font);
       GenerateSimulation.setBackground(new Color(160, 126, 206));
       GenerateSimulation.setForeground(new Color(0x2A2A31));
    }

    private void addToPanel() {
        //add
        add(nrOfQueues);
        add(nrOfTasks);
        add(simulationInterval);
        add(minimArrivaltime);
        add(maximArrivaltime);
        add(minimServicetime);
        add(maximServicetime);
        add(strategyType);

        add(queues);
        add(clients);
        add(interval);
        add(minArrival);
        add(maxArrival);
        add(minService);
        add(maxService);
        add(strategy);

        add(GenerateSimulation);

        setCoordinates();
    }

    private void setCoordinates(){
        //coordinates
        nrOfQueues.setBounds(230, 80, 200, 30);
        nrOfTasks.setBounds(230, 130, 200, 30);
        simulationInterval.setBounds(230, 180, 200, 30 );
        minimArrivaltime.setBounds(230, 230, 200, 30 );
        maximArrivaltime.setBounds(230, 280, 200, 30 );
        minimServicetime.setBounds(230, 330, 200, 30 );
        maximServicetime.setBounds(230, 380, 200, 30 );
        strategyType.setBounds(230, 430, 200, 30);

        queues.setBounds(100, 70, 200, 50);
        clients.setBounds(105, 120, 200, 50);
        interval.setBounds(100, 170, 200, 50);
        minArrival.setBounds(80, 220, 200, 50);
        maxArrival.setBounds(75, 270, 200, 50);
        minService.setBounds(75, 320, 200, 50);
        maxService.setBounds(75, 370, 200, 50);
        strategy.setBounds(130, 420, 200, 50);

        GenerateSimulation.setBounds(220, 490, 150, 50);
    }



    //geters
    public JTextField getNrOfQueues() {
        return nrOfQueues;
    }

    public JTextField getNrOfTasks() {
        return nrOfTasks;
    }

    public JTextField getSimulationInterval() {
        return simulationInterval;
    }

    public JTextField getMinimArrivalTime() {
        return minimArrivaltime;
    }

    public JTextField getMaximArrivalTime() {
        return maximArrivaltime;
    }

    public JTextField getMinimServiceTime() {
        return minimServicetime;
    }

    public JTextField getMaximServiceTime() {
        return maximServicetime;
    }

    public JButton getGenerateSimulation() {
        return GenerateSimulation;
    }

    public String[] getOptions() {
        return options;
    }

    public JComboBox<String> getStrategyType() {
        return strategyType;
    }


}
