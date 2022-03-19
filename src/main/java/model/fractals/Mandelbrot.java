package model.fractals;

import java.awt.*;
import java.util.Random;

public class Mandelbrot implements Fractal {

    private final Random random;

    public Mandelbrot () {
        random = new Random();
    }

    @Override
    public Color getColorAt(double x, double y) {
        var r = random.nextFloat();
        var g = random.nextFloat();
        var b = random.nextFloat();
        return new Color(r, g, b);
    }
}
