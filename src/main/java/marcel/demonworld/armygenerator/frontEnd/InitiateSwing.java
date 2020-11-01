package marcel.demonworld.armygenerator.frontEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class starts Swing UI
 */
@Component
public class InitiateSwing {

    @Autowired
    AppWindow appWindow;

    /**
     *  start UI
     */
    public void init() {
        try {
            appWindow.createMainWindow();
        } catch (
                IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
