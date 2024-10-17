package Model;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private static AtomicInteger ID = new AtomicInteger(0);

    public Server()
    {
        this.tasks = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger();
        this.ID.incrementAndGet();
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        // add service time for each new task in waiting period
        this.waitingPeriod.addAndGet(newTask.getServiceTime());
    }


    @Override
    public void run() {
        while(true)
        {
            if (!tasks.isEmpty()) {
                try {
                    //take the first client from the queue
                    Task newTask = tasks.peek();
                    if (newTask != null) {
                        int serviceTime = newTask.getServiceTime();
                        //decrement its serviceTime
                        for (int i = 0; i < serviceTime; i++) {
                            Thread.sleep(1000);
                            newTask.decrementServiceTime();
                        }
                    }
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
            }
        }
    }

    public String getQueueStatus() {
        if (tasks.isEmpty()) {
            return "closed";
        } else {
            StringBuilder builder = new StringBuilder();
            for (Task task : tasks) {
                builder.append("(")
                        .append(task.getID()).append(", ")
                        .append(task.getArrivalTime()).append(", ")
                        .append(task.getServiceTime()).append("), ");
            }
            // delete the last comma and space
            if (builder.length() > 2) {
                builder.setLength(builder.length() - 2);
            }
            return builder.toString();
        }
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public int getID() { return ID.get(); };

}
