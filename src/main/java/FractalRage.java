import lombok.extern.slf4j.Slf4j;
import model.FractalRenderer;
import model.data.Bounds;
import model.data.Resolution;
import model.fractals.Fractal;
import model.fractals.Mandelbrot;
import model.fractals.RandomFractal;
import view.FractalComponent;
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
        final var res = new Resolution(1920, 1080);
        double r = 2.7E-10;
        var bounds = new AtomicReference<>(new Bounds(-0.0452407411 - (r / 2f), 0.9868162204352258 - (r / 2f), 2.7E-10, 2.7E-10));
        return new FractalComponent(fractal, bounds, res, 0.1);
    }
}
