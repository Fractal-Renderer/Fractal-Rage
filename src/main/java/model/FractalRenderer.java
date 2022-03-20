package model;

import lombok.Setter;
import model.data.Bounds;
import model.data.Resolution;
import model.fractals.Fractal;

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

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.setRGB(i, j, fractal.getColorAt(i * stepSizeW + bounds.x(), j * stepSizeH + bounds.y()).getRGB());
            }
        }

        return image;
    }
}
