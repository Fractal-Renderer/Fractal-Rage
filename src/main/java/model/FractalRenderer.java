package model;

import lombok.Setter;

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

        BufferedImage image = new BufferedImage(res.getWidth(), res.getHeight(), BufferedImage.TYPE_INT_RGB);
        double stepSizeH = res.getHeight() / bounds.height();
        double stepSizeW = res.getWidth() / bounds.width();

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.setRGB(i, j, fractal.getColorAt(i * stepSizeW, j * stepSizeH).getRGB());
            }
        }

        return image;
    }
}
