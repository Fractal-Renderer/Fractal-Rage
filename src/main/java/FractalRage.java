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
        FractalRageWindow mainWindow = new FractalRageWindow();

        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        // add panel(s)

        mainWindow.setVisible(true);
    }
}
