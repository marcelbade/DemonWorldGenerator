package marcel.demonworld.armygenerator;

import marcel.demonworld.armygenerator.Frontend.AppWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class ArmyBuilderApplication {

    @Autowired
    static AppWindow appWindow;

    public static void main(String[] args) {
        SpringApplication.run(ArmyBuilderApplication.class, args);

        appWindow.createMainWindow();
    }


}
