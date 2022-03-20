package model.fractals;

import model.data.Vector3;

import java.awt.*;

public interface Fractal {
    Vector3 getColorAt(double x, double y);
}
