import lombok.extern.slf4j.Slf4j;
import view.FractalRageWindow;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class FractalRage {

    public static void main(String[] args) {
        log.info("Starting application");
        setupWindow();
    }

    private static void setupWindow() {
        FractalRageWindow win = new FractalRageWindow();

        win.setExtendedState(JFrame.MAXIMIZED_BOTH);
        win.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        // add panel(s)

        win.setVisible(true);
    }
}
