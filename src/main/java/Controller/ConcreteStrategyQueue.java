package Controller;

import Model.Server;
import Model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy{
    @Override
    //verify which server has the shortest tasks list (clients in queue)
    //and assign another client to it
    public int addTask(List<Server> servers, Task task) {
        Server smallestQueue = servers.getFirst();
        for (Server server : servers) {
            if (server.getTasks().size() < smallestQueue.getTasks().size()) {
                smallestQueue = server;
            }
        }
        smallestQueue.addTask(task);
        return smallestQueue.getID();
    }
}