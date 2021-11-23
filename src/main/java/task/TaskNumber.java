package task;

public enum TaskNumber {
    TASK1, TASK2, TASK3, TASK4, TASK5, TASK6, TASK7, TASK8, TASK9;

    public int getNumber() {
        return this.ordinal() + 1;
    }
}
