package config;

import task.TaskNumber;

import java.util.Random;

public enum Configuration {
    instance;

    public final Random r = new Random();
    public final TaskNumber taskToRun = TaskNumber.TASK8;
}
