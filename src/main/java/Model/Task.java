package Model;

public class Task implements Comparable<Task>{
    private int ID;
    private int arrivalTime;
    private int serviceTime;

    public Task(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public void decrementServiceTime() {
        if (serviceTime > 0) {
            serviceTime--;
        }
    }

    @Override
    public int compareTo(Task otherTask) {
        return Integer.compare(this.arrivalTime, otherTask.arrivalTime);
    }

    //getters
    public int getID() {
        return ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

}
