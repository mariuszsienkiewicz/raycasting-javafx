package github.mariuszsienkiewicz.view;

import github.mariuszsienkiewicz.controller.MainController;
import github.mariuszsienkiewicz.model.Bound;
import github.mariuszsienkiewicz.model.Particle;
import github.mariuszsienkiewicz.model.Ray;
import github.mariuszsienkiewicz.model.Vector;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class MainView {

    private final Stage root;
    private GraphicsContext graphicsContext;
    private Scene scene;
    private MainController controller;
    private final double width = 1000;
    private final double height = 1000;

    public MainView(Stage root) {
        this.root = root;
    }

    public void setupView() {
        VBox vBox = new VBox(0);
        Canvas canvas = new Canvas(width, height);

        vBox.getChildren().addAll(canvas);

        this.scene = new Scene(vBox);
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.root.setTitle("Raycasting");
        this.root.setScene(scene);

        addController();
        passOnMouseMovedValuesToController();
    }

    public void addController() {
        this.controller = new MainController(this);
    }

    public void passOnMouseMovedValuesToController() {
        this.scene.setOnMouseMoved(e -> {
            this.controller.mouseMovedEvent(e.getSceneX(), e.getSceneY());
        });
    }

    public void show() {
        this.root.show();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void display() {
        setupView();
        show();
    }

    public void drawBackground() {
        this.graphicsContext.setFill(Color.BLACK);
        this.graphicsContext.fillRect(0, 0, width, height);
    }

    public void drawBounds(Bound[] bounds) {
        for (Bound b : bounds) {
            this.graphicsContext.setStroke(Color.WHITE);
            this.graphicsContext.strokeLine(b.getStartVector().getX(), b.getStartVector().getY(), b.getFinishVector().getX(), b.getFinishVector().getY());
        }
    }

    public void drawParticle(Particle particle) {
        this.graphicsContext.setFill(Color.RED);
        this.graphicsContext.fillOval(particle.getVector().getX() - 5, particle.getVector().getY() - 5, 10, 10);
    }

    public void drawVectorsFromParticle(Particle particle, List<Vector> vectors) {
        this.graphicsContext.setStroke(Color.RED);
        for (Vector vector : vectors) {
            this.graphicsContext.strokeLine(particle.getVector().getX(), particle.getVector().getY(), vector.getX(), vector.getY());
        }
    }

    public void drawRaysFromParticle(Particle particle, List<Ray> rays) {
        this.graphicsContext.setStroke(Color.RED);
        for (Ray ray : rays) {
            this.graphicsContext.strokeLine(
                    particle.getVector().getX(),
                    particle.getVector().getY(),
                    (ray.getDirection().getX()) + ray.getVector().getX(),
                    (ray.getDirection().getY()) + ray.getVector().getY()
            );
        }
    }
}
