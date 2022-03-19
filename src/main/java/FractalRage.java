import lombok.extern.slf4j.Slf4j;
import model.FractalRenderer;
import model.data.Bounds;
import model.data.Resolution;
import model.fractals.Fractal;
import model.fractals.Mandelbrot;
import view.FractalRageWindow;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.ImageObserver;

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

        var bounds = new Bounds(-2, -2, 4, 4);
        var res = new Resolution(750, 750);

        var fractalComp = new JComponent() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(renderer.render(bounds, res), 0, 0, null);
            }
        };

        fractalComp.setPreferredSize(new Dimension(res.width(), res.height()));
        return fractalComp;
    }
}
