package airplane.body;

import util.TaskLogger;

public class AntiCollisionLight {
    private boolean on = false;

    public void blinkThrice() {
        int count = 0;
        while (count < 3) {
            try {
                count++;
                this.on = true;
                TaskLogger.getLogger().info("AntiCollisionLight@" + Integer.toHexString(hashCode()) + " blinking on.");
                Thread.sleep(3000);
                this.on = false;
                TaskLogger.getLogger().info("AntiCollisionLight@" + Integer.toHexString(hashCode()) + " blinking off.");
                if (count >= 3) break;
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
