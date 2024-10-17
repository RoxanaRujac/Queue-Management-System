package Controller;

import Model.Logger;
import Model.Server;
import View.SimulationData;
import Model.Task;

import java.util.*;
import java.util.concurrent.BlockingQueue;

public class SimulationManager implements Runnable {

    //data read from UI
    public int timeLimit;
    public int minArrivalTime;
    public int maxArrivalTime;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int numberOfServers;
    public int numberOfClients;
    public SelectionPolicy selectionPolicy;
    public SimulationData data;

    //entity responsible with queue management and client distribution
    private Scheduler scheduler;
    private List<Task> generatedTasks;
    private int peakHour;
    private int peakMax = 0;
    private double averageWaitingTime;
    private double arrivalTimeTotal = 0;
    private double finishTimeTotal = 0;
    private double averageServiceTime;
    private Logger log = new Logger();

    public SimulationManager(SimulationData data) {
        this.data = data;
        initializeData();
        this.scheduler = new Scheduler(numberOfServers, numberOfClients);
        scheduler.changeStrategy(selectionPolicy);
        generateNRandomTasks();

        //start a new thread
        Thread simulationThread = new Thread(this);
        simulationThread.start();
    }

    void initializeData() {
        this.numberOfClients = data.getNumOfTasks();
        this.numberOfServers = data.getNumOfQueues();
        this.timeLimit = data.getSimulationTime();
        this.minArrivalTime = data.getMinArrivalTime();
        this.maxArrivalTime = data.getMaxArrivalTime();
        this.minProcessingTime = data.getMinServiceTime();
        this.maxProcessingTime = data.getMaxServiceTime();
        this.selectionPolicy = data.getSelectionPolicy();
    }

    private void generateNRandomTasks() {
        generatedTasks = new ArrayList<>();
        for (int i = 0; i < numberOfClients; i++) {
            //generate a random client with the id = i
            Random random = new Random();
            int arrivalTime = random.nextInt(minArrivalTime, maxArrivalTime);
            int serviceTime = random.nextInt(minProcessingTime, maxProcessingTime);
            Task newTask = new Task(i, arrivalTime, serviceTime);

            //add it to the list of tasks
            generatedTasks.add(newTask);
            //add the service time for each client
            averageServiceTime += serviceTime;
            //add the arrival time for each client
            arrivalTimeTotal += arrivalTime;
        }

        //sort them by arrival time
        generatedTasks.sort(Comparator.comparingInt(Task::getArrivalTime));
        averageServiceTime /= numberOfClients;
    }

    public void peak(int time) {
        int aux = 0;
        for (Server server : scheduler.getServers()){
            aux += server.getTasks().size();
        }
        if (aux > peakMax) {
            peakMax = aux;
            peakHour = time;
        }
    }


    @Override
    public void run() {
        Logger.log("-------------------------Generated clients:-----------------------------\n");
        for (Task task : generatedTasks) {
            Logger.log("id: " + task.getID() + " arrival time: " + task.getArrivalTime() + " service time: " + task.getServiceTime());
        }
        Logger.log("-------------------------------------------------------------------------");

        int currentTime = 0;

        List<Task> waitingClients = new ArrayList<>(generatedTasks);

        while (currentTime <= timeLimit) {
            Logger.log("\nTime: " + currentTime);
            Logger.log("Waiting clients: ");
            for (Task task : waitingClients) {
                if (task.getArrivalTime() <= currentTime) {
                    // if the current client has arrived -> add to a servers queue
                    if (task.getArrivalTime() == currentTime) {
                        // if he s already in a queue
                        boolean alreadyInQueue = scheduler.getServers().stream().anyMatch(server -> server.getTasks().contains(task));
                        if (!alreadyInQueue) {
                            int serverIndex = scheduler.dispatchTask(task);
                            Logger.log("(" + task.getID() + " " + task.getArrivalTime() + " " + task.getServiceTime() + ")");
                        }
                    }
                }
            }

            // iterate through the clients and remove the ones with servicetime = 0
            for (Server server : scheduler.getServers()) {
                BlockingQueue<Task> tasks = server.getTasks();
                Iterator<Task> taskIterator = tasks.iterator();
                while (taskIterator.hasNext()) {
                    Task task = taskIterator.next();
                    if (task.getServiceTime() == 0) {
                        taskIterator.remove();
                        //add the finish time for each client
                        finishTimeTotal += currentTime;
                    }
                }
            }

            //print the queues content
            for (int i = 0; i < numberOfServers; i++) {
                Server server = scheduler.getServers().get(i);
                Logger.log("Queue " + (i + 1) + ": " + server.getQueueStatus());
            }

            peak(currentTime);
            currentTime++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        averageWaitingTime = (finishTimeTotal - arrivalTimeTotal) / numberOfClients;
        Logger.log("\n-------------------------Statistics------------------------------\n");
        Logger.log("Average service time : " + averageServiceTime);
        Logger.log("Average waiting time : " + averageWaitingTime);
        Logger.log("Peek hour: " + peakHour);

    }
}


