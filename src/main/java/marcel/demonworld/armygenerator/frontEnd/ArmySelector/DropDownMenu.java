package marcel.demonworld.armygenerator.frontEnd.ArmySelector;

import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.frontEnd.treeView.ArmyTreeView;
import marcel.demonworld.armygenerator.services.SelectArmyService;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

//combobox
@Component
public class DropDownMenu {

    @Autowired
    private SelectArmyService service;
    @Autowired
    ArmySelection armySelection;
    @Autowired
    ArmyTreeView armyTreeView;



    public JComboBox<String> createDropDownMenuForArmyNames() {

        JComboBox<String> DropDownMenu = new JComboBox<>(service.returnArmyNames());

        ActionListener selectActionListener = e -> {
            // global method / class for setting faction and therefore calculator....
            armySelection.setSelectedArmy((String) DropDownMenu.getSelectedItem());
            System.out.println("<<FIRED:::getSelectedItem ->" + (String) DropDownMenu.getSelectedItem());

        };

        DropDownMenu.addActionListener(selectActionListener);

        return DropDownMenu;
    }
}




