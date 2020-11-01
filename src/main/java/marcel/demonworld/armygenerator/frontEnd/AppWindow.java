package marcel.demonworld.armygenerator.frontEnd;

import marcel.demonworld.armygenerator.frontEnd.ArmySelector.ArmySelection;
import marcel.demonworld.armygenerator.frontEnd.ArmySelector.DropDownMenu;
import marcel.demonworld.armygenerator.frontEnd.table.MainTable;
import marcel.demonworld.armygenerator.frontEnd.table.TableUtilities;
import marcel.demonworld.armygenerator.frontEnd.treeView.ArmyTreeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

/**
 * main class for the UI. all components are injected here.
 */
@Component
public class AppWindow {

    @Autowired
    MainTable mainTable;

    @Autowired
    TableUtilities utils;

    @Autowired
    DropDownMenu dropDownMenu;

    @Autowired
    ArmySelection armySelection;

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

        //table
        String[][] tableData = utils.createTableData(armySelection.getSelectedArmy());
        String[] columnNames = utils.getUnitStatNames();
        JTable completeArmyStatsTable = new JTable(tableData, columnNames);
        completeArmyStatsTable.setBounds(30, 40, 200, 300);
        JScrollPane tableScrollPane = new JScrollPane(completeArmyStatsTable);


        //Reset the java logo in the top left corner with something better
        //TODO: not working, doesnt find file
        ImageIcon windowIcon = new ImageIcon("/resources/Demonworld_Logo.png");

        //  ==== Panels=====

        // dropDown Panel
        JPanel dropDownPanel = new JPanel();
        dropDownPanel.add(dropDownMenu.createDropDownMenuForArmyNames());
        dropDownPanel.setBounds(50, 50, 90, 20);

        //TreeView Panel
        JPanel treePane = new JPanel();
        treePane.add(treeView.createTree(armySelection.getSelectedArmy()));
        treePane.setBounds(150, 150, 390, 320);

        //table Panel
        JPanel tablePane = new JPanel();
        tablePane.add(tableScrollPane);
        tablePane.setBounds(50, 50, 90, 20);


        // Add to main Window
        MainWindow.add(treePane);
        MainWindow.add(dropDownPanel);
       // MainWindow.add(tablePane);

        // set main window
        MainWindow.getContentPane().setBackground(Color.DARK_GRAY);
        MainWindow.setIconImage(windowIcon.getImage());
        MainWindow.setTitle("DemonWorld Armee Generator");
        MainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainWindow.setSize(300, 400);
        MainWindow.setVisible(true);
        MainWindow.setLayout(null);


    }

}
