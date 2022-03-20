package model.fractals;

import java.awt.*;
import java.util.Random;

public class RandomFractal implements Fractal {
    @Override
    public Color getColorAt (double x, double y) {
        float value = (float) ((x % 2) + (y % 2));
        value = Math.max(Math.min(value, 1), 0);
        return new Color(value, value, value);
    }
}
