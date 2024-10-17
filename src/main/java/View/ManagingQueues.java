package View;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;

public class ManagingQueues extends JPanel{

    private JPanel panel;
    private JPanel timePanel;
    private JPanel queuesPanel;
    private JLabel timeLabel;
    private JLabel waitingClients;
    private JTextField queuesField;
    private JTextField waitingClientsField;
    private ArrayList<JTextField> queues;
    private SimulationData data;
    public ManagingQueues(SimulationData data) {
        this.data = data;
        initializeFrame();
        initializeData();
    }

    private void initializeFrame() {
        setBackground(new Color(171, 150, 253));
        setBorder(BorderFactory.createBevelBorder(1, new Color(131, 47, 211), new Color(131, 47, 211), new Color(131, 47, 211), new Color(131, 47, 211)));
        setPreferredSize(new Dimension(570, 570));
        //createTime();
        //createQueues();
    }

    private void initializeData(){
        queues = new ArrayList<>(data.getNumOfQueues());
    }

    private void createTime() {
        timePanel = new JPanel(new GridLayout(2, 1));
        timeLabel = new JLabel("Current Time: 0");
        timePanel.add(timeLabel);

        waitingClients = new JLabel("Waiting Clients: 0");
        timePanel.add(waitingClients);

        add(timePanel, BorderLayout.NORTH);
    }

    private void createQueues() {
        queuesPanel = new JPanel(new GridLayout(data.getNumOfQueues(), 1));

        for (int i = 0; i < data.getNumOfQueues(); i++) {
            JPanel queueInfoPanel = new JPanel(new GridLayout(2, 1));

            JTextField queueField = new JTextField("Queue " + (i + 1));
            queueField.setEditable(false);
            queuesPanel.add(queueField);

            JTextArea clientsInfo = new JTextArea();
            clientsInfo.setEditable(false);
            queueInfoPanel.add(new JScrollPane(clientsInfo));

            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);
            queueInfoPanel.add(progressBar);

            queuesPanel.add(queueInfoPanel);

            queues.add(queueField);
        }

        add(queuesPanel);
    }

}