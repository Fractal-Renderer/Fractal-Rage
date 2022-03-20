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
        FractalRenderer renderer = new FractalRenderer(fractal);

        var bounds = new AtomicReference<>(new Bounds(-2, -2, 4, 4));

        var res = new Resolution(800, 800);

        var fractalComp = new JComponent() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(renderer.render(bounds.get(), res), 0, 0, null);
            }
        };

        fractalComp.addMouseWheelListener(e -> {
            var curBounds = bounds.get();

            var pos = fractalComp.getMousePosition();
            var mX = ((pos.getX() / (double) fractalComp.getWidth()) * curBounds.width()) + curBounds.x();
            var mY = ((pos.getY() / (double) fractalComp.getHeight()) * curBounds.height()) + curBounds.y();

            double width = curBounds.width() + e.getWheelRotation();
            double height = curBounds.height() + e.getWheelRotation();

            double x = mX - (width / 2f);
            double y = mY - (height / 2f);

            bounds.set(new Bounds(x, y, width, height));
            fractalComp.repaint();
            System.out.println(bounds);
        });



        fractalComp.setPreferredSize(new Dimension(res.width(), res.height()));
        return fractalComp;
    }
}
