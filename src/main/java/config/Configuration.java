package config;

import java.util.Random;

public enum Configuration {
    instance;

    public final Random r = new Random();
    public final int taskToRun = 1;
}
