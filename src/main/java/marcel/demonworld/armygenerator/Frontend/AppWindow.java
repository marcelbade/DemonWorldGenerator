package marcel.demonworld.armygenerator.Frontend;

import org.springframework.stereotype.Component;

import javax.swing.*;


@Component
public class AppWindow {

    public  void createMainWindow(){

        JFrame f = new JFrame();
        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);

        f.add(b);//adding button in JFrame

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible


    }

}
