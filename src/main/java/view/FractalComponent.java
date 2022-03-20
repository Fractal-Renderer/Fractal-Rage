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

        addMouseWheelListener(e -> {
            var cBounds = bounds.get();

            // TODO make independent of hardcoded values
            double scale = e.getWheelRotation() > 0 ? 1.1 : 0.9;

            // mouse pos on screen
            var pos = getMousePosition();

            // TODO make this cleaner
            if (pos == null) {
                return;
            }

            double pX = ((pos.getX() / getWidth()) * cBounds.width()) + cBounds.x();
            double pY = ((pos.getY() / getHeight()) * cBounds.height()) + cBounds.y();

            // new coordinates
            double width = cBounds.width() * scale;
            double height = cBounds.height() * scale;

            double x = pX - (scale * (pX - cBounds.x()));
            double y = pY - (scale * (pY - cBounds.y()));

            bounds.set(new Bounds(x, y, width, height));
            repaint();
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(renderer.render(bounds.get(), resolution), 0, 0, null);
    }

}
