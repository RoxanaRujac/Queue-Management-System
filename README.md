# Queue-Management-System

**Technologies**: Java, Multithreading, Spring Framework

## 📝 Table of Contents
1. [Project Overview](#project-overview)
2. [Objective](#objective)
3. [Functional Requirements](#functional-requirements)
4. [Architecture](#architecture)
5. [Getting Started](#getting-started)
6. [Usage](#usage)
7. [Results](#results)
8. [Conclusions](#conclusions)
9. [Future Improvements](#future-improvements)

## Project Overview
This Java-based application simulates a **queue management system** where multiple clients arrive, wait in multiple queues, are served, and eventually leave. The system utilizes **multithreading** to handle client processing concurrently. The application computes the **average wait time**, **average service time**, and **peak service hours**.

![Queue Management System](https://github.com/user-attachments/assets/e71c5518-4604-47cb-846f-6d4b8133c830)

## Objective
The main objective of this project is to design and implement a queue management system using **multithreading** techniques. It simulates client arrivals and service in a queue, tracks processing times, and computes performance metrics.

## Functional Requirements

### 1. Queue Simulation:
- Simulate clients arriving and being served concurrently using multiple threads.
- Handle queue assignments based on user-defined strategies.

### 2. Error Handling:
- Validate input fields to ensure correct data is entered (e.g., no empty fields, numeric values where required).
- Handle invalid input gracefully.

![Error Handling](https://github.com/user-attachments/assets/046b0078-0653-4af3-bd71-e4becd99d1c6)

### 3. Export Results:
- Export simulation results to a `.txt` file for review.

### 4. Non-Functional Requirements:
- The program must be **easy to use** and **intuitive**.
- Provide a **simple** and **compact design**.
- Ensure **quick response times** using multithreading.
- The system should run without errors or exceptions.

![System Design](https://github.com/user-attachments/assets/ae67c698-ab84-476e-bc1f-448d5d9873bf)

## Architecture

The project is designed using the **MVC (Model-View-Controller)** architecture, ensuring separation of concerns:

### Model
- **Task Class**: Represents a client with attributes such as arrival time, service time, and remaining service time.
- **Server Class**: Implements `Runnable` for handling the task queue using multithreading.
- **Logger Class**: Logs the results of the simulation to a text file.

### View
- **SimulationFrame Class**: The main graphical interface where the user can configure simulation parameters (number of clients, queues, service times, etc.).
- **SimulationData Class**: Manages user inputs and validates the data.
- **ManagingQueues Class**: Displays real-time updates of queues during simulation.

### Controller
- **SimulationManager Class**: Manages simulation logic, including task generation, queue management, and logging results.
- **Scheduler Class**: Handles task assignment to queues based on user-selected strategies.
- **Strategy Interface and Concrete Implementations**: Selects the optimal queue management strategy (e.g., shortest wait time or shortest queue).

 
## Getting Started

### Prerequisites
- Java Development Kit (JDK) version 8 or higher.
- An IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor for editing Java files.
- MySQL (for any potential database integration).


### Usage

1. Launch the application, and a graphical interface will appear.
2. Input the following parameters:
   - **Number of clients**
   - **Number of queues**
   - **Maximum simulation time**
   - **Minimum and maximum arrival times**
   - **Minimum and maximum service times**
   - **Queue assignment strategy** (choose from available options)
3. Click **Generate Simulation** to start the simulation.
4. The simulation will run, displaying real-time updates of client queues.

After starting the simulation, the system will display queue processing in real-time and log the results to a `.txt` file.

### Results

The application generates the following performance metrics:
- **Average Waiting Time**: The average time a client waits before being served.
- **Average Service Time**: The average time taken to serve a client.
- **Peak Hours**: The time period with the most clients in the system.

### Conclusions

This project allowed me to explore advanced concepts in Java, such as multithreading, concurrency, and file I/O. The application efficiently simulates a queue system, offering insights into real-time queue management and performance metrics.

#### Future improvements could include:
- Enhanced graphical user interface (GUI).
- Priority-based client handling.
- More efficient algorithms for queue assignment.
