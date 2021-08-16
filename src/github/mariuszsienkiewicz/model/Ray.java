package github.mariuszsienkiewicz.model;

public class Ray {
    private final Vector vector;
    private final Direction direction;

    public Ray(Vector vector, double angle) {
        this.vector = vector;
        this.direction = new Direction(Math.cos(angle), Math.sin(angle));
    }

    public Vector getVector() {
        return vector;
    }

    public Direction getDirection() {
        return direction;
    }

    public Vector casts(Bound b) {
        double bx1 = b.getStartVector().getX();
        double by1 = b.getStartVector().getY();
        double bx2 = b.getFinishVector().getX();
        double by2 = b.getFinishVector().getY();

        double rx3 = this.vector.getX();
        double ry3 = this.vector.getY();
        double rx4 = this.vector.getX() + this.direction.getX();
        double ry4 = this.vector.getY() + this.direction.getY();

        double den = (bx1 - bx2) * (ry3 - ry4) - (by1 - by2) * (rx3 - rx4);
        if (den == 0) {
            return null;
        }

        double t = ((bx1 - rx3) * (ry3 - ry4) - (by1 - ry3) * (rx3 - rx4)) / den;
        double u = -((bx1 - bx2) * (by1 - ry3) - (by1 - by2) * (bx1 - rx3)) / den;

        if (t > 0 && t < 1 && u > 0) {
            double x = bx1 + t * (bx2 - bx1);
            double y = by1 + t * (by2 - by1);

            return new Vector(x, y);
        }

        return null;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "vector=" + vector +
                ", direction=" + direction +
                '}';
    }
}
