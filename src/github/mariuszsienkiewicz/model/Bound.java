package github.mariuszsienkiewicz.model;

import java.util.Random;

public class Bound {
    private Vector startVector;
    private Vector finishVector;

    public Bound(Vector start, Vector finish) {
        this.startVector = start;
        this.finishVector = finish;
    }

    public Vector getStartVector() {
        return startVector;
    }

    public void setStartVector(Vector startVector) {
        this.startVector = startVector;
    }

    public Vector getFinishVector() {
        return finishVector;
    }

    public void setFinishVector(Vector finishVector) {
        this.finishVector = finishVector;
    }

    public static Bound createRandomBound(double width, double height) {
        Random r = new Random();
        return new Bound(new Vector((double) r.nextInt((int) width) + 0, r.nextInt((int) height)), new Vector(r.nextInt((int) width), r.nextInt((int) height)));
    }

    public static Bound[] createArrayOfBounds(double width, double height, int numberOfBounds) {
        Bound[] bounds = new Bound[numberOfBounds];

        for (int i = 0; i < bounds.length; i++) {
            bounds[i] = createRandomBound(width, height);
        }

        return bounds;
    }
}
