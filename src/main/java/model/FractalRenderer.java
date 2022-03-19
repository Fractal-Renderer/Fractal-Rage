package model;

import lombok.Setter;
import model.data.Bounds;
import model.data.Resolution;
import model.fractals.Fractal;

import java.awt.image.BufferedImage;

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
        double stepSizeH = res.height() / bounds.height();
        double stepSizeW = res.width() / bounds.width();

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.setRGB(i, j, fractal.getColorAt(i * stepSizeW, j * stepSizeH).getRGB());
            }
        }

        return image;
    }
}
