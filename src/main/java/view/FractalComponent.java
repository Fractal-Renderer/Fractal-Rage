package view;

import lombok.Setter;
import model.FractalRenderer;
import model.data.Bounds;
import model.data.Resolution;
import model.fractals.Fractal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.util.concurrent.atomic.AtomicReference;

public class FractalComponent extends JComponent {

    @Setter
    private Fractal fractal;

    private final FractalRenderer renderer;

    private final AtomicReference<Bounds> bounds;
    private final Resolution resolution;

    public FractalComponent (Fractal fractal, AtomicReference<Bounds> bounds, Resolution resolution, double scaleFactor) {
        this.fractal = fractal;
        this.renderer = new FractalRenderer(fractal);
        this.bounds = bounds;
        this.resolution = resolution;


        var listener = new MouseAdapter() {

            private Point2D.Double prev;

            @Override
            public void mouseDragged (MouseEvent e) {
                var point = fromMouse(e.getPoint());
                if (prev != null) {
                    double difX = prev.getX() - point.getX();
                    double difY = prev.getY() - point.getY();

                    bounds.set(new Bounds(bounds.get().x() + difX / 2, bounds.get().y() + difY / 2, bounds.get().width(), bounds.get().height()));
                    repaint();
                }
                prev = point;
            }

            @Override
            public void mouseReleased (MouseEvent e) {
                prev = null;
            }

            @Override
            public void mouseWheelMoved (MouseWheelEvent e) {
                var cBounds = bounds.get();

                double scale;
                if (e.getWheelRotation() > 0) {
                    scale = 1 + scaleFactor;
                } else {
                    scale = 1 - scaleFactor;
                }

                // mouse pos on screen
                var pos = getMousePosition();

                if (pos != null) {
                    var p = fromMouse(pos);

                    // new coordinates
                    double width = cBounds.width() * scale;
                    double height = cBounds.height() * scale;

                    double x = p.getX() - (scale * (p.getX() - cBounds.x()));
                    double y = p.getY() - (scale * (p.getY() - cBounds.y()));

                    bounds.set(new Bounds(x, y, width, height));
                    repaint();
                }
            }
        };

        addMouseListener(listener);
        addMouseMotionListener(listener);
        addMouseWheelListener(listener);

        setPreferredSize(new Dimension(resolution.width(), resolution.height()));
    }

    private Point2D.Double fromMouse (Point mPoint) {
        var cBounds = bounds.get();
        double pX = ((mPoint.getX() / getWidth()) * cBounds.width()) + cBounds.x();
        double pY = ((mPoint.getY() / getHeight()) * cBounds.height()) + cBounds.y();
        return new Point2D.Double(pX, pY);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(renderer.render(bounds.get(), resolution), 0, 0, null);
    }

}
