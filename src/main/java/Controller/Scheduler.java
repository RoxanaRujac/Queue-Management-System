package Controller;

import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.servers = new ArrayList<>();

        //create all the servers and start a new thread for each
        for (int i = 0; i < maxNoServers; i++) {
            Server server = new Server();
            //add to the list of servers
            servers.add(server);
            //create and start a thread for each
            Thread thread = new Thread(server);
            thread.start();
        }
    }

    public void changeStrategy (SelectionPolicy policy) {
        //strategy corresponding to policy
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }


    public int dispatchTask (Task t) {
        //return the corresponding servers id
        int serverIndex = strategy.addTask(servers, t);
        return serverIndex;
    }

    //getters
    public List<Server> getServers() {
        return servers;
    }

    public int getMaxNoServers() {
        return maxNoServers;
    }

    public int getMaxTasksPerServer() {
        return maxTasksPerServer;
    }

    public Strategy getStrategy() {
        return strategy;
    }

}
