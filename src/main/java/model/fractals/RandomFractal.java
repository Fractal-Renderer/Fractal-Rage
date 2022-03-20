package model.fractals;

import model.data.Vector3;

import java.awt.*;
import java.util.Random;

public class RandomFractal implements Fractal {
    @Override
    public Vector3 getColorAt (double x, double y) {
        float value = (float) ((x % 2) + (y % 2));
        value = Math.max(Math.min(value, 1), 0);
        return new Vector3(value, value, value);
    }
}
