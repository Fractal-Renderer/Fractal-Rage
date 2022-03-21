package model.fractals;

import model.data.Vector3;

public class Mandelbrot implements Fractal {

    int power = 2;
    int maxIter = 100;
    int threshold = 4;

    public Mandelbrot () {

    }

    @Override
    public Vector3 getColorAt(double x0, double y0) {
        double cr = x0, ci = y0, r = 0.00000000000000000000001, i = 0;
        int iter = 0;
        for (int k = 0; k < maxIter; k++) {
            double argz = Math.atan(i/r);
            double modz = len(r, i);
            r = (Math.pow(modz, power) * Math.cos(argz * power)) + cr;
            i = (Math.pow(modz, power) * Math.sin(argz * power)) + ci;

            if (dot(r, i, r, i) > threshold) {
                k = maxIter;
            } else {
                iter++;
            }
        }

        if(iter < maxIter) {
            //https://iquilezles.org/www/articles/mset_smooth/mset_smooth.htm
            double sn = iter - log2(log2(dot(r, i, r, i))/(log2(threshold)))/log2(power);

            float colx = (float)(0.25f * (1 + Math.cos(3.0f + sn * 0.075f * power + 0.0f)));
            float coly = (float)(0.25f * (1 + Math.cos(3.0f + sn * 0.075f * power + 0.8f)));
            float colz = (float)(0.25f * (1 + Math.cos(3.0f + sn * 0.075f * power + 5.8f)));

            return new Vector3(colx, coly, colz);
        }

        return new Vector3();
    }

    double dot(final double ax, final double ay, final double bx, final double by) {
        return (ax * bx) + (ay * by);
    }

    double clamp(final double val, final double min, final double max) {
        return Math.max(min, Math.min(max, val));
    }

    double len(final double ax, final double ay) {
        return Math.sqrt(ax * ax + ay * ay);
    }

    public double log2(double N) {
        return Math.log(N) / Math.log(2);
    }
}
