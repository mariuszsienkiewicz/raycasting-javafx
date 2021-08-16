package github.mariuszsienkiewicz.model;

import java.util.ArrayList;
import java.util.List;

public class Particle {
    private Vector vector;
    private List<Ray> rays;
    private final int FOV = 90;
    private final int precision = 1;

    public Particle(Vector vector) {
        this.vector = vector;
        this.rays = new ArrayList<>();
        for (int i = -(FOV / 2); i < (FOV / 2); i+= precision) {
            this.rays.add(new Ray(vector, Math.toRadians(i)));
        }
    }

    public void update(double x, double y) {
        this.vector.setX(x);
        this.vector.setY(y);
    }

    public List<Ray> getRays() {
        return rays;
    }

    public void setRays(List<Ray> rays) {
        this.rays = rays;
    }

    public Vector getVector() {
        return vector;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
    }
}
