package view;

import lombok.Setter;
import model.FractalRenderer;
import model.data.Bounds;
import model.data.Resolution;
import model.fractals.Fractal;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class FractalComponent extends JComponent {

    @Setter
    private Fractal fractal;

    private final FractalRenderer renderer;

    private final AtomicReference<Bounds> bounds;
    private final Resolution resolution;

    public FractalComponent (Fractal fractal, AtomicReference<Bounds> bounds, Resolution resolution) {
        this.fractal = fractal;
        this.renderer = new FractalRenderer(fractal);
        this.bounds = bounds;
        this.resolution = resolution;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(renderer.render(bounds, resolution), 0, 0, null);
    }

}
