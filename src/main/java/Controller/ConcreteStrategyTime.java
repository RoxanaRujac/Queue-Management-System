package Controller;

import Model.Server;
import Model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy{
    @Override

    //verify which server has the shortest overall waiting time
    //and assign another client to it
    public int addTask(List<Server> servers, Task task) {
        Server shortestTime = servers.getFirst();
        for (Server server : servers) {
            if (server.getWaitingPeriod().get() < shortestTime.getWaitingPeriod().get()) {
                shortestTime = server;
            }
        }
        shortestTime.addTask(task);
        return shortestTime.getID();
    }
}

