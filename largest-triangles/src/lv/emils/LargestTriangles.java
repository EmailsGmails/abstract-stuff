package lv.emils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LargestTriangles {

    static int n, m, xTreshold, yTreshold;

    public static void main(String[] args) {
        n = 100;
        m = 100;
        xTreshold = n * 3/4;
        yTreshold = m * 3/4;
        double desiredPerimeter = tresholdPerimeter(xTreshold, yTreshold);

        List<Point> pointsTopLeft = new ArrayList<>();
        List<Point> pointsTopRight = new ArrayList<>();
        List<Point> pointsBottomRight = new ArrayList<>();
        List<Point> pointsBottomLeft = new ArrayList<>();

        for (int y = 1; y <= m/2; y++) {;
            for (int x = -n/2; x < 0; x++) {
                pointsTopLeft.add(new Point(x, y));
            }
        }
        for (int y = 1; y <= m/2; y++) {;
            for (int x = 1; x <= n/2; x++) {
                pointsTopRight.add(new Point(x, y));
            }
        }
        for (int y = -m/2; y < 0; y++) {;
            for (int x = 1; x <= n/2; x++) {
                pointsBottomRight.add(new Point(x, y));
            }
        }
        for (int y = -m/2; y < 0; y++) {;
            for (int x = -n/2; x < 0; x++) {
                pointsBottomLeft.add(new Point(x, y));
            }
        }

        List<List<Point>> allPoints = new ArrayList<>();
        allPoints.add(pointsTopLeft);
        allPoints.add(pointsTopRight);
        allPoints.add(pointsBottomRight);
        allPoints.add(pointsBottomLeft);

        Random rand = new Random();
        double perimeter = 0;

        while (perimeter < desiredPerimeter) {
            int aRand = rand.nextInt(allPoints.size());
            int bRand = rand.nextInt(allPoints.size());
            int cRand = rand.nextInt(allPoints.size());
            List<Point> aQuadrant = allPoints.get(aRand);

            while (aRand == bRand) {
                bRand = rand.nextInt(allPoints.size());
            }
            List<Point> bQuadrant = allPoints.get(bRand);

            while (aRand == cRand || bRand == cRand) {
                cRand = rand.nextInt(allPoints.size());
            }
            List<Point> cQuadrant = allPoints.get(cRand);

            int aX = 0, aY = 0, bX = 0, bY = 0, cX = 0, cY = 0;

            while (!(aX >= xTreshold/2 || aX <= -xTreshold/2)) {
                aX = aQuadrant.get(rand.nextInt(aQuadrant.size())).getX();
            }
            while (!(aY >= yTreshold/2 || aY <= -yTreshold/2)) {
                aY = aQuadrant.get(rand.nextInt(aQuadrant.size())).getY();
            }
            Point a = new Point(aX, aY);

            while (!(bX >= xTreshold/2 || bX <= -xTreshold/2)) {
                bX = bQuadrant.get(rand.nextInt(bQuadrant.size())).getX();
            }
            while (!(bY >= yTreshold/2 || bY <= -yTreshold/2)) {
                bY = bQuadrant.get(rand.nextInt(bQuadrant.size())).getY();
            }
            Point b = new Point(bX, bY);

            while (!(cX >= xTreshold/2 || cX <= -xTreshold/2)) {
                cX = cQuadrant.get(rand.nextInt(cQuadrant.size())).getX();
            }
            while (!(cY >= yTreshold/2 || cY <= -yTreshold/2)) {
                cY = cQuadrant.get(rand.nextInt(cQuadrant.size())).getY();
            }
            Point c = new Point(cX, cY);

            perimeter = getPerimeter(a, b, c);

            if (perimeter >= desiredPerimeter) {
                System.out.println("Longest perimeter found: " + perimeter);
                System.out.println("A = " + a + " B = " + b + " C = " + c);
                break;
            }
        }
    }

    public static double tresholdPerimeter(int xTreshold, int yTreshold) {
        double aEdge, bEdge, cEdge;
        double perimeter;
        aEdge = xTreshold;
        bEdge = yTreshold;
        cEdge = Math.sqrt(Math.pow(aEdge, 2.0) + Math.pow(bEdge, 2.0) );
        perimeter = aEdge + bEdge + cEdge;
        System.out.println("Treshold perimeter = " + perimeter);
        return perimeter;
    }

    public static double getPerimeter(Point a, Point b, Point c) {
        double aEdge, bEdge, cEdge;
        double perimeter;
        aEdge = Math.sqrt(Math.pow((double)(b.getX()) - a.getX(), 2.0)
                + Math.pow((double)b.getY() - a.getY(), 2.0) );
        bEdge = Math.sqrt(Math.pow((double)c.getX() - b.getX(), 2.0)
                + Math.pow((double)c.getY() - b.getY(), 2.0) );
        cEdge = Math.sqrt(Math.pow((double)(a.getX() - c.getX()), 2.0)
                + Math.pow((double)a.getY() - c.getY(), 2.0) );
        perimeter = aEdge + bEdge + cEdge;
        return perimeter;
    }
}

class Point {
    private int x;
    private int y;
    public Point(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}