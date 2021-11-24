package airplane.actors;

import config.Configuration;

public class Passenger {
    private final char[] faceCharPool = {'a', 'c', 'e', 'h', 'i', 'l', 'l', 'n', 'o', 'p', 's'};
    private final char[][] face;

    public Passenger() {
        this.face = getRandomFace();
    }

    private char[][] getRandomFace() {
        char[][] ret = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ret[i][j] = getRandomFaceChar();
            }
        }
        return ret;
    }

    private char getRandomFaceChar() {
        int randomCharIndex = Configuration.instance.r.nextInt(faceCharPool.length);
        return faceCharPool[randomCharIndex];
    }

    public char[][] getFace() {
        return face;
    }
}
