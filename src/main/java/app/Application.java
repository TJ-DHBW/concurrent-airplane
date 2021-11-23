package app;

import config.Configuration;
import task.*;

public class Application {

    public static void main(String[] args) {
        switch (Configuration.instance.taskToRun) {
            case TASK1:
                Task1.run();
                break;
            case TASK2:
                Task2.run();
                break;
            case TASK3:
                Task3.run();
                break;
            case TASK4:
                Task4.run();
                break;
            case TASK5:
                Task5.run();
                break;
            case TASK6:
                Task6.run();
                break;
            case TASK7:
                Task7.run();
                break;
            case TASK8:
                Task8.run();
                break;
            case TASK9:
                Task9.run();
        }
    }
}
