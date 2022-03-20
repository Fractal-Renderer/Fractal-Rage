import lombok.extern.slf4j.Slf4j;
import model.FractalRenderer;
import model.data.Bounds;
import model.data.Resolution;
import model.fractals.Fractal;
import model.fractals.Mandelbrot;
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
        final var res = new Resolution(800, 800);
        var bounds = new AtomicReference<>(new Bounds(-2, -2, 4, 4));
        return new FractalComponent(fractal, bounds, res, 0.1);
    }
}
