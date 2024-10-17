# Queue-Management-System
Queue Management System is a multithreaded application developed in Java to simulate and manage queues using multithreading techniques. The system models the behavior of N clients arriving for service, entering Q queues, waiting, being served, and leaving the queues. It calculates key performance metrics such as average waiting time, average service time, and peak hours.

Key Features:
- Simulates a queue management system with multiple clients and queues.
- Utilizes Java multithreading for simulating concurrent client arrivals and service operations.
- Calculates important metrics such as:
    - **Average Waiting Time**: The average time a client spends waiting in the queue.
    - **Average Service Time**: The average time it takes to serve a client.
    - **Peak Hours**: The time period with the highest client load.
- Follows the MVC architecture for better organization:
    - **Model**: Handles queue operations, client data, and metric calculations.
    - **View**: User interface developed using Java Spring.
    - **Controller**: Manages interactions between the user interface and the backend logic.
- Multithreading techniques are used to manage concurrent operations efficiently.
- Clean and well-organized user interface for visualizing queue states and results.

Technologies: Java, Multithreading, Spring Framework
