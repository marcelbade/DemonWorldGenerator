package marcel.demonworld.armygenerator.frontEnd.ArmySelector;

import marcel.demonworld.armygenerator.frontEnd.treeView.ArmyTreeView;
import marcel.demonworld.armygenerator.frontEnd.treeView.DtoTreeNode;
import marcel.demonworld.armygenerator.services.SelectArmyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionListener;


//combobox
@Component
public class DropDownMenu {

    @Autowired
    private SelectArmyService service;
    @Autowired
    ArmySelection armySelection;
    @Autowired
    ArmyTreeView armyTreeView;

    DtoTreeNode<String> dtoTreeNode = new DtoTreeNode<>();

    public JComboBox<String> createDropDownMenuForArmyNames() {

        JComboBox<String> DropDownMenu = new JComboBox<>(service.returnArmyNames());

        ActionListener selectActionListener = e -> {
            // global method / class for setting faction and therefore calculator....
            armySelection.setSelectedArmy((String) DropDownMenu.getSelectedItem());
            armyTreeView.reloadTree();
        };
        DropDownMenu.addActionListener(selectActionListener);
        return DropDownMenu;
    }
}
