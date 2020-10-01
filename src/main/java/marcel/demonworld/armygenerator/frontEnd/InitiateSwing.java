package marcel.demonworld.armygenerator.frontEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitiateSwing {

    @Autowired
    AppWindow appWindow;

    public void init() {

        // start GUI
        try {
            appWindow.createMainWindow();
        } catch (
                IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
