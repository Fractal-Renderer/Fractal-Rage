package model;

import lombok.Setter;
import model.data.Bounds;
import model.data.Resolution;
import model.data.Vector3;
import model.fractals.Fractal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicReference;

public class FractalRenderer {

    @Setter
    private Fractal fractal;

    public FractalRenderer(Fractal fractal) {
        this.fractal = fractal;
    }

    public FractalRenderer() {
        this(null);
    }

    public BufferedImage render(Bounds bounds, Resolution res) {
        if (fractal == null) {
            throw new IllegalStateException("Cannot render null fractal");
        }

        BufferedImage image = new BufferedImage(res.width(), res.height(), BufferedImage.TYPE_INT_RGB);
        double stepSizeH = bounds.height() / res.height();
        double stepSizeW = bounds.width() / res.width();

        long time0 = System.currentTimeMillis();
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.setRGB(i, j, renderPixel(i, j, stepSizeW, stepSizeH, bounds).getRGB());
            }
        }
        long time1 = System.currentTimeMillis();
        System.out.println(time1 - time0);
        return image;
    }

    private Color renderPixel(double x, double y, double sx, double sy, Bounds bounds) {
        //Set this to 2 for SSAA 4x anti-aliasing. Note 4x slower to render
        int AA = 2;

        Vector3 col = new Vector3();

        for(int a=0; a<AA; a++) {
            for (int b = 0; b < AA; b++) {

                Vector3 f = fractal.getColorAt(x * sx + bounds.x(), y * sy + bounds.y());
                col = col.add(new Vector3(f.x, f.y, f.z));

            }
        }

        col = col.div(AA*AA);
        col = col.clamp(0, 1);
        Color color = new Color(col.x, col.y, col.z);

        return color;

    }
}
