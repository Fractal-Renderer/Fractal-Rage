import lombok.extern.slf4j.Slf4j;
import model.FractalRenderer;
import model.data.Bounds;
import model.data.Resolution;
import model.fractals.Fractal;
import model.fractals.Mandelbrot;
import view.FractalRageWindow;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class FractalRage {

    public static void main(String[] args) {
        log.info("Starting application");
        bootstrapApplication();
    }

    private static void bootstrapApplication () {
        FractalRageWindow win = new FractalRageWindow();

        win.setExtendedState(JFrame.MAXIMIZED_BOTH);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        // add panel(s)
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(getFractal(new Mandelbrot()));

        win.add(panel);

        // show window
        win.setVisible(true);
    }

    private static JComponent getFractal (Fractal fractal) {

        final var res = new Resolution(800, 800);
        final FractalRenderer renderer = new FractalRenderer(fractal);

        var bounds = new AtomicReference<>(new Bounds(-2, -2, 4, 4));

        var fractalComp = new JComponent() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(renderer.render(bounds.get(), res), 0, 0, null);
            }
        };

        fractalComp.addMouseWheelListener(e -> {
            var cBounds = bounds.get();

            // TODO make independent of hardcoded values
            double scale = e.getWheelRotation() > 0 ? 1.1 : 0.9;

            // mouse pos on screen
            var pos = fractalComp.getMousePosition();

            // TODO make this cleaner
            if (pos == null) {
                return;
            }

            double pX = ((pos.getX() / fractalComp.getWidth()) * cBounds.width()) + cBounds.x();
            double pY = ((pos.getY() / fractalComp.getHeight()) * cBounds.height()) + cBounds.y();


            // new coordinates
            double width = cBounds.width() * scale;
            double height = cBounds.height() * scale;

            double x = pX - (scale * (pX - cBounds.x()));
            double y = pY - (scale * (pY - cBounds.y()));

            bounds.set(new Bounds(x, y, width, height));
            fractalComp.repaint();
        });

        fractalComp.setPreferredSize(new Dimension(res.width(), res.height()));
        return fractalComp;
    }
}
