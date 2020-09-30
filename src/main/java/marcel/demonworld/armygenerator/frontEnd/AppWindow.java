package marcel.demonworld.armygenerator.frontEnd;

import org.springframework.stereotype.Component;

import javax.swing.*;


@Component
public class AppWindow {

    public static void createMainWindow() {

        JFrame appWindow = new JFrame();
        appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appWindow.setSize(400, 500);//400 width and 500 height
        appWindow.setLayout(null);//using no layout managers
        appWindow.setVisible(true);//making the frame visible


    }

}
