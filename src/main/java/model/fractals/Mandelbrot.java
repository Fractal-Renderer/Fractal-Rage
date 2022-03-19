package model.fractals;

import java.awt.*;

public class Mandelbrot implements Fractal {

    public Mandelbrot () {

    }

    @Override
    public Color getColorAt(double x, double y) {
        return getValueAt(x, y);
    }

    private Color getValueAt(double x0, double y0) {
        double x = 0, y = 0, x2 = 0, y2 = 0;

        for (int iteration = 0; iteration < 100; iteration++) {
            if (x2 + y2 > 4) {
                float value = (float) (iteration - (Math.log(Math.log(x2 * x2 + y2 * y2)) - Math.log(Math.log(2))) / Math.log(2));
                return Color.getHSBColor(value / 100f, 1, 1);
            }

            y = 2 * x * y + y0;
            x = x2 - y2 + x0;
            x2 = x * x;
            y2 = y * y;
        }

        return Color.BLACK;
    }
}
