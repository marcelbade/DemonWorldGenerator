package marcel.demonworld.armygenerator.frontEnd;

import marcel.demonworld.armygenerator.frontEnd.table.MainTable;
import marcel.demonworld.armygenerator.frontEnd.table.TableUtilities;
import marcel.demonworld.armygenerator.frontEnd.treeView.ArmyTreeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;


@Component
public class AppWindow {

    @Autowired
    MainTable mainTable;

    @Autowired
    TableUtilities utils;

    @Autowired
    ArmyTreeView treeView;

    public void createMainWindow() throws IllegalAccessException {
/*
        JFrame appWindow = new JFrame();

        //when gui is closed, end program
        appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //fullscreen
        appWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        appWindow.setLayout(null);//using no layout managers
        appWindow.setVisible(true);//making the frame visible


        appWindow.add(mainTable.createMainTable("Zwerge"));*/

        JFrame MainWindow;

        MainWindow = new JFrame();
        String data[][] = utils.createTableData("Zwerge");
        String column[] = utils.getTableAttributeNames();
        JTable completeArmyStatsTable = new JTable(data, column);
        completeArmyStatsTable.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(completeArmyStatsTable);


        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(treeView.createTree("Delfen"));

        MainWindow.add(sp);
        MainWindow.add(contentPane);
        MainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainWindow.setSize(300, 400);
        MainWindow.setVisible(true);

    }

}
