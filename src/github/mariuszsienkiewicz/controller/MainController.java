package github.mariuszsienkiewicz.controller;

import github.mariuszsienkiewicz.model.Bound;
import github.mariuszsienkiewicz.model.Particle;
import github.mariuszsienkiewicz.model.Ray;
import github.mariuszsienkiewicz.model.Vector;
import github.mariuszsienkiewicz.view.MainView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private MainView view;
    private Bound[] bounds;
    private Particle particle;
    private List<Vector> closestVectors = new ArrayList<>();
    private List<Ray> rays = new ArrayList<>();

    public MainController(MainView view) {
        this.view = view;

        createParticle();
        createBounds();
        createTimeline();
    }

    public void createTimeline() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), e->update()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void update() {
        findClosestVectorsAndRays();

        updateView();
    }

    public void updateView() {
        this.view.drawBackground();
        this.view.drawBounds(this.bounds);
        this.view.drawParticle(this.particle);
        this.view.drawVectorsFromParticle(this.particle, this.closestVectors);
        this.view.drawRaysFromParticle(this.particle, this.rays);
    }

    public void createBounds() {
        this.bounds = Bound.createArrayOfBounds(this.view.getWidth(), this.view.getHeight(), 10);
    }

    public void createParticle() {
        this.particle = new Particle(new Vector(this.view.getWidth() / 2, this.view.getHeight() / 2));
    }

    public void mouseMovedEvent(double mousePositionX, double mousePositionY) {
        this.particle.update(mousePositionX, mousePositionY);
    }

    public void findClosestVectorsAndRays() {
        this.closestVectors.clear();
        this.rays.clear();

        for (Ray ray : particle.getRays()) {
            Vector closestVector = findClosestVector(ray);

            if (closestVector != null) {
                closestVectors.add(closestVector);
            } else {
                rays.add(ray);
            }
        }
    }

    public Vector findClosestVector(Ray ray) {
        Vector closestVector = null;
        double closestDistance = Double.POSITIVE_INFINITY;

        for (Bound b : bounds) {
            Vector vector = ray.casts(b);

            if (vector != null) {
                double distance = Math.hypot(this.particle.getVector().getX() - vector.getX(), this.particle.getVector().getY() - vector.getY());
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestVector = vector;
                }
            }
        }

        return closestVector;
    }
}
